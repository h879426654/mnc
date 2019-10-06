package com.basics.cu.entity;


public class CuPicture extends CuPictureBase {
	public CuPicture id(String id) {
		this.setId(id);
		return this;
	}
	public CuPicture image(String image) {
		this.setImage(image);
		return this;
	}
	public CuPicture type(String type) {
		this.setType(type);
		return this;
	}
	public CuPicture url(String url) {
		this.setUrl(url);
		return this;
	}
	public CuPicture delFlag(String delFlag) {
		this.setDelFlag(delFlag);
		return this;
	}
}