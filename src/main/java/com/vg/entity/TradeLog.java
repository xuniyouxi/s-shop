package com.vg.entity;

import java.util.Date;

public class TradeLog {
	private Integer record_id;

	private String user_id;

	private Integer team_id;

	private String to_user_id;

	private Double trade_number;

	private Double service_charge;

	private Date trade_time;

	public Integer getRecord_id() {
		return record_id;
	}

	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}

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

	public String getTo_user_id() {
		return to_user_id;
	}

	public void setTo_user_id(String to_user_id) {
		this.to_user_id = to_user_id;
	}

	public Double getTrade_number() {
		return trade_number;
	}

	public void setTrade_number(Double trade_number) {
		this.trade_number = trade_number;
	}

	public Double getService_charge() {
		return service_charge;
	}

	public void setService_charge(Double service_charge) {
		this.service_charge = service_charge;
	}

	public Date getTrade_time() {
		return trade_time;
	}

	public void setTrade_time(Date trade_time) {
		this.trade_time = trade_time;
	}

	public TradeLog(Integer record_id, String user_id, Integer team_id, String to_user_id, Double trade_number,
			Double service_charge, Date trade_time) {
		super();
		this.record_id = record_id;
		this.user_id = user_id;
		this.team_id = team_id;
		this.to_user_id = to_user_id;
		this.trade_number = trade_number;
		this.service_charge = service_charge;
		this.trade_time = trade_time;
	}

	public TradeLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}