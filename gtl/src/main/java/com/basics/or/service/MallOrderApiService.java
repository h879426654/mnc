package com.basics.or.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.basics.common.DataItemResponse;
import com.basics.common.DataPageComonResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenIdRequest;
import com.basics.common.TokenIdsRequest;
import com.basics.common.TokenTypePageRequest;
import com.basics.mall.controller.request.CreateOrderInfoRequest;
import com.basics.mall.controller.request.CustomerCarRequest;
import com.basics.mall.controller.response.CustomerCarResponse;
import com.basics.mall.controller.response.MallOrderResponse;
import com.basics.or.controller.request.PayOrderRequest;

import javax.servlet.http.HttpServletRequest;

public interface MallOrderApiService {

	//构建订单号
	DataItemResponse<List<CustomerCarResponse>> doBuildOrderInfo(CustomerCarRequest request, HttpServletRequest req);

	//构建订单号(购物车)
	DataItemResponse<List<CustomerCarResponse>> doBuildOrderInfoByCars(TokenIdsRequest request, HttpServletRequest req);

	//创建订单号
	DataItemResponse<List<String>> doCreateOrderInfo(CreateOrderInfoRequest request, HttpServletRequest req);

	//取消订单
	DataResponse doCancelOrderInfo(TokenIdRequest request, HttpServletRequest req);

	//删除订单
	DataResponse doDelOrderInfo(TokenIdRequest request, HttpServletRequest req);

	//查询订单
	DataPageComonResponse<MallOrderResponse> doSelectMyOrder(TokenTypePageRequest request, HttpServletRequest req);

	//确认收货
	DataResponse doConfirmOrder(PayOrderRequest request, HttpServletRequest req);
	
	//根据订单获取物流信息
	DataItemResponse<JSONObject> doSelectLogisticsInfo(TokenIdRequest request, HttpServletRequest req);

	/**
	 * 支付订单
	 */
	DataResponse doPayMyOrder(PayOrderRequest request, HttpServletRequest req);

}
