package com.basics.cu.controller.request;

import com.basics.common.DataRequest;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

public class ConsumeRequest extends DataRequest {

	private static final long serialVersionUID = 742680166365561238L;

	@NotBlank(message = "商家Id不能为空")
	private String shopId;

	@NotBlank(message = "商家名称不能为空")
	private String shopName;

	@NotBlank(message = "用户id不能为空")
	private String customerId;

	@NotBlank(message = "记账金额不能为空")
	private String money;

	@NotBlank(message = "手机号不能为空")
	private String phone;

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
