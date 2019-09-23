package com.basics.cu.controller.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.basics.common.DataRequest;

public class EmailRequest extends DataRequest {

	private static final long serialVersionUID = -2423211711282445204L;

	@NotBlank(message = "{emailRequest.email}")
	private String email;
	@NotNull(message = "{emailRequest.emailType}")
	private Integer emailType;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEmailType() {
		return emailType;
	}

	public void setEmailType(Integer emailType) {
		this.emailType = emailType;
	}
}
