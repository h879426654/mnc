package com.basics.mall.controller.request;

import javax.validation.constraints.NotNull;

import com.basics.common.TokenIdRequest;

public class CustomerCarRequest extends TokenIdRequest {
	
	private static final long serialVersionUID = -7860738816467438372L;

	@NotNull(message = "{moneyToIntegralRequest.moneyNum}")
	private Integer productNum;

//	@NotBlank(message = "规格ID不能为空！")
	private String combinationId;

	public Integer getProductNum() {
		return productNum;
	}

	public String getCombinationId() {
		return combinationId;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public void setCombinationId(String combinationId) {
		this.combinationId = combinationId;
	}
	
	

}
