package com.basics.app.controller.backend;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basics.app.entity.AppOption;
import com.basics.support.DataSourceRequest;
import com.basics.support.DataSourceResponse;
import com.basics.support.FilterOperatorEnum;

@Controller
@RequestMapping("/backend/app/appOption/")
public class AppOptionBackendController extends BaseBackendController<AppOption> {

 @Override
 public String getUiTextField() {
  return "name";
 }

 @Override
 public String getUiParentIdField() {
  return "parentId";
 }

 @Override
 public String getUiAttributesField() {
  return "url";
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

 @RequestMapping(value = "options/{parentId}/{pageNum}/{pageSize}")
 @ResponseBody
 public DataSourceResponse options(@PathVariable("parentId") String parentId, @PathVariable("pageNum") Integer pageNum,
  @PathVariable("pageSize") Integer pageSize, HttpServletResponse response) {
  DataSourceRequest request = new DataSourceRequest();
  if (StringUtils.isBlank(parentId)) {
   parentId = "_";
  }
  request.and("parentId", FilterOperatorEnum.EQ, parentId);
  if (null != pageNum && pageNum > 0 && null != pageSize && pageSize > 0) {
   request.setPage(pageNum);
   request.setPageSize(pageSize);
  }
  DataSourceResponse dataSource = this.service.getDataSource(request);
  return dataSource;
 }

 @RequestMapping(value = "options")
 @ResponseBody
 public DataSourceResponse options() {
  DataSourceRequest request = new DataSourceRequest();
  request.and("parentId", FilterOperatorEnum.EQ, AppOption.DEFAULT_PARENT);
  DataSourceResponse dataSource = this.service.getDataSource(request);
  return dataSource;
 }
}
