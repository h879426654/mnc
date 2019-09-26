package com.basics.cu.entity;


import java.math.BigDecimal;
import java.util.Date;

public class CuConsume extends CuConsumeBase {
	public CuConsume Id(Integer id) {
		this.setId(id);
		return this;
	}
	public CuConsume shopId(String shopId) {
		this.setShopId(shopId);
		return this;
	}
	public CuConsume customerId(String customerId) {
		this.setCustomerId(customerId);
		return this;
	}
	public CuConsume money(BigDecimal money) {
		this.setMoney(money);
		return this;
	}
	public CuConsume shopName(String shopName) {
		this.setShopName(shopName);
		return this;
	}
	public CuConsume phone(String phone) {
		this.setPhone(phone);
		return this;
	}
	public CuConsume state(String state) {
		this.setState(state);
		return this;
	}
	public CuConsume image(String image) {
		this.setImage(image);
		return this;
	}
	public CuConsume stateText(String stateText) {
		this.setStateText(stateText);
		return this;
	}
	public CuConsume createTime(Date createTime) {
		this.setCreateTime(createTime);
		return this;
	}
}