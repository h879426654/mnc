package com.basics.tr.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.basics.tr.service.TradeCoinApiService;

/**
 * 链交易模块
 * 
 * @author fan
 *
 */
@RestController
@RequestMapping("/api/tradeCoin/")
@Scope("prototype")
public class TradeCoinApiController implements ApplicationContextAware {

	@Autowired
	private TradeCoinApiService tradeCoinApiService;

	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 发布交易信息
	 */
	@RequestMapping("pushTradeInfo")
	public DataResponse pushTradeInfo(@Valid PushTradeRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeCoinApiService.doPushTradeInfo(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 交易大厅查询
	 */
	@RequestMapping("selectTradeList")
	public DataPageComonResponse<TradeResponse> selectTradeList(@Valid SelectTradeListRequest request, BindingResult result, HttpServletRequest req) {
		DataPageComonResponse<TradeResponse> response = new DataPageComonResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeCoinApiService.doSelectTradeList(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 我的待交易列表(买入/卖出)
	 */
	@RequestMapping("selectMyTrade")
	public DataItemResponse<List<TradeResponse>> selectMyTrade(@Valid TokenTypeRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<List<TradeResponse>> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeCoinApiService.doSelectMyTrade(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 匹配交易
	 */
	@RequestMapping("matchingTrade")
	public DataResponse matchingTrade(@Valid MatchIngTradeRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeCoinApiService.doMatchingTrade(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 我的交易列表(交易中)
	 */
	@RequestMapping("selectMyTrading")
	public DataItemResponse<List<TradeResponse>> selectMyTrading(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<List<TradeResponse>> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeCoinApiService.doSelectMyTrading(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 撤销
	 */
	@RequestMapping("cancleMyTradeById")
	public DataResponse cancleMyTradeById(@Valid TokenIdRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeCoinApiService.doCancleMyTradeById(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 上传交易凭证
	 */
	@RequestMapping("payMyTrade")
	public DataResponse payMyTrade(@Valid PayMyTradeRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeCoinApiService.doPayMyTrade(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 确认
	 */
	@RequestMapping("confirmMyTrade")
	public DataResponse confirmMyTrade(@Valid TokenIdRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeCoinApiService.doConfirmMyTrade(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 交易完成列表
	 */
	@RequestMapping("selectMyTradeList")
	public DataPageComonResponse<TradeResponse> selectMyTradeList(@Valid TokenPageRequest request, BindingResult result, HttpServletRequest req) {
		DataPageComonResponse<TradeResponse> response = new DataPageComonResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeCoinApiService.selectMyTradeList(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 转币到交易所
	 */
	@RequestMapping("modifyCoinTrade")
	public DataResponse modifyCoinTrade(@Valid ModifyCoinTradeRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeCoinApiService.modifyCoinTrade(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
}
