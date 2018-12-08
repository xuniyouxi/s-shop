package com.vg.entity.EVO;

import com.vg.entity.UserData;

public class UserTokenEvo {

	private String user_token;
	private UserData userdata;

	public String getUser_token() {
		return user_token;
	}

	public void setUser_token(String user_token) {
		this.user_token = user_token;
	}

	public UserData getUserdata() {
		return userdata;
	}

	public void setUserdata(UserData userdata) {
		this.userdata = userdata;
	}

	public UserTokenEvo(String user_token, UserData userdata) {
		super();
		this.user_token = user_token;
		this.userdata = userdata;
	}

	public UserTokenEvo() {
		super();
		// TODO Auto-generated constructor stub
	}

}
