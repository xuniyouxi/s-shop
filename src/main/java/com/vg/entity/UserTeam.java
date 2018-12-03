package com.vg.entity;

public class UserTeam {
	private Integer userId;

	private Integer teamId;

	private Integer invitedFather;

	private Integer invitedSum;

	private Integer memberLayer;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getInvitedFather() {
		return invitedFather;
	}

	public void setInvitedFather(Integer invitedFather) {
		this.invitedFather = invitedFather;
	}

	public Integer getInvitedSum() {
		return invitedSum;
	}

	public void setInvitedSum(Integer invitedSum) {
		this.invitedSum = invitedSum;
	}

	public Integer getMemberLayer() {
		return memberLayer;
	}

	public void setMemberLayer(Integer memberLayer) {
		this.memberLayer = memberLayer;
	}

	public UserTeam(Integer userId, Integer teamId, Integer invitedFather, Integer invitedSum, Integer memberLayer) {
		super();
		this.userId = userId;
		this.teamId = teamId;
		this.invitedFather = invitedFather;
		this.invitedSum = invitedSum;
		this.memberLayer = memberLayer;
	}

	public UserTeam() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserTeam [userId=" + userId + ", teamId=" + teamId + ", invitedFather=" + invitedFather
				+ ", invitedSum=" + invitedSum + ", memberLayer=" + memberLayer + "]";
	}

}