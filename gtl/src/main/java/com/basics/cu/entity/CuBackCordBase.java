package com.basics.cu.entity;

import java.util.Date;

class CuBackCordBase extends BaseBean {
	private String id;
	private String customerId;
	private String cordNumber;
	private Date createTime;
	private String delFlag;

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

	public String getCordNumber() {
		return cordNumber;
	}

	public void setCordNumber(String cordNumber) {
		this.cordNumber = cordNumber;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}