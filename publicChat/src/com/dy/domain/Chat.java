package com.dy.domain;

public class Chat {

	private int ROOM_ID = 0;
	
	private String userNo = "";

	private String lastMessage = "";
	
	private String dateTime = "";
	
	public int getROOM_ID() {
		return ROOM_ID;
	}

	public void setROOM_ID(int rOOM_ID) {
		ROOM_ID = rOOM_ID;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
}
