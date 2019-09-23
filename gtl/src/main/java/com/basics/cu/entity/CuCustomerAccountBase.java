package com.basics.cu.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CuCustomerAccountBase extends BaseBean {
	/**
	 * 用户ID
	 */
	private String id;

	/**
	 * 可用余额
	 */
	private BigDecimal useMoney;

	/**
	 * 冻结余额
	 */
	private BigDecimal lockMoney;

	/**
	 * 总共余额
	 */
	private BigDecimal totalMoney;

	/**
	 * 会员积分
	 */
	private BigDecimal customerIntegral;

	/**
	 * 可用链
	 */
	private BigDecimal useCoin;

	/**
	 * 冻结链
	 */
	private BigDecimal lockCoin;

	/**
	 * 总共链
	 */
	private BigDecimal totalCoin;

	/**
	 * 交易链
	 */
	private BigDecimal tradeCoin;

	/**
	 * 二级密码
	 */
	private String customerPayPass;

	/**
	 * 会员钱包
	 */
	private String customerPurse;

	/**
	 * 释放比例
	 */
	private BigDecimal rateNum;

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

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getUseMoney() {
		return this.useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}

	public BigDecimal getLockMoney() {
		return this.lockMoney;
	}

	public void setLockMoney(BigDecimal lockMoney) {
		this.lockMoney = lockMoney;
	}

	public BigDecimal getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public BigDecimal getCustomerIntegral() {
		return this.customerIntegral;
	}

	public void setCustomerIntegral(BigDecimal customerIntegral) {
		this.customerIntegral = customerIntegral;
	}

	public BigDecimal getUseCoin() {
		return this.useCoin;
	}

	public void setUseCoin(BigDecimal useCoin) {
		this.useCoin = useCoin;
	}

	public BigDecimal getLockCoin() {
		return this.lockCoin;
	}

	public void setLockCoin(BigDecimal lockCoin) {
		this.lockCoin = lockCoin;
	}

	public BigDecimal getTotalCoin() {
		return this.totalCoin;
	}

	public void setTotalCoin(BigDecimal totalCoin) {
		this.totalCoin = totalCoin;
	}

	public String getCustomerPayPass() {
		return this.customerPayPass;
	}

	public void setCustomerPayPass(String customerPayPass) {
		this.customerPayPass = customerPayPass;
	}

	public String getCustomerPurse() {
		return this.customerPurse;
	}

	public void setCustomerPurse(String customerPurse) {
		this.customerPurse = customerPurse;
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

	public BigDecimal getTradeCoin() {
		return tradeCoin;
	}

	public void setTradeCoin(BigDecimal tradeCoin) {
		this.tradeCoin = tradeCoin;
	}

	public BigDecimal getRateNum() {
		return rateNum;
	}

	public void setRateNum(BigDecimal rateNum) {
		this.rateNum = rateNum;
	}
}