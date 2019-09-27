package com.basics.cu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.basics.cu.entity.*;
import com.basics.sys.entity.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.basics.app.entity.AppOption;
import com.basics.common.BaseApiService;
import com.basics.common.Constant;
import com.basics.common.DataItemResponse;
import com.basics.common.DataPageComonResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenIdRequest;
import com.basics.common.TokenIdsRequest;
import com.basics.common.TokenPageRequest;
import com.basics.common.TokenRequest;
import com.basics.common.TokenTypePageRequest;
import com.basics.common.TokenTypeRequest;
import com.basics.cu.controller.request.CustomerFeedbackRequest;
import com.basics.cu.controller.request.ModifyCustomerInfoRequest;
import com.basics.cu.controller.response.BaseImgResponse;
import com.basics.cu.controller.response.CustomerInfoResponse;
import com.basics.cu.controller.response.DirectCustomerResponse;
import com.basics.cu.controller.response.IndexViewResponse;
import com.basics.cu.controller.response.TurntableResponse;
import com.basics.cu.service.CustomerApiService;
import com.basics.support.CommonSupport;
import com.basics.support.DateUtils;
import com.basics.support.PaginationSupport;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.reward.RewardUtil;
import com.basics.tr.entity.TrTradeCoin;
import com.basics.tr.entity.TrTradeMoney;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class CustomerApiServiceImpl extends BaseApiService implements CustomerApiService {

	/**
	 * 个人信息
	 */
	@Override
	public DataItemResponse<CustomerInfoResponse> selectCustomerInfo(TokenRequest request, HttpServletRequest req) {
		DataItemResponse<CustomerInfoResponse> response = new DataItemResponse<>();
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
		Map<String, Object> map = new HashMap<>();
		map.put("id", user.getId());
		CustomerInfoResponse data = cuCustomerInfoDao.getExtend(map, "queryCustomerInfo");
		//等级
		CuCustomerInfo info = cuCustomerInfoDao.get(user.getId());
		CuCustomerCount count = cuCustomerCountDao.get(user.getId());
		SysCustomerLevel level = sysCustomerLevelDao.get(count.getCustomerLevelId());
		if(Constant.COUNTRY_CHINA.equals(info.getCountryId())) {
			data.setCustomerLevelName(level.getLevelName());
		} else if(Constant.COUNTRY_KOREA.equals(info.getCountryId())) {
			data.setCustomerLevelName(level.getLevelKoreanName());
		} else if(Constant.COUNTRY_JAPAN.equals(info.getCountryId())) {
			data.setCustomerLevelName(level.getLevelJapaneseName());
		} else {
			data.setCustomerLevelName(level.getLevelEnglishName());
		}

		List<DirectCustomerResponse> list = cuCustomerRefereeDao.queryExtend(new QueryFilterBuilder().put("id", user.getId()).build(), "queryRefereeCustomer");
		if (CollectionUtils.isNotEmpty(list)) {
			DirectCustomerResponse referee = list.get(0);
			data.setRefereeName(referee.getCustomerName());
			data.setRefereePhone(referee.getCustomerPhone());
		}
		response.setItem(data);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 修改个人信息
	 */
	@Override
	public DataResponse doModifyCustomerInfo(ModifyCustomerInfoRequest request, HttpServletRequest req) {
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
		//if (StringUtils.isBlank(info.getCustomerCard()) && StringUtils.isNotBlank(request.getCustomerCard())) {
		//if (request.getCustomerCard().length() == 15) {
		//		if (!Pattern.compile(Constant.AUTH_15_REGX).matcher(request.getCustomerCard()).matches()) {
		//			response.onHandleFail(getMessage(req, "customerApiServiceImpl.doModifyCustomerInfo.idCard.error"));
		//			return response;
		//		}
		//	} else if (request.getCustomerCard().length() == 18) {
		//		if (!Pattern.compile(Constant.AUTH_18_REGX).matcher(request.getCustomerCard()).matches()) {
		//			response.onHandleFail(getMessage(req, "customerApiServiceImpl.doModifyCustomerInfo.idCard.error"));
		//			return response;
		//		}
		//	} else {
		//		response.onHandleFail(getMessage(req, "customerApiServiceImpl.doModifyCustomerInfo.idCard.error"));
		//		return response;
		//	}
		//	info.setCustomerCard(request.getCustomerCard());
		//}
		//if (StringUtils.isBlank(info.getRealName())) {
		//	info.setRealName(request.getRealName());
		//}
		//info.setCustomerAlipay(request.getCustomerAlipay());
		//info.setCustomerCard(request.getCustomerCard());
		//info.setBankCard(request.getBankCard());
		//info.setBankName(request.getBankName());
		info.setCustomerName(request.getCustomerName());
		cuCustomerInfoDao.save(info);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 根据手机号查找用户
	 */
	@Override
	public DataItemResponse<CuCustomerInfo> selectCustomerByPhone(TokenIdRequest request, HttpServletRequest req) {
		DataItemResponse<CuCustomerInfo> response = new DataItemResponse<>();
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
		CuCustomerInfo data = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("customerPhone", request.getId()).build());
		if (null != data) {
			response.setItem(data);
		} else {
			response.setItem(new CuCustomerInfo());
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 我的圈友
	 */
	@Override
	public DataPageComonResponse<DirectCustomerResponse> selectDirectCustomerList(TokenPageRequest request, HttpServletRequest req) {
		DataPageComonResponse<DirectCustomerResponse> response = new DataPageComonResponse<>();
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
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("refereeId", user.getId());
		PaginationSupport paginationExtend = cuCustomerRefereeDao.queryPaginationExtend("queryDirectCustomer", "count", params, request.getPageNo(), request.getPageSize());
		if (CollectionUtils.isNotEmpty(paginationExtend.getItems())) {
			response.getData().setItems(paginationExtend.getItems());
			response.getData().setPageCount(paginationExtend.getPageCount());
			response.getData().setTotalCount(paginationExtend.getTotalCount());
			response.getData().setCurrentPageNo(paginationExtend.getCurrentPageNo());
		}
		response.onHandleSuccess();
		return response;

	}

	/**
	 * 首页
	 */
	@Override
	public DataItemResponse<IndexViewResponse> selectIndexView(TokenRequest request, HttpServletRequest req) {
		DataItemResponse<IndexViewResponse> response = new DataItemResponse<>();
		IndexViewResponse data = new IndexViewResponse();
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

		List<BaseImgResponse> banners = sysBannerDao.queryExtend(new QueryFilterBuilder().put("bannerType", Constant.BANNER_TYPE_1).build(), "queryBannerImg");
		if (CollectionUtils.isNotEmpty(banners)) {
			data.setBanners(banners);
		}
		List<SysNotice> notices = sysNoticeDao.query(new QueryFilterBuilder().put("bulletinType", Constant.BULLETIN_TYPE_1).orderBy("sysNotice.CREATE_TIME DESC").limit(1).build());
		if (CollectionUtils.isNotEmpty(notices)) {
			data.setNotices(notices);
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
			data.getTables().put("moneyJyl", jylMap);
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
			data.getTables().put("coinJyl", jylMap);
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
			data.getTables().put("hq", hqMap);
		}
		response.setItem(data);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 用户未读的公告信息
	 */
	@Override
	public DataItemResponse<SysNotice> selectCustomerTopMsg(TokenRequest request, HttpServletRequest req) {
		DataItemResponse<SysNotice> response = new DataItemResponse<>();
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
		SysNotice notice = sysNoticeDao.queryOne(new QueryFilterBuilder().put("bulletinType", Constant.BULLETIN_TYPE_2).orderBy("sysNotice.CREATE_TIME DESC").build());
		if (null != notice) {
			CuCustomerMessage message = cuCustomerMessageDao.queryOne(new QueryFilterBuilder().put("customerId", user.getId()).put("appMessageId", notice.getId()).build());
			if (null == message) {
				pushCustomerMessage(user.getId(), notice.getId(), notice.getNoticeTitle(), notice.getNoticeContext(), Constant.STATE_YES, Constant.Message_TYPE_1);
				response.setItem(notice);
			}
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 查询公告
	 */
	@Override
	public DataPageComonResponse<SysNotice> selectSysNotice(TokenTypePageRequest request, HttpServletRequest req) {
		DataPageComonResponse<SysNotice> response = new DataPageComonResponse<>();
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
		Map<String, Object> map = new HashMap<>();
		map.put("bulletinType", request.getType());
		if(Constant.BULLETIN_TYPE_5 == request.getType()) {
			map.put("orderBy", "sysNotice.CREATE_TIME ASC");
			request.setPageSize(1);
		} else {
			map.put("orderBy", "sysNotice.NOTICE_SORT ASC");
		}
		PaginationSupport pagination = sysNoticeDao.queryPagination(map, request.getPageNo(), request.getPageSize());
		if (null != pagination && CollectionUtils.isNotEmpty(pagination.getItems())) {
			response.getData().setItems(pagination.getItems());
			response.getData().setPageCount(pagination.getPageCount());
			response.getData().setTotalCount(pagination.getTotalCount());
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 用户消息查询
	 */
	@Override
	public DataPageComonResponse<CuCustomerMessage> selectCustomerMessage(TokenTypePageRequest request, HttpServletRequest req) {
		DataPageComonResponse<CuCustomerMessage> response = new DataPageComonResponse<>();
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
		Map<String, Object> map = new HashMap<>();
		map.put("messageType", request.getType());
		map.put("customerId", user.getId());
		map.put("orderBy", "cuCustomerMessage.FLAG_READ ASC");
		PaginationSupport pagination = cuCustomerMessageDao.queryPagination(map, request.getPageNo(), request.getPageSize());
		if (null != pagination && CollectionUtils.isNotEmpty(pagination.getItems())) {
			response.getData().setItems(pagination.getItems());
			response.getData().setPageCount(pagination.getPageCount());
			response.getData().setTotalCount(pagination.getTotalCount());
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 查询banner
	 */
	@Override
	public DataItemResponse<List<BaseImgResponse>> selectSysBanner(TokenTypeRequest request, HttpServletRequest req) {
		DataItemResponse<List<BaseImgResponse>> response = new DataItemResponse<>();
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
		
		List<BaseImgResponse> list = sysBannerDao.queryExtend(new QueryFilterBuilder().put("bannerType", request.getType()).orderBy("sysBanner.BANNER_SORT ASC").build(), "queryBannerImg");
		if(CollectionUtils.isNotEmpty(list)) {
			response.setItem(list);
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 会员签到
	 */
	@Override
	public DataResponse doCustomerSign(TokenRequest request, HttpServletRequest req) {
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
		CuCustomerSign sign = cuCustomerSignDao.queryOne(new QueryFilterBuilder().put("customerId", user.getId()).put("nowDate", new Date()).build());
		if(null != sign) {
			response.onHandleFail(getMessage(req, "customerApiServiceImpl.doCustomerSign.sign.agent"));
			return response;
		}
		
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		CommonSupport.checkNotNull(rule, "系统规则不能为空");
		if(0 < rule.getSignRewardNum().doubleValue()) {
			createActiveMq(user.getId(), Constant.ACTIVEMQ_TYPE_39, BigDecimal.ZERO, null, "签到", null);

			response.onHandleSuccess();
		} else {
			response.onHandleFail("系统升级中，敬请期待");
		}
		return response;
	}

	/**
	 * 提出反馈
	 */
	@Override
	public DataResponse doPushCustomerFeedback(CustomerFeedbackRequest request, HttpServletRequest req) {
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
		CuCustomerFeedback feedback = new CuCustomerFeedback();
		feedback.feedbackType(request.getType()).customerId(user.getId()).feedbackContext(request.getContext()).feedbackStatus(Constant.APPLY_STATUS_1).createTime(new Date());
		cuCustomerFeedbackDao.save(feedback);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 意见反馈列表
	 */
	@Override
	public DataItemResponse<List<CuCustomerFeedback>> getCustomerFeedbackList(TokenRequest request, HttpServletRequest req) {
		DataItemResponse<List<CuCustomerFeedback>> response = new DataItemResponse<>();
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
		List<CuCustomerFeedback> list = cuCustomerFeedbackDao.query(new QueryFilterBuilder().put("customerId", user.getId()).orderBy("cuCustomerFeedback.create_time DESC").build());
		if(CollectionUtils.isNotEmpty(list)) {
			response.setItem(list);
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 意见反馈类型
	 */
	@Override
	public DataItemResponse<List<AppOption>> getFeedbackType(TokenRequest request, HttpServletRequest req) {
		DataItemResponse<List<AppOption>> response = new DataItemResponse<>();
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
		List<AppOption> list = appOptionDao.query(new QueryFilterBuilder().put("parentId", "FEEDBACK_TYPE").orderBy("appOption.OPTION_CODE ASC").build());
		if(CollectionUtils.isNotEmpty(list)) {
			response.setItem(list);
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 获取抽奖配置
	 */
	@Override
	public DataItemResponse<Map<String, Object>> doRewardTurntable(TokenRequest request, HttpServletRequest req) {
		DataItemResponse<Map<String, Object>> response = new DataItemResponse<>();
		Map<String, Object> map = new HashMap<>();
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
		List<SysTurntable> list = sysTurntableDao.query(new QueryFilterBuilder().orderBy("REWARD_SORT ASC").build());
		if (CollectionUtils.isNotEmpty(list)) {
			List<TurntableResponse> result = new ArrayList<TurntableResponse>();
			TurntableResponse info = null;
			for (SysTurntable turntable : list) {
				info = new TurntableResponse();
				info.setRewardType(turntable.getRewardType());
				info.setRewardNum(turntable.getRewardNum());
				result.add(info);
			}
			map.put("list", result);
		} else {
			response.onHandleFail("无法获取转盘奖品");
			return response;
		}
		response.setItem(map);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 获取中奖数
	 */
	@Override
	public DataItemResponse<Integer> doRewardNum(TokenRequest request, HttpServletRequest req) {
		DataItemResponse<Integer> response = new DataItemResponse<Integer>();
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
		SysRule sysRule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		CommonSupport.checkNotNull(sysRule, "没有系统配置");
		if(Constant.STATE_NO == sysRule.getRewardFlag()) {
			response.onHandleFail("抽奖已结束");
			return response;
		}
		CuCustomerAccount account = cuCustomerAccountDao.get(user.getId());
		CommonSupport.checkNotNull(account, "会员账户错误");
		if (account.getUseMoney().doubleValue() < sysRule.getRewardNum().doubleValue()) {
			response.onHandleFail("余额不足，无法抽奖");
			return response;
		}
		List<SysTurntable> list = sysTurntableDao.query(new QueryFilterBuilder().orderBy("REWARD_SORT ASC").build());
		if (CollectionUtils.isEmpty(list)) {
			response.onHandleFail("无法获取转盘奖品");
			return response;
		}
		// 判断今日平台盈利
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nowDate", new Date());
		Double sysNum = cuDrawRewardDao.getExtend(map, "sumDrawReward");
		Double rewardNum = sysTurntableRewardDao.getExtend(map, "sumTurntableReward");
		BigDecimal sysNumBig = new BigDecimal(sysNum).multiply(new BigDecimal(0.8));
		SysTurntable info = null;
		if (sysNumBig.doubleValue() >= rewardNum) {
			info = RewardUtil.generateReward(list);
		} else {
			info = sysTurntableDao.queryOne(new QueryFilterBuilder().orderBy("REWARD_NUM ASC").limit(1).build());
		}
		if (null != info) {
			for (int i = 0; i < list.size(); i++) {
				if (info.getId().equals(list.get(i).getId())) {
					response.setItem(i + 1);
				}
			}
			createActiveMq(user.getId(), Constant.ACTIVEMQ_TYPE_33, sysRule.getRewardNum(), info.getId(), "大转盘抽奖", new Object());
			response.onHandleSuccess();
			return response;
		} else {
			response.onHandleFail("无法获取抽奖结果");
			return response;
		}
	}

	/**
	 * 中奖列表
	 */
	@Override
	public DataItemResponse<Map<String, Object>> doGetRewardList(TokenRequest request, HttpServletRequest req) {
		DataItemResponse<Map<String, Object>> response = new DataItemResponse<>();
		Map<String, Object> map = new HashMap<>();
		// 判断token是否存在
		CuCustomerLogin user = checkToken(request.getToken());
		if (null == user) {
			response.onHandleFail("token失效");
			response.setStatus(2);
			return response;
		}
		if (!checkCustomerStatus(user)) {
			response.onHandleFail("该账号被冻结，无法进行相关操作");
			return response;
		}
		
		List<SysTurntableReward> data = sysTurntableRewardDao.query(new QueryFilterBuilder().orderBy("sysTurntableReward.CREATE_TIME DESC").limit(50).build());
		if (CollectionUtils.isNotEmpty(data)) {
			map.put("list", data);
		}
		long count = sysTurntableRewardDao.count(new HashMap<String, Object>());
		map.put("count", count);
		response.setItem(map);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 我的中奖记录
	 */
	@Override
	public DataPageComonResponse<SysTurntableReward> getCustomerReward(TokenPageRequest request, HttpServletRequest req) {
		DataPageComonResponse<SysTurntableReward> response = new DataPageComonResponse<>();
		// 判断token是否存在
		CuCustomerLogin user = checkToken(request.getToken());
		if (null == user) {
			response.onHandleFail("token失效");
			response.setStatus(2);
			return response;
		}
		if (!checkCustomerStatus(user)) {
			response.onHandleFail("该账号被冻结，无法进行相关操作");
			return response;
		}
		Map<String, Object> params = new HashMap<>();
		params.put("customerId", user.getId());
		params.put("orderBy", "sysTurntableReward.CREATE_TIME DESC");
		PaginationSupport pagination = sysTurntableRewardDao.queryPaginationExtend("queryTurntableReward", "count", params, request.getPageNo(), request.getPageSize());
		if(null != pagination && CollectionUtils.isNotEmpty(pagination.getItems())) {
			response.getData().setItems(pagination.getItems());
			response.getData().setPageCount(pagination.getPageCount());
			response.getData().setTotalCount(pagination.getTotalCount());
			response.getData().setCurrentPageNo(pagination.getCurrentPageNo());
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 消息详情
	 */
	@Override
	public DataItemResponse<CuCustomerMessage> selectCustomerMessageInfo(TokenIdRequest request, HttpServletRequest req) {
		DataItemResponse<CuCustomerMessage> response = new DataItemResponse<>();
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
		CuCustomerMessage data = cuCustomerMessageDao.queryOne(new QueryFilterBuilder().put("id", request.getId()).put("customerId", user.getId()).build());
		if (null != data) {
			data.setFlagRead(Constant.STATE_YES);
			cuCustomerMessageDao.save(data);
			response.setItem(data);
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 删除/批量删除消息
	 */
	@Override
	public DataResponse delCustomerMessageByIds(TokenIdsRequest request, HttpServletRequest req) {
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
		if (null != request.getIds() && request.getIds().length > 0) {
			CuCustomerMessage info = null;
			for (int i = 0; i < request.getIds().length; i++) {
				info = new CuCustomerMessage();
				info.setId(request.getIds()[i]);
				info.setCustomerId(user.getId());
				info.setFlagDel(Constant.STATE_YES);
				cuCustomerMessageDao.save(info);
			}
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 一键已读
	 */
	@Override
	public DataResponse messagetoRead(TokenRequest request, HttpServletRequest req) {
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
		CuCustomerMessage info = new CuCustomerMessage();
		info.setCustomerId(user.getId());
		info.setFlagRead(Constant.STATE_YES);
		cuCustomerMessageDao.updateExtend(info, "updateByCustomerId");
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 未读信息条数
	 */
	@Override
	public DataItemResponse<JSONObject> selectUnReadNum(TokenTypeRequest request, HttpServletRequest req) {
		DataItemResponse<JSONObject> response = new DataItemResponse<>();
		JSONObject data = new JSONObject();
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
		Map<String, Object> map = new HashMap<>();
		map.put("customerId", user.getId());
		map.put("flagRead", Constant.STATE_NO);
		map.put("flagDel", Constant.STATE_NO);
		if(Constant.STATE_YES == request.getType()) {
			Long num = cuCustomerMessageDao.count(map );
			data.put("all", num);
		} else {
			map.put("messageType", Constant.Message_TYPE_1);
			data.put("system", cuCustomerMessageDao.count(map));
			map.put("messageType", Constant.Message_TYPE_2);
			data.put("trade", cuCustomerMessageDao.count(map));
			map.put("messageType", Constant.Message_TYPE_3);
			data.put("mall", cuCustomerMessageDao.count(map));
			
		}
		response.setItem(data);
		response.onHandleSuccess();
		return response;
	}

}
