package com.basics.app.controller.backend;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basics.app.entity.AppArea;
import com.basics.app.entity.AppOption;
import com.basics.support.DataSourceRequest;
import com.basics.support.DataSourceResponse;
import com.basics.support.FilterOperatorEnum;

@Controller
@RequestMapping("/backend/app/appArea/")
public class AppAreaBackendController extends BaseBackendController<AppArea> {
	@Override
	public String getUiTextField() {
		return "name";
	}

	@Override
	public String getUiParentIdField() {
		return "parentId";
	}

	@RequestMapping(value = "options/{parentId}")
	@ResponseBody
	public DataSourceResponse options(@PathVariable("parentId") String parentId, HttpServletResponse response) {
		DataSourceRequest request = new DataSourceRequest();
		if (StringUtils.isBlank(parentId)) {
			parentId = "_";
		}
		request.and("parentId", FilterOperatorEnum.EQ, parentId);
		DataSourceResponse dataSource = this.service.getDataSource(request);
		return dataSource;
	}

	@RequestMapping(value = "provice")
	@ResponseBody
	public DataSourceResponse provice() {
		DataSourceRequest request = new DataSourceRequest();
		request.and("parentId", FilterOperatorEnum.EQ, AppOption.DEFAULT_PARENT);
		DataSourceResponse dataSource = this.service.getDataSource(request);
		return dataSource;
	}

	@RequestMapping(value = "city")
	@ResponseBody
	public DataSourceResponse city(@RequestBody DataSourceRequest request) {
		DataSourceResponse dataSource = this.service.getDataSource(request);
		return dataSource;
	}

	@RequestMapping(value = "area")
	@ResponseBody
	public DataSourceResponse area(@RequestBody DataSourceRequest request) {
		DataSourceResponse dataSource = this.service.getDataSource(request);
		return dataSource;
	}
}
