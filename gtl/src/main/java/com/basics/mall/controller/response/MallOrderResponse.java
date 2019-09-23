package com.basics.mall.controller.response;

import java.util.ArrayList;
import java.util.List;

import com.basics.or.entity.OrOrder;

public class MallOrderResponse extends OrOrder {
	
	private static final long serialVersionUID = 4994806430512966840L;
	
	/**
	 * 商家名称
	 */
	private String shopName;
	
	private String shopId;
	
	/**
	 * 商品
	 */
	private List<MallOrderProductDetailsResponse> list = new ArrayList<>();
	
	private Integer flag;

	public Integer getFlag() {
		return flag;
	}
	
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public List<MallOrderProductDetailsResponse> getList() {
		return list;
	}

	public void setList(List<MallOrderProductDetailsResponse> list) {
		this.list = list;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	
	
	
}
