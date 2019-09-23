package com.basics.mall.controller.request;

import com.basics.common.TokenPageRequest;

public class MallProductRequest extends TokenPageRequest {

	private static final long serialVersionUID = -920966414826184261L;
	
	/**
	* 商品一级分类
	*/
	private String productFirstClassify;

	/**
	* 商品二级分类
	*/
	private String productSecondClassify;

	/**
	* 商品名称
	*/
	private String productName;
	/**
	* 商家ID
	*/
	private String businessId;

	/**
	 * 排序方式（1综合 2销量 3价格）
	 */
	private Integer orderType;

	public String getProductFirstClassify() {
		return productFirstClassify;
	}

	public String getProductSecondClassify() {
		return productSecondClassify;
	}

	public String getProductName() {
		return productName;
	}

	public String getBusinessId() {
		return businessId;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setProductFirstClassify(String productFirstClassify) {
		this.productFirstClassify = productFirstClassify;
	}

	public void setProductSecondClassify(String productSecondClassify) {
		this.productSecondClassify = productSecondClassify;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	
	
	

}
