package com.basics.cu.controller.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.basics.common.TokenRequest;

public class CustomerFeedbackRequest extends TokenRequest {

	private static final long serialVersionUID = 359533412266353275L;
	@NotNull(message = "{emailRequest.emailType}")
	private Integer type;
	@NotBlank(message = "{customerFeedbackRequest.context}")
	private String context;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	
	

}
