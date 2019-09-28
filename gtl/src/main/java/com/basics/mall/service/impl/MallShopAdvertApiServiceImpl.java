package com.basics.mall.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.basics.mall.entity.MallGoods;
import com.basics.mall.entity.MallShop;
import net.sf.json.JSONArray;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.basics.app.entity.AppImage;
import com.basics.common.BaseApiService;
import com.basics.common.Constant;
import com.basics.common.DataItemResponse;
import com.basics.common.DataPageComonResponse;
import com.basics.common.DataPageListResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenIdRequest;
import com.basics.common.TokenRequest;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.cu.entity.CuCustomerLogin;
import com.basics.mall.controller.request.PushShopAdvert2Request;
import com.basics.mall.controller.request.PushShopAdvertRequest;
import com.basics.mall.controller.request.SelectShopAdvertRequest;
import com.basics.mall.controller.response.OwnShopAdvertResponse;
import com.basics.mall.controller.response.ShopAdvertInfoResponse;
import com.basics.mall.entity.MallShopAdvert;
import com.basics.mall.entity.MallShopClassify;
import com.basics.mall.service.MallShopAdvertApiService;
import com.basics.support.CommonSupport;
import com.basics.support.GuidUtils;
import com.basics.support.MD5Util;
import com.basics.support.PaginationSupport;
import com.basics.support.QueryFilterBuilder;
import com.basics.sys.entity.SysRule;
import org.web3j.abi.datatypes.Int;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class MallShopAdvertApiServiceImpl extends BaseApiService implements MallShopAdvertApiService {
	
	/**
	 * 判断是否发布商圈
	 */
	@Override
	public DataItemResponse<JSONObject> doCheckShopAdvert(TokenRequest request, HttpServletRequest req) {
		DataItemResponse<JSONObject> response = new DataItemResponse<>();
		JSONObject data = new JSONObject();
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
		
		MallShopAdvert advert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("customerId", user.getId()).build());
		if(null != advert) {
			data.put("status", advert.getApplyStatus());
			data.put("context", advert.getApplyContext());
		} else {
			data.put("status", Constant.STATE_NO);
			data.put("context", "");
		}
		response.setItem(data);
		response.onHandleSuccess();
		return response;
	}
	
	
	/**
	 * 获得商圈分类
	 */
	@Override
	public DataItemResponse<List<MallShopClassify>> doGetShopClassify(TokenRequest request, HttpServletRequest req) {
		DataItemResponse<List<MallShopClassify>> response = new DataItemResponse<>();
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
		CuCustomerInfo info = cuCustomerInfoDao.get(user.getId());
		List<MallShopClassify> data = mallShopClassifyDao.query(new QueryFilterBuilder().put("countryId", info.getCountryId()).orderBy("mallShopClassify.CLASSIFY_SORT ASC").build());
		if(CollectionUtils.isNotEmpty(data)){
			response.setItem(data);
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 发布商圈信息
	 */
	@Override
	public DataResponse doPushShopAdvert(PushShopAdvertRequest request, HttpServletRequest req) {

		return null;
	}
	
	/**
	 * 发布商圈信息2
	 */
	@Override
	public DataResponse doPushShopAdvert2(PushShopAdvert2Request request, HttpServletRequest req) {
		return null;
	}
	
	/**
	 * 编辑商家信息
	 */
	@Override
	public DataResponse doUpdateShopAdvert(PushShopAdvertRequest request, HttpServletRequest req) {
		return null;
	}
	
	/**
	 * 编辑商家信息2
	 */
	@Override
	public DataResponse doUpdateShopAdvert2(PushShopAdvert2Request request, HttpServletRequest req) {
		return null;
	}
	
	/**
	 * 删除商圈信息
	 */
	@Override
	public DataResponse doDelShopAdvert(TokenIdRequest request, HttpServletRequest req) {
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
		MallShopAdvert advert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("id", request.getId()).put("customerId", user.getId()).build());
		if (null == advert) {
            response.onHandleFail(getMessage(req, "mallShopAdvertApiServiceImpl.doUpdateShopAdvert.nonexistent"));
			return response;
		}
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ownerId", advert.getId());
		paramMap.put("ownerClass", "MallShopAdvert");
		appImageDao.deleteAll(paramMap);
		mallShopAdvertDao.delete(advert);
		response.onHandleSuccess();
		return response;
	}
	
	/**
	 * 商家信息列表查询
	 */
	@Override
	public DataPageComonResponse<MallShopAdvert> doPushShopAdvert(SelectShopAdvertRequest request, HttpServletRequest req) {
		DataPageComonResponse<MallShopAdvert> response = new DataPageComonResponse<>();
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
		Map<String, Object> params = new HashMap<>();
		/*if(StringUtils.isNotBlank(request.getAdCode())) {
			List<AppArea> list = appAreaDao.queryExtend(new QueryFilterBuilder().put("id", request.getAdCode()).build(), "queryParentAreaByCode");
			if(CollectionUtils.isNotEmpty(list)){
				params.put("addressCity", list.get(0).getId());
			}
		}*/
		if (StringUtils.isNotBlank(request.getAdvertClassifyId())) {
			params.put("advertClassifyId", request.getAdvertClassifyId());
		}
		if (StringUtils.isNotBlank(request.getAdvertName())) {
			params.put("likeAdvertName", request.getAdvertName());
		}
		CuCustomerInfo info = cuCustomerInfoDao.get(user.getId());
		params.put("countryId", info.getCountryId());
		params.put("applyStatus", Constant.APPLY_STATUS_2);
		params.put("flagDel", Constant.STATE_NO);
		PaginationSupport paginationSupport = mallShopAdvertDao.queryPagination(params, request.getPageNo(), request.getPageSize());
		if (null != paginationSupport && CollectionUtils.isNotEmpty(paginationSupport.getItems())) {
			DataPageListResponse<MallShopAdvert> data = new DataPageListResponse<>();
			data.setItems(paginationSupport.getItems());
			data.setPageCount(paginationSupport.getPageCount());
			data.setTotalCount(paginationSupport.getTotalCount());
			response.setData(data);
		}
		response.onHandleSuccess();
		return response;
	}
	
	/**
	 * 商圈详情
	 */
	@Override
	public DataItemResponse<ShopAdvertInfoResponse> doSelectShopAdvertInfo(TokenIdRequest request, HttpServletRequest req) {
		DataItemResponse<ShopAdvertInfoResponse> response = new DataItemResponse<>();
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
		Map<String, Object> params = new HashMap<>();
		params.put("id", request.getId());
		ShopAdvertInfoResponse data = mallShopAdvertDao.getExtend(params, "queryShopAdvertInfo");
		if (null != data) {
			response.setItem(data);
			List<AppImage> appImages = appImageDao.listImgsByClassAndId("MallShopAdvert", data.getId());
			if (CollectionUtils.isNotEmpty(appImages)) {
				List<String> imgUrs = new ArrayList<>();
				imgUrs.add(data.getAdvertImage());
				for (AppImage appImage : appImages) {
					imgUrs.add(appImage.getUrl());
				}
				data.setImages(imgUrs);
			}
		}
		response.onHandleSuccess();
		return response;
	}
	
	/**
	 * 查询自己商圈
	 */
	@Override
	public DataItemResponse<OwnShopAdvertResponse> doOwnShopAdvert(TokenRequest request, HttpServletRequest req) {
		DataItemResponse<OwnShopAdvertResponse> response = new DataItemResponse<>();
		OwnShopAdvertResponse data = new OwnShopAdvertResponse();
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
		
		MallShopAdvert advert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("customerId", user.getId()).put("applyStatus", Constant.APPLY_STATUS_2).build());
		if(null == advert) {
            response.onHandleFail(getMessage(req, "mallShopAdvertApiServiceImpl.doUpdateShopAdvert.notRelease"));
			return response;
		}
		data.setMallShopAdvert(advert);
		List<AppImage> appImages = appImageDao.listImgsByClassAndId("MallShopAdvert", advert.getId());
		if (CollectionUtils.isNotEmpty(appImages)) {
			List<String> imgUrs = new ArrayList<>();
			for (AppImage appImage : appImages) {
				imgUrs.add(appImage.getUrl());
			}
			data.setImgs(imgUrs);
		}
		response.setItem(data);
		response.onHandleSuccess();
		return response;
	}

	@Override
	public String searchAdvert(String classifyId, String city, String region, String isNew, String sale, Integer pageNum, Integer pageSize, String shopName, String more) {
		if (null == pageNum) {
			pageNum = 1;
		}
		if (null == pageSize) {
			pageSize = 10;
		}
		Map params = new HashMap();
		params.put("classifyId", classifyId);
		params.put("city", city);
		params.put("region", region);
		params.put("createTime", isNew);
		params.put("advertSale", sale);
		params.put("pageN", (pageNum-1)*10);
		params.put("pageS", pageSize);
		if (null != more) {
			String classifyIds = "";
			List<MallShopClassify> list = mallShopClassifyDao.query(new QueryFilterBuilder().put("flagDel","2").build());
			for (MallShopClassify mallShopClassify : list) {
				classifyIds += ",'" + mallShopClassify +"'";
			}
			params.put("classifyIds",classifyIds.substring(1));
		}
		params.put("advertName", shopName);
		try {
			List<MallShopAdvert> mallShopAdverts = mallShopAdvertDao.query(new QueryFilterBuilder().putAll(params).build());
			JSONArray json = JSONArray.fromObject(mallShopAdverts);
			return json.toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public String searchClassify() {
		List<MallShopClassify> list = mallShopClassifyDao.query(new QueryFilterBuilder().put("userA","123").build());
		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
	}

	@Override
	public String searchGoodsByShopId(String shopId) {
		Integer pageN = 0;
		Integer pageS = 4;
		MallShopAdvert mallShopAdvert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("id", shopId).build());
		try {
			Map params = new HashMap();
			params.put("pageN", pageN);
			params.put("pageS", pageS);
			params.put("advertId", shopId);
			params.put("state", "1");
			params.put("delFlag",0);
			List<MallGoods> list = mallGoodsDao.query(new QueryFilterBuilder().putAll(params).build());
			JSONArray json = JSONArray.fromObject(list);
			mallShopAdvert.setList(json.toString());
			JSONObject json1 = (JSONObject) JSONObject.toJSON(mallShopAdvert);
			return json1.toString();
		} catch (Exception e) {
			return "";
		}
	}

	@Override
	public String searchGoodsById(String id, Integer pageNum, Integer pageSize) {
		Map params = new HashMap();
		if (pageNum == null){
			pageNum = 1;
		}
		if (pageSize == null){
			pageSize = 10;
		}
		params.put("pageN", (pageNum-1)*10);
		params.put("pageS", pageSize);
		params.put("state", "1");
		params.put("delFlag",0);
		List<MallGoods> list = mallGoodsDao.query(new QueryFilterBuilder().putAll(params).build());
		JSONObject json = (JSONObject) JSONObject.toJSON(list);
		return json.toString();
	}

}
