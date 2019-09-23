package com.basics.cu.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CuCustomerAccount extends CuCustomerAccountBase {
	/**
	 * 用户ID
	 */
	public CuCustomerAccount id(String id) {
		this.setId(id);
		return this;
	}

	/**
	 * 可用余额
	 */
	public CuCustomerAccount useMoney(BigDecimal useMoney) {
		this.setUseMoney(useMoney);
		return this;
	}

	/**
	 * 冻结余额
	 */
	public CuCustomerAccount lockMoney(BigDecimal lockMoney) {
		this.setLockMoney(lockMoney);
		return this;
	}

	/**
	 * 总共余额
	 */
	public CuCustomerAccount totalMoney(BigDecimal totalMoney) {
		this.setTotalMoney(totalMoney);
		return this;
	}

	/**
	 * 会员积分
	 */
	public CuCustomerAccount customerIntegral(BigDecimal customerIntegral) {
		this.setCustomerIntegral(customerIntegral);
		return this;
	}

	/**
	 * 可用链
	 */
	public CuCustomerAccount useCoin(BigDecimal useCoin) {
		this.setUseCoin(useCoin);
		return this;
	}

	/**
	 * 冻结链
	 */
	public CuCustomerAccount lockCoin(BigDecimal lockCoin) {
		this.setLockCoin(lockCoin);
		return this;
	}

	/**
	 * 总共链
	 */
	public CuCustomerAccount totalCoin(BigDecimal totalCoin) {
		this.setTotalCoin(totalCoin);
		return this;
	}

	/**
	 * 二级密码
	 */
	public CuCustomerAccount customerPayPass(String customerPayPass) {
		this.setCustomerPayPass(customerPayPass);
		return this;
	}

	/**
	 * 会员钱包
	 */
	public CuCustomerAccount customerPurse(String customerPurse) {
		this.setCustomerPurse(customerPurse);
		return this;
	}

	/**
	 * 释放比例
	 */
	public CuCustomerAccount rateNum(BigDecimal rateNum) {
		this.setRateNum(rateNum);
		return this;
	}

	/**
	 * 版本号
	 */
	public CuCustomerAccount versionNum(Integer versionNum) {
		this.setVersionNum(versionNum);
		return this;
	}

	/**
	 * 是否删除（1是 0否）
	 */
	public CuCustomerAccount flagDel(Integer flagDel) {
		this.setFlagDel(flagDel);
		return this;
	}

	/**
	 * 创建时间
	 */
	public CuCustomerAccount createTime(Date createTime) {
		this.setCreateTime(createTime);
		return this;
	}

	/**
	 * 创建人
	 */
	public CuCustomerAccount createUser(String createUser) {
		this.setCreateUser(createUser);
		return this;
	}

	/**
	 * 修改人
	 */
	public CuCustomerAccount modifyUser(String modifyUser) {
		this.setModifyUser(modifyUser);
		return this;
	}

	/**
	 * 修改时间
	 */
	public CuCustomerAccount modifyTime(Date modifyTime) {
		this.setModifyTime(modifyTime);
		return this;
	}
}