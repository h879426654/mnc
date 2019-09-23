package com.basics.mall.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basics.common.BaseApiService;
import com.basics.common.Constant;
import com.basics.common.DataItemResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenIdRequest;
import com.basics.common.TokenIdsRequest;
import com.basics.common.TokenRequest;
import com.basics.cu.entity.CuCustomerLogin;
import com.basics.mall.controller.request.CustomerCarRequest;
import com.basics.mall.controller.response.CustomerCarData;
import com.basics.mall.controller.response.CustomerCarResponse;
import com.basics.mall.controller.response.CustomerCarResponsePlus;
import com.basics.mall.entity.MallCustomerCar;
import com.basics.mall.entity.MallProduct;
import com.basics.mall.entity.MallProductKindCombination;
import com.basics.mall.service.MallCustomerCarApiService;
import com.basics.mall.vo.KindValueVo;
import com.basics.mall.vo.MainKindVo;
import com.basics.support.CommonSupport;
import com.basics.support.QueryFilterBuilder;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class MallCustomerCarApiServiceImpl extends BaseApiService implements MallCustomerCarApiService {

	/**
	 * 添加购物车
	 */
	@Override
	public DataItemResponse<Integer> doAddCustomerCard(CustomerCarRequest request, HttpServletRequest req) {
		DataItemResponse<Integer> response = new DataItemResponse<>();
		// 判断token是否存在
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
		if(null ==  request.getCombinationId()) {
			response.onHandleFail(getMessage(req, "mallCustomerCarApiServiceImpl.doAddCustomerCard.combination.empty"));
			return response;
		}
		if(Constant.STATE_NO >= request.getProductNum().intValue()) {
			response.onHandleFail(getMessage(req, "mallCustomerCarApiServiceImpl.doAddCustomerCard.num.illegal"));
			return response;
		}
		// 判断商品状态及库存
		MallProduct goods = mallProductDao.queryOne(new QueryFilterBuilder().put("id", request.getId()).put("flagDel", Constant.STATE_NO).put("productStatus", Constant.PRODUCT_STATUS_SALE).build());
		CommonSupport.checkNotNull(goods, getMessage(req, "mallApiServiceImpl.doSelectProductDetail.product.nonexistent"));
		if (goods.getProductStock().intValue() < request.getProductNum().intValue()) {
			response.onHandleFail(getMessage(req, "mallCustomerCarApiServiceImpl.doAddCustomerCard.stock.insufficient"));
			return response;
		}
		MallProductKindCombination combination = mallProductKindCombinationDao.get(request.getCombinationId());
		if (null == combination || combination.getCombinationStockNum().intValue() < request.getProductNum().intValue()) {
			response.onHandleFail(getMessage(req, "mallCustomerCarApiServiceImpl.doAddCustomerCard.stock.insufficient"));
			return response;
		}
		MallCustomerCar car = mallCustomerCarDao.queryOne(new QueryFilterBuilder().put("customerId", user.getId()).put("productId", request.getId()).put("combinationId", request.getCombinationId()).build());
		if (null != car) {
			car.setProductNum(car.getProductNum() + request.getProductNum());
		} else {
			car = new MallCustomerCar();
			car.setCustomerId(user.getId());
			car.setCombinationId(request.getCombinationId());
			car.setProductId(request.getId());
			car.setProductNum(request.getProductNum());
			car.setCreateTime(new Date());
		}
		mallCustomerCarDao.save(car);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customerId", user.getId());
		Long count = mallCustomerCarDao.count(paramMap);
		response.setItem(count.intValue());
		response.onHandleSuccess();
		return response;
	}

	@Override
	public DataResponse doModifyCustomerCard(CustomerCarRequest request, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		// 判断token是否存在
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
		if(Constant.STATE_NO >= request.getProductNum().intValue()) {
			response.onHandleFail(getMessage(req, "mallCustomerCarApiServiceImpl.doAddCustomerCard.num.illegal"));
			return response;
		}
		MallCustomerCar car = mallCustomerCarDao.queryOne(new QueryFilterBuilder().put("customerId", user.getId()).put("id", request.getId()).build());
		if (null == car) {
			response.onHandleFail(getMessage(req, "mallCustomerCarApiServiceImpl.doModifyCustomerCard.car.error"));
			return response;
		}
		MallProductKindCombination combination = mallProductKindCombinationDao.get(car.getCombinationId());
		// 判断商品状态及库存
		MallProduct product = mallProductDao.queryOne(new QueryFilterBuilder().put("id", car.getProductId()).put("flagDel", Constant.STATE_NO).put("productStatus", Constant.PRODUCT_STATUS_SALE).build());
		CommonSupport.checkNotNull(product, getMessage(req, "mallApiServiceImpl.doSelectProductDetail.product.nonexistent"));
		if (product.getProductStock().intValue() < request.getProductNum().intValue()) {
			response.onHandleFail(getMessage(req, "mallCustomerCarApiServiceImpl.doAddCustomerCard.stock.insufficient"));
			return response;
		}
		if (null == combination || combination.getCombinationStockNum().intValue() < request.getProductNum().intValue()) {
			response.onHandleFail(getMessage(req, "mallCustomerCarApiServiceImpl.doAddCustomerCard.stock.insufficient"));
			return response;
		}
		car.setProductNum(request.getProductNum());
		mallCustomerCarDao.save(car);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 删除购物车
	 */
	@Override
	public DataItemResponse<Long> doDelCustomerCard(TokenIdRequest request, HttpServletRequest req) {
		DataItemResponse<Long> response = new DataItemResponse<Long>();
		// 判断token是否存在
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
		MallCustomerCar car = mallCustomerCarDao.queryOne(new QueryFilterBuilder().put("customerId", user.getId()).put("id", request.getId()).build());
		if (null == car) {
			response.onHandleFail(getMessage(req, "mallCustomerCarApiServiceImpl.doModifyCustomerCard.car.error"));
			return response;
		}
		mallCustomerCarDao.delete(request.getId());
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customerId", user.getId());
		Long count = mallCustomerCarDao.count(paramMap);
		response.setItem(count);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 批量删除购物车
	 */
	@Override
	public DataItemResponse<Long> doDelMoreCustomerCar(TokenIdsRequest request, HttpServletRequest req) {
		DataItemResponse<Long> response = new DataItemResponse<Long>();
		// 判断token是否存在
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
		if (CollectionUtils.isNotEmpty(Arrays.asList(request.getIds()))) {
			for (String id : request.getIds()) {
				MallCustomerCar car = mallCustomerCarDao.queryOne(new QueryFilterBuilder().put("customerId", user.getId()).put("id", id).build());
				if (null == car) {
					response.onHandleFail(getMessage(req, "mallCustomerCarApiServiceImpl.doModifyCustomerCard.car.error"));
					return response;
				}
				mallCustomerCarDao.delete(id);
			}
		}
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customerId", user.getId());
		Long count = mallCustomerCarDao.count(paramMap);
		response.setItem(count);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 我的购物车
	 */
	@Override
	public DataItemResponse<CustomerCarResponsePlus> doSelectCustomerCar(TokenRequest request, HttpServletRequest req) {
		DataItemResponse<CustomerCarResponsePlus> response = new DataItemResponse<CustomerCarResponsePlus>();
		// 判断token是否存在
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
		CustomerCarResponsePlus customerCarResponsePlus = new CustomerCarResponsePlus();
		List<CustomerCarResponse> businessList = mallCustomerCarDao.queryExtend(new QueryFilterBuilder().put("customerId", user.getId()).build(), "selectCustomerCarGroupByShop");
		List<CustomerCarData> customerCarList = mallCustomerCarDao.queryExtend(new QueryFilterBuilder().put("customerId", user.getId()).put("productStatus",2).build(), "selectCustomerCar");
		List<CustomerCarData> expireProduct = mallCustomerCarDao.queryExtend(new QueryFilterBuilder().put("customerId", user.getId()).put("productStatus",3).build(), "selectCustomerCar");
		if (null != businessList && CollectionUtils.isNotEmpty(businessList) && null != customerCarList && CollectionUtils.isNotEmpty(customerCarList)) {
			for (CustomerCarResponse business : businessList) {
				List<CustomerCarData> carList = new ArrayList<CustomerCarData>();
				for (CustomerCarData customerCar : customerCarList) {
					if (business.getShopId().equals(customerCar.getShopId())) {
						extracted(carList, customerCar);
					}
				}
				business.setCarList(carList);
			}
		}
		customerCarResponsePlus.setList(businessList);
		List<CustomerCarData> carList = new ArrayList<CustomerCarData>();
		if(CollectionUtils.isNotEmpty(expireProduct)){
			for (CustomerCarData customerCar : expireProduct) {
					extracted(carList, customerCar);
			}
		}
		customerCarResponsePlus.setExpireProduct(carList);
		response.setItem(customerCarResponsePlus);
		response.onHandleSuccess();
		return response;
	}

	private void extracted(List<CustomerCarData> carList, CustomerCarData customerCar) {
		MallProductKindCombination combination = mallProductKindCombinationDao.get(customerCar.getCombinationId());
		String[] strings = combination.getCombination().split("/");
		// 设置规格列表
		List<MainKindVo> kindVos = mallProductKindContrastDao.findProductMainKinds(customerCar.getProductId());
		if (CollectionUtils.isNotEmpty(kindVos)) {
			for (Integer i = 0; i < kindVos.size(); i++) {
				MainKindVo kindVo = kindVos.get(i);
				kindVo.setKindValues(mallProductKindContrastDao.findProductKindValue(customerCar.getProductId(), kindVo.getKindId()));
				Iterator<KindValueVo> iterator = kindVo.getKindValues().iterator();
				while (iterator.hasNext()) {
					KindValueVo valueVo = iterator.next();
					if (!strings[i].equals(valueVo.getKindDetailId())) {
						iterator.remove();
					}
				}
			}
		}
		customerCar.setKinds(kindVos);
		customerCar.setProductPrice(combination.getCombinationPrice());
		carList.add(customerCar);
	}
	
	/**
	 * 查询购物车数量接口
	 */
	@Override
	public DataItemResponse<Integer> doSelectCustomerCarNum(TokenRequest request, HttpServletRequest req) {
		DataItemResponse<Integer> response = new DataItemResponse<>();
		// 判断token是否存在
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
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customerId", user.getId());
		Long count = mallCustomerCarDao.count(paramMap);
		response.setItem(count.intValue());
		response.onHandleSuccess();
		return response;
	}

}
