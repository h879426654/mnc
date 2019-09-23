package com.basics.tr.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TrTradeMoneyBase extends BaseBean {
	/**
	 * 交易ID
	 */
	private String id;

	/**
	 * 国际ID
	 */
	private String countryId;

	/**
	 * 交易流水号
	 */
	private String tradeSerial;

	/**
	 * 发布用户ID
	 */
	private String customerId;

	/**
	 * 交易类型(1出售 2购买)
	 */
	private Integer tradeType;

	/**
	 * 交易价格
	 */
	private BigDecimal tradePrice;

	/**
	 * 平台币数量
	 */
	private BigDecimal moneyNum;

	/**
	 * 交易状态(1待交易2待支付 3待确认4交易投诉5交易取消6交易完成)
	 */
	private Integer tradeStatus;

	/**
	 * 购买用户ID
	 */
	private String customerBuyId;

	/**
	 * 匹配时间
	 */
	private Date tradeMatchTime;

	/**
	 * 支付方式(1微信 2支付宝)
	 */
	private Integer tradePayType;

	/**
	 * 支付时间
	 */
	private Date tradePayTime;

	/**
	 * 交易完成时间
	 */
	private Date tradeFinishTime;

	/**
	 * 审核状态状态(1待审核 2审核通过 3审核不通过)
	 */
	private Integer applyStatus;

	/**
	 * 审核意见
	 */
	private String applyContext;

	/**
	 * 审核时间
	 */
	private Date applyTime;

	/**
	 * 卖方应冻结平台币
	 */
	private BigDecimal lockMoneyNum;

	/**
	 * 卖方应冻结交易币
	 */
	private BigDecimal lockTradeCoin;

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
	private Date modifyTime;

	private String customerName;
	private String customerPhone;
	private String customerRealName;
	private String customerBuyName;
	private String customerBuyPhone;
	private String customerBuyRealName;
	private String tradeTypeName;
	private String applyStatusName;
	private String tradeStatusName;

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerRealName() {
		return customerRealName;
	}

	public void setCustomerRealName(String customerRealName) {
		this.customerRealName = customerRealName;
	}

	public String getCustomerBuyPhone() {
		return customerBuyPhone;
	}

	public void setCustomerBuyPhone(String customerBuyPhone) {
		this.customerBuyPhone = customerBuyPhone;
	}

	public String getCustomerBuyRealName() {
		return customerBuyRealName;
	}

	public void setCustomerBuyRealName(String customerBuyRealName) {
		this.customerBuyRealName = customerBuyRealName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerBuyName() {
		return customerBuyName;
	}

	public void setCustomerBuyName(String customerBuyName) {
		this.customerBuyName = customerBuyName;
	}

	public String getTradeTypeName() {
		return tradeTypeName;
	}

	public void setTradeTypeName(String tradeTypeName) {
		this.tradeTypeName = tradeTypeName;
	}

	public String getApplyStatusName() {
		return applyStatusName;
	}

	public void setApplyStatusName(String applyStatusName) {
		this.applyStatusName = applyStatusName;
	}

	public String getTradeStatusName() {
		return tradeStatusName;
	}

	public void setTradeStatusName(String tradeStatusName) {
		this.tradeStatusName = tradeStatusName;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTradeSerial() {
		return this.tradeSerial;
	}

	public void setTradeSerial(String tradeSerial) {
		this.tradeSerial = tradeSerial;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getTradeType() {
		return this.tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public BigDecimal getTradePrice() {
		return this.tradePrice;
	}

	public void setTradePrice(BigDecimal tradePrice) {
		this.tradePrice = tradePrice;
	}

	public BigDecimal getMoneyNum() {
		return this.moneyNum;
	}

	public void setMoneyNum(BigDecimal moneyNum) {
		this.moneyNum = moneyNum;
	}

	public Integer getTradeStatus() {
		return this.tradeStatus;
	}

	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getCustomerBuyId() {
		return this.customerBuyId;
	}

	public void setCustomerBuyId(String customerBuyId) {
		this.customerBuyId = customerBuyId;
	}

	public Date getTradeMatchTime() {
		return this.tradeMatchTime;
	}

	public void setTradeMatchTime(Date tradeMatchTime) {
		this.tradeMatchTime = tradeMatchTime;
	}

	public Integer getTradePayType() {
		return this.tradePayType;
	}

	public void setTradePayType(Integer tradePayType) {
		this.tradePayType = tradePayType;
	}

	public Date getTradePayTime() {
		return this.tradePayTime;
	}

	public void setTradePayTime(Date tradePayTime) {
		this.tradePayTime = tradePayTime;
	}

	public Date getTradeFinishTime() {
		return this.tradeFinishTime;
	}

	public void setTradeFinishTime(Date tradeFinishTime) {
		this.tradeFinishTime = tradeFinishTime;
	}

	public Integer getApplyStatus() {
		return this.applyStatus;
	}

	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}

	public String getApplyContext() {
		return this.applyContext;
	}

	public void setApplyContext(String applyContext) {
		this.applyContext = applyContext;
	}

	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public BigDecimal getLockMoneyNum() {
		return this.lockMoneyNum;
	}

	public void setLockMoneyNum(BigDecimal lockMoneyNum) {
		this.lockMoneyNum = lockMoneyNum;
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

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public BigDecimal getLockTradeCoin() {
		return lockTradeCoin;
	}

	public void setLockTradeCoin(BigDecimal lockTradeCoin) {
		this.lockTradeCoin = lockTradeCoin;
	}

}