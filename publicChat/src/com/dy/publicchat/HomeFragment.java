package com.dy.publicchat;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.dy.domain.Chat;
import com.dy.domain.ChatRoom;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Button;

public class HomeFragment extends BaseFragment{

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		try
		{
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			
			ListView listChatRooms = (ListView) getActivity().findViewById(R.id.listChatRooms);
			
			ChatRoomListAdapter roomListAdapter = new ChatRoomListAdapter( getActivity() );
			
			String roomListResult = "[{\"title\":\"타이틀\", \"maxNumOfUsers\":4, \"numOfUsers\":2 }, {\"title\":\"타이틀\", \"maxNumOfUsers\":4, \"numOfUsers\":2 }]";
			ObjectMapper mapper = new ObjectMapper();
			List<ChatRoom> data = mapper.readValue(roomListResult, new TypeReference<List<ChatRoom>>(){});
			roomListAdapter.setData(data);
			listChatRooms.setAdapter( roomListAdapter );
			
		}
		catch( Exception ex )
		{
			Log.e("ERROR", ex.getMessage());
		}
	} 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_home, container, false);
		                      
		Button btnQuickChat = (Button) v.findViewById(R.id.btnQuickChat);
		btnQuickChat.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showToastMessage("quick");
			}
		});
		
		return v;
	}
}
