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
import com.dy.domain.OneComment;

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
        	
			addChatFetchTimer();
			
			fetchMessageHandler = new Handler(){
				
				@Override
		        public void handleMessage(Message msg) {
					
					try
					{
						super.handleMessage(msg);
			             
			            switch (msg.what) {
			            case 1:
			                
			            	
			            	
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

	private void fetchMessages() throws JSONException {
		JSONObject requestObj = new JSONObject();
		requestObj.put("userNo", getMetaInfoString("userNo") );
		requestObj.put("anotherUserNo", otherUsers.get(0));
		execTransReturningString("/fetchMessage.do", requestObj, Constants.REQUESTCODE_FETCH_MSG, false);
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
					adapter.add(new OneComment(false ,getMetaInfoString("userNo"), edtInput.getText().toString(), "" ));
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
					adapter.add(new OneComment(false ,getMetaInfoString("userNo"), edtInput.getText().toString(), "" ));
					
					JSONObject requestObj = new JSONObject();
					requestObj.put("userNo", getMetaInfoString("userNo") );
					requestObj.put("anotherUserNo", otherUsers.get(0));
					requestObj.put("msg", edtInput.getText().toString());
					execTransReturningString("/sendMessage.do", requestObj, Constants.REQUESTCODE_SEND_MSG, false );
					
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
	
	@Override
    protected void onDestroy() {
        tChatFetchTimer.cancel();
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
				ArrayList<OneComment> list = mapper.readValue( result.toString(), new TypeReference<ArrayList<OneComment>>(){});
				adapter.setChatList(list);
				
				fetchMessages();
			}
		}
		catch( Exception ex )
		{
			catchException(ex);
		}
	}
}
