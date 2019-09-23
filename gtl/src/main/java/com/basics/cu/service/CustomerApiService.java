package com.basics.cu.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.basics.app.entity.AppOption;
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
import com.basics.cu.entity.CuCustomerFeedback;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.cu.entity.CuCustomerMessage;
import com.basics.sys.entity.SysNotice;
import com.basics.sys.entity.SysTurntableReward;

import javax.servlet.http.HttpServletRequest;

public interface CustomerApiService {

	// 个人信息
	DataItemResponse<CustomerInfoResponse> selectCustomerInfo(TokenRequest request, HttpServletRequest req);

	// 修改个人信息
	DataResponse doModifyCustomerInfo(ModifyCustomerInfoRequest request, HttpServletRequest req);

	/**
	 * 根据手机号查找用户
	 */
	DataItemResponse<CuCustomerInfo> selectCustomerByPhone(TokenIdRequest request, HttpServletRequest req);

	// 我的圈友
	DataPageComonResponse<DirectCustomerResponse> selectDirectCustomerList(TokenPageRequest request, HttpServletRequest req);

	// 首页
	DataItemResponse<IndexViewResponse> selectIndexView(TokenRequest request, HttpServletRequest req);

	/**
	 * 用户未读的公告信息
	 */
	DataItemResponse<SysNotice> selectCustomerTopMsg(TokenRequest request, HttpServletRequest req);

	/**
	 * 用户消息查询
	 */
	DataPageComonResponse<CuCustomerMessage> selectCustomerMessage(TokenTypePageRequest request, HttpServletRequest req);

	/**
	 * 查询公告
	 */
	DataPageComonResponse<SysNotice> selectSysNotice(TokenTypePageRequest request, HttpServletRequest req);

	/**
	 * 查询banner
	 */
	DataItemResponse<List<BaseImgResponse>> selectSysBanner(TokenTypeRequest request, HttpServletRequest req);

	/**
	 * 会员签到
	 */
	DataResponse doCustomerSign(TokenRequest request, HttpServletRequest req);

	DataResponse doPushCustomerFeedback(CustomerFeedbackRequest request, HttpServletRequest req);

	/**
	 * 意见反馈列表
	 */
	DataItemResponse<List<CuCustomerFeedback>> getCustomerFeedbackList(TokenRequest request, HttpServletRequest req);

	/**
	 * 意见反馈类型
	 */
	DataItemResponse<List<AppOption>> getFeedbackType(TokenRequest request, HttpServletRequest req);

	/**
	 * 获取抽奖配置
	 */
	DataItemResponse<Map<String, Object>> doRewardTurntable(TokenRequest request, HttpServletRequest req);

	/**
	 * 获取中奖数
	 */
	DataItemResponse<Integer> doRewardNum(TokenRequest request, HttpServletRequest req);
	
	/**
	 * 中奖列表
	 */
	DataItemResponse<Map<String, Object>> doGetRewardList(TokenRequest request, HttpServletRequest req);

	/**
	 * 我的中奖记录
	 */
	DataPageComonResponse<SysTurntableReward> getCustomerReward(TokenPageRequest request, HttpServletRequest req);

	/**
	 * 消息详情
	 */
	DataItemResponse<CuCustomerMessage> selectCustomerMessageInfo(TokenIdRequest request, HttpServletRequest req);

	/**
	 * 删除/批量删除消息
	 */
	DataResponse delCustomerMessageByIds(TokenIdsRequest request, HttpServletRequest req);

	/**
	 * 一键已读
	 */
	DataResponse messagetoRead(TokenRequest request, HttpServletRequest req);

	/**
	 * 未读信息条数
	 */
	DataItemResponse<JSONObject> selectUnReadNum(TokenTypeRequest request, HttpServletRequest req);

}
