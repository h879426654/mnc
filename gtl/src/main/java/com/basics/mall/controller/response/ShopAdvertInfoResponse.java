package com.basics.mall.controller.response;

import java.io.Serializable;
import java.util.List;

public class ShopAdvertInfoResponse implements Serializable {

	private static final long serialVersionUID = -8706377449924566514L;

	/**
	 * 商家广告ID
	 */
	private String id;

	/**
	 * 商家名称
	 */
	private String advertName;

	/**
	 * 商家介绍
	 */
	private String advertContext;

	/**
	 * 商家广告封面图片
	 */
	private String advertImage;
	
	/**
	 * 
	 */
	private String shopLicence;
	
	/**
	 * 
	 */
	private String shopVideo;
	/**
	 * 联系方式
	 */
	private String advertPhone;

	/**
	 * 地址
	 */
	private String advertAddress;

	/**
	 * 经度
	 */
	private String advertLongitude;

	/**
	 * 纬度
	 */
	private String advertLatitude;

	private List<String> images;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdvertName() {
		return advertName;
	}

	public void setAdvertName(String advertName) {
		this.advertName = advertName;
	}

	public String getAdvertContext() {
		return advertContext;
	}

	public void setAdvertContext(String advertContext) {
		this.advertContext = advertContext;
	}

	public String getAdvertImage() {
		return advertImage;
	}

	public void setAdvertImage(String advertImage) {
		this.advertImage = advertImage;
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

	public String getAdvertLongitude() {
		return advertLongitude;
	}

	public void setAdvertLongitude(String advertLongitude) {
		this.advertLongitude = advertLongitude;
	}

	public String getAdvertLatitude() {
		return advertLatitude;
	}

	public void setAdvertLatitude(String advertLatitude) {
		this.advertLatitude = advertLatitude;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getShopLicence() {
		return shopLicence;
	}

	public void setShopLicence(String shopLicence) {
		this.shopLicence = shopLicence;
	}

	public String getShopVideo() {
		return shopVideo;
	}

	public void setShopVideo(String shopVideo) {
		this.shopVideo = shopVideo;
	}
	

}
