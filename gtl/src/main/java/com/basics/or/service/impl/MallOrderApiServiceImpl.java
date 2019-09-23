package com.basics.or.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.basics.common.BaseApiService;
import com.basics.common.Constant;
import com.basics.common.DataItemResponse;
import com.basics.common.DataPageComonResponse;
import com.basics.common.DataPageListResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenIdRequest;
import com.basics.common.TokenIdsRequest;
import com.basics.common.TokenTypePageRequest;
import com.basics.cu.entity.CuCustomerAccount;
import com.basics.cu.entity.CuCustomerAddress;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.cu.entity.CuCustomerLogin;
import com.basics.mall.controller.request.CreateOrderInfoRequest;
import com.basics.mall.controller.request.CustomerCarRequest;
import com.basics.mall.controller.response.CustomerCarData;
import com.basics.mall.controller.response.CustomerCarResponse;
import com.basics.mall.controller.response.MallOrderProductDetailsResponse;
import com.basics.mall.controller.response.MallOrderResponse;
import com.basics.mall.entity.MallCustomerCar;
import com.basics.mall.entity.MallProduct;
import com.basics.mall.entity.MallProductKindCombination;
import com.basics.mall.entity.MallShop;
import com.basics.mall.vo.KindValueVo;
import com.basics.mall.vo.MainKindVo;
import com.basics.or.controller.request.PayOrderRequest;
import com.basics.or.entity.OrOrder;
import com.basics.or.entity.OrOrderDetails;
import com.basics.or.service.MallOrderApiService;
import com.basics.support.CommonSupport;
import com.basics.support.MD5Util;
import com.basics.support.PaginationSupport;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.SerialnumberUtils;
import com.basics.support.kuaidi.KdniaoTrackQueryAPI;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author fan
 *
 */
@Service
public class MallOrderApiServiceImpl extends BaseApiService implements MallOrderApiService {

