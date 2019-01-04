package com.vg.entity;

import java.util.Date;

public class authorization_code {
	private Integer code_id;
	private String code_content;
	private String apply_admin;
	private Date apply_time;
	private Integer used_state;

	public Integer getCode_id() {
		return code_id;
	}

	public void setCode_id(Integer code_id) {
		this.code_id = code_id;
	}

	public String getCode_content() {
		return code_content;
	}

	public void setCode_content(String code_content) {
		this.code_content = code_content;
	}

	public String getApply_admin() {
		return apply_admin;
	}

	public void setApply_admin(String apply_admin) {
		this.apply_admin = apply_admin;
	}

	public Date getApply_time() {
		return apply_time;
	}

	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}

	public Integer getUsed_state() {
		return used_state;
	}

	public void setUsed_state(Integer used_state) {
		this.used_state = used_state;
	}

	public authorization_code(Integer code_id, String code_content, String apply_admin, Date apply_time,
			Integer used_state) {
		super();
		this.code_id = code_id;
		this.code_content = code_content;
		this.apply_admin = apply_admin;
		this.apply_time = apply_time;
		this.used_state = used_state;
	}

	public authorization_code() {
		super();
		// TODO Auto-generated constructor stub
	}

}
