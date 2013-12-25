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

import com.dy.common.HttpTransactionReturningString;
import com.dy.common.TransactionDelegate;
import com.dy.domain.Chat;
import com.dy.domain.User;

public class ChatListFragment extends BaseListFragment implements TransactionDelegate
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
			new HttpTransactionReturningString( this, "/chatList.do", 1 ).execute( requestObj );
		}
		catch( Exception ex )
		{
			Log.e("error", ex.getMessage());
		}
	}
	
	@Override public void onListItemClick(ListView l, View v, int position, long id) {
		// Insert desired behavior here.
		Log.i("FragmentComplexList", "Item clicked: " + id);
		Chat chat = mAdapter.getItem(position);
		
		Intent intent = new Intent( getActivity(), ChatActivity.class);
		intent.putExtra("anotherUserNo", String.valueOf( chat.getUserNo() ) );
		startActivity(intent);
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