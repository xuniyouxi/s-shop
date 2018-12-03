package com.vg.entity;

import java.util.Date;

public class Student {
    private Integer goodsId;

    private String goodsName;

    private String goodsImg;

    private String goodsDescribe;

    private Double goodsRmb;

    private Double goodsEnergynum;

    private Integer goodsSum;

    private Date goodsShelftime;

    private Integer goodsState;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public String getGoodsDescribe() {
		return goodsDescribe;
	}

	public void setGoodsDescribe(String goodsDescribe) {
		this.goodsDescribe = goodsDescribe;
	}

	public Double getGoodsRmb() {
		return goodsRmb;
	}

	public void setGoodsRmb(Double goodsRmb) {
		this.goodsRmb = goodsRmb;
	}

	public Double getGoodsEnergynum() {
		return goodsEnergynum;
	}

	public void setGoodsEnergynum(Double goodsEnergynum) {
		this.goodsEnergynum = goodsEnergynum;
	}

	public Integer getGoodsSum() {
		return goodsSum;
	}

	public void setGoodsSum(Integer goodsSum) {
		this.goodsSum = goodsSum;
	}

	public Date getGoodsShelftime() {
		return goodsShelftime;
	}

	public void setGoodsShelftime(Date goodsShelftime) {
		this.goodsShelftime = goodsShelftime;
	}

	public Integer getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(Integer goodsState) {
		this.goodsState = goodsState;
	}

	public Student(Integer goodsId, String goodsName, String goodsImg, String goodsDescribe, Double goodsRmb,
			Double goodsEnergynum, Integer goodsSum, Date goodsShelftime, Integer goodsState) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsImg = goodsImg;
		this.goodsDescribe = goodsDescribe;
		this.goodsRmb = goodsRmb;
		this.goodsEnergynum = goodsEnergynum;
		this.goodsSum = goodsSum;
		this.goodsShelftime = goodsShelftime;
		this.goodsState = goodsState;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

}