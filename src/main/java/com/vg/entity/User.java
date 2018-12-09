package com.vg.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User implements Serializable {
	/**
	 * 
	 */
	private String user_id;

	private Integer user_role;

	private String user_phone;

	private String user_password;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getUser_role() {
		return user_role;
	}

	public void setUser_role(Integer user_role) {
		this.user_role = user_role;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public User(String user_id, Integer user_role, String user_phone, String user_password, Date create_time) {
		super();
		this.user_id = user_id;
		this.user_role = user_role;
		this.user_phone = user_phone;
		this.user_password = user_password;
		this.create_time = create_time;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}