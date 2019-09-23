package com.basics.mall.controller.response;

import java.io.Serializable;
import java.util.List;

import com.basics.mall.entity.MallIndexType;

public class IndexProductResponse implements Serializable {
	
	private static final long serialVersionUID = 5352322008659929821L;

	private MallIndexType indexType;

	private List<MallProductResponse> goodsList;

	public MallIndexType getIndexType() {
		return indexType;
	}

	public List<MallProductResponse> getGoodsList() {
		return goodsList;
	}

	public void setIndexType(MallIndexType indexType) {
		this.indexType = indexType;
	}

	public void setGoodsList(List<MallProductResponse> goodsList) {
		this.goodsList = goodsList;
	}
	
	

}
