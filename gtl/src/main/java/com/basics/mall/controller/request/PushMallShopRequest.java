package com.basics.mall.controller.request;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import com.basics.common.TokenRequest;

public class PushMallShopRequest extends TokenRequest {

	private static final long serialVersionUID = 1522079525041728377L;
	@NotBlank(message = "{pushMallShopRequest.shopName}")
	private String shopName;
	//	@NotNull(message = "商铺logo不能为空")
	private MultipartFile file;
	@NotBlank(message = "{pushMallShopRequest.shopService}")
	private String shopService;
	@NotBlank(message = "pushMallShopRequest.shopAddr")
	private String shopAddr;
	@NotBlank(message = "{registerUserRequest.password}")
	private String shopPass;
	@NotBlank(message = "{mallCustomerAddressRequest.addressPhone}")
	private String shopPhone;
	private MultipartFile licence;

	private List<MultipartFile> aptitudeFiles;

	private String shopLogo;
	private String shopLicence;
	public List<MultipartFile> getAptitudeFiles() {
		return aptitudeFiles;
	}

	public void setAptitudeFiles(List<MultipartFile> aptitudeFiles) {
		this.aptitudeFiles = aptitudeFiles;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getShopService() {
		return shopService;
	}

	public void setShopService(String shopService) {
		this.shopService = shopService;
	}

	public String getShopAddr() {
		return shopAddr;
	}

	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}

	public String getShopPass() {
		return shopPass;
	}

	public void setShopPass(String shopPass) {
		this.shopPass = shopPass;
	}

	public String getShopPhone() {
		return shopPhone;
	}

	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}

	public MultipartFile getLicence() {
		return licence;
	}

	public void setLicence(MultipartFile licence) {
		this.licence = licence;
	}

	public String getShopLicence() {
		return shopLicence;
	}

	public void setShopLicence(String shopLicence) {
		this.shopLicence = shopLicence;
	}


	
	
	
}
