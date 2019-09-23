package com.basics.app.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.basics.app.dao.AppRolePermissionDao;
import com.basics.app.entity.AppPermission;
import com.basics.app.entity.AppRolePermission;
import com.basics.app.support.GenericMybatisDaoSupport;
import com.basics.support.CommonSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import com.basics.support.QueryFilterBuilder;

/**
 * AppRolePermissionMybatisDao.
 */
@Repository
public class AppRolePermissionMybatisDao extends GenericMybatisDaoSupport<AppRolePermission> implements AppRolePermissionDao {

 /**
  * The Constructor.
  */
 public AppRolePermissionMybatisDao() {
  super();
  this.setPrimaryKeyFields("roleId,permissionId");
 }

    /*
     * (non-Javadoc)
     * 
     * @see com.basics.support.GenericMybatisDao#onBuildNameMapper()
     */
 public INameMapper onBuildNameMapper() {
  DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
  defaultNameMapper.configFieldColumnName("roleId", "ROLE_ID");
  defaultNameMapper.configFieldColumnName("permissionId", "PERMISSION_ID");
  return defaultNameMapper;
 }

 /**
  * Save role permissions.
  *
  * @param roleId
  *         the role id
  * @param permissions
  *         the permissions
  * @param clearBeforeSave
  *         the clear before save
  */
 public void saveRolePermissions(String roleId, String permissions, boolean clearBeforeSave) {
  CommonSupport.checkState(!StringUtils.isBlank(roleId), "roleId not allow blank.");
  if (clearBeforeSave) {
   this.deleteAll(new QueryFilterBuilder().put("roleId", roleId).build().getParams());
  }
  String[] permissionIds = StringUtils.split(permissions, ",");
  if (permissionIds == null) {
   return;
  }
  for (String permissionId : permissionIds) {
   if (StringUtils.isNotBlank(permissionId)) {
    this.save(new AppRolePermission().roleId(roleId).permissionId(permissionId));
   }
  }
 }

 /**
  * Save role permissions.
  *
  * @param roleId
  *         the role id
  * @param permissions
  *         the permissions
  * @param clearBeforeSave
  *         the clear before save
  */
 public void saveRolePermissions(String roleId, List<AppPermission> permissions, boolean clearBeforeSave) {
  CommonSupport.checkState(!StringUtils.isBlank(roleId), "roleId not allow blank.");
  if (clearBeforeSave) {
   this.deleteAll(new QueryFilterBuilder().put("roleId", roleId).build().getParams());
  }
  for (AppPermission permission : permissions) {
   String permissionId = permission.getId();
   if (StringUtils.isNotBlank(permissionId)) {
    this.save(new AppRolePermission().roleId(roleId).permissionId(permissionId));
   }
  }
 }
}
