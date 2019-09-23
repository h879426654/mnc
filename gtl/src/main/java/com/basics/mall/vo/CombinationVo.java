package com.basics.mall.vo;

import java.math.BigDecimal;

import com.basics.mall.entity.BaseBean;

public class CombinationVo extends BaseBean {
	private static final long serialVersionUID = -967650341929812048L;
	private String combinationStr;
	private Integer combinationStock;
	private BigDecimal combinationPrice;
	private String combinationImg;

	public String getCombinationStr() {
		return combinationStr;
	}

	public void setCombinationStr(String combinationStr) {
		this.combinationStr = combinationStr;
	}

	public Integer getCombinationStock() {
		return combinationStock;
	}

	public void setCombinationStock(Integer combinationStock) {
		this.combinationStock = combinationStock;
	}

	public BigDecimal getCombinationPrice() {
		return combinationPrice;
	}

	public String getCombinationImg() {
		return combinationImg;
	}

	public void setCombinationPrice(BigDecimal combinationPrice) {
		this.combinationPrice = combinationPrice;
	}

	public void setCombinationImg(String combinationImg) {
		this.combinationImg = combinationImg;
	}
	
}
