package com.vg.entity;

import java.util.Date;

public class User {
    private Integer userId;

    private String userRealname;

    private String userName;

    private String userPhone;

    private String userWxcode;

    private String userPassword;

    private String userPayPassword;

    private String userEquipmentId1;

    private String userEquipmentId2;

    private String inviteCode;

    private String userAddress;

    private String userHeadPicture;

    private Double userBalance;

    private Integer poolUsedcapacity;

    private Integer poolRank;

    private Integer userVip;

    private Date createTime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserRealname() {
		return userRealname;
	}

	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserWxcode() {
		return userWxcode;
	}

	public void setUserWxcode(String userWxcode) {
		this.userWxcode = userWxcode;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPayPassword() {
		return userPayPassword;
	}

	public void setUserPayPassword(String userPayPassword) {
		this.userPayPassword = userPayPassword;
	}

	public String getUserEquipmentId1() {
		return userEquipmentId1;
	}

	public void setUserEquipmentId1(String userEquipmentId1) {
		this.userEquipmentId1 = userEquipmentId1;
	}

	public String getUserEquipmentId2() {
		return userEquipmentId2;
	}

	public void setUserEquipmentId2(String userEquipmentId2) {
		this.userEquipmentId2 = userEquipmentId2;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserHeadPicture() {
		return userHeadPicture;
	}

	public void setUserHeadPicture(String userHeadPicture) {
		this.userHeadPicture = userHeadPicture;
	}

	public Double getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(Double userBalance) {
		this.userBalance = userBalance;
	}

	public Integer getPoolUsedcapacity() {
		return poolUsedcapacity;
	}

	public void setPoolUsedcapacity(Integer poolUsedcapacity) {
		this.poolUsedcapacity = poolUsedcapacity;
	}

	public Integer getPoolRank() {
		return poolRank;
	}

	public void setPoolRank(Integer poolRank) {
		this.poolRank = poolRank;
	}

	public Integer getUserVip() {
		return userVip;
	}

	public void setUserVip(Integer userVip) {
		this.userVip = userVip;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User(Integer userId, String userRealname, String userName, String userPhone, String userWxcode,
			String userPassword, String userPayPassword, String userEquipmentId1, String userEquipmentId2,
			String inviteCode, String userAddress, String userHeadPicture, Double userBalance, Integer poolUsedcapacity,
			Integer poolRank, Integer userVip, Date createTime) {
		super();
		this.userId = userId;
		this.userRealname = userRealname;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userWxcode = userWxcode;
		this.userPassword = userPassword;
		this.userPayPassword = userPayPassword;
		this.userEquipmentId1 = userEquipmentId1;
		this.userEquipmentId2 = userEquipmentId2;
		this.inviteCode = inviteCode;
		this.userAddress = userAddress;
		this.userHeadPicture = userHeadPicture;
		this.userBalance = userBalance;
		this.poolUsedcapacity = poolUsedcapacity;
		this.poolRank = poolRank;
		this.userVip = userVip;
		this.createTime = createTime;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userRealname=" + userRealname + ", userName=" + userName + ", userPhone="
				+ userPhone + ", userWxcode=" + userWxcode + ", userPassword=" + userPassword + ", userPayPassword="
				+ userPayPassword + ", userEquipmentId1=" + userEquipmentId1 + ", userEquipmentId2=" + userEquipmentId2
				+ ", inviteCode=" + inviteCode + ", userAddress=" + userAddress + ", userHeadPicture=" + userHeadPicture
				+ ", userBalance=" + userBalance + ", poolUsedcapacity=" + poolUsedcapacity + ", poolRank=" + poolRank
				+ ", userVip=" + userVip + ", createTime=" + createTime + "]";
	}

}