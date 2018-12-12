package com.vg.entity.EVO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class GlanceGoods {

	private Integer goods_id;
	private String goods_name;
	private String goods_img;
	private Double goods_rmb;
	private Double goods_energyNum;
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
	public Integer getGoods_state() {
		return goods_state;
	}
	public void setGoods_state(Integer goods_state) {
		this.goods_state = goods_state;
	}
	
}
