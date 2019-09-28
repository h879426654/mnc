package com.basics.mall.controller.response;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
/**
 * 商品数据
 */
public class MallAdvertResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "shopName")
	private String shopName;
	private String advertImage;
	private String advertContext;
	private String person;
	private String advertPhone;
	private String advertAddress;
	private String shopLicence;
	private String token;
	private String city;
	private String region;
	private String claasifyId;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getAdvertImage() {
		return advertImage;
	}

	public void setAdvertImage(String advertImage) {
		this.advertImage = advertImage;
	}

	public String getAdvertContext() {
		return advertContext;
	}

	public void setAdvertContext(String advertContext) {
		this.advertContext = advertContext;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getAdvertPhone() {
		return advertPhone;
	}

	public void setAdvertPhone(String advertPhone) {
		this.advertPhone = advertPhone;
	}

	public String getAdvertAddress() {
		return advertAddress;
	}

	public void setAdvertAddress(String advertAddress) {
		this.advertAddress = advertAddress;
	}

	public String getShopLicence() {
		return shopLicence;
	}

	public void setShopLicence(String shopLicence) {
		this.shopLicence = shopLicence;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getClaasifyId() {
		return claasifyId;
	}

	public void setClaasifyId(String claasifyId) {
		this.claasifyId = claasifyId;
	}
}
