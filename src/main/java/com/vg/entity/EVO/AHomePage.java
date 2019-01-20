package com.vg.entity.EVO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


//管理员首页信息
@AllArgsConstructor
@NoArgsConstructor
public class AHomePage {

	//VRB总数
	private Double totalVRB;
	//可交易VRB
	private Double balanceVRB;
	//能量池VRB
	private Integer poolVRB;
	//注册用户数
	private Integer totalLogin;
	//已激活用户数
	private Integer totalMember;
	//图表
	//交易VRB
	private List<AHomeVRB> dealVRB;
	//存入能量池
	private List<AHomeVRB> intoPool;
	//注册用户数
	private List<AHomeUser> userLogin;
	//激活用户数
	private List<AHomeUser> userActivate;
	//待发货商品数
	private Integer orderConfirmNum;
	public Double getTotalVRB() {
		return totalVRB;
	}
	public void setTotalVRB(Double totalVRB) {
		this.totalVRB = totalVRB;
	}
	public Double getBalanceVRB() {
		return balanceVRB;
	}
	public void setBalanceVRB(Double balanceVRB) {
		this.balanceVRB = balanceVRB;
	}
	public Integer getPoolVRB() {
		return poolVRB;
	}
	public void setPoolVRB(Integer poolVRB) {
		this.poolVRB = poolVRB;
	}
	public Integer getTotalLogin() {
		return totalLogin;
	}
	public void setTotalLogin(Integer totalLogin) {
		this.totalLogin = totalLogin;
	}
	public Integer getTotalMember() {
		return totalMember;
	}
	public void setTotalMember(Integer totalMember) {
		this.totalMember = totalMember;
	}
	public List<AHomeVRB> getDealVRB() {
		return dealVRB;
	}
	public void setDealVRB(List<AHomeVRB> dealVRB) {
		this.dealVRB = dealVRB;
	}
	public List<AHomeVRB> getIntoPool() {
		return intoPool;
	}
	public void setIntoPool(List<AHomeVRB> intoPool) {
		this.intoPool = intoPool;
	}
	public List<AHomeUser> getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(List<AHomeUser> userLogin) {
		this.userLogin = userLogin;
	}
	public List<AHomeUser> getUserActivate() {
		return userActivate;
	}
	public void setUserActivate(List<AHomeUser> userActivate) {
		this.userActivate = userActivate;
	}
	public Integer getOrderConfirmNum() {
		return orderConfirmNum;
	}
	public void setOrderConfirmNum(Integer orderConfirmNum) {
		this.orderConfirmNum = orderConfirmNum;
	}
	
}
