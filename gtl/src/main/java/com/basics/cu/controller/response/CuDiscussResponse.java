package com.basics.cu.controller.response;

import com.basics.cu.entity.CuCustomerInfo;
import com.basics.cu.entity.CuDiscuss;

import java.math.BigDecimal;
import java.util.Date;

public class CuDiscussResponse extends CuDiscuss {

	private static final long serialVersionUID = -3058578580570630254L;

	private int id;
	private String shopId;
	private String customerId;
	private String state;
	private Date createTime;
	private String remark;

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
