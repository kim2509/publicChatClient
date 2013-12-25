package com.dy.publicchat;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.actionbarsherlock.app.SherlockListFragment;

public class BaseListFragment extends SherlockListFragment 
{
	public String getMetaInfoString( String key )
	{
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences( getActivity() );
		
		String value = settings.getString(key, "");
		if ("".equals( value ))
			return "";
		
	    return value;
	}
}
