package com.dy.common;

public class Constants {

	public static boolean bReal = false;
	public static String serverURL = bReal ? "http://www.hanintownsg.com/randomMsgServer" : "http://192.168.10.105:8080/randomMsgServer";
	
	public final static String SUCCESS = "0000";
	public final static String FAIL = "9999";
	
	public static int REQUESTCODE_COMMON = 1;
	public static int REQUESTCODE_SAVE_SEX = 2;
	public static int REQUESTCODE_SAVE_BIRTHYEAR = 3;
}
