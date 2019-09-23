package com.basics.mall.controller.response;

import java.io.Serializable;
import java.util.List;

public class CustomerCarResponse implements Serializable {
	
	private static final long serialVersionUID = -2266619215939062540L;

	/**
    * 商家ID
    */
	private String shopId;

	/**
	* 商家名称
	*/
	private String shopName;

	private boolean checked = false;

	private List<CustomerCarData> carList;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<CustomerCarData> getCarList() {
		return carList;
	}

	public void setCarList(List<CustomerCarData> carList) {
		this.carList = carList;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
		
}
