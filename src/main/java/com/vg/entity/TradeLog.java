package com.vg.entity;

import java.util.Date;

public class TradeLog {
    private Integer recordId;

    private Integer userId;

    private Integer teamId;

    private Integer toUserId;

    private Double tradeNumber;

    private Double serviceCharge;

    private Date tradeTime;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	public Double getTradeNumber() {
		return tradeNumber;
	}

	public void setTradeNumber(Double tradeNumber) {
		this.tradeNumber = tradeNumber;
	}

	public Double getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public TradeLog(Integer recordId, Integer userId, Integer teamId, Integer toUserId, Double tradeNumber,
			Double serviceCharge, Date tradeTime) {
		super();
		this.recordId = recordId;
		this.userId = userId;
		this.teamId = teamId;
		this.toUserId = toUserId;
		this.tradeNumber = tradeNumber;
		this.serviceCharge = serviceCharge;
		this.tradeTime = tradeTime;
	}

	public TradeLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TradeLog [recordId=" + recordId + ", userId=" + userId + ", teamId=" + teamId + ", toUserId=" + toUserId
				+ ", tradeNumber=" + tradeNumber + ", serviceCharge=" + serviceCharge + ", tradeTime=" + tradeTime
				+ "]";
	}

    
}