package com.basics.mall.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.basics.mall.controller.response.MallAdvertResponse;
import jnr.ffi.annotations.In;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.basics.common.DataItemResponse;
import com.basics.common.DataPageComonResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenIdRequest;
import com.basics.common.TokenRequest;
import com.basics.mall.controller.request.PushShopAdvert2Request;
import com.basics.mall.controller.request.PushShopAdvertRequest;
import com.basics.mall.controller.request.SelectShopAdvertRequest;
import com.basics.mall.controller.response.OwnShopAdvertResponse;
import com.basics.mall.controller.response.ShopAdvertInfoResponse;
import com.basics.mall.entity.MallShopAdvert;
import com.basics.mall.entity.MallShopClassify;
import com.basics.mall.service.MallShopAdvertApiService;

/**
 * 
 * @author fan
 *
 */
@RestController
@RequestMapping("/api/shopAdvert/")
public class MallShopAdvertApiController implements ApplicationContextAware {
	
	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;
	@Autowired
	private MallShopAdvertApiService mallshopApiService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	
	/**
	 * 判断是否发布商圈
	 */
	@RequestMapping("checkShopAdvert")
	public DataItemResponse<JSONObject> checkShopAdvert(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<JSONObject> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallshopApiService.doCheckShopAdvert(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	
	/**
	 * 获取商圈所有分类
	 */
	@RequestMapping("getShopClassify")
	public DataItemResponse<List<MallShopClassify>> getShopClassify(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<List<MallShopClassify>> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallshopApiService.doGetShopClassify(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 发布商圈
	 */
	@RequestMapping("pushShopAdvert")
	public DataResponse pushShopAdvert(@Valid PushShopAdvertRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallshopApiService.doPushShopAdvert(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 发布商圈2
	 */
	@RequestMapping("pushShopAdvert2")
	public DataResponse pushShopAdvert2(@Valid PushShopAdvert2Request request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallshopApiService.doPushShopAdvert2(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 编辑商家信息
	 */
	@RequestMapping("updateShopAdvert")
	public DataResponse updateShopAdvert(@Valid PushShopAdvertRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallshopApiService.doUpdateShopAdvert(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 编辑商家信息2
	 */
	@RequestMapping("updateShopAdvert2")
	public DataResponse updateShopAdvert2(@Valid PushShopAdvert2Request request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallshopApiService.doUpdateShopAdvert2(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 删除商家信息
	 */
	@RequestMapping("delShopAdvert")
	public DataResponse delBusinessAdvert(@Valid TokenIdRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallshopApiService.doDelShopAdvert(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 商家信息列表查询
	 */
	@RequestMapping("selectShopAdvertList")
	public DataPageComonResponse<MallShopAdvert> selectShopAdvertList(@Valid SelectShopAdvertRequest request, BindingResult result, HttpServletRequest req) {
		DataPageComonResponse<MallShopAdvert> response = new DataPageComonResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallshopApiService.doPushShopAdvert(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 商圈详情
	 */
	@RequestMapping("selectShopAdvertInfo")
	public DataItemResponse<ShopAdvertInfoResponse> selectBusinessAdvertInfo(@Valid TokenIdRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<ShopAdvertInfoResponse> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallshopApiService.doSelectShopAdvertInfo(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	
	/**
	 * 自家商圈
	 */
	@RequestMapping("ownShopAdvert")
	public DataItemResponse<OwnShopAdvertResponse> ownShopAdvert(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<OwnShopAdvertResponse> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallshopApiService.doOwnShopAdvert(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	@RequestMapping("searchAdvert")
	public String searchAdvert(@Valid String classifyId, String city, String isNew, String sale, Integer page, Integer rows, String shopName) {
		return mallshopApiService.searchAdvert(classifyId, city, isNew, sale, page, rows, shopName);
	}

	@RequestMapping("searchClassify")
	public String searchClassify() {
		return mallshopApiService.searchClassify();
	}

	@RequestMapping("searchGoodsByShopId")
	public String searchGoodsByShopId(@Valid String shopId) {
		return mallshopApiService.searchGoodsByShopId(shopId);
	}

	@RequestMapping("searchGoodsById")
	public String searchGoodsById(@Valid String shopId , Integer pageNum, Integer pageSize) {
		return mallshopApiService.searchGoodsById(shopId, pageNum, pageSize);
	}

	@RequestMapping("insertShopAdvert")
	public String insertShopAdvert(@Valid MallAdvertResponse mallAdvertResponse) {
		return mallshopApiService.insertShopAdvert(mallAdvertResponse);
	}

}
