package com.vg.entity;

import java.io.Serializable;

public class UserTeam implements Serializable{
	private String user_id;

	private Integer team_id;

	private String invited_father;

	private Integer invited_sum;

	private Integer member_layer;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getTeam_id() {
		return team_id;
	}

	public void setTeam_id(Integer team_id) {
		this.team_id = team_id;
	}

	public String getInvited_father() {
		return invited_father;
	}

	public void setInvited_father(String invited_father) {
		this.invited_father = invited_father;
	}

	public Integer getInvited_sum() {
		return invited_sum;
	}

	public void setInvited_sum(Integer invited_sum) {
		this.invited_sum = invited_sum;
	}

	public Integer getMember_layer() {
		return member_layer;
	}

	public void setMember_layer(Integer member_layer) {
		this.member_layer = member_layer;
	}

	public UserTeam(String user_id, Integer team_id, String invited_father, Integer invited_sum, Integer member_layer) {
		super();
		this.user_id = user_id;
		this.team_id = team_id;
		this.invited_father = invited_father;
		this.invited_sum = invited_sum;
		this.member_layer = member_layer;
	}

	public UserTeam() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserTeam [user_id=" + user_id + ", team_id=" + team_id + ", invited_father=" + invited_father
				+ ", invited_sum=" + invited_sum + ", member_layer=" + member_layer + "]";
	}

	
}