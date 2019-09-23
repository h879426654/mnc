package com.basics.app.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.basics.support.LogUtils;

public class DefaultUserAuthorizingRealm extends AuthorizingRealm implements AuthorizationInfoCacheCleanSupport {

	/**
	 * 认证之前回调,增加业务逻辑.
	 *
	 * @param user
	 * @throws AuthenticationException
	 */
	public void willAuthentication(UserSupport user) throws AuthenticationException {

	}

	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		this.clearAllCachedAuthorizationInfo();
		String username = token.getUsername();
		String password = new String(token.getPassword());
		String userType = token.getUserType();
		LogUtils.performance.info("doGetAuthenticationInfo:{} username={} password={} captcha:{} userType:{}", new Object[] { this.getClass().getSimpleName(), username, password, userType });
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			LogUtils.performance.error("username or password is blank.");
			return null;
		}
		if (this.getUrpService().isMultipleUser() && userType == null) {
			LogUtils.performance.error("isMultipleUser userType not allow null");
			return null;
		}
		UserSupport user = null;
		if (this.getUrpService().isMultipleUser()) {
			user = this.getUrpService().findByMultipleUsername(username, password, userType);
		} else {
			user = this.urpService.findByUsername(token.getUsername(), password);
		}
		this.willAuthentication(user);
		if (user == null) {
			LogUtils.performance.error("user with username={} not found.", token.getUsername());
		} else {
			LogUtils.performance.info("isPasswordMatch: plain={} encodedPassword={} match={}", password, user.getPassword(), this.urpService.isPasswordMatch(password, user.getPassword()));
		}
		if (user != null && this.urpService.isPasswordMatch(password, user.getPassword())) {
			byte[] salt = password.getBytes();
			return new SimpleAuthenticationInfo(user, password, ByteSource.Util.bytes(salt), getName());
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		UserSupport user = (UserSupport) getAvailablePrincipal(principals);
		if (user != null) {
			return buildAuthorizationInfo(user);
		} else {
			return null;
		}
	}

	protected UserRolePermissionSupport urpService;

	public UserRolePermissionSupport getUrpService() {
		return urpService;
	}

	public void setUrpService(UserRolePermissionSupport urpService) {
		this.urpService = urpService;
	}

	public AuthorizationInfo buildAuthorizationInfo(UserSupport user) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 角色.
		List<RoleSupport> roles = urpService.getUserRolesByUserId(user.getId());
		List<String> roleCodes = new ArrayList<String>();
		for (RoleSupport role : roles) {
			if (StringUtils.isNotBlank(role.getCode()) && !roleCodes.contains(role.getCode())) {
				roleCodes.add(role.getCode());
			}
		}
		if (!roleCodes.isEmpty()) {
			info.addRoles(roleCodes);
		}
		// 权限.
		List<PermissionSupport> permissions = urpService.getUserPermissions(user.getId());
		List<String> permissionCodes = new ArrayList<String>();
		for (PermissionSupport permission : permissions) {
			if (StringUtils.isNotBlank(permission.getCode()) && !permissionCodes.contains(permission.getCode())) {
				permissionCodes.add(permission.getCode());
			}
		}
		if (!permissionCodes.isEmpty()) {
			info.addStringPermissions(permissionCodes);
		}
		return info;

	}

	/**
	 * 清空用户关联权限认证，待下次使用时重新加载
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清空所有关联认证
	 */
	public void clearAllCachedAuthorizationInfo() {
		LogUtils.performance.info("clearAllCachedAuthorizationInfo:清空所有关联认证");
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

}
