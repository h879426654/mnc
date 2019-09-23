package com.basics.mall.controller.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.basics.mall.vo.MainKindVo;

public class MallOrderProductDetailsResponse implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -5030870115857474494L;

	/**
     * 订单id
     */
	private String id;

	private String orderId;
	
	private String productId;

	private Integer productNum;

	private String productName;
	//商品图片
	private BigDecimal productPrice;
	
	private String productImg;	
	
	private String combinationImg;

	private BigDecimal combinationPrice;

	private String combinationId;
	
	private String combinationContext;


	/**
	  * 规格列表
	  */
	private List<MainKindVo> kinds;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public Integer getProductNum() {
		return productNum;
	}


	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public BigDecimal getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}


	public String getProductImg() {
		return productImg;
	}


	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}


	public String getCombinationImg() {
		return combinationImg;
	}


	public void setCombinationImg(String combinationImg) {
		this.combinationImg = combinationImg;
	}


	public BigDecimal getCombinationPrice() {
		return combinationPrice;
	}


	public void setCombinationPrice(BigDecimal combinationPrice) {
		this.combinationPrice = combinationPrice;
	}


	public String getCombinationId() {
		return combinationId;
	}


	public void setCombinationId(String combinationId) {
		this.combinationId = combinationId;
	}


	public String getCombinationContext() {
		return combinationContext;
	}


	public void setCombinationContext(String combinationContext) {
		this.combinationContext = combinationContext;
	}


	public List<MainKindVo> getKinds() {
		return kinds;
	}


	public void setKinds(List<MainKindVo> kinds) {
		this.kinds = kinds;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	
	
}
