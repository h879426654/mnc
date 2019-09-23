package com.basics.app.controller.backend;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basics.app.entity.AppRole;
import com.basics.app.entity.RolesAndPermissions;
import com.basics.app.service.AppRoleService;
import com.basics.app.service.AppUserRolePermissionService;
import com.basics.app.ui.AppRoleZTreeDataProvider;
import com.basics.support.ItemResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.ResultSupport;
import com.basics.support.ServletUtils;
import com.basics.support.ztree.ZTreeBuilder;
import com.basics.support.ztree.ZTreeNode;

import jodd.bean.BeanUtil;

@Controller
@RequestMapping("/backend/app/appRole/")
@RequiresPermissions("app:appRole:index")
public class AppRoleBackendController extends BaseBackendController<AppRole> {

 @Override
 public String getUiTextField() {
  return "name";
 }

 @RequestMapping(value = "/tree")
 public void tree(@RequestParam(value = "id", required = false) String parentId, HttpServletRequest request, HttpServletResponse response) {
  JSONArray tree = new JSONArray();
  if (StringUtils.isBlank(parentId)) {
   List<AppRole> list = this.service.query(new QueryFilterBuilder().put("state", 1).build());
   for (AppRole item : list) {
    JSONObject node = new JSONObject();
    node.put("id", BeanUtil.getPropertySilently(item, this.getUiIdField()));
    node.put("text", BeanUtil.getPropertySilently(item, this.getUiTextField()));
    node.put("iconCls", "icon-folder");

    tree.add(node);
   }
  }
  ServletUtils.renderJsonAsText(response, tree);
 }

 @Autowired
 AppUserRolePermissionService urpService;
/*
修改角色权限
 */
 @RequestMapping(value = "getRolePermissions")
 public void getRolePermissions(String role, HttpServletRequest request, HttpServletResponse response) {
  ItemResultSupport<String> result = new ItemResultSupport<String>();
  try {
   result = this.urpService.getRolePermissions(role);
  } catch (Throwable e) {
   result.onException(e);
   LogUtils.performance.error("{}", e);
  }
  ServletUtils.renderJsonAsText(response, result);
 }
/*
保存角色权限
 */
 @RequestMapping(value = "saveRolePermissions")
 public void saveRolePermissions(RolesAndPermissions rolesAndPermissions, HttpServletRequest request, HttpServletResponse response) {
  ItemResultSupport<RolesAndPermissions> result = new ItemResultSupport<RolesAndPermissions>();
  try {
   result = this.urpService.saveRolePermissions(rolesAndPermissions);
  } catch (Throwable e) {
   result.onException(e);
  }
  ServletUtils.renderJsonAsText(response, result);
 }
/*
验证码
 */
 @RequestMapping(value = "validateCode")
 public void validateCode(AppRole entity, HttpServletRequest request, HttpServletResponse response) {
  ResultSupport result = new ResultSupport();
  try {
   if (StringUtils.isBlank(entity.getCode())) {
    throw new RuntimeException("编码不允许为空");
   }
   if (StringUtils.isBlank(entity.getCode())) {
    throw new RuntimeException("编码不允许为空");
   }
   AppRole existed = this.service.queryOne(new QueryFilterBuilder().put("code", entity.getCode()).build());
   if (existed != null && !existed.getId().equals(entity.getId())) {
    throw new RuntimeException("编码" + existed.getCode() + "已经存在");
   }
  } catch (Throwable e) {
   result.onException(e.getMessage());
  }
  ServletUtils.renderJsonAsText(response, result);
 }

 @Autowired
 AppRoleService appRoleService;
/*
树插件
 */
 @RequestMapping(value = "ztree")
 public void ztree(HttpServletRequest request, HttpServletResponse response) {
  ItemResultSupport<List<ZTreeNode>> result = new ItemResultSupport<List<ZTreeNode>>();
  try {
   AppRoleZTreeDataProvider dataProvider = new AppRoleZTreeDataProvider(appRoleService);
   result.setItem(new ZTreeBuilder<AppRole>().build(dataProvider));
  } catch (Throwable e) {
   result.onException(e);
  }
  ServletUtils.renderJsonAsText(response, result);
 }
}
