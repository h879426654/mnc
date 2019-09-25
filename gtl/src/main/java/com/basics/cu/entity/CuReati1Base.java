package com.basics.cu.entity;

import java.math.BigDecimal;
import java.util.Date;

class CuRetail1Base extends BaseBean {
	private String id;
	private String customerId;
	private BigDecimal money;
	private BigDecimal indirectMoney;
	private Date createTime;
	private Integer count2;
	private Integer count3;
	private String list2;
	private String list3;
	public Integer getCount2() {
		return count2;
	}

	public void setCount2(Integer count2) {
		this.count2 = count2;
	}

	public Integer getCount3() {
		return count3;
	}

	public void setCount3(Integer count3) {
		this.count3 = count3;
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

	public String getList2() {
		return list2;
	}

	public void setList2(String list2) {
		this.list2 = list2;
	}

	public String getList3() {
		return list3;
	}

	public void setList3(String list3) {
		this.list3 = list3;
	}
}