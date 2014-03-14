package com.dy.publicchat;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.dy.domain.Chat;
import com.dy.domain.ChatRoom;
import com.dy.domain.Keyword;
import com.dy.domain.User;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
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
			
			String roomListResult = getChatRoomList();
			ObjectMapper mapper = new ObjectMapper();
			List<ChatRoom> data = mapper.readValue(roomListResult, new TypeReference<List<ChatRoom>>(){});
			
			roomListAdapter.setData(data);
			listChatRooms.setAdapter( roomListAdapter );
			roomListAdapter.notifyDataSetChanged();
			LayoutParams param = listChatRooms.getLayoutParams();
			param.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		}
		catch( Exception ex )
		{
			Log.e("ERROR", ex.getMessage());
		}
	} 
	
	public String getChatRoomList() throws Exception
	{
		List<ChatRoom> obj = new ArrayList<ChatRoom>();
		
		ChatRoom room = new ChatRoom();
		room.setTitle("오늘 영화볼 사람");
		room.setMaxNumOfUsers(4);
		room.setLocation("구로구 구로동");
		List<User> userList = new ArrayList<User>();
		userList.add( new User() );
		room.setUserList( userList );
		List<Keyword> keywordList = new ArrayList<Keyword>();
		keywordList.add(new Keyword("20대"));
		keywordList.add(new Keyword("학생"));
		keywordList.add(new Keyword("영화"));
		room.setKeywordList(keywordList);
		obj.add( room );
		
		room = new ChatRoom();
		room.setTitle("구로에서 번개");
		room.setMaxNumOfUsers(4);
		room.setLocation("강남구 역삼동");
		userList = new ArrayList<User>();
		userList.add( new User() );
		room.setUserList( userList );
		keywordList = new ArrayList<Keyword>();
		keywordList.add(new Keyword("30대"));
		keywordList.add(new Keyword("직장인"));
		keywordList.add(new Keyword("삼겹살"));
		room.setKeywordList(keywordList);
		obj.add( room );
		
		room = new ChatRoom();
		room.setTitle("싱글 파티");
		room.setMaxNumOfUsers(4);
		room.setLocation("여의도");
		userList = new ArrayList<User>();
		userList.add( new User() );
		userList.add( new User() );
		room.setUserList( userList );
		keywordList = new ArrayList<Keyword>();
		keywordList.add(new Keyword("20대"));
		keywordList.add(new Keyword("학생"));
		keywordList.add(new Keyword("미팅"));
		room.setKeywordList(keywordList);
		obj.add( room );
		
		room = new ChatRoom();
		room.setTitle("강남 밤사");
		room.setMaxNumOfUsers(4);
		room.setLocation("강남");
		userList = new ArrayList<User>();
		userList.add( new User() );
		userList.add( new User() );
		userList.add( new User() );
		userList.add( new User() );
		room.setUserList( userList );
		keywordList = new ArrayList<Keyword>();
		keywordList.add(new Keyword("30대"));
		keywordList.add(new Keyword("직장인"));
		keywordList.add(new Keyword("춤"));
		room.setKeywordList(keywordList);
		obj.add( room );
		
		room = new ChatRoom();
		room.setTitle("역삼 번개");
		room.setMaxNumOfUsers(4);
		room.setLocation("역삼동");
		userList = new ArrayList<User>();
		userList.add( new User() );
		room.setUserList( userList );
		keywordList = new ArrayList<Keyword>();
		keywordList.add(new Keyword("30대"));
		keywordList.add(new Keyword("직장인"));
		keywordList.add(new Keyword("회"));
		room.setKeywordList(keywordList);
		obj.add( room );
		
		room = new ChatRoom();
		room.setTitle("강남 스크린 골프");
		room.setMaxNumOfUsers(4);
		room.setLocation("강남역");
		userList = new ArrayList<User>();
		userList.add( new User() );
		userList.add( new User() );
		userList.add( new User() );
		room.setUserList( userList );
		keywordList = new ArrayList<Keyword>();
		keywordList.add(new Keyword("40대"));
		keywordList.add(new Keyword("직장인"));
		keywordList.add(new Keyword("골프"));
		room.setKeywordList(keywordList);
		obj.add( room );
		
		room = new ChatRoom();
		room.setTitle("대학로 연극번개");
		room.setLocation("혜화");
		room.setMaxNumOfUsers(4);
		userList = new ArrayList<User>();
		userList.add( new User() );
		userList.add( new User() );
		room.setUserList( userList );
		obj.add( room );
		
		room = new ChatRoom();
		room.setTitle("족발 먹을 사람~");
		room.setLocation("성수동");
		room.setMaxNumOfUsers(4);
		userList = new ArrayList<User>();
		userList.add( new User() );
		userList.add( new User() );
		room.setUserList( userList );
		obj.add( room );
		
		room = new ChatRoom();
		room.setTitle("프로그래머 모집.");
		room.setLocation("역삼동");
		room.setMaxNumOfUsers(4);
		userList = new ArrayList<User>();
		userList.add( new User() );
		userList.add( new User() );
		room.setUserList( userList );
		keywordList = new ArrayList<Keyword>();
		keywordList.add(new Keyword("30대"));
		keywordList.add(new Keyword("직장인"));
		keywordList.add(new Keyword("맛집"));
		room.setKeywordList(keywordList);
		obj.add( room );
		
		room = new ChatRoom();
		room.setTitle("카풀 할 사람");
		room.setLocation("부산");
		room.setMaxNumOfUsers(4);
		userList = new ArrayList<User>();
		userList.add( new User() );
		userList.add( new User() );
		userList.add( new User() );
		room.setUserList( userList );
		keywordList = new ArrayList<Keyword>();
		keywordList.add(new Keyword("30대"));
		keywordList.add(new Keyword("직장인"));
		keywordList.add(new Keyword("드라이브"));
		room.setKeywordList(keywordList);
		obj.add( room );
		
		room = new ChatRoom();
		room.setTitle("2대2 미팅할 사람");
		room.setLocation("구로동");
		room.setMaxNumOfUsers(4);
		userList = new ArrayList<User>();
		userList.add( new User() );
		room.setUserList( userList );
		keywordList = new ArrayList<Keyword>();
		keywordList.add(new Keyword("30대"));
		keywordList.add(new Keyword("직장인"));
		keywordList.add(new Keyword("미팅"));
		room.setKeywordList(keywordList);
		obj.add( room );

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
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
