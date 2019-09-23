package com.basics.mall.controller.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basics.common.DataItemResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenIdRequest;
import com.basics.common.TokenIdsRequest;
import com.basics.common.TokenRequest;
import com.basics.mall.controller.request.CustomerCarRequest;
import com.basics.mall.controller.response.CustomerCarResponsePlus;
import com.basics.mall.service.MallCustomerCarApiService;

@RestController
@RequestMapping("/api/customerCar/")
public class MallCustomerCarApiController implements ApplicationContextAware {

	@SuppressWarnings("unused")
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private MallCustomerCarApiService mallCustomerCarApiService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	/**
	 * 添加购物车
	 */
	@RequestMapping("addCustomerCar")
	public DataItemResponse<Integer> addCustomerCar(@Valid CustomerCarRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<Integer> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallCustomerCarApiService.doAddCustomerCard(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 编辑购物车
	 */
	@RequestMapping("modifyCustomerCar")
	public DataResponse modifyCustomerCar(@Valid CustomerCarRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallCustomerCarApiService.doModifyCustomerCard(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 删除购物车
	 */
	@RequestMapping("delCustomerCar")
	public DataItemResponse<Long> delCustomerCar(@Valid TokenIdRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<Long> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallCustomerCarApiService.doDelCustomerCard(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 批量删除购物车
	 */
	@RequestMapping("delMoreCustomerCar")
	public DataItemResponse<Long> delMoreCustomerCar(@Valid TokenIdsRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<Long> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallCustomerCarApiService.doDelMoreCustomerCar(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 我的购物车
	 */
	@RequestMapping("selectCustomerCar")
	public DataItemResponse<CustomerCarResponsePlus> selectCustomerCar(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<CustomerCarResponsePlus> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallCustomerCarApiService.doSelectCustomerCar(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 查询购物车数量接口
	 */
	@RequestMapping("selectCustomerCarNum")
	public DataItemResponse<Integer> selectCustomerCarNum(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<Integer> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallCustomerCarApiService.doSelectCustomerCarNum(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

}
