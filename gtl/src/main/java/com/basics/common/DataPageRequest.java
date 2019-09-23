package com.basics.common;

/**
 * DataItemRequest. 扩展数据请求,带参数.
 * 
 * @param <Item>
 *         the generic type
 */
public class DataPageRequest extends DataRequest {

 private static final long serialVersionUID = 637974025122284757L;

    private int pageNo = 1;
    private int pageSize = 10;

  

    public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
