package com.dy.publicchat;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dy.common.Constants;
import com.dy.common.UserListAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
			JSONObject requestObj = new JSONObject();
			requestObj.put("userNo", getMetaInfoString("userNo") );
			requestObj.put("latitude", location.getLatitude());
			requestObj.put("longitude", location.getLongitude());
			execTransReturningString("/mainInfo.do", requestObj, Constants.REQUESTCODE_SAVE_USER_PROFILE_KEYWORDS, false );
		}
		catch( Exception ex )
		{
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void doPostTransaction(int requestCode, Object result) {
		// TODO Auto-generated method stub
		try
		{
			if ( requestCode == Constants.REQUESTCODE_SAVE_USER_PROFILE_KEYWORDS )
				displayUsersAround( result );
			
			super.doPostTransaction(requestCode, result);	
		}
		catch( Exception ex )
		{
			catchException(ex);
		}
	}
	
	public void displayUsersAround( Object userList ) throws Exception
	{
		TextView txtUsersAround = (TextView) findViewById(R.id.txtUsersAround);
		
		JSONArray users = new JSONArray( userList.toString() );
		txtUsersAround.setText( "근처에 " + users.length() + "명의 사용자가 있습니다.");
		
		ListView listUsersAround = (ListView) findViewById(R.id.listUsersAround);
		listUsersAround.setAdapter( new UserListAdapter(this, users ));
		
		listUsersAround.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				try
				{
					Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
					
					TextView tv = (TextView) v.findViewById(R.id.txtUserNo);
					intent.putExtra("anotherUserNo", tv.getText().toString());
					startActivity(intent);
				}
				catch( Exception ex )
				{
					catchException(ex);
				}
			}
		});
	}
}
