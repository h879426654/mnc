package com.basics.app.dao;

import java.util.List;

import com.basics.app.entity.AppPermission;
import com.basics.app.entity.AppRolePermission;
import com.basics.support.GenericDao;

public interface AppRolePermissionDao extends GenericDao<AppRolePermission> {

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
 public void saveRolePermissions(String roleId, String permissions, boolean clearBeforeSave);

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
 public void saveRolePermissions(String roleId, List<AppPermission> permissions, boolean clearBeforeSave);
}
