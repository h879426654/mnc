package com.basics.gty.controller.request;

import com.basics.common.TokenIdRequest;
import com.basics.common.TokenRequest;
import org.hibernate.validator.constraints.NotBlank;

public class TokenTransferRequest extends TokenIdRequest {

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

	// 1,mnc转交易所；2mnc转流通钱包；3流通转记账钱包；4流通转mtoken；5流通点对点；6超级钱包转流通钱包；7超级钱包转交易所；
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

}
