package com.basics.tr.service;

import java.util.List;

import com.basics.common.DataItemResponse;
import com.basics.common.DataPageComonResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenIdRequest;
import com.basics.common.TokenPageRequest;
import com.basics.common.TokenRequest;
import com.basics.common.TokenTypePageRequest;
import com.basics.cu.entity.CuAccountConvert;
import com.basics.cu.entity.CuCustomerProfit;
import com.basics.tr.controller.request.CustomerProfitRequest;
import com.basics.tr.controller.request.MoneyToIntegralRequest;
import com.basics.tr.controller.request.PayMyTradeRequest;
import com.basics.tr.controller.request.TransferAccountsRequest;
import com.basics.tr.controller.response.IndexDataResponse;
import com.basics.tr.controller.response.TradeResponse;

import javax.servlet.http.HttpServletRequest;

public interface TradeConvertApiService {

	/**
	 * 交易顶部数据
	 */
	DataItemResponse<IndexDataResponse> selectIndexData(TokenRequest request, HttpServletRequest req);

	/**
	 * 余额转积分
	 */
	DataResponse doMoneyToIntegral(MoneyToIntegralRequest request, HttpServletRequest req);

	/**
	 * 余额转链
	 */
	DataResponse doMoneyToCoin(MoneyToIntegralRequest request, HttpServletRequest req);

	/**
	 * 链转余额
	 */
	DataResponse doCoinToMoney(MoneyToIntegralRequest request, HttpServletRequest req);

	/**
	 * 转化记录
	 */
	DataPageComonResponse<CuAccountConvert> selectAccountConvert(TokenTypePageRequest request, HttpServletRequest req);

	/**
	 * 收支流水
	 */
	DataPageComonResponse<CuCustomerProfit> selectCustomerProfit(CustomerProfitRequest request, HttpServletRequest req);

	/**
	 * 匹配余额转账
	 */
	DataResponse doMatchTransferAccount(TransferAccountsRequest request, HttpServletRequest req);

	/**
	 * 转帐中
	 */
	DataItemResponse<List<TradeResponse>> doSelectConvertTrading(TokenRequest request, HttpServletRequest req);

	/**
	 * 上传凭证
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
	 * 撤销
	 */
	DataResponse doCancleMyTradeById(TokenIdRequest request, HttpServletRequest req);

	/**
	 * 二维码收付款
	 */
	DataResponse doTradeByQRCode(TransferAccountsRequest request, HttpServletRequest req);

}
