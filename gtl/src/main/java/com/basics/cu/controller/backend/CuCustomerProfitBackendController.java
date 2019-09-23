package com.basics.cu.controller.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.cu.entity.CuCustomerProfit;
import com.basics.cu.service.BaseAccountApiService;
import com.basics.support.QueryFilterBuilder;
@Controller
@RequestMapping("/backend/cu/cuCustomerProfit/")
public class CuCustomerProfitBackendController extends BaseBackendController<CuCustomerProfit> {
	
	@Autowired
	private BaseAccountApiService baseAccountApiService;
	
	/**
	 * 会员列表
	 */
	@RequestMapping("/selectCustomerProfitInfo")
	public void selectTradIng(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.orderBy("cuCustomerProfit.PROFIT_HAVED_TIME DESC");
		swgrid(gridPager, builder, this.service, "queryCustomerProfitInfo", "count", request, response);
	}

	/**
	 * 系统统计
	 */
	@RequestMapping("countSystemData")
	public void countSystemData(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		baseAccountApiService.countSystemData(gridPager, request, response);
	}
	
	
	
}
