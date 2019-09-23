package com.basics.mall.service;

import java.util.List;

import com.basics.common.DataItemResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenIdRequest;
import com.basics.common.TokenIdsRequest;
import com.basics.common.TokenRequest;
import com.basics.mall.controller.request.CustomerCarRequest;
import com.basics.mall.controller.response.CustomerCarResponse;
import com.basics.mall.controller.response.CustomerCarResponsePlus;

import javax.servlet.http.HttpServletRequest;

public interface MallCustomerCarApiService {

	//添加购物车
	DataItemResponse<Integer> doAddCustomerCard(CustomerCarRequest request, HttpServletRequest req);

	//编辑购物车
	DataResponse doModifyCustomerCard(CustomerCarRequest request, HttpServletRequest req);

	//删除购物车
	DataItemResponse<Long> doDelCustomerCard(TokenIdRequest request, HttpServletRequest req);

	//批量删除购物车
	DataItemResponse<Long> doDelMoreCustomerCar(TokenIdsRequest request, HttpServletRequest req);

	//我的购物车
	DataItemResponse<CustomerCarResponsePlus> doSelectCustomerCar(TokenRequest request, HttpServletRequest req);
	
	//查询购物车数量接口
	DataItemResponse<Integer> doSelectCustomerCarNum(TokenRequest request, HttpServletRequest req);


}
