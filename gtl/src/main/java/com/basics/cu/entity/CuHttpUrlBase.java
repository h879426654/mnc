package com.basics.cu.entity;

import java.math.BigDecimal;

class CuHttpUrlBase extends BaseBean {
	private String id;
	private String url;
	private String name;
	private String image;
	private String userName;
	private BigDecimal mncCoin;
	private BigDecimal mp;
	private String httpList;
	private Integer vermicelli;
	private String shopId;
	private String isAdv;

	public String getIsAdv() {
		return isAdv;
	}

	public void setIsAdv(String isAdv) {
		this.isAdv = isAdv;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public Integer getVermicelli() {
		return vermicelli;
	}

	public void setVermicelli(Integer vermicelli) {
		this.vermicelli = vermicelli;
	}

	public String getHttpList() {
		return httpList;
	}

	public void setHttpList(String httpList) {
		this.httpList = httpList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getMncCoin() {
		return mncCoin;
	}

	public void setMncCoin(BigDecimal mncCoin) {
		this.mncCoin = mncCoin;
	}

	public BigDecimal getMp() {
		return mp;
	}

	public void setMp(BigDecimal mp) {
		this.mp = mp;
	}

}