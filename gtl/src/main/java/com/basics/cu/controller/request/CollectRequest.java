package com.basics.cu.controller.request;

import com.basics.common.DataRequest;
import org.hibernate.validator.constraints.NotBlank;

public class CollectRequest extends DataRequest {
	
	private static final long serialVersionUID = 742680166365561238L;

	@NotBlank(message = "商品Id不能为空")
	private String shopId;

	@NotBlank(message = "商品名称不能为空")
	private String shopName;

	private String phone;
	private String token;
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
