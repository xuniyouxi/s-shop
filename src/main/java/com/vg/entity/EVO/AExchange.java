package com.vg.entity.EVO;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AExchange {

	private String exchange_id;
	private String goods_name;
	private String user_realname;
	private String user_name;
	private String goods_energyNum;
	private String user_address;
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss", timezone="GMT+8")
	private Timestamp exchange_time;
	private String exchange_status;
	public AExchange() {}
	public AExchange(String exchange_id, String goods_name, String user_realname, String user_name,
			String goods_energyNum, String user_address, Timestamp exchange_time, String exchange_status) {
		super();
		this.exchange_id = exchange_id;
		this.goods_name = goods_name;
		this.user_realname = user_realname;
		this.user_name = user_name;
		this.goods_energyNum = goods_energyNum;
		this.user_address = user_address;
		this.exchange_time = exchange_time;
		this.exchange_status = exchange_status;
	}
	public String getExchange_id() {
		return exchange_id;
	}
	public void setExchange_id(String exchange_id) {
		this.exchange_id = exchange_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getUser_realname() {
		return user_realname;
	}
	public void setUser_realname(String user_realname) {
		this.user_realname = user_realname;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getGoods_energyNum() {
		return goods_energyNum;
	}
	public void setGoods_energyNum(String goods_energyNum) {
		this.goods_energyNum = goods_energyNum;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public Timestamp getExchange_time() {
		return exchange_time;
	}
	public void setExchange_time(Timestamp exchange_time) {
		this.exchange_time = exchange_time;
	}
	public String getExchange_status() {
		return exchange_status;
	}
	public void setExchange_status(String exchange_status) {
		this.exchange_status = exchange_status;
	}
	
}
