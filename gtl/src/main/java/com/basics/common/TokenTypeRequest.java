package com.basics.common;

import javax.validation.constraints.NotNull;

public class TokenTypeRequest extends TokenRequest {
	private static final long serialVersionUID = -7810839171748972441L;
	@NotNull(message = "类型不能为空！")
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
