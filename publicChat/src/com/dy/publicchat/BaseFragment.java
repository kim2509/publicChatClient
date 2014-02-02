package com.dy.publicchat;

import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class BaseFragment extends SherlockFragment{
	public void showToastMessage( String message )
	{
		Toast.makeText( getActivity(), message, Toast.LENGTH_LONG).show();
	}
}
