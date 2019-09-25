package com.basics.wallet.controller.request;

import com.basics.common.TokenRequest;
import org.hibernate.validator.constraints.NotBlank;

public class TokenBalanceRequest extends TokenRequest {

	private static final long serialVersionUID = -5361837667824710431L;
	@NotBlank(message = "{common.validate.field.not_null}")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
