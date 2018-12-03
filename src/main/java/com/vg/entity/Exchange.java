package com.vg.entity;

import java.util.Date;

public class Exchange {
    private Integer exchangeId;

    private Integer userId;

    private Integer goodsId;

    private Double goodsEnergynum;

    private Date exchangeTime;

	public Integer getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(Integer exchangeId) {
		this.exchangeId = exchangeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Double getGoodsEnergynum() {
		return goodsEnergynum;
	}

	public void setGoodsEnergynum(Double goodsEnergynum) {
		this.goodsEnergynum = goodsEnergynum;
	}

	public Date getExchangeTime() {
		return exchangeTime;
	}

	public void setExchangeTime(Date exchangeTime) {
		this.exchangeTime = exchangeTime;
	}

	public Exchange(Integer exchangeId, Integer userId, Integer goodsId, Double goodsEnergynum, Date exchangeTime) {
		super();
		this.exchangeId = exchangeId;
		this.userId = userId;
		this.goodsId = goodsId;
		this.goodsEnergynum = goodsEnergynum;
		this.exchangeTime = exchangeTime;
	}

	public Exchange() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Exchange [exchangeId=" + exchangeId + ", userId=" + userId + ", goodsId=" + goodsId
				+ ", goodsEnergynum=" + goodsEnergynum + ", exchangeTime=" + exchangeTime + "]";
	}

 
}