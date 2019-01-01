package com.vg.entity;

import java.util.Date;

public class IdentifyCode {
	private String user_phone;

	private Integer identify_code;

	private Integer used_static;

	private Integer used_method;

	private Date used_time;

	public IdentifyCode(String user_phone, Integer identify_code, Date used_time) {
		super();
		this.user_phone = user_phone;
		this.identify_code = identify_code;
		this.used_time = used_time;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public Integer getIdentify_code() {
		return identify_code;
	}

	public void setIdentify_code(Integer identify_code) {
		this.identify_code = identify_code;
	}

	public Integer getUsed_static() {
		return used_static;
	}

	public void setUsed_static(Integer used_static) {
		this.used_static = used_static;
	}

	public Integer getUsed_method() {
		return used_method;
	}

	public void setUsed_method(Integer used_method) {
		this.used_method = used_method;
	}

	public Date getUsed_time() {
		return used_time;
	}

	public void setUsed_time(Date used_time) {
		this.used_time = used_time;
	}

	public IdentifyCode(String user_phone, Integer identify_code, Integer used_static, Integer used_method,
			Date used_time) {
		super();
		this.user_phone = user_phone;
		this.identify_code = identify_code;
		this.used_static = used_static;
		this.used_method = used_method;
		this.used_time = used_time;
	}

	public IdentifyCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "IdentifyCode [user_phone=" + user_phone + ", identify_code=" + identify_code + ", used_static="
				+ used_static + ", used_method=" + used_method + ", used_time=" + used_time + "]";
	}

}