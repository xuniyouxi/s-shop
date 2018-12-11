package com.vg.entity.EVO;



public class UserRegister {
	private String user_id;
	private String user_realname;
	private String user_equipment_id1;
	private String invite_code;
	private String user_phone;
	private String user_password;
	private int user_role;

	private String father_inviteCode;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public int getUser_role() {
		return user_role;
	}

	public void setUser_role(int user_role) {
		this.user_role = user_role;
	}

	public String getUser_equipment_id1() {
		return user_equipment_id1;
	}

	public void setUser_equipment_id1(String user_equipment_id1) {
		this.user_equipment_id1 = user_equipment_id1;
	}

	public String getInvite_code() {
		return invite_code;
	}

	public void setInvite_code(String invite_code) {
		this.invite_code = invite_code;
	}

	public String getUser_realname() {
		return user_realname;
	}

	public void setUser_realname(String user_realname) {
		this.user_realname = user_realname;
	}

	public String getFather_inviteCode() {
		return father_inviteCode;
	}

	public void setFather_inviteCode(String father_inviteCode) {
		this.father_inviteCode = father_inviteCode;
	}

	public UserRegister(String user_id, String user_phone, String user_password, int user_role,
			String user_equipment_id1, String invite_code, String user_realname, String father_inviteCode) {
		super();
		this.user_id = user_id;
		this.user_phone = user_phone;
		this.user_password = user_password;
		this.user_role = user_role;
		this.user_equipment_id1 = user_equipment_id1;
		this.invite_code = invite_code;
		this.user_realname = user_realname;
		this.father_inviteCode = father_inviteCode;
	}

	public UserRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

}
