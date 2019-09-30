package com.basics.broswer.service.impl;

import com.basics.app.shiro.AppUserUtils;
import com.basics.app.shiro.UserSupport;
import com.basics.broswer.service.CuConcerService;
import com.basics.common.BaseApiService;
import com.basics.common.Constant;
import com.basics.cu.entity.*;
import com.basics.cu.service.BaseAccountApiService;
import com.basics.mall.entity.MallShop;
import com.basics.or.entity.OrOrder;
import com.basics.support.*;
import com.basics.support.dlshouwen.Pager;
import com.basics.sys.entity.SysCustomerLevel;
import com.basics.tr.entity.TrTradeCoin;
import com.basics.tr.entity.TrTradeConvert;
import com.basics.tr.entity.TrTradeMoney;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class CuConceImplService extends BaseApiService implements CuConcerService {


	@Override
	public List<CuConsume> getConSumeList() {
		List<CuConsume> cuConsumes = cuConsumeDao.query(new QueryFilterBuilder().build());
		return cuConsumes;
	}
}
