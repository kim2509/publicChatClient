package com.dy.domain;

public class OneComment {
	private boolean left;
	private String msg;
	private String sender;
	private String createDate;
	private long roomID;
	private int chatID;

	public OneComment() {
	}
	
	public OneComment(boolean left, String sender, String comment, String createDate ) {
		super();
		this.setLeft(left);
		this.setMsg(comment);
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public long getRoomID() {
		return roomID;
	}

	public void setRoomID(long roomID) {
		this.roomID = roomID;
	}

	public int getChatID() {
		return chatID;
	}

	public void setChatID(int chatID) {
		this.chatID = chatID;
	}
}