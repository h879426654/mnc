package com.basics.app.controller.backend.org;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.app.controller.backend.BaseBackendController;
import com.basics.app.entity.AppUser;
import com.basics.app.entity.UsersAndRoles;
import com.basics.app.service.AppRoleService;
import com.basics.app.service.AppUserRolePermissionService;
import com.basics.support.FormResultSupport;
import com.basics.support.ItemResultSupport;
import com.basics.support.ListResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.ServletUtils;

@Controller("/backend/app/appUser/org")
@RequestMapping("/backend/app/appUser/org")
public class AppUserBackendController extends BaseBackendController<AppUser> {

 @Override
 public String getBaseViewDirName() {
        return "com.basics.app.backend/org";
 }

 public void beforeList(AppUser entity, HttpServletRequest request) {
  AppUser user = this.getCurrentUserObject(request);
  // 按组织路径过滤，实现上级部门可以查看下级部门
  // entity.setOrgPathStartWith(user.getOrgPath());
  // 按组织过滤
  entity.setOrgId(user.getOrgId());
  entity.setType(AppUser.TYPE_ADMIN);
 }

 @Autowired
 AppRoleService roleService;

 public void didList(ListResultSupport result, HttpServletRequest request, HttpServletResponse response) {
  if (result.isSuccess()) {
   List<AppUser> items = result.getRows();
   for (AppUser item : items) {
    if (StringUtils.isBlank(item.getRoleNames())) {
     item.setRoleNames(roleService.getUserRoleNames(item.getId()));
    }
   }
  }
 }

 @Override
 public void beforeSave(FormResultSupport result, AppUser entity, HttpServletRequest request) {
  entity.setType(AppUser.TYPE_ADMIN);
  super.beforeSave(result, entity, request);
 }

 @Override
 public String getUiTextField() {
  return "name";
 }

 @Autowired
 AppUserRolePermissionService urpService;

 @RequestMapping(value = "getUserRoles")
 public void getUserRoles(String user, HttpServletRequest request, HttpServletResponse response) {
  ItemResultSupport<String> result = new ItemResultSupport<String>();
  try {
   result = this.urpService.getUserRoleIds(user);
  } catch (Throwable e) {
   result.onException(e);
   LogUtils.performance.error("{}", e);
  }
  ServletUtils.renderJsonAsText(response, result);
 }

 @RequiresPermissions("app:appUser:index")
 @RequestMapping(value = "saveUsersForRoles")
 public void saveUsersForRoles(UsersAndRoles usersAndRoles, HttpServletRequest request, HttpServletResponse response) {
  ItemResultSupport<UsersAndRoles> result = new ItemResultSupport<UsersAndRoles>();
  try {
   result = this.urpService.saveUsersForRoles(usersAndRoles);
  } catch (Throwable e) {
   result.onException(e);
  }
  ServletUtils.renderJsonAsText(response, result);
 }

}
