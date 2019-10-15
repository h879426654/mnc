package com.basics.cu.entity;

import java.math.BigDecimal;
import java.util.Date;

class CuLogsBase extends BaseBean {
	private Integer id;
	private String type;
	private String customerId;
	private String shopId;
	private BigDecimal mp;
	private BigDecimal money;
	private String remark;
	private Date createTime;
	private Integer pageN;
	private Integer pageS;

	public Integer getPageN() {
		return pageN;
	}

	public void setPageN(Integer pageN) {
		this.pageN = pageN;
	}

	public Integer getPageS() {
		return pageS;
	}

	public void setPageS(Integer pageS) {
		this.pageS = pageS;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public BigDecimal getMp() {
		return mp;
	}

	public void setMp(BigDecimal mp) {
		this.mp = mp;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}