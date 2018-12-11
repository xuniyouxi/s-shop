package com.vg.entity.EVO;

public class UserLogin {
	private String user_phone;
	private String user_password;
	private String user_equipment_id;

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

	public String getUser_equipment_id() {
		return user_equipment_id;
	}

	public void setUser_equipment_id(String user_equipment_id) {
		this.user_equipment_id = user_equipment_id;
	}

	public UserLogin(String user_phone, String user_password, String user_equipment_id) {
		super();
		this.user_phone = user_phone;
		this.user_password = user_password;
		this.user_equipment_id = user_equipment_id;
	}

	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserLogin [user_phone=" + user_phone + ", user_password=" + user_password + ", user_equipment_id="
				+ user_equipment_id + "]";
	}

}
