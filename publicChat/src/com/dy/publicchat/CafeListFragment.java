package com.dy.publicchat;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;

import com.dy.common.HttpTransactionReturningString;
import com.dy.common.TransactionDelegate;
import com.dy.domain.Chat;

public class CafeListFragment extends BaseListFragment implements TransactionDelegate
{
	ChatListAdapter mAdapter;
	
	@Override public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		try
		{
			// Give some text to display if there is no data.  In a real
			// application this would come from a resource.
			setEmptyText("No users around.");

			setListShown(false);

			mAdapter = new ChatListAdapter( getActivity() );
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
	
	@Override
	public void doPostTransaction(int requestCode, Object result) {
		// TODO Auto-generated method stub
		try
		{
			Log.i("data return", result.toString());
			
			ObjectMapper mapper = new ObjectMapper();
			List<Chat> list = mapper.readValue(result.toString(), new TypeReference<List<Chat>>(){});
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