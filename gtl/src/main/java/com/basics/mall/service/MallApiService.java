package com.basics.mall.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.basics.common.DataItemResponse;
import com.basics.common.DataPageComonResponse;
import com.basics.common.DataResponse;
import com.basics.common.IdRequest;
import com.basics.common.TokenIdRequest;
import com.basics.common.TokenIdsRequest;
import com.basics.common.TokenPageRequest;
import com.basics.cu.entity.CuCustomerAddress;
import com.basics.mall.controller.request.AddProductRequest;
import com.basics.mall.controller.request.CommentIdPageRequest;
import com.basics.mall.controller.request.CustomerAddressRequest;
import com.basics.mall.controller.request.MallCustomerAddressRequest;
import com.basics.mall.controller.request.MallProductRequest;
import com.basics.mall.controller.request.PushProductCommentRequestList;
import com.basics.mall.controller.response.MallProductDetailResponse;
import com.basics.mall.controller.response.MallProductResponsePlus;
import com.basics.mall.controller.response.MyCollectionResponse;
import com.basics.mall.entity.MallProductClassify;
import com.basics.mall.vo.CommentInfoVo;

import javax.servlet.http.HttpServletRequest;

public interface MallApiService {

	// 商品分类
	DataItemResponse<List<MallProductClassify>> doSelectProductClassifyList(TokenIdRequest request, HttpServletRequest req);

	// 商城首页模块
	DataItemResponse<Map<String, Object>> doSelectIndexProct();

	// 商品列表
	DataItemResponse<MallProductResponsePlus> doSelectMallProductList(MallProductRequest request, HttpServletRequest req);

	// 商品详情
	DataItemResponse<MallProductDetailResponse> doSelectProductDetail(TokenIdRequest request, HttpServletRequest req);

	// 评价商品
	DataResponse doPushProductComment(PushProductCommentRequestList request, HttpServletRequest req) throws IOException;

	// 商品评价列表
	DataPageComonResponse<CommentInfoVo> doSelectProductCommentList(CommentIdPageRequest request, HttpServletRequest req);

	// 我的收藏列表
	DataPageComonResponse<MyCollectionResponse> doSelectMyCollection(TokenPageRequest request, HttpServletRequest req);

	// 批量收藏
	DataItemResponse<Integer> batchMyCollection(TokenIdsRequest request, HttpServletRequest req);

	// 收藏/取消收藏
	DataItemResponse<Integer> doModifyMyCollection(TokenIdRequest request, HttpServletRequest req);

	// 添加/修改收获地址
	DataResponse doSaveMallCustomerAddress(MallCustomerAddressRequest request, HttpServletRequest req);

	// 查询所有收货地址
	DataItemResponse<List<CuCustomerAddress>> doSelectMallCustomerAddressList(CustomerAddressRequest request, HttpServletRequest req);

	// 删除收货地址
	DataResponse delMallCustomerAddress(TokenIdRequest request, HttpServletRequest req);

	// 设置默认地址
	DataResponse doSetDefaultAddress(TokenIdRequest request, HttpServletRequest req);

	/**
	 * 商品添加功能
	 */
	DataResponse doAddProduct(AddProductRequest request, HttpServletRequest req);

}
