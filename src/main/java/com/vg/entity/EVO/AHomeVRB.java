package com.vg.entity.EVO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class AHomeVRB {

//	@JsonFormat(pattern="MM/dd", timezone="GMT+8")
	private long date;
	private Double VRBNum;
	
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public Double getVRBNum() {
		return VRBNum;
	}
	public void setVRBNum(Double vRBNum) {
		VRBNum = vRBNum;
	}
	
}
