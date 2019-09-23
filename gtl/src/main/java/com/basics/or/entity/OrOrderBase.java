package com.basics.or.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrOrderBase extends BaseBean {
	/**
	 * 订单ID
	 */
	private String id;

	/**
	 * 店铺ID
	 */
	private String shopId;

	/**
	 * 客户ID
	 */
	private String customerId;

	/**
	 * 订单总价
	 */
	private BigDecimal orderTotalPrice;

	/**
	 * 支付金额
	 */
	private BigDecimal orderPayPrice;

	/**
	 * 支付方式(1支付宝 2微信 3银行卡)
	 */
	private Integer orderPayType;

	/**
	 * 交易流水号
	 */
	private String orderNumber;

	/**
	 * 订单支付时间
	 */
	private Date orderPayTime;

	/**
	 * 订单状态（1待支付 2待发货 3待签收 4待评价 5订单完成 6订单取消 7订单退款）
	 */
	private Integer orderStatus;

	/**
	 * 物流公司编号
	 */
	private String orderLogisticsCode;

	/**
	 * 物流公司名称
	 */
	private String orderLogisticsName;

	/**
	 * 物流单号
	 */
	private String orderLogisticsNum;

	/**
	 * 发货时间
	 */
	private Date orderLogisticsTime;

	/**
	 * 收货人姓名
	 */
	private String orderReceiver;

	/**
	 * 收货人联系方式
	 */
	private String orderReceiverPhone;

	/**
	 * 收货地址省ID
	 */
	private String addressProvince;

	/**
	 * 收货地址市ID
	 */
	private String addressCity;

	/**
	 * 收货地址区域ID
	 */
	private String addressArea;

	/**
	 * 收货详细地址
	 */
	private String addressInfo;

	/**
	 * 订单确认时间
	 */
	private Date orderFinishTime;

	/**
	 * 买家备注
	 */
	private String orderRemark;

	/**
	 * 版本号
	 */
	private Integer versionNum;

	/**
	 * 是否删除（1是 0否）
	 */
	private Integer flagDel;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 创建人
	 */
	private String createUser;

	/**
	 * 修改人
	 */
	private String modifyUser;

	/**
	 * 修改时间
	 */
	private Date modifyDate;

	private String customerName;
	private String customerPhone;
	private String orderPayTypeName;
	private String orderStatusName;

	private String location;
	private String shopName;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOrderPayTypeName() {
		return orderPayTypeName;
	}

	public void setOrderPayTypeName(String orderPayTypeName) {
		this.orderPayTypeName = orderPayTypeName;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public String getId() {
		return this.id;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShopId() {
		return this.shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getOrderTotalPrice() {
		return this.orderTotalPrice;
	}

	public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public BigDecimal getOrderPayPrice() {
		return this.orderPayPrice;
	}

	public void setOrderPayPrice(BigDecimal orderPayPrice) {
		this.orderPayPrice = orderPayPrice;
	}

	public Integer getOrderPayType() {
		return this.orderPayType;
	}

	public void setOrderPayType(Integer orderPayType) {
		this.orderPayType = orderPayType;
	}

	public String getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getOrderPayTime() {
		return this.orderPayTime;
	}

	public void setOrderPayTime(Date orderPayTime) {
		this.orderPayTime = orderPayTime;
	}

	public Integer getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderLogisticsCode() {
		return this.orderLogisticsCode;
	}

	public void setOrderLogisticsCode(String orderLogisticsCode) {
		this.orderLogisticsCode = orderLogisticsCode;
	}

	public String getOrderLogisticsName() {
		return this.orderLogisticsName;
	}

	public void setOrderLogisticsName(String orderLogisticsName) {
		this.orderLogisticsName = orderLogisticsName;
	}

	public String getOrderLogisticsNum() {
		return this.orderLogisticsNum;
	}

	public void setOrderLogisticsNum(String orderLogisticsNum) {
		this.orderLogisticsNum = orderLogisticsNum;
	}

	public String getOrderReceiver() {
		return this.orderReceiver;
	}

	public void setOrderReceiver(String orderReceiver) {
		this.orderReceiver = orderReceiver;
	}

	public String getOrderReceiverPhone() {
		return this.orderReceiverPhone;
	}

	public void setOrderReceiverPhone(String orderReceiverPhone) {
		this.orderReceiverPhone = orderReceiverPhone;
	}

	public String getAddressProvince() {
		return this.addressProvince;
	}

	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}

	public String getAddressCity() {
		return this.addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressArea() {
		return this.addressArea;
	}

	public void setAddressArea(String addressArea) {
		this.addressArea = addressArea;
	}

	public String getAddressInfo() {
		return this.addressInfo;
	}

	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}

	public Date getOrderFinishTime() {
		return this.orderFinishTime;
	}

	public void setOrderFinishTime(Date orderFinishTime) {
		this.orderFinishTime = orderFinishTime;
	}

	public String getOrderRemark() {
		return this.orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public Integer getVersionNum() {
		return this.versionNum;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	public Integer getFlagDel() {
		return this.flagDel;
	}

	public void setFlagDel(Integer flagDel) {
		this.flagDel = flagDel;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getOrderLogisticsTime() {
		return orderLogisticsTime;
	}

	public void setOrderLogisticsTime(Date orderLogisticsTime) {
		this.orderLogisticsTime = orderLogisticsTime;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
}