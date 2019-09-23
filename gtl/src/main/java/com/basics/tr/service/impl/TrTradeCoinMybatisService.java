package com.basics.tr.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import com.basics.support.CommonSupport;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basics.app.shiro.AppUserUtils;
import com.basics.common.Constant;
import com.basics.cu.dao.CuCustomerAccountDao;
import com.basics.cu.dao.CuCustomerProfitDao;
import com.basics.cu.entity.CuCustomerAccount;
import com.basics.cu.entity.CuCustomerProfit;
import com.basics.support.FormResultSupport;
import com.basics.support.GenericMybatisService;
import com.basics.support.QueryFilterBuilder;
import com.basics.tr.dao.TrTradeCoinDao;
import com.basics.tr.entity.TrTradeCoin;
import com.basics.tr.service.TrTradeCoinService;

@Service
@Transactional
public class TrTradeCoinMybatisService extends GenericMybatisService<TrTradeCoin> implements TrTradeCoinService {

	@Autowired
	private TrTradeCoinDao trTradeCoinDao;
	@Autowired
	private CuCustomerProfitDao cuCustomerProfitDao;
	@Autowired
	private CuCustomerAccountDao cuCustomerAccountDao;

	/**
	 * 通过
	 */
	@Override
	public FormResultSupport doApplyTrade(TrTradeCoin entity) {
		FormResultSupport result = new FormResultSupport();
		// 判断当前交易是否为待审核状态
		TrTradeCoin info = trTradeCoinDao.queryOne(new QueryFilterBuilder().put("id", entity.getId()).put("applyStatus", Constant.APPLY_STATUS_1).build());
		if (null != info) {
			info.setApplyStatus(Constant.APPLY_STATUS_2);
			info.setApplyContext(entity.getApplyContext());
			info.setApplyTime(new Date());
			info.setModifyUser(AppUserUtils.getCurrentUserSupport().getId());
			trTradeCoinDao.save(info);
			result.onSuccess("操作成功");
		} else {
			result.onException("该交易不存在或已审核");
		}
		return result;
	}

	/**
	 * 不通过
	 */
	@Override
	public FormResultSupport doApplyTradeFail(TrTradeCoin entity) {
		FormResultSupport result = new FormResultSupport();
		// 判断当前交易是否为待审核状态
		TrTradeCoin trade = trTradeCoinDao.queryOne(new QueryFilterBuilder().put("id", entity.getId()).put("applyStatus", Constant.APPLY_STATUS_1).build());
		if (null != trade) {
			// 解冻出售方货币
			CuCustomerAccount account = cuCustomerAccountDao.get(trade.getCustomerId());
			if (null != account) {
				if(account.getLockCoin().doubleValue() < trade.getLockMoneyNum().doubleValue()) {
					CommonSupport.checkNotNull(null, "金额错误");
				}
				account.setUseCoin(account.getUseCoin().add(trade.getLockMoneyNum()));
				account.setLockCoin(account.getLockCoin().subtract(trade.getLockMoneyNum()));
				account.setTotalCoin(account.getTotalCoin().add(trade.getLockMoneyNum()));
				cuCustomerAccountDao.save(account);
				// 流水
				doAddCustomerProfit(account.getId(), trade.getId(), trade.getLockMoneyNum(), Constant.PROFIT_STATUS_1, Constant.MONEY_TYPE_2, "交易审核不通过");
			}
			trade.setApplyStatus(Constant.APPLY_STATUS_3);
			trade.setTradeStatus(Constant.TRADE_STATUS_5);
			trade.setTradeFinishTime(new Date());
			trTradeCoinDao.save(trade);
			result.onSuccess("操作成功");
		} else {
			result.onException("该交易不存在或已审核");
		}
		return result;
	}

	/**
	 * 增加收益记录
	 */
	private void doAddCustomerProfit(String customerId, String profitSource, BigDecimal rate, int earningStatus, int earningsType, String profitRemark) {
		CuCustomerProfit profit = new CuCustomerProfit();
		profit.setCustomerId(customerId);
		if (StringUtils.isNotBlank(profitSource)) {
			profit.setProfitSource(profitSource);
		}
		profit.setProfitNum(rate);
		profit.setProfitHavedTime(new Date());
		profit.setProfitStatus(earningStatus);
		profit.setProfitType(earningsType);
		profit.setCreateTime(new Date());
		profit.setProfitRemark(profitRemark);
		cuCustomerProfitDao.save(profit);
	}

}
