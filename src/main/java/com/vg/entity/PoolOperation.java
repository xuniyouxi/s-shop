package com.vg.entity;

import java.util.Date;

public class PoolOperation {
	private Integer operation_id;

	private String user_id;

	private Integer into_balance;

	private Date operation_time;

	public Integer getOperation_id() {
		return operation_id;
	}

	public void setOperation_id(Integer operation_id) {
		this.operation_id = operation_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getInto_balance() {
		return into_balance;
	}

	public void setInto_balance(Integer into_balance) {
		this.into_balance = into_balance;
	}

	public Date getOperation_time() {
		return operation_time;
	}

	public void setOperation_time(Date operation_time) {
		this.operation_time = operation_time;
	}

	public PoolOperation(Integer operation_id, String user_id, Integer into_balance, Date operation_time) {
		super();
		this.operation_id = operation_id;
		this.user_id = user_id;
		this.into_balance = into_balance;
		this.operation_time = operation_time;
	}

	public PoolOperation() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}