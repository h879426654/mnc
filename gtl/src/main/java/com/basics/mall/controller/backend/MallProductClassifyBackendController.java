package com.basics.mall.controller.backend;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.basics.mall.entity.MallShop;
import com.basics.mall.service.MallProductClassifyService;
import com.basics.mall.service.MallShopService;
import com.basics.support.*;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basics.app.entity.AppOption;
import com.basics.app.shiro.AppUserUtils;
import com.basics.app.shiro.UserSupport;
import com.basics.mall.entity.MallProductClassify;

@Controller
@RequestMapping("/backend/mall/mallProductClassify/")
public class MallProductClassifyBackendController extends BaseBackendController<MallProductClassify> {

	@Autowired
	private MallProductClassifyService mallProductClassifyService;
	@Autowired
	private MallShopService mallShopService;

	
	@Override
	public void beforeSave(FormResultSupport result, MallProductClassify entity, HttpServletRequest request) {
		UserSupport userSupport = AppUserUtils.getCurrentUserSupport();
		entity.createTime(new Date()).createUser(userSupport.getId()).modifyDate(new Date()).modifyUser(userSupport.getId());
	}


	@Override
	@RequestMapping(value = "/save")
	public void save(MallProductClassify entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			result = mallProductClassifyService.doSave(entity);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	@RequestMapping(value = "firstClassify")
	@ResponseBody
	public DataSourceResponse firstClassify() {
		DataSourceRequest request = new DataSourceRequest();
		MallShop shop = mallShopService.queryByPK(AppUserUtils.getCurrentUserSupport().getId());
		request.and("classifyParentId", FilterOperatorEnum.EQ, AppOption.DEFAULT_PARENT)
				.and("countryId", FilterOperatorEnum.EQ, shop.getCountryId());
		DataSourceResponse dataSource = this.service.getDataSource(request);
		return dataSource;
	}


	@RequestMapping(value = "readFirst", method = RequestMethod.POST)
	@ResponseBody
	public DataSourceResponse readFirst(@RequestBody DataSourceRequest request) {
		request.and("classifyParentId", FilterOperatorEnum.EQ, AppOption.DEFAULT_PARENT);
		return this.service.getDataSource(request);
	}

	
	
	@RequestMapping(value = "secondClassify")
	@ResponseBody
	public DataSourceResponse secondClassify(@RequestBody DataSourceRequest request) {
		DataSourceResponse dataSource = this.service.getDataSource(request);
		return dataSource;
	}
	
	@RequestMapping(value = "newSecondClassify")
	@ResponseBody
	public List<MallProductClassify> newSecondClassify(String productFirstClassify) {
		return this.service.query(new QueryFilterBuilder().put("classifyParentId", productFirstClassify).build());
	}

	@RequestMapping(value = "getFirstClassify")
	public void getFirstClassify(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.put("classifyParentId", AppOption.DEFAULT_PARENT).orderBy("classifySort").build();
		swgrid(gridPager, builder, this.service, request, response);
	}

	@RequestMapping(value = "getSecondClassify")
	public void getSecondClassify(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.orderBy("classifySort").build();
		swgrid(gridPager, builder, this.service, request, response);
	}

}
