package com.vg.entity;

import java.util.Date;

public class Admin {
    private Integer adminId;

    private String adminName;

    private String adminRank;

    private String adminStatic;

    private Date createTime;

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminRank() {
		return adminRank;
	}

	public void setAdminRank(String adminRank) {
		this.adminRank = adminRank;
	}

	public String getAdminStatic() {
		return adminStatic;
	}

	public void setAdminStatic(String adminStatic) {
		this.adminStatic = adminStatic;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminRank=" + adminRank + ", adminStatic="
				+ adminStatic + ", createTime=" + createTime + "]";
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(Integer adminId, String adminName, String adminRank, String adminStatic, Date createTime) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminRank = adminRank;
		this.adminStatic = adminStatic;
		this.createTime = createTime;
	}

}