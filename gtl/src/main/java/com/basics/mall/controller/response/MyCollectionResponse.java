package com.basics.mall.controller.response;

import java.io.Serializable;
import java.math.BigDecimal;

public class MyCollectionResponse implements Serializable {
	
	private static final long serialVersionUID = 6651511716934348060L;

	private String id;

	private String productId;

	private String productName;

	private String productImg;

	private Integer productSale;
	
	private BigDecimal productPrice;

	public String getId() {
		return id;
	}

	public String getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductImg() {
		return productImg;
	}

	public Integer getProductSale() {
		return productSale;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public void setProductSale(Integer productSale) {
		this.productSale = productSale;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	
	
	

}
