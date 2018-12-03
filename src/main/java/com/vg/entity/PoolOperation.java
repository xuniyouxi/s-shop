package com.vg.entity;

import java.util.Date;

public class PoolOperation {
    private Integer operationId;

    private Integer userId;

    private Integer intoBalance;

    private Date operationTime;

	public Integer getOperationId() {
		return operationId;
	}

	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getIntoBalance() {
		return intoBalance;
	}

	public void setIntoBalance(Integer intoBalance) {
		this.intoBalance = intoBalance;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public PoolOperation(Integer operationId, Integer userId, Integer intoBalance, Date operationTime) {
		super();
		this.operationId = operationId;
		this.userId = userId;
		this.intoBalance = intoBalance;
		this.operationTime = operationTime;
	}

	public PoolOperation() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PoolOperation [operationId=" + operationId + ", userId=" + userId + ", intoBalance=" + intoBalance
				+ ", operationTime=" + operationTime + "]";
	}


}