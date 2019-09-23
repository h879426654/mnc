package com.basics.mall.controller.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import com.basics.common.TokenRequest;

public class PushShopAdvertRequest extends TokenRequest {

	private static final long serialVersionUID = -6817442105867823411L;

	@NotBlank(message = "{pushShopAdvertRequest.advertName}")
	private String advertName;

	@NotBlank(message = "{pushShopAdvertRequest.advertContext}")
	private String advertContext;

	@NotNull(message = "{pushShopAdvertRequest.advertImage}")
	private MultipartFile advertImage;
	
//	@NotNull(message = "营业执照不能为空！")
	private MultipartFile licence;
	
	private MultipartFile shopVideo;

	@NotBlank(message = "{pushShopAdvertRequest.advertClassifyId}")
	private String advertClassifyId;

	@NotBlank(message = "{mallCustomerAddressRequest.addressPhone}")
	private String advertPhone;

	@NotBlank(message = "{pushShopAdvertRequest.advertAddress}")
	private String advertAddress;

	@NotBlank(message = "{pushShopAdvertRequest.advertLongitude}")
	private String advertLongitude;

	@NotBlank(message = "{pushShopAdvertRequest.advertLatitude}")
	private String advertLatitude;
	
	@NotBlank(message = "{mallCustomerAddressRequest.addressProvince}")
	private String addressProvince;
	
	@NotBlank(message = "{mallCustomerAddressRequest.addressCity}")
	private String addressCity;
	
	@NotBlank(message = "{mallCustomerAddressRequest.addressArea}")
	private String addressArea;
	

	private List<MultipartFile> images;
	

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

	

	public MultipartFile getAdvertImage() {
		return advertImage;
	}

	public void setAdvertImage(MultipartFile advertImage) {
		this.advertImage = advertImage;
	}

	public String getAdvertClassifyId() {
		return advertClassifyId;
	}

	public void setAdvertClassifyId(String advertClassifyId) {
		this.advertClassifyId = advertClassifyId;
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

	public List<MultipartFile> getImages() {
		return images;
	}

	public void setImages(List<MultipartFile> images) {
		this.images = images;
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

	public MultipartFile getLicence() {
		return licence;
	}

	public void setLicence(MultipartFile licence) {
		this.licence = licence;
	}

	public MultipartFile getShopVideo() {
		return shopVideo;
	}

	public void setShopVideo(MultipartFile shopVideo) {
		this.shopVideo = shopVideo;
	}

	
}
