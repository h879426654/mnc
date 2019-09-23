package com.basics.common;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class TokenTypePageRequest extends DataPageRequest {

	private static final long serialVersionUID = -5361837667824710431L;
	@NotBlank(message = "token不能为空！")
	private String token;
	@NotNull(message = "类型不能为空！")
	private Integer type;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
