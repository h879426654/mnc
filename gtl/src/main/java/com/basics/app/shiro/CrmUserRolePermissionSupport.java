package com.basics.app.shiro;

import java.util.List;

import com.basics.support.ItemResultSupport;
import com.basics.support.QueryFilter;

// TODO: Auto-generated Javadoc
/**
 * UserRolePermissionSupport.
 */
public interface CrmUserRolePermissionSupport {

 /**
  * 为指定的角色分配用户.
  *
  * @param userAndRoles
  *         the user and roles
  * @return the item result support< users and roles>
  */
 public ItemResultSupport<UsersAndRoles> saveUsersForRoles(UsersAndRoles userAndRoles);

 /**
  * 获取用户分配的角色.
  *
  * @param userIdOrCode
  *         the user id or code
  * @return the user roles
  */
 public ItemResultSupport<String> getUserRoles(String userIdOrCode);

 /**
  * 获取角色分配的权限.
  *
  * @param roleIdOrCode
  *         the role id or code
  * @return the role permissions
  */
 public ItemResultSupport<String> getRolePermissions(String roleIdOrCode);

 /**
  * 为指定的角色分配权限.
  *
  * @param rolesAndPermissions
  *         the roles and permissions
  * @return the item result support< roles and permissions>
  */
 public ItemResultSupport<RolesAndPermissions> saveRolePermissions(RolesAndPermissions rolesAndPermissions);

 /**
  * 获取用户分配的角色.
  *
  * @param userId
  *         the user id
  * @return the user roles by user id
  */
 public List<RoleSupport> getUserRolesByUserId(String userId);

 /**
  * 获取用户分配的所有权限.
  *
  * @param userId
  *         the user id
  * @return the user permissions
  */
 public List<PermissionSupport> getUserPermissions(String userId);

 /**
  * 获取用户分配的权限.
  *
  * @param userId
  *         the user id
  * @param parentId
  *         the parent id
  * @param type
  *         the type
  * @return the user permissions
  */
 public List<PermissionSupport> getUserPermissions(String userId, String parentId, int type);

 /**
  * Gets the permissions.
  *
  * @param filter
  *         the filter
  * @return the permissions
  */
 public List<PermissionSupport> getPermissions(QueryFilter filter);

 /**
  * Gets the systems.
  *
  * @param userId
  *         the user id
  * @return the systems
  */
 public List<PermissionSupport> getUserSystems(String userId);

 /**
  * Find by username.
  *
  * @param username
  *         the username
  * @return the user support
  */
 public UserSupport findByUsername(String username);
}
