package com.dy.publicchat;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class IntroActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		try
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_intro);
			
			RelativeLayout layoutLogin = (RelativeLayout) findViewById(R.id.layoutLogin);
			
			if ( !"".equals( getMetaInfoString("userNo") ) )
			{
				layoutLogin.setVisibility(ViewGroup.GONE);
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						final Intent mainIntent = new Intent(IntroActivity.this, MainActivity.class);
						startActivity(mainIntent);
						finish();
					}
				}, 1000);	
			}
		}
		catch( Exception ex )
		{
			catchException(ex);
		}
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
