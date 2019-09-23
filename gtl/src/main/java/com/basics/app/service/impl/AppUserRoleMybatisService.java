package com.basics.app.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.basics.app.entity.AppUserRole;
import com.basics.app.service.AppUserRoleService;
import com.basics.support.GenericMybatisService;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilterBuilder;

@Service
public class AppUserRoleMybatisService extends GenericMybatisService<AppUserRole> implements AppUserRoleService {

 public void saveUserAndRole(String userId, String roleId) {
  if (StringUtils.isBlank(userId) || StringUtils.isBlank(roleId)) {
   return;
  }
  AppUserRole userRole = new AppUserRole();
  userRole.setUserId(userId);
  userRole.setRoleId(roleId);
  if (this.count(new QueryFilterBuilder().put("userId", userId).put("roleId", roleId).build().getParams()) == 0) {
   LogUtils.performance.info("saveUserAndRole: userId={} roleId={}", userId, roleId);
   this.dao.insert(userRole);
  } else {
   LogUtils.performance.info("saveUserAndRole: userId={} roleId={} existed.", userId, roleId);
  }
 }

 public void deleteUserRole(String userId) {
  if (StringUtils.isBlank(userId)) {
   return;
  }
  this.dao.deleteAll(new QueryFilterBuilder().put("userId", userId).build().getParams());
 }
}
