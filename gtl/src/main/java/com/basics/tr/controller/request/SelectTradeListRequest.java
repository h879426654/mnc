package com.basics.tr.controller.request;

import javax.validation.constraints.NotNull;

import com.basics.common.TokenPageRequest;

public class SelectTradeListRequest extends TokenPageRequest {
	
	private static final long serialVersionUID = 2575049065374492081L;

	@NotNull(message = "{emailRequest.emailType}")
	private Integer tradeType;

	private String searchName;// 搜索内容;

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

}
