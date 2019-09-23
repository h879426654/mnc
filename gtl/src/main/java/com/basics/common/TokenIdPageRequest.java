package com.basics.common;

import org.hibernate.validator.constraints.NotBlank;

public class TokenIdPageRequest extends DataPageRequest {

	private static final long serialVersionUID = -5361837667824710431L;
	@NotBlank(message = "token不能为空！")
	private String token;

	@NotBlank(message = "{common.validate.field.not_null}")
	private String id;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
