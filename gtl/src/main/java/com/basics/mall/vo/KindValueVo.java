package com.basics.mall.vo;

import java.io.Serializable;

public class KindValueVo implements Serializable {

	private static final long serialVersionUID = 1011391668938595946L;
	/**
	  * 商品子规格ID
	  */
	private String kindDetailId;

	/**
	 * 商品子规格值
	 */
	private String kindeDetailVaue;

	/**
	 * 子规格描述
	 */
	private String kindDetailDesc;

	public String getKindDetailId() {
		return kindDetailId;
	}

	public void setKindDetailId(String kindDetailId) {
		this.kindDetailId = kindDetailId;
	}

	public String getKindeDetailVaue() {
		return kindeDetailVaue;
	}

	public void setKindeDetailVaue(String kindeDetailVaue) {
		this.kindeDetailVaue = kindeDetailVaue;
	}

	public String getKindDetailDesc() {
		return kindDetailDesc;
	}

	public void setKindDetailDesc(String kindDetailDesc) {
		this.kindDetailDesc = kindDetailDesc;
	}
}
