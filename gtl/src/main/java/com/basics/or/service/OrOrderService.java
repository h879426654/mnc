package com.basics.or.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.basics.or.entity.OrOrder;
import com.basics.support.GenericService;

public interface OrOrderService extends GenericService<OrOrder> {

	/**
	 * 商品列表
	 */
	void selectProductListByOrderId(String gridPager, HttpServletRequest request, HttpServletResponse response);

}
