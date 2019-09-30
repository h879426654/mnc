package com.basics.broswer.service;

import com.basics.cu.entity.CuConsume;
import com.basics.cu.entity.CuCustomerAccount;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.mall.entity.MallShop;
import com.basics.or.entity.OrOrder;
import com.basics.support.FormResultSupport;
import com.basics.tr.entity.TrTradeCoin;
import com.basics.tr.entity.TrTradeConvert;
import com.basics.tr.entity.TrTradeMoney;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CuConcerService {

	List<CuConsume> getConSumeList();

}
