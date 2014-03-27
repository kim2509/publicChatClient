package com.dy.publicchat;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
 
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;

import android.widget.TabHost;

public class MainActivity extends BaseActivity {

	TabHost mTabHost;
	ViewPager  mViewPager;
	TabsAdapter mTabsAdapter;
	
	// Declare Variables
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;
    MenuListAdapter mMenuAdapter;
    String[] title;
    String[] subtitle;
    int[] icon;
    Fragment fragment1 = new Fragment1();
    Fragment fragment2 = new Fragment2();
    Fragment fragment3 = new Fragment3();
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.drawer_main);
			
			/*
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
			*/
			
			// Get the Title
	        mTitle = mDrawerTitle = getTitle();
	 
	        // Generate title
	        title = new String[] { "Title Fragment 1", "Title Fragment 2",
	                "Title Fragment 3" };
	 
	        // Generate subtitle
	        subtitle = new String[] { "Subtitle Fragment 1", "Subtitle Fragment 2",
	                "Subtitle Fragment 3" };
	        
	     // Generate icon
	        icon = new int[] { R.drawable.action_about, R.drawable.action_settings,
	                R.drawable.collections_cloud };
	 
	        // Locate DrawerLayout in drawer_main.xml
	        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	 
	        // Locate ListView in drawer_main.xml
	        mDrawerList = (ListView) findViewById(R.id.listview_drawer);
	 
	        // Set a custom shadow that overlays the main content when the drawer
	        // opens
	        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
	                GravityCompat.START);
	 
	        // Pass string arrays to MenuListAdapter
	        mMenuAdapter = new MenuListAdapter(MainActivity.this, title, subtitle,
	                icon);
	 
	        // Set the MenuListAdapter to the ListView
	        mDrawerList.setAdapter(mMenuAdapter);
	 
	        // Capture listview menu item click
	        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
	 
	        // Enable ActionBar app icon to behave as action to toggle nav drawer
	        getSupportActionBar().setHomeButtonEnabled(true);
	        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	 
	        // ActionBarDrawerToggle ties together the the proper interactions
	        // between the sliding drawer and the action bar app icon
	        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
	                R.drawable.ic_drawer, R.string.drawer_open,
	                R.string.drawer_close) {
	 
	            public void onDrawerClosed(View view) {
	                // TODO Auto-generated method stub
	                super.onDrawerClosed(view);
	            }
	 
	            public void onDrawerOpened(View drawerView) {
	                // TODO Auto-generated method stub
	                // Set the title on the action when drawer open
	                getSupportActionBar().setTitle(mDrawerTitle);
	                super.onDrawerOpened(drawerView);
	            }
	        };
	 
	        mDrawerLayout.setDrawerListener(mDrawerToggle);
	 
	        if (savedInstanceState == null) {
	            selectItem(0);
	        }
		}
		catch( Exception ex )
		{
		}

	}
	
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
 
        if (item.getItemId() == android.R.id.home) {
 
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }
 
        return super.onOptionsItemSelected(item);
    }
	
	
	// ListView click listener in the navigation drawer
    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            selectItem(position);
        }
    }
    
    private void selectItem(int position) {
    	 
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Locate Position
        switch (position) {
        case 0:
            ft.replace(R.id.content_frame, fragment1);
            break;
        case 1:
            ft.replace(R.id.content_frame, fragment2);
            break;
        case 2:
            ft.replace(R.id.content_frame, fragment3);
            break;
        }
        ft.commit();
        mDrawerList.setItemChecked(position, true);
 
        // Get the title followed by the position
        setTitle(title[position]);
        // Close drawer
        mDrawerLayout.closeDrawer(mDrawerList);
    }
	
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
 
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }
}
