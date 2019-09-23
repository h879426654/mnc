package com.basics.tr.controller.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basics.common.Constant;
import com.basics.cu.service.BaseAccountApiService;
import com.basics.support.FormResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.ServletUtils;
import com.basics.tr.entity.TrTradeCoin;
import com.basics.tr.service.TrTradeCoinService;

@Controller
@RequestMapping("/backend/tr/trTradeCoin/")
public class TrTradeCoinBackendController extends BaseBackendController<TrTradeCoin> {

	@Autowired
	private TrTradeCoinService trTradeCoinService;
	@Autowired
	private BaseAccountApiService baseAccountApiService;

	@RequestMapping("trading")
	public String tradingView() {
		return getView("trading");
	}

	@RequestMapping("record")
	public String recordView() {
		return getView("record");
	}

	@RequestMapping("notTrading")
	public String notTradingView() {
		return getView("notTrading");
	}

	/**
	 * 交易审核
	 */
	@RequestMapping(value = "/save")
	public void save(TrTradeCoin entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			this.beforeSave(result, entity, request);
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			result = baseAccountApiService.doApplyCoinTrade(entity);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	@RequestMapping("/selectTradeUnApply")
	public void selectTradeUnApply(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.put("applyStatus", Constant.APPLY_STATUS_1).put("tradeStatus", Constant.TRADE_STATUS_1).orderBy("trTradeCoin.TRADE_MATCH_TIME DESC");
		swgrid(gridPager, builder, trTradeCoinService, "queryTradeInfo", "countTradeResponse", request, response);
	}

	@RequestMapping("/selectTradeUnTrading")
	public void selectTradeUnTrading(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.put("applyStatus", Constant.APPLY_STATUS_2).put("tradeStatus", Constant.TRADE_STATUS_1).orderBy("trTradeCoin.CREATE_TIME DESC");
		swgrid(gridPager, builder, trTradeCoinService, "queryTradeResponse", "countTradeResponse", request, response);
	}

	/**
	 * 交易中
	 */
	@RequestMapping("/selectTradeByTrading")
	public void selectTradeByTrading(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.put("applyStatus", Constant.APPLY_STATUS_2).put("tradeIng", Constant.STATE_YES).orderBy("trTradeCoin.TRADE_MATCH_TIME DESC");
		swgrid(gridPager, builder, trTradeCoinService, "queryTradeInfo", "countTradeResponse", request, response);
	}

	/**
	 * 交易记录
	 */
	@RequestMapping("/selectTradeRecord")
	public void selectTradeRecord(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.put("applyStatus", Constant.APPLY_STATUS_2).put("GTtradeStatus", Constant.TRADE_STATUS_4).orderBy("trTradeCoin.TRADE_MATCH_TIME DESC");
		swgrid(gridPager, builder, trTradeCoinService, "queryTradeInfo", "countTradeResponse", request, response);
	}

	/**
	 * 完成交易
	 */
	@RequestMapping("finishTrade")
	@ResponseBody
	public void finishTrade(TrTradeCoin entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			result = baseAccountApiService.doFinishTradeCoin(entity);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 取消交易
	 */
	@RequestMapping("cancelTrade")
	@ResponseBody
	public void cancelTrade(TrTradeCoin entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			result = baseAccountApiService.doCancleTradeCoin(entity);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

}
