package com.basics.cu.controller.request;

import com.basics.common.TokenRequest;

public class ModifyCustomerHeadRequest extends TokenRequest {
	
	private static final long serialVersionUID = 150500030523392575L;

	private String headImg;

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

}
