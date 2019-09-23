package com.basics.common;

import org.hibernate.validator.constraints.NotBlank;

public class TokenPassRequest extends TokenRequest {
	private static final long serialVersionUID = -7810839171748972441L;
	@NotBlank(message = "密码不能为空！")
	private String pass;

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
