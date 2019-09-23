package com.basics.mall.controller.backend;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basics.app.shiro.AppUserUtils;
import com.basics.app.shiro.UserSupport;
import com.basics.common.Constant;
import com.basics.mall.controller.request.AddProductRequest;
import com.basics.mall.entity.MallProduct;
import com.basics.mall.service.MallProductService;
import com.basics.mall.vo.ProductCombinationVo;
import com.basics.support.DataSourceRequest;
import com.basics.support.DataSourceResponse;
import com.basics.support.FilterDescriptor;
import com.basics.support.FilterOperatorEnum;
import com.basics.support.FormResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.ResultSupport;
import com.basics.support.ServletUtils;
import com.basics.support.dlshouwen.Pager;

@Controller
@RequestMapping("/backend/mall/mallProduct/")
public class MallProductBackendController extends BaseBackendController<MallProduct> {
	@Autowired
	private MallProductService mallProductService;

	/**
	 * 商品添加页面
	 */
	@RequestMapping(value = "addView", method = RequestMethod.GET)
	public String addView() {
		return getView("add");
	}

	@Override
	public void willQuery(QueryFilterBuilder builder) {
		builder.put("shopId", AppUserUtils.getCurrentUserSupport().getId()).put("flagDel", Constant.STATE_NO).orderBy("createTime DESC");
	}

	/**
	 * 商品列表
	 */
	@RequestMapping(value = "showProductBusinessById", method = RequestMethod.POST)
	public void showProductBusinessById(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		UserSupport user = AppUserUtils.getCurrentUserSupport();
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.put("shopId", user.getId()).orderBy("createTime DESC").put("flagDel", Constant.STATE_NO).build();
		swgrid(gridPager, builder, mallProductService, "showProductBusinessById", "countProductBusinessById", request, response);
	}

	/**
	 * 商品添加功能
	 */
	@RequestMapping(value = "addProduct2", method = RequestMethod.POST)
	public void subAdd(AddProductRequest vo, HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult) {
		FormResultSupport formResultSupport = new FormResultSupport();
		if (bindingResult.hasErrors()) {
			formResultSupport.onException("请检查填写字段！");
		} else {
			try {
				formResultSupport = mallProductService.doAddGoods2(vo);
			} catch (Throwable t) {
				t.printStackTrace();
				formResultSupport.onException(t);
			}
		}
		ServletUtils.renderJsonAsText(response, formResultSupport);
	}

	/**
	 * 删除商品(逻辑)
	 */
	@Override
	@RequestMapping(value = "/del")
	public void del(String id, HttpServletRequest request, HttpServletResponse response) {
		ResultSupport result = new ResultSupport();
		try {
			String[] array = StringUtils.split(id, ",");
			if (array == null || array.length == 0) {
				result.onException("请指定删除的商品");
			} else {
				service.updateByIds(array, "flagDel", Constant.STATE_YES + "");
				result.onSuccess("删除成功");
			}
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException("删除失败");
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 商品修改
	 */
	@RequestMapping("/productModify")
	public void productModify(ProductCombinationVo combinationVo, HttpServletResponse response) {
		FormResultSupport formResultSupport = new FormResultSupport();
		try {
			formResultSupport = mallProductService.doProductModify(combinationVo);
		} catch (Throwable t) {
			t.printStackTrace();
			formResultSupport.onException(t);
		}
		ServletUtils.renderJsonAsText(response, formResultSupport);
	}

	/**
	 * 商品详情
	 */
	@RequestMapping("/getProductDetail")
	public void getProductDetail(String gridPager, HttpServletResponse response) {
		Pager pager = new Pager();
		try {
			pager = mallProductService.getProductDetail(gridPager);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		ServletUtils.renderJsonAsText(response, pager);
	}

	@RequestMapping("/getProducts")
	@ResponseBody
	public DataSourceResponse secondClassify(@RequestBody DataSourceRequest request) {
		List<FilterDescriptor> filters = request.getFilter().getFilters();
		FilterDescriptor filterDescriptor = filters.get(0);
		filters.clear();
		request.and("productSecondClassify", FilterOperatorEnum.EQ, filterDescriptor.getValue()).and("flagDel", FilterOperatorEnum.EQ, Constant.STATE_NO).and("productStatus", FilterOperatorEnum.EQ, Constant.PRODUCT_STATUS_SALE);
		return mallProductService.getDataSource(request);
	}

	/**
	 * 所有商品页面
	 */
	@RequestMapping(value = "all", method = RequestMethod.GET)
	public String allProduct() {
		return getView("all");
	}

	@RequestMapping(value = "swgridByAdmin", method = RequestMethod.POST)
	public void swgrid(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.put("flagDel", Constant.STATE_NO).orderBy("createTime DESC");
		this.swgrid(gridPager, builder, this.service, request, response);
	}
}
