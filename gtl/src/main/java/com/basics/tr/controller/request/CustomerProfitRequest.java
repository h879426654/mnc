package com.basics.tr.controller.request;

import javax.validation.constraints.NotNull;

import com.basics.common.TokenPageRequest;

public class CustomerProfitRequest extends TokenPageRequest{

	private static final long serialVersionUID = 7585831139379576138L;
	
	//收益类型
	@NotNull(message = "{emailRequest.emailType}")
	private Integer type;
	//收益状态
//	@NotNull(message = "status不能为空")
	private Integer status;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	
}
