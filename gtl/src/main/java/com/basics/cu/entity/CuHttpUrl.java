package com.basics.cu.entity;


import java.math.BigDecimal;

public class CuHttpUrl extends CuHttpUrlBase {
	public CuHttpUrl Id(String id) {
		this.setId(id);
		return this;
	}
	public CuHttpUrl url(String url) {
		this.setUrl(url);
		return this;
	}
	public CuHttpUrl name(String name) {
		this.setName(name);
		return this;
	}
	public CuHttpUrl image(String image) {
		this.setImage(image);
		return this;
	}
	public CuHttpUrl userName(String userName) {
		this.setUserName(userName);
		return this;
	}
	public CuHttpUrl mncCoin(BigDecimal mncCoin) {
		this.setMncCoin(mncCoin);
		return this;
	}
	public CuHttpUrl mncMp(BigDecimal mp) {
		this.setMp(mp);
		return this;
	}
	public CuHttpUrl httpList(String httpList) {
		this.setHttpList(httpList);
		return this;
	}
	public CuHttpUrl vermicelli(Integer vermicelli) {
		this.setVermicelli(vermicelli);
		return this;
	}
	public CuHttpUrl shopId(String shopId) {
		this.setShopId(shopId);
		return this;
	}
	public CuHttpUrl isAdv(String isAdv) {
		this.setIsAdv(isAdv);
		return this;
	}
}