	/**
	 * 构建订单号
	 */
	@Override
	public DataItemResponse<List<CustomerCarResponse>> doBuildOrderInfo(CustomerCarRequest request, HttpServletRequest req) {
		DataItemResponse<List<CustomerCarResponse>> response = new DataItemResponse<>();
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
		MallProduct product = mallProductDao.get(request.getId());
		if (null == product) {
			response.onHandleFail(getMessage(req, "mallOrderApiServiceImpl.doBuildOrderInfo.product.nonexistent"));
			return response;
		}
		MallShop business = mallShopDao.get(product.getShopId());
		if (null == business || Constant.SHOP_STATUS_2 == business.getShopStatus()) {
			response.onHandleFail(getMessage(req, "mallOrderApiServiceImpl.doBuildOrderInfo.shop.nonexistent"));
			return response;
		}
		if (user.getId().equals(business.getCustomerId())) {
			response.onHandleFail(getMessage(req, "mallOrderApiServiceImpl.doBuildOrderInfo.buy.own"));
			return response;
		}
		CustomerCarResponse data = new CustomerCarResponse();
		data.setShopId(business.getId());
		data.setShopName(business.getShopName());
		CustomerCarData productinfo = new CustomerCarData();
		productinfo.setId(product.getId());
		productinfo.setProductName(product.getProductName());
		//productinfo.setProductPrice(product.getProductPrice());
		productinfo.setProductNum(request.getProductNum());
		productinfo.setProductImg(product.getProductImg());
		productinfo.setProductFreight(product.getProductFreight());
		MallProductKindCombination combination = mallProductKindCombinationDao.get(request.getCombinationId());
		productinfo.setProductPrice(combination.getCombinationPrice());
		String[] strings = combination.getCombination().split("/");
		// 设置规格列表
		List<MainKindVo> kindVos = mallProductKindContrastDao.findProductMainKinds(product.getId());
		if (CollectionUtils.isNotEmpty(kindVos)) {
			for (Integer i = 0; i < kindVos.size(); i++) {
				MainKindVo kindVo = kindVos.get(i);
				kindVo.setKindValues(
						mallProductKindContrastDao.findProductKindValue(product.getId(), kindVo.getKindId()));
				Iterator<KindValueVo> iterator = kindVo.getKindValues().iterator();
				while (iterator.hasNext()) {
					KindValueVo valueVo = iterator.next();
					if (!strings[i].equals(valueVo.getKindDetailId())) {
						iterator.remove();
					}
				}
			}
			productinfo.setKinds(kindVos);
		}
		List<CustomerCarData> cars = new ArrayList<>();
		cars.add(productinfo);
		data.setCarList(cars);
		List<CustomerCarResponse> result = new ArrayList<>();
		result.add(data);
		response.setItem(result);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 构建订单号(购物车)
	 */
	@Override
	public DataItemResponse<List<CustomerCarResponse>> doBuildOrderInfoByCars(TokenIdsRequest request, HttpServletRequest req) {
		DataItemResponse<List<CustomerCarResponse>> response = new DataItemResponse<>();
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
			List<CustomerCarData> cars = mallCustomerCarDao.queryExtend(new QueryFilterBuilder().put("carIds", request.getIds()).put("customerId", user.getId()).build(), "selectProductsCarBusinessData");
			if (CollectionUtils.isNotEmpty(cars)) {
				for (CustomerCarData productCar : cars) {
					MallProduct product = mallProductDao
							.queryOne(new QueryFilterBuilder().put("id", productCar.getProductId())
									.put("productStatus", Constant.PRODUCT_STATUS_SALE).build());
					CommonSupport.checkNotNull(product, getMessage(req, "mallApiServiceImpl.doSelectProductDetail.product.nonexistent"));
				}
			}
			// 对购物车内不同商家的商品分组
			List<CustomerCarResponse> businessList = mallCustomerCarDao.queryExtend(new QueryFilterBuilder().put("carIds", request.getIds()).put("customerId", user.getId()).build(),"selectCustomerCarGroupByBussinessByCarIds");
			List<CustomerCarData> customerCarList = mallCustomerCarDao.queryExtend(new QueryFilterBuilder().put("carIds", request.getIds()).put("customerId", user.getId()).build(),"selectCustomerCarByCarIds");
			if (CollectionUtils.isNotEmpty(businessList) && CollectionUtils.isNotEmpty(customerCarList)) {
				for (CustomerCarResponse business : businessList) {
					MallShop businessInfo = mallShopDao.get(business.getShopId());
					if (null == businessInfo || Constant.SHOP_STATUS_2 == businessInfo.getShopStatus()) {
						response.onHandleFail(getMessage(req, "mallOrderApiServiceImpl.doBuildOrderInfo.shop.nonexistent"));
						return response;
					}
					if (user.getId().equals(businessInfo.getCustomerId())) {
						response.onHandleFail(getMessage(req, "mallOrderApiServiceImpl.doBuildOrderInfo.buy.own"));
						return response;
					}
					List<CustomerCarData> carList = new ArrayList<CustomerCarData>();
					for (CustomerCarData customerCar : customerCarList) {
						if (business.getShopId().equals(customerCar.getShopId())) {
							MallProductKindCombination combination = mallProductKindCombinationDao.get(customerCar.getCombinationId());
							customerCar.setProductPrice(combination.getCombinationPrice());
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
							carList.add(customerCar);
						}
					}
					business.setCarList(carList);
				}
				response.setItem(businessList);
			}
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 创建订单号
	 */
	@Override
	public DataItemResponse<List<String>> doCreateOrderInfo(CreateOrderInfoRequest request, HttpServletRequest req) {
		DataItemResponse<List<String>> response = new DataItemResponse<>();
		List<String> data = new ArrayList<>();
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
		CuCustomerAccount account = cuCustomerAccountDao.get(user.getId());
		CommonSupport.checkNotNull(account, "账号错误");
		if (Constant.UN_ACTIVE_NUM > account.getCustomerIntegral().doubleValue()) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.user.notActive"));
			return response;
		}
		CuCustomerInfo userInfo = cuCustomerInfoDao.get(user.getId());
		CommonSupport.checkNotNull(userInfo, "用户id错误， id:"+user.getId());
		// 判断收货地址
		CuCustomerAddress address = cuCustomerAddressDao.queryOne(new QueryFilterBuilder().put("id", request.getAddressId()).put("customerId", user.getId()).build());
		if (null == address) {
			response.onHandleFail(getMessage(req, "tmallApiServiceImpl.delMallCustomerAddress.address.nonexistent"));
			return response;
		}
		BigDecimal orderPaylPrice;
		BigDecimal orderBalancePrice = BigDecimal.ZERO;
		BigDecimal orderTotalPrice;

		// 判断创建订单的路径(1立即购买 2购物车购买)
		if (Constant.NUMBER_1 == request.getCreateType().intValue()) {
			if (StringUtils.isBlank(request.getProductId())) {
				response.onHandleFail("商品Id不能为空");
				return response;
			}
			if (StringUtils.isBlank(request.getCombinationId())) {
				response.onHandleFail("商品规格不能为空");
				return response;
			}
			if (null == request.getProductNum() || request.getProductNum() <= 0) {
				response.onHandleFail("商品数量不合法");
				return response;
			}
			Map<String, Object> result = doCheckProduct(request.getProductId(), request.getProductNum(),request.getCombinationId());
			orderTotalPrice = (BigDecimal) result.get("totalPrice");
			List<MallCustomerCar> cars = new ArrayList<>();
			MallCustomerCar info = new MallCustomerCar();
			info.setProductId(request.getProductId());
			info.setProductNum(request.getProductNum());
			info.setCombinationId(request.getCombinationId());
			cars.add(info);
			if (Constant.PAY_TYPE_1 == request.getOrderPayType()) {
				orderPaylPrice = orderTotalPrice;
			} else {
				response.onHandleFail("目前只支持余额支付");
				return response;
			}
			String orderId = doSaveOrderData(orderPaylPrice, orderBalancePrice, orderTotalPrice, userInfo, (String) result.get("shopId"), address, request, cars);
			data.add(orderId);
			response.setItem(data);
			response.onHandleSuccess();
		} else if (Constant.NUMBER_2 == request.getCreateType().intValue()) {
			if (CollectionUtils.isEmpty(request.getIds())) {
				response.onHandleFail("购物车ID不能为空");
				return response;
			}
			if (Constant.NUMBER_1 == request.getIds().size()) {
				MallCustomerCar car = mallCustomerCarDao.get(request.getIds().get(0));
				Map<String, Object> result = doCheckProduct(car.getProductId(), car.getProductNum(),
						car.getCombinationId());
				orderTotalPrice = (BigDecimal) result.get("totalPrice");
				List<MallCustomerCar> cars = new ArrayList<>();
				cars.add(car);
				if (Constant.PAY_TYPE_1 == request.getOrderPayType()) {
					orderPaylPrice = orderTotalPrice;
				} else {
					response.onHandleFail("目前只支持余额支付");
					return response;
				}
				String orderId = doSaveOrderData(orderPaylPrice, orderBalancePrice, orderTotalPrice, userInfo, (String) result.get("shopId"), address, request, cars);
				data.add(orderId);
				response.setItem(data);
				response.onHandleSuccess();
			} else {
				// 对购物车内不同商家的商品分组
				List<String> businesslist = mallCustomerCarDao.queryExtend(new QueryFilterBuilder().put("carIds", request.getIds()).put("customerId", user.getId()).build(),"selectBussinessByCar");
				List<CustomerCarData> cars = mallCustomerCarDao.queryExtend(new QueryFilterBuilder().put("carIds", request.getIds()).put("customerId", user.getId()).build(),"selectProductsCarBusinessData");
				if (CollectionUtils.isNotEmpty(businesslist) && CollectionUtils.isNotEmpty(cars)) {
					List<MallCustomerCar> carsData;
					for (String business : businesslist) {
						carsData = new ArrayList<>();
						orderTotalPrice = BigDecimal.ZERO;
						for (CustomerCarData car : cars) {
							if (business.equals(car.getShopId())) {
								Map<String, Object> result = doCheckProduct(car.getProductId(), car.getProductNum(),car.getCombinationId());
								orderTotalPrice = orderTotalPrice.add((BigDecimal) result.get("totalPrice"));
								carsData.add(car);
							}
						}
						if (Constant.PAY_TYPE_1 == request.getOrderPayType()) {
							orderPaylPrice = orderTotalPrice;
						} else {
							response.onHandleFail("目前只支持余额支付");
							return response;
						}
						String orderId = doSaveOrderData(orderPaylPrice, orderBalancePrice, orderTotalPrice, userInfo,business, address, request, carsData);
						data.add(orderId);
					}
					response.setItem(data);
				}
				response.onHandleSuccess();
			}
		} else {
			response.onHandleFail("订单类型错误");
		}
		return response;
	}

	
	/**
	 * 订单商品的校验及更新
	 */
	private Map<String, Object> doCheckProduct(String productId, Integer productNum, String combination) {
		Map<String, Object> result = new HashMap<>(2);
		// 判断当前商品是否存在及库存是否足够
		MallProductKindCombination mallProductKindCombination = mallProductKindCombinationDao
				.queryOne(new QueryFilterBuilder().put("productId", productId).put("id", combination).build());
		MallProduct product = mallProductDao.get(productId);
		CommonSupport.checkNotNull(product, "商品不存在或已下架");
		CommonSupport.checkNotNull(mallProductKindCombination, "该类型商品不存在或已下架");
		if (Constant.STATE_NO >= productNum) {
			CommonSupport.checkNotNull(null, "商品数量不合法");
		}
		if (mallProductKindCombination.getCombinationStockNum() < productNum) {
			CommonSupport.checkNotNull(null, "商品库存不足");
		}
		mallProductKindCombination.setCombinationSellNum(
				mallProductKindCombination.getCombinationSellNum() + productNum);
		mallProductKindCombination.setCombinationStockNum(
				mallProductKindCombination.getCombinationStockNum() - productNum);
		mallProductKindCombinationDao.save(mallProductKindCombination);
		product.setProductSale(product.getProductSale() + productNum);
		product.setProductStock(product.getProductStock() - productNum);
		mallProductDao.save(product);
		result.put("shopId", product.getShopId());
		result.put("totalPrice", mallProductKindCombination.getCombinationPrice().multiply(BigDecimal.valueOf(productNum)));
		return result;
	}

	/**
	 * 维护订单数据
	 */
	private String doSaveOrderData(BigDecimal orderPaylPrice, BigDecimal orderBalancePrice, BigDecimal orderTotalPrice, CuCustomerInfo user, String shopId, CuCustomerAddress address, CreateOrderInfoRequest request,List<MallCustomerCar> cars) {
		// 创建订单主表
		OrOrder order = new OrOrder();
		// 获取商家ID
		order.setShopId(shopId);
		order.setCustomerId(user.getId());
		order.setOrderPayPrice(orderPaylPrice);
//		order.setOrderBalancePrice(orderBalancePrice);
		order.setOrderTotalPrice(orderTotalPrice);
		order.setOrderPayType(request.getOrderPayType());
		order.setOrderNumber(SerialnumberUtils.generate());
		order.setOrderStatus(Constant.ORDER_STATUS_1);
		order.setOrderReceiver(user.getCustomerName());
		order.setOrderReceiverPhone(user.getCustomerPhone());
		order.setAddressProvince(address.getAddressProvince());
		order.setAddressCity(address.getAddressCity());
		order.setAddressArea(address.getAddressArea());
		order.setAddressInfo(address.getAddressInfo());
		order.setOrderRemark(request.getOrderRemark());
		order.setFlagDel(Constant.STATE_NO);
		order.setCreateTime(new Date());
		orOrderDao.save(order);
		// 添加订单详情
		if (CollectionUtils.isNotEmpty(cars)) {
			OrOrderDetails details = null;
			for (MallCustomerCar car : cars) {
				details = new OrOrderDetails();
				details.setCombinationId(car.getCombinationId());
				details.setOrderId(order.getId());
				details.setProductId(car.getProductId());
				details.setProductNum(car.getProductNum());
				details.setCreateTime(new Date());
				details.setFlagDel(Constant.STATE_NO);
				orOrderDetailsDao.save(details);
				// 删除购物车
				if (StringUtils.isNotBlank(car.getId())) {
					mallCustomerCarDao.delete(car.getId());
				}
			}
		}
		return order.getId();
	}

	/**
	 * 取消订单
	 */
	@Override
	public DataResponse doCancelOrderInfo(TokenIdRequest request, HttpServletRequest req) {
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
		OrOrder mallOrder = orOrderDao.queryOne(new QueryFilterBuilder().put("customerId", user.getId()).put("id", request.getId()).build());
		if (null == mallOrder) {
			response.onHandleFail(getMessage(req, "mallOrderApiServiceImpl.doCancelOrderInfo.error"));
			return response;
		}
		if (Constant.ORDER_STATUS_1 != mallOrder.getOrderStatus()) {
			response.onHandleFail(getMessage(req, "mallOrderApiServiceImpl.doCancelOrderInfo.status.error"));
			return response;
		}
		mallOrder.setOrderStatus(Constant.ORDER_STATUS_6);
		mallOrder.setOrderFinishTime(new Date());
		orOrderDao.save(mallOrder);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 删除订单
	 */
	@Override
	public DataResponse doDelOrderInfo(TokenIdRequest request, HttpServletRequest req) {
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
		OrOrder mallOrder = orOrderDao.queryOne(new QueryFilterBuilder().put("customerId", user.getId()).put("id", request.getId()).build());
		if (null == mallOrder) {
			response.onHandleFail(getMessage(req, "mallOrderApiServiceImpl.doCancelOrderInfo.error"));
			return response;
		}
		if (Constant.ORDER_STATUS_5 <= mallOrder.getOrderStatus()) {
			orOrderDao.delete(mallOrder.getId());
			response.onHandleSuccess();
			return response;
		} else {
			response.onHandleFail(getMessage(req, "mallOrderApiServiceImpl.doCancelOrderInfo.status.error"));
			return response;
		}
	}

	/**
	 * 查询订单
	 */
	@Override
	public DataPageComonResponse<MallOrderResponse> doSelectMyOrder(TokenTypePageRequest request, HttpServletRequest req) {

		DataPageComonResponse<MallOrderResponse> response = new DataPageComonResponse<>();
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

		Map<String,Object> builder = new HashMap<String,Object> (5);
		builder.put("customerId", user.getId());
		builder.put("orderBy","orOrder.CREATE_TIME DESC");
		if (null != request.getType() && 0 != request.getType() ) {
			builder.put("orderStatus", request.getType());
		}
		 PaginationSupport queryPaginationExtend = orOrderDao.queryPaginationExtend("queryMallOrderById", "count", builder,request.getPageNo(), request.getPageSize());
		 List<MallOrderResponse> list = queryPaginationExtend.getItems();
		if (CollectionUtils.isNotEmpty(list)) {
			for (MallOrderResponse order : list) {
				order.setFlag(request.getType());
				List<MallOrderProductDetailsResponse> productList = orOrderDetailsDao.queryExtend(new QueryFilterBuilder().put("orderId", order.getId()).build(), "queryOrderDetailsList");
				if (CollectionUtils.isNotEmpty(productList)) {
					order.setList(productList);
					for (MallOrderProductDetailsResponse product : productList) {
						MallProductKindCombination combination = mallProductKindCombinationDao.get(product.getCombinationId());
						product.setProductPrice(combination.getCombinationPrice());
						String[] strings = combination.getCombination().split("/");
						// 设置规格列表
						List<MainKindVo> kindVos = mallProductKindContrastDao.findProductMainKinds(product.getProductId());
						if (CollectionUtils.isNotEmpty(kindVos)) {
							for (Integer i = 0; i < kindVos.size(); i++) {
								MainKindVo kindVo = kindVos.get(i);
								kindVo.setKindValues(mallProductKindContrastDao.findProductKindValue(product.getProductId(), kindVo.getKindId()));
								Iterator<KindValueVo> iterator = kindVo.getKindValues().iterator();
								while (iterator.hasNext()) {
									KindValueVo valueVo = iterator.next();
									if (!strings[i].equals(valueVo.getKindDetailId())) {
										iterator.remove();
									}
								}
							}
							product.setKinds(kindVos);
						}
					}
				}
			}
		}
		if (CollectionUtils.isNotEmpty(list)) {
			DataPageListResponse<MallOrderResponse> data=new DataPageListResponse<>();
			data.setItems(list);
			data.setPageCount(queryPaginationExtend.getPageCount());
			data.setTotalCount(queryPaginationExtend.getTotalCount());
			data.setCurrentPageNo(queryPaginationExtend.getCurrentPageNo());
			response.setData(data);
		}
		response.onHandleSuccess();
		return response;
	}
	
	/**
	 * 支付订单
	 */
	@Override
	public DataResponse doPayMyOrder(PayOrderRequest request, HttpServletRequest req) {
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
		if(CollectionUtils.isEmpty(request.getIds())) {
			response.onHandleFail("id不能为空");
			return response;
		}
		CuCustomerAccount account = cuCustomerAccountDao.get(user.getId());
		CommonSupport.checkNotNull(account, "用户account的错误");
		if (Constant.UN_ACTIVE_NUM > account.getCustomerIntegral().doubleValue()) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.user.notActive"));
			return response;
		}
		if (!account.getCustomerPayPass().equals(MD5Util.GetMD5Code(request.getPayPass()))) {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.payPass.error"));
			return response;
		}
		BigDecimal totalRate = BigDecimal.ZERO;
		for (String id : request.getIds()) {
			OrOrder order = orOrderDao.queryOne(new QueryFilterBuilder().put("id", id).put("customerId", user.getId()).put("orderStatus", Constant.ORDER_STATUS_1).build());
			if(null != order) {
				totalRate = totalRate.add(order.getOrderPayPrice());
			} else {
				response.onHandleFail(getMessage(req, "mallOrderApiServiceImpl.doCancelOrderInfo.pay.agent"));
				return response;
			}
		}
		if(account.getUseMoney().doubleValue() >= totalRate.doubleValue()) {
			for (String id : request.getIds()) {
				createActiveMq(account.getId(), Constant.ACTIVEMQ_TYPE_28, BigDecimal.ZERO, id, "商城--订单支付", null);
			}
			response.onHandleSuccess();
			return response;
		} else {
			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.mc.insufficient"));
			return response;
		}
	}
	
	/**
	 * 确认收货
	 */
	@Override
	public DataResponse doConfirmOrder(PayOrderRequest request, HttpServletRequest req) {
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
		OrOrder order = orOrderDao.queryOne(new QueryFilterBuilder().put("id", request.getIds().get(0)).put("customerId", user.getId()).put("orderStatus", Constant.ORDER_STATUS_3).build());
		if(null != order) {
			CuCustomerAccount account = cuCustomerAccountDao.get(user.getId());
			CommonSupport.checkNotNull(account, "用户account的错误");
			if (!account.getCustomerPayPass().equals(MD5Util.GetMD5Code(request.getPayPass()))) {
				response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.payPass.error"));
				return response;
			}
			if(account.getLockMoney().doubleValue() >= order.getOrderPayPrice().doubleValue()) {
				createActiveMq(account.getId(), Constant.ACTIVEMQ_TYPE_29, BigDecimal.ZERO, order.getId(), "商城--确认收货",null);
			} else {
				CommonSupport.checkNotNull(account, "用户account锁定币错误");
			}
		} else {
			response.onHandleFail(getMessage(req, "mallOrderApiServiceImpl.doCancelOrderInfo.Signing"));
		}
		response.onHandleSuccess();
		return response;
	}
	
	/**
	 * 根据订单获取物流信息
	 */
	@Override
	public DataItemResponse<JSONObject> doSelectLogisticsInfo(TokenIdRequest request, HttpServletRequest req) {
		DataItemResponse<JSONObject> response = new DataItemResponse<>();
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
		OrOrder mallOrder = orOrderDao.queryOne(new QueryFilterBuilder().put("id", request.getId()).put("customerId", user.getId()).build());
		if (null == mallOrder) {
			response.onHandleFail("订单不存在");
			return response;
		}
		if (StringUtils.isBlank(mallOrder.getOrderLogisticsNum())) {
			response.onHandleFail("请等待商家发货，暂无法获取物流信息");
			return response;
		}
		try {
			String result = KdniaoTrackQueryAPI.getOrderTracesByJson(mallOrder.getOrderLogisticsCode(),mallOrder.getOrderLogisticsNum());
			JSONObject data = JSONObject.parseObject(result);
			data.put("name", mallOrder.getOrderLogisticsName());
			response.setItem(data);
			response.onHandleSuccess();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.onException(e);
			return response;
		}
	}

}