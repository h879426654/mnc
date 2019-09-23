package com.basics.mall.vo;

import java.util.List;

import com.basics.mall.entity.BaseBean;

public class KindsVo extends BaseBean {

	private static final long serialVersionUID = 7735976577546590924L;

	private String id;
	private String name;
	private Integer order;
	private List<KindDetailVo> kindDetail;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public List<KindDetailVo> getKindDetail() {
		return kindDetail;
	}

	public void setKindDetail(List<KindDetailVo> kindDetail) {
		this.kindDetail = kindDetail;
	}
}
