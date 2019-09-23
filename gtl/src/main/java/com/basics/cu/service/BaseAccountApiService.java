package com.basics.cu.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.basics.cu.entity.CuCustomerAccount;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.mall.entity.MallShop;
import com.basics.or.entity.OrOrder;
import com.basics.support.FormResultSupport;
import com.basics.tr.entity.TrTradeCoin;
import com.basics.tr.entity.TrTradeConvert;
import com.basics.tr.entity.TrTradeMoney;

public interface BaseAccountApiService {

	/**
	 * 发放/扣除余额 flagReward true发放 flase扣除
	 */
	FormResultSupport doUpdateMoneyByAdmin(CuCustomerAccount entity, boolean flagReward);

	/**
	 * 发放/扣除积分 flagReward true发放 flase扣除
	 */
	FormResultSupport doUpdateIntegralByAdmin(CuCustomerAccount entity, boolean flagReward);

	/**
	 * 发放/扣除链 flagReward true发放 flase扣除
	 */
	FormResultSupport doUpdateCoinByAdmin(CuCustomerAccount entity, boolean flagReward);

	/**
	 * 修改推荐人
	 */
	FormResultSupport dealChangeReferee(CuCustomerInfo entity);

	FormResultSupport doCancleTradeCoin(TrTradeCoin entity);

	FormResultSupport doFinishTradeCoin(TrTradeCoin entity);

	FormResultSupport doCancelTradeMoney(TrTradeMoney entity);

	FormResultSupport doFinishTradeMoney(TrTradeMoney entity);

	FormResultSupport doCancleTradeConvert(TrTradeConvert entity);

	FormResultSupport doFinishTradeConvert(TrTradeConvert entity);

	FormResultSupport doApplyShop(MallShop entity);

	FormResultSupport doApplyShopByRefuse(MallShop entity);

	/**
	 * 取消订单
	 */
	FormResultSupport cancelOrder(OrOrder entity);

	/**
	 * 更新会员等级
	 */
	void updateCustomerLevel();

	/**
	 * 删除用户
	 */
	FormResultSupport doDeleteCustomer(CuCustomerInfo entity);

	/**
	 * 系统统计
	 */
	void countSystemData(String gridPager, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 恢复等级
	 */
	String recoveryLevel(String id);

	/**
	 * 交易审核
	 */
	FormResultSupport doApplyMoneyTrade(TrTradeMoney entity);

	FormResultSupport doApplyCoinTrade(TrTradeCoin entity);

	FormResultSupport doUpdateTradeCoinByAdmin(CuCustomerAccount entity, boolean flagReward);

	FormResultSupport doGranReleaseRate(CuCustomerAccount entity);
}
