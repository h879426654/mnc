package com.basics.cu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.basics.app.entity.AppCode;
import com.basics.app.entity.AppLog;
import com.basics.app.entity.AppOption;
import com.basics.app.entity.AppToken;
import com.basics.common.*;
import com.basics.cu.controller.request.*;
import com.basics.cu.dao.CuReatil1Dao;
import com.basics.cu.entity.*;
import com.basics.cu.service.CommonApiService;
import com.basics.cu.service.CuReatil1Service;
import com.basics.support.*;
import com.basics.support.email.EmailUtil;
import com.basics.support.sms.SmsWhUtil;
import com.basics.sys.entity.SysCountry;
import com.basics.sys.entity.SysCustomerLevel;
import com.basics.sys.entity.SysVersion;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class CuReatil1ServiceImpl extends BaseApiService implements CuReatil1Service {
    @Autowired
    private CuReatil1Dao cuReatil1Dao;

    @Override
    public CuReatil1 searchByCustomerId(String customerId) {
        return cuReatil1Dao.searchMoneyAndIndirectMoney(customerId);
    }
}
