package com.basics.common;

import javax.validation.constraints.NotNull;

public class TokenIdsRequest extends TokenRequest {

	private static final long serialVersionUID = -5361837667824710431L;
	@NotNull(message = "{common.validate.field.not_null}")
	private String[] ids;

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

}
