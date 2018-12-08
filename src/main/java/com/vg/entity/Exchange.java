package com.vg.entity;

import java.util.Date;

public class Exchange {
	private Integer exchange_id;

	private String user_id;

	private Integer goods_id;

	private Double goods_energyNum;

	private Date exchange_time;

	public Integer getExchange_id() {
		return exchange_id;
	}

	public void setExchange_id(Integer exchange_id) {
		this.exchange_id = exchange_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}

	public Double getGoods_energyNum() {
		return goods_energyNum;
	}

	public void setGoods_energyNum(Double goods_energyNum) {
		this.goods_energyNum = goods_energyNum;
	}

	public Date getExchange_time() {
		return exchange_time;
	}

	public void setExchange_time(Date exchange_time) {
		this.exchange_time = exchange_time;
	}

	public Exchange(Integer exchange_id, String user_id, Integer goods_id, Double goods_energyNum, Date exchange_time) {
		super();
		this.exchange_id = exchange_id;
		this.user_id = user_id;
		this.goods_id = goods_id;
		this.goods_energyNum = goods_energyNum;
		this.exchange_time = exchange_time;
	}

	public Exchange() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}