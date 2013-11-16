package com.dy.publicchat;

import org.json.JSONObject;

import com.dy.common.Constants;

import android.os.Bundle;
import android.view.Menu;

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
			showToastMessage( result.toString() );
			
			super.doPostTransaction(requestCode, result);	
		}
		catch( Exception ex )
		{
			catchException(ex);
		}
	}
}
