package com.basics.cu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basics.app.shiro.AppUserUtils;
import com.basics.app.shiro.UserSupport;
import com.basics.common.BaseApiService;
import com.basics.common.Constant;
import com.basics.cu.entity.CuCustomerAccount;
import com.basics.cu.entity.CuCustomerCount;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.cu.entity.CuCustomerReferee;
import com.basics.cu.service.BaseAccountApiService;
import com.basics.mall.entity.MallShop;
import com.basics.or.entity.OrOrder;
import com.basics.support.CommonSupport;
import com.basics.support.FormResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.ServletUtils;
import com.basics.support.dlshouwen.Pager;
import com.basics.sys.entity.SysCustomerLevel;
import com.basics.tr.entity.TrTradeCoin;
import com.basics.tr.entity.TrTradeConvert;
import com.basics.tr.entity.TrTradeMoney;

@Service
@Transactional
public class BaseAccountApiImplService extends BaseApiService implements BaseAccountApiService {

	/**
	 * 发放/扣除余额 flagReward true发放 flase扣除
	 */
	@Override
	public FormResultSupport doUpdateMoneyByAdmin(CuCustomerAccount entity, boolean flagReward) {
		FormResultSupport result = new FormResultSupport();
		CuCustomerAccount account = cuCustomerAccountDao.get(entity.getId());
		CommonSupport.checkNotNull(account, "用户account错误，id:" + entity.getId());
		UserSupport support = AppUserUtils.getCurrentUserSupport();
		if (entity.getUseMoney().doubleValue() <= 0) {
			result.onException("数量不合法");
			return result;
		}
		if (flagReward) {
			createActiveMq(entity.getId(), Constant.ACTIVEMQ_TYPE_1, entity.getUseMoney(), support.getId(), "余额发放", null);
		} else {
			if (account.getUseMoney().doubleValue() >= entity.getUseMoney().doubleValue()) {
				createActiveMq(entity.getId(), Constant.ACTIVEMQ_TYPE_2, entity.getUseMoney(), support.getId(), "余额扣除", null);
			} else {
				result.onException("余额不足，无法扣除");
				return result;
			}
		}
		result.onSuccess("操作成功");
		return result;
	}

	/**
	 * 发放/扣除积分 flagReward true发放 flase扣除
	 */
	@Override
	public FormResultSupport doUpdateIntegralByAdmin(CuCustomerAccount entity, boolean flagReward) {
		FormResultSupport result = new FormResultSupport();
		CuCustomerAccount account = cuCustomerAccountDao.get(entity.getId());
		CommonSupport.checkNotNull(account, "用户account错误，id:" + entity.getId());
		UserSupport support = AppUserUtils.getCurrentUserSupport();
		if (entity.getCustomerIntegral().doubleValue() <= 0) {
			result.onException("数量错误");
			return result;
		}
		if (flagReward) {
			createActiveMq(entity.getId(), Constant.ACTIVEMQ_TYPE_3, entity.getCustomerIntegral(), support.getId(), "积分发放", null);
		} else {
			if (account.getCustomerIntegral().doubleValue() >= entity.getCustomerIntegral().doubleValue()) {
				createActiveMq(entity.getId(), Constant.ACTIVEMQ_TYPE_4, entity.getCustomerIntegral(), support.getId(), "积分扣除", null);
			} else {
				result.onException("积分不足，无法扣除");
				return result;
			}
		}
		result.onSuccess("操作成功");
		return result;
	}

	/**
	 * 发放/扣除链 flagReward true发放 flase扣除
	 */
	@Override
	public FormResultSupport doUpdateCoinByAdmin(CuCustomerAccount entity, boolean flagReward) {
		FormResultSupport result = new FormResultSupport();
		if (entity.getUseCoin().doubleValue() <= 0) {
			result.onException("数量不合法");
			return result;
		}
		CuCustomerAccount account = cuCustomerAccountDao.get(entity.getId());
		CommonSupport.checkNotNull(account, "用户account错误，id:" + entity.getId());
		UserSupport support = AppUserUtils.getCurrentUserSupport();
		if (flagReward) {
			createActiveMq(entity.getId(), Constant.ACTIVEMQ_TYPE_5, entity.getUseCoin(), support.getId(), "链发放", null);
		} else {
			if (account.getUseCoin().doubleValue() >= entity.getUseCoin().doubleValue()) {
				createActiveMq(entity.getId(), Constant.ACTIVEMQ_TYPE_6, entity.getUseCoin(), support.getId(), "链扣除", null);
			} else {
				result.onException("链不足，无法扣除");
				return result;
			}
		}
		result.onSuccess("操作成功");
		return result;
	}

