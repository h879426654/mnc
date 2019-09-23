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
import com.basics.tr.entity.TrTradeConvert;
import com.basics.tr.service.TrTradeConvertService;

@Controller
@RequestMapping("/backend/tr/trTradeConvert/")
public class TrTradeConvertBackendController extends BaseBackendController<TrTradeConvert> {

	@Autowired
	private TrTradeConvertService trTradeConvertService;
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

	/**
	 * 交易审核
	 */
	@RequestMapping(value = "/save")
	public void save(TrTradeConvert entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			this.beforeSave(result, entity, request);
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			if (Constant.APPLY_STATUS_2 == entity.getApplyStatus().intValue()) {// 审核成功，将交易返回
				result = trTradeConvertService.doApplyTrade(entity);
			} else if (Constant.APPLY_STATUS_3 == entity.getApplyStatus().intValue()) {
				result = trTradeConvertService.doApplyTradeFail(entity); // 审核失败
			} else {
				this.onSave(entity);
				result.onSuccess("操作成功");
			}
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	@RequestMapping("/selectTradeUnApply")
	public void selectTradeUnApply(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.put("applyStatus", Constant.APPLY_STATUS_1).put("tradeStatus", Constant.TRADE_STATUS_2).orderBy("trTradeConvert.CREATE_TIME DESC");
		swgrid(gridPager, builder, trTradeConvertService, "queryTradeInfo", "countTradeResponse", request, response);
	}

	/**
	 * 交易中
	 */
	@RequestMapping("/selectTradeByTrading")
	public void selectTradeByTrading(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.put("applyStatus", Constant.APPLY_STATUS_2).put("tradeIng", Constant.STATE_YES).orderBy("trTradeConvert.APPLY_TIME DESC");
		swgrid(gridPager, builder, trTradeConvertService, "queryTradeInfo", "countTradeResponse", request, response);
	}

	/**
	 * 交易记录
	 */
	@RequestMapping("/selectTradeRecord")
	public void selectTradeRecord(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.put("applyStatus", Constant.APPLY_STATUS_2).put("GTtradeStatus", Constant.TRADE_STATUS_4).orderBy("trTradeConvert.TRADE_FINISH_TIME DESC");
		swgrid(gridPager, builder, trTradeConvertService, "queryTradeInfo", "countTradeResponse", request, response);
	}

	/**
	 * 完成交易
	 */
	@RequestMapping("finishTrade")
	@ResponseBody
	public void finishTrade(TrTradeConvert entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			result = baseAccountApiService.doFinishTradeConvert(entity);
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
	public void cancelTrade(TrTradeConvert entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			result = baseAccountApiService.doCancleTradeConvert(entity);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

}
