package com.basics.mall.vo;

import java.io.Serializable;

public class KindVo implements Serializable {

	private static final long serialVersionUID = 4484693632089911480L;

	/**
	  * 主规格名称
	  */
	private String kindName;

	/**
	 * 主规格值
	 */
	private String kindValue;

	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public String getKindValue() {
		return kindValue;
	}

	public void setKindValue(String kindValue) {
		this.kindValue = kindValue;
	}
}
