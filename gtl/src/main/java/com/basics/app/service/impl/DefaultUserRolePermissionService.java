package com.basics.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basics.app.entity.AppPermission;
import com.basics.app.entity.AppRole;
import com.basics.app.entity.AppRolePermission;
import com.basics.app.entity.AppUser;
import com.basics.app.entity.AppUserRole;
import com.basics.app.entity.RolesAndPermissions;
import com.basics.app.entity.UsersAndRoles;
import com.basics.app.service.AppPermissionService;
import com.basics.app.service.AppRolePermissionService;
import com.basics.app.service.AppRoleService;
import com.basics.app.service.AppUserRolePermissionService;
import com.basics.app.service.AppUserRoleService;
import com.basics.app.service.AppUserService;
import com.basics.app.shiro.AuthorizationInfoCacheCleanSupport;
import com.basics.support.ItemResultSupport;
import com.basics.support.QueryFilterBuilder;

@Service
public class DefaultUserRolePermissionService implements AppUserRolePermissionService {

 @Autowired
 protected AppUserService userService;
 @Autowired
 protected AppRoleService roleService;
 @Autowired
 protected AppPermissionService permissionService;
 @Autowired
 protected AppUserRoleService userRoleService;
 @Autowired
 protected AppRolePermissionService rolePermissionService;
 @Autowired
 protected AuthorizationInfoCacheCleanSupport authCacheCleanSupport;

 public void clearAllCachedAuthorizationInfo() {
  this.authCacheCleanSupport.clearAllCachedAuthorizationInfo();
 }

 /**
  * 为指定的角色分配用户.
  *
  * @param userAndRoles
  * @return
  */
 public ItemResultSupport<UsersAndRoles> saveUsersForRoles(UsersAndRoles userAndRoles) {
  ItemResultSupport<UsersAndRoles> result = new ItemResultSupport<UsersAndRoles>();
  try {
   // 角色.
   List<AppRole> roleList = new ArrayList<AppRole>();
   String[] roles = StringUtils.split(userAndRoles.getRoles(), ",");
   if ((roles == null) || (roles.length > 0)) {
    for (String role : roles) {
     AppRole videoRole = this.roleService.getByCode(role);
     if (videoRole == null) {
      throw new RuntimeException("角色" + role + "不存在.");
     }
     roleList.add(videoRole);
    }
   }
   // 用户.
   String[] users = StringUtils.split(userAndRoles.getUsers(), ",");
   List<AppUser> userList = new ArrayList<AppUser>();
   if ((users != null) && (users.length > 0)) {
    for (String user : users) {
     AppUser appUser = this.userService.getByCode(user);
     if (appUser == null) {
      throw new RuntimeException("用户" + user + "不存在.");
     }
     userList.add(appUser);
    }
   }
   // 用户分配角色.
   for (AppUser user : userList) {
    // 清除用户下面的角色.
    this.userRoleService.deleteAll(new QueryFilterBuilder().put("userId", user.getId()).build().getParams());
    // 角色分配用户.
    for (AppRole role : roleList) {
     AppUserRole userRole = new AppUserRole();
     userRole.setUserId(user.getId());
     userRole.setRoleId(role.getId());
     this.userRoleService.save(userRole);
    }
   }
  } catch (Throwable e) {
   result.onException(e);
  }
  this.clearAllCachedAuthorizationInfo();
  return result;
 }

 /**
  * 获取角色下面的用户.
  *
  * @param roleIdOrCode
  * @return
  */
 public ItemResultSupport<String> getRoleUsers(String roleIdOrCode) {
  ItemResultSupport<String> result = new ItemResultSupport<String>();
  try {
   AppRole appRole = this.roleService.getByCode(roleIdOrCode);
   if (appRole == null) {
    throw new RuntimeException("角色" + roleIdOrCode + "不存在.");
   }
   List<String> users = new ArrayList<String>();
   List<AppUserRole> userRoles = this.userRoleService.query(new QueryFilterBuilder().put("roleId", appRole.getId()).build());
   for (AppUserRole userRole : userRoles) {
    users.add(userRole.getUserId().toString());
   }
   if (users.size() > 0) {
    String[] userArray = new String[users.size()];
    users.toArray(userArray);
    result.setItem(StringUtils.join(userArray, ","));
   } else {
    result.setItem("");
   }
  } catch (Throwable e) {
   result.onException(e);
  }
  return result;
 }

