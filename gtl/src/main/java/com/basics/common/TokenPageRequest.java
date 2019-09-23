package com.basics.common;

import org.hibernate.validator.constraints.NotBlank;

public class TokenPageRequest extends DataPageRequest {

	private static final long serialVersionUID = -5361837667824710431L;
	@NotBlank(message = "token不能为空！")
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
