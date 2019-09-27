package com.basics.cu.entity;


import java.util.Date;

public class CuCustomerCollect extends CuCustomerCollectBase {

	public CuCustomerCollect Id(String id) {
		this.setId(id);
		return this;
	}

	public CuCustomerCollect shopId(String shopId) {
		this.setShopId(shopId);
		return this;
	}

	public CuCustomerCollect shopName(String shopName) {
		this.setShopName(shopName);
		return this;
	}

	public CuCustomerCollect customerId(String customerId) {
		this.setCustomerId(customerId);
		return this;
	}

	public CuCustomerCollect state(String state) {
		this.setState(state);
		return this;
	}

	public CuCustomerCollect createTime(Date createTime) {
		this.setCreateTime(createTime);
		return this;
	}

	public CuCustomerCollect count(Integer count) {
		this.setCount(count);
		return this;
	}

	public CuCustomerCollect page(Integer page) {
		this.setPage(page);
		return this;
	}

	public CuCustomerCollect rows(String rows) {
		this.setRows(rows);
		return this;
	}

	public CuCustomerCollect limit(String limit) {
		this.setLimit(limit);
		return this;
	}

}