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
	public CuReatil1 count2(Integer count2) {
		this.setCount2(count2);
		return this;
	}
	public CuReatil1 count3(Integer count3) {
		this.setCount3(count3);
		return this;
	}
	public CuReatil1 list2(String list2) {
		this.setList2(list2);
		return this;
	}
	public CuReatil1 list3(String list3) {
		this.setList3(list3);
		return this;
	}
}