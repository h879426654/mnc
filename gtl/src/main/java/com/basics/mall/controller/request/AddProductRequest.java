package com.basics.mall.controller.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.basics.mall.entity.BaseBean;

public class AddProductRequest extends BaseBean {
	
	private static final long serialVersionUID = -2040543080738817690L;
	
	private String token;
	
	private String productId;
	
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
	* 商品封面图
	*/
	private String productImg;
	
	/**
	* 商品价格
	*/
	private BigDecimal productPrice;
	
	/**
	* 商品成本价
	*/
	private BigDecimal productCost;

	/**
	* 运费
	*/
	private BigDecimal productFreight;

	/**
	 * 库存
	 */
	private Integer productStock;

	/**
	* 商品详情描述
	*/
	private String productContext;

	private String kinds;

	private String combinations;
	
	private String  carouselImg ;

	public String getProductId() {
		return productId;
	}


	public String getProductName() {
		return productName;
	}

	public String getProductImg() {
		return productImg;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public BigDecimal getProductCost() {
		return productCost;
	}

	public BigDecimal getProductFreight() {
		return productFreight;
	}

	public Integer getProductStock() {
		return productStock;
	}

	public String getProductContext() {
		return productContext;
	}

	public String getKinds() {
		return kinds;
	}

	public String getCombinations() {
		return combinations;
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

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public void setProductCost(BigDecimal productCost) {
		this.productCost = productCost;
	}

	public void setProductFreight(BigDecimal productFreight) {
		this.productFreight = productFreight;
	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	public void setProductContext(String productContext) {
		this.productContext = productContext;
	}

	public void setKinds(String kinds) {
		this.kinds = kinds;
	}

	public void setCombinations(String combinations) {
		this.combinations = combinations;
	}


	public String getProductFirstClassify() {
		return productFirstClassify;
	}


	public String getProductSecondClassify() {
		return productSecondClassify;
	}


	public void setProductFirstClassify(String productFirstClassify) {
		this.productFirstClassify = productFirstClassify;
	}


	public void setProductSecondClassify(String productSecondClassify) {
		this.productSecondClassify = productSecondClassify;
	}


	public String getCarouselImg() {
		return carouselImg;
	}


	public void setCarouselImg(String carouselImg) {
		this.carouselImg = carouselImg;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}

	
}
