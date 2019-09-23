package com.basics.support.activemq;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.basics.app.entity.AppOption;
import com.basics.app.entity.AppRole;
import com.basics.app.entity.AppUser;
import com.basics.app.entity.AppUserRole;
import com.basics.common.BaseApiService;
import com.basics.common.Constant;
import com.basics.cu.entity.CuCustomerAccount;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.cu.entity.CuCustomerReferee;
import com.basics.cu.entity.CuCustomerSign;
import com.basics.cu.entity.CuDrawReward;
import com.basics.mall.entity.MallShop;
import com.basics.mall.entity.MallShopAdvert;
import com.basics.or.entity.OrOrder;
import com.basics.support.CommonSupport;
import com.basics.support.DateUtils;
import com.basics.support.GuidUtils;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.SerialnumberUtils;
import com.basics.support.mybatis.CacheUsed;
import com.basics.sys.entity.SysConfig;
import com.basics.sys.entity.SysMarketValue;
import com.basics.sys.entity.SysRule;
import com.basics.sys.entity.SysTurntable;
import com.basics.sys.entity.SysTurntableReward;
import com.basics.tr.entity.TrConvert;
import com.basics.tr.entity.TrTradeCoin;
import com.basics.tr.entity.TrTradeConvert;
import com.basics.tr.entity.TrTradeMoney;

