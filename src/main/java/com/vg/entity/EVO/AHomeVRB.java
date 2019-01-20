package com.vg.entity.EVO;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class AHomeVRB {

	@JsonFormat(pattern="MM/dd", timezone="GMT+8")
	private Date date;
	private Double VRBNum;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getVRBNum() {
		return VRBNum;
	}
	public void setVRBNum(Double vRBNum) {
		VRBNum = vRBNum;
	}
	
}
