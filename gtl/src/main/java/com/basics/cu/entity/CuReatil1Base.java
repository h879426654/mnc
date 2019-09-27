package com.basics.cu.entity;

import java.math.BigDecimal;
import java.util.Date;

class CuReatil1Base extends BaseBean {
	private String id;
	private String customerId;
	private BigDecimal money;
	private BigDecimal indirectMoney;
	private Date createTime;
	private String directList;
	private String indirectList;
	private Integer directCount;
	private Integer indirectidCount;

	public Integer getIndirectidCount() {
		return indirectidCount;
	}

	public void setIndirectidCount(Integer indirectidCount) {
		this.indirectidCount = indirectidCount;
	}

	public Integer getDirectCount() {
		return directCount;
	}

	public void setDirectCount(Integer directCount) {
		this.directCount = directCount;
	}
	public String getDirectList() {
		return directList;
	}

	public void setDirectList(String directList) {
		this.directList = directList;
	}

	public String getIndirectList() {
		return indirectList;
	}

	public void setIndirectList(String indirectList) {
		this.indirectList = indirectList;
	}

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

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getIndirectMoney() {
		return indirectMoney;
	}

	public void setIndirectMoney(BigDecimal indirectMoney) {
		this.indirectMoney = indirectMoney;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

