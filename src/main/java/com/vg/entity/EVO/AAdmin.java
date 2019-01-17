package com.vg.entity.EVO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AAdmin {

	private String admin_account;
	private String admin_name;
	private Integer admin_rank;
	private String admin_password;
	private Integer admin_status;
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss", timezone="GMT+8")
	private Date last_login_time;
	public String getAdmin_account() {
		return admin_account;
	}
	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public Integer getAdmin_rank() {
		return admin_rank;
	}
	public void setAdmin_rank(Integer admin_rank) {
		this.admin_rank = admin_rank;
	}
	public Integer getAdmin_status() {
		return admin_status;
	}
	public void setAdmin_status(Integer admin_status) {
		this.admin_status = admin_status;
	}
	public Date getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	
	
}
