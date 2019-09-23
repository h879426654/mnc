package com.basics.mall.controller.request;

import org.hibernate.validator.constraints.NotBlank;

import com.basics.common.TokenPageRequest;

public class TokenIdPageRequest extends TokenPageRequest {

	private static final long serialVersionUID = -3707049586719087967L;
	
	@NotBlank(message = "{common.validate.field.not_null}")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
