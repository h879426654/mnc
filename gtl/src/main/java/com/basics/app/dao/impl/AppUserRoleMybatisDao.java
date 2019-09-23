package com.basics.app.dao.impl;

import org.springframework.stereotype.Repository;

import com.basics.app.dao.AppUserRoleDao;
import com.basics.app.entity.AppUserRole;
import com.basics.app.support.GenericMybatisDaoSupport;
import com.basics.support.CommonSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import com.basics.support.QueryFilterBuilder;

@Repository
public class AppUserRoleMybatisDao extends GenericMybatisDaoSupport<AppUserRole> implements AppUserRoleDao {

 public AppUserRoleMybatisDao() {
  super();
  this.setPrimaryKeyFields("userId,roleId");
 }

 public INameMapper onBuildNameMapper() {
  DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
  defaultNameMapper.configFieldColumnName("userId", "USER_ID");
  defaultNameMapper.configFieldColumnName("roleId", "ROLE_ID");
  return defaultNameMapper;
 }

 /**
  * Save user roles.
  *
  * @param userId
  *         the user id
  * @param roleIds
  *         the role ids
  */
 public void saveUserRoles(String userId, String... roleIds) {
  CommonSupport.checkNotNull(userId, "用户不允许为空");
  CommonSupport.checkNotNull(roleIds, "角色不允许为空");
  this.deleteAll(new QueryFilterBuilder().put("userId", userId).build().getParams());
  for (String roleId : roleIds) {
   this.save(new AppUserRole().userId(userId).roleId(roleId));
  }
 }

}
