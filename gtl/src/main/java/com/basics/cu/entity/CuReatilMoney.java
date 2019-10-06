package com.basics.cu.entity;


import java.math.BigDecimal;

public class CuReatilMoney extends CuReatilMoneyBase {
	public CuReatilMoney Id(Integer id) {
		this.setId(id);
		return this;
	}
	public CuReatilMoney reatilType(String reatilType) {
		this.setReatilType(reatilType);
		return this;
	}
	public CuReatilMoney money(BigDecimal money) {
		this.setMoney(money);
		return this;
	}
	public CuReatilMoney delFlag(String delFlag) {
		this.setDelFlag(delFlag);
		return this;
	}
}