@Transactional
@Component
public class TopicMessageListener extends BaseApiService implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			String messageStr = textMessage.getText();
			JSONObject json = JSONObject.parseObject(messageStr);
			CommonSupport.checkNotNull(json, "messageStr error");
			String customerId = json.getString("customerId");
			Integer activemqType = json.getInteger("activemqType");
			BigDecimal num = json.getBigDecimal("num");
			String sourceId = json.getString("sourceId");
			String activemqId = json.getString("activemqId");
			String obj = json.getString("obj");
			dealMessage(customerId, activemqType, num, sourceId, activemqId, obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NullPointerException(String.valueOf("消息处理错误:") + e.getMessage());
		}
	}

	/**
	 * 根据类型处理消息
	 */
	private synchronized void dealMessage(String customerId, Integer activemqType, BigDecimal num, String sourceId, String activemqId, String obj) {
		CacheUsed.clearAllCache();
		if (Constant.ACTIVEMQ_TYPE_1 == activemqType.intValue()) {
			doUpdateMoney(customerId, num, true);
			// 添加收支流水
			doAddCustomerProfit(customerId, null, num, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "MC发放");
			doAddCustomerProfitAdmin(customerId, null, num, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "MC发放", sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_2 == activemqType.intValue()) {
			doUpdateMoney(customerId, num, false);
			// 添加收支流水
			doAddCustomerProfit(customerId, null, num, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_1, "MC扣除");
			doAddCustomerProfitAdmin(customerId, null, num, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_1, "MC扣除", sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_3 == activemqType.intValue()) {
			doUpdateIntegral(customerId, num, true);
			// 添加收支流水
			doAddCustomerProfit(customerId, null, num, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "MP发放");
			doAddCustomerProfitAdmin(customerId, null, num, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "MP发放", sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_4 == activemqType.intValue()) {
			doUpdateIntegral(customerId, num, false);
			// 添加收支流水
			doAddCustomerProfit(customerId, null, num, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_3, "MP扣除");
			doAddCustomerProfitAdmin(customerId, null, num, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_3, "MP扣除", sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_5 == activemqType.intValue()) {
			doUpdateCoin(customerId, num, true);
			// 添加收支流水
			doAddCustomerProfit(customerId, null, num, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_2, "MNC发放");
			doAddCustomerProfitAdmin(customerId, null, num, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_2, "MNC发放", sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_6 == activemqType.intValue()) {
			doUpdateCoin(customerId, num, false);
			// 添加收支流水
			doAddCustomerProfit(customerId, null, num, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_2, "MNC扣除");
			doAddCustomerProfitAdmin(customerId, null, num, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_2, "MNC扣除", sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_7 == activemqType.intValue()) {
			doPushTradeMoney(customerId, num);
		} else if (Constant.ACTIVEMQ_TYPE_8 == activemqType.intValue()) {
			doMatchingTradeMoney(customerId, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_9 == activemqType.intValue()) {
			confirmTradeForMoney(customerId, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_10 == activemqType.intValue()) {
			doCancelTradeMoney(customerId, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_11 == activemqType.intValue()) {
			doTradeConvertMoney(customerId, sourceId, num);
		} else if (Constant.ACTIVEMQ_TYPE_12 == activemqType.intValue()) {
			confirmTradeForConvert(customerId, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_13 == activemqType.intValue()) {
			doCancelTradeConvert(customerId, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_14 == activemqType.intValue()) {
			doPushTradeCoin(customerId, num, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_15 == activemqType.intValue()) {
			doMatchingTradeCoin(customerId, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_16 == activemqType.intValue()) {
			confirmTradeForCoin(customerId, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_17 == activemqType.intValue()) {
			cancleMyTradeCoin(customerId, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_18 == activemqType.intValue()) {
			dealMoneyToIntegral(customerId, num);
		} else if (Constant.ACTIVEMQ_TYPE_19 == activemqType.intValue()) {
			doMoneyToCoin(customerId, num);
		} else if (Constant.ACTIVEMQ_TYPE_20 == activemqType.intValue()) {
			coinToMoney(customerId, num);
		} else if (Constant.ACTIVEMQ_TYPE_21 == activemqType.intValue()) {
			doFinishTradeMoneyByAdmin(customerId, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_22 == activemqType.intValue()) {
			doCancleTradeMoneyByAdmin(customerId, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_23 == activemqType.intValue()) {
			doFinishTradeConvertByAdmin(customerId, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_24 == activemqType.intValue()) {
			doCancleTradeConvertByAdmin(customerId, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_25 == activemqType.intValue()) {
			doFinishTradeCoinByAdmin(customerId, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_26 == activemqType.intValue()) {
			doCancleTradeCoinByAdmin(customerId, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_27 == activemqType.intValue()) {
			staticRelease();
		} else if (Constant.ACTIVEMQ_TYPE_28 == activemqType.intValue()) {
			// 商城支付
			doPayOrderPrice(customerId, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_29 == activemqType.intValue()) {
			// 确认收货
			doConfirmOrder(customerId, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_30 == activemqType.intValue()) {
			// 商家申请
			doApplyShop(customerId, num, obj);
		} else if (Constant.ACTIVEMQ_TYPE_31 == activemqType.intValue()) {
			// 商家通过
			doApplyAgreeByAdmin(customerId, sourceId, obj);
		} else if (Constant.ACTIVEMQ_TYPE_32 == activemqType.intValue()) {
			// 商家拒绝
			doApplyRefuseByAdmin(customerId, sourceId, obj);
		} else if (Constant.ACTIVEMQ_TYPE_33 == activemqType.intValue()) {
			// 大转盘抽奖
			doTurntable(customerId, num, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_34 == activemqType) {
			// "商家取消订单";
			doCancelOrder(customerId, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_35 == activemqType) {
			doTradeByQRCode(customerId, num, sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_36 == activemqType) {
			//规定时间未支付取消交易
			doCancelTradeByMatchTimeOver();
		} else if (Constant.ACTIVEMQ_TYPE_37 == activemqType) {
			//审核失败
			doApplyMoneyTradeFail(sourceId, obj);
		} else if (Constant.ACTIVEMQ_TYPE_38 == activemqType) {
			//审核失败
			doApplyCoinTradeFail(sourceId, obj);
		} else if (Constant.ACTIVEMQ_TYPE_39 == activemqType) {
			//签到
			doSign(customerId);
		} else if (Constant.ACTIVEMQ_TYPE_40 == activemqType.intValue()) {
			doUpdateTradeCoin(customerId, num, true);
			// 添加收支流水
			doAddCustomerProfit(customerId, null, num, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_4, "交易链发放");
			doAddCustomerProfitAdmin(customerId, null, num, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_4, "交易链发放", sourceId);
		} else if (Constant.ACTIVEMQ_TYPE_41 == activemqType.intValue()) {
			doUpdateTradeCoin(customerId, num, false);
			// 添加收支流水
			doAddCustomerProfit(customerId, null, num, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_4, "交易链扣除");
			doAddCustomerProfitAdmin(customerId, null, num, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_4, "交易链扣除", sourceId);
		}
		createSysActivemqResponse(customerId, activemqType, activemqId, "操作成功");
	}

	/**
	 * 签到
	 * 
	 * @param customerId
	 */
	private void doSign(String customerId) {
		CuCustomerSign sign = cuCustomerSignDao.queryOne(new QueryFilterBuilder().put("customerId", customerId).put("nowDate", new Date()).build());
		if (null != sign) {
			CommonSupport.checkNotNull(null, "已签到");
		}
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		sign = new CuCustomerSign();
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		account.setCustomerIntegral(account.getCustomerIntegral().add(new BigDecimal(rule.getSignRewardNum())));
		cuCustomerAccountDao.save(account);
		sign.customerId(account.getId()).signNum(rule.getSignRewardNum()).signTime(new Date()).createTime(new Date());
		cuCustomerSignDao.save(sign);
		//流水
		doAddCustomerProfit(account.getId(), sign.getId(), new BigDecimal(rule.getSignRewardNum()), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "会员签到");
	}

	/**
	 * MNC交易审核失败
	 * 
	 * @param sourceId
	 */
	public void doApplyCoinTradeFail(String sourceId, String obj) {
		TrTradeCoin entity = JSONObject.parseObject(obj, TrTradeCoin.class);
		TrTradeCoin trade = trTradeCoinDao.get(sourceId);
		if (Constant.APPLY_STATUS_1 != trade.getApplyStatus()) {
			CommonSupport.checkNotNull(null, "该交易已审核");
		}
		if (Constant.TRADE_STATUS_1 == trade.getTradeStatus() && StringUtils.isNotBlank(trade.getCustomerId())) {
			CuCustomerAccount account = cuCustomerAccountDao.get(trade.getCustomerId());
			if (account.getLockCoin().doubleValue() < trade.getLockMoneyNum().doubleValue()) {
				CommonSupport.checkNotNull(null, "数据错误");
			}
			account.setUseCoin(account.getUseCoin().add(trade.getLockMoneyNum()));
			account.setTotalCoin(account.getTotalCoin().add(trade.getLockMoneyNum()));
			account.setLockCoin(account.getLockCoin().subtract(trade.getLockMoneyNum()));
			cuCustomerAccountDao.save(account);
			doAddCustomerProfit(account.getId(), trade.getId(), trade.getLockMoneyNum(), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_2, "审核不通过");
		}
		trade.setTradeStatus(Constant.TRADE_STATUS_5);
		trade.setApplyStatus(Constant.APPLY_STATUS_3);
		trade.setApplyContext(entity.getApplyContext());
		trade.setApplyTime(new Date());
		trTradeCoinDao.save(trade);
	}

	/**
	 * 余额交易审核失败
	 * 
	 * @param sourceId
	 */
	public void doApplyMoneyTradeFail(String sourceId, String obj) {
		TrTradeMoney entity = JSONObject.parseObject(obj, TrTradeMoney.class);
		TrTradeMoney trade = trTradeMoneyDao.get(sourceId);
		if (Constant.APPLY_STATUS_1 != trade.getApplyStatus()) {
			CommonSupport.checkNotNull(null, "该交易已审核");
		}
		if (Constant.TRADE_STATUS_1 == trade.getTradeStatus() && StringUtils.isNotBlank(trade.getCustomerId())) {
			CuCustomerAccount account = cuCustomerAccountDao.get(trade.getCustomerId());
			if (account.getLockMoney().doubleValue() < trade.getLockMoneyNum().doubleValue()) {
				CommonSupport.checkNotNull(null, "数据错误");
			}
			account.setUseMoney(account.getUseMoney().add(trade.getLockMoneyNum()));
			account.setTotalMoney(account.getTotalMoney().add(trade.getLockMoneyNum()));
			account.setLockMoney(account.getLockMoney().subtract(trade.getLockMoneyNum()));
			cuCustomerAccountDao.save(account);
			doAddCustomerProfit(account.getId(), trade.getId(), trade.getLockMoneyNum(), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "审核不通过");
		}
		trade.setTradeStatus(Constant.TRADE_STATUS_5);
		trade.setApplyStatus(Constant.APPLY_STATUS_3);
		trade.setApplyContext(entity.getApplyContext());
		trade.setApplyTime(new Date());
		trTradeMoneyDao.save(trade);
	}

	/**
	 * 规定时间未付款, 冻结账号
	 */
	private void doCancelTradeByMatchTimeOver() {
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		List<TrTradeMoney> list = trTradeMoneyDao
				.query(new QueryFilterBuilder().put("applyStatus", Constant.APPLY_STATUS_2).put("tradeStatus", Constant.TRADE_STATUS_2).put("tradeMatchingTimeOver", rule.getTradeTimeOut()).build());
		if (CollectionUtils.isNotEmpty(list)) {
			for (TrTradeMoney trade : list) {
				CuCustomerAccount account = cuCustomerAccountDao.get(trade.getCustomerId());
				if (account.getLockMoney().doubleValue() >= trade.getLockMoneyNum().doubleValue()) {
					account.setUseMoney(account.getUseMoney().add(trade.getLockMoneyNum()));
					account.setTotalMoney(account.getTotalMoney().add(trade.getLockMoneyNum()));
					account.setLockMoney(account.getLockMoney().subtract(trade.getLockMoneyNum()));
					cuCustomerAccountDao.save(account);
					doAddCustomerProfit(account.getId(), trade.getId(), trade.getLockMoneyNum(), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "交易取消");
					// 冻结买方
					if (Constant.TRADE_STATUS_2 == trade.getTradeStatus().intValue()) {
						doFrozenCustomer(trade.getCustomerBuyId(), "买方" + rule.getTradeTimeOut() + "小时内未付款");
					}
					trade.setTradeFinishTime(new Date());
					trade.setTradeStatus(Constant.TRADE_STATUS_5);
					trTradeMoneyDao.save(trade);
				}
			}
		}
	}

	/**
	 * 二维码收付款
	 */
	private void doTradeByQRCode(String customerId, BigDecimal num, String sourceId) {
		// source
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		CommonSupport.checkNotNull(account, "用户account错误");
		/*
		 * if (Constant.UN_ACTIVE_NUM >
		 * sourceAccount.getCustomerIntegral().doubleValue()) {
		 * CommonSupport.checkNotNull(null, "您账号暂未激活, 无法兑换"); }
		 */
		if (account.getUseMoney().doubleValue() < num.doubleValue()) {
			CommonSupport.checkNotNull(null, "MC不足，付款失败");
		}
		//判断交易链是否足够
		AppOption tradeRateValue = appOptionDao.get("TRADE_RATE_VALUE");
		if (null != tradeRateValue) {
			BigDecimal tradeRateCoin = BigDecimal.valueOf(Double.valueOf(tradeRateValue.getCode()));
			if (account.getTradeCoin().doubleValue() < num.multiply(tradeRateCoin).doubleValue()) {
				CommonSupport.checkNotNull(null, "交易链不足！");
			}
			account.setTradeCoin(account.getTradeCoin().subtract(num.multiply(tradeRateCoin)));
			doAddCustomerProfit(customerId, sourceId, num.multiply(tradeRateCoin), Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_4, "二维码-交易链");
		}
		account.setUseMoney(account.getUseMoney().subtract(num));
		account.setTotalMoney(account.getTotalMoney().subtract(num));
		doAddCustomerProfit(customerId, sourceId, num, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_1, "二维码-付款");

		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		CommonSupport.checkNotNull(rule, "系统规则不存在");

		CuCustomerAccount target = cuCustomerAccountDao.get(sourceId);
		CommonSupport.checkNotNull(target, "用户account错误");
		target.setUseMoney(target.getUseMoney().add(num.multiply(rule.getConvertRate())));
		target.setTotalMoney(target.getTotalMoney().add(num.multiply(rule.getConvertRate())));
		target.setCustomerIntegral(target.getCustomerIntegral().add(num.subtract(num.multiply(rule.getConvertRate()))));
		doAddCustomerProfit(sourceId, customerId, num.multiply(rule.getConvertRate()), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "二维码-收款");
		doAddCustomerProfit(sourceId, customerId, num.subtract(num.multiply(rule.getConvertRate())), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "二维码-收款");
		// 判断是否为商家
		MallShopAdvert advert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("customerId", target.getId()).put("applyStatus", Constant.APPLY_STATUS_2).build());

		if (null != advert) {
			account.setCustomerIntegral(account.getCustomerIntegral().add(num.multiply(rule.getMoneySaleRate())));
			doAddCustomerProfit(customerId, sourceId, num.multiply(rule.getMoneySaleRate()), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "二维码-收款(赠送)");
		}

		cuCustomerAccountDao.save(account);
		cuCustomerAccountDao.save(target);

		// 判断其上级是否能获取奖励
		CuCustomerReferee referee = cuCustomerRefereeDao.get(customerId);
		if (null != referee && !"0".equals(referee.getRefereeId())) {
			rewardParent(customerId, referee.getRefereeId(), num, Constant.PROFIT_STATUS_2, Constant.STATE_YES);
		}
		/*
		 * CuCustomerReferee sourceReferee = cuCustomerRefereeDao.get(sourceId); if
		 * (null != sourceReferee && !"0".equals(sourceReferee.getRefereeId())) {
		 * rewardParent(sourceId, sourceReferee.getRefereeId(), num,
		 * Constant.PROFIT_STATUS_1, Constant.STATE_YES); }
		 */

	}

	/**
	 * 商家取消订单
	 * 
	 * @param sourceId
	 *            订单ID
	 */
	private void doCancelOrder(String modifyUser, String sourceId) {
		OrOrder order = orOrderDao.get(sourceId);
		CommonSupport.checkNotNull(order, "订单不存在");
		CuCustomerAccount account = cuCustomerAccountDao.get(order.getCustomerId());
		CommonSupport.checkNotNull(account, "账号错误");
		if (Constant.ORDER_STATUS_2 == order.getOrderStatus() || Constant.ORDER_STATUS_3 == order.getOrderStatus()) {
			if (account.getLockMoney().doubleValue() >= order.getOrderPayPrice().doubleValue()) {
				account.setUseMoney(account.getUseMoney().add(order.getOrderPayPrice()));
				account.setTotalMoney(account.getTotalMoney().add(order.getOrderPayPrice()));
				account.setLockMoney(account.getLockMoney().subtract(order.getOrderPayPrice()));
				cuCustomerAccountDao.save(account);
				doAddCustomerProfit(account.getId(), order.getId(), order.getOrderPayPrice(), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "取消订单");
				order.setOrderStatus(Constant.ORDER_STATUS_6);
				order.setOrderFinishTime(new Date());
				order.setModifyUser(modifyUser);
				orOrderDao.save(order);
			}
		} else if (Constant.ORDER_STATUS_1 == order.getOrderStatus()) {
			order.setOrderStatus(Constant.ORDER_STATUS_6);
			order.setOrderFinishTime(new Date());
			order.setModifyUser(modifyUser);
			orOrderDao.save(order);
		}
	}

	/**
	 * 静态释放
	 */
	private void staticRelease() {
		List<CuCustomerAccount> list = cuCustomerAccountDao.queryExtend(new QueryFilterBuilder().put("GTCustomerIntegral", Constant.UN_ACTIVE_NUM).build(), "queryReleaseList");
		//List<CuCustomerAccount> list = cuCustomerAccountDao.queryRelease(new QueryFilterBuilder().put("GTCustomerIntegral", Constant.UN_ACTIVE_NUM).build());
		if (CollectionUtils.isNotEmpty(list)) {
			/*SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
			CommonSupport.checkNotNull(rule, "系统规则不存在");*/
			SysConfig sixConfig = sysConfigDao.getConfigByCode("RELEASE_SIX_RATE");
			SysConfig threeConfig = sysConfigDao.getConfigByCode("RELEASE_THREE_RATE");
			SysConfig oneConfig = sysConfigDao.getConfigByCode("RELEASE_ONE_RATE");
			Date nowDate = new Date();
			for (CuCustomerAccount account : list) {
				//BigDecimal rate = account.getCustomerIntegral().multiply(rule.getReleaseRateDay());
				BigDecimal rate = BigDecimal.ZERO;
				if (account.getRateNum().doubleValue() > 0) {
					rate = account.getRateNum();
				} else {
					if (account.getCreateTime().getTime() <= DateUtils.getTimeBeforByNum(nowDate, 180).getTime()) {
						if (null != sixConfig) {
							rate = sixConfig.getConfigValue();
						}
					} else if (account.getCreateTime().getTime() > DateUtils.getTimeBeforByNum(nowDate, 180).getTime()
							&& account.getCreateTime().getTime() <= DateUtils.getTimeBeforByNum(nowDate, 90).getTime()) {
						if (null != threeConfig) {
							rate = threeConfig.getConfigValue();
						}
					} else {
						if (null != oneConfig) {
							rate = oneConfig.getConfigValue();
						}
					}
				}
				BigDecimal reward = account.getCustomerIntegral().multiply(rate);
				if (reward.doubleValue() > 0) {
					account.setUseMoney(account.getUseMoney().add(reward));
					account.setTotalMoney(account.getTotalMoney().add(reward));
					account.setCustomerIntegral(account.getCustomerIntegral().subtract(reward));
					int i = cuCustomerAccountDao.save(account);
					if (Constant.STATE_NO < i) {
						doAddCustomerProfit(account.getId(), null, reward, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "当日MC释放");
						doAddCustomerProfit(account.getId(), null, reward, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_3, "当日MC释放");
					}
				}
			}
		}
	}

	/**
	 * 后台取消链交易
	 */
	private void doCancleTradeCoinByAdmin(String customerId, String sourceId) {
		TrTradeCoin trade = trTradeCoinDao.queryOne(new QueryFilterBuilder().put("id", sourceId).put("LTtradeStatus", Constant.TRADE_STATUS_5).build());
		CommonSupport.checkNotNull(trade, "该交易不存在或当前状态不能取消");
		if (StringUtils.isNotBlank(trade.getCustomerId())) {
			CuCustomerAccount account = cuCustomerAccountDao.get(trade.getCustomerId());
			if (account.getLockCoin().doubleValue() < trade.getLockMoneyNum().doubleValue()) {
				CommonSupport.checkNotNull(null, "会员交易数据错误");
			}
			account.setUseCoin(account.getUseCoin().add(trade.getLockMoneyNum()));
			account.setTotalCoin(account.getTotalCoin().add(trade.getLockMoneyNum()));
			account.setLockCoin(account.getLockCoin().subtract(trade.getLockMoneyNum()));
			cuCustomerAccountDao.save(account);
			// 流水
			doAddCustomerProfit(account.getId(), trade.getId(), trade.getLockMoneyNum(), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_2, "交易取消");
		}
		trade.setTradeStatus(Constant.TRADE_STATUS_5);
		trade.setTradeFinishTime(new Date());
		trTradeCoinDao.save(trade);
	}

	/**
	 * 后台完成链交易
	 */
	private void doFinishTradeCoinByAdmin(String customerId, String sourceId) {
		confirmTradeForCoin(customerId, sourceId);
	}

	/**
	 * 后台取消定向交易
	 */
	private void doCancleTradeConvertByAdmin(String customerId, String sourceId) {
		// 判断当前交易是否为待审核状态
		TrTradeConvert trade = trTradeConvertDao.queryOne(new QueryFilterBuilder().put("id", sourceId).put("tradeIng", "tradeIng").build());
		CommonSupport.checkNotNull(trade, "交易不存在或已经取消");
		// 解冻出售方货币
		CuCustomerAccount account = cuCustomerAccountDao.get(trade.getCustomerId());
		CommonSupport.checkNotNull(account, "卖方账户获取失败");
		if (account.getLockMoney().doubleValue() < trade.getLockMoneyNum().doubleValue()) {
			CommonSupport.checkNotNull(null, "会员交易数据错误");
		}
		account.setUseMoney(account.getUseMoney().add(trade.getLockMoneyNum()));
		account.setLockMoney(account.getLockMoney().subtract(trade.getLockMoneyNum()));
		account.setTotalMoney(account.getTotalMoney().add(trade.getLockMoneyNum()));
		cuCustomerAccountDao.save(account);
		// 流水
		doAddCustomerProfit(account.getId(), trade.getId(), trade.getLockMoneyNum(), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "转账取消");
		trade.setTradeStatus(Constant.TRADE_STATUS_5);
		trade.setTradeFinishTime(new Date());
		trTradeConvertDao.save(trade);
	}

	/**
	 * 后台完成定向交易
	 */
	private void doFinishTradeConvertByAdmin(String customerId, String sourceId) {
		confirmTradeForConvert(customerId, sourceId);
	}

	/**
	 * 后台取消余额交易
	 */
	private void doCancleTradeMoneyByAdmin(String customerId, String sourceId) {
		TrTradeMoney trade = trTradeMoneyDao.queryOne(new QueryFilterBuilder().put("id", sourceId).put("LTtradeStatus", Constant.TRADE_STATUS_5).build());
		CommonSupport.checkNotNull(trade, "交易不存在或已取消");
		if (StringUtils.isNotBlank(trade.getCustomerId())) {
			CuCustomerAccount account = cuCustomerAccountDao.get(trade.getCustomerId());
			if (account.getLockMoney().doubleValue() < trade.getLockMoneyNum().doubleValue()) {
				CommonSupport.checkNotNull(null, "会员交易数据错误");
			}
			account.setUseMoney(account.getUseMoney().add(trade.getLockMoneyNum()));
			account.setLockMoney(account.getLockMoney().subtract(trade.getLockMoneyNum()));
			account.setTotalMoney(account.getTotalMoney().add(trade.getLockMoneyNum()));
			cuCustomerAccountDao.save(account);
			// 流水
			doAddCustomerProfit(account.getId(), trade.getId(), trade.getLockMoneyNum(), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "交易取消");
		}
		trade.setTradeStatus(Constant.TRADE_STATUS_5);
		trade.setTradeFinishTime(new Date());
		trTradeMoneyDao.save(trade);
	}

	/**
	 * 后台完成余额交易
	 */
	private void doFinishTradeMoneyByAdmin(String customerId, String sourceId) {
		confirmTradeForMoney(customerId, sourceId);
	}

	/**
	 * 余额转链
	 */
	private void doMoneyToCoin(String customerId, BigDecimal needNum) {
		TrConvert trConvert = trConvertDao.queryOne(new QueryFilterBuilder().build());
		SysMarketValue marketValue = sysMarketValueDao.queryOne(new QueryFilterBuilder().put("nowDate", new Date()).build());
		BigDecimal coin = needNum.divide(marketValue.getRmbRate(), 5, BigDecimal.ROUND_DOWN); // 多少个链
		if (trConvert.getConvertNum().doubleValue() < coin.doubleValue()) {
			CommonSupport.checkNotNull(null, "奖池不足, 无法兑换");
		}
		trConvert.setConvertNum(trConvert.getConvertNum().subtract(coin));
		int i = trConvertDao.save(trConvert);
		if (i != 1) {
			CommonSupport.checkNotNull(null, "兑换繁忙，请稍后重试");
		}
		// 判断会员余额是否足够
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		if (account.getUseMoney().doubleValue() < needNum.doubleValue()) {
			CommonSupport.checkNotNull(null, "兑换错误");
		}
		if (StringUtils.isNotBlank(judgeLimitCoin(customerId, coin))) {
			CommonSupport.checkNotNull(null, "兑换错误");
		}
		account.setUseCoin(account.getUseCoin().add(coin));
		account.setTotalCoin(account.getTotalCoin().add(coin));
		account.setTotalMoney(account.getTotalMoney().subtract(needNum));
		account.setUseMoney(account.getUseMoney().subtract(needNum));
		cuCustomerAccountDao.save(account);
		doAddCustomerProfit(customerId, null, needNum, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_1, "MNC兑换");
		doAddCustomerProfit(customerId, null, coin, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_2, "MNC兑换");
		createAccountConvert(account.getId(), Constant.CONVERT_TYPE_4, needNum, coin, trConvert.getConvertSerial(), "MNC兑换");
		// 判断其上级是否能获取奖励
		CuCustomerReferee referee = cuCustomerRefereeDao.get(customerId);
		if (null != referee && !"0".equals(referee.getRefereeId())) {
			rewardParent(customerId, referee.getRefereeId(), needNum, Constant.PROFIT_STATUS_2, Constant.STATE_YES);
		}
	}

	/**
	 * 取消链交易
	 */
	private void cancleMyTradeCoin(String customerId, String sourceId) {
		TrTradeCoin trade = trTradeCoinDao.queryOne(new QueryFilterBuilder().put("id", sourceId).put("cancleTrade", Constant.TRADE_STATUS_2).build());
		if (StringUtils.isNotBlank(trade.getCustomerId())) {
			CuCustomerAccount customerAccount = cuCustomerAccountDao.get(trade.getCustomerId());
			CommonSupport.checkNotNull(customerAccount, "卖方账户获取失败");
			if (customerAccount.getLockCoin().doubleValue() < trade.getLockMoneyNum().doubleValue()) {
				CommonSupport.checkNotNull(null, "会员交易数据错误");
			}
			if (trade.getLockTradeCoin().doubleValue() > 0) {
				customerAccount.setTradeCoin(customerAccount.getTradeCoin().add(trade.getLockTradeCoin()));
				doAddCustomerProfit(customerAccount.getId(), trade.getId(), trade.getLockTradeCoin(), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_4, "交易链交易取消");
			}
			customerAccount.setUseCoin(customerAccount.getUseCoin().add(trade.getLockMoneyNum()));
			customerAccount.setTotalCoin(customerAccount.getTotalCoin().add(trade.getLockMoneyNum()));
			customerAccount.setLockCoin(customerAccount.getLockCoin().subtract(trade.getLockMoneyNum()));
			cuCustomerAccountDao.save(customerAccount);
			// 流水
			doAddCustomerProfit(customerAccount.getId(), trade.getId(), trade.getLockMoneyNum(), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_2, "交易取消");
		}
		trade.setTradeStatus(Constant.TRADE_STATUS_5);
		trade.setTradeFinishTime(new Date());
		trTradeCoinDao.save(trade);
	}

	/**
	 * 匹配链交易
	 */
	private void doMatchingTradeCoin(String customerId, String sourceId) {
		TrTradeCoin trade = trTradeCoinDao.queryOne(new QueryFilterBuilder().put("id", sourceId).put("tradeStatus", Constant.TRADE_STATUS_1).build());
		CommonSupport.checkNotNull(trade, "该交易不存在或已被交易");
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		CommonSupport.checkNotNull(rule, "交易规则不存在");
		if (customerId.equals(trade.getCustomerId()) || customerId.equals(trade.getCustomerBuyId())) {
			CommonSupport.checkNotNull(null, "该交易本人不能购买");
		}
		if (Constant.TRADE_TYPE_2 == trade.getTradeType().intValue()) {
			// 冻结平台币
			CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
			CommonSupport.checkNotNull(account, "会员账号错误");
			/*
			 * if (Constant.UN_ACTIVE_TRADE_NUM >
			 * account.getCustomerIntegral().doubleValue()) {
			 * CommonSupport.checkNotNull(null, "您账号暂未激活, 无法匹配"); }
			 */
			if (account.getUseCoin().doubleValue() < trade.getLockMoneyNum().doubleValue()) {
				CommonSupport.checkNotNull(null, "您的MNC不足，无法匹配");
			} else {
				account.setUseCoin(keepFiveNum(account.getUseCoin().subtract(trade.getLockMoneyNum())));
				account.setLockCoin(keepFiveNum(account.getLockCoin().add(trade.getLockMoneyNum())));
				account.setTotalCoin(keepFiveNum(account.getTotalCoin().subtract(trade.getLockMoneyNum())));
				cuCustomerAccountDao.save(account);
				// 流水
				doAddCustomerProfit(account.getId(), trade.getId(), trade.getLockMoneyNum(), Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_2, "MNC交易-出售");
			}
			trade.setCustomerId(customerId);
		} else {
			trade.setCustomerBuyId(customerId);
		}
		pushCustomerMessage(trade.getCustomerId(), trade.getId(), "MNC交易通知", "您的交易" + trade.getTradeSerial() + "已经匹配成功，并" + rule.getTradeTimeOut() + "小时内完成此交易", Constant.STATE_NO,
				Constant.Message_TYPE_2);
		pushCustomerMessage(trade.getCustomerBuyId(), trade.getId(), "MNC交易通知", "您的交易" + trade.getTradeSerial() + "已经匹配成功，并" + rule.getTradeTimeOut() + "小时内完成此交易", Constant.STATE_NO,
				Constant.Message_TYPE_2);
		trade.tradeStatus(Constant.TRADE_STATUS_2).tradeMatchTime(new Date());
		trTradeCoinDao.save(trade);
	}

	/**
	 * 发布链交易
	 */
	private void doPushTradeCoin(String customerId, BigDecimal num, String sourceId) {
		if (num.doubleValue() <= 0) {
			CommonSupport.checkNotNull(null, "金额不合法");
		}
		CuCustomerInfo info = cuCustomerInfoDao.get(customerId);
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		CommonSupport.checkNotNull(account, "会员账号错误");
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		CommonSupport.checkNotNull(rule, "系统规则不存在");
		BigDecimal rate = keepFiveNum(num.add(num.multiply(rule.getTradeRate())));
		if (account.getUseCoin().doubleValue() < rate.doubleValue()) {
			CommonSupport.checkNotNull(null, "MP不足，无法发布");
		}
		TrTradeCoin trade = new TrTradeCoin();
		AppOption tradeRateValue = appOptionDao.get("TRADE_RATE_VALUE");
		if (null != tradeRateValue) {
			BigDecimal tradeRateCoin = BigDecimal.valueOf(Double.valueOf(tradeRateValue.getCode()));
			if (account.getTradeCoin().doubleValue() < num.multiply(tradeRateCoin).doubleValue()) {
				CommonSupport.checkNotNull(null, "交易链不足，无法发布！");
			}
			trade.setLockTradeCoin(num.multiply(tradeRateCoin));
			account.setTradeCoin(account.getTradeCoin().subtract(num.multiply(tradeRateCoin)));
			doAddCustomerProfit(account.getId(), trade.getId(), trade.getLockTradeCoin(), Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_4, "交易链交易-出售");
		}
		/*
		 * if (Constant.UN_ACTIVE_TRADE_NUM >
		 * account.getCustomerIntegral().doubleValue()) {
		 * CommonSupport.checkNotNull(null, "您账号暂未激活, 无法发布"); }
		 */
		account.setUseCoin(keepFiveNum(account.getUseCoin().subtract(rate)));
		account.setLockCoin(keepFiveNum(account.getLockCoin().add(rate)));
		account.setTotalCoin(keepFiveNum(account.getTotalCoin().subtract(rate)));
		cuCustomerAccountDao.save(account);
		BigDecimal tradePrice = BigDecimal.valueOf(Double.valueOf(sourceId));
		trade.setLockMoneyNum(rate);
		trade.setCustomerId(customerId);
		trade.tradeType(Constant.TRADE_TYPE_1).moneyNum(num).tradePrice(tradePrice).tradeSerial(SerialnumberUtils.generateUsePrefix("CEC", true)).tradeStatus(Constant.TRADE_STATUS_1)
				.createTime(new Date()).applyStatus(Constant.APPLY_STATUS_1).countryId(info.getCountryId());
		if (Constant.STATE_NO == rule.getTradeApplyFlag().intValue()) {
			trade.setApplyStatus(Constant.APPLY_STATUS_2);
		}
		trTradeCoinDao.save(trade);
		// 流水
		doAddCustomerProfit(account.getId(), trade.getId(), rate, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_2, "MNC交易-出售");
	}

	/**
	 * 取消余额定向交易
	 */
	private void doCancelTradeConvert(String customerId, String sourceId) {
		TrTradeConvert trade = trTradeConvertDao.queryOne(new QueryFilterBuilder().put("id", sourceId).put("tradeStatus", Constant.TRADE_STATUS_2).build());
		CommonSupport.checkNotNull(null, "该交易不存在或当前状态不能取消交易");
		if (!trade.getCustomerId().equals(customerId) && !trade.getCustomerBuyId().equals(customerId)) {
			CommonSupport.checkNotNull(null, "当前交易不属于您");
		}
		CuCustomerAccount account = cuCustomerAccountDao.get(trade.getCustomerId());
		CommonSupport.checkNotNull(account, "用户account错误， id:" + trade.getCustomerId());
		if (account.getLockMoney().doubleValue() < trade.getLockMoneyNum().doubleValue()) {
			CommonSupport.checkNotNull(null, "交易数据错误");
		}
		account.setUseMoney(account.getUseMoney().add(trade.getLockMoneyNum()));
		account.setTotalMoney(account.getTotalMoney().add(trade.getLockMoneyNum()));
		account.setLockMoney(account.getLockMoney().subtract(trade.getLockMoneyNum()));
		cuCustomerAccountDao.save(account);
		// 流水
		doAddCustomerProfit(account.getId(), trade.getId(), trade.getLockMoneyNum(), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "取消MC定向交易");
		trade.setTradeStatus(Constant.TRADE_STATUS_5);
		trade.setTradeFinishTime(new Date());
		trTradeConvertDao.save(trade);

	}

	/**
	 * 余额定向交易
	 */
	@Transactional
	private void doTradeConvertMoney(String customerId, String sourceId, BigDecimal num) {
		if (null == num || 0 >= num.doubleValue()) {
			CommonSupport.checkNotNull(null, "数量不合法");
			return;
		}
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		CommonSupport.checkNotNull(account, "用户account错误， id:" + customerId);
		if (account.getUseMoney().doubleValue() < num.doubleValue()) {
			CommonSupport.checkNotNull(null, "余额不足");
			return;
		}
		if (customerId.equals(sourceId)) {
			CommonSupport.checkNotNull(null, "您不能给您自己转账");
			return;
		}
		/*
		 * if (Constant.UN_ACTIVE_TRADE_NUM >
		 * account.getCustomerIntegral().doubleValue()) {
		 * CommonSupport.checkNotNull(null, "您账号暂未激活, 无法转账"); }
		 */
		//判断交易链是否足够
		AppOption tradeRateValue = appOptionDao.get("TRADE_RATE_VALUE");
		if (null != tradeRateValue) {
			BigDecimal tradeRateCoin = BigDecimal.valueOf(Double.valueOf(tradeRateValue.getCode()));
			if (account.getTradeCoin().doubleValue() < num.multiply(tradeRateCoin).doubleValue()) {
				CommonSupport.checkNotNull(null, "交易链不足");
				return;
			}
			account.setTradeCoin(account.getTradeCoin().subtract(num.multiply(tradeRateCoin)));
			doAddCustomerProfit(account.getId(), sourceId, num.multiply(tradeRateCoin), Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_4, "交易链交易");
		}
		account.setUseMoney(account.getUseMoney().subtract(num));
		account.setTotalMoney(account.getTotalMoney().subtract(num));
		account.setLockMoney(account.getLockMoney().add(num));
		cuCustomerAccountDao.save(account);
		doAddCustomerProfit(customerId, sourceId, num, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_1, "MC定向交易");
		String tradeId = GuidUtils.generateSimpleGuid();
		TrTradeConvert trade = new TrTradeConvert();
		trade.id(tradeId).customerId(customerId).customerBuyId(sourceId).moneyNum(num).lockMoneyNum(num).tradeStatus(Constant.TRADE_STATUS_3)
				.tradeSerial(SerialnumberUtils.generateUsePrefix("转账", true)).applyStatus(Constant.APPLY_STATUS_2).applyTime(new Date()).createTime(new Date());
		trTradeConvertDao.insert(trade);
		confirmTradeForConvert(customerId, tradeId);
	}

	/**
	 * 取消余额交易
	 */
	private void doCancelTradeMoney(String customerId, String sourceId) {
		TrTradeMoney trade = trTradeMoneyDao.queryOne(new QueryFilterBuilder().put("id", sourceId).put("cancleTrade", Constant.TRADE_STATUS_2).build());
		if (StringUtils.isNotBlank(trade.getCustomerId())) {
			CuCustomerAccount account = cuCustomerAccountDao.get(trade.getCustomerId());
			if (account.getLockMoney().doubleValue() < trade.getLockMoneyNum().doubleValue()) {
				CommonSupport.checkNotNull(null, "会员交易数据错误");
			}
			if (trade.getLockTradeCoin().doubleValue() > 0) {
				account.setTradeCoin(account.getTradeCoin().add(trade.getLockTradeCoin()));
				doAddCustomerProfit(account.getId(), trade.getId(), trade.getLockTradeCoin(), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_4, "交易链交易取消");
			}
			account.setUseMoney(account.getUseMoney().add(trade.getLockMoneyNum()));
			account.setTotalMoney(account.getTotalMoney().add(trade.getLockMoneyNum()));
			account.setLockMoney(account.getLockMoney().subtract(trade.getLockMoneyNum()));
			cuCustomerAccountDao.save(account);
			// 流水
			doAddCustomerProfit(account.getId(), trade.getId(), trade.getLockMoneyNum(), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "MC交易取消");
		}
		trade.setTradeStatus(Constant.TRADE_STATUS_5);
		trade.setTradeFinishTime(new Date());
		trTradeMoneyDao.save(trade);
	}

	/**
	 * 匹配余额交易
	 */
	private void doMatchingTradeMoney(String customerId, String sourceId) {
		TrTradeMoney trade = trTradeMoneyDao.queryOne(new QueryFilterBuilder().put("id", sourceId).put("tradeStatus", Constant.TRADE_STATUS_1).build());
		CommonSupport.checkNotNull(trade, "该交易不存在或已被交易");
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		if (Constant.TRADE_TYPE_1 == trade.getTradeType().intValue()) {
			trade.setCustomerBuyId(customerId);
		} else {
			/*
			 * if (Constant.UN_ACTIVE_TRADE_NUM >
			 * account.getCustomerIntegral().doubleValue()) {
			 * CommonSupport.checkNotNull(null, "您账号暂未激活, 无法匹配"); }
			 */

			if (!judgeTradeTime(customerId)) {
				CommonSupport.checkNotNull(null, "今日出售次数已满");
			}

			if (account.getUseMoney().doubleValue() < trade.getLockMoneyNum().doubleValue()) {
				CommonSupport.checkNotNull(null, "MC不足，无法匹配");
			}
			account.setUseMoney(keepFiveNum(account.getUseMoney().subtract(trade.getLockMoneyNum())));
			account.setLockMoney(keepFiveNum(account.getLockMoney().add(trade.getLockMoneyNum())));
			account.setTotalMoney(keepFiveNum(account.getTotalMoney().subtract(trade.getLockMoneyNum())));
			cuCustomerAccountDao.save(account);
			// 流水
			doAddCustomerProfit(account.getId(), trade.getId(), trade.getLockMoneyNum(), Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_1, "MC交易-出售");
			trade.setCustomerId(customerId);
		}
		pushCustomerMessage(trade.getCustomerId(), trade.getId(), "MC交易通知", "您的交易" + trade.getTradeSerial() + "已经匹配成功，并" + rule.getTradeTimeOut() + "小时内完成此交易", Constant.STATE_NO,
				Constant.Message_TYPE_2);
		pushCustomerMessage(trade.getCustomerBuyId(), trade.getId(), "MC交易通知", "您的交易" + trade.getTradeSerial() + "已经匹配成功，并" + rule.getTradeTimeOut() + "小时内完成此交易", Constant.STATE_NO,
				Constant.Message_TYPE_2);
		trade.tradeStatus(Constant.TRADE_STATUS_2).tradeMatchTime(new Date());
		trTradeMoneyDao.save(trade);
	}

	/**
	 * 发布余额交易(出售)
	 */
	private void doPushTradeMoney(String customerId, BigDecimal num) {
		CuCustomerInfo info = cuCustomerInfoDao.get(customerId);
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		/*
		 * if (Constant.UN_ACTIVE_TRADE_NUM >
		 * account.getCustomerIntegral().doubleValue()) {
		 * CommonSupport.checkNotNull(null, "您账号暂未激活, 无法发布"); }
		 */

		if (!judgeTradeTime(customerId)) {
			CommonSupport.checkNotNull(null, "今日出售次数已满");
		}

		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		CommonSupport.checkNotNull(rule, "系统规则不存在");
		BigDecimal rate = keepFiveNum(num.add(num.multiply(rule.getTradeRate())));
		if (account.getUseMoney().doubleValue() < rate.doubleValue()) {
			CommonSupport.checkNotNull(null, "MC不足，无法发布");
		}
		TrTradeMoney trade = new TrTradeMoney();
		//判断交易链是否足够
		AppOption tradeRateValue = appOptionDao.get("TRADE_RATE_VALUE");
		if (null != tradeRateValue) {
			BigDecimal tradeRateCoin = BigDecimal.valueOf(Double.valueOf(tradeRateValue.getCode()));
			if (account.getTradeCoin().doubleValue() < num.multiply(tradeRateCoin).doubleValue()) {
				CommonSupport.checkNotNull(null, "交易链不足，无法发布！");
			}
			account.setTradeCoin(account.getTradeCoin().subtract(num.multiply(tradeRateCoin)));
			trade.setLockTradeCoin(num.multiply(tradeRateCoin));
			doAddCustomerProfit(customerId, trade.getId(), trade.getLockTradeCoin(), Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_4, "交易链交易-出售");
		}
		account.setUseMoney(keepFiveNum(account.getUseMoney().subtract(rate)));
		account.setLockMoney(keepFiveNum(account.getLockMoney().add(rate)));
		account.setTotalMoney(keepFiveNum(account.getTotalMoney().subtract(rate)));
		cuCustomerAccountDao.save(account);
		trade.customerId(customerId).tradeType(Constant.TRADE_TYPE_1).moneyNum(num).lockMoneyNum(rate).tradePrice(BigDecimal.ONE).tradeSerial(SerialnumberUtils.generateUsePrefix("余额", true))
				.tradeStatus(Constant.TRADE_STATUS_1).createTime(new Date()).applyStatus(Constant.APPLY_STATUS_1).countryId(info.getCountryId());
		if (Constant.STATE_NO == rule.getTradeApplyFlag().intValue()) {
			trade.setApplyStatus(Constant.APPLY_STATUS_2);
		}
		trTradeMoneyDao.save(trade);
		// 流水
		doAddCustomerProfit(customerId, trade.getId(), rate, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_1, "MC交易-出售");
	}

	/**
	 * 商城支付
	 */
	private void doPayOrderPrice(String customerId, String sourceId) {
		Date now = new Date();
		OrOrder order = orOrderDao.queryOne(new QueryFilterBuilder().put("id", sourceId).put("customerId", customerId).put("orderStatus", Constant.ORDER_STATUS_1).build());
		CommonSupport.checkNotNull(order, "订单错误， id:" + sourceId);
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		CommonSupport.checkNotNull(account, "用户account的错误");
		if (account.getUseMoney().doubleValue() >= order.getOrderPayPrice().doubleValue()) {
			account.setUseMoney(account.getUseMoney().subtract(order.getOrderPayPrice()));
			account.setTotalMoney(account.getTotalMoney().subtract(order.getOrderPayPrice()));
			account.setLockMoney(account.getLockMoney().add(order.getOrderPayPrice()));
			cuCustomerAccountDao.save(account);
			doAddCustomerProfit(account.getId(), order.getId(), order.getOrderPayPrice(), Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_1, "订单支付");
			order.setOrderPayTime(now);
			order.setOrderStatus(Constant.ORDER_STATUS_2);
			orOrderDao.save(order);

			// 给商家推送一条消息
			MallShop shop = mallShopDao.get(order.getShopId());
			CommonSupport.checkNotNull(shop, "商家不存在");
			pushCustomerMessage(shop.getCustomerId(), order.getId(), "商家发货提醒", "您的商城有人支付成功, 请到后台及时处理订单", Constant.STATE_NO, Constant.Message_TYPE_3);
		}
	}

	/**
	 * 确认收货
	 */
	private void doConfirmOrder(String customerId, String sourceId) {
		OrOrder order = orOrderDao.queryOne(new QueryFilterBuilder().put("id", sourceId).put("customerId", customerId).put("orderStatus", Constant.ORDER_STATUS_3).build());
		CommonSupport.checkNotNull(order, "订单错误， id:" + sourceId);
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		CommonSupport.checkNotNull(account, "用户account的错误");
		SysRule sysRule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		if (account.getLockMoney().doubleValue() >= order.getOrderPayPrice().doubleValue()) {
			account.setLockMoney(account.getLockMoney().subtract(order.getOrderPayPrice()));
			BigDecimal coin;
			if (null == sysRule) {
				coin = order.getOrderPayPrice().multiply(new BigDecimal(0.8));
			} else {
				coin = order.getOrderPayPrice().multiply(sysRule.getMoneySaleRate());
			}
			account.setCustomerIntegral(account.getCustomerIntegral().add(coin));
			cuCustomerAccountDao.save(account);
			doAddCustomerProfit(account.getId(), order.getId(), coin, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "商城购物返利");
			order.setOrderStatus(Constant.ORDER_STATUS_4);
			order.setOrderFinishTime(new Date());
			orOrderDao.save(order);
			// 商家账号
			MallShop shop = mallShopDao.get(order.getShopId());
			CommonSupport.checkNotNull(shop, "店铺为空， id:" + order.getShopId());
			CuCustomerAccount shopAccount = cuCustomerAccountDao.get(shop.getCustomerId());
			CommonSupport.checkNotNull(shopAccount, "店铺account的错误， id:" + shop.getCustomerId());
			// 更新商家账号
			BigDecimal money;
			if (null == sysRule) {
				money = order.getOrderPayPrice().multiply(new BigDecimal(0.8));
			} else {
				money = order.getOrderPayPrice().multiply(sysRule.getConvertRate());
			}
			BigDecimal integral = order.getOrderPayPrice().subtract(money);
			if (0 < money.doubleValue()) {
				shopAccount.setUseMoney(shopAccount.getUseMoney().add(money));
				shopAccount.setTotalMoney(shopAccount.getTotalMoney().add(money));
				// 添加收支流水
				doAddCustomerProfit(shopAccount.getId(), order.getId(), money, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "商城销售分润");
			}
			if (0 < integral.doubleValue()) {
				shopAccount.setCustomerIntegral(shopAccount.getCustomerIntegral().add(integral));
				// 添加收支流水
				doAddCustomerProfit(shopAccount.getId(), order.getId(), integral, Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "商城销售分润");
			}
			cuCustomerAccountDao.save(shopAccount);
			// 获取买家推荐人
			CuCustomerReferee referee = cuCustomerRefereeDao.get(account.getId());
			if (null != referee && !Constant.DEFAULT_REFEREE.equals(referee.getRefereeId())) {
				rewardParent(referee.getId(), referee.getRefereeId(), order.getOrderPayPrice(), Constant.PROFIT_STATUS_2, Constant.STATE_YES);
			}
			// 获取商家推荐人
			/*
			 * CuCustomerReferee shopReferee =
			 * cuCustomerRefereeDao.get(shopAccount.getId()); if (null != shopReferee &&
			 * !Constant.DEFAULT_REFEREE.equals(shopReferee.getRefereeId())) {
			 * rewardParent(shopReferee.getId(), shopReferee.getRefereeId(),
			 * order.getOrderPayPrice(), Constant.PROFIT_STATUS_1, Constant.STATE_YES); }
			 */
		} else {
			CommonSupport.checkNotNull(null, "用户account锁定币错误");
		}
	}

	/**
	 * 商家申请
	 */
	private void doApplyShop(String customerId, BigDecimal num, String obj) {
		MallShop shop = mallShopDao.queryOne(new QueryFilterBuilder().put("customerId", customerId).put("flagDel", Constant.STATE_NO).build());
		if (null != shop && Constant.APPLY_STATUS_3 != shop.getApplyStatus()) {
			CommonSupport.checkNotNull(null, "你的申请在审核中或者已通过");
		}

		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		CommonSupport.checkNotNull(account, "用户account错误， id:" + customerId);
		/*
		 * if (Constant.UN_ACTIVE_NUM > account.getCustomerIntegral().doubleValue()) {
		 * CommonSupport.checkNotNull(null, "您账号暂未激活, 无法申请"); }
		 */
		if (account.getUseMoney().doubleValue() < num.doubleValue()) {
			CommonSupport.checkNotNull(null, "用户余额不足，申请商家失败");
		}
		account.setUseMoney(account.getUseMoney().subtract(num));
		account.setTotalMoney(account.getTotalMoney().subtract(num));
		account.setLockMoney(account.getLockMoney().add(num));
		cuCustomerAccountDao.save(account);
		shop = JSONObject.parseObject(obj, MallShop.class);
		mallShopDao.save(shop);
		doAddCustomerProfit(account.getId(), shop.getId(), num, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_1, "商家申请");
		// CacheUsed.clearAllCache();
	}

	/**
	 * 商家同意
	 */
	private void doApplyAgreeByAdmin(String customerId, String sourceId, String obj) {
		MallShop shop = mallShopDao.get(sourceId);
		CommonSupport.checkNotNull(shop, "商铺为空, id:" + sourceId);
		AppUser user = new AppUser();
		user.setId(shop.getId());
		user.setCode(shop.getShopName());
		AppUser appUser = appUserDao.queryOne(new QueryFilterBuilder().put("code", shop.getShopName()).build());
		if (null != appUser) {
			CommonSupport.checkNotNull(null, "店铺已存在，审核失败");
		}
		// 赋权
		AppRole role = appRoleDao.queryOne(new QueryFilterBuilder().put("code", "SHOP").build());
		CommonSupport.checkNotNull(role, "没有商家角色，暂时无法审核");
		user.setComment("商家");
		user.setState(Constant.STATE_YES);
		user.setName(shop.getShopName());
		user.setPassword(shop.getShopPass());

		appUserDao.save(user);
		AppUserRole userRole = new AppUserRole();
		userRole.setUserId(user.getId());
		userRole.setRoleId(role.getId());
		appUserRoleDao.save(userRole);
		shop.setApplyStatus(Constant.APPLY_STATUS_2);
		shop.setApplyContext(JSONObject.parseObject(obj, String.class));
		MallShop maxShop = mallShopDao.queryOne(new QueryFilterBuilder().orderBy("mallShop.SHOP_NUMBER DESC").build());
		shop.setShopNumber(null == maxShop ? 1000 : maxShop.getShopNumber() + 1);
		mallShopDao.save(shop);
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		CommonSupport.checkNotNull(account, "用户account错误， id:" + customerId);
		if (account.getLockMoney().doubleValue() < shop.getNeedMoney().doubleValue()) {
			CommonSupport.checkNotNull(null, "锁定余额不足, id:" + customerId);
		}
		account.setLockMoney(account.getLockMoney().subtract(shop.getNeedMoney()));
		cuCustomerAccountDao.save(account);
		// 获取买家推荐人
		CuCustomerReferee referee = cuCustomerRefereeDao.get(account.getId());
		if (null != referee && !Constant.DEFAULT_REFEREE.equals(referee.getRefereeId())) {
			rewardParent(referee.getId(), referee.getRefereeId(), shop.getNeedMoney(), Constant.PROFIT_STATUS_2, Constant.STATE_YES);
		}
	}

	/**
	 * 商家拒绝
	 */
	private void doApplyRefuseByAdmin(String customerId, String sourceId, String obj) {
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		CommonSupport.checkNotNull(account, "用户account错误， id:" + customerId);
		MallShop shop = mallShopDao.get(sourceId);
		CommonSupport.checkNotNull(shop, "商铺不存在， id:" + sourceId);
		if (account.getLockMoney().doubleValue() < shop.getNeedMoney().doubleValue()) {
			CommonSupport.checkNotNull(null, "锁定余额不足, id:" + customerId);
		}
		account.setUseMoney(account.getUseMoney().add(shop.getNeedMoney()));
		account.setTotalMoney(account.getTotalMoney().add(shop.getNeedMoney()));
		account.setLockMoney(account.getLockMoney().subtract(shop.getNeedMoney()));
		cuCustomerAccountDao.save(account);
		doAddCustomerProfit(customerId, sourceId, shop.getNeedMoney(), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "商家申请拒绝--退款");
		shop.setApplyStatus(Constant.APPLY_STATUS_3);
		shop.setApplyContext(JSONObject.parseObject(obj, String.class));
		shop.setModifyTime(new Date());
		mallShopDao.save(shop);
	}

	/**
	 * 大转盘抽奖
	 */
	private void doTurntable(String customerId, BigDecimal num, String sourceId) {
		if (num.doubleValue() <= 0) {
			CommonSupport.checkNotNull(null, "金额不合法");
		}
		CuCustomerAccount account = cuCustomerAccountDao.get(customerId);
		CommonSupport.checkNotNull(account, "会员账号错误");
		SysTurntable turntable = sysTurntableDao.get(sourceId);
		CommonSupport.checkNotNull(turntable, "奖品配置获取失败");
		if (account.getUseMoney().doubleValue() < num.doubleValue()) {
			CommonSupport.checkNotNull(null, "金额不足");
		}
		// 添加抽奖记录
		CuDrawReward drawReward = new CuDrawReward();
		drawReward.customerId(customerId).createTime(new Date()).rewardNum(num.intValue());
		cuDrawRewardDao.save(drawReward);
		account.setUseMoney(keepFiveNum(account.getUseMoney().subtract(num)));
		account.setTotalMoney(keepFiveNum(account.getTotalMoney().subtract(num)));
		// 流水
		doAddCustomerProfit(customerId, drawReward.getId(), num, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_1, "MC支出-大转盘");
		if (turntable.getRewardNum().intValue() > 0) {
			// 中奖记录
			SysTurntableReward turntableReward = new SysTurntableReward();
			turntableReward.customerId(customerId).rewardType(turntable.getRewardType()).rewardNum(turntable.getRewardNum()).createTime(new Date());
			sysTurntableRewardDao.save(turntableReward);
			if (Constant.MONEY_TYPE_1 == turntable.getRewardType().intValue()) {
				account.setUseMoney(keepFiveNum(account.getUseMoney().add(new BigDecimal(turntable.getRewardNum()))));
				account.setTotalMoney(keepFiveNum(account.getTotalMoney().add(new BigDecimal(turntable.getRewardNum()))));
				doAddCustomerProfit(customerId, turntableReward.getId(), new BigDecimal(turntable.getRewardNum()), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_1, "MC收入-大转盘中奖");
			}
			if (Constant.MONEY_TYPE_2 == turntable.getRewardType().intValue()) {
				account.setUseCoin(account.getUseCoin().add(new BigDecimal(turntable.getRewardNum())));
				account.setTotalCoin(account.getTotalCoin().add(new BigDecimal(turntable.getRewardNum())));
				doAddCustomerProfit(customerId, turntableReward.getId(), new BigDecimal(turntable.getRewardNum()), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_2, "MNC收入-大转盘中奖");
			}
			if (Constant.MONEY_TYPE_3 == turntable.getRewardType().intValue()) {
				account.setCustomerIntegral(account.getCustomerIntegral().add(new BigDecimal(turntable.getRewardNum())));
				doAddCustomerProfit(customerId, turntableReward.getId(), new BigDecimal(turntable.getRewardNum()), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_3, "MP收入-大转盘中奖");
			}
		}
		cuCustomerAccountDao.save(account);
	}

}
