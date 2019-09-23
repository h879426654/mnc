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
import com.basics.common.TokenTypePageRequest;
import com.basics.cu.entity.CuAccountConvert;
import com.basics.cu.entity.CuCustomerProfit;
import com.basics.tr.controller.request.CustomerProfitRequest;
import com.basics.tr.controller.request.MoneyToIntegralRequest;
import com.basics.tr.controller.request.PayMyTradeRequest;
import com.basics.tr.controller.request.TransferAccountsRequest;
import com.basics.tr.controller.response.IndexDataResponse;
import com.basics.tr.controller.response.TradeResponse;
import com.basics.tr.service.TradeConvertApiService;

@RestController
@RequestMapping("/api/convert/")
@Scope("prototype")
public class TradeConvertApiController implements ApplicationContextAware {

	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;

	@Autowired
	private TradeConvertApiService tradeConvertService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 交易顶部数据
	 */
	@RequestMapping("selectIndexData")
	public DataItemResponse<IndexDataResponse> selectIndexData(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<IndexDataResponse> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeConvertService.selectIndexData(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 匹配转账
	 */
	@RequestMapping("matchTransferAccount")
	public DataResponse matchTransferAccount(@Valid TransferAccountsRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeConvertService.doMatchTransferAccount(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 转帐中
	 */
	@RequestMapping("selectConvertTrading")
	public DataItemResponse<List<TradeResponse>> selectConvertTrading(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<List<TradeResponse>> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeConvertService.doSelectConvertTrading(request, req);
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
			response = tradeConvertService.doPayMyTrade(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 确认
	 */
	//@RequestMapping("confirmMyTrade")
	public DataResponse confirmMyTrade(@Valid TokenIdRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeConvertService.doConfirmMyTrade(request, req);
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
			response = tradeConvertService.selectMyTradeList(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 余额转积分
	 */
	@RequestMapping("doMoneryToIntegral")
	public DataResponse doMoneyToIntegral(@Valid MoneyToIntegralRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeConvertService.doMoneyToIntegral(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 余额转链
	 */
	@RequestMapping("doMoneyToCoin")
	public DataResponse doMoneyToCoin(@Valid MoneyToIntegralRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeConvertService.doMoneyToCoin(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 链转余额
	 */
	@RequestMapping("doCoinToMoney")
	public DataResponse doCoinToMoney(@Valid MoneyToIntegralRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeConvertService.doCoinToMoney(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 转化记录
	 */
	@RequestMapping("selectAccountConvert")
	public DataPageComonResponse<CuAccountConvert> selectAccountConvert(@Valid TokenTypePageRequest request, BindingResult result, HttpServletRequest req) {
		DataPageComonResponse<CuAccountConvert> response = new DataPageComonResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeConvertService.selectAccountConvert(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 收支流水
	 */
	@RequestMapping("selectCustomerProfit")
	public DataPageComonResponse<CuCustomerProfit> selectCustomerProfit(@Valid CustomerProfitRequest request, BindingResult result, HttpServletRequest req) {
		DataPageComonResponse<CuCustomerProfit> response = new DataPageComonResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeConvertService.selectCustomerProfit(request, req);
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
			response = tradeConvertService.doCancleMyTradeById(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 二维码收付款
	 */
	@RequestMapping("tradeByQRCode")
	public DataResponse tradeByQRCode(@Valid TransferAccountsRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = tradeConvertService.doTradeByQRCode(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

}
