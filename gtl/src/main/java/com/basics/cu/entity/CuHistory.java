package com.basics.cu.entity;


import java.math.BigDecimal;
import java.util.Date;

public class CuHistory extends CuHistoryBase {
	public CuHistory Id(Integer id) {
		this.setId(id);
		return this;
	}
	public CuHistory shopId(String shopId) {
		this.setShopId(shopId);
		return this;
	}
	public CuHistory customerId(String customerId) {
		this.setCustomerId(customerId);
		return this;
	}
	public CuHistory shopName(String shopName) {
		this.setShopName(shopName);
		return this;
	}
	public CuHistory address(String address) {
		this.setAddress(address);
		return this;
	}
	public CuHistory longitude(String longitude) {
		this.setLongitude(longitude);
		return this;
	}
	public CuHistory latitude(String latitude) {
		this.setLatitude(latitude);
		return this;
	}
	public CuHistory createTime(Date createTime) {
		this.setCreateTime(createTime);
		return this;
	}
	public CuHistory count(Integer count) {
		this.setCount(count);
		return this;
	}
}