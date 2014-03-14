package com.dy.domain;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class ChatRoom {

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
	
}
