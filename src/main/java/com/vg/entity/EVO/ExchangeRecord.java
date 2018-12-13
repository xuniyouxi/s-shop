package com.vg.entity.EVO;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRecord {

	private Integer exchange_id;
	private double goods_energyNum;	
	@JsonFormat(pattern="yyyy/MM/dd", timezone="GMT+8")
	private Date exchange_time;	
	private String goods_name;
	private double goods_rmb;
	private String goods_describe;
	public Integer getExchange_id() {
		return exchange_id;
	}
	public void setExchange_id(Integer exchange_id) {
		this.exchange_id = exchange_id;
	}
	public double getGoods_energyNum() {
		return goods_energyNum;
	}
	public void setGoods_energyNum(double goods_energyNum) {
		this.goods_energyNum = goods_energyNum;
	}
	public Date getExchange_time() {
		return exchange_time;
	}
	public void setExchange_time(Date exchange_time) {
		this.exchange_time = exchange_time;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public double getGoods_rmb() {
		return goods_rmb;
	}
	public void setGoods_rmb(double goods_rmb) {
		this.goods_rmb = goods_rmb;
	}
	public String getGoods_describe() {
		return goods_describe;
	}
	public void setGoods_describe(String goods_describe) {
		this.goods_describe = goods_describe;
	}
	
}
