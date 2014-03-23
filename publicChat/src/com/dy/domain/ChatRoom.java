package com.dy.domain;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.dy.publicchat.R;

import android.view.View;
import android.widget.TextView;

public class ChatRoom extends HomeListItem{

	private int ROOM_ID = 0;

	private String title = "";

	private int maxNumOfUsers = 0;
	
	private List<User> userList = null;
	
	private List<Keyword> keywordList = null;
	
	private String location = "";
	
	public int getROOM_ID() {
		return ROOM_ID;
	}

	public void setROOM_ID(int rOOM_ID) {
		ROOM_ID = rOOM_ID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonIgnore
	public int getNumOfUsers() {
		return (userList == null )? 0:userList.size();
	}

	public int getMaxNumOfUsers() {
		return maxNumOfUsers;
	}

	public void setMaxNumOfUsers(int maxNumOfUsers) {
		this.maxNumOfUsers = maxNumOfUsers;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Keyword> getKeywordList() {
		return keywordList;
	}

	public void setKeywordList(List<Keyword> keywordList) {
		this.keywordList = keywordList;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	@JsonIgnore
	public void setViewData(View view) {
		// TODO Auto-generated method stub
		super.setViewData(view);
		
		TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
		txtTitle.setText( getTitle() );
		
		TextView txtNumOfUsers = (TextView) view.findViewById(R.id.txtNumOfUsers);
		txtNumOfUsers.setText( getNumOfUsers() + "/" + getMaxNumOfUsers() );
		
		TextView txtLocation = (TextView) view.findViewById(R.id.txtLocation);
		txtLocation.setText( getLocation() );
		
		TextView txtKeyword1 = (TextView) view.findViewById(R.id.txtKeyword1);
		TextView txtKeyword2 = (TextView) view.findViewById(R.id.txtKeyword2);
		TextView txtKeyword3 = (TextView) view.findViewById(R.id.txtKeyword3);
		
		txtKeyword1.setText("");
		txtKeyword2.setText("");
		txtKeyword3.setText("");
		
		if ( getKeywordList() != null )
		{
			for ( int i = 0; i < getKeywordList().size(); i++ )
			{
				Keyword keyword = getKeywordList().get(i);
				switch( i )
				{
				case 0:
					txtKeyword1.setText( keyword.getName() );
					break;
				case 1:
					txtKeyword2.setText( keyword.getName() );
					break;
				case 2:
					txtKeyword3.setText( keyword.getName() );
					break;
					default:
						break;
				}
				
				if ( i == 2 ) break;
			}
		}
	}
}
