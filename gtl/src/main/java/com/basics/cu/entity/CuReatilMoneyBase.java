package com.basics.cu.entity;

import java.math.BigDecimal;

class CuReatilMoneyBase extends BaseBean {
	private Integer id;
	private String reatilType;
	private BigDecimal money;
	private String delFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReatilType() {
		return reatilType;
	}

	public void setReatilType(String reatilType) {
		this.reatilType = reatilType;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}