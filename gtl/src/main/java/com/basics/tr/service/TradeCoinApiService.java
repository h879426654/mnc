package com.basics.tr.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.basics.common.DataItemResponse;
import com.basics.common.DataPageComonResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenIdRequest;
import com.basics.common.TokenPageRequest;
import com.basics.common.TokenRequest;
import com.basics.common.TokenTypeRequest;
import com.basics.tr.controller.request.MatchIngTradeRequest;
import com.basics.tr.controller.request.ModifyCoinTradeRequest;
import com.basics.tr.controller.request.PayMyTradeRequest;
import com.basics.tr.controller.request.PushTradeRequest;
import com.basics.tr.controller.request.SelectTradeListRequest;
import com.basics.tr.controller.response.TradeResponse;

public interface TradeCoinApiService {

	/**
	 * 发布交易信息
	 */
	DataResponse doPushTradeInfo(PushTradeRequest request, HttpServletRequest req);

	/**
	 * 交易大厅查询
	 */
	DataPageComonResponse<TradeResponse> doSelectTradeList(SelectTradeListRequest request, HttpServletRequest req);

	/**
	 * 我的待交易列表(买入/卖出)
	 */
	DataItemResponse<List<TradeResponse>> doSelectMyTrade(TokenTypeRequest request, HttpServletRequest req);

	/**
	 * 匹配交易
	 */
	DataResponse doMatchingTrade(MatchIngTradeRequest request, HttpServletRequest req);

	/**
	 * 我的交易列表(交易中)
	 */
	DataItemResponse<List<TradeResponse>> doSelectMyTrading(TokenRequest request, HttpServletRequest req);

	/**
	 * 撤销
	 */
	DataResponse doCancleMyTradeById(TokenIdRequest request, HttpServletRequest req);

	/**
	 * 上传交易凭证
	 */
	DataResponse doPayMyTrade(PayMyTradeRequest request, HttpServletRequest req);

	/**
	 * 确认
	 */
	DataResponse doConfirmMyTrade(TokenIdRequest request, HttpServletRequest req);

	/**
	 * 交易完成列表
	 */
	DataPageComonResponse<TradeResponse> selectMyTradeList(TokenPageRequest request, HttpServletRequest req);

	/**
	 * 转MNC到交易所
	 */
	DataResponse modifyCoinTrade(ModifyCoinTradeRequest request, HttpServletRequest req) throws Exception;

}
