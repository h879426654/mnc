package com.basics.common;

import org.hibernate.validator.constraints.NotBlank;

public class IdPointRequest extends TokenRequest {

	private static final long serialVersionUID = -5361837667824710431L;
	@NotBlank(message = "{common.validate.field.not_null}")
	private String id;
	private String num;
	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


}
