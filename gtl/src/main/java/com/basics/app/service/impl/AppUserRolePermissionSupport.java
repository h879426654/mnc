package com.basics.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.basics.app.entity.AppPermission;
import com.basics.app.entity.AppRole;
import com.basics.app.entity.AppRolePermission;
import com.basics.app.entity.AppUser;
import com.basics.app.entity.AppUserRole;
import com.basics.app.service.AppPermissionService;
import com.basics.app.service.AppRolePermissionService;
import com.basics.app.service.AppRoleService;
import com.basics.app.service.AppUserRoleService;
import com.basics.app.service.AppUserService;
import com.basics.app.shiro.AuthorizationInfoCacheCleanSupport;
import com.basics.app.shiro.PermissionSupport;
import com.basics.app.shiro.RoleSupport;
import com.basics.app.shiro.RolesAndPermissions;
import com.basics.app.shiro.UpdatePasswordRequest;
import com.basics.app.shiro.UpdatePasswordResponse;
import com.basics.app.shiro.UserRolePermissionSupport;
import com.basics.app.shiro.UserSupport;
import com.basics.app.shiro.UsersAndRoles;
import com.basics.support.ItemResultSupport;
import com.basics.support.QueryFilter;
import com.basics.support.QueryFilterBuilder;

/**
 * AppUserRolePermissionService.
 */
// @Service
public class AppUserRolePermissionSupport implements UserRolePermissionSupport {

	/** The user service. */
	@Autowired
	protected AppUserService userService;

	/** The role service. */
	@Autowired
	protected AppRoleService roleService;

	/** The permission service. */
	@Autowired
	protected AppPermissionService permissionService;

	/** The user role service. */
	@Autowired
	protected AppUserRoleService userRoleService;

	/** The role permission service. */
	@Autowired
	protected AppRolePermissionService rolePermissionService;

	/** The auth cache clean support. */
	@Autowired
	protected AuthorizationInfoCacheCleanSupport authCacheCleanSupport;

	/**
	 * 是否多用户类型体系:用户按类型,保存在不同的表里面.
	 */
	protected boolean multipleUser;

	public boolean isMultipleUser() {
		return multipleUser;
	}

	public void setMultipleUser(boolean multipleUser) {
		this.multipleUser = multipleUser;
	}

	/**
	 * Clear all cached authorization info.
	 */
	public void clearAllCachedAuthorizationInfo() {
		this.authCacheCleanSupport.clearAllCachedAuthorizationInfo();
	}

