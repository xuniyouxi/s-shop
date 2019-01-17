package com.vg.entity.EVO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AUserInfo {

	private String user_id;
	private String user_realname;
	private String user_phone;
	private String user_name;
	private String user_wxcode;
	private Integer user_vip;
	private Integer pool_rank;
	private Integer pool_usedCapacity;
	private double user_balance;
	private String team_user_name;
	private Integer team_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_realname() {
		return user_realname;
	}
	public void setUser_realname(String user_realname) {
		this.user_realname = user_realname;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_wxcode() {
		return user_wxcode;
	}
	public void setUser_wxcode(String user_wxcode) {
		this.user_wxcode = user_wxcode;
	}
	public Integer getUser_vip() {
		return user_vip;
	}
	public void setUser_vip(Integer user_vip) {
		this.user_vip = user_vip;
	}
	public Integer getPool_rank() {
		return pool_rank;
	}
	public void setPool_rank(Integer pool_rank) {
		this.pool_rank = pool_rank;
	}
	public Integer getPool_usedCapacity() {
		return pool_usedCapacity;
	}
	public void setPool_usedCapacity(Integer pool_usedCapacity) {
		this.pool_usedCapacity = pool_usedCapacity;
	}
	public double getUser_balance() {
		return user_balance;
	}
	public void setUser_balance(double user_balance) {
		this.user_balance = user_balance;
	}
	public String getTeam_user_name() {
		return team_user_name;
	}
	public void setTeam_user_name(String team_user_name) {
		this.team_user_name = team_user_name;
	}
	public Integer getTeam_id() {
		return team_id;
	}
	public void setTeam_id(Integer team_id) {
		this.team_id = team_id;
	}
	
}
