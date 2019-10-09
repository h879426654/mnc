package com.basics.common;

import org.hibernate.validator.constraints.NotBlank;

public class IdNumsRequest extends TokenRequest {

	private static final long serialVersionUID = -5361837667824710431L;
	@NotBlank(message = "{common.validate.field.not_null}")
	private String id;
	private String Num;


	public String getNum() {
		return Num;
	}

	public void setNum(String num) {
		Num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


}
