package com.basics.cu.entity;

import java.math.BigDecimal;
import java.util.Date;

class CuConsumeBase extends BaseBean {
	private String id;
	private String shopId;
	private String customerId;
	private String shopName;
	private BigDecimal money;
	private String phone;
	private String state;
	private String image;
	private String stateText;
	private Date createTime;
	private String pageN;
	private String pageS;
	private String appraise;
	private BigDecimal mtoken;

	public BigDecimal getMtoken() {
		return mtoken;
	}

	public void setMtoken(BigDecimal mtoken) {
		this.mtoken = mtoken;
	}

	public String getAppraise() {
		return appraise;
	}

	public void setAppraise(String appraise) {
		this.appraise = appraise;
	}

	public String getPageN() {
		return pageN;
	}

	public void setPageN(String pageN) {
		this.pageN = pageN;
	}

	public String getPageS() {
		return pageS;
	}

	public void setPageS(String pageS) {
		this.pageS = pageS;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStateText() {
		return stateText;
	}

	public void setStateText(String stateText) {
		this.stateText = stateText;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}