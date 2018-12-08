package com.vg.entity;

import java.util.Date;

public class IdentifyCode {
	private String user_id;

	private Integer identify_code;

	private String used_static;

	private String used_method;

	private Date used_time;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getIdentify_code() {
		return identify_code;
	}

	public void setIdentify_code(Integer identify_code) {
		this.identify_code = identify_code;
	}

	public String getUsed_static() {
		return used_static;
	}

	public void setUsed_static(String used_static) {
		this.used_static = used_static;
	}

	public String getUsed_method() {
		return used_method;
	}

	public void setUsed_method(String used_method) {
		this.used_method = used_method;
	}

	public Date getUsed_time() {
		return used_time;
	}

	public void setUsed_time(Date used_time) {
		this.used_time = used_time;
	}

	public IdentifyCode(String user_id, Integer identify_code, String used_static, String used_method, Date used_time) {
		super();
		this.user_id = user_id;
		this.identify_code = identify_code;
		this.used_static = used_static;
		this.used_method = used_method;
		this.used_time = used_time;
	}

	public IdentifyCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}