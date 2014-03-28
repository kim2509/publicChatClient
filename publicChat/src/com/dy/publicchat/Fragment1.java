package com.dy.publicchat;

import com.actionbarsherlock.app.SherlockFragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
 
public class Fragment1 extends SherlockFragment {
    
	TabHost mTabHost;
	ViewPager  mViewPager;
	TabsAdapter mTabsAdapter;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment1, container, false);
		
        mTabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);
		mTabHost.setup();
		
		mViewPager = (ViewPager) rootView.findViewById(R.id.pager);

		mTabsAdapter = new TabsAdapter( getActivity(), mTabHost, mViewPager);

		Bundle args = new Bundle();
//		args.putDouble("latitude", );
//		args.putDouble("longitude", location.getLongitude());
		
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
		
        return rootView;
    }
 
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		
		
	}
}