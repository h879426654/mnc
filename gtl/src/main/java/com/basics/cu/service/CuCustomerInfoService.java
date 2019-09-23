package com.basics.cu.service;

import com.basics.cu.entity.CuCustomerCount;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.support.FormResultSupport;
import com.basics.support.GenericService;

public interface CuCustomerInfoService extends GenericService<CuCustomerInfo> {

	void updateCustomerStatus(CuCustomerInfo customerInfo);

	String banCustomerFlagTrade(String id);

	FormResultSupport updateCustomerInfo(CuCustomerInfo entity);

	FormResultSupport doSetLevel(CuCustomerCount entity);

}
