package com.basics.mall.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.basics.cu.entity.CuCustomerInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.basics.app.entity.AppCode;
import com.basics.app.entity.AppImage;
import com.basics.app.entity.AppUser;
import com.basics.common.BaseApiService;
import com.basics.common.Constant;
import com.basics.common.DataItemResponse;
import com.basics.common.DataPageComonResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenIdRequest;
import com.basics.common.TokenRequest;
import com.basics.common.TokenTypePageRequest;
import com.basics.cu.controller.request.ModifyPassRequest;
import com.basics.cu.entity.CuCustomerAccount;
import com.basics.cu.entity.CuCustomerLogin;
import com.basics.mall.controller.request.PushMallShopRequest;
import com.basics.mall.entity.MallProduct;
import com.basics.mall.entity.MallShop;
import com.basics.mall.service.MallShopApiService;
import com.basics.support.CommonSupport;
import com.basics.support.GuidUtils;
import com.basics.support.MD5Util;
import com.basics.support.PaginationSupport;
import com.basics.support.QueryFilterBuilder;
import com.basics.sys.entity.SysRule;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fan
 *
 */
@Service
public class MallShopApiServiceImpl extends BaseApiService implements MallShopApiService {

	/**
	 * 商家申请
	 */
	@Override
	public DataResponse doApplyMallShop(PushMallShopRequest request, HttpServletRequest req) {
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
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		CommonSupport.checkNotNull(rule, "系统配置不能为空");

		if(Constant.STATE_YES == rule.getNeedUploadLicence()) {
			if(null == request.getShopLicence() || request.getShopLicence().isEmpty()) {
				response.onHandleFail(getMessage(req, "mallShopAdvertApiServiceImpl.doPushShopAdvert.licence.empty"));
				return response;
			}
		}

		MallShop shop = mallShopDao.queryOne(new QueryFilterBuilder().put("customerId", user.getId()).put("flagDel", Constant.STATE_NO).build());
//		if(null != shop && Constant.APPLY_STATUS_3 != shop.getApplyStatus()) {
//			response.onHandleFail(getMessage(req, "mallShopAdvertApiServiceImpl.doPushShopAdvert.advert.agent"));
//			return response;
//		}

		shop = mallShopDao.queryOne(new QueryFilterBuilder().put("shopName", request.getShopName()).put("LTapplyStatus", Constant.APPLY_STATUS_3).put("flagDel", Constant.STATE_NO).build());
		if(null != shop){
			response.onHandleFail(getMessage(req, "mallShopApiServiceImpl.doApplyMallShop.name.repeat"));
			return response;
		}
		AppUser appUser = appUserDao.queryOne(new QueryFilterBuilder().put("code", request.getShopName()).build());
		if(null != appUser) {
			response.onHandleFail(getMessage(req, "mallShopApiServiceImpl.doApplyMallShop.name.repeat"));
			return response;
		}
		CuCustomerAccount account = cuCustomerAccountDao.get(user.getId());
		if(account.getUseMoney().doubleValue() < rule.getNeedShopMoney().doubleValue()) {
			response.onHandleFail(getMessage(req, "tradeCoinApiServiceImpl.doPushTradeInfo.mnc.insufficient"));
			return response;
		}
//		if (Constant.UN_ACTIVE_NUM > account.getCustomerIntegral().doubleValue()) {
//			response.onHandleFail(getMessage(req, "tradeMoneyApiServiceImpl.doPushTradeInfo.user.notActive"));
//			return response;
//		}
		String path = "shop/";
		String fileName = "";
		if (null != request.getFile() && !request.getFile().isEmpty()) {
			fileName = request.getFile().getOriginalFilename();
			path += GuidUtils.generateSimpleGuid() + MD5Util.random(6) + fileName.substring(fileName.lastIndexOf("."));
			try {
				this.baseFileStoreService.write(path, request.getFile().getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			String url = this.baseFileStoreService.getInternetUrl(path);
			request.setShopLogo(url);
		}

		shop = new MallShop();
		if (null != request.getShopLicence() && !request.getShopLicence().isEmpty()) {
			fileName = request.getShopLicence();
//			path += GuidUtils.generateSimpleGuid() + MD5Util.random(6) + fileName.substring(fileName.lastIndexOf("."));
//			try {
//				this.baseFileStoreService.write(path, request.getLicence().getInputStream());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			String url = this.baseFileStoreService.getInternetUrl(path);
			shop.setShopLicence(fileName);
		}
		BeanUtils.copyProperties(request, shop);
		CuCustomerInfo info = cuCustomerInfoDao.get(user.getId());
		shop.setCountryId(info.getCountryId());
		shop.id(user.getId()).applyStatus(Constant.APPLY_STATUS_1).shopStatus(Constant.SHOP_STATUS_2).createTime(new Date()).shopPass(passwordEncoder.encode(request.getShopPass())).customerId(user.getId()).needMoney(rule.getNeedShopMoney()).shopAddress(request.getShopAddr());
//		if(CollectionUtils.isNotEmpty(request.getAptitudeFiles())) {
//			for ( MultipartFile file : request.getAptitudeFiles()) {
		AppImage appImage = null;
//				if (!file.isEmpty()) {
//					path = "shop/";
//					//fileName = file.getOriginalFilename();
//					path += GuidUtils.generateSimpleGuid() + MD5Util.random(6) + fileName.substring(fileName.lastIndexOf("."));
//					try {
//						this.baseFileStoreService.write(path, file.getInputStream());
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
		String url = this.baseFileStoreService.getInternetUrl(path);
		appImage = new AppImage();
		appImage.setOwnerId(shop.getId());
		appImage.setOwnerClass("MallShop");
		appImage.setUrl(fileName);
		appImageDao.save(appImage);
//				}
//			}
//		}
		//createActiveMq(user.getId(), Constant.ACTIVEMQ_TYPE_30, rule.getNeedShopMoney(), null, "商家申请", shop);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 检查商家申请状态
	 */
	@Override
	public DataItemResponse<JSONObject> checkApplyStatus(TokenRequest request, HttpServletRequest req) {
		DataItemResponse<JSONObject> response = new DataItemResponse<>();
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
		JSONObject result=new JSONObject();
		MallShop shop = mallShopDao.queryOne(new QueryFilterBuilder().put("customerId", user.getId()).orderBy("mallShop.CREATE_TIME DESC").build());
		if(null != shop) {
			result.put("checked", shop.getApplyStatus());
			result.put("applyContext", shop.getApplyContext());
		} else {
			result.put("checked", Constant.NUMBER_0);
		}
		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		CommonSupport.checkNotNull(rule, "系统规则不能为空");
		result.put("needMoney", rule.getNeedShopMoney());
		response.setItem(result);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 商家主页
	 */
	@Override
	public DataItemResponse<MallShop> storeHomePage(TokenIdRequest request, HttpServletRequest req) {
		DataItemResponse<MallShop> response = new DataItemResponse<>();
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
		MallShop shop;
		if (StringUtils.isNotBlank(request.getId())&& ! Constant.DEFAULT_REFEREE.equals(request.getId())){
			shop = mallShopDao.queryOne(new QueryFilterBuilder().put("id", request.getId()).put("applyStatus", Constant.APPLY_STATUS_2).build());
		}else {
			shop = mallShopDao.queryOne(new QueryFilterBuilder().put("customerId",user.getId()).put("applyStatus", Constant.APPLY_STATUS_2).build());
		}
		if (null==shop){
            response.onHandleFail("获取商家信息失败");
            return response;
        }
		response.setItem(shop);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 商品上下架
	 */
	@Override
	public DataResponse changeProductStatus(TokenIdRequest request, HttpServletRequest req) {
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

		MallShop shop = mallShopDao.queryOne(new QueryFilterBuilder().put("customerId",user.getId()).build());
		if (null == shop) {
			response.onHandleFail(getMessage(req, "mallShopApiServiceImpl.changeProductStatus.nonexistent"));
			response.setStatus(2);
			return response;
		}
		MallProduct product = mallProductDao.get(request.getId());
		if(null != product) {
			if(!product.getShopId().equals(shop.getId())){
				response.onHandleFail("该商品不属于您");
				response.setStatus(2);
				return response;
			}
			product.setProductStatus(Constant.PRODUCT_STATUS_SALE == product.getProductStatus() ? Constant.PRODUCT_STATUS_OFF : Constant.PRODUCT_STATUS_SALE);
			mallProductDao.save(product);
			response.onHandleSuccess();
		} else {
			response.onHandleFail("商品不存在");
		}
		return response;
	}

	/**
	 * 查询商品列表
	 */
	@Override
	public DataPageComonResponse<MallProduct> selectMyShopProduct(TokenTypePageRequest request, HttpServletRequest req) {
		DataPageComonResponse<MallProduct> response = new DataPageComonResponse<>();
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
		MallShop shop = mallShopDao.queryOne(new QueryFilterBuilder().put("customerId",user.getId()).build());
		if (null == shop) {
			response.onHandleFail(getMessage(req, "mallShopApiServiceImpl.changeProductStatus.nonexistent"));
			response.setStatus(2);
			return response;
		}
		Map<String, Object> params = new HashMap<>();
		if(Constant.PRODUCT_STATUS_WAIT == request.getType().intValue() || Constant.PRODUCT_STATUS_SALE == request.getType().intValue() || Constant.PRODUCT_STATUS_OFF == request.getType().intValue()) {
			params.put("productStatus", request.getType());
		}
		params.put("shopId", shop.getId());
		params.put("flagDel", Constant.STATE_NO);
		params.put("orderBy", "mallProduct.CREATE_TIME DESC");
		PaginationSupport pagination = mallProductDao.queryPagination(params, request.getPageNo(), request.getPageSize());
		if (null != pagination && CollectionUtils.isNotEmpty(pagination.getItems())) {
			response.getData().setItems(pagination.getItems());
			response.getData().setPageCount(pagination.getPageCount());;
			response.getData().setTotalCount(pagination.getTotalCount());
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 商家忘记密码
	 */
	@Override
	public DataResponse updateShopPass(ModifyPassRequest request, HttpServletRequest req) {
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
		if (!request.getPhone().equals(user.getCustomerPhone())) {
			response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.different"));
			return response;
		}
		MallShop shop = mallShopDao.queryOne(new QueryFilterBuilder().put("customerId",user.getId()).put("applyStatus", Constant.APPLY_STATUS_2).build());
		if (null == shop) {
			response.onHandleFail(getMessage(req, "mallShopApiServiceImpl.changeProductStatus.nonexistent"));
			return response;
		}
		//验证码
		CuCustomerInfo info = cuCustomerInfoDao.get(user.getId());
		String msg = checkSmsCode(user.getCustomerPhone(), Constant.SMS_TYPE_6, request.getCode(), info.getCountryId(), req);
		if(StringUtils.isNotBlank(msg)) {
			response.onHandleFail(msg);
			return response;
		}
		AppUser appUser = appUserDao.get(shop.getId());
		CommonSupport.checkNotNull(appUser,"账户错误");
		shop.shopPass(passwordEncoder.encode(request.getNewPassword()));
		mallShopDao.save(shop);
		appUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
		appUserDao.save(appUser);
		response.onHandleSuccess();
		return response;
	}
}