	/**
	 * 修改推荐人
	 */
	@Override
	public FormResultSupport dealChangeReferee(CuCustomerInfo entity) {
		FormResultSupport result = new FormResultSupport();
		if (StringUtils.isBlank(entity.getCustomerPhone())) {
			result.onException("推荐人手机不能为空");
			return result;
		}
		if (StringUtils.isBlank(entity.getId())) {
			result.onException("会员信息获取失败");
			return result;
		}
		// 根据手机号获取推荐人
		CuCustomerInfo parentUser = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("customerPhone", entity.getCustomerPhone()).build());
		CommonSupport.checkNotNull(parentUser, "推荐人信息获取失败");
		if (checkWithTeam(entity.getId(), parentUser.getId())) {
			result.onException("推荐人属于该会员团队，无法进行当前操作");
			return result;
		}
		CuCustomerCount count = cuCustomerCountDao.get(entity.getId());
		CommonSupport.checkNotNull(count, "会员统计失败");
		// 减去当前推荐人的团队人数
		CuCustomerReferee referee = cuCustomerRefereeDao.get(entity.getId());
		CommonSupport.checkNotNull(referee, "无法获取该账户的推荐人");
		doUpdateTeamNumByReferee(referee.getRefereeId(), count.getTeamNum(), true, false);
		// 修改其推荐人
		referee.setRefereeId(parentUser.getId());
		cuCustomerRefereeDao.save(referee);
		// 增加新推荐的团队人数
		doUpdateTeamNumByReferee(parentUser.getId(), count.getTeamNum(), true, true);
		result.onSuccess("操作成功");
		return result;
	}

	@Override
	public FormResultSupport doCancleTradeCoin(TrTradeCoin entity) {
		FormResultSupport result = new FormResultSupport();
		TrTradeCoin trade = trTradeCoinDao.queryOne(new QueryFilterBuilder().put("id", entity.getId()).put("LTtradeStatus", Constant.TRADE_STATUS_5).build());
		CommonSupport.checkNotNull(trade, "交易不存在或已经取消");
		createActiveMq(AppUserUtils.getCurrentUserSupport().getId(), Constant.ACTIVEMQ_TYPE_26, BigDecimal.ZERO, entity.getId(), "后台取消链交易", null);
		result.onSuccess("操作成功");
		return result;
	}

	@Override
	public FormResultSupport doFinishTradeCoin(TrTradeCoin entity) {
		FormResultSupport result = new FormResultSupport();
		TrTradeCoin trade = trTradeCoinDao.queryOne(new QueryFilterBuilder().put("id", entity.getId()).put("tradeIng", "tradeIng").build());
		CommonSupport.checkNotNull(trade, "交易不存在或已完成");
		createActiveMq(AppUserUtils.getCurrentUserSupport().getId(), Constant.ACTIVEMQ_TYPE_25, BigDecimal.ZERO, trade.getId(), "后台完成链交易", null);
		result.onSuccess("操作成功");
		return result;
	}

	@Override
	public FormResultSupport doCancelTradeMoney(TrTradeMoney entity) {
		FormResultSupport result = new FormResultSupport();
		TrTradeMoney trade = trTradeMoneyDao.queryOne(new QueryFilterBuilder().put("id", entity.getId()).put("LTtradeStatus", Constant.TRADE_STATUS_5).build());
		CommonSupport.checkNotNull(trade, "交易不存在或已取消");
		createActiveMq(AppUserUtils.getCurrentUserSupport().getId(), Constant.ACTIVEMQ_TYPE_22, BigDecimal.ZERO, trade.getId(), "后台取消余额交易", null);
		result.onSuccess("操作成功");
		return result;
	}

	@Override
	public FormResultSupport doFinishTradeMoney(TrTradeMoney entity) {
		FormResultSupport result = new FormResultSupport();
		TrTradeMoney trade = trTradeMoneyDao.queryOne(new QueryFilterBuilder().put("id", entity.getId()).put("tradeIng", "tradeIng").build());
		CommonSupport.checkNotNull(trade, "交易不存在或已取消");
		createActiveMq(AppUserUtils.getCurrentUserSupport().getId(), Constant.ACTIVEMQ_TYPE_21, BigDecimal.ZERO, trade.getId(), "后台完成余额交易", null);
		result.onSuccess("操作成功");
		return result;
	}

	@Override
	public FormResultSupport doCancleTradeConvert(TrTradeConvert entity) {
		FormResultSupport result = new FormResultSupport();
		TrTradeConvert trade = trTradeConvertDao.queryOne(new QueryFilterBuilder().put("id", entity.getId()).put("tradeIng", "tradeIng").build());
		CommonSupport.checkNotNull(trade, "交易不存在或已经取消");
		createActiveMq(AppUserUtils.getCurrentUserSupport().getId(), Constant.ACTIVEMQ_TYPE_24, BigDecimal.ZERO, trade.getId(), "后台取消定向交易", null);
		result.onSuccess("操作成功");
		return result;
	}

	@Override
	public FormResultSupport doFinishTradeConvert(TrTradeConvert entity) {
		FormResultSupport result = new FormResultSupport();
		TrTradeConvert trade = trTradeConvertDao.queryOne(new QueryFilterBuilder().put("id", entity.getId()).put("tradeIng", "tradeIng").build());
		CommonSupport.checkNotNull(trade, "交易不存在或当前状态无法确认");
		createActiveMq(AppUserUtils.getCurrentUserSupport().getId(), Constant.ACTIVEMQ_TYPE_23, BigDecimal.ZERO, trade.getId(), "后台完成定向交易", null);
		result.onSuccess("操作成功");
		return result;
	}

	/**
	 * 商家审核通过
	 */
	@Override
	public FormResultSupport doApplyShop(MallShop entity) {
		FormResultSupport result = new FormResultSupport();
		MallShop shop = mallShopDao.get(entity.getId());
		if (Constant.APPLY_STATUS_1 != shop.getApplyStatus().doubleValue()) {
			result.onException("已审核通过或拒绝了");
			return result;
		}
		createActiveMq(shop.getCustomerId(), Constant.ACTIVEMQ_TYPE_31, BigDecimal.ZERO, shop.getId(), "商家申请通过", entity.getApplyContext());
		result.onSuccess("操作成功");
		return result;
	}

	/**
	 * 商家审核失败
	 */
	@Override
	public FormResultSupport doApplyShopByRefuse(MallShop entity) {
		FormResultSupport result = new FormResultSupport();
		MallShop shop = mallShopDao.get(entity.getId());
		if (Constant.APPLY_STATUS_1 != shop.getApplyStatus().doubleValue()) {
			result.onException("已审核通过或拒绝了");
			return result;
		}
		createActiveMq(shop.getCustomerId(), Constant.ACTIVEMQ_TYPE_32, BigDecimal.ZERO, shop.getId(), "商家申请拒绝", entity.getApplyContext());
		result.onSuccess("操作成功");
		return result;
	}

	/**
	 * 取消订单
	 */
	@Override
	public FormResultSupport cancelOrder(OrOrder entity) {
		FormResultSupport result = new FormResultSupport();
		OrOrder order = orOrderDao.get(entity.getId());
		CommonSupport.checkNotNull(order, "订单不存在");
		if (Constant.ORDER_STATUS_1 == order.getOrderStatus() || Constant.ORDER_STATUS_2 == order.getOrderStatus() || Constant.ORDER_STATUS_3 == order.getOrderStatus()) {
			createActiveMq(entity.getModifyUser(), Constant.ACTIVEMQ_TYPE_34, BigDecimal.ZERO, order.getId(), "商家取消订单", null);
			result.onSuccess("操作成功");
		} else {
			result.onException("当前状态无法取消订单");
		}
		return result;
	}

	/**
	 * 更新会员等级
	 */
	@Override
	public void updateCustomerLevel() {
		/*List<CuCustomerAccount> list = cuCustomerAccountDao.query(new QueryFilterBuilder().build());
		for (CuCustomerAccount account : list) {
			checkCustomerLevel(account);
		}*/

	}

	/**
	 * 删除用户
	 */
	@Override
	public FormResultSupport doDeleteCustomer(CuCustomerInfo entity) {
		FormResultSupport result = new FormResultSupport();
		CuCustomerCount count = cuCustomerCountDao.get(entity.getId());
		CuCustomerReferee referee = cuCustomerRefereeDao.get(count.getId());
		if (null != referee && !"0".equals(referee.getRefereeId())) {
			//更新人数
			doUpdateTeamNum(referee.getRefereeId(), count.getTeamNum(), true, false);
			//删除该节点以下的用户
			deleteCustomers(count.getId());
			result.onSuccess("删除成功");
		} else {
			result.onException("该节点为根节点,无法删除");
		}

		return result;
	}

	/**
	 * 系统统计
	 */
	@Override
	public void countSystemData(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		//平台总币量
		CuCustomerAccount account = cuCustomerAccountDao.getExtend(map, "sumTerraceAccount");
		if (null != account) {
			data.put("totalMoney", account.getUseMoney());
			data.put("totalCoin", account.getUseCoin());
			data.put("totalIntegral", account.getCustomerIntegral());
		}

		List<Map<String, Object>> list = new ArrayList<>();
		list.add(data);

		Pager pager = new Pager();
		try {
			pager.setRecordCount(1);
			pager.setPageCount(1);
			pager.setNowPage(1);
			pager.setStartRecord(1);
			pager.setExhibitDatas(list);
			pager.setIsSuccess(true);
		} catch (Throwable e) {
			LogUtils.performance.error("swgrid:gridPager:{} exception:{}", gridPager, ExceptionUtils.getFullStackTrace(e));
			pager.setIsSuccess(false);
		}
		ServletUtils.renderJsonAsText(response, pager);

	}

	/**
	 * 恢复等级
	 */
	@Override
	public String recoveryLevel(String id) {
		CuCustomerAccount account = cuCustomerAccountDao.get(id);
		SysCustomerLevel level = sysCustomerLevelDao.queryOne(new QueryFilterBuilder().put("tallyWithLevel", account.getCustomerIntegral()).build());
		CuCustomerCount count = cuCustomerCountDao.get(account.getId());
		if (null != level) {
			if (!count.getCustomerLevelId().equals(level.getId())) {
				count.setCustomerLevelId(level.getId());
			}
		}
		count.setFlagLevelAuto(Constant.STATE_YES);
		cuCustomerCountDao.save(count);
		return "操作成功";
	}

	/**
	 * 交易审核
	 */
	@Override
	public FormResultSupport doApplyMoneyTrade(TrTradeMoney entity) {
		FormResultSupport result = new FormResultSupport();

		TrTradeMoney trade = trTradeMoneyDao.get(entity.getId());
		if (Constant.APPLY_STATUS_1 != trade.getApplyStatus()) {
			result.onException("该交易已审核");
			return result;
		}
		if (Constant.TRADE_STATUS_1 != trade.getTradeStatus()) {
			result.onException("该交易已取消");
			return result;
		}
		if (Constant.APPLY_STATUS_2 == entity.getApplyStatus()) {
			trade.setApplyStatus(Constant.APPLY_STATUS_2);
			trade.setApplyContext(entity.getApplyContext());
			trade.setApplyTime(new Date());
			trTradeMoneyDao.save(trade);
		} else {
			createActiveMq(trade.getCustomerId(), Constant.ACTIVEMQ_TYPE_37, BigDecimal.ZERO, trade.getId(), "MC交易审核不通过", entity);
		}
		result.onSuccess("操作成功");
		return result;
	}

	@Override
	public FormResultSupport doApplyCoinTrade(TrTradeCoin entity) {
		FormResultSupport result = new FormResultSupport();
		TrTradeCoin trade = trTradeCoinDao.get(entity.getId());
		if (Constant.APPLY_STATUS_1 != trade.getApplyStatus()) {
			result.onException("该交易已审核");
		}
		if (Constant.APPLY_STATUS_2 == entity.getApplyStatus()) {
			trade.setApplyStatus(Constant.APPLY_STATUS_2);
			trade.setApplyContext(entity.getApplyContext());
			trade.setApplyTime(new Date());
			trTradeCoinDao.save(trade);
		} else {
			createActiveMq(trade.getCustomerId(), Constant.ACTIVEMQ_TYPE_38, BigDecimal.ZERO, trade.getId(), "MNC交易审核不通过", entity);
		}
		result.onSuccess("操作成功");
		return result;
	}

	@Override
	public FormResultSupport doUpdateTradeCoinByAdmin(CuCustomerAccount entity, boolean flagReward) {
		FormResultSupport result = new FormResultSupport();
		CuCustomerAccount account = cuCustomerAccountDao.get(entity.getId());
		CommonSupport.checkNotNull(account, "用户account错误，id:" + entity.getId());
		UserSupport support = AppUserUtils.getCurrentUserSupport();
		if (entity.getTradeCoin().doubleValue() <= 0) {
			result.onException("数量不合法");
			return result;
		}
		if (flagReward) {
			createActiveMq(entity.getId(), Constant.ACTIVEMQ_TYPE_40, entity.getTradeCoin(), support.getId(), "交易链发放", null);
		} else {
			if (account.getTradeCoin().doubleValue() >= entity.getTradeCoin().doubleValue()) {
				createActiveMq(entity.getId(), Constant.ACTIVEMQ_TYPE_41, entity.getTradeCoin(), support.getId(), "交易链扣除", null);
			} else {
				result.onException("交易链不足，无法扣除");
				return result;
			}
		}
		result.onSuccess("操作成功");
		return result;
	}

	@Override
	public FormResultSupport doGranReleaseRate(CuCustomerAccount entity) {
		FormResultSupport result = new FormResultSupport();
		CuCustomerAccount account = cuCustomerAccountDao.get(entity.getId());
		CommonSupport.checkNotNull(account, "用户account错误，id:" + entity.getId());
		if (entity.getTradeCoin().doubleValue() < 0) {
			result.onException("数量不合法");
			return result;
		}
		account.setTradeCoin(entity.getTradeCoin());
		cuCustomerAccountDao.save(account);
		result.onSuccess("操作成功");
		return result;
	}
}
