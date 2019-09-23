package com.basics.tr.controller.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.basics.common.TokenRequest;

public class PushTradeRequest extends TokenRequest {
	
	private static final long serialVersionUID = 1904063382798857542L;

	/**
	 * 交易类型(1出售 2购买)
	 */
	@NotNull(message = "{emailRequest.emailType}")
	private Integer tradeType;

	/**
	 * 交易价格
	 */
//	@NotNull(message = "交易价格不能为空！")
	private BigDecimal tradePrice;

	/**
	 * 数量
	 */
	@NotNull(message = "{moneyToIntegralRequest.moneyNum}")
	private Integer moneyNum;
	
	
	/**
	 * 二级密码
	 */
	private String payPass;

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public BigDecimal getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(BigDecimal tradePrice) {
		this.tradePrice = tradePrice;
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
