package com.basics.mall.service;

import java.util.List;

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

import javax.servlet.http.HttpServletRequest;

public interface MallShopAdvertApiService {
	
	DataItemResponse<List<MallShopClassify>> doGetShopClassify(TokenRequest request, HttpServletRequest req);
	
	DataResponse doPushShopAdvert(PushShopAdvertRequest request, HttpServletRequest req);
	
	DataResponse doPushShopAdvert2(PushShopAdvert2Request request, HttpServletRequest req);
	
	DataResponse doUpdateShopAdvert(PushShopAdvertRequest request, HttpServletRequest req);
	
	DataResponse doUpdateShopAdvert2(PushShopAdvert2Request request, HttpServletRequest req);

	DataResponse doDelShopAdvert(TokenIdRequest request, HttpServletRequest req);

	DataPageComonResponse<MallShopAdvert> doPushShopAdvert(SelectShopAdvertRequest request, HttpServletRequest req);

	DataItemResponse<ShopAdvertInfoResponse> doSelectShopAdvertInfo(TokenIdRequest request, HttpServletRequest req);

	DataItemResponse<JSONObject> doCheckShopAdvert(TokenRequest request, HttpServletRequest req);

	DataItemResponse<OwnShopAdvertResponse> doOwnShopAdvert(TokenRequest request, HttpServletRequest req);

}
