package com.basics.common;

import org.hibernate.validator.constraints.NotBlank;

public class IdNumRequest extends TokenRequest {

	private static final long serialVersionUID = -5361837667824710431L;
	@NotBlank(message = "{common.validate.field.not_null}")
	private String id;
	private String upNum;
	private String downNum;
	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUpNum() {
		return upNum;
	}

	public void setUpNum(String upNum) {
		this.upNum = upNum;
	}

	public String getDownNum() {
		return downNum;
	}

	public void setDownNum(String downNum) {
		this.downNum = downNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


}
