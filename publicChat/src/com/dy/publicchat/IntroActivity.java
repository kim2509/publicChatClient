package com.dy.publicchat;

import org.json.JSONObject;

import com.dy.common.Constants;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class IntroActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		try
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_intro);	
		}
		catch( Exception ex )
		{
			catchException(ex);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.intro, menu);
		return true;
	}
	
	public void tempIDLogin_Clicked( View v )
	{
		try
		{
			final Intent mainIntent = new Intent(IntroActivity.this, RegisterAsRandomID.class);
			IntroActivity.this.startActivity(mainIntent);
			IntroActivity.this.finish();
		}
		catch( Exception ex )
		{
			catchException(ex);
		}
	}
}
