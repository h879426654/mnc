package com.basics.cu.controller.request;

import org.hibernate.validator.constraints.NotBlank;

import com.basics.common.TokenRequest;

public class ModifyCustomerInfoRequest extends TokenRequest {

	private static final long serialVersionUID = -3068696896745575424L;
	@NotBlank(message="{modifyCustomerInfoRequest.customerName}")
	private String customerName;

	private String customerCard;
	
	private String realName;
	
//	@NotBlank(message="支付宝账号不能为空")
	private String customerAlipay;
	
//	@NotBlank(message="银行卡不能为空")
	private String bankCard;
	
//	@NotBlank(message = "开户银行不能为空")
	private String bankName;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCustomerCard() {
		return customerCard;
	}

	public void setCustomerCard(String customerCard) {
		this.customerCard = customerCard;
	}

	public String getCustomerAlipay() {
		return customerAlipay;
	}

	public void setCustomerAlipay(String customerAlipay) {
		this.customerAlipay = customerAlipay;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
}
