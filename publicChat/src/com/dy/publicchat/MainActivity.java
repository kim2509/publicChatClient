package com.dy.publicchat;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.dy.common.Constants;
import com.dy.common.HttpTransactionReturningString;
import com.dy.common.TransactionDelegate;
import com.dy.common.UserListAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Contacts.People;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SearchViewCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.widget.SearchViewCompat.OnQueryTextListenerCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

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
			setTheme(R.style.Theme_Sherlock_Light);

			/*
			JSONObject requestObj = new JSONObject();
			requestObj.put("userNo", getMetaInfoString("userNo") );
			requestObj.put("latitude", location.getLatitude());
			requestObj.put("longitude", location.getLongitude());
			execTransReturningString("/mainInfo.do", requestObj, Constants.REQUESTCODE_SAVE_USER_PROFILE_KEYWORDS, false );
			 */

			mTabHost = (TabHost)findViewById(android.R.id.tabhost);
			mTabHost.setup();

			mViewPager = (ViewPager)findViewById(R.id.pager);

			mTabsAdapter = new TabsAdapter(this, mTabHost, mViewPager);

			mTabsAdapter.addTab(mTabHost.newTabSpec("simple").setIndicator("Simple"),
					CursorLoaderListFragment.class, null);
			
			mTabsAdapter.addTab(mTabHost.newTabSpec("contacts").setIndicator("Contacts"),
					UsersAroundFragment.class, null );
