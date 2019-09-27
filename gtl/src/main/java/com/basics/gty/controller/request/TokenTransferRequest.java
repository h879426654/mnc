package com.basics.gty.controller.request;

import com.basics.common.TokenRequest;
import org.hibernate.validator.constraints.NotBlank;

public class TokenTransferRequest extends TokenRequest {

	private static final long serialVersionUID = -5361837667824710431L;
	@NotBlank(message = "{common.validate.field.not_null}")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String num;
	private String toAddress;

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	private int fromType;// 0mnc;1流通钱包;2super;3record
	private int toType;// 0交易所;1流通钱包;2mtoken;3record

	public int getFromType() {
		return fromType;
	}

	public void setFromType(int fromType) {
		this.fromType = fromType;
	}

	public int getToType() {
		return toType;
	}

	public void setToType(int toType) {
		this.toType = toType;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

}
