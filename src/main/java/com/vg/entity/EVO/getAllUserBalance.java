package com.vg.entity.EVO;

public class getAllUserBalance {
	private String user_id;
	private double user_balance;
	private Integer pool_usedCapacity;
	private Integer pool_rank;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public double getUser_balance() {
		return user_balance;
	}

	public void setUser_balance(double user_balance) {
		this.user_balance = user_balance;
	}

	public Integer getPool_usedCapacity() {
		return pool_usedCapacity;
	}

	public void setPool_usedCapacity(Integer pool_usedCapacity) {
		this.pool_usedCapacity = pool_usedCapacity;
	}

	public Integer getPool_rank() {
		return pool_rank;
	}

	public void setPool_rank(Integer pool_rank) {
		this.pool_rank = pool_rank;
	}

	public getAllUserBalance(String user_id, double user_balance, Integer pool_usedCapacity, Integer pool_rank) {
		super();
		this.user_id = user_id;
		this.user_balance = user_balance;
		this.pool_usedCapacity = pool_usedCapacity;
		this.pool_rank = pool_rank;
	}

	public getAllUserBalance() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "getAllUserBalance [user_id=" + user_id + ", user_balance=" + user_balance + ", pool_usedCapacity="
				+ pool_usedCapacity + ", pool_rank=" + pool_rank + "]";
	}

}
