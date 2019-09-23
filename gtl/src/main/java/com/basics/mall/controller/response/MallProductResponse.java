package com.basics.mall.controller.response;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品数据
 */
public class MallProductResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	* 商品ID
	*/
	private String id;
	
	/**
	 * 商家id
	 */
	private String shopId;
	
	
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
	 * 商品状态(1待上架 2上架中 3已下架)
	 */
	private Integer productStatus;

	
	/**
	* 销量
	*/
	private Integer productSale;
	
	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getId() {
		return id;
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

	public Integer getProductStatus() {
		return productStatus;
	}

	public Integer getProductSale() {
		return productSale;
	}

	public void setId(String id) {
		this.id = id;
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

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	public void setProductSale(Integer productSale) {
		this.productSale = productSale;
	}


}
