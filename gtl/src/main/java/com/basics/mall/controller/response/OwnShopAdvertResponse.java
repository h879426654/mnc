package com.basics.mall.controller.response;

import java.io.Serializable;
import java.util.List;

import com.basics.mall.entity.MallShopAdvert;

public class OwnShopAdvertResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private MallShopAdvert mallShopAdvert;
	
	private List<String> imgs ;

	public MallShopAdvert getMallShopAdvert() {
		return mallShopAdvert;
	}

	public void setMallShopAdvert(MallShopAdvert mallShopAdvert) {
		this.mallShopAdvert = mallShopAdvert;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

	

}
