package com.vg.entity.EVO;

public class UserRecommendInfo {

	private String user_id;
	private int invited_sum;
	private int invited_son;
	private int user_vip;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getInvited_sum() {
		return invited_sum;
	}

	public void setInvited_sum(int invited_sum) {
		this.invited_sum = invited_sum;
	}

	public int getInvited_son() {
		return invited_son;
	}

	public void setInvited_son(int invited_son) {
		this.invited_son = invited_son;
	}

	public int getUser_vip() {
		return user_vip;
	}

	public void setUser_vip(int user_vip) {
		this.user_vip = user_vip;
	}

	public UserRecommendInfo(String user_id, int invited_sum, int invited_son, int user_vip) {
		super();
		this.user_id = user_id;
		this.invited_sum = invited_sum;
		this.invited_son = invited_son;
		this.user_vip = user_vip;
	}

	public UserRecommendInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserRecommendInfo [user_id=" + user_id + ", invited_sum=" + invited_sum + ", invited_son=" + invited_son
				+ ", user_vip=" + user_vip + "]";
	}

}
