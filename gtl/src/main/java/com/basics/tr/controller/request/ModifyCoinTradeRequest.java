package com.basics.tr.controller.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.basics.common.TokenRequest;

public class ModifyCoinTradeRequest extends TokenRequest {

	private static final long serialVersionUID = 1904063382798857542L;

	/**
	 * 账号
	 */
	@NotBlank(message = "{common.validate.field.not_null}")
	private String member;

	/**
	 * 数量
	 */
	@NotNull(message = "{moneyToIntegralRequest.moneyNum}")
	private Integer moneyNum;

	/**
	 * 二级密码
	 */
	@NotBlank(message = "{moneyToIntegralRequest.payPass}")
	private String payPass;

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public Integer getMoneyNum() {
		return moneyNum;
	}

	public void setMoneyNum(Integer moneyNum) {
		this.moneyNum = moneyNum;
	}

	public String getPayPass() {
		return payPass;
	}

	public void setPayPass(String payPass) {
		this.payPass = payPass;
	}

}
