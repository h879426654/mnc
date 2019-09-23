package com.basics.mall.vo;

import java.io.Serializable;
import java.util.List;

public class MainKindVo implements Serializable {
	private static final long serialVersionUID = -3966733249131916401L;
	/**
	  * 主规格ID
	  */
	private String kindId;

	/**
	 * 主规格名称
	 */
	private String kindName;

	/**
	 * 排序
	 */
	private Integer order;

	/**
	 * 规格列表
	 */
	private List<KindValueVo> kindValues;

	public String getKindId() {
		return kindId;
	}

	public void setKindId(String kindId) {
		this.kindId = kindId;
	}

	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public List<KindValueVo> getKindValues() {
		return kindValues;
	}

	public void setKindValues(List<KindValueVo> kindValues) {
		this.kindValues = kindValues;
	}
}
