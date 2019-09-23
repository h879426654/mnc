package com.basics.mall.vo;

import com.basics.mall.entity.BaseBean;

public class KindDetailVo extends BaseBean {
	private static final long serialVersionUID = 7688917524904048930L;

	private String id;
	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
