package com.basics.app.service;

import java.util.List;

import com.basics.app.entity.AppPermission;
import com.basics.app.entity.AppRole;
import com.basics.app.entity.RolesAndPermissions;
import com.basics.app.entity.UsersAndRoles;
import com.basics.support.ItemResultSupport;

public interface AppUserRolePermissionService {

 /**
  * 为指定的角色分配用户.
  * 
  * @param userAndRoles
  * @return
  */
 public ItemResultSupport<UsersAndRoles> saveUsersForRoles(UsersAndRoles userAndRoles);

 /**
  * 获取用户分配的角色.
  * 
  * @param userIdOrCode
  * @return
  */
 public ItemResultSupport<String> getUserRoleIds(String userIdOrCode);

 /**
  * 获取角色分配的权限.
  * 
  * @param roleIdOrCode
  * @return
  */
 public ItemResultSupport<String> getRolePermissions(String roleIdOrCode);

 /**
  * 为指定的角色分配权限.
  * 
  * @param rolesAndPermissions
  * @return
  */
 public ItemResultSupport<RolesAndPermissions> saveRolePermissions(RolesAndPermissions rolesAndPermissions);

 /**
  * 获取用户分配的角色.
  * 
  * @param userId
  * @return
  */
 public List<AppRole> getUserRoles(String userId);

 /**
  * 获取用户分配的权限.
  * 
  * @param userId
  * @return
  */
 public List<AppPermission> getUserPermissions(String userId);

 /**
  * 
  * @param code
  * @param name
  * @return
  */
 public AppRole saveOrGetRole(String code, String name);
}
