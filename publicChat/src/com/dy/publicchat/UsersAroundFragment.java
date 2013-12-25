package com.dy.publicchat;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.dy.common.HttpTransactionReturningString;
import com.dy.common.TransactionDelegate;
import com.dy.domain.User;

public class UsersAroundFragment extends BaseListFragment implements TransactionDelegate
{

	// This is the Adapter being used to display the list's data.
	UserListAdapter mAdapter;

	// If non-null, this is the current filter the user has provided.
	String mCurFilter;

	@Override public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		try
		{
			// Give some text to display if there is no data.  In a real
			// application this would come from a resource.
			setEmptyText("No users around.");

			setListShown(false);

			mAdapter = new UserListAdapter( getActivity() );
			setListAdapter(mAdapter);
			
			Bundle args = getArguments();
			
			JSONObject requestObj = new JSONObject();
			requestObj.put("userNo", getMetaInfoString("userNo") );
			requestObj.put("latitude", args.getDouble("latitude"));
			requestObj.put("longitude", args.getDouble("longitude"));
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

	@Override
	public void doPostTransaction(int requestCode, Object result) {
		// TODO Auto-generated method stub
		try
		{
			Log.i("data return", result.toString());
			
			ObjectMapper mapper = new ObjectMapper();
			List<User> list = mapper.readValue(result.toString(), new TypeReference<List<User>>(){});
			mAdapter.setData( list );
			mAdapter.notifyDataSetChanged();
			
			setListShown(true);
		}
		catch( Exception ex )
		{
			Log.e("error", ex.getMessage() );
		}
	}
}