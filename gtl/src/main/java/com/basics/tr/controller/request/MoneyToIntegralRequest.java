package com.basics.tr.controller.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.basics.common.TokenRequest;

public class MoneyToIntegralRequest extends TokenRequest {

	private static final long serialVersionUID = -4551696603587380097L;
	
	@NotNull(message = "{moneyToIntegralRequest.moneyNum}")
	private BigDecimal moneyNum;
	@NotNull(message = "{moneyToIntegralRequest.payPass}")
	private String payPass; 

	public BigDecimal getMoneyNum() {
		return moneyNum;
	}

	public void setMoneyNum(BigDecimal moneyNum) {
		this.moneyNum = moneyNum;
	}

	public String getPayPass() {
		return payPass;
	}

	public void setPayPass(String payPass) {
		this.payPass = payPass;
	}

	

	
	
	
	
	

}
