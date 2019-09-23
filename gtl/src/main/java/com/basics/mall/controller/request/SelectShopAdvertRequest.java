package com.basics.mall.controller.request;

import com.basics.common.TokenPageRequest;

public class SelectShopAdvertRequest extends TokenPageRequest {

	private static final long serialVersionUID = -1282668793768004165L;

	private String advertName;

	private String advertClassifyId;
	
	private String adCode;

	public String getAdvertName() {
		return advertName;
	}

	public void setAdvertName(String advertName) {
		this.advertName = advertName;
	}

	public String getAdvertClassifyId() {
		return advertClassifyId;
	}

	public void setAdvertClassifyId(String advertClassifyId) {
		this.advertClassifyId = advertClassifyId;
	}

	public String getAdCode() {
		return adCode;
	}

	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}


}
