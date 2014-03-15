package com.dy.publicchat;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONException;
import org.json.JSONObject;

import com.dy.common.Constants;
import com.dy.common.DiscussArrayAdapter;
import com.dy.domain.Comment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ChatActivity extends BaseActivity {

	private DiscussArrayAdapter adapter;
	private ListView lv;
	private EditText edtInput;
	
	private TimerTask tChatFetchTimerTask;
    private Timer tChatFetchTimer;
    Handler fetchMessageHandler;
    
    private ArrayList<String> otherUsers;
    
    private boolean bFetchReady = true;
    private int lastChatID = 0;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		try
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_chat);
			
			otherUsers = new ArrayList<String>();
			Object anotherUserNo = getIntent().getExtras().get("anotherUserNo");
			if ( anotherUserNo != null )
				otherUsers.add( anotherUserNo.toString() );
			
			initializeChatListView();
			
			fetchMessages();
        	
			fetchMessageHandler = new Handler(){
				
				@Override
		        public void handleMessage(Message msg) {
					
					try
					{
						super.handleMessage(msg);
			             
			            switch (msg.what) {
			            case 1:
			                
			            	fetchMessages();
			            	
			                break;
			                 
			            case 2:
			                
			                break;
			 
			            default:
			                break;
			            }	
					}
					catch( Exception ex )
					{
						catchException(ex);
					}
		        }
				
			};
		}
		catch( Exception ex )
		{
			catchException(ex);
		}
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		removeFetchTimer();
	}
		
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		addChatFetchTimer();
	}

	private void fetchMessages() throws JSONException {
		if ( bFetchReady )
		{
			bFetchReady = false;
			JSONObject requestObj = new JSONObject();
			requestObj.put("userNo", getMetaInfoString("userNo") );
			requestObj.put("anotherUserNo", otherUsers.get(0));
			requestObj.put("lastChatID", lastChatID );
			execTransReturningString("/fetchMessage.do", requestObj, Constants.REQUESTCODE_FETCH_MSG );	
		}
	}

	private void initializeChatListView() {
		lv = (ListView) findViewById(R.id.listView1);

		adapter = new DiscussArrayAdapter(getApplicationContext(), R.layout.listitem_discuss);

		lv.setAdapter(adapter);

		edtInput = (EditText) findViewById(R.id.edtInput);
		edtInput.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
				if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
					// Perform action on key press
					adapter.add(new Comment(false ,getMetaInfoString("userNo"), edtInput.getText().toString(), "" ));
					edtInput.setText("");
					scrollMyListViewToBottom();
					return true;
				}
				return false;
			}
		});

		Button btnSend = (Button) findViewById(R.id.btnSend);
		
		btnSend.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try
				{
					adapter.add(new Comment(false ,getMetaInfoString("userNo"), edtInput.getText().toString(), "" ));
					
					JSONObject requestObj = new JSONObject();
					requestObj.put("userNo", getMetaInfoString("userNo") );
					requestObj.put("anotherUserNo", otherUsers.get(0));
					requestObj.put("msg", edtInput.getText().toString());
					execTransReturningString("/sendMessage.do", requestObj, Constants.REQUESTCODE_SEND_MSG );
					
					edtInput.setText("");	
					scrollMyListViewToBottom();
				}
				catch( Exception ex )
				{
					
				}	
			}
		});		
	}
	
	private void scrollMyListViewToBottom() {
		
	    lv.post(new Runnable() {
	        @Override
	        public void run() {
	            // Select the last row so it will scroll into view...
	            lv.setSelection(lv.getCount() - 1);
	        }
	    });
	}
	
	private void addChatFetchTimer()
	{
		if ( tChatFetchTimer != null ) return;
		
		tChatFetchTimerTask = new TimerTask() {
            @Override
            public void run() {
            	
            	try
            	{
                	Message msg = fetchMessageHandler.obtainMessage();
                	msg.what = 1;
                	msg.obj = "test";
                	fetchMessageHandler.sendMessage( msg );	
            	}
            	catch( Exception ex )
            	{
            		Log.e("error", "errorrrr" );
            	}
            }
        };
        
        tChatFetchTimer = new Timer();
        tChatFetchTimer.schedule(tChatFetchTimerTask, 0, 5000);
	}
	
	private void removeFetchTimer()
	{
		if ( tChatFetchTimer == null ) return;
		tChatFetchTimer.cancel();
		tChatFetchTimer = null;
	}
	
	@Override
    protected void onDestroy() {
		removeFetchTimer();
        super.onDestroy();
    }
	
	@Override
	public void doPostTransaction(int requestCode, Object result) {
		// TODO Auto-generated method stub
		try
		{
			super.doPostTransaction(requestCode, result);
			
			if ( requestCode == Constants.REQUESTCODE_FETCH_MSG )
			{
				Log.d("log", result.toString() );
				ObjectMapper mapper = new ObjectMapper();
				ArrayList<Comment> list = mapper.readValue( result.toString(), new TypeReference<ArrayList<Comment>>(){});
				
				if ( list.size() > 0 )
				{
					for ( int i = 0; i < list.size(); i++ )
					{
						Comment comment = list.get(i);
						if ( comment.getSender().equals( getMetaInfoString("userNo")) ) comment.setLeft(false);
						else
							comment.setLeft(true);
						adapter.add( comment );	
					}
					
					lastChatID = list.get(list.size() - 1 ).getChatID();
					
					scrollMyListViewToBottom();	
				}
				
				bFetchReady = true;
			}
		}
		catch( Exception ex )
		{
			catchException(ex);
		}
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);

	    // Checks whether a hardware keyboard is available
	    if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO) {
	    	scrollMyListViewToBottom();
	    } else if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_YES) {
	    	scrollMyListViewToBottom();
	    }
	}
}
