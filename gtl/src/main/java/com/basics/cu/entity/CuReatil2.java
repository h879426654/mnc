package com.basics.cu.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CuReatil2 extends CuReatil2Base {
	public CuReatil2 Id(String id) {
		this.setId(id);
		return this;
	}
	public CuReatil2 customerId(String customerId) {
		this.setCustomerId(customerId);
		return this;
	}
	public CuReatil2 customerIdSecond(String customerIdSecond) {
		this.setCustomerIdSecond(customerIdSecond);
		return this;
	}
	public CuReatil2 image(String image) {
		this.setImage(image);
		return this;
	}
}