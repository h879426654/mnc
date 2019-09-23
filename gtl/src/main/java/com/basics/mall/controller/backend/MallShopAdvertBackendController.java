package com.basics.mall.controller.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basics.app.service.AppImageService;
import com.basics.common.Constant;
import com.basics.mall.entity.MallShopAdvert;
import com.basics.mall.service.MallShopAdvertService;
import com.basics.support.DataSourceResponse;
import com.basics.support.FormResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.ServletUtils;

@Controller
@RequestMapping("/backend/mall/mallShopAdvert/")
public class MallShopAdvertBackendController extends BaseBackendController<MallShopAdvert> {

	@Autowired
	private MallShopAdvertService mallShopAdvertService;
	@Autowired
	private AppImageService appImageService;
	
	
	@RequestMapping(value = "/apply")
	public String applyView() {
		return getView("apply");
	}
	
	@Override
	public void willQuery(QueryFilterBuilder builder) {
		builder.put("applyStatus", Constant.APPLY_STATUS_2).orderBy("mallShopAdvert.CREATE_TIME DESC");
	}
	
	/**
	 * 商品列表
	 */
	@RequestMapping(value = "queryAdvertInfoByApply", method = RequestMethod.POST)
	public void queryAdvertInfoByApply(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.put("applyStatus", Constant.APPLY_STATUS_1).orderBy("createTime DESC").build();
		swgrid(gridPager, builder, this.service, "query", "count", request, response);
	}

	@Override
	@RequestMapping(value = "/save")
	public void save(MallShopAdvert entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			this.beforeSave(result, entity, request);
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			result = mallShopAdvertService.doSave(entity);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delShopAdvert")
	public String delShopAdvert(MallShopAdvert mallShopAdvert) {
		return mallShopAdvertService.delShopAdvert(mallShopAdvert);
	}
	
	/**
	 * 查看用户信息
	 */
	@RequestMapping(value = "findImgs/{id}")
	@ResponseBody
	public DataSourceResponse findImgs(@PathVariable("id") String id, HttpServletResponse response) {
		DataSourceResponse result = new DataSourceResponse();
		result.setTotal(1);
		result.setData(appImageService.listImgsByClassAndId("MallShopAdvert", id));
		return result;
	}

}