	/**
	 * 为指定的角色分配用户.
	 *
	 * @param userAndRoles
	 *         the user and roles
	 * @return the item result support< users and roles>
	 */
	public ItemResultSupport<UsersAndRoles> saveUsersForRoles(UsersAndRoles userAndRoles) {
		ItemResultSupport<UsersAndRoles> result = new ItemResultSupport<UsersAndRoles>();
		try {
			// 角色.
			List<AppRole> roleList = new ArrayList<AppRole>();
			String[] roles = StringUtils.split(userAndRoles.getRoles(), ",");
			if (roles == null || roles.length > 0) {
				for (String role : roles) {
					AppRole appRole = this.roleService.getByCode(role);
					if (appRole == null) {
						throw new RuntimeException("角色" + role + "不存在.");
					}
					roleList.add(appRole);
				}
			}
			// 用户.
			String[] users = StringUtils.split(userAndRoles.getUsers(), ",");
			List<AppUser> userList = new ArrayList<AppUser>();
			if (users != null && users.length > 0) {
				for (String user : users) {
					AppUser appUser = this.userService.getByCode(user);
					if (appUser == null) {
						throw new RuntimeException("用户" + user + "不存在.");
					}
					userList.add(appUser);
				}
			}
			// 用户分配角色.
			for (UserSupport user : userList) {
				// 清除用户下面的角色.
				userRoleService.deleteAll(new QueryFilterBuilder().put("userId", user.getId()).build().getParams());
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
	 * 为指定的用户分配角色.
	 *
	 * @param userAndRoles
	 *         the user and roles
	 * @return the item result support< users and roles>
	 */
	public ItemResultSupport<UsersAndRoles> saveUserRoles(UsersAndRoles userAndRoles) {
		ItemResultSupport<UsersAndRoles> result = new ItemResultSupport<UsersAndRoles>();
		try {
			// 角色.
			List<AppRole> roleList = new ArrayList<AppRole>();
			String[] roles = StringUtils.split(userAndRoles.getRoles(), ",");
			if (roles == null || roles.length > 0) {
				for (String role : roles) {
					AppRole appRole = this.roleService.getByCode(role);
					if (appRole == null) {
						throw new RuntimeException("角色" + role + "不存在.");
					}
					roleList.add(appRole);
				}
			}
			// 用户.
			String[] users = StringUtils.split(userAndRoles.getUsers(), ",");
			// 用户分配角色.
			for (String user : users) {
				// 清除用户下面的角色.
				userRoleService.deleteAll(new QueryFilterBuilder().put("userId", user).build().getParams());
				// 角色分配用户.
				for (AppRole role : roleList) {
					AppUserRole userRole = new AppUserRole();
					userRole.setUserId(user);
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
	 *         the role id or code
	 * @return the role users
	 */
	public ItemResultSupport<String> getRoleUsers(String roleIdOrCode) {
		ItemResultSupport<String> result = new ItemResultSupport<String>();
		try {
			AppRole appRole = this.roleService.getByCode(roleIdOrCode);
			if (appRole == null) {
				throw new RuntimeException("角色" + roleIdOrCode + "不存在.");
			}
			List<String> users = new ArrayList<String>();
			List<AppUserRole> userRoles = userRoleService.query(new QueryFilterBuilder().put("roleId", appRole.getId()).build());
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
	 *         the user id or code
	 * @return the user roles
	 */
	public ItemResultSupport<String> getUserRoles(String userIdOrCode) {
		ItemResultSupport<String> result = new ItemResultSupport<String>();
		try {
			UserSupport appUser = this.userService.getByCode(userIdOrCode);
			if (appUser == null) {
				throw new RuntimeException("用户" + userIdOrCode + "不存在.");
			}
			List<String> roles = new ArrayList<String>();
			List<AppUserRole> userRoles = userRoleService.query(new QueryFilterBuilder().put("userId", appUser.getId()).build());
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
	 *         the user id
	 * @return the user roles by user id
	 */
	public List<RoleSupport> getUserRolesByUserId(String userId) {
		List<AppRole> items = roleService.query(new QueryFilterBuilder().put("userId", userId).put("state", 1).build());
		List<RoleSupport> results = new ArrayList<RoleSupport>();
		results.addAll(items);
		return results;
	}

	/**
	 * 获取用户分配的权限.
	 *
	 * @param userId
	 *         the user id
	 * @return the user permissions
	 */
	public List<PermissionSupport> getUserPermissions(String userId) {
		List<AppPermission> items = permissionService.query(new QueryFilterBuilder().put("userId", userId).put("state", 1).build());
		List<PermissionSupport> results = new ArrayList<PermissionSupport>();
		results.addAll(items);
		return results;
	}

	/**
	 * 获取角色分配的权限.
	 *
	 * @param roleIdOrCode
	 *         the role id or code
	 * @return the role permissions
	 */
	public ItemResultSupport<String> getRolePermissions(String roleIdOrCode) {
		ItemResultSupport<String> result = new ItemResultSupport<String>();
		try {
			AppRole appRole = this.roleService.getByCode(roleIdOrCode);
			if (appRole == null) {
				throw new RuntimeException("角色" + roleIdOrCode + "不存在.");
			}
			List<String> permissions = new ArrayList<String>();
			List<AppRolePermission> rolePermissions = rolePermissionService.query(new QueryFilterBuilder().put("roleId", appRole.getId()).build());
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
	 *         the roles and permissions
	 * @return the item result support< roles and permissions>
	 */
	public ItemResultSupport<RolesAndPermissions> saveRolePermissions(RolesAndPermissions rolesAndPermissions) {
		ItemResultSupport<RolesAndPermissions> result = new ItemResultSupport<RolesAndPermissions>();
		try {
			// 角色.
			String[] roles = StringUtils.split(rolesAndPermissions.getRoles(), ",");
			if (roles == null || roles.length == 0) {
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
			if (permissions != null && permissions.length > 0) {
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
				rolePermissionService.deleteAll(new QueryFilterBuilder().put("roleId", role.getId()).build().getParams());
				for (PermissionSupport permission : permissionList) {
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

	/**
	 * Gets the systems.
	 *
	 * @param userId
	 *         the user id
	 * @return the systems
	 */
	public List<PermissionSupport> getUserSystems(String userId) {
		List<AppPermission> items = this.permissionService
				.query(new QueryFilterBuilder().put("parentId", PermissionSupport.ROOT_ID).put("type", PermissionSupport.TYPE_SYSTEM).put("state", 1).put("userId", userId).build());
		List<PermissionSupport> results = new ArrayList<PermissionSupport>();
		results.addAll(items);
		return results;
	}

	/**
	 * Gets the user permissions.
	 *
	 * @param userId
	 *         the user id
	 * @param parentId
	 *         the parent id
	 * @param type
	 *         the type
	 * @return the user permissions
	 */
	public List<PermissionSupport> getUserPermissions(String userId, String parentId, int type) {
		if (StringUtils.isBlank(parentId)) {
			parentId = PermissionSupport.ROOT_ID + "";
		}
		List<AppPermission> items = this.permissionService
				.query(new QueryFilterBuilder().put("parentId", parentId).put("type", type).put("state", 1).put("userId", userId).orderBy("PERMISSION_ORDER ASC").build());
		List<PermissionSupport> results = new ArrayList<PermissionSupport>();
		results.addAll(items);
		return results;
	}

	/**
	 * Gets the permissions.
	 *
	 * @param filter
	 *         the filter
	 * @return the permissions
	 */
	public List<PermissionSupport> getPermissions(QueryFilter filter) {
		List<AppPermission> items = this.permissionService.query(filter);
		List<PermissionSupport> results = new ArrayList<PermissionSupport>();
		results.addAll(items);
		return results;
	}

	/**
	 * Find by username.
	 *
	 * @param username
	 *         the username
	 * @return the user support
	 */
	public UserSupport findByUsername(String username, String password) {
		return this.userService.findByUsername(username, password);
	}

	/** The password cryptor. */
	@Autowired
	PasswordEncoder passwordCryptor;

	/**
	 * Checks if is password match.
	 *
	 * @param plainPassword
	 *         the plain password
	 * @param encodedPassword
	 *         the encoded password
	 * @return true, if checks if is password match
	 */
	public boolean isPasswordMatch(String plainPassword, String encodedPassword) {
		return this.passwordCryptor.matches(plainPassword, encodedPassword);
	}

	/**
	 * Encrypt password.
	 *
	 * @param plainPassword
	 *         the plain password
	 * @return the string
	 */
	public String encryptPassword(String plainPassword) {
		return this.passwordCryptor.encode(plainPassword);
	}

	public AppUser getAppUserByUsername(String username) {
		if (StringUtils.isBlank(username)) {
			return null;
		}
		QueryFilter userFilter = new QueryFilterBuilder().put("code", username).put("state", new Integer(1)).build();
		AppUser user = this.userService.queryOne(userFilter);
		return user;
	}

	public UpdatePasswordResponse updatePassword(UserSupport loginUser, UpdatePasswordRequest request) {
		UpdatePasswordResponse response = new UpdatePasswordResponse();
		try {
			String username = loginUser.getCode();
			AppUser user = this.getAppUserByUsername(username);
			if (user == null) {
				throw new RuntimeException("用户" + username + "不存在!");
			}
			StringBuilder sb = new StringBuilder();
			if (StringUtils.isBlank(request.getPasswordOld())) {
				sb.append("\r\n原密码不允许为空!");
			} else {
				if (!StringUtils.equals(request.getPasswordOld(), user.getPassword())) {
					throw new RuntimeException("原密码不正确!");
				}
			}
			if (StringUtils.isBlank(request.getPasswordNew())) {
				sb.append("\r\n新密码不允许为空!");
			}
			if (StringUtils.isBlank(request.getPasswordConfirmed())) {
				sb.append("\r\n确认新密码不允许为空!");
			}
			if (!StringUtils.equals(request.getPasswordNew(), request.getPasswordConfirmed())) {
				sb.append("\r\n确认新密码不一致!");
			} else {
				if (StringUtils.length(request.getPasswordNew()) > 32) {
					throw new RuntimeException("密码长度不允许超过32!");
				}
				if (StringUtils.equals(request.getPasswordOld(), request.getPasswordNew())) {
					throw new RuntimeException("新密码不允许和原始密码相同!");
				}
			}
			if (sb.length() > 0) {
				response.onException(sb.toString());
			} else {
				user.setPasswordNew(request.getPasswordNew());
				user.setPasswordConfirmed(request.getPasswordConfirmed());
				this.userService.save(user);
			}
		} catch (Throwable e) {
			response.onException(e.getMessage());
		}
		return response;
	}

	/**
	 * Find by username.
	 *
	 * @param username
	 *         the username
	 * @param userType
	 *         the user type
	 * @return the user support
	 */
	public UserSupport findByMultipleUsername(String username, String password, String userType) {
		return this.userService.findByUsername(username, password);
	}
}
