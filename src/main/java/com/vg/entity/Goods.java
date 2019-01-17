package com.vg.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Goods {
	private Integer goods_id;

	private String goods_name;

	private String goods_img;

	private String goods_describe;

	private Double goods_rmb;

	private Double goods_energyNum;

	private Integer goods_sum;

	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss", timezone="GMT+8")
	private Date goods_ShelfTime;

	private Integer goods_state;

	public Integer getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getGoods_img() {
		return goods_img;
	}

	public void setGoods_img(String goods_img) {
		this.goods_img = goods_img;
	}

	public String getGoods_describe() {
		return goods_describe;
	}

	public void setGoods_describe(String goods_describe) {
		this.goods_describe = goods_describe;
	}

	public Double getGoods_rmb() {
		return goods_rmb;
	}

	public void setGoods_rmb(Double goods_rmb) {
		this.goods_rmb = goods_rmb;
	}

	public Double getGoods_energyNum() {
		return goods_energyNum;
	}

	public void setGoods_energyNum(Double goods_energyNum) {
		this.goods_energyNum = goods_energyNum;
	}

	public Integer getGoods_sum() {
		return goods_sum;
	}

	public void setGoods_sum(Integer goods_sum) {
		this.goods_sum = goods_sum;
	}

	public Date getGoods_ShelfTime() {
		return goods_ShelfTime;
	}

	public void setGoods_ShelfTime(Date goods_ShelfTime) {
		this.goods_ShelfTime = goods_ShelfTime;
	}

	public Integer getGoods_state() {
		return goods_state;
	}

	public void setGoods_state(Integer goods_state) {
		this.goods_state = goods_state;
	}

	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Goods(Integer goods_id, String goods_name, String goods_img, String goods_describe, Double goods_rmb,
			Double goods_energyNum, Integer goods_sum, Date goods_ShelfTime, Integer goods_state) {
		super();
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.goods_img = goods_img;
		this.goods_describe = goods_describe;
		this.goods_rmb = goods_rmb;
		this.goods_energyNum = goods_energyNum;
		this.goods_sum = goods_sum;
		this.goods_ShelfTime = goods_ShelfTime;
		this.goods_state = goods_state;
	}

}