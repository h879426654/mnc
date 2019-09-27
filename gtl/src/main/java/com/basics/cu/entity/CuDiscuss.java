package com.basics.cu.entity;



import java.util.Date;

public class CuDiscuss extends CuDiscussBase {
	public CuDiscuss id(int id) {
		this.setId(id);
		return this;
	}
    public CuDiscuss customerId(String customerId) {
        this.setCustomerId(customerId);
        return this;
    }
    public CuDiscuss shopId(String shopId) {
        this.setShopId(shopId);
        return this;
    }
    public CuDiscuss createTime(Date createTime) {
        this.setCreateTime(createTime);
        return this;
    }
    public CuDiscuss state(String state) {
        this.setState(state);
        return this;
    }
    public CuDiscuss name(String name) {
        this.setName(name);
        return this;
    }
    public CuDiscuss image(String image) {
        this.setImage(image);
        return this;
    }
    public CuDiscuss remark(String remark) {
        this.setRemark(remark);
        return this;
    }
    public CuDiscuss total(Long total) {
	    this.setTotal(total);
	    return this;
    }
    public CuDiscuss list(String list) {
        this.setList(list);
        return this;
    }
    public CuDiscuss pagey (Integer pagey) {
	    this.setPagey(pagey);
	    return this;
    }
    public CuDiscuss pages(Integer pages) {
        this.setPages(pages);
        return this;
    }
}