/*
			mTabsAdapter.addTab(mTabHost.newTabSpec("custom").setIndicator("Custom"),
					LoaderCustomSupport.AppListFragment.class, null);
			mTabsAdapter.addTab(mTabHost.newTabSpec("throttle").setIndicator("Throttle"),
					LoaderThrottleSupport.ThrottledLoaderListFragment.class, null);
*/
			
			if (savedInstanceState != null) {
				mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
			}
		}
		catch( Exception ex )
		{
		}

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
		/*
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
		 */
	}

	/**
	 * This is a helper class that implements the management of tabs and all
	 * details of connecting a ViewPager with associated TabHost.  It relies on a
	 * trick.  Normally a tab host has a simple API for supplying a View or
	 * Intent that each tab will show.  This is not sufficient for switching
	 * between pages.  So instead we make the content part of the tab host
	 * 0dp high (it is not shown) and the TabsAdapter supplies its own dummy
	 * view to show as the tab content.  It listens to changes in tabs, and takes
	 * care of switch to the correct paged in the ViewPager whenever the selected
	 * tab changes.
	 */
	public static class TabsAdapter extends FragmentPagerAdapter
	implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
		private final Context mContext;
		private final TabHost mTabHost;
		private final ViewPager mViewPager;
		private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

		static final class TabInfo {
			private final String tag;
			private final Class<?> clss;
			private final Bundle args;

			TabInfo(String _tag, Class<?> _class, Bundle _args) {
				tag = _tag;
				clss = _class;
				args = _args;
			}
		}

		static class DummyTabFactory implements TabHost.TabContentFactory {
			private final Context mContext;

			public DummyTabFactory(Context context) {
				mContext = context;
			}

			@Override
			public View createTabContent(String tag) {
				View v = new View(mContext);
				v.setMinimumWidth(0);
				v.setMinimumHeight(0);
				return v;
			}
		}

		public TabsAdapter(FragmentActivity activity, TabHost tabHost, ViewPager pager) {
			super(activity.getSupportFragmentManager());
			mContext = activity;
			mTabHost = tabHost;
			mViewPager = pager;
			mTabHost.setOnTabChangedListener(this);
			mViewPager.setAdapter(this);
			mViewPager.setOnPageChangeListener(this);
		}

		public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
			tabSpec.setContent(new DummyTabFactory(mContext));
			String tag = tabSpec.getTag();

			TabInfo info = new TabInfo(tag, clss, args);
			mTabs.add(info);
			mTabHost.addTab(tabSpec);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}

		@Override
		public Fragment getItem(int position) {
			TabInfo info = mTabs.get(position);
			return Fragment.instantiate(mContext, info.clss.getName(), info.args);
		}

		@Override
		public void onTabChanged(String tabId) {
			int position = mTabHost.getCurrentTab();
			mViewPager.setCurrentItem(position);
		}

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		}

		@Override
		public void onPageSelected(int position) {
			// Unfortunately when TabHost changes the current tab, it kindly
			// also takes care of putting focus on it when not in touch mode.
			// The jerk.
			// This hack tries to prevent this from pulling focus out of our
			// ViewPager.
			TabWidget widget = mTabHost.getTabWidget();
			int oldFocusability = widget.getDescendantFocusability();
			widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
			mTabHost.setCurrentTab(position);
			widget.setDescendantFocusability(oldFocusability);
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}
	}

	public static class CursorLoaderListFragment extends SherlockListFragment
	implements LoaderManager.LoaderCallbacks<Cursor> {

		// This is the Adapter being used to display the list's data.
		SimpleCursorAdapter mAdapter;

		// If non-null, this is the current filter the user has provided.
		String mCurFilter;

		@Override public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);

			// Give some text to display if there is no data.  In a real
			// application this would come from a resource.
			setEmptyText("No phone numbers");

			// We have a menu item to show in action bar.
			setHasOptionsMenu(true);

			// Create an empty adapter we will use to display the loaded data.
			mAdapter = new SimpleCursorAdapter(getActivity(),
					android.R.layout.simple_list_item_1, null,
					new String[] { People.DISPLAY_NAME },
					new int[] { android.R.id.text1}, 0);
			setListAdapter(mAdapter);

			// Start out with a progress indicator.
			setListShown(false);

			// Prepare the loader.  Either re-connect with an existing one,
			// or start a new one.
			getLoaderManager().initLoader(0, null, this);
		}

		@Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
			// Place an action bar item for searching.
			MenuItem item = menu.add("Search");
			item.setIcon(android.R.drawable.ic_menu_search);
			item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			SherlockFragmentActivity activity = (SherlockFragmentActivity)getActivity();
			View searchView = SearchViewCompat.newSearchView(activity.getSupportActionBar().getThemedContext());
			if (searchView != null) {
				SearchViewCompat.setOnQueryTextListener(searchView,
						new OnQueryTextListenerCompat() {
					@Override
					public boolean onQueryTextChange(String newText) {
						// Called when the action bar search text has changed.  Update
						// the search filter, and restart the loader to do a new query
						// with this filter.
						mCurFilter = !TextUtils.isEmpty(newText) ? newText : null;
						getLoaderManager().restartLoader(0, null, CursorLoaderListFragment.this);
						return true;
					}
				});
				item.setActionView(searchView);
			}
		}

		@Override public void onListItemClick(ListView l, View v, int position, long id) {
			// Insert desired behavior here.
			Log.i("FragmentComplexList", "Item clicked: " + id);
		}

		// These are the Contacts rows that we will retrieve.
		static final String[] CONTACTS_SUMMARY_PROJECTION = new String[] {
			People._ID,
			People.DISPLAY_NAME,
		};

		public Loader<Cursor> onCreateLoader(int id, Bundle args) {
			// This is called when a new Loader needs to be created.  This
			// sample only has one Loader, so we don't care about the ID.
			// First, pick the base URI to use depending on whether we are
			// currently filtering.
			Uri baseUri;
			if (mCurFilter != null) {
				baseUri = Uri.withAppendedPath(People.CONTENT_FILTER_URI, Uri.encode(mCurFilter));
			} else {
				baseUri = People.CONTENT_URI;
			}

			// Now create and return a CursorLoader that will take care of
			// creating a Cursor for the data being displayed.
			String select = "((" + People.DISPLAY_NAME + " NOTNULL) AND ("
					+ People.DISPLAY_NAME + " != '' ))";
			return new CursorLoader(getActivity(), baseUri,
					CONTACTS_SUMMARY_PROJECTION, select, null,
					People.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
		}

		public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
			// Swap the new cursor in.  (The framework will take care of closing the
			// old cursor once we return.)
			mAdapter.swapCursor(data);

			// The list should now be shown.
			if (isResumed()) {
				setListShown(true);
			} else {
				setListShownNoAnimation(true);
			}
		}

		public void onLoaderReset(Loader<Cursor> loader) {
			// This is called when the last Cursor provided to onLoadFinished()
			// above is about to be closed.  We need to make sure we are no
			// longer using it.
			mAdapter.swapCursor(null);
		}
	}
	
	public static class UsersAroundFragment extends SherlockListFragment implements TransactionDelegate
	{

		// This is the Adapter being used to display the list's data.
		SimpleCursorAdapter mAdapter;

		// If non-null, this is the current filter the user has provided.
		String mCurFilter;

		@Override public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);

			try
			{
				// Give some text to display if there is no data.  In a real
				// application this would come from a resource.
				setEmptyText("No users around.");

				// We have a menu item to show in action bar.
				//setHasOptionsMenu(true);

				setListShown(true);

				// Prepare the loader.  Either re-connect with an existing one,
				// or start a new one.
				//getLoaderManager().initLoader(0, null, this);
				
				JSONObject requestObj = new JSONObject();
				requestObj.put("userNo", getMetaInfoString("userNo") );
				new HttpTransactionReturningString( this, "/mainInfo.do", 1 ).execute( requestObj );
			}
			catch( Exception ex )
			{
				
			}
		}

		@Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
			// Place an action bar item for searching.
			
		}

		@Override public void onListItemClick(ListView l, View v, int position, long id) {
			// Insert desired behavior here.
			Log.i("FragmentComplexList", "Item clicked: " + id);
		}
		
		public String getMetaInfoString( String key )
		{
			SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences( getActivity() );
			
			String value = settings.getString(key, "");
			if ("".equals( value ))
				return "";
			
		    return value;
		}

		@Override
		public void doPostTransaction(int requestCode, Object result) {
			// TODO Auto-generated method stub
			Log.i("data return", result.toString());
		}
		
		public void displayUsersAround( Object userList ) throws Exception
		{
			/*
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
			 */
		}
	}
}
