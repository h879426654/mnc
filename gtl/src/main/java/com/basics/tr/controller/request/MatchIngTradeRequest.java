package com.basics.tr.controller.request;

import com.basics.common.TokenIdRequest;

public class MatchIngTradeRequest extends TokenIdRequest {
	
private static final long serialVersionUID = -4828346937452104122L;
	
	/**
	 * 验证码
	 */
	private String code;
	
	/**
	 * 二级密码
	 */
	private String payPass;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPayPass() {
		return payPass;
	}

	public void setPayPass(String payPass) {
		this.payPass = payPass;
	}
	

}
