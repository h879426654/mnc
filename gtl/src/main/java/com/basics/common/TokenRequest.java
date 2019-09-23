package com.basics.common;

import org.hibernate.validator.constraints.NotBlank;

public class TokenRequest extends DataRequest {
	private static final long serialVersionUID = -7810839171748972441L;
	@NotBlank(message = "token不能为空！")
	private String token;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
