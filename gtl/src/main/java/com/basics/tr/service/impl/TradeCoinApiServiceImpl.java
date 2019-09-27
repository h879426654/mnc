package com.basics.tr.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.basics.app.entity.AppImage;
import com.basics.app.entity.AppOption;
import com.basics.common.BaseApiService;
import com.basics.common.Constant;
import com.basics.common.DataItemResponse;
import com.basics.common.DataPageComonResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenIdRequest;
import com.basics.common.TokenPageRequest;
import com.basics.common.TokenRequest;
import com.basics.common.TokenTypeRequest;
import com.basics.cu.entity.CuCustomerAccount;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.cu.entity.CuCustomerLogin;
import com.basics.cu.entity.CuCustomerProfit;
import com.basics.support.CommonSupport;
import com.basics.support.DateUtils;
import com.basics.support.GuidUtils;
import com.basics.support.MD5Util;
import com.basics.support.PaginationSupport;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.SerialnumberUtils;
import com.basics.support.auth.ModifyCoinUtil;
import com.basics.sys.entity.SysConfig;
import com.basics.sys.entity.SysRule;
import com.basics.tr.controller.request.MatchIngTradeRequest;
import com.basics.tr.controller.request.ModifyCoinTradeRequest;
import com.basics.tr.controller.request.PayMyTradeRequest;
import com.basics.tr.controller.request.PushTradeRequest;
import com.basics.tr.controller.request.SelectTradeListRequest;
import com.basics.tr.controller.response.TradeResponse;
import com.basics.tr.entity.TrTradeCoin;
import com.basics.tr.service.TradeCoinApiService;

@Service
@Transactional
public class TradeCoinApiServiceImpl extends BaseApiService implements TradeCoinApiService {


