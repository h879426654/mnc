package com.basics.or.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.basics.or.service.MallOrderApiService;
@RestController
@RequestMapping("/api/order/")
public class OrderApiController implements ApplicationContextAware {

	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;
	@Autowired
	private MallOrderApiService mallOrderApiService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	/**
	 * 构建订单号
	 */
	@RequestMapping("buildOrderInfo")
	public DataItemResponse<List<CustomerCarResponse>> buildOrderInfo(@Valid CustomerCarRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<List<CustomerCarResponse>> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallOrderApiService.doBuildOrderInfo(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 构建订单号(购物车)
	 */
	@RequestMapping("buildOrderInfoByCars")
	public DataItemResponse<List<CustomerCarResponse>> buildOrderInfoByCars(@Valid TokenIdsRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<List<CustomerCarResponse>> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallOrderApiService.doBuildOrderInfoByCars(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 创建订单
	 */
	@RequestMapping("createOrderInfo")
	public DataItemResponse<List<String>> createOrderInfo(@Valid CreateOrderInfoRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<List<String>> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallOrderApiService.doCreateOrderInfo(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 取消订单
	 */
	@RequestMapping("cancelOrderInfo")
	public DataResponse cancelOrderInfo(@Valid TokenIdRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallOrderApiService.doCancelOrderInfo(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 删除订单
	 */
	@RequestMapping("delOrderInfo")
	public DataResponse delOrderInfo(@Valid TokenIdRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallOrderApiService.doDelOrderInfo(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 查询订单
	 */
	@RequestMapping("selectMyOrder")
	public DataPageComonResponse<MallOrderResponse> selectMyOrder(@Valid TokenTypePageRequest request, BindingResult result, HttpServletRequest req) {
		DataPageComonResponse<MallOrderResponse> response = new DataPageComonResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallOrderApiService.doSelectMyOrder(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 支付订单
	 */
	@RequestMapping("payMyOrder")
	public DataResponse payMyOrder(@Valid PayOrderRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallOrderApiService.doPayMyOrder(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 确认收货
	 */
	@RequestMapping("confirmOrder")
	public DataResponse confirmOrder(@Valid PayOrderRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallOrderApiService.doConfirmOrder(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 根据订单获取物流信息
	 */
	@RequestMapping("selectLogisticsInfo")
	public DataItemResponse<JSONObject> selectLogisticsInfo(@Valid TokenIdRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<JSONObject> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallOrderApiService.doSelectLogisticsInfo(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
}
