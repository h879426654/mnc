package com.basics.cu.controller.response;

import java.io.Serializable;
import java.math.BigDecimal;

public class CountRewardResponse implements Serializable{

	private static final long serialVersionUID = 944082334016868415L;
	
	private BigDecimal winNum;
	
	private BigDecimal drawNum;
	
	private String createTime;

	public BigDecimal getWinNum() {
		return winNum;
	}

	public void setWinNum(BigDecimal winNum) {
		this.winNum = winNum;
	}

	public BigDecimal getDrawNum() {
		return drawNum;
	}

	public void setDrawNum(BigDecimal drawNum) {
		this.drawNum = drawNum;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
	

}
