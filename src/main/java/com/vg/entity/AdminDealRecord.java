package com.vg.entity;

import java.sql.Timestamp;

public class AdminDealRecord {

	private String admin_account;
	private String deal_user_id;
	private String deal_content;
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Timestamp deal_time;
	public AdminDealRecord() {}
	public AdminDealRecord(String admin_account, String deal_user_id, String deal_content, Timestamp deal_time) {
		super();
		this.admin_account = admin_account;
		this.deal_user_id = deal_user_id;
		this.deal_content = deal_content;
		this.deal_time = deal_time;
	}
	public AdminDealRecord(String admin_account, String deal_content, Timestamp deal_time) {
		super();
		this.admin_account = admin_account;
		this.deal_content = deal_content;
		this.deal_time = deal_time;
	}
	public String getAdmin_account() {
		return admin_account;
	}
	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}
	public String getDeal_user_id() {
		return deal_user_id;
	}
	public void setDeal_user_id(String deal_user_id) {
		this.deal_user_id = deal_user_id;
	}
	public String getDeal_content() {
		return deal_content;
	}
	public void setDeal_content(String deal_content) {
		this.deal_content = deal_content;
	}
	public Timestamp getDeal_time() {
		return deal_time;
	}
	public void setDeal_time(Timestamp deal_time) {
		this.deal_time = deal_time;
	}
	
}
