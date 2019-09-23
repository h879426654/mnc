package com.basics.mall.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.basics.app.entity.AppImage;
import com.basics.common.BaseApiService;
import com.basics.common.Constant;
import com.basics.common.DataItemResponse;
import com.basics.common.DataPageComonResponse;
import com.basics.common.DataPageListResponse;
import com.basics.common.DataResponse;
import com.basics.common.IdRequest;
import com.basics.common.TokenIdRequest;
import com.basics.common.TokenIdsRequest;
import com.basics.common.TokenPageRequest;
import com.basics.cu.entity.CuCustomerAddress;
import com.basics.cu.entity.CuCustomerCollection;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.cu.entity.CuCustomerLogin;
import com.basics.mall.controller.request.AddProductRequest;
import com.basics.mall.controller.request.CommentIdPageRequest;
import com.basics.mall.controller.request.CustomerAddressRequest;
import com.basics.mall.controller.request.MallCustomerAddressRequest;
import com.basics.mall.controller.request.MallProductRequest;
import com.basics.mall.controller.request.PushProductCommentRequest;
import com.basics.mall.controller.request.PushProductCommentRequestList;
import com.basics.mall.controller.response.IndexProductResponse;
import com.basics.mall.controller.response.MallProductDetailResponse;
import com.basics.mall.controller.response.MallProductResponse;
import com.basics.mall.controller.response.MallProductResponsePlus;
import com.basics.mall.controller.response.MyCollectionResponse;
import com.basics.mall.entity.MallIndexType;
import com.basics.mall.entity.MallProduct;
import com.basics.mall.entity.MallProductClassify;
import com.basics.mall.entity.MallProductComment;
import com.basics.mall.entity.MallProductKind;
import com.basics.mall.entity.MallProductKindCombination;
import com.basics.mall.entity.MallProductKindContrast;
import com.basics.mall.entity.MallProductKindDetail;
import com.basics.mall.entity.MallShop;
import com.basics.mall.service.MallApiService;
import com.basics.mall.vo.CombinationVo;
import com.basics.mall.vo.CommentInfoVo;
import com.basics.mall.vo.KindDetailVo;
import com.basics.mall.vo.KindsVo;
import com.basics.mall.vo.MainKindVo;
import com.basics.mall.vo.ProductCombinationVo;
import com.basics.or.entity.OrOrder;
import com.basics.or.entity.OrOrderDetails;
import com.basics.support.FileStoreService;
import com.basics.support.GuidUtils;
import com.basics.support.MD5Util;
import com.basics.support.PaginationSupport;
import com.basics.support.QueryFilterBuilder;
import com.basics.sys.entity.SysBanner;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class MallApiServiceImpl extends BaseApiService implements MallApiService {

	@Autowired
	protected FileStoreService baseFileStoreService;

	/**
	 * 商品分类
	 */
	@Override
	public DataItemResponse<List<MallProductClassify>> doSelectProductClassifyList(TokenIdRequest request, HttpServletRequest req) {
		DataItemResponse<List<MallProductClassify>> response = new DataItemResponse<>();
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
		QueryFilterBuilder builder = new QueryFilterBuilder();
		if (request.getId() != null) {
			builder.put("classifyParentId", request.getId());
		} else {
			builder.put("classifyParentId", "0");
		}
		CuCustomerInfo info = cuCustomerInfoDao.get(user.getId());
		builder.put("countryId", info.getCountryId());
		List<MallProductClassify> data = mallProductClassifyDao.query(builder.orderBy("mallProductClassify.CLASSIFY_SORT ASC").build());
		response.setItem(data);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 商城首页模块
	 */
	@Override
	public DataItemResponse<Map<String, Object>> doSelectIndexProct() {
		DataItemResponse<Map<String, Object>> response = new DataItemResponse<>();
		Map<String, Object> map = new HashMap<>(2);
		// 首页banner
		List<SysBanner> banners = sysBannerDao.query(new QueryFilterBuilder().put("bannerType", Constant.BANNER_TYPE_2).orderBy("sysBanner.BANNER_SORT ASC").build());
		if (CollectionUtils.isNotEmpty(banners)) {
			map.put("banner", banners);
		}
		// 首页商品
		List<MallIndexType> indexType = mallIndexTypeDao.query(new QueryFilterBuilder().orderBy("typeSort").build());
		if (CollectionUtils.isNotEmpty(indexType)) {
			List<IndexProductResponse> indexList = new ArrayList<>();
			for (MallIndexType type : indexType) {
				List<MallProductResponse> indexProduct = mallIndexProductDao
						.queryExtend(new QueryFilterBuilder().put("typeId", type.getId()).put("productStatus", 2).orderBy("mallIndexProduct.INDEX_SORT ASC").build(), "selectMallIndexProductList");
				if (CollectionUtils.isNotEmpty(indexProduct)) {
					IndexProductResponse indexProductResponse = new IndexProductResponse();
					indexProductResponse.setIndexType(type);
					indexProductResponse.setGoodsList(indexProduct);
					indexList.add(indexProductResponse);
				}
			}
			map.put("index", indexList);
		}
		response.setItem(map);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 查询商品列表
	 */
	@Override
	public DataItemResponse<MallProductResponsePlus> doSelectMallProductList(MallProductRequest request, HttpServletRequest req) {
		DataItemResponse<MallProductResponsePlus> response = new DataItemResponse<>();
		MallProductResponsePlus mallProductResponsePlus = new MallProductResponsePlus();
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
		
		// 添加查询条件
		Map<String, Object> params = new HashMap<>();
		if (StringUtils.isNotBlank(request.getProductFirstClassify())) {
			params.put("productFirstClassify", request.getProductFirstClassify());
			List<MallProductClassify> productClassifys = mallProductClassifyDao.query(new QueryFilterBuilder().put("classifyParentId", request.getProductFirstClassify()).build());
			if (CollectionUtils.isNotEmpty(productClassifys)) {
				params.put("productSecondClassify", productClassifys.get(0).getId());
				mallProductResponsePlus.setClassifyParentName(productClassifys.get(0).getClassifyParentName());
				mallProductResponsePlus.setProductClassifys(productClassifys);
			}
		}
		if (StringUtils.isNotBlank(request.getProductSecondClassify())) {
			params.put("productSecondClassify", request.getProductSecondClassify());
		}
		if (StringUtils.isNotBlank(request.getProductName())) {
			params.put("q", request.getProductName());
		}
		if (StringUtils.isNotBlank(request.getBusinessId())) {
			params.put("shopId", request.getBusinessId());
		}
		if (null != request.getOrderType()) {
			if (1 == request.getOrderType()) {
				params.put("orderBy", "mallProduct.CREATE_TIME DESC");
			}
			if (2 == request.getOrderType()) {
				params.put("orderBy", "mallProduct.PRODUCT_SALE DESC");
			}
			if (3 == request.getOrderType()) {
				params.put("orderBy", "mallProduct.PRODUCT_PRICE DESC");
			}
			if (4 == request.getOrderType()) {
				params.put("orderBy", "mallProduct.PRODUCT_COOLECTION DESC");
			}
		}
		CuCustomerInfo info = cuCustomerInfoDao.get(user.getId());
		params.put("countryId", info.getCountryId());
		params.put("productStatus", Constant.PRODUCT_STATUS_SALE);
		params.put("flagDel", Constant.STATE_NO);
		PaginationSupport paginationSupport = mallProductDao.queryPaginationExtend("selectMallProductList", "count", params, request.getPageNo(), request.getPageSize());
		if (null != paginationSupport && CollectionUtils.isNotEmpty(paginationSupport.getItems())) {
			DataPageListResponse<MallProductResponse> dataPageListResponse = new DataPageListResponse<>();
			dataPageListResponse.setItems(paginationSupport.getItems());
			dataPageListResponse.setPageCount(paginationSupport.getPageCount());
			dataPageListResponse.setTotalCount(paginationSupport.getTotalCount());
			mallProductResponsePlus.setItems(dataPageListResponse);
		}
		response.setItem(mallProductResponsePlus);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 商品详情
	 */
	@Override
	public DataItemResponse<MallProductDetailResponse> doSelectProductDetail(TokenIdRequest request, HttpServletRequest req) {
		DataItemResponse<MallProductDetailResponse> response = new DataItemResponse<>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", request.getId());
		map.put("flagDel", Constant.STATE_NO);
		MallProductDetailResponse data = mallProductDao.getExtend(map, "selectProductDetail");
		if (null == data) {
			response.onHandleFail(getMessage(req, "mallApiServiceImpl.doSelectProductDetail.product.nonexistent"));
			return response;
		}
		// 商品评价
		map.clear();
		map.put("productId", request.getId());
		MallProductComment commentAvg = mallProductCommentDao.getExtend(map, "queryProductCommentAvg");
		if (null != commentAvg) {
			data.setCommentDescribeSart(commentAvg.getCommentDescribeSart());
			data.setCommentLogisticsSart(commentAvg.getCommentLogisticsSart());
			data.setCommentServiceSart(commentAvg.getCommentServiceSart());
		}
		// 商品轮播图
		List<AppImage> appImages = appImageDao.listImgsByClassAndId("MallProduct", data.getId());
		if (CollectionUtils.isNotEmpty(appImages)) {
			List<String> imgUrs = new ArrayList<>();
			for (AppImage appImage : appImages) {
				imgUrs.add(appImage.getUrl());
			}
			data.setImages(imgUrs);
		}
		// 设置规格列表
		List<MainKindVo> kindVos = mallProductKindContrastDao.findProductMainKinds(data.getId());
		if (CollectionUtils.isNotEmpty(kindVos)) {
			for (MainKindVo kindVo : kindVos) {
				kindVo.setKindValues(mallProductKindContrastDao.findProductKindValue(data.getId(), kindVo.getKindId()));
			}
			data.setKinds(kindVos);
		}
		// 设置规格库存
		List<MallProductKindCombination> combinationsStocks = mallProductKindCombinationDao.query(new QueryFilterBuilder().put("productId", data.getId()).build());
		if (CollectionUtils.isNotEmpty(combinationsStocks)) {
			List<ProductCombinationVo> combinationVos = new ArrayList<ProductCombinationVo>();
			for (MallProductKindCombination mallProductKindCombination : combinationsStocks) {
				ProductCombinationVo combinationVo = new ProductCombinationVo();
				combinationVo.setProuductId(mallProductKindCombination.getProductId());
				combinationVo.setCombinationId(mallProductKindCombination.getId());
				combinationVo.setStock(mallProductKindCombination.getCombinationStockNum());
				combinationVo.setCombination(mallProductKindCombination.getCombination());
				combinationVo.setCombinationPrice(mallProductKindCombination.getCombinationPrice());
				combinationVo.setCombinationImg(mallProductKindCombination.getCombinationImg());
				combinationVos.add(combinationVo);
			}
			data.setCombinationsStock(combinationVos);
		}
		// 查看最新评论
		map.clear();
		map.put("productId", data.getId());
		List<CommentInfoVo> comments = mallProductCommentDao.queryExtend(new QueryFilterBuilder().put("productId", data.getId()).orderBy("mallProductComment.CREATE_TIME DESC").limit(1).build(),
				"selectCommentInfoVo");
		Long count = mallProductCommentDao.count(map);
		data.setCommentNum(count.intValue());
		if (CollectionUtils.isNotEmpty(comments)) {
			data.setComment(comments.get(0));
			List<AppImage> images = appImageDao.query(new QueryFilterBuilder().put("ownerId", comments.get(0).getId()).build());
			ArrayList<String> arrayList = new ArrayList<>();
			if (CollectionUtils.isNotEmpty(images)) {
				for (AppImage appImage : images) {
					arrayList.add(appImage.getUrl());
				}
				data.setCommentImages(arrayList);
			}
		} else {
			data.setComment(new CommentInfoVo());
		}
		// 判断商品是否收藏
		data.setFlagCollection(0);
		if (request.getToken() != null) {
			CuCustomerLogin user = checkToken(request.getToken());
			if (user != null) {
				CuCustomerCollection collection = cuCustomerCollectionDao.queryOne(new QueryFilterBuilder().put("customerId", user.getId()).put("sourceId", request.getId()).build());
				if (collection != null) {
					// 商品未收藏
					data.setFlagCollection(1);
				}
			}
		}
		response.setItem(data);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 评价商品
	 */
	@Override
	public DataResponse doPushProductComment(PushProductCommentRequestList request, HttpServletRequest req) throws IOException {
		String temp = "[" + request.getList() + "]";
		List<PushProductCommentRequest> parseArray = JSONArray.parseArray(temp, PushProductCommentRequest.class);
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
		OrOrder mallOrder = orOrderDao.queryOne(new QueryFilterBuilder().put("id", request.getOrderId()).put("orderStatus", Constant.ORDER_STATUS_4).build());
		if (null == mallOrder) {
			response.onHandleFail(getMessage(req, "mallApiServiceImpl.doPushProductComment.comment.error"));
			return response;
		}
		// 添加商品评论
		if (request.getList() != null) {
			for (PushProductCommentRequest comments : parseArray) {
				List<String> images = new ArrayList<String>();
				if (null != comments.getFiles() && comments.getFiles().length > 0) {
					for (MultipartFile file : comments.getFiles()) {
						if (!file.isEmpty()) {
							String path = "BusinessComment/";
							String fileName = file.getOriginalFilename();
							path += GuidUtils.generateSimpleGuid() + MD5Util.random(6) + fileName.substring(fileName.lastIndexOf("."));
							this.baseFileStoreService.write(path, file.getInputStream());
							String url = this.baseFileStoreService.getInternetUrl(path);
							images.add(url);
						}
					}
				}
				// 获取订单下的商品
				List<OrOrderDetails> lists = orOrderDetailsDao.query(new QueryFilterBuilder().put("orderId", mallOrder.getId()).build());
				if (CollectionUtils.isNotEmpty(lists)) {
					for (OrOrderDetails mallOrderDetails : lists) {
						if (mallOrderDetails.getProductId().equals(comments.getProductId())) {
							MallProductComment comment = new MallProductComment();
							comment.setProductId(mallOrderDetails.getProductId());
							comment.setCustomerId(user.getId());
							comment.setCommentContext(comments.getCommentContext());
							comment.setCommentType(comments.getCommentType());
							comment.setCommentDescribeSart(request.getCommentDescribeSart());
							comment.setCommentLogisticsSart(request.getCommentLogisticsSart());
							comment.setCommentServiceSart(request.getCommentServiceSart());
							comment.setReplyTime(new Date());
							if (null != comments.getFlagAnonymous()) {
								comment.setFlagAnonymous(comments.getFlagAnonymous());
							}
							comment.setCreateTime(new Date());
							comment.setCreateUser(user.getId());
							mallProductCommentDao.save(comment);
							if (CollectionUtils.isNotEmpty(images)) {
								AppImage appImage = null;
								for (String string : images) {
									appImage = new AppImage();
									appImage.setOwnerId(comment.getId());
									appImage.setOwnerClass("MallProductComment");
									appImage.setUrl(string);
									appImageDao.save(appImage);
								}
							}
						}
					}
				}
			}
		}
		// 只要被请求便将状态改成订单完成
		mallOrder.setOrderStatus(Constant.ORDER_STATUS_5);
		orOrderDao.save(mallOrder);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 商品评价列表
	 */
	@Override
	public DataPageComonResponse<CommentInfoVo> doSelectProductCommentList(CommentIdPageRequest request, HttpServletRequest req) {
		DataPageComonResponse<CommentInfoVo> response = new DataPageComonResponse<>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productId", request.getProdectId());
		map.put("orderBy", "mallProductComment.CREATE_TIME DESC");
		PaginationSupport paginationSupport = mallProductCommentDao.queryPaginationExtend("selectCommentInfoVo", "count", map, request.getPageNo(), request.getPageSize());
		response.getData().setPageCount(paginationSupport.getPageCount());
		response.getData().setTotalCount(paginationSupport.getTotalCount());
		if (null != paginationSupport && CollectionUtils.isNotEmpty(paginationSupport.getItems())) {
			List<CommentInfoVo> list = paginationSupport.getItems();
			for (CommentInfoVo vo : list) {
				List<AppImage> appImages = appImageDao.listImgsByClassAndId("MallProductComment", vo.getId());
				if (CollectionUtils.isNotEmpty(appImages)) {
					List<String> imgUrs = new ArrayList<>();
					for (AppImage appImage : appImages) {
						imgUrs.add(appImage.getUrl());
					}
					vo.setImages(imgUrs);
				}
			}
			response.getData().setItems(paginationSupport.getItems());
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 我的收藏列表
	 */
	@Override
	public DataPageComonResponse<MyCollectionResponse> doSelectMyCollection(TokenPageRequest request, HttpServletRequest req) {
		DataPageComonResponse<MyCollectionResponse> response = new DataPageComonResponse<MyCollectionResponse>();
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customerId", user.getId());
		PaginationSupport paginationSupport = cuCustomerCollectionDao.queryPaginationExtend("queryMyCollection", "count", map, request.getPageNo(), request.getPageSize());
		if (null != paginationSupport && CollectionUtils.isNotEmpty(paginationSupport.getItems())) {
			response.getData().setItems(paginationSupport.getItems());
			response.getData().setPageCount(paginationSupport.getPageCount());
			response.getData().setTotalCount(paginationSupport.getTotalCount());
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 收藏/取消收藏
	 */
	@Override
	public DataItemResponse<Integer> doModifyMyCollection(TokenIdRequest request, HttpServletRequest req) {
		DataItemResponse<Integer> response = new DataItemResponse<>();
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
		CuCustomerCollection info = cuCustomerCollectionDao.queryOne(new QueryFilterBuilder().put("customerId", user.getId()).put("sourceId", request.getId()).build());
		MallProduct mallProduct = mallProductDao.get(request.getId());
		if (null == mallProduct) {
			response.onHandleFail(getMessage(req, "mallApiServiceImpl.doSelectProductDetail.product.nonexistent"));
			return response;
		}
		if (null != info) {
			cuCustomerCollectionDao.delete(info.getId());
			response.setItem(Constant.STATE_NO);
			mallProduct.setProductCoolection(mallProduct.getProductCoolection() - 1 < 0 ? 0 : mallProduct.getProductCoolection() - 1);
		} else {
			info = new CuCustomerCollection();
			info.setCustomerId(user.getId());
			info.setSourceId(request.getId());
			info.setCreateTime(new Date());
			cuCustomerCollectionDao.save(info);
			response.setItem(Constant.STATE_YES);
			mallProduct.setProductCoolection(mallProduct.getProductCoolection() + 1);
		}
		mallProductDao.save(mallProduct);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 批量收藏
	 */
	@Override
	public DataItemResponse<Integer> batchMyCollection(TokenIdsRequest request, HttpServletRequest req) {
		DataItemResponse<Integer> response = new DataItemResponse<>();
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
		for (int i = 0; i < request.getIds().length; i++) {
			String id = request.getIds()[i];
			CuCustomerCollection info = cuCustomerCollectionDao.queryOne(new QueryFilterBuilder().put("customerId", user.getId()).put("sourceId", id).build());
			MallProduct mallProduct = mallProductDao.get(id);
			if (null == mallProduct) {
				response.onHandleFail(getMessage(req, "mallApiServiceImpl.doSelectProductDetail.product.nonexistent"));
				return response;
			}
			if (null != info) {
				response.onHandleFail(getMessage(req, "mallApiServiceImpl.batchMyCollection.comment.agent"));
				return response;
			}
			info = new CuCustomerCollection();
			info.setCustomerId(user.getId());
			info.setSourceId(id);
			info.setCreateTime(new Date());
			cuCustomerCollectionDao.save(info);
			response.setItem(Constant.STATE_YES);
			mallProduct.setProductCoolection(mallProduct.getProductCoolection() + 1);
			mallProductDao.save(mallProduct);
			response.onHandleSuccess();
		}
		return response;
	}

	/**
	 * 添加/修改收获地址
	 */
	@Override
	public DataResponse doSaveMallCustomerAddress(MallCustomerAddressRequest request, HttpServletRequest req) {
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
		CuCustomerAddress addr = new CuCustomerAddress();
		if (null != request.getAddressId() && !"".equals(request.getAddressId())) {
			addr.setId(request.getAddressId());
		}
		if (Constant.STATE_YES == request.getAddressFlag()) {
			List<CuCustomerAddress> list = cuCustomerAddressDao.query(new QueryFilterBuilder().put("customerId", user.getId()).build());
			if (CollectionUtils.isNotEmpty(list)) {
				for (CuCustomerAddress cuCustomerAddress : list) {
					cuCustomerAddress.setAddressFlag(Constant.STATE_NO);
					cuCustomerAddressDao.save(cuCustomerAddress);
				}
			}

		}
		addr.setCustomerId(user.getId());
		addr.setAddressFlag(request.getAddressFlag());
		BeanUtils.copyProperties(request, addr, "addressFlag");
		cuCustomerAddressDao.save(addr);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 查询所有收货地址
	 */
	@Override
	public DataItemResponse<List<CuCustomerAddress>> doSelectMallCustomerAddressList(CustomerAddressRequest request, HttpServletRequest req) {
		DataItemResponse<List<CuCustomerAddress>> response = new DataItemResponse<>();
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
		QueryFilterBuilder params = new QueryFilterBuilder();
		if (null != request.getFlag() && 1 == request.getFlag()) {
			params.put("addressFlag", 1);
		}
		params.put("customerId", user.getId());
		List<CuCustomerAddress> data = cuCustomerAddressDao.query(params.build());
		if (CollectionUtils.isNotEmpty(data)) {
			response.setItem(data);
		}
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 删除收货地址
	 */
	@Override
	public DataResponse delMallCustomerAddress(TokenIdRequest request, HttpServletRequest req) {
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
		CuCustomerAddress address = cuCustomerAddressDao.queryOne(new QueryFilterBuilder().put("id", request.getId()).put("customerId", user.getId()).build());
		if (null == address) {
			response.onHandleFail(getMessage(req, "mallApiServiceImpl.delMallCustomerAddress.address.nonexistent"));
			return response;
		}
		cuCustomerAddressDao.delete(address.getId());
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 设置默认地址
	 */
	@Override
	public DataResponse doSetDefaultAddress(TokenIdRequest request, HttpServletRequest req) {
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
		// 如果是添加默认地址，查询出原有的默认地址进行修改
		CuCustomerAddress address = cuCustomerAddressDao.queryOne(new QueryFilterBuilder().put("customerId", user.getId()).put("addressFlag", Constant.STATE_YES).build());
		if (null != address) {
			address.setAddressFlag(Constant.STATE_NO);
			cuCustomerAddressDao.save(address);
		}
		address = cuCustomerAddressDao.get(request.getId());
		address.setAddressFlag(1);
		cuCustomerAddressDao.save(address);
		response.onHandleSuccess();
		return response;
	}

	/**
	 * 商品添加功能
	 */
	@Override
	public DataResponse doAddProduct(AddProductRequest request, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if(StringUtils.isEmpty(request.getToken())) {
			response.onHandleFail(getMessage(req, "impl.doModifyLoginPass.token.invalid"));
			return response;
		}
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
		if (request.getProductCost().doubleValue() < 0 || request.getProductFreight().doubleValue() < 0 || request.getProductPrice().doubleValue() < 0) {
			response.onHandleFail("价格不能为负数！");
			return response;
		}
		MallShop shop = mallShopDao.queryOne(new QueryFilterBuilder().put("customerId", user.getId()).build());
		if(null == shop) {
			response.onHandleFail("店铺不存在，无法添加商品");
			return response;
		}
		// 成本价不能大于售价
		/*if (vo.getProductCost().doubleValue() > vo.getProductPrice().doubleValue()) {
			formResultSupport.onException("成本价不能大于售价！");
			return formResultSupport;
		}*/
		if (StringUtils.isBlank(request.getKinds())) {
			response.onHandleFail("商品规格不能为空！");
			return response;
		}
		List<KindsVo> kinds = JSON.parseArray(request.getKinds(), KindsVo.class);
		if (CollectionUtils.isEmpty(kinds)) {
			response.onHandleFail("商品规格不能为空！");
			return response;
		}
		List<CombinationVo> combinations = JSON.parseArray(request.getCombinations(), CombinationVo.class);
		if (CollectionUtils.isEmpty(combinations)) {
			response.onHandleFail("商品规格不能为空！");
			return response;
		}
		String productId = request.getProductId();
		MallProduct product = new MallProduct();
		product.id(productId).shopId(shop.getId()).productName(request.getProductName()).productImg(request.getProductImg()).productPrice(request.getProductPrice()).productFirstClassify(request.getProductFirstClassify())
				.productSecondClassify(request.getProductSecondClassify()).productCost(request.getProductCost()).productFreight(request.getProductFreight()).productContext(request.getProductContext())
				.createTime(new Date());
		// 商品总库存
		Integer totalStock = 0;
		// 保存主规格
		for (KindsVo kind : kinds) {
			MallProductKind mallProductKind = new MallProductKind();
			mallProductKind.setId(kind.getId());
			mallProductKind.setProductKindName(kind.getName());
			mallProductKind.setProductKindMosaicOrder(kind.getOrder());
			mallProductKindDao.save(mallProductKind);
			for (int i = 0; i < kind.getKindDetail().size(); i++) {
				MallProductKindDetail kindDetail = new MallProductKindDetail();
				KindDetailVo kindDetailVo = kind.getKindDetail().get(i);
				kindDetail.setId(kindDetailVo.getId());
				kindDetail.setDetailKindId(kind.getId());
				kindDetail.setDetailName(kindDetailVo.getValue());
				kindDetail.setDetailKindValue(kindDetailVo.getValue());
				kindDetail.setDetailDescription(i + "");
				mallProductKindDetailDao.save(kindDetail);
				// 保存商品规格信息
				MallProductKindContrast mallProductKindContrast = new MallProductKindContrast();
				mallProductKindContrast.setId(GuidUtils.generateSimpleGuid());
				mallProductKindContrast.setKindId(kind.getId());
				mallProductKindContrast.setKindDetailId(kindDetailVo.getId());
				mallProductKindContrast.setProductId(productId);
				mallProductKindContrastDao.save(mallProductKindContrast);
			}
		}
		// 保存商品combination
		for (CombinationVo combination : combinations) {
			MallProductKindCombination mallProductKindCombination = new MallProductKindCombination();
			mallProductKindCombination.setId(GuidUtils.generateSimpleGuid());
			mallProductKindCombination.setProductId(productId);
			mallProductKindCombination.setCombinationStockNum(combination.getCombinationStock());
			mallProductKindCombination.combinationImg(combination.getCombinationImg());
			mallProductKindCombination.setCombinationPrice(combination.getCombinationPrice());
			mallProductKindCombination.setCombination(combination.getCombinationStr());
			mallProductKindCombinationDao.save(mallProductKindCombination);
			totalStock += combination.getCombinationStock();
		}
		product.setProductPrice(combinations.get(0).getCombinationPrice());
		product.setProductStock(totalStock);
		product.setProductSale(0);
		mallProductDao.save(product);
		// 保存商品轮播图
		List<String> carouselImg = JSON.parseArray(request.getCarouselImg(), String.class);
		if (CollectionUtils.isNotEmpty(carouselImg)) {
			AppImage appImage = null;
			for (String image : carouselImg) {
				appImage = new AppImage();
				appImage.setOwnerId(product.getId());
				appImage.setOwnerClass("MallProduct");
				appImage.setUrl(image);
				appImageDao.save(appImage);
			}
		}
		response.onHandleSuccess();
		return response;
	}

}
