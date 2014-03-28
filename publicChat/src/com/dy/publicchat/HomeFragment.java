package com.dy.publicchat;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.dy.domain.Chat;
import com.dy.domain.ChatRoom;
import com.dy.domain.HomeListItem;
import com.dy.domain.HomeListItemBtns;
import com.dy.domain.HomeListItemDesc1;
import com.dy.domain.HomeListItemDesc2;
import com.dy.domain.Keyword;
import com.dy.domain.User;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.Button;

public class HomeFragment extends BaseFragment{

	HomeListAdapter homeListAdapter = null;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		try
		{
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);

			ListView listHome = (ListView) getActivity().findViewById(R.id.listHome);

			homeListAdapter = new HomeListAdapter( getActivity() );

			String roomListResult = getChatRoomList();
			ObjectMapper mapper = new ObjectMapper();
			ArrayList<HomeListItem> data = new ArrayList<HomeListItem>();

			data.add( new HomeListItemBtns() );

			data.add( new HomeListItemDesc1() );

			List<HomeListItem> roomList = mapper.readValue(roomListResult, new TypeReference<List<ChatRoom>>(){});
			data.addAll( roomList );

			data.add( new HomeListItemDesc2() );

			homeListAdapter.setData(data);
			listHome.setAdapter( homeListAdapter );
		}
		catch( Exception ex )
		{
			Log.e("ERROR", ex.getMessage());
		}
	} 

	@Override
	public void onResume() {
		// TODO Auto-generated method stub

		try
		{
			super.onResume();


		}
		catch(Exception ex )
		{

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
		userList.add( new User("http://www.hanintownsg.com/58_run/1.png") );
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
		userList.add( new User("http://www.hanintownsg.com/58_run/2.png") );
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
		userList.add( new User("http://www.hanintownsg.com/58_run/3.png") );
		userList.add( new User("http://www.hanintownsg.com/58_run/4.png") );
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
		userList.add( new User("http://www.hanintownsg.com/58_run/5.png") );
		userList.add( new User("http://www.hanintownsg.com/58_run/6.png") );
		userList.add( new User("http://www.hanintownsg.com/58_run/7.png") );
		userList.add( new User("http://www.hanintownsg.com/58_run/8.png") );
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
		userList.add( new User("http://www.hanintownsg.com/58_run/9.png") );
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
		userList.add( new User("http://www.hanintownsg.com/58_run/10.png") );
		userList.add( new User("http://www.hanintownsg.com/58_run/11.png") );
		userList.add( new User("http://www.hanintownsg.com/58_run/12.png") );
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
		userList.add( new User("http://www.hanintownsg.com/58_run/1.png") );
		userList.add( new User("http://www.hanintownsg.com/58_run/2.png") );
		room.setUserList( userList );
		obj.add( room );

		room = new ChatRoom();
		room.setTitle("족발 먹을 사람~");
		room.setLocation("성수동");
		room.setMaxNumOfUsers(4);
		userList = new ArrayList<User>();
		userList.add( new User("http://www.hanintownsg.com/58_run/3.png") );
		userList.add( new User("http://www.hanintownsg.com/58_run/4.png") );
		room.setUserList( userList );
		obj.add( room );

		room = new ChatRoom();
		room.setTitle("프로그래머 모집.");
		room.setLocation("역삼동");
		room.setMaxNumOfUsers(4);
		userList = new ArrayList<User>();
		userList.add( new User("http://www.hanintownsg.com/58_run/5.png") );
		userList.add( new User("http://www.hanintownsg.com/58_run/6.png") );
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
		userList.add( new User("http://www.hanintownsg.com/58_run/7.png") );
		userList.add( new User("http://www.hanintownsg.com/58_run/8.png") );
		userList.add( new User("http://www.hanintownsg.com/58_run/9.png") );
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
		userList.add( new User("http://www.hanintownsg.com/58_run/10.png") );
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

		//		Button btnQuickChat = (Button) v.findViewById(R.id.btnQuickChat);
		//		btnQuickChat.setOnClickListener( new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				showToastMessage("quick");
		//			}
		//		});

		return v;
	}
}
