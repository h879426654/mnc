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
	public CuReatil1 createTime(Date createTime) {
		this.setCreateTime(createTime);
		return this;
	}
	public CuReatil1 indirectList(String indirectList) {
		this.setIndirectList(indirectList);
		return this;
	}
	public CuReatil1 indirectids(String directList) {
		this.setDirectList(directList);
		return this;
	}
	public CuReatil1 directCount(Integer directCount) {
		this.setDirectCount(directCount);
		return this;
	}
	public CuReatil1 indirectCount(Integer indirectCount) {
		this.setIndirectidCount(indirectCount);
		return this;
	}
}