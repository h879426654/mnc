package com.basics.mall.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductCombinationVo implements Serializable {
	private static final long serialVersionUID = 4340888651014124703L;

	private String prouductId;

	private String combinationId;

	private String combination;

	private Integer stock;
	
	private BigDecimal combinationPrice;
	
	private String combinationImg;

	private String productName;
	
	public BigDecimal getCombinationPrice() {
		return combinationPrice;
	}

	public void setCombinationPrice(BigDecimal combinationPrice) {
		this.combinationPrice = combinationPrice;
	}

	public String getCombinationImg() {
		return combinationImg;
	}

	public void setCombinationImg(String combinationImg) {
		this.combinationImg = combinationImg;
	}

	public String getProuductId() {
		return prouductId;
	}

	public void setProuductId(String prouductId) {
		this.prouductId = prouductId;
	}

	public String getCombinationId() {
		return combinationId;
	}

	public void setCombinationId(String combinationId) {
		this.combinationId = combinationId;
	}

	public String getCombination() {
		return combination;
	}

	public void setCombination(String combination) {
		this.combination = combination;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