 /**
  * 获取用户分配的角色.
  *
  * @param userIdOrCode
  * @return
  */
 public ItemResultSupport<String> getUserRoleIds(String userIdOrCode) {
  ItemResultSupport<String> result = new ItemResultSupport<String>();
  try {
   AppUser appUser = this.userService.getByCode(userIdOrCode);
   if (appUser == null) {
    throw new RuntimeException("用户" + userIdOrCode + "不存在.");
   }
   List<String> roles = new ArrayList<String>();
   List<AppUserRole> userRoles = this.userRoleService.query(new QueryFilterBuilder().put("userId", appUser.getId()).build());
   for (AppUserRole userRole : userRoles) {
    roles.add(userRole.getRoleId().toString());
   }
   if (roles.size() > 0) {
    String[] idArray = new String[roles.size()];
    roles.toArray(idArray);
    result.setItem(StringUtils.join(idArray, ","));
   } else {
    result.setItem("");
   }
  } catch (Throwable e) {
   result.onException(e);
  }
  return result;
 }

 /**
  * 获取用户分配的角色.
  *
  * @param userId
  * @return
  */
 public List<AppRole> getUserRoles(String userId) {
  return this.roleService.query(new QueryFilterBuilder().put("userId", userId).put("state", 1).build());
 }

 /**
  * 获取用户分配的权限.
  *
  * @param userId
  * @return
  */
 public List<AppPermission> getUserPermissions(String userId) {
  return this.permissionService.query(new QueryFilterBuilder().put("userId", userId).put("state", 1).build());
 }

 /**
  * 获取角色分配的权限.
  *
  * @param roleIdOrCode
  * @return
  */
 public ItemResultSupport<String> getRolePermissions(String roleIdOrCode) {
  ItemResultSupport<String> result = new ItemResultSupport<String>();
  try {
   AppRole appRole = this.roleService.getByCode(roleIdOrCode);
   if (appRole == null) {
    throw new RuntimeException("角色" + roleIdOrCode + "不存在.");
   }
   List<String> permissions = new ArrayList<String>();
   List<AppRolePermission> rolePermissions = this.rolePermissionService
    .query(new QueryFilterBuilder().put("roleId", appRole.getId()).build());
   for (AppRolePermission roleRole : rolePermissions) {
    permissions.add(roleRole.getPermissionId().toString());
   }
   if (permissions.size() > 0) {
    String[] idArray = new String[permissions.size()];
    permissions.toArray(idArray);
    result.setItem(StringUtils.join(idArray, ","));
   } else {
    result.setItem("");
   }
  } catch (Throwable e) {
   result.onException(e);
  }
  return result;
 }

 /**
  * 为指定的角色分配权限.
  *
  * @param rolesAndPermissions
  * @return
  */
 public ItemResultSupport<RolesAndPermissions> saveRolePermissions(RolesAndPermissions rolesAndPermissions) {
  ItemResultSupport<RolesAndPermissions> result = new ItemResultSupport<RolesAndPermissions>();
  try {
   // 角色.
   String[] roles = StringUtils.split(rolesAndPermissions.getRoles(), ",");
   if ((roles == null) || (roles.length == 0)) {
    throw new RuntimeException("请指定角色.");
   }
   List<AppRole> roleList = new ArrayList<AppRole>();
   for (String role : roles) {
    AppRole appRole = this.roleService.getByCode(role);
    if (appRole == null) {
     throw new RuntimeException("角色" + role + "不存在.");
    }
    roleList.add(appRole);
   }
   // 权限.
   String[] permissions = StringUtils.split(rolesAndPermissions.getPermissions(), ",");
   List<AppPermission> permissionList = new ArrayList<AppPermission>();
   if ((permissions != null) && (permissions.length > 0)) {
    for (String permission : permissions) {
     AppPermission appPermission = this.permissionService.get(permission).getItem();
     if (appPermission == null) {
      throw new RuntimeException("权限" + permission + "不存在.");
     }
     permissionList.add(appPermission);
    }
   }
   // 角色分配权限.
   for (AppRole role : roleList) {
    // 清除角色下面的权限.
    this.rolePermissionService.deleteAll(new QueryFilterBuilder().put("roleId", role.getId()).build().getParams());
    for (AppPermission permission : permissionList) {
     // 角色分配权限.
     AppRolePermission rolePermission = new AppRolePermission();
     rolePermission.setPermissionId(permission.getId());
     rolePermission.setRoleId(role.getId());
     this.rolePermissionService.save(rolePermission);
    }
   }
  } catch (Throwable e) {
   result.onException(e);
  }
  this.clearAllCachedAuthorizationInfo();
  return result;
 }

 public AppRole saveOrGetRole(String code, String name) {
  AppRole role = new AppRole();
  role.setId(code);
  role.setCode(code);
  role.setName(name);
  role.setComment(name);
  role.setState(1);
  return roleService.saveOrGet(role);
 }
}
