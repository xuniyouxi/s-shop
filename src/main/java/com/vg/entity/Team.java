package com.vg.entity;

import java.util.Date;

public class Team {
    private Integer teamId;

    private Integer teamSum;

    private Date createTime;

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getTeamSum() {
		return teamSum;
	}

	public void setTeamSum(Integer teamSum) {
		this.teamSum = teamSum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Team(Integer teamId, Integer teamSum, Date createTime) {
		super();
		this.teamId = teamId;
		this.teamSum = teamSum;
		this.createTime = createTime;
	}

	public Team() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Team [teamId=" + teamId + ", teamSum=" + teamSum + ", createTime=" + createTime + "]";
	}


}