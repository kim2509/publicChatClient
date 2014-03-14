package com.dy.domain;

public class ChatRoom {

	private int ROOM_ID = 0;

	private String title = "";

	private int numOfUsers = 0;
	
	private int maxNumOfUsers = 0;
	
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

	public int getNumOfUsers() {
		return numOfUsers;
	}

	public void setNumOfUsers(int numOfUsers) {
		this.numOfUsers = numOfUsers;
	}

	public int getMaxNumOfUsers() {
		return maxNumOfUsers;
	}

	public void setMaxNumOfUsers(int maxNumOfUsers) {
		this.maxNumOfUsers = maxNumOfUsers;
	}
	
}
