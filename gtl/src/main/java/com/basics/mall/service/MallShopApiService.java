package com.basics.mall.service;

import com.alibaba.fastjson.JSONObject;
import com.basics.common.DataItemResponse;
import com.basics.common.DataPageComonResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenIdRequest;
import com.basics.common.TokenRequest;
import com.basics.common.TokenTypePageRequest;
import com.basics.cu.controller.request.ModifyPassRequest;
import com.basics.mall.controller.request.PushMallShopRequest;
import com.basics.mall.entity.MallProduct;
import com.basics.mall.entity.MallShop;

import javax.servlet.http.HttpServletRequest;

public interface MallShopApiService {

	/**
	 * 商家申请
	 */
	DataResponse doApplyMallShop(PushMallShopRequest request, HttpServletRequest req);

	/**
	 * 检查商家申请
	 */
	DataItemResponse<JSONObject> checkApplyStatus(TokenRequest request, HttpServletRequest req);

	/**
	 * 商家主页
	 */
	DataItemResponse<MallShop> storeHomePage(TokenIdRequest request, HttpServletRequest req);

	/**
	 * 商品上下架
	 */
	DataResponse changeProductStatus(TokenIdRequest request, HttpServletRequest req);

	/**
	 * 查询商品列表
	 */
	DataPageComonResponse<MallProduct> selectMyShopProduct(TokenTypePageRequest request, HttpServletRequest req);

	/**
	 * 商家忘记密码
	 */
    DataResponse updateShopPass(ModifyPassRequest request, HttpServletRequest req);
}