	/**
	 * 发布交易信息 业务需求 1. 一人只能有发一单 2. 出售方需要支付密码 3. 判断发布的金额,数量是否合法
	 * 
	 */
	@Override
	public DataResponse doPushTradeInfo(PushTradeRequest request, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		// 判断token是否存在
		CuCustomerLogin user = checkToken(request.getToken());
		if (null == user) {
			response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
			response.setStatus(2);
			return response;
		}
		if (!checkCustomerStatus(user)) {
			response.onHandleFail(getMessage(req, "impl.doLogin.user.freeze"));
			return response;
		}
		if (checkCustomerInfo(user.getId())) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.user.complete"));
			return response;
		}
		if (null == request.getTradePrice() || request.getTradePrice().doubleValue() <= 0) {
			response.onHandleFail("金额不合法");
			return response;
		}
		CuCustomerInfo info = cuCustomerInfoDao.get(user.getId());
		CommonSupport.checkNotNull(info, "用户信息错误， id:" + user.getId());
		if (Constant.STATE_NO == info.getFlagTrade()) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.trade.ban"));
			return response;
		}
		// 交易规则的校验
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		CommonSupport.checkNotNull(rule, "系统规则不存在");
		// 判断是否一笔出售一笔求购
		if (checkTradeTypeIsOk(user.getId(), request.getTradeType(), Constant.MONEY_TYPE_2)) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.trade.undone"));
			return response;
		}
		Date now = new Date();
		// 每笔数量判断
		String checkResult = checkTradeRule(rule, new BigDecimal(request.getMoneyNum()), request.getTradePrice(), now, request.getTradeType(), Constant.MONEY_TYPE_2, true);
		if (null != checkResult && !"".equals(checkResult)) {
			response.onHandleFail(checkResult);
			return response;
		}
		TrTradeCoin trade = new TrTradeCoin();
		BigDecimal rate = keepFiveNum(new BigDecimal(request.getMoneyNum()).add(new BigDecimal(request.getMoneyNum()).multiply(rule.getTradeRate())));
		if (Constant.TRADE_TYPE_1 == request.getTradeType().intValue()) { // 出售
			if (StringUtils.isBlank(request.getPayPass())) {
				response.onHandleFail("支付密码不能为空");
				return response;
			}
			CuCustomerAccount customerAccount = cuCustomerAccountDao.get(user.getId());
			if (Constant.UN_ACTIVE_TRADE_NUM > customerAccount.getCustomerIntegral().doubleValue()) {
				response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.user.notActive"));
				return response;
			}
			CommonSupport.checkNotNull(customerAccount, "会员账号错误");
			if (!customerAccount.getCustomerPayPass().equals(MD5Util.GetMD5Code(request.getPayPass()))) {
				response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.payPass.error"));
				return response;
			}
			//判断交易链是否足够
			AppOption tradeRateValue = appOptionDao.get("TRADE_RATE_VALUE");
			if (null != tradeRateValue) {
				BigDecimal tradeRateCoin = BigDecimal.valueOf(Double.valueOf(tradeRateValue.getCode()));
				if (customerAccount.getTradeCoin().doubleValue() < BigDecimal.valueOf(request.getMoneyNum().doubleValue()).multiply(tradeRateCoin).doubleValue()) {
					response.onHandleFail("交易链不足，无法发布！");
					return response;
				}
			}
			if (customerAccount.getUseCoin().doubleValue() < rate.doubleValue()) {
				response.onHandleFail(getMessage(req, "tradeCoinApiServiceImpl.doPushTradeInfo.mnc.insufficient"));
				return response;
			}
			createActiveMq(user.getId(), Constant.ACTIVEMQ_TYPE_14, new BigDecimal(request.getMoneyNum()), request.getTradePrice().toString(), "CEC交易-出售", null);
			response.onHandleSuccess();
			return response;
		} else {
			trade.setCustomerBuyId(user.getId());
			trade.tradeType(request.getTradeType()).moneyNum(new BigDecimal(request.getMoneyNum())).lockMoneyNum(rate).tradePrice(request.getTradePrice())
					.tradeSerial(SerialnumberUtils.generateUsePrefix("MNC", true)).tradeStatus(Constant.TRADE_STATUS_1).createTime(now).applyStatus(Constant.APPLY_STATUS_2)
					.countryId(info.getCountryId());
			trTradeCoinDao.save(trade);

			response.onHandleSuccess();
			return response;
		}

	}

	@Override
	public int getName() {
		Map<String, Object> map = new HashMap<>();
		int a = trConvertDao.getExtend(map, "aaa");
		return 0;
	}

	/**
	 * 交易大厅查询
	 */
	@Override
	public DataPageComonResponse<TradeResponse> doSelectTradeList(SelectTradeListRequest request, HttpServletRequest req) {
		DataPageComonResponse<TradeResponse> response = new DataPageComonResponse<TradeResponse>();
		// 判断token是否存在
		CuCustomerLogin user = checkToken(request.getToken());
		if (null == user) {
			response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
			response.setStatus(2);
			return response;
		}
		if (!checkCustomerStatus(user)) {
			response.onHandleFail(getMessage(req, "impl.doLogin.user.freeze"));
			return response;
		}
		CuCustomerInfo info = cuCustomerInfoDao.get(user.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("countryId", info.getCountryId());
		map.put("tradeType", request.getTradeType());
		map.put("tradeStatus", Constant.TRADE_STATUS_1);
		map.put("applyStatus", Constant.APPLY_STATUS_2);
		if (StringUtils.isNotBlank(request.getSearchName())) {
			map.put("searchName", request.getSearchName());
		}
		if (Constant.TRADE_TYPE_1 == request.getTradeType().intValue()) {
			map.put("orderBy", "trTradeCoin.trade_price ASC");
		} else {
			map.put("orderBy", "trTradeCoin.trade_price DESC");
		}
		PaginationSupport paginationSupport = trTradeCoinDao.queryPaginationExtend("queryTradeResponse", "countTradeResponse", map, request.getPageNo(), request.getPageSize());
		if (null != paginationSupport && CollectionUtils.isNotEmpty(paginationSupport.getItems())) {
			response.getData().setCurrentPageNo(paginationSupport.getCurrentPageNo());
			response.getData().setItems(paginationSupport.getItems());
			response.getData().setTotalCount(paginationSupport.getTotalCount());
			response.getData().setPageCount(paginationSupport.getPageCount());
			response.onHandleSuccess();
			return response;
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 我的待交易列表(买入/卖出)
	 */
	@Override
	public DataItemResponse<List<TradeResponse>> doSelectMyTrade(TokenTypeRequest request, HttpServletRequest req) {
		DataItemResponse<List<TradeResponse>> response = new DataItemResponse<List<TradeResponse>>();
		// 判断token是否存在
		CuCustomerLogin user = checkToken(request.getToken());
		if (null == user) {
			response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
			response.setStatus(2);
			return response;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tradeType", request.getType());
		map.put("tradeStatus", Constant.TRADE_STATUS_1);
		if (Constant.TRADE_TYPE_1 == request.getType().intValue()) {
			map.put("customerId", user.getId());
		} else {
			map.put("customerBuyId", user.getId());
		}
		map.put("orderBy", "trTradeCoin.CREATE_TIME DESC");
		List<TradeResponse> data = trTradeCoinDao.queryExtend(new QueryFilterBuilder().putAll(map).build(), "queryTradeResponse");
		if (CollectionUtils.isNotEmpty(data)) {
			response.setItem(data);
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 匹配交易
	 */
	@Override
	public DataResponse doMatchingTrade(MatchIngTradeRequest request, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		// 判断token是否存在
		CuCustomerLogin user = checkToken(request.getToken());
		if (null == user) {
			response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
			response.setStatus(2);
			return response;
		}
		if (!checkCustomerStatus(user)) {
			response.onHandleFail(getMessage(req, "impl.doLogin.user.freeze"));
			return response;
		}

		if (checkCustomerInfo(user.getId())) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.user.complete"));
			return response;
		}
		CuCustomerInfo info = cuCustomerInfoDao.get(user.getId());
		CommonSupport.checkNotNull(info, "用户信息错误， id:" + user.getId());
		if (Constant.STATE_NO == info.getFlagTrade()) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.trade.ban"));
			return response;
		}
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		Date now = new Date();
		// 规则校验
		String checkResult = checkTradeRule(rule, null, null, now, Constant.TRADE_TYPE_1, Constant.MONEY_TYPE_1, false);
		if (null != checkResult && !"".equals(checkResult)) {
			response.onHandleFail(checkResult);
			return response;
		}
		TrTradeCoin trade = trTradeCoinDao.queryOne(new QueryFilterBuilder().put("id", request.getId()).put("tradeStatus", Constant.TRADE_STATUS_1).build());
		CommonSupport.checkNotNull(trade, getMessage(req, "tradeMoneyApiServiceImpl.doMatchingTrade.trade.status.error"));
		// 判断是否一笔出售一笔求购
		if (checkTradeTypeIsOk(user.getId(), Constant.TRADE_TYPE_1 == trade.getTradeType() ? Constant.TRADE_TYPE_2 : Constant.TRADE_TYPE_1, Constant.MONEY_TYPE_2)) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.trade.undone"));
			return response;
		}
		if (user.getId().equals(trade.getCustomerId()) || user.getId().equals(trade.getCustomerBuyId())) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doMatchingTrade.own.buy"));
			return response;
		}
		if (Constant.TRADE_TYPE_2 == trade.getTradeType().intValue()) {
			if (StringUtils.isBlank(request.getPayPass())) {
				response.onHandleFail("支付密码不能为空");
				return response;
			}
			CuCustomerAccount account = cuCustomerAccountDao.get(user.getId());
			if (Constant.UN_ACTIVE_TRADE_NUM > account.getCustomerIntegral().doubleValue()) {
				response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.user.notActive"));
				return response;
			}
			CommonSupport.checkNotNull(account, "会员账号错误");
			if (!account.getCustomerPayPass().equals(MD5Util.GetMD5Code(request.getPayPass()))) {
				response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.payPass.error"));
				return response;
			}
			BigDecimal rate = keepFiveNum(trade.getMoneyNum().add(trade.getMoneyNum().multiply(rule.getTradeRate())));
			if (account.getUseCoin().doubleValue() < rate.doubleValue()) {
				response.onHandleFail(getMessage(req, "tradeCoinApiServiceImpl.doPushTradeInfo.mnc.insufficient"));
				return response;
			}
		}
		createActiveMq(user.getId(), Constant.ACTIVEMQ_TYPE_15, BigDecimal.ZERO, trade.getId(), "匹配链交易-出售", null);
		response.onHandleSuccess();
		return response;

	}

	/**
	 * 我的交易列表(交易中)
	 */
	@Override
	public DataItemResponse<List<TradeResponse>> doSelectMyTrading(TokenRequest request, HttpServletRequest req) {
		DataItemResponse<List<TradeResponse>> response = new DataItemResponse<>();
		List<TradeResponse> totalData = new ArrayList<>();
		// 判断token是否存在
		CuCustomerLogin user = checkToken(request.getToken());
		if (null == user) {
			response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
			response.setStatus(2);
			return response;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tradeStatus", Constant.TRADE_STATUS_1);
		map.put("customerOrBuyId", user.getId());
		map.put("orderBy", "trTradeCoin.CREATE_TIME DESC");
		List<TradeResponse> data = trTradeCoinDao.queryExtend(new QueryFilterBuilder().putAll(map).build(), "queryTradeResponse");
		if (CollectionUtils.isNotEmpty(data)) {
			totalData.addAll(data);
		}

		map.clear();
		;
		map.put("customerOrBuyId", user.getId());
		map.put("tradeIng", "tradeIng");// 交易中
		// map.put("applyStatus", Constant.APPLY_STATUS_2);
		map.put("orderBy", "trTradeCoin.TRADE_PAY_TIME DESC");
		data = trTradeCoinDao.queryExtend(new QueryFilterBuilder().putAll(map).build(), "queryTradeIngResponse");
		if (CollectionUtils.isNotEmpty(data)) {
			for (TradeResponse trading : data) {
				List<AppImage> appImages = appImageDao.listImgsByClassAndId("TrTradeCoin", trading.getId());
				if (CollectionUtils.isNotEmpty(appImages)) {
					List<String> imgUrs = new ArrayList<>();
					for (AppImage appImage : appImages) {
						imgUrs.add(appImage.getUrl());
					}
					trading.setVoucherImg(imgUrs);
				}
				if (trading.getCustomerId().equals(user.getId())) {
					if (Constant.APPLY_STATUS_1 == trading.getApplyStatus().intValue()) {
						trading.setBuyBankCard(null);
						trading.setBuyBankName(null);
						trading.setBuyCustomerAlipay(null);
						trading.setBuyCustomerHead(null);
						trading.setBuyCustomerName(null);
						trading.setBuyCustomerPhone(null);
						trading.setBuyRealName(null);
					}
					trading.setFlagMerchant(1);
				} else {
					if (Constant.APPLY_STATUS_1 == trading.getApplyStatus().intValue()) {
						trading.setMallBankCard(null);
						trading.setMallBankName(null);
						trading.setMallCustomerAlipay(null);
						trading.setMallCustomerHead(null);
						trading.setMallCustomerName(null);
						trading.setMallCustomerPhone(null);
						trading.setMallRealName(null);
					}
					trading.setFlagMerchant(0);
				}
			}
			totalData.addAll(data);
		}
		response.setItem(totalData);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 撤销
	 */
	@Override
	public DataResponse doCancleMyTradeById(TokenIdRequest request, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		// 判断token是否存在
		CuCustomerLogin user = checkToken(request.getToken());
		if (null == user) {
			response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
			response.setStatus(2);
			return response;
		}
		if (!checkCustomerStatus(user)) {
			response.onHandleFail(getMessage(req, "impl.doLogin.user.freeze"));
			return response;
		}
		CuCustomerInfo info = cuCustomerInfoDao.get(user.getId());
		CommonSupport.checkNotNull(info, "用户信息错误， id:" + user.getId());
		if (Constant.STATE_NO == info.getFlagTrade()) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.trade.ban"));
			return response;
		}
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		CommonSupport.checkNotNull(rule, "交易规则不存在");
		Date now = new Date();
		// 规则校验
		String checkResult = checkTradeRule(rule, null, null, now, Constant.TRADE_TYPE_1, Constant.MONEY_TYPE_1, false);
		if (null != checkResult && !"".equals(checkResult)) {
			response.onHandleFail(checkResult);
			return response;
		}
		// 查询交易表数据
		TrTradeCoin trade = trTradeCoinDao.queryOne(new QueryFilterBuilder().put("id", request.getId()).put("cancleTrade", Constant.TRADE_STATUS_2).build());
		CommonSupport.checkNotNull(trade, getMessage(req, "tradeMoneyApiServiceImpl.doMatchingTrade.trade.status.error"));
		if (trade.getCustomerId().equals(user.getId()) || trade.getCustomerBuyId().equals(user.getId())) {
			createActiveMq(user.getId(), Constant.ACTIVEMQ_TYPE_17, BigDecimal.ZERO, trade.getId(), "取消链交易", null);
			response.onHandleSuccess();
			return response;
		} else {
			response.onHandleFail("该交易不属于您");
			return response;
		}
	}

	/**
	 * 上传交易凭证
	 */
	@Override
	public DataResponse doPayMyTrade(PayMyTradeRequest request, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		// 判断token是否存在
		CuCustomerLogin user = checkToken(request.getToken());
		if (null == user) {
			response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
			response.setStatus(2);
			return response;
		}
		if (!checkCustomerStatus(user)) {
			response.onHandleFail(getMessage(req, "impl.doLogin.user.freeze"));
			return response;
		}
		CuCustomerInfo info = cuCustomerInfoDao.get(user.getId());
		CommonSupport.checkNotNull(info, "用户信息错误， id:" + user.getId());
		if (Constant.STATE_NO == info.getFlagTrade()) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.trade.ban"));
			return response;
		}
		Date now = new Date();
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		CommonSupport.checkNotNull(rule, "交易规则不存在");
		// 规则校验
		String checkResult = checkTradeRule(rule, null, null, now, Constant.TRADE_TYPE_1, Constant.MONEY_TYPE_1, false);
		if (null != checkResult && !"".equals(checkResult)) {
			response.onHandleFail(checkResult);
			return response;
		}
		TrTradeCoin trade = trTradeCoinDao.queryOne(new QueryFilterBuilder().put("id", request.getId()).put("customerBuyId", user.getId()).put("tradeStatus", Constant.TRADE_STATUS_2).build());
		CommonSupport.checkNotNull(trade, "该交易不存在或不属于您");
		if (null != request.getFiles() && request.getFiles().length > 0) {
			AppImage appImage = null;
			for (MultipartFile file : request.getFiles()) {
				if (!file.isEmpty()) {
					String path = "voucher/";
					String fileName = file.getOriginalFilename();
					path += GuidUtils.generateSimpleGuid() + MD5Util.random(6) + fileName.substring(fileName.lastIndexOf("."));
					try {
						this.baseFileStoreService.write(path, file.getInputStream());
					} catch (IOException e) {
						e.printStackTrace();
					}
					String url = this.baseFileStoreService.getInternetUrl(path);
					appImage = new AppImage();
					appImage.setOwnerId(request.getId());
					appImage.setOwnerClass("TrTradeCoin");
					appImage.setUrl(url);
					appImageDao.save(appImage);
				}
			}
			trade.setTradePayTime(new Date());
			trade.setTradeStatus(Constant.TRADE_STATUS_3);
			trTradeCoinDao.save(trade);
			response.onHandleSuccess();
		} else {
			response.onHandleFail("图片不能为空");
		}
		return response;
	}

	/**
	 * 确认
	 */
	@Override
	public DataResponse doConfirmMyTrade(TokenIdRequest request, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		// 判断token是否存在
		CuCustomerLogin user = checkToken(request.getToken());
		if (null == user) {
			response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
			response.setStatus(2);
			return response;
		}
		if (!checkCustomerStatus(user)) {
			response.onHandleFail(getMessage(req, "impl.doLogin.user.freeze"));
			return response;
		}
		CuCustomerInfo info = cuCustomerInfoDao.get(user.getId());
		CommonSupport.checkNotNull(info, "用户信息错误， id:" + user.getId());
		if (Constant.STATE_NO == info.getFlagTrade()) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.trade.ban"));
			return response;
		}
		// 交易规则
		Date now = new Date();
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		// 规则校验
		String checkResult = checkTradeRule(rule, null, null, now, Constant.TRADE_TYPE_1, Constant.MONEY_TYPE_2, false);
		if (null != checkResult && !"".equals(checkResult)) {
			response.onHandleFail(checkResult);
			return response;
		}
		TrTradeCoin trade = trTradeCoinDao.queryOne(new QueryFilterBuilder().put("id", request.getId()).put("customerId", user.getId()).put("tradeStatus", Constant.TRADE_STATUS_3).build());
		CommonSupport.checkNotNull(trade, getMessage(req, "tradeMoneyApiServiceImpl.doMatchingTrade.trade.status.error"));
		createActiveMq(user.getId(), Constant.ACTIVEMQ_TYPE_16, BigDecimal.ZERO, trade.getId(), "确认链交易", null);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 交易完成列表
	 */
	@Override
	public DataPageComonResponse<TradeResponse> selectMyTradeList(TokenPageRequest request, HttpServletRequest req) {
		DataPageComonResponse<TradeResponse> response = new DataPageComonResponse<>();
		// 判断token是否存在
		CuCustomerLogin user = checkToken(request.getToken());
		if (null == user) {
			response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
			response.setStatus(2);
			return response;
		}
		if (!checkCustomerStatus(user)) {
			response.onHandleFail(getMessage(req, "impl.doLogin.user.freeze"));
			return response;
		}

		Map<String, Object> params = new HashMap<>();
		params.put("GTtradeStatus", Constant.TRADE_STATUS_5);
		params.put("customerOrBuyId", user.getId());
		params.put("applyStatus", Constant.APPLY_STATUS_2);
		params.put("orderBy", "trTradeCoin.trade_finish_time DESC");
		PaginationSupport queryPaginationExtend = trTradeCoinDao.queryPaginationExtend("queryTradeIngResponse", "countTradeResponse", params, request.getPageNo(), request.getPageSize());
		if (null != queryPaginationExtend && CollectionUtils.isNotEmpty(queryPaginationExtend.getItems())) {
			List<TradeResponse> list = queryPaginationExtend.getItems();
			for (TradeResponse trade : list) {
				List<AppImage> appImages = appImageDao.listImgsByClassAndId("TrTradeCoin", trade.getId());
				if (CollectionUtils.isNotEmpty(appImages)) {
					List<String> imgUrs = new ArrayList<>();
					for (AppImage appImage : appImages) {
						imgUrs.add(appImage.getUrl());
					}
					trade.setVoucherImg(imgUrs);
				}
				if (trade.getCustomerId().equals(user.getId())) {
					trade.setFlagMerchant(1);
				} else {
					trade.setFlagMerchant(0);
				}
			}
			response.getData().setItems(list);
		}
		response.getData().setPageCount(queryPaginationExtend.getPageCount());
		response.getData().setTotalCount(queryPaginationExtend.getTotalCount());
		response.onHandleSuccess();
		return response;
	}

	@Override
	public synchronized DataResponse modifyCoinTrade(ModifyCoinTradeRequest request, HttpServletRequest req) throws Exception {
		DataResponse response = new DataResponse();
		// 判断token是否存在
		CuCustomerLogin user = checkToken(request.getToken());
		if (null == user) {
			response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
			response.setStatus(2);
			return response;
		}
		if (!checkCustomerStatus(user)) {
			response.onHandleFail(getMessage(req, "impl.doLogin.user.freeze"));
			return response;
		}
		if (checkCustomerInfo(user.getId())) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.user.complete"));
			return response;
		}
		if (null == request.getMoneyNum() || request.getMoneyNum().intValue() <= 0) {
			response.onHandleFail("数量必须满足50个以上");
			return response;
		}
		AppOption flagModify = appOptionDao.get("FLAG_MODIFY");
		if (null == flagModify || "0".equals(flagModify.getCode())) {
			response.onHandleFail("系统维护中，该功能暂不开放");
			return response;
		}
		List<CuCustomerProfit> profitList = cuCustomerProfitDao
				.query(new QueryFilterBuilder().put("nowDate", DateUtils.getDate()).put("customerId", user.getId()).put("profitRemark", "转币到交易所").build());
		if (CollectionUtils.isNotEmpty(profitList)) {
			response.onHandleFail("每天只能转一次");
			return response;
		}
		CuCustomerInfo info = cuCustomerInfoDao.get(user.getId());
		CommonSupport.checkNotNull(info, "用户信息错误， id:" + user.getId());
		if (Constant.STATE_NO == info.getFlagTrade()) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.trade.ban"));
			return response;
		}
		if (StringUtils.isBlank(request.getPayPass())) {
			response.onHandleFail("支付密码不能为空");
			return response;
		}
		CuCustomerAccount customerAccount = cuCustomerAccountDao.get(user.getId());
		CommonSupport.checkNotNull(customerAccount, "会员账号错误");
		if (!customerAccount.getCustomerPayPass().equals(MD5Util.GetMD5Code(request.getPayPass()))) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.payPass.error"));
			return response;
		}
		//手续费
		SysConfig rate = sysConfigDao.getConfigByCode("MODIFY_COIN_RATE");
		BigDecimal modifyMoney = BigDecimal.valueOf(request.getMoneyNum());
		if (null != rate && rate.getConfigValue().doubleValue() > 0) {
			modifyMoney = modifyMoney.multiply(BigDecimal.ONE.add(rate.getConfigValue()));
		}
		if (customerAccount.getUseCoin().doubleValue() < modifyMoney.doubleValue()) {
			response.onHandleFail("MNC资产不足");
			return response;
		}
		String resultStr = ModifyCoinUtil.modifyCoin(request.getMember(), request.getMoneyNum().toString());
		JSONObject result = JSONObject.parseObject(resultStr);
		if ("0".equals(result.getString("code"))) {
			customerAccount.setUseCoin(keepFiveNum(customerAccount.getUseCoin().subtract(modifyMoney)));
			customerAccount.setTotalCoin(keepFiveNum(customerAccount.getTotalCoin().subtract(modifyMoney)));
			cuCustomerAccountDao.save(customerAccount);
			doAddCustomerProfit(customerAccount.getId(), "", modifyMoney, Constant.PROFIT_STATUS_2, Constant.MONEY_TYPE_2, "转币到交易所");
			response.onHandleSuccess();
			return response;
		} else {
			response.onHandleFail(result.getString("msg"));
			return response;
		}
	}

}
