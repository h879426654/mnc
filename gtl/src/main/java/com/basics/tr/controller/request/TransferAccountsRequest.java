package com.basics.tr.controller.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.basics.common.TokenRequest;

public class TransferAccountsRequest extends TokenRequest {

	private static final long serialVersionUID = 8549493125170778673L;
	@NotNull(message = "{moneyToIntegralRequest.moneyNum}")
	private BigDecimal moneyNum;
	
	@NotEmpty(message = "{smsRequest.phone}")
	private String targetPhone;
	
	@NotEmpty(message = "{moneyToIntegralRequest.payPass}")
	private String payPass;

	public BigDecimal getMoneyNum() {
		return moneyNum;
	}

	public void setMoneyNum(BigDecimal moneyNum) {
		this.moneyNum = moneyNum;
	}

	public String getTargetPhone() {
		return targetPhone;
	}

	public void setTargetPhone(String targetPhone) {
		this.targetPhone = targetPhone;
	}

	public String getPayPass() {
		return payPass;
	}

	public void setPayPass(String payPass) {
		this.payPass = payPass;
	}
	
	
	
	

}
