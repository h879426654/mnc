package com.basics.mall.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.basics.app.entity.AppToken;
import com.basics.cu.entity.CuConsume;
import com.basics.mall.controller.response.MallAdvertResponse;
import com.basics.mall.entity.MallGoods;
import com.basics.mall.entity.MallShop;
import com.basics.support.auth.HttpClientUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jodd.util.StringUtil;
import net.sf.json.JSONArray;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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
	public String searchAdvert(String classifyId, String city, String isNew, String sale, Integer pageNum, Integer pageSize, String shopName) {
		if (null == pageNum) {
			pageNum = 1;
		}
		if (null == pageSize) {
			pageSize = 10;
		}
		if ("全国".equals(city)) {
			city = "";
		}
		Map params = new HashMap();
		params.put("classifyId", classifyId);
		params.put("city", city);
		if ("1".equals(isNew)){
			params.put("time1", "0");
		} else if ("0".equals(isNew)){
			params.put("time2", "0");
		} else if ("1".equals(sale)){
			params.put("sale1", "0");
		} else if ("0".equals(sale)){
			params.put("sale2", "0");
		}

		params.put("pageN", (pageNum-1)*10);
		params.put("pageS", pageSize);
		params.put("advertName", shopName);
		params.put("applyStatus", "2");
		params.put("delFlag", "0");
		try {
			List<MallShopAdvert> mallShopAdverts = mallShopAdvertDao.query(new QueryFilterBuilder().putAll(params).build());
			Map map = new HashMap();
			for (MallShopAdvert mallShopAdvert : mallShopAdverts) {
				map.put("shopId", mallShopAdvert.getId());
				map.put("state", "1");
				Long count = cuConsumeDao.count(map);
				mallShopAdvert.setCount(count.intValue());
			}

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
			params.put("delFlag","0");
			params.put("status", "1");
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
		params.put("delFlag","0");
		params.put("status", "1");
		params.put("id", id);
		List<MallGoods> list = mallGoodsDao.query(new QueryFilterBuilder().putAll(params).build());
		JSONObject json = (JSONObject) JSONObject.toJSON(list);
		return json.toString();
	}

	@Override
	public String insertShopAdvert(MallAdvertResponse mallAdvertResponse) {
		try {
			AppToken appToken = appTokenDao.queryOne(new QueryFilterBuilder().put("id", mallAdvertResponse.getToken()).build());
			MallShopAdvert advert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("customerId", appToken.getUserId()).put("applyStatus", "2").put("delFlag", "0").build());
			if (advert != null) {
				return "该商家已入驻";
			}
			MallShopAdvert mallShopAdvert = new MallShopAdvert();
			mallShopAdvert.setId(UUID.randomUUID().toString().replace("-",""));
			mallShopAdvert.setAdvertImage(mallAdvertResponse.getAdvertImage());
			mallShopAdvert.setAdvertName(mallAdvertResponse.getShopName());
			mallShopAdvert.setAdvertContext(mallAdvertResponse.getAdvertContext());
			mallShopAdvert.setPerson(mallAdvertResponse.getPerson());
			mallShopAdvert.setAdvertPhone(mallAdvertResponse.getAdvertPhone());
			mallShopAdvert.setAdvertAddress(mallAdvertResponse.getAdvertAddress());
			mallShopAdvert.setShopLicence(mallAdvertResponse.getShopLicence());
			mallShopAdvert.setCity(mallAdvertResponse.getCity());
			mallShopAdvert.setRegion(mallAdvertResponse.getRegion());
			mallShopAdvert.setCustomerId(appToken.getUserId());
			Map<String, Object> map = this.getLngAndLat(mallAdvertResponse.getAdvertAddress());
			mallShopAdvert.setAdvertLongitude(String.valueOf(map.get("Longitude")));
			mallShopAdvert.setAdvertLatitude(String.valueOf(map.get("Latitude")));
			mallShopAdvert.setClassifyId(mallAdvertResponse.getClaasifyId());
			mallShopAdvertDao.insert(mallShopAdvert);
			return "成功";
		} catch (Exception e) {
			return "失败";
		}
	}

	@Override
	public String searchGoodsAll(String shopId, Integer page, Integer rows) {

		Map params = new HashMap();
		if (page == null){
			page = 1;
		}
		if (rows == null){
			rows = 10;
		}
		params.put("pageN", (page-1)*10);
		params.put("pageS", rows);
		params.put("delFlag","0");
		params.put("status", "1");
		params.put("advertId", shopId);
		List<MallGoods> list = mallGoodsDao.query(new QueryFilterBuilder().putAll(params).build());
		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
	}

	public static Map<String, Object> getLngAndLat(String address) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			HttpURLConnection connection = null;
			InputStream is = null;

			Map<String, String> map = new HashMap<>();
			map.put("address", address);
			map.put("key","18b6949c7f33350e232c2b58430e0159");
			String result = HttpClientUtils.invokeGet("http://restapi.amap.com/v3/geocode/geo", map);
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(result);
			JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("geocodes"));
			net.sf.json.JSONObject j_2 = net.sf.json.JSONObject.fromObject(jsonArray.get(0));
			String location = j_2.getString("location");
			param.put("Longitude", location.split(",")[0]);
			param.put("Latitude", location.split(",")[1]);
//			URL url = new URL("http://restapi.amap.com/v3/geocode/geo?address="+address+"&key=18b6949c7f33350e232c2b58430e0159");
//			connection = (HttpURLConnection) url.openConnection();
//			connection.setRequestMethod("GET");
//			connection.setConnectTimeout(15000);
//			connection.setReadTimeout(60000);
//			connection.setRequestProperty("contentType", "utf-8");
//			connection.connect();
//			is = connection.getInputStream();
//			JsonNode jsonNode = new ObjectMapper().readTree(is);
//			if(StringUtil.equals(jsonNode.findValue("status").textValue(),"1") && jsonNode.findValue("geocodes").size()>0){
//				String[] degree = jsonNode.findValue("geocodes").findValue("location").textValue().split(",");
//				param.put("longitude", degree[0]);
//				param.put("dimension", degree[1]);
//			}
			return param;
		} catch (Exception e) {
			return null;
		}
	}

}
