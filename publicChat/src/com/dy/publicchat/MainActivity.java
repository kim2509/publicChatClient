package com.dy.publicchat;

import com.actionbarsherlock.view.Window;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TabHost;

public class MainActivity extends BaseActivity {

	TabHost mTabHost;
	ViewPager  mViewPager;
	TabsAdapter mTabsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try
		{
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			setTheme(R.style.Theme_Sherlock_Light);

			
			mTabHost = (TabHost)findViewById(android.R.id.tabhost);
			mTabHost.setup();

			mViewPager = (ViewPager)findViewById(R.id.pager);

			mTabsAdapter = new TabsAdapter(this, mTabHost, mViewPager);

			Bundle args = new Bundle();
			args.putDouble("latitude", location.getLatitude());
			args.putDouble("longitude", location.getLongitude());
			
			mTabsAdapter.addTab(mTabHost.newTabSpec("Users").setIndicator("Users"),
					UsersAroundFragment.class, args );
			
			mTabsAdapter.addTab(mTabHost.newTabSpec("chat").setIndicator("chat"),
					ChatListFragment.class, args);
			
			mTabsAdapter.addTab(mTabHost.newTabSpec("cafe").setIndicator("cafe"),
					CafeListFragment.class, args);
			
			mTabsAdapter.addTab(mTabHost.newTabSpec("more").setIndicator("more"),
					MoreListFragment.class, args);
			
			if (savedInstanceState != null) {
				mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
			}
		}
		catch( Exception ex )
		{
		}

	}
}
