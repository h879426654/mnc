package com.basics.mall.controller.response;

import java.util.List;

public class CustomerCarResponsePlus{
	private List<CustomerCarResponse> list;
	private List<CustomerCarData> expireProduct;
	public List<CustomerCarResponse> getList() {
		return list;
	}
	public void setList(List<CustomerCarResponse> list) {
		this.list = list;
	}
	public List<CustomerCarData> getExpireProduct() {
		return expireProduct;
	}
	public void setExpireProduct(List<CustomerCarData> expireProduct) {
		this.expireProduct = expireProduct;
	}
	
}
