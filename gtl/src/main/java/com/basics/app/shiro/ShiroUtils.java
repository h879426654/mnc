package com.basics.app.shiro;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;

public class ShiroUtils {

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * Ensures that the calling <code>Subject</code> has the Annotation's specified
	 * permissions, and if not, throws an <code>AuthorizingException</code>
	 * indicating access is denied.
	 *
	 * @param requiresPermissions
	 *         the RequiresPermission annotation being inspected to check for one
	 *         or more permissions
	 * @throws org.apache.shiro.authz.AuthorizationException
	 *          if the calling <code>Subject</code> does not have the permission(s)
	 *          necessary to continue access or execution.
	 */
	public static void assertAuthorized(RequiresPermissions requiresPermissions) throws AuthorizationException {
		String[] perms = requiresPermissions.value();
		Subject subject = getSubject();
		if (perms.length == 1) {
			subject.checkPermission(perms[0]);
			return;
		}
		if (Logical.AND.equals(requiresPermissions.logical())) {
			getSubject().checkPermissions(perms);
			return;
		}
		if (Logical.OR.equals(requiresPermissions.logical())) {
			// Avoid processing exceptions unnecessarily - "delay" throwing the
			// exception by calling hasRole first
			boolean hasAtLeastOnePermission = false;
			for (String permission : perms)
				if (getSubject().isPermitted(permission))
					hasAtLeastOnePermission = true;
			// Cause the exception if none of the role match, note that the exception
			// message will be a bit misleading
			if (!hasAtLeastOnePermission)
				getSubject().checkPermission(perms[0]);
		}
	}

	/**
	 * Ensures that the calling <code>Subject</code> has the Annotation's specified
	 * roles, and if not, throws an <code>AuthorizingException</code> indicating
	 * that access is denied.
	 *
	 * @param requiresRoles
	 *         the RequiresRoles annotation to use to check for one or more roles
	 * @throws org.apache.shiro.authz.AuthorizationException
	 *          if the calling <code>Subject</code> does not have the role(s)
	 *          necessary to proceed.
	 */
	public static void assertAuthorized(RequiresRoles requiresRoles) throws AuthorizationException {
		String[] roles = requiresRoles.value();
		if (roles.length == 1) {
			getSubject().checkRole(roles[0]);
			return;
		}
		if (Logical.AND.equals(requiresRoles.logical())) {
			getSubject().checkRoles(Arrays.asList(roles));
			return;
		}
		if (Logical.OR.equals(requiresRoles.logical())) {
			// Avoid processing exceptions unnecessarily - "delay" throwing the
			// exception by calling hasRole first
			boolean hasAtLeastOneRole = false;
			for (String role : roles)
				if (getSubject().hasRole(role))
					hasAtLeastOneRole = true;
			// Cause the exception if none of the role match, note that the exception
			// message will be a bit misleading
			if (!hasAtLeastOneRole)
				getSubject().checkRole(roles[0]);
		}
	}

	public static String getIp() {
		return getSubject().getSession().getHost();
	}

}
