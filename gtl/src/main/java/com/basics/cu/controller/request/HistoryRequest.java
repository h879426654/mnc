package com.basics.cu.controller.request;

import com.basics.common.DataRequest;
import org.hibernate.validator.constraints.NotBlank;

public class HistoryRequest extends DataRequest {
	
	private static final long serialVersionUID = 742680166365561238L;

	@NotBlank(message = "用户id不能为空")
	private String customerId;
	@NotBlank(message = "商家id不能为空")
	private String shopId;
	@NotBlank(message = "商家名称不能为空")
	private String shopName;
	@NotBlank(message = "商家图片不能为空")
	private String image;
	@NotBlank(message = "商家地址不能为空")
	private String address;
	@NotBlank(message = "经度不能为空")
	private String longitude;
	@NotBlank(message = "纬度不能为空")
	private String latitude;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
