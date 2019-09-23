package com.basics.mall.controller.api;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
import com.basics.mall.service.MallApiService;
import com.basics.mall.vo.CommentInfoVo;

@RestController
@RequestMapping("/api/mall/")
public class MallApiController implements ApplicationContextAware {

	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;
	@Autowired
	private MallApiService mallApiService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 查询商品分类
	 */
	@RequestMapping("selectProductClassifyList")
	public DataItemResponse<List<MallProductClassify>> selectProductClassifyList(TokenIdRequest request, HttpServletRequest req) {
		DataItemResponse<List<MallProductClassify>> response = new DataItemResponse<>();
		try {
			response = mallApiService.doSelectProductClassifyList(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 商城首页模块
	 */
	@RequestMapping("selectIndexProduct")
	public DataItemResponse<Map<String, Object>> selectIndexGoods() {
		DataItemResponse<Map<String, Object>> response = new DataItemResponse<>();
		try {
			response = mallApiService.doSelectIndexProct();
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 查询商品列表
	 */
	@RequestMapping("selectMallProductList")
	public DataItemResponse<MallProductResponsePlus> selectMallProductList(@Valid MallProductRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<MallProductResponsePlus> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallApiService.doSelectMallProductList(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 商品详情
	 */
	@RequestMapping("selectProductDetail")
	public DataItemResponse<MallProductDetailResponse> selectProductDetail(@Valid TokenIdRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<MallProductDetailResponse> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallApiService.doSelectProductDetail(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 评价商品
	 */
	@RequestMapping("pushProductComment")
	public DataResponse pushProductComment(@Valid PushProductCommentRequestList request, BindingResult result, HttpServletRequest req) throws IOException {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallApiService.doPushProductComment(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 商品评价列表
	 */
	@RequestMapping("selectProductCommentList")
	public DataPageComonResponse<CommentInfoVo> selectProductCommentList(@Valid CommentIdPageRequest request, BindingResult result, HttpServletRequest req) {
		DataPageComonResponse<CommentInfoVo> response = new DataPageComonResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallApiService.doSelectProductCommentList(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 我的收藏列表
	 */
	@RequestMapping("selectMyCollection")
	public DataPageComonResponse<MyCollectionResponse> selectMyCollection(@Valid TokenPageRequest request, BindingResult result, HttpServletRequest req) {
		DataPageComonResponse<MyCollectionResponse> response = new DataPageComonResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallApiService.doSelectMyCollection(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 收藏/取消收藏
	 */
	@RequestMapping("modifyMyCollection")
	public DataItemResponse<Integer> modifyMyCollection(@Valid TokenIdRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<Integer> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallApiService.doModifyMyCollection(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 批量收藏
	 */
	@RequestMapping("batchMyCollection")
	public DataItemResponse<Integer> batchMyCollection(@Valid TokenIdsRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<Integer> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallApiService.batchMyCollection(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 添加/修改收获地址
	 */
	@RequestMapping("saveMallCustomerAddress")
	public DataResponse saveMallCustomerAddress(@Valid MallCustomerAddressRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallApiService.doSaveMallCustomerAddress(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 查询所有收货地址
	 */
	@RequestMapping("selectMallCustomerAddressList")
	public DataItemResponse<List<CuCustomerAddress>> selectMallCustomerAddress(@Valid CustomerAddressRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<List<CuCustomerAddress>> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallApiService.doSelectMallCustomerAddressList(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 删除收获地址
	 */
	@RequestMapping("delMallCustomerAddress")
	public DataResponse delMallCustomerAddress(@Valid TokenIdRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallApiService.delMallCustomerAddress(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 设置默认地址
	 */
	@RequestMapping("setDefultAdress")
	public DataResponse doSetDefaultAddress(@Valid TokenIdRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallApiService.doSetDefaultAddress(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	/**
	 * 商品添加功能
	 */
	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
	public DataResponse addProduct(@Valid AddProductRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = mallApiService.doAddProduct(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	
	
	

}
