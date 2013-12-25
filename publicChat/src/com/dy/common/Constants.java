package com.dy.common;

public class Constants {

	public static boolean bReal = true;
	public static String serverURL = bReal ? "http://www.hanintownsg.com/randomMsgServer" : "http://192.168.10.105:8080/randomMsgServer";
	
	public final static String SUCCESS = "0000";
	public final static String FAIL = "9999";
	
	public static int REQUESTCODE_COMMON = 1;
	public static int REQUESTCODE_SAVE_SEX = 2;
	public static int REQUESTCODE_SAVE_BIRTHYEAR = 3;
	public static int REQUESTCODE_SAVE_USER_PROFILE_KEYWORDS = 4;
	public static int REQUESTCODE_SEND_MSG = 5;
	public static int REQUESTCODE_FETCH_MSG = 6;
}
