package com.basics.cu.controller.response;

import java.math.BigDecimal;
import java.util.Date;

import com.basics.cu.entity.CuCustomerInfo;

public class DirectCustomerResponse extends CuCustomerInfo{

	private static final long serialVersionUID = 265062915497686429L;
	
	private String customerLevelName;
	
	private BigDecimal useMoney;
	
	private BigDecimal useCoin;
	
	private BigDecimal customerIntegral;
	
	private Integer salfNum;
	
	private Integer teamNum;
	
	private Date registerTime;
	
	public Integer getTeamNum() {
		return teamNum;
	}

	public void setTeamNum(Integer teamNum) {
		this.teamNum = teamNum;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getSalfNum() {
		return salfNum;
	}

	public void setSalfNum(Integer salfNum) {
		this.salfNum = salfNum;
	}

	public BigDecimal getUseCoin() {
		return useCoin;
	}

	public void setUseCoin(BigDecimal useCoin) {
		this.useCoin = useCoin;
	}

	public BigDecimal getCustomerIntegral() {
		return customerIntegral;
	}

	public void setCustomerIntegral(BigDecimal customerIntegral) {
		this.customerIntegral = customerIntegral;
	}

	public String getCustomerLevelName() {
		return customerLevelName;
	}

	public void setCustomerLevelName(String customerLevelName) {
		this.customerLevelName = customerLevelName;
	}

	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}
	
	
	

}
