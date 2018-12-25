package com.vg.config.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPhoneNub {

	/**
	 * 通用判断
	 * 
	 * @param telNum
	 * @return
	 */
	public static boolean isMobiPhoneNum(String telNum) {
		String regex = "^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(telNum);
		return m.matches();
	}

	/**
	 * 更严格的判断
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNum(String telNum) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(telNum);
		return m.matches();
	}

}
