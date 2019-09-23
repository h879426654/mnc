package com.basics.app.controller.backend;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basics.app.entity.AppPermission;
import com.basics.app.service.AppPermissionService;
import com.basics.app.ui.AppPermissionZTreeDataProvider;
import com.basics.support.DataSourceRequest;
import com.basics.support.DataSourceResponse;
import com.basics.support.FilterOperatorEnum;
import com.basics.support.ItemResultSupport;
import com.basics.support.ServletUtils;
import com.basics.support.ztree.ZTreeBuilder;
import com.basics.support.ztree.ZTreeNode;

@Controller
@RequestMapping("/backend/app/appPermission/")
public class AppPermissionBackendController extends BaseBackendController<AppPermission> {

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
 
 @RequestMapping(value = "/icon")
 public String icon() {
  return getView("icon");
 }

 public String getIconCls(AppPermission item) {
  return item.getIconCls();
 }

 @RequestMapping(value = "treeForCurrentUser")
 public void treeForCurrentUser(@RequestParam(value = "id", required = false) String parentId, HttpServletRequest request,
  HttpServletResponse response) {
  // this.renderTree(parentId, new QueryFilterBuilder().put("state",
  // 1).put("userId", this.getCurrentUserObject(request).getId()), request,
  // response);
 }

 @RequestMapping(value = "/treeselect")
 public void treeselect(@RequestParam(value = "id", required = false) String parentId, HttpServletRequest request,
  HttpServletResponse response) {
  // this.renderTreeselect(parentId, new QueryFilterBuilder().put("state", 1),
  // request, response);
 }

 @Override
 @RequiresPermissions("app:appPermission:index")
 @RequestMapping(value = "/index")
 public String index() {
  return super.index();
 }

 @Override
 @RequiresPermissions("app:appPermission:index")
 @RequestMapping(value = "/save")
 public void save(AppPermission entity, HttpServletRequest request, HttpServletResponse response) {
  super.save(entity, request, response);
 }

 @Autowired
 AppPermissionService appPermissionService;

 @RequestMapping(value = "/ztree")
 public void ztree(HttpServletRequest request, HttpServletResponse response) {
  ItemResultSupport<List<ZTreeNode>> result = new ItemResultSupport<List<ZTreeNode>>();
  try {
   AppPermissionZTreeDataProvider dataProvider = new AppPermissionZTreeDataProvider(appPermissionService);
   result.setItem(new ZTreeBuilder<AppPermission>().build(dataProvider));
  } catch (Throwable e) {
   result.onException(e);
  }
  ServletUtils.renderJsonAsText(response, result);
 }

 @RequestMapping(value = "permissions/{parentId}")
 @ResponseBody
 public DataSourceResponse permissions(@PathVariable("parentId") String parentId, HttpServletResponse response) {
  DataSourceRequest request = new DataSourceRequest();
  if (StringUtils.isBlank(parentId)) {
   parentId = "_";
  }
  request.and("parentId", FilterOperatorEnum.EQ, parentId);
  DataSourceResponse dataSource = this.service.getDataSource(request);
  return dataSource;
 }

 @RequestMapping(value = "permissions")
 @ResponseBody
 public DataSourceResponse permissions() {
  DataSourceRequest request = new DataSourceRequest();
  request.and("parentId", FilterOperatorEnum.EQ, AppPermission.ROOT_ID);
  DataSourceResponse dataSource = this.service.getDataSource(request);
  return dataSource;
 }
}
