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
import com.basics.support.CommonSupport;
import com.basics.support.GuidUtils;
import com.basics.support.MD5Util;
import com.basics.support.PaginationSupport;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.SerialnumberUtils;
import com.basics.sys.entity.SysConfig;
import com.basics.sys.entity.SysRule;
import com.basics.tr.controller.request.MatchIngTradeRequest;
import com.basics.tr.controller.request.PayMyTradeRequest;
import com.basics.tr.controller.request.PushTradeRequest;
import com.basics.tr.controller.request.SelectTradeListRequest;
import com.basics.tr.controller.response.TradeResponse;
import com.basics.tr.entity.TrTradeMoney;
import com.basics.tr.service.TradeMoneyApiService;

/**
 * @author fan
 *
 */
@Service
@Transactional
public class TradeMoneyApiServiceImpl extends BaseApiService implements TradeMoneyApiService {

	/**
	 * 发布交易信息 业务需求 1. 一人只能有发一单 2. 出售方需要二级密码 3. 交易为余额交易时，交易金额为1
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
		CuCustomerInfo info = cuCustomerInfoDao.get(user.getId());
		if (Constant.STATE_NO == info.getFlagTrade()) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.trade.ban"));
			return response;
		}

		// 交易规则的校验
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		/*
		 * CommonSupport.checkNotNull(rule, "系统规则不存在"); if
		 * (checkCustomerTrade(user.getId(), rule, Constant.MONEY_TYPE_1)) {
		 * response.onHandleFail("您还有未完成的交易"); return response; }
		 */
		// 判断是否一笔出售一笔求购
		if (checkTradeTypeIsOk(user.getId(), request.getTradeType(), Constant.MONEY_TYPE_1)) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.trade.undone"));
			return response;
		}

		if (null == request.getMoneyNum() || 0 >= request.getMoneyNum()) {
			response.onHandleFail("数量不能为空");
			return response;
		} else {
			if (request.getMoneyNum() % 50 != 0) {
				response.onHandleFail("交易数量必须为50的整数");
				return response;
			}
		}
		Date now = new Date();
		// 每笔数量判断
		String checkResult = checkTradeRule(rule, new BigDecimal(request.getMoneyNum()), null, now, request.getTradeType(), Constant.MONEY_TYPE_1, true);
		if (null != checkResult && !"".equals(checkResult)) {
			response.onHandleFail(checkResult);
			return response;
		}
		if (Constant.TRADE_TYPE_1 == request.getTradeType().intValue()) { // 出售
			SysConfig config = sysConfigDao.getConfigByCode("TRADE_MC_SELL_FLAG");
			if (Constant.STATE_YES == config.getConfigFlag() && Constant.STATE_NO == config.getConfigValue().intValue()) {
				response.onHandleFail("未到出售时间");
				return response;
			}
			if (!judgeTradeTime(user.getId())) {
				response.onHandleFail("今日出售次数已满");
				return response;
			}

			if (StringUtils.isBlank(request.getPayPass())) {
				response.onHandleFail("支付密码不能为空");
				return response;
			}
			CuCustomerAccount account = cuCustomerAccountDao.get(user.getId());
			CommonSupport.checkNotNull(account, "会员账号错误");
			if (!account.getCustomerPayPass().equals(MD5Util.GetMD5Code(request.getPayPass()))) {
				response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.payPass.error"));
				return response;
			}
			if (Constant.UN_ACTIVE_TRADE_NUM > account.getCustomerIntegral().doubleValue()) {
				response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.user.notActive"));
				return response;
			}
			BigDecimal rate = keepFiveNum(new BigDecimal(request.getMoneyNum()).add(new BigDecimal(request.getMoneyNum()).multiply(rule.getTradeRate())));
			if (account.getUseMoney().doubleValue() < rate.doubleValue()) {
				response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.mc.insufficient"));
				return response;
			}
			//判断交易链是否足够
			AppOption tradeRateValue = appOptionDao.get("TRADE_RATE_VALUE");
			if (null != tradeRateValue) {
				BigDecimal tradeRateCoin = BigDecimal.valueOf(Double.valueOf(tradeRateValue.getCode()));
				if (account.getTradeCoin().doubleValue() < BigDecimal.valueOf(request.getMoneyNum().doubleValue()).multiply(tradeRateCoin).doubleValue()) {
					response.onHandleFail("交易链不足，无法发布！");
					return response;
				}
			}
			createActiveMq(user.getId(), Constant.ACTIVEMQ_TYPE_7, new BigDecimal(request.getMoneyNum()), "", "余额交易-出售", null);
			response.onHandleSuccess();
			return response;
		} else if (Constant.TRADE_TYPE_2 == request.getTradeType().intValue()) {
			SysConfig config = sysConfigDao.getConfigByCode("TRADE_MC_BUY_FLAG");
			if (Constant.STATE_YES == config.getConfigFlag() && Constant.STATE_NO == config.getConfigValue().intValue()) {
				response.onHandleFail("未到求购时间");
				return response;
			}
			TrTradeMoney trade = new TrTradeMoney();
			BigDecimal moneyNum = new BigDecimal(request.getMoneyNum());
			BigDecimal rate = keepFiveNum(moneyNum.add(moneyNum.multiply(rule.getTradeRate())));
			trade.customerBuyId(user.getId()).tradeType(request.getTradeType()).moneyNum(moneyNum).lockMoneyNum(rate).tradePrice(new BigDecimal(1)).tradeStatus(Constant.TRADE_STATUS_1)
					.tradeSerial(SerialnumberUtils.generateUsePrefix("MC", true)).createTime(now).applyStatus(Constant.APPLY_STATUS_2).countryId(info.getCountryId());
			/*if (Constant.TRADE_TYPE_1 == request.getTradeType().intValue()) {
				if (Constant.STATE_NO == rule.getTradeApplyFlag().intValue()) {
					trade.setApplyStatus(Constant.TRADE_STATUS_2);
				} else {
					trade.setApplyStatus(Constant.TRADE_STATUS_1);
				}
			}*/
			trTradeMoneyDao.save(trade);
			response.onHandleSuccess();
			return response;
		} else {
			response.onHandleFail("交易类型错误");
			return response;
		}
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
		Map<String, Object> map = new HashMap<>();
		map.put("countryId", info.getCountryId());
		map.put("tradeType", request.getTradeType());
		map.put("tradeStatus", Constant.TRADE_STATUS_1);
		map.put("applyStatus", Constant.APPLY_STATUS_2);
		if (StringUtils.isNotBlank(request.getSearchName())) {
			map.put("searchName", request.getSearchName());
		}
		map.put("orderBy", "trTradeMoney.CREATE_TIME ASC");
		PaginationSupport support = trTradeMoneyDao.queryPaginationExtend("queryTradeMoneyResponse", "countTradeResponse", map, request.getPageNo(), request.getPageSize());
		if (CollectionUtils.isNotEmpty(support.getItems())) {
			response.getData().setItems(support.getItems());
			response.getData().setCurrentPageNo(support.getCurrentPageNo());
			response.getData().setTotalCount(support.getTotalCount());
			response.getData().setPageCount(support.getPageCount());
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
		map.put("orderBy", "trTradeMoney.CREATE_TIME DESC");
		List<TradeResponse> data = trTradeMoneyDao.queryExtend(new QueryFilterBuilder().putAll(map).build(), "queryTradeMoneyResponse");
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
		TrTradeMoney trade = trTradeMoneyDao.queryOne(new QueryFilterBuilder().put("id", request.getId()).put("tradeStatus", Constant.TRADE_STATUS_1).build());
		CommonSupport.checkNotNull(trade, getMessage(req, "tradeMoneyApiServiceImpl.doMatchingTrade.trade.status.error"));
		// 判断是否一笔出售一笔求购
		if (checkTradeTypeIsOk(user.getId(), Constant.TRADE_TYPE_1 == trade.getTradeType() ? Constant.TRADE_TYPE_2 : Constant.TRADE_TYPE_1, Constant.MONEY_TYPE_1)) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doMatchingTrade.have.trading"));
			return response;
		}

		if (user.getId().equals(trade.getCustomerId()) || user.getId().equals(trade.getCustomerBuyId())) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doMatchingTrade.own.buy"));
			return response;
		}
		if (Constant.TRADE_TYPE_2 == trade.getTradeType().intValue()) {
			SysConfig config = sysConfigDao.getConfigByCode("TRADE_MC_SELL_FLAG");
			if (Constant.STATE_YES == config.getConfigFlag() && Constant.STATE_NO == config.getConfigValue().intValue()) {
				response.onHandleFail("未到出售时间");
				return response;
			}
			if (!judgeTradeTime(user.getId())) {
				response.onHandleFail("今日出售次数已满");
				return response;
			}
			if (StringUtils.isBlank(request.getPayPass())) {
				response.onHandleFail("支付密码不能为空");
				return response;
			}
			CuCustomerAccount sybCustomerAccount = cuCustomerAccountDao.get(user.getId());
			if (Constant.UN_ACTIVE_TRADE_NUM > sybCustomerAccount.getCustomerIntegral().doubleValue()) {
				response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.user.notActive"));
				return response;
			}
			if (null == sybCustomerAccount || !sybCustomerAccount.getCustomerPayPass().equals(MD5Util.GetMD5Code(request.getPayPass()))) {
				response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.payPass.error"));
				return response;
			}

			if (sybCustomerAccount.getUseMoney().doubleValue() < trade.getLockMoneyNum().doubleValue()) {
				response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.mc.insufficient"));
				return response;
			}
		} else {
			SysConfig config = sysConfigDao.getConfigByCode("TRADE_MC_BUY_FLAG");
			if (Constant.STATE_YES == config.getConfigFlag() && Constant.STATE_NO == config.getConfigValue().intValue()) {
				response.onHandleFail("未到求购时间");
				return response;
			}
		}
		createActiveMq(user.getId(), Constant.ACTIVEMQ_TYPE_8, BigDecimal.ZERO, request.getId(), "匹配余额交易", null);
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

		Map<String, Object> map = new HashMap<>();
		map.put("tradeStatus", Constant.TRADE_STATUS_1);
		map.put("customerOrBuyId", user.getId());
		map.put("orderBy", "trTradeMoney.CREATE_TIME DESC");
		List<TradeResponse> data = trTradeMoneyDao.queryExtend(new QueryFilterBuilder().putAll(map).build(), "queryTradeMoneyResponse");
		if (CollectionUtils.isNotEmpty(data)) {
			totalData.addAll(data);
		}

		map.clear();
		map.put("customerOrBuyId", user.getId());
		map.put("tradeIng", "tradeIng");// 交易中
		map.put("orderBy", "trTradeMoney.TRADE_PAY_TIME DESC");
		data = trTradeMoneyDao.queryExtend(new QueryFilterBuilder().putAll(map).build(), "queryTradeIngResponse");
		if (CollectionUtils.isNotEmpty(data)) {
			for (TradeResponse trading : data) {
				List<AppImage> appImages = appImageDao.listImgsByClassAndId("TrTradeMoney", trading.getId());
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
		TrTradeMoney trade = trTradeMoneyDao.queryOne(new QueryFilterBuilder().put("id", request.getId()).put("tradeStatus", Constant.TRADE_STATUS_1).build());
		CommonSupport.checkNotNull(trade, "该交易不存在或当前状态不能取消");

		if (trade.getCustomerId().equals(user.getId()) || trade.getCustomerBuyId().equals(user.getId())) {
			/*if (Constant.APPLY_STATUS_2 == trade.getApplyStatus().intValue()) {
				response.onHandleFail("该交易后台已审核通过，请尽快完成交易");
				return response;
			}*/
			createActiveMq(user.getId(), Constant.ACTIVEMQ_TYPE_10, BigDecimal.ZERO, trade.getId(), "取消余额交易", null);
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
		TrTradeMoney trade = trTradeMoneyDao.queryOne(new QueryFilterBuilder().put("id", request.getId()).put("customerBuyId", user.getId()).put("tradeStatus", Constant.TRADE_STATUS_2).build());
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
					appImage.setOwnerClass("TrTradeMoney");
					appImage.setUrl(url);
					appImageDao.save(appImage);
				}
			}
			trade.setTradePayTime(new Date());
			trade.setTradeStatus(Constant.TRADE_STATUS_3);
			trTradeMoneyDao.save(trade);
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
		TrTradeMoney trade = trTradeMoneyDao.queryOne(new QueryFilterBuilder().put("id", request.getId()).put("customerId", user.getId()).put("tradeStatus", Constant.TRADE_STATUS_3).build());
		CommonSupport.checkNotNull(trade, "该交易状态无法确认");
		createActiveMq(user.getId(), Constant.ACTIVEMQ_TYPE_9, BigDecimal.ZERO, trade.getId(), "完成MC交易", null);
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
		params.put("orderBy", "trTradeMoney.trade_finish_time DESC");
		PaginationSupport queryPaginationExtend = trTradeMoneyDao.queryPaginationExtend("queryTradeIngResponse", "countTradeResponse", params, request.getPageNo(), request.getPageSize());
		if (null != queryPaginationExtend && CollectionUtils.isNotEmpty(queryPaginationExtend.getItems())) {
			List<TradeResponse> list = queryPaginationExtend.getItems();
			for (TradeResponse trade : list) {
				List<AppImage> appImages = appImageDao.listImgsByClassAndId("TrTradeMoney", trade.getId());
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

	/**
	 * 余额+CEC交易完成列表
	 */
	@Override
	public DataPageComonResponse<TradeResponse> selectMyAllTradeList(TokenPageRequest request, HttpServletRequest req) {
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
		params.put("orderBy", "trTradeMoney.trade_finish_time DESC");
		PaginationSupport queryPaginationExtend = trTradeMoneyDao.queryPaginationExtend("queryAllTradeIngResponse", "countAllTradeResponse", params, request.getPageNo(), request.getPageSize());
		if (null != queryPaginationExtend && CollectionUtils.isNotEmpty(queryPaginationExtend.getItems())) {
			List<TradeResponse> list = queryPaginationExtend.getItems();
			for (TradeResponse trade : list) {
				List<AppImage> appImages = appImageDao.listImgsByClassAndId("TrTradeMoney", trade.getId());
				List<AppImage> coinImages = appImageDao.listImgsByClassAndId("TrTradeCoin", trade.getId());
				if (CollectionUtils.isNotEmpty(appImages)) {
					List<String> imgUrs = new ArrayList<>();
					for (AppImage appImage : appImages) {
						imgUrs.add(appImage.getUrl());
					}
					trade.setVoucherImg(imgUrs);
				}
				if (CollectionUtils.isNotEmpty(coinImages)) {
					List<String> imgUrs = new ArrayList<>();
					for (AppImage appImage : coinImages) {
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

}
