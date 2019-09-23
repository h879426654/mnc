package com.basics.cu.controller.response;

import java.io.Serializable;

public class BaseImgResponse implements Serializable{

	private static final long serialVersionUID = 5282079883460622249L;

	private String img;
	
	private String url;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	
}
