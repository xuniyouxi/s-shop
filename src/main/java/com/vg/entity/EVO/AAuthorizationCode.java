package com.vg.entity.EVO;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AAuthorizationCode {

	private Integer code_id;
	private String code_content;
	private String apply_admin;
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss", timezone="GMT+8")
	private Timestamp apply_time;
	private Integer used_state;
	public AAuthorizationCode() {}
	
	public AAuthorizationCode(String code_content, String apply_admin, Timestamp apply_time, Integer used_state) {
		super();
		this.code_content = code_content;
		this.apply_admin = apply_admin;
		this.apply_time = apply_time;
		this.used_state = used_state;
	}

	public AAuthorizationCode(Integer code_id, String code_content, String apply_admin, Timestamp apply_time,
			Integer used_state) {
		super();
		this.code_id = code_id;
		this.code_content = code_content;
		this.apply_admin = apply_admin;
		this.apply_time = apply_time;
		this.used_state = used_state;
	}

	public AAuthorizationCode(String code_content, String apply_admin, Timestamp apply_time) {
		super();
		this.code_content = code_content;
		this.apply_admin = apply_admin;
		this.apply_time = apply_time;
	}

	public AAuthorizationCode(Integer code_id, String code_content, String apply_admin, Timestamp apply_time) {
		super();
		this.code_id = code_id;
		this.code_content = code_content;
		this.apply_admin = apply_admin;
		this.apply_time = apply_time;
	}
	public Integer getUsed_state() {
		return used_state;
	}

	public void setUsed_state(Integer used_state) {
		this.used_state = used_state;
	}

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
	public Timestamp getApply_time() {
		return apply_time;
	}
	public void setApply_time(Timestamp apply_time) {
		this.apply_time = apply_time;
	}
	
}
