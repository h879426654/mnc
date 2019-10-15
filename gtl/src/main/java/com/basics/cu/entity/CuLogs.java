package com.basics.cu.entity;


import jnr.ffi.annotations.In;

import java.math.BigDecimal;
import java.util.Date;

public class CuLogs extends CuLogsBase {
	public CuLogs Id(Integer id) {
		this.setId(id);
		return this;
	}
	public CuLogs type(String type) {
		this.setType(type);
		return this;
	}
	public CuLogs customerId(String customerId) {
		this.setCustomerId(customerId);
		return this;
	}
	public CuLogs shopId(String shopId) {
		this.setShopId(shopId);
		return this;
	}
	public CuLogs mp(BigDecimal mp) {
		this.setMp(mp);
		return this;
	}
	public CuLogs money(BigDecimal money) {
		this.setMoney(money);
		return this;
	}
	public CuLogs remark(String remark) {
		this.setRemark(remark);
		return this;
	}
	public CuLogs createTime(Date createTime) {
		this.setCreateTime(createTime);
		return this;
	}
	public CuLogs pageS(Integer pageS) {
		this.setPageS(pageS);
		return this;
	}
	public CuLogs pageN(Integer pageN) {
		this.setPageN(pageN);
		return this;
	}
}