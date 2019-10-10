package com.basics.cu.entity;


import java.math.BigDecimal;
import java.util.Date;

public class CuConsume extends CuConsumeBase {
	public CuConsume Id(String id) {
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
	public CuConsume pageN(String pageN) {
		this.setPageN(pageN);
		return this;
	}
	public CuConsume pageS(String pageS) {
		this.setPageS(pageS);
		return this;
	}
	public CuConsume appraise(String appraise) {
		this.setAppraise(appraise);
		return this;
	}
	public CuConsume mtoken(BigDecimal mtoken) {
		this.setMtoken(mtoken);
		return this;
	}
}