package com.dy.publicchat;

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
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
			mTabHost = (TabHost)findViewById(android.R.id.tabhost);
			mTabHost.setup();

			mViewPager = (ViewPager)findViewById(R.id.pager);

			mTabsAdapter = new TabsAdapter(this, mTabHost, mViewPager);

			Bundle args = new Bundle();
			args.putDouble("latitude", location.getLatitude());
			args.putDouble("longitude", location.getLongitude());
			
			mTabsAdapter.addTab(mTabHost.newTabSpec("홈").setIndicator("홈"),
					HomeFragment.class, args );
			
			mTabsAdapter.addTab(mTabHost.newTabSpec("채팅").setIndicator("채팅"),
					ChatListFragment.class, args);
			
			mTabsAdapter.addTab(mTabHost.newTabSpec("친구").setIndicator("친구"),
					CafeListFragment.class, args);
			
			mTabsAdapter.addTab(mTabHost.newTabSpec("더보기").setIndicator("더보기"),
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
