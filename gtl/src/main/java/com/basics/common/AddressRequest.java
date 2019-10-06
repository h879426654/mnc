package com.basics.common;

import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

public class AddressRequest extends DataRequest {
	private static final long serialVersionUID = -7810839171748972441L;
	@NotBlank(message = "地址不能为空！")
	private String address;
	private BigDecimal num;

	public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
