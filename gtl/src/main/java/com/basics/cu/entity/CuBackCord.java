package com.basics.cu.entity;


import java.math.BigDecimal;
import java.util.Date;

public class CuBackCord extends CuBackCordBase {
	public CuBackCord id(String id) {
		this.setId(id);
		return this;
	}
	public CuBackCord customerId(String customerId) {
		this.setCustomerId(customerId);
		return this;
	}
	public CuBackCord cordNumber(String cordNumber) {
		this.setCordNumber(cordNumber);
		return this;
	}
	public CuBackCord createTime(Date createTime) {
		this.createTime(createTime);
		return this;
	}
	public CuBackCord delFlag(String delFlag){
		this.delFlag(delFlag);
		return this;
	}
}