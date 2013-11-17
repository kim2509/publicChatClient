package com.dy.publicchat;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.dy.common.Constants;
import com.dy.common.DiscussArrayAdapter;
import com.dy.common.OneComment;

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
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		try
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_chat);
			
			initializeChatListView();
			
			addChatFetchTimer();
			
			fetchMessageHandler = new Handler(){
				
				@Override
		        public void handleMessage(Message msg) {
		            super.handleMessage(msg);
		             
		            switch (msg.what) {
		            case 1:
		                
		            	execTransReturningString("/fetchMessage.do", msg.obj, Constants.REQUESTCODE_SEND_MSG, false);
		            	
		                break;
		                 
		            case 2:
		                
		                break;
		 
		            default:
		                break;
		            }
		        }
				
			};
		}
		catch( Exception ex )
		{
			catchException(ex);
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
					adapter.add(new OneComment(false, edtInput.getText().toString()));
					edtInput.setText("");
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
				adapter.add(new OneComment(false , edtInput.getText().toString()));
				edtInput.setText("");
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
}
