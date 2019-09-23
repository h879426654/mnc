package com.basics.mall.controller.response;

import java.util.List;

import com.basics.common.DataPageListResponse;
import com.basics.mall.entity.MallProductClassify;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MallProductResponsePlus{
	private String classifyParentName;
	private List<MallProductClassify> productClassifys;

	public List<MallProductClassify> getProductClassifys() {
		return productClassifys;
	}
    @JsonProperty("child")
	private DataPageListResponse<MallProductResponse> items;
	
	
	public void setProductClassifys(List<MallProductClassify> productClassifys) {
		this.productClassifys = productClassifys;
	}

	public String getClassifyParentName() {
		return classifyParentName;
	}

	public void setClassifyParentName(String classifyParentName) {
		this.classifyParentName = classifyParentName;
	}

	public DataPageListResponse<MallProductResponse> getItems() {
		return items;
	}

	public void setItems(DataPageListResponse<MallProductResponse> items) {
		this.items = items;
	} 
	
	
}
