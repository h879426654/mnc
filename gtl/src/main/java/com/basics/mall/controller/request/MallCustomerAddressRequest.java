package com.basics.mall.controller.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.basics.common.TokenRequest;

public class MallCustomerAddressRequest extends TokenRequest {
	
private static final long serialVersionUID = 1L;
	
	/**
	 * 收货地址Id
	 */
	private String addressId;
	
	/**
	 * 联系电话
	 */
	@NotBlank(message = "{mallCustomerAddressRequest.addressPhone}")
	private String addressPhone;
	
	/**
	 * 联系姓名
	 */
	@NotBlank(message = "{mallCustomerAddressRequest.addressName}")
	private String addressName;
	
	/**
	 * 省ID
	 */
	@NotBlank(message = "{mallCustomerAddressRequest.addressProvince}")
	private String addressProvince;
	
	/**
	 * 市ID
	 */
	@NotBlank(message = "{mallCustomerAddressRequest.addressCity}")
	private String addressCity;
	
	/**
	 * 区ID
	 */
	private String addressArea;
	
	/**
	 * 详细地址
	 */
	@NotBlank(message = "{mallCustomerAddressRequest.addressInfo}")
	private String addressInfo;
	
	/**
	 * 是否为默认(1是 0否)
	 */
	@NotNull(message = "{mallCustomerAddressRequest.addressFlag}")
	private Integer addressFlag;
	
	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getAddressPhone() {
		return addressPhone;
	}

	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getAddressProvince() {
		return addressProvince;
	}

	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
	
	public String getAddressArea() {
		return addressArea;
	}

	public void setAddressArea(String addressArea) {
		this.addressArea = addressArea;
	}

	public String getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}

	public Integer getAddressFlag() {
		return addressFlag;
	}

	public void setAddressFlag(Integer addressFlag) {
		this.addressFlag = addressFlag;
	}

}
