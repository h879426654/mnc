package com.basics.app.controller.backend.org;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basics.app.controller.backend.BaseBackendController;
import com.basics.app.entity.AppRole;
import com.basics.app.entity.RolesAndPermissions;
import com.basics.app.service.AppUserRolePermissionService;
import com.basics.support.ItemResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.ServletUtils;

import jodd.bean.BeanUtil;

@Controller("/backend/app/appRole/org")
@RequestMapping("/backend/app/appRole/org")
public class AppRoleBackendController extends BaseBackendController<AppRole> {

 @Override
 public String getUiTextField() {
  return "name";
 }

 @RequestMapping(value = "/tree")
 public void tree(@RequestParam(value = "id", required = false) String parentId, HttpServletRequest request, HttpServletResponse response) {
  JSONArray tree = new JSONArray();
  if (StringUtils.isBlank(parentId)) {
   List<AppRole> list = this.urpService.getUserRoles(this.getCurrentUserObject(request).getId());
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
}
