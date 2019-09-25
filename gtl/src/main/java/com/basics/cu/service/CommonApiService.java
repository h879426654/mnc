package com.basics.cu.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.basics.common.DataItemResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenResponse;
import com.basics.cu.controller.request.*;
import com.basics.mall.entity.MallAdvertHot;
import com.basics.sys.entity.SysCountry;
import com.basics.sys.entity.SysVersion;

public interface CommonApiService {

	// 发送短信验证码
	DataResponse doPushSms(SmsRequest request, HttpServletRequest req);

	// 注册
	DataItemResponse<Integer> doRegiestUser(RegisterUserRequest request, HttpServletRequest req);

	// 登录
	DataItemResponse<TokenResponse> doLogin(LoginRequest request, HttpServletRequest req);

	// 忘记密码
	DataResponse doForgetPass(ForGetPassRequest request, HttpServletRequest req);

	// 修改登录密码
	DataResponse doModifyLoginPass(ModifyPassRequest request, HttpServletRequest req);

	// 修改支付密码
	DataResponse doModifyPayPass(ModifyPassRequest request, HttpServletRequest req);

	// 修改用户头像
	DataResponse doModifyCustomerHead(ModifyCustomerHeadRequest request, HttpServletRequest req);

	// 获取app版本
	DataItemResponse<SysVersion> getAppVersion(Integer type);

	DataResponse doFlagSpecial();

	/**
	 * 获取国家
	 */
	DataItemResponse<List<SysCountry>> getCountry();

	DataResponse doEmailCode(EmailRequest request, HttpServletRequest req);

	DataResponse doInternationalSms(SmsRequest request, HttpServletRequest req);

	DataItemResponse<Map<String, String>> getIndexUrl();
    //修改手机号
	String modifyPhone(String phone);
    //判断验证码
	String judgeCode(String phone, String Code, String type, String orderPhone);
    //新手机号
	String newPhone(String phone);

	List<MallAdvertHot> getHot();
}
