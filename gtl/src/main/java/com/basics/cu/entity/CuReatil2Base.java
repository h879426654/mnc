package com.basics.cu.entity;

import java.math.BigDecimal;
import java.util.Date;

class CuReatil2Base extends BaseBean {
	private String id;
	private String customerId;
	private String customerIdSecond;
	private String image;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerIdSecond() {
		return customerIdSecond;
	}

	public void setCustomerIdSecond(String customerIdSecond) {
		this.customerIdSecond = customerIdSecond;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}