package com.basics.common;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataPageListResponse<T> {
	/***
	 * 当前页数
	 */
	private Integer currentPageNo = 1;

	/***
	 * 总记录条数
	 */
	private Long totalCount = 0L;

	/**
	 * 总页数
	 */
	private Integer pageCount = 0;

	@JsonProperty("list")
	private List<T> items = new ArrayList<T>();

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public Integer getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(Integer currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

}
