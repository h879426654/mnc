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
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
import com.basics.common.TokenTypePageRequest;
import com.basics.cu.entity.CuAccountConvert;
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
import com.basics.support.mybatis.CacheUsed;
import com.basics.sys.entity.SysConfig;
import com.basics.sys.entity.SysMarketValue;
import com.basics.sys.entity.SysRule;
import com.basics.tr.controller.request.CustomerProfitRequest;
import com.basics.tr.controller.request.MoneyToIntegralRequest;
import com.basics.tr.controller.request.PayMyTradeRequest;
import com.basics.tr.controller.request.TransferAccountsRequest;
import com.basics.tr.controller.response.IndexDataResponse;
import com.basics.tr.controller.response.TradeResponse;
import com.basics.tr.entity.TrConvert;
import com.basics.tr.entity.TrTradeCoin;
import com.basics.tr.entity.TrTradeConvert;
import com.basics.tr.entity.TrTradeMoney;
import com.basics.tr.service.TradeConvertApiService;

@Service
@Transactional
public class TradeConvertApiServiceImpl extends BaseApiService implements TradeConvertApiService {

	/**
	 * 交易顶部数据
	 */
	@Override
	public DataItemResponse<IndexDataResponse> selectIndexData(TokenRequest request, HttpServletRequest req) {
		DataItemResponse<IndexDataResponse> response = new DataItemResponse<>();
		IndexDataResponse index = new IndexDataResponse();
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
		CacheUsed.clearAllCache();
		// 获取今日市值
		SysMarketValue moneyRate = getMarketValueToday();
		if (null != moneyRate) {
			index.setDollarRate(moneyRate.getDollarRate());
			index.setMoneyRate(moneyRate.getRmbRate());
			index.setRatioRate(new BigDecimal(1).divide(moneyRate.getRmbRate(), 5, BigDecimal.ROUND_HALF_UP));
		}
		TrConvert convert = trConvertDao.queryOne(new QueryFilterBuilder().build());
		index.setConvert(convert);

		CuCustomerAccount account = cuCustomerAccountDao.get(user.getId());
		CommonSupport.checkNotNull(account, "用户account错误, id:" + user.getId());
		index.setUseMoney(account.getUseMoney());
		index.setUseCoin(account.getUseCoin());
		index.setLockMoney(account.getLockMoney());
		index.setLockCoin(account.getLockCoin());
		index.setCustomerIntegral(account.getCustomerIntegral());
		index.setCustomerPurse(account.getCustomerPurse());
		SysRule sysRule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		if (null != sysRule) {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("customerId", account.getId());
			paramMap.put("convertType", Constant.CONVERT_TYPE_1);
			long count = cuAccountConvertDao.count(paramMap);
			if (0 < count) {
				index.setRateToIntegralMore(sysRule.getRateToIntegralMore());
			} else {
				index.setRateToIntegralMore(sysRule.getRateToIntegralFirst());
			}
			index.setTradeRate(sysRule.getTradeRate());
			index.setRewardNum(sysRule.getRewardNum());
			index.setSignRewardNum(sysRule.getSignRewardNum());
			index.setDiscountNum(sysRule.getDiscountNum());
			index.setNeedUploadLicence(sysRule.getNeedUploadLicence());
		}

		// 昨天今天静态释放
		CuCustomerProfit profit = cuCustomerProfitDao.queryOne(new QueryFilterBuilder().put("nowDate", new Date()).put("customerId", user.getId()).put("profitRemark", "当日MC释放").build());
		if (null != profit) {
			index.setYesterdayStaitcIntegral(profit.getProfitNum());
		}
		// money交易量
		List<TrTradeMoney> jyl = trTradeMoneyDao.queryExtend(new QueryFilterBuilder().build(), "queryTradeMoneryTables");
		if (CollectionUtils.isNotEmpty(jyl)) {
			Map<String, Object> jylMap = new HashMap<>();
			List<String> date = new ArrayList<>();
			List<BigDecimal> value = new ArrayList<>();
			for (TrTradeMoney trade : jyl) {
				date.add(DateUtils.formatDate(trade.getTradeFinishTime(), "MM-dd"));
				value.add(trade.getMoneyNum());
			}
			jylMap.put("date", date);
			jylMap.put("value", value);
			index.getTables().put("moneyJyl", jylMap);
		}

		// coin交易量
		List<TrTradeCoin> coinJyl = trTradeCoinDao.queryExtend(new QueryFilterBuilder().build(), "queryTradeCoinTables");
		if (CollectionUtils.isNotEmpty(coinJyl)) {
			Map<String, Object> jylMap = new HashMap<>();
			List<String> date = new ArrayList<>();
			List<BigDecimal> value = new ArrayList<>();
			for (TrTradeCoin trade : coinJyl) {
				date.add(DateUtils.formatDate(trade.getTradeFinishTime(), "MM-dd"));
				value.add(trade.getMoneyNum());
			}
			jylMap.put("date", date);
			jylMap.put("value", value);
			index.getTables().put("coinJyl", jylMap);
		}

		// 行情
		List<SysMarketValue> hq = sysMarketValueDao.queryExtend(new QueryFilterBuilder().build(), "queryMarketValueTables");
		if (CollectionUtils.isNotEmpty(hq)) {
			Map<String, Object> hqMap = new HashMap<>();
			List<String> date = new ArrayList<>();
			List<BigDecimal> value = new ArrayList<>();
			for (SysMarketValue market : hq) {
				date.add(DateUtils.formatDate(market.getRateTime(), "MM-dd"));
				value.add(market.getDollarRate());
			}
			hqMap.put("date", date);
			hqMap.put("value", value);
			index.getTables().put("hq", hqMap);
		}
		response.setItem(index);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 余额转积分
	 */
	@Override
	public DataResponse doMoneyToIntegral(MoneyToIntegralRequest request, HttpServletRequest req) {
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

		if (null == request.getMoneyNum() || 0 >= request.getMoneyNum().doubleValue()) {
			response.onHandleFail("数量不能为空");
			return response;
		} else {
			if (request.getMoneyNum().doubleValue() % 50 != 0) {
				response.onHandleFail("复投数量必须为50的整数");
				return response;
			}
		}
		if (StringUtils.isBlank(request.getPayPass())) {
			response.onHandleFail("支付密码不能为空");
			return response;
		}
		CuCustomerAccount account = cuCustomerAccountDao.get(user.getId());
		CommonSupport.checkNotNull(account, "用户account错误， id:" + user.getId());
		if (!account.getCustomerPayPass().equals(MD5Util.GetMD5Code(request.getPayPass()))) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.payPass.error"));
			return response;
		}
		if (account.getUseMoney().doubleValue() < request.getMoneyNum().doubleValue()) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.mc.insufficient"));
			return response;
		}
		createActiveMq(user.getId(), Constant.ACTIVEMQ_TYPE_18, request.getMoneyNum(), "", "余额转积分", null);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 余额转链
	 */
	@Override
	public DataResponse doMoneyToCoin(MoneyToIntegralRequest request, HttpServletRequest req) {
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
		if (null == request.getMoneyNum() || 0 >= request.getMoneyNum().doubleValue()) {
			response.onHandleFail("数量错误");
			return response;
		}
		if (StringUtils.isBlank(request.getPayPass())) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.payPass.error"));
			return response;
		}
		CuCustomerAccount account = cuCustomerAccountDao.get(user.getId());
		CommonSupport.checkNotNull(account, "用户account错误， id:" + user.getId());
		if (!account.getCustomerPayPass().equals(MD5Util.GetMD5Code(request.getPayPass()))) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.payPass.error"));
			return response;
		}
		if (Constant.UN_ACTIVE_NUM > account.getCustomerIntegral().doubleValue()) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.user.notActive"));
			return response;
		}
		//条件判断
		TrConvert trConvert = trConvertDao.queryOne(new QueryFilterBuilder().build());
		CommonSupport.checkNotNull(trConvert, "获取兑换信息错误");
		Date now = new Date();
		if (trConvert.getConvertStartTime().getTime() > now.getTime()) {
			response.onHandleFail(getMessage(req, "tradeConvertApiServiceImpl.doMoneyToCoin.convert.notStart"));
			return response;
		}
		if (trConvert.getConvertEndTime().getTime() < now.getTime()) {
			response.onHandleFail(getMessage(req, "tradeConvertApiServiceImpl.doMoneyToCoin.convert.end"));
			return response;
		}
		SysMarketValue marketValue = sysMarketValueDao.queryOne(new QueryFilterBuilder().put("nowDate", new Date()).build());
		CommonSupport.checkNotNull(marketValue, "获取市值失败");
		BigDecimal num = request.getMoneyNum().divide(marketValue.getRmbRate(), 5, BigDecimal.ROUND_DOWN); // 多少个链
		if (trConvert.getConvertNum().doubleValue() < num.doubleValue()) {
			response.onHandleFail(getMessage(req, "tradeConvertApiServiceImpl.doMoneyToCoin.convert.notEnough"));
			return response;
		}
		if (account.getUseMoney().doubleValue() < request.getMoneyNum().doubleValue()) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.mc.insufficient"));
			return response;
		}
		//MNC限购判断
		String result = judgeLimitCoin(user.getId(), num);
		if (StringUtils.isNotBlank(result)) {
			response.onHandleFail(result);
			return response;
		}

		createActiveMq(user.getId(), Constant.ACTIVEMQ_TYPE_19, request.getMoneyNum(), "", "余额转链", null);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 链转余额
	 */
	@Override
	public DataResponse doCoinToMoney(MoneyToIntegralRequest request, HttpServletRequest req) {
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
		if (null == request.getMoneyNum() || 0 >= request.getMoneyNum().doubleValue()) {
			response.onHandleFail("数量错误");
			return response;
		}
		if (StringUtils.isBlank(request.getPayPass())) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.payPass.error"));
			return response;
		}
		CuCustomerAccount account = cuCustomerAccountDao.get(user.getId());
		CommonSupport.checkNotNull(account, "用户account错误， id:" + user.getId());
		if (!account.getCustomerPayPass().equals(MD5Util.GetMD5Code(request.getPayPass()))) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.payPass.error"));
			return response;
		}
		SysMarketValue marketValue = sysMarketValueDao.queryOne(new QueryFilterBuilder().put("nowDate", new Date()).build());
		CommonSupport.checkNotNull(marketValue, "获取市值失败");
		if (account.getUseCoin().doubleValue() < request.getMoneyNum().doubleValue()) {
			response.onHandleFail(getMessage(req, "tradeCoinApiServiceImpl.doPushTradeInfo.mnc.insufficient"));
			return response;
		}
		createActiveMq(user.getId(), Constant.ACTIVEMQ_TYPE_20, request.getMoneyNum(), "", "链转余额", null);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 转化记录
	 */
	@Override
	public DataPageComonResponse<CuAccountConvert> selectAccountConvert(TokenTypePageRequest request, HttpServletRequest req) {
		DataPageComonResponse<CuAccountConvert> response = new DataPageComonResponse<>();
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
		params.put("customerId", user.getId());
		if (Constant.STATE_NO == request.getType()) {
			params.put("convertTypeOneAndFice", Constant.TRADE_STATUS_1);
		} else {
			params.put("convertType", request.getType());
		}
		params.put("orderBy", "cuAccountConvert.CREATE_TIME DESC");
		PaginationSupport pagination = cuAccountConvertDao.queryPagination(params, request.getPageNo(), request.getPageSize());
		if (null != pagination && CollectionUtils.isNotEmpty(pagination.getItems())) {
			response.getData().setItems(pagination.getItems());
			response.getData().setPageCount(pagination.getPageCount());
			response.getData().setTotalCount(pagination.getTotalCount());
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 收支流水
	 */
	@Override
	public DataPageComonResponse<CuCustomerProfit> selectCustomerProfit(CustomerProfitRequest request, HttpServletRequest req) {
		DataPageComonResponse<CuCustomerProfit> response = new DataPageComonResponse<>();
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
		if (null != request.getStatus()) {
			params.put("profitStatus", request.getStatus());
		}
		params.put("profitType", request.getType());
		params.put("customerId", user.getId());
		params.put("GTProfitNum", Constant.MIN_PROFIT_NUM_SHOW);
		params.put("orderBy", "cuCustomerProfit.PROFIT_HAVED_TIME DESC");
		PaginationSupport pagination = cuCustomerProfitDao.queryPaginationExtend("queryProfitInfo", "count", params, request.getPageNo(), request.getPageSize());
		if (null != pagination && CollectionUtils.isNotEmpty(pagination.getItems())) {
			response.getData().setItems(pagination.getItems());
			response.getData().setPageCount(pagination.getPageCount());
			response.getData().setTotalCount(pagination.getTotalCount());
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 匹配转账
	 */
	@Override
	public DataResponse doMatchTransferAccount(TransferAccountsRequest request, HttpServletRequest req) {
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
		if (null == request.getMoneyNum() || 0 >= request.getMoneyNum().doubleValue()) {
			response.onHandleFail("数量不能为空");
			return response;
		} else {
			if (request.getMoneyNum().doubleValue() % 1 != 0) {
				response.onHandleFail("交易数量必须为1的整数");
				return response;
			}
		}
		// source
		CuCustomerAccount sourceAccount = cuCustomerAccountDao.get(user.getId());
		CommonSupport.checkNotNull(sourceAccount, "用户account错误， id:" + user.getId());
		if (!sourceAccount.getCustomerPayPass().equals(MD5Util.GetMD5Code(request.getPayPass()))) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.payPass.error"));
			return response;
		}
		if (Constant.UN_ACTIVE_NUM > sourceAccount.getCustomerIntegral().doubleValue()) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.user.notActive"));
			return response;
		}
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		CommonSupport.checkNotNull(rule, "获取平台规则失败");
		if (checkCustomerTrade(sourceAccount.getId(), rule, Constant.MONEY_TYPE_3)) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.trade.undone"));
			return response;
		}
		BigDecimal rate = request.getMoneyNum();
		if (sourceAccount.getUseMoney().doubleValue() < rate.doubleValue()) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.mc.insufficient"));
			return response;
		}
		//判断交易链是否足够
		AppOption tradeRateValue = appOptionDao.get("TRADE_RATE_VALUE");
		if (null != tradeRateValue) {
			BigDecimal tradeRateCoin = BigDecimal.valueOf(Double.valueOf(tradeRateValue.getCode()));
			if (sourceAccount.getTradeCoin().doubleValue() < rate.multiply(tradeRateCoin).doubleValue()) {
				response.onHandleFail("交易链不足！");
				return response;
			}
		}
		// target
		CuCustomerInfo targetCustomer = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("customerPhone", request.getTargetPhone()).build());
		if (null == targetCustomer) {
			response.onHandleFail(getMessage(req, "tradeConvertApiServiceImpl.doMatchTransferAccount.target.nonexistent"));
			return response;
		}
		if (checkCustomerInfo(targetCustomer.getId())) {
			response.onHandleFail(getMessage(req, "tradeConvertApiServiceImpl.doMatchTransferAccount.target.complete"));
			return response;
		}
		if (sourceAccount.getId().equals(targetCustomer.getId())) {
			response.onHandleFail(getMessage(req, "tradeConvertApiServiceImpl.doMatchTransferAccount.target.error"));
			return response;
		}

		// TODO
		if (3 != info.getFlagSpecial() && 3 != targetCustomer.getFlagSpecial()) {
			if (null != info.getFlagSpecial()) {
				if (null != targetCustomer.getFlagSpecial()) {
					if (info.getFlagSpecial().intValue() != targetCustomer.getFlagSpecial().intValue()) {
						response.onHandleFail("您只能在网体内转账");
						return response;
					}
				} else {
					response.onHandleFail("您只能在网体内转账");
					return response;
				}
			} else {
				if (null != targetCustomer.getFlagSpecial()) {
					response.onHandleFail("转账失败");
					return response;
				}
			}
			/*
			 * if(customerIsReferee(sourceAccount.getId(), targetCustomer.getId())) {
			 * response.onHandleFail("您只能往自己网体以下转账"); return response; }
			 */
		}
		createActiveMq(user.getId(), Constant.ACTIVEMQ_TYPE_11, request.getMoneyNum(), targetCustomer.getId(), "余额定向转账", null);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 转账中
	 */
	@Override
	public DataItemResponse<List<TradeResponse>> doSelectConvertTrading(TokenRequest request, HttpServletRequest req) {
		DataItemResponse<List<TradeResponse>> response = new DataItemResponse<>();
		// 判断token是否存在
		CuCustomerLogin user = checkToken(request.getToken());
		if (null == user) {
			response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
			response.setStatus(2);
			return response;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customerOrBuyId", user.getId());
		map.put("tradeIng", "tradeIng");// 交易中
		// map.put("applyStatus", Constant.APPLY_STATUS_2);
		map.put("orderBy", "trTradeConvert.TRADE_PAY_TIME DESC");
		List<TradeResponse> data = trTradeConvertDao.queryExtend(new QueryFilterBuilder().putAll(map).build(), "queryTradeIngResponse");
		if (CollectionUtils.isNotEmpty(data)) {
			for (TradeResponse trading : data) {
				List<AppImage> appImages = appImageDao.listImgsByClassAndId("TrTradeConvert", trading.getId());
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
			response.setItem(data);
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 上传凭证
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
		TrTradeConvert trade = trTradeConvertDao.queryOne(new QueryFilterBuilder().put("id", request.getId()).put("customerBuyId", user.getId()).put("tradeStatus", Constant.TRADE_STATUS_2).build());
		CommonSupport.checkNotNull(trade, getMessage(req, "tradeMoneyApiServiceImpl.doMatchingTrade.trade.status.error"));
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
					appImage.setOwnerClass("TrTradeConvert");
					appImage.setUrl(url);
					appImageDao.save(appImage);
				}
			}
			trade.setTradePayTime(new Date());
			trade.setTradeStatus(Constant.TRADE_STATUS_3);
			trTradeConvertDao.save(trade);
			response.onHandleSuccess();
		} else {
			response.onHandleFail("图片不能为空");
		}
		return response;
	}

	/**
	 * 确定交易
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
		String checkResult = checkTradeRule(rule, null, null, now, Constant.TRADE_TYPE_1, Constant.MONEY_TYPE_1, false);
		if (null != checkResult && !"".equals(checkResult)) {
			response.onHandleFail(checkResult);
			return response;
		}
		TrTradeConvert trade = trTradeConvertDao.queryOne(new QueryFilterBuilder().put("id", request.getId()).put("customerId", user.getId()).put("tradeStatus", Constant.TRADE_STATUS_3).build());
		CommonSupport.checkNotNull(trade, "该交易状态无法确认");
		createActiveMq(user.getId(), Constant.ACTIVEMQ_TYPE_12, trade.getMoneyNum(), trade.getId(), "完成余额定向转账", null);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 转账完成列表
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
		params.put("orderBy", "trTradeConvert.trade_finish_time DESC");
		PaginationSupport queryPaginationExtend = trTradeConvertDao.queryPaginationExtend("queryTradeIngResponse", "countTradeResponse", params, request.getPageNo(), request.getPageSize());
		if (null != queryPaginationExtend && CollectionUtils.isNotEmpty(queryPaginationExtend.getItems())) {
			List<TradeResponse> list = queryPaginationExtend.getItems();
			for (TradeResponse trade : list) {
				List<AppImage> appImages = appImageDao.listImgsByClassAndId("TrTradeConvert", trade.getId());
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
			response.getData().setItems(queryPaginationExtend.getItems());
		}
		response.getData().setPageCount(queryPaginationExtend.getPageCount());
		response.getData().setTotalCount(queryPaginationExtend.getTotalCount());
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 撤销交易
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
		TrTradeConvert trade = trTradeConvertDao.queryOne(new QueryFilterBuilder().put("id", request.getId()).put("tradeStatus", Constant.TRADE_STATUS_2).build());
		if (null == trade) {
			response.onHandleFail("该交易不存在或当前状态不能取消交易");
			return response;
		}
		if (trade.getCustomerId().equals(user.getId()) || trade.getCustomerBuyId().equals(user.getId())) {
			if (Constant.APPLY_STATUS_2 == trade.getApplyStatus().intValue()) {
				response.onHandleFail("该交易后台已审核通过，请尽快完成交易");
				return response;
			}
			createActiveMq(user.getId(), Constant.ACTIVEMQ_TYPE_13, BigDecimal.ZERO, trade.getId(), "取消余额定向转账", null);
			response.onHandleSuccess();
			return response;
		} else {
			response.onHandleFail("该交易不属于您");
			return response;
		}
	}

	/**
	 * 二维码收付款
	 */
	@Override
	public DataResponse doTradeByQRCode(TransferAccountsRequest request, HttpServletRequest req) {
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
		if (Constant.STATE_NO == info.getFlagTrade()) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.trade.ban"));
			return response;
		}

		// source
		CuCustomerAccount sourceAccount = cuCustomerAccountDao.get(user.getId());
		CommonSupport.checkNotNull(sourceAccount, "用户account错误， id:" + user.getId());
		if (!sourceAccount.getCustomerPayPass().equals(MD5Util.GetMD5Code(request.getPayPass()))) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.payPass.error"));
			return response;
		}
		if (Constant.UN_ACTIVE_NUM > sourceAccount.getCustomerIntegral().doubleValue()) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.user.notActive"));
			return response;
		}
		if (sourceAccount.getUseMoney().doubleValue() < request.getMoneyNum().doubleValue()) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.mc.insufficient"));
			return response;
		}
		//判断交易链是否足够
		AppOption tradeRateValue = appOptionDao.get("TRADE_RATE_VALUE");
		if (null != tradeRateValue) {
			BigDecimal tradeRateCoin = BigDecimal.valueOf(Double.valueOf(tradeRateValue.getCode()));
			if (sourceAccount.getTradeCoin().doubleValue() < request.getMoneyNum().multiply(tradeRateCoin).doubleValue()) {
				response.onHandleFail("交易链不足！");
				return response;
			}
		}
		// source
		CuCustomerInfo target = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("customerPhone", request.getTargetPhone()).build());
		if (null == target) {
			response.onHandleFail(getMessage(req, "tradeConvertApiServiceImpl.doTradeByQRCode.target.error"));
			return response;
		}
		if (user.getId().equals(target.getId())) {
			response.onHandleFail("不能给自己转账");
			return response;
		}
		SysConfig flag = sysConfigDao.getConfigByCode("TRANSFER_QRCODE_A_TO_B_FLAG");
		if (Constant.STATE_YES == flag.getConfigFlag() && Constant.STATE_NO == flag.getConfigValue().intValue()) {
			if (3 != info.getFlagSpecial() && 3 != target.getFlagSpecial()) {
				if (null != info.getFlagSpecial()) {
					if (null != target.getFlagSpecial()) {
						if (info.getFlagSpecial().intValue() != target.getFlagSpecial().intValue()) {
							response.onHandleFail("您只能在网体内转账");
							return response;
						}
					} else {
						response.onHandleFail("您只能在网体内转账");
						return response;
					}
				} else {
					if (null != target.getFlagSpecial()) {
						response.onHandleFail("转账失败");
						return response;
					}
				}
				/*
				 * if(customerIsReferee(sourceAccount.getId(), targetCustomer.getId())) {
				 * response.onHandleFail("您只能往自己网体以下转账"); return response; }
				 */
			}

		}

		// 判断是否为商家
		/*
		 * MallShopAdvert advert = mallShopAdvertDao.queryOne(new
		 * QueryFilterBuilder().put("customerId", target.getId()).put("applyStatus",
		 * Constant.APPLY_STATUS_2).build()); if(null == advert) {
		 * response.onHandleFail("你还没有发布商圈"); return response; }
		 */

		createActiveMq(user.getId(), Constant.ACTIVEMQ_TYPE_35, request.getMoneyNum(), target.getId(), "二维码收付款", null);
		response.onHandleSuccess();
		return response;
	}

}
