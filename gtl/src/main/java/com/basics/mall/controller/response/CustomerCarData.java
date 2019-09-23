package com.basics.mall.controller.response;

import java.math.BigDecimal;
import java.util.List;

import com.basics.mall.entity.MallCustomerCar;
import com.basics.mall.vo.MainKindVo;

public class CustomerCarData extends MallCustomerCar {
	
	private static final long serialVersionUID = -6418445007947086969L;

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
	* 商品库存
	*/
	private String productStock;
	
	private BigDecimal productFreight;
	
	/**
	 * 规格参数
	 */
	private List<MainKindVo> kinds;

	private boolean checked = false;
	
	/**
	 * 商家id
	 */
	private String shopId;

	public List<MainKindVo> getKinds() {
		return kinds;
	}


	public void setKinds(List<MainKindVo> kinds) {
		this.kinds = kinds;
	}

	public boolean isChecked() {
		return checked;
	}


	public void setChecked(boolean checked) {
		this.checked = checked;
	}


	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductStock() {
		return productStock;
	}

	public void setProductStock(String productStock) {
		this.productStock = productStock;
	}

	public BigDecimal getProductFreight() {
		return productFreight;
	}

	public void setProductFreight(BigDecimal productFreight) {
		this.productFreight = productFreight;
	}


	
}
