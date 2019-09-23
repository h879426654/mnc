package com.basics.job;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.basics.common.BaseApiService;
import com.basics.common.Constant;
import com.basics.support.CommonSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilterBuilder;
import com.basics.sys.entity.SysRule;
import com.basics.tr.entity.TrTradeMoney;

/**
 * 规定时间内未完成交易的冻结账号
 * @author fan
 *
 */
@Component
public class EveryHourFrozenTradeJob extends BaseApiService {// implements EveryHourJob

	// @Override
	public void doJob() {
		LogUtils.performance.info("{} begin at {}", this.getClass().getName(), new Date());
		// 交易规则
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		CommonSupport.checkNotNull(rule, "交易规则不存在");
		List<TrTradeMoney> moneyTrade = trTradeMoneyDao.query(new QueryFilterBuilder().put("applyStatus", Constant.APPLY_STATUS_2).put("tradeStatus", Constant.TRADE_STATUS_2).put("tradeMatchingTimeOver", rule.getTradeTimeOut()).build());
		if (CollectionUtils.isNotEmpty(moneyTrade)) {
			createActiveMq(null, Constant.ACTIVEMQ_TYPE_36, BigDecimal.ZERO, null, "规定时间未付款-取消交易", null);
		}
		LogUtils.performance.info("{} end at {}", this.getClass().getName(), new Date());
	}

}
