package com.basics.cu.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CuReatil1 extends CuReatil1Base {
	public CuReatil1 Id(String id) {
		this.setId(id);
		return this;
	}
	public CuReatil1 customerId(String customerId) {
		this.setCustomerId(customerId);
		return this;
	}
	public CuReatil1 money(BigDecimal money) {
		this.setMoney(money);
		return this;
	}
	public CuReatil1 indirectMoney(BigDecimal indirectMoney) {
		this.setIndirectMoney(indirectMoney);
		return this;
	}
	public CuReatil1 createTime(Date createTime) {
		this.setCreateTime(createTime);
		return this;
	}
}