package com.vg.entity;

import java.util.Date;

public class IdentifyCode {
    private Integer userId;

    private Integer identifyCode;

    private String usedStatic;

    private String usedMethod;

    private Date usedTime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getIdentifyCode() {
		return identifyCode;
	}

	public void setIdentifyCode(Integer identifyCode) {
		this.identifyCode = identifyCode;
	}

	public String getUsedStatic() {
		return usedStatic;
	}

	public void setUsedStatic(String usedStatic) {
		this.usedStatic = usedStatic;
	}

	public String getUsedMethod() {
		return usedMethod;
	}

	public void setUsedMethod(String usedMethod) {
		this.usedMethod = usedMethod;
	}

	public Date getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(Date usedTime) {
		this.usedTime = usedTime;
	}

	public IdentifyCode(Integer userId, Integer identifyCode, String usedStatic, String usedMethod, Date usedTime) {
		super();
		this.userId = userId;
		this.identifyCode = identifyCode;
		this.usedStatic = usedStatic;
		this.usedMethod = usedMethod;
		this.usedTime = usedTime;
	}

	public IdentifyCode() {
		super();
		// TODO Auto-generated constructor stub
	}

   
}