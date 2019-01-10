package com.vg.config.Util;


import javax.servlet.http.HttpServletResponse;

public class TokenHeader {

	public static Boolean addTokenToResponseHeder(HttpServletResponse response, String name, String content) {
		if (name == "" || content == "")
			return false;
		response.addHeader(name, content);

		return true;
	}
}
