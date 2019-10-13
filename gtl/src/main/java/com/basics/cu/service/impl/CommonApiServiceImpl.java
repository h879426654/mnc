package com.basics.cu.service.impl;

import java.math.BigDecimal;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.basics.cu.controller.request.*;
import com.basics.cu.entity.*;
import com.basics.gty.entity.GtyWallet;
import com.basics.mall.dao.MallAdvertHotDao;
import com.basics.mall.entity.MallAdvertHot;
import com.basics.mall.entity.MallShopAdvert;
import com.basics.support.*;
import javassist.bytecode.stackmap.BasicBlock;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.basics.app.entity.AppCode;
import com.basics.app.entity.AppLog;
import com.basics.app.entity.AppOption;
import com.basics.app.entity.AppToken;
import com.basics.common.BaseApiService;
import com.basics.common.Constant;
import com.basics.common.DataItemResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenResponse;
import com.basics.cu.service.CommonApiService;
import com.basics.support.email.EmailUtil;
import com.basics.support.sms.SmsWhUtil;
import com.basics.sys.entity.SysCountry;
import com.basics.sys.entity.SysCustomerLevel;
import com.basics.sys.entity.SysVersion;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommonApiServiceImpl extends BaseApiService implements CommonApiService {

    /**
     * 发送短信验证码
     */
    @Override
    public DataResponse doPushSms(SmsRequest request, HttpServletRequest req) {
        DataResponse response = new DataResponse();
        if (Constant.SMS_TYPE_1 == request.getSmsType().intValue()) {
            // 判断当前手机号是否已注册
            CuCustomerLogin customer = cuCustomerLoginDao.queryOne(new QueryFilterBuilder().put("customerPhone", request.getPhone()).build());
            if (null != customer) {
                response.onHandleFail(getMessage(req, "impl.doPushSms.phone.used"));
                return response;
            }
        }
        // 判断号码发送短信超额
        AppLog log = appLogDao.queryOne(new QueryFilterBuilder().put("logRemark", request.getPhone()).orderBy("appLog.LOG_CREATE_DATE DESC").build());
        if (null != log && log.getLogCreateDate().getTime() + 1000 * 60 > System.currentTimeMillis()) {
            response.onHandleFail(getMessage(req, "impl.doPushSms.time.in"));
            return response;
        }
        List<AppLog> list = appLogDao.query(new QueryFilterBuilder().put("nowToOneHour", new Date()).put("logRemark", request.getPhone()).build());
        if (CollectionUtils.isNotEmpty(list) && list.size() >= 10) {
            response.onHandleFail(getMessage(req, "impl.doPushSms.sms.frequently"));
            return response;
        }
        List<AppLog> list2 = appLogDao.query(new QueryFilterBuilder().put("nowToOneDay", new Date()).put("logRemark", request.getPhone()).build());
        if (CollectionUtils.isNotEmpty(list2) && list2.size() >= 20) {
            response.onHandleFail(getMessage(req, "impl.doPushSms.sms.frequently"));
            return response;
        }
        AppCode appCode = appCodeDao.queryOne(
                new QueryFilterBuilder().put("codeMobile", request.getPhone()).put("codeState", Constant.STATE_YES).put("codeType", request.getSmsType()).orderBy("CODE_CREATE_TIME DESC").build());
        if (null != appCode && appCode.getCodeCreateTime().getTime() + 1000 * 60 > System.currentTimeMillis()) {
            response.onHandleFail(getMessage(req, "impl.doPushSms.time.in"));
        } else {
            String code = MD5Util.random(6);
            appLogDao.save(new AppLog().logName("短信发送参数").logRemark(request.getPhone()).logContext("手机号：" + request.getPhone() + "，验证码：" + code).logCreateDate(new Date()));
            try {
                String result = SmsWhUtil.send(request.getPhone(), code, SmsWhUtil.SMS_MODE);
                if (StringUtils.isNotBlank(result)) {
                    appLogDao.save(new AppLog().logName("短信发送结果").logRemark(request.getPhone()).logContext(/* result */ code).logCreateDate(new Date()));
                    if (null != JSONObject.parseObject(result) && null != JSONObject.parseObject(result).getString("status") && "0".equals(JSONObject.parseObject(result).getString("status"))) {
                        appCode = new AppCode();
                        appCode.setCodeType(request.getSmsType());
                        appCode.setCodeCode(code);
                        appCode.setCodeCreateTime(new Date());
                        appCode.setCodeMobile(request.getPhone());
                        appCode.setCodeState(Constant.STATE_YES);
                        appCodeDao.save(appCode);
                        response.onHandleSuccess(getMessage(req, "impl.doPushSms.sms.success"));
                        return response;
                    } else {
                        response.onHandleFail(getMessage(req, "impl.doPushSms.sms.fail"));
                        return response;
                    }
                } else {
                    response.onHandleFail(getMessage(req, "impl.doPushSms.sms.fail"));
                    return response;
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.onHandleFail(getMessage(req, "impl.doPushSms.sms.fail"));
                return response;
            }
        }
        return response;
    }

    /**
     * 发送国际短信验证码
     */
    @Override
    public DataResponse doInternationalSms(SmsRequest request, HttpServletRequest req) {
        DataResponse response = new DataResponse();
        if (Constant.SMS_TYPE_1 == request.getSmsType().intValue()) {
            // 判断当前手机号是否已注册
            CuCustomerLogin customer = cuCustomerLoginDao.queryOne(new QueryFilterBuilder().put("customerPhone", request.getPhone()).build());
            if (null != customer) {
                response.onHandleFail(getMessage(req, "impl.doPushSms.phone.used"));
                return response;
            }
        }
        // 判断号码发送短信超额
        AppLog log = appLogDao.queryOne(new QueryFilterBuilder().put("logRemark", request.getPhone()).orderBy("appLog.LOG_CREATE_DATE DESC").build());
        if (null != log && log.getLogCreateDate().getTime() + 1000 * 60 > System.currentTimeMillis()) {
            response.onHandleFail(getMessage(req, "impl.doPushSms.time.in"));
            return response;
        }
        List<AppLog> list = appLogDao.query(new QueryFilterBuilder().put("nowToOneHour", new Date()).put("logRemark", request.getPhone()).build());
        if (CollectionUtils.isNotEmpty(list) && list.size() >= 10) {
            response.onHandleFail(getMessage(req, "impl.doPushSms.sms.frequently"));
            return response;
        }
        List<AppLog> list2 = appLogDao.query(new QueryFilterBuilder().put("nowToOneDay", new Date()).put("logRemark", request.getPhone()).build());
        if (CollectionUtils.isNotEmpty(list2) && list2.size() >= 20) {
            response.onHandleFail(getMessage(req, "impl.doPushSms.sms.frequently"));
            return response;
        }
        AppCode appCode = appCodeDao.queryOne(
                new QueryFilterBuilder().put("codeMobile", request.getPhone()).put("codeState", Constant.STATE_YES).put("codeType", request.getSmsType()).orderBy("CODE_CREATE_TIME DESC").build());
        if (null != appCode && appCode.getCodeCreateTime().getTime() + 1000 * 60 > System.currentTimeMillis()) {
            response.onHandleFail(getMessage(req, "impl.doPushSms.time.in"));
            return response;
        }
        String code = MD5Util.random(6);
        appLogDao.save(new AppLog().logName("短信发送参数").logRemark(request.getPhone()).logContext("手机号：" + request.getPhone() + "，验证码：" + code).logCreateDate(new Date()));
        try {
            String result = SmsWhUtil.sendInternational(request.getPhone(), code, SmsWhUtil.SMS_MODE);
            if (StringUtils.isBlank(result)) {
                response.onHandleFail(getMessage(req, "impl.doPushSms.sms.fail"));
                return response;
            }
            appLogDao.save(new AppLog().logName("短信发送结果").logRemark(request.getPhone()).logContext(result).logCreateDate(new Date()));
            if (null != JSONObject.parseObject(result) && null != JSONObject.parseObject(result).getString("code") && "0".equals(JSONObject.parseObject(result).getString("code"))) {
                appCode = new AppCode();
                appCode.codeType(request.getSmsType()).codeCode(code).codeCreateTime(new Date()).codeState(Constant.STATE_YES).codeMobile(request.getPhone());
                appCodeDao.save(appCode);
                response.onHandleSuccess(getMessage(req, "impl.doPushSms.sms.success"));
                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.onHandleFail(getMessage(req, "impl.doPushSms.sms.fail"));
        return response;
    }

    @Override
    public DataResponse doEmailCode(EmailRequest request, HttpServletRequest req) {
        DataResponse response = new DataResponse();
        if (Constant.SMS_TYPE_1 == request.getEmailType().intValue()) {
            // 判断当前手机号是否已注册
            CuCustomerLogin customer = cuCustomerLoginDao.queryOne(new QueryFilterBuilder().put("customerEmail", request.getEmail()).build());
            if (null != customer) {
                response.onHandleFail(getMessage(req, "impl.doPushSms.phone.used"));
                return response;
            }
        }
        // 判断号码发送短信超额
        AppLog log = appLogDao.queryOne(new QueryFilterBuilder().put("logRemark", request.getEmail()).orderBy("appLog.LOG_CREATE_DATE DESC").build());
        if (null != log && log.getLogCreateDate().getTime() + 1000 * 60 > System.currentTimeMillis()) {
            response.onHandleFail(getMessage(req, "impl.doPushSms.time.in"));
            return response;
        }
        List<AppLog> list = appLogDao.query(new QueryFilterBuilder().put("nowToOneHour", new Date()).put("logRemark", request.getEmail()).build());
        if (CollectionUtils.isNotEmpty(list) && list.size() >= 10) {
            response.onHandleFail(getMessage(req, "impl.doPushSms.sms.frequently"));
            return response;
        }
        List<AppLog> list2 = appLogDao.query(new QueryFilterBuilder().put("nowToOneDay", new Date()).put("logRemark", request.getEmail()).build());
        if (CollectionUtils.isNotEmpty(list2) && list2.size() >= 20) {
            response.onHandleFail(getMessage(req, "impl.doPushSms.sms.frequently"));
            return response;
        }
        AppCode appCode = appCodeDao.queryOne(
                new QueryFilterBuilder().put("codeMobile", request.getEmail()).put("codeState", Constant.STATE_YES).put("codeType", request.getEmailType()).orderBy("CODE_CREATE_TIME DESC").build());
        if (null != appCode && appCode.getCodeCreateTime().getTime() + 1000 * 60 > System.currentTimeMillis()) {
            response.onHandleFail(getMessage(req, "impl.doPushSms.time.in"));
        } else {
            String code = MD5Util.random(6);
            appLogDao.save(new AppLog().logName("邮件发送参数").logRemark(request.getEmail()).logContext("邮箱：" + request.getEmail() + "，验证码：" + code).logCreateDate(new Date()));
            try {
                EmailUtil.sendTextEmail(request.getEmail(), code);
                appCode = new AppCode();
                appCode.setCodeType(request.getEmailType());
                appCode.setCodeCode(code);
                appCode.setCodeCreateTime(new Date());
                appCode.setCodeMobile(request.getEmail());
                appCode.setCodeState(Constant.STATE_YES);
                appCodeDao.save(appCode);
                response.onHandleSuccess(getMessage(req, "impl.doPushSms.sms.success"));
                return response;
            } catch (Exception e) {
                e.printStackTrace();
                response.onHandleFail(getMessage(req, "impl.doPushSms.sms.fail"));
                return response;
            }
        }
        return response;
    }

    /**
     * 注册
     */
    @Override
    public synchronized DataItemResponse<Integer> doRegiestUser(RegisterUserRequest request, HttpServletRequest req) {
        DataItemResponse<Integer> response = new DataItemResponse<Integer>();
        // 判断当前手机号是否已注册
        CuCustomerLogin customer = cuCustomerLoginDao.queryOne(new QueryFilterBuilder().put("customerPhone", request.getPhone()).build());
        if (null != customer) {
            // response.onHandleFail("该手机号已注冊");
            response.onHandleFail(getMessage(req, "impl.doPushSms.phone.used"));
            return response;
        }
        // 短信验证码
        String msg = checkSmsCode(request.getPhone(), Constant.SMS_TYPE_1, request.getCode(), "e84a151ced294a04b45a8f2086fc7157", req);
        if (StringUtils.isNotBlank(msg)) {
            response.onHandleFail(msg);
            return response;
        }
        // 父级
        CuCustomerInfo parent = null;
        if (Constant.STATE_YES == request.getType().intValue()) {
            parent = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("customerPhone", request.getParentPhone()).build());
        } else {
            parent = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("customerNumber", request.getParentPhone()).build());
        }
        if (null == parent) {
            response.onHandleFail(getMessage(req, "impl.doRegisterUser.referee.nonexistent"));
            return response;
        }
        String[] strarr = PasswordEncoder.getEncPasswordAndSalt(request.getPassword());
        // 用户登陆信息表
        customer = new CuCustomerLogin();
        customer.customerPassword(strarr[0]).passSalt(strarr[1]).createTime(new Date()).customerPhone(request.getPhone());
        if (StringUtils.isNotBlank(request.getEmail())) {
            //scustomer.setCustomerEmail(request.getEmail());
        }
        cuCustomerLoginDao.save(customer);
        // 用户信息表
        CuCustomerInfo info = new CuCustomerInfo();
        String curdateString = new Long(new Date().getTime()).toString();
        String number = new Long(GuidUtils.generateSimpleLongGuid()).toString().substring(0, 5) + curdateString.substring(curdateString.length() - 3, curdateString.length());
        info.id(customer.getId()).customerName(null == request.getUserName() ? "mnc" + new Long(GuidUtils.generateSimpleLongGuid()).toString().substring(0, 7) : request.getUserName())
                .registerTime(new Date()).customerNumber(number).customerPhone(request.getPhone()).flagSpecial(parent.getFlagSpecial()).countryId(request.getCountry());
        if (StringUtils.isNotBlank(request.getEmail())) {
            info.setCustomerEmail(request.getEmail());
        }
        // 初始化账户表
        CuCustomerAccount account = new CuCustomerAccount();
        account.id(customer.getId()).customerPurse(GuidUtils.generateSimpleGuid().toUpperCase()).customerPayPass(MD5Util.GetMD5Code(null == request.getPayPass() ? "123456" : request.getPayPass()));

        // 保存
        cuCustomerInfoDao.save(info);
        cuCustomerAccountDao.save(account);
        GtyWallet gtyWallet = new GtyWallet();
        gtyWallet.setUserId(info.getId());
        gtyWallet.setWalletAddress(UUID.randomUUID().toString().replace("-",""));
        gtyWalletDao.insert(gtyWallet);
        insertReatil(info.getId(), parent.getId());
        response.onHandleSuccess();
        return response;
    }

    private void insertReatil(String customerId, String parentCustomerId) {
        //通过邀请人id,查询是不是迁移数据如果是 将用户插入到表中
        CuReatil1 oldReatil = cuReatil1Dao.queryOne(new QueryFilterBuilder().put("customerId", parentCustomerId).build());
        if (null == oldReatil) {
            CuReatil1 oldReatil1 = new CuReatil1();
            oldReatil1.setId(UUID.randomUUID().toString().replace("-", ""));
            oldReatil1.setCustomerId(parentCustomerId);
            oldReatil1.setMoney(BigDecimal.ZERO);
            oldReatil1.setIndirectMoney(BigDecimal.ZERO);
            cuReatil1Dao.insert(oldReatil1);
        }

        //将被邀请人存入表中
        CuReatil1 newReatil = new CuReatil1();
        newReatil.setId(UUID.randomUUID().toString().replace("-", ""));
        newReatil.setCustomerId(customerId);
        newReatil.setMoney(BigDecimal.ZERO);
        newReatil.setIndirectMoney(BigDecimal.ZERO);
        cuReatil1Dao.insert(newReatil);

        //存入关系表中
        CuReatil2 cuReatil2 = new CuReatil2();
        cuReatil2.setId(UUID.randomUUID().toString().replace("-", ""));
        cuReatil2.setCustomerId(parentCustomerId);
        cuReatil2.setCustomerIdSecond(customerId);
        cuReatil2Dao.insert(cuReatil2);


    }
    /**
     * 登录
     */
    @Override
    public DataItemResponse<TokenResponse> doLogin(LoginRequest request, HttpServletRequest req) {
        DataItemResponse<TokenResponse> response = new DataItemResponse<TokenResponse>();
        CuCustomerLogin user = null;
        if (request.getPhone().contains("@")) {
            user = cuCustomerLoginDao.queryOne(new QueryFilterBuilder().put("customerEmail", request.getPhone()).build());
        } else {
            user = cuCustomerLoginDao.queryOne(new QueryFilterBuilder().put("customerPhone", request.getPhone()).build());
        }
        if (null == user) {
            response.onHandleFail(getMessage(req, "impl.doLogin.user.nonexistent"));
            return response;
        }
        if (!checkCustomerStatus(user)) {
            response.onHandleFail(getMessage(req, "impl.doLogin.user.freeze"));
            return response;
        }
        if (!PasswordEncoder.checkPassword(user.getPassSalt(), user.getCustomerPassword(), request.getPassword())) {
            response.onHandleFail(getMessage(req, "impl.doLogin.password.error"));
            return response;
        }
        TokenResponse data = new TokenResponse();
        AppToken appToken = appTokenDao.queryOne(new QueryFilterBuilder().put("userId", user.getId()).build());
        appTokenDao.delete(appToken);
        Date start = new Date();
        appToken = new AppToken();
        appToken.setUserId(user.getId());
        appToken.setTokenCreateTime(start);
        appToken.setTokenExpirationTime(DateUtils.addDateMinut(start, 24));// 一个小时
        appTokenDao.save(appToken);
        user.setLastLoginTime(start);
        cuCustomerLoginDao.save(user);
        data.setToken(appToken.getId());
        response.setItem(data);
        response.onHandleSuccess();
        GtyWallet gtyWallet = gtyWalletDao.queryOne(new QueryFilterBuilder().put("userId", user.getId()).build());
        if (null == gtyWallet.getWalletAddress() || gtyWallet.getWalletAddress().isEmpty()){
            gtyWallet.setWalletAddress(UUID.randomUUID().toString().replace("-",""));
            gtyWalletDao.update(gtyWallet);
        }
        return response;
    }

    /**
     * 忘记密码
     */
    @Override
    public DataResponse doForgetPass(ForGetPassRequest request, HttpServletRequest req) {
        DataResponse response = new DataResponse();
        AppCode appCode = appCodeDao.queryOne(new QueryFilterBuilder().put("codeMobile", request.getPhone()).orderBy("code_create_time desc").limit(1).build());
        Date codeDate = appCode.getCodeCreateTime();
        Date date = new Date();
        Date afterDate = new Date(codeDate.getTime()+300000);
        if (null == request.getCode()) {
            response.onHandleFail("验证码不能为空");
            return response;
        } else if (!request.getPassword().equals(request.getRepeatPassword())) {
            response.onHandleFail("两次输入的密码不相符");
            return response;
        } else if (!appCode.getCodeCode().equals(request.getCode())) {
            response.onHandleFail("验证码错误");
            return response;
        } else if (afterDate.compareTo(date) < 0) {
            response.onHandleFail("验证码失效");
            return response;
        }
        CuCustomerLogin cuCustomerLogin = cuCustomerLoginDao.queryOne(new QueryFilterBuilder().put("customerPhone", request.getPhone()).build());
        String[] strarr = PasswordEncoder.getEncPasswordAndSalt(request.getPassword());
        cuCustomerLogin.customerPassword(strarr[0]).passSalt(strarr[1]);
        int a = cuCustomerLoginDao.update(cuCustomerLogin);
        response.setMsg("");
        return response;
    }

    /**
     * 修改登录密码
     */
    @Override
    public DataResponse doModifyLoginPass(ModifyPassRequest request, HttpServletRequest req) {
        DataResponse response = new DataResponse();
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
        if (!request.getPhone().equals(user.getCustomerPhone())) {
            response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.different"));
            return response;
        }
        // 验证码
        CuCustomerInfo info = cuCustomerInfoDao.get(user.getId());
        String msg = checkSmsCode(user.getCustomerPhone(), Constant.SMS_TYPE_3, request.getCode(), info.getCountryId(), req);
        if (StringUtils.isNotBlank(msg)) {
            response.onHandleFail(msg);
            return response;
        }
        if (!PasswordEncoder.checkPassword(user.getPassSalt(), user.getCustomerPassword(), request.getOldPassword())) {
            response.onHandleFail(getMessage(req, "impl.doLogin.password.error"));
            return response;
        }
        String[] strarr = PasswordEncoder.getEncPasswordAndSalt(request.getNewPassword());
        user.customerPassword(strarr[0]).passSalt(strarr[1]);
        cuCustomerLoginDao.save(user);

        AppToken appToken = appTokenDao.get(request.getToken());
        appToken.setTokenExpirationTime(new Date());
        appTokenDao.save(appToken);

        response.onHandleSuccess();
        return response;
    }

    /**
     * 修改支付密码
     */
    @Override
    public DataResponse doModifyPayPass(ModifyPassRequest request, HttpServletRequest req) {
        DataResponse response = new DataResponse();
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
        if (!request.getPhone().equals(user.getCustomerPhone())) {
            response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.different"));
            return response;
        }
        // 验证码
        CuCustomerInfo info = cuCustomerInfoDao.get(user.getId());
        String msg = checkSmsCode(user.getCustomerPhone(), Constant.SMS_TYPE_4, request.getCode(), info.getCountryId(), req);
        if (StringUtils.isNotBlank(msg)) {
            response.onHandleFail(msg);
            return response;
        }
        CuCustomerAccount account = cuCustomerAccountDao.get(user.getId());
        if (null == account) {
            response.onHandleFail(getMessage(req, "impl.doLogin.user.nonexistent"));
            return response;
        }
        account.customerPayPass(MD5Util.GetMD5Code(request.getNewPassword()));
        cuCustomerAccountDao.save(account);
        response.onHandleSuccess();
        return response;
    }

    /**
     * 修改用户头像
     */
    @Override
    public DataResponse doModifyCustomerHead(ModifyCustomerHeadRequest request, HttpServletRequest req) {
        DataResponse response = new DataResponse();
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
        CommonSupport.checkNotNull(info, "用户信息错误");
        info.setCustomerHead(request.getHeadImg());
        cuCustomerInfoDao.save(info);
        response.onHandleSuccess();
        return response;
    }

    /**
     * 获取app版本
     */
    @Override
    public DataItemResponse<SysVersion> getAppVersion(Integer type) {
        DataItemResponse<SysVersion> response = new DataItemResponse<>();
        if (null == type) {
            response.onHandleFail("type不能为空");
            return response;
        }
        SysVersion version = sysVersionDao.queryOne(new QueryFilterBuilder().put("vaesionType", type).orderBy("sysVersion.CREATE_TIME DESC").build());
        if (null == version) {
            response.onHandleFail("APP版本错误");
            return response;
        }
        response.setItem(version);
        return response;
    }

    @Override
    public DataResponse doFlagSpecial() {
        DataResponse response = new DataResponse();
        String phone = "13104513591";
        CuCustomerInfo customer = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("customerPhone", phone).build());
        if (null != customer) {
            customer.setFlagSpecial(Constant.STATE_YES);
            cuCustomerInfoDao.save(customer);
            customerTeam(customer.getId());
        }

        /*
         * Map<String, Object> map = new HashMap<>(); List<CuCustomerCount> list =
         * cuCustomerCountDao.query(new QueryFilterBuilder().build()); for
         * (CuCustomerCount count : list) { map.put("refereeId", count.getId()); long l
         * = cuCustomerRefereeDao.count(map); if (0 < l) { int team =
         * sumCustomerTeam(count.getId(), 1); count.setSalfNum(new Long(l).intValue());
         * count.setTeamNum(team); cuCustomerCountDao.save(count); } else {
         * count.setTeamNum(1); cuCustomerCountDao.save(count); } }
         */
        response.onHandleSuccess();
        return response;
    }

    /**
     * 用户
     */
    public void customerTeam(String customerId) {
        List<CuCustomerReferee> list = cuCustomerRefereeDao.query(new QueryFilterBuilder().put("refereeId", customerId).build());
        if (CollectionUtils.isNotEmpty(list)) {
            for (CuCustomerReferee referee : list) {
                CuCustomerInfo info = cuCustomerInfoDao.get(referee.getId());
                info.setFlagSpecial(Constant.STATE_YES);
                cuCustomerInfoDao.save(info);
                customerTeam(referee.getId());
            }
        }
    }

    /**
     * 获取国家
     */
    @Override
    public DataItemResponse<List<SysCountry>> getCountry() {
        DataItemResponse<List<SysCountry>> response = new DataItemResponse<>();
        response.setItem(sysCountryDao.query(new QueryFilterBuilder().orderBy("sysCountry.COUNTRY_SORT ASC").build()));
        response.onHandleSuccess();
        return response;
    }

    @Override
    public DataItemResponse<Map<String, String>> getIndexUrl() {
        DataItemResponse<Map<String, String>> response = new DataItemResponse<Map<String, String>>();
        AppOption coinUrl = appOptionDao.get(Constant.AMCOIN_URL);
        AppOption mlUrl = appOptionDao.get(Constant.ML_URL);
        Map<String, String> data = new HashMap<String, String>();
        if (null != coinUrl) {
            data.put(Constant.AMCOIN_URL, coinUrl.getCode());
        }
        if (null != mlUrl) {
            data.put(Constant.ML_URL, mlUrl.getCode());
        }
        response.setItem(data);
        response.onHandleSuccess();
        return response;
    }

    @Override
    public String modifyPhone(String phone) {
        if (phone.isEmpty()) {
            return "2";
        }
        String code = MD5Util.random(6);
        try {
            List<AppCode> list = appCodeDao.query(new QueryFilterBuilder().put("codeMobile", phone).orderBy("appCode.code_create_time desc").build());
            Date now = new Date();
            Date afterDate = new Date(list.get(0).getCodeCreateTime().getTime() + 60000);
            if (afterDate.compareTo(now) > 0) {
                return "4";
            }
            saveAppDate(phone,code);
        } catch (Exception e) {
            return "3";
        }

        return "1";
    }

    @Override
    public String judgeCode(String phone, String Code, String type, String orderPhone) {
        try{
            //获取
            List<AppCode> list = appCodeDao.query(new QueryFilterBuilder().put("codeMobile", phone).orderBy("appCode.code_create_time desc").build());
            Date now = new Date();
            Date afterDate = new Date(list.get(0).getCodeCreateTime().getTime() + 300000);
            if (afterDate.compareTo(now) < 0) {
                return "2";
            } else if (!Code.equals(list.get(0).getCodeCode())){
                return "1";
            } if (Code.equals(list.get(0).getCodeCode())) {
                if ("2".equals(type)) {
                    CuCustomerLogin cuCustomerLogin = cuCustomerLoginDao.queryOne(new QueryFilterBuilder().put("customerPhone", orderPhone).build());
                    CuCustomerInfo cuCustomerInfo = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("customerPhone", orderPhone).build());
                    cuCustomerLogin.setCustomerPhone(phone);
                    cuCustomerInfo.setCustomerPhone(phone);
                    cuCustomerLoginDao.update(cuCustomerLogin);
                    cuCustomerInfoDao.update(cuCustomerInfo);

                }
                return "0";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "3";
        }
        return null;
    }

    @Override
    public String newPhone(String phone) {
        if (phone.isEmpty()) {
            return "2";
        }
        CuCustomerLogin cuCustomerLogin = cuCustomerLoginDao.queryOne(new QueryFilterBuilder().put("customerPhone",phone).build());
        CuCustomerInfo cuCustomerInfo = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("customerPhone",phone).build());
        if (null != cuCustomerLogin && null != cuCustomerInfo) {
            return "4";
        }
        String code = MD5Util.random(6);
        try {
            List<AppCode> list = appCodeDao.query(new QueryFilterBuilder().put("codeMobile", phone).orderBy("appCode.code_create_time desc").build());
            Date now = new Date();
            Date afterDate = new Date(list.get(0).getCodeCreateTime().getTime() + 60000);
            if (afterDate.compareTo(now) > 0) {
                return "5";
            }
            saveAppDate(phone,code);
            return "1";
        } catch (Exception e) {
            return "3";
        }
    }

    @Override
    public List<MallShopAdvert> getHot() {
        List<MallShopAdvert> list = mallShopAdvertDao.query(new QueryFilterBuilder().put("hot",1).put("applyStatus","2").put("delFlag","0").build());
        for (MallShopAdvert mallShopAdvert : list) {
            Map map = new HashMap();
            map.put("shopId", mallShopAdvert.getId());
            map.put("state", "1");
            Long count = cuConsumeDao.count(map);
            mallShopAdvert.setCount(count.intValue());
        }
        return list;
    }

    private void saveAppDate(String phone, String code) throws Exception{
        String result = SmsWhUtil.send(phone, code, SmsWhUtil.SMS_MODE);
        AppCode appCode = new AppCode();
        appCode.setId(UUID.randomUUID().toString().replace("-",""));
        appCode.setCodeType(4);
        appCode.setCodeMobile(phone);
        appCode.setCodeCode(code);
        appCode.setCodeCreateTime(new Date());
        appCodeDao.save(appCode);
    }
}
