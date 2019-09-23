package com.basics.support.app;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.basics.app.service.impl.AppUserRolePermissionSupport;
import com.basics.app.shiro.UpdatePasswordRequest;
import com.basics.app.shiro.UpdatePasswordResponse;
import com.basics.app.shiro.UpdatePasswordService;
import com.basics.app.shiro.UserSupport;
import com.basics.app.support.AppUserTypeEnum;
import com.basics.support.CommonSupport;

/**
 * 
 * @author yuwenfeng
 *
 */
@Service
public class MultipleUserRolePermissioinSupport extends AppUserRolePermissionSupport implements UpdatePasswordService, MultipleUserSupportService {

	@Override
	public boolean isMultipleUser() {
		return true;
	}

	@Override
	public UserSupport findByMultipleUsername(String username, String password, String userType) {
		if (CommonSupport.contains(userType, ",")) {
			String[] types = CommonSupport.split(userType, ",");
			for (String type : types) {
				AppUserTypeEnum userTypeEnum = AppUserTypeEnum.from(type);
				UserSupport user = this.findByMultipleUsername(username, password, userTypeEnum);
				if (user != null) {
					return user;
				}
			}
		} else {
			AppUserTypeEnum userTypeEnum = AppUserTypeEnum.from(userType);
			return this.findByMultipleUsername(username, password, userTypeEnum);
		}
		return null;
	}

	public UserSupport findByMultipleUsername(String username, String password, AppUserTypeEnum userTypeEnum) {
		CommonSupport.checkState(StringUtils.isNotBlank(username), "账号不允许为空!");
		CommonSupport.checkState(StringUtils.isNotBlank(password), "密码不允许为空!");
		CommonSupport.checkNotNull(userTypeEnum, "用户类型不允许为空!");
		if (AppUserTypeEnum.ADMIN.equals(userTypeEnum) || AppUserTypeEnum.HQ.equals(userTypeEnum)) {
			return this.userService.findByUsername(username, password);
		}
		CommonSupport.checkState(false, "不支持用户类型%s登录", userTypeEnum.getMessage());
		return null;
	}

	public UserSupport getByUserIdAndType(String userId, String userType) {
		if (CommonSupport.contains(userType, ",")) {
			String[] types = CommonSupport.split(userType, ",");
			for (String type : types) {
				AppUserTypeEnum userTypeEnum = AppUserTypeEnum.from(type);
				UserSupport user = this.getByUserIdAndType(userId, userTypeEnum);
				if (user != null) {
					return user;
				}
			}
		} else {
			AppUserTypeEnum userTypeEnum = AppUserTypeEnum.from(userType);
			return this.getByUserIdAndType(userId, userTypeEnum);
		}
		return null;
	}

	public UserSupport getByUserIdAndType(String userId, AppUserTypeEnum userTypeEnum) {
		CommonSupport.checkState(StringUtils.isNotBlank(userId), "用户不允许为空!");
		CommonSupport.checkNotNull(userTypeEnum, "用户类型不允许为空!");
		if (AppUserTypeEnum.ADMIN.equals(userTypeEnum) || AppUserTypeEnum.HQ.equals(userTypeEnum)) {
			return this.userService.get(userId).getItem();
		}
		CommonSupport.checkState(false, "不支持用户类型%s", userTypeEnum.getMessage());
		return null;
	}

	public boolean accept(Object loginUser) {
		return loginUser instanceof UserSupport;
	}

	public UpdatePasswordResponse updatePassword(Object loginUser, UpdatePasswordRequest request) {
		UpdatePasswordResponse response = new UpdatePasswordResponse();
		if (loginUser instanceof UserSupport) {
			return this.updatePassword((UserSupport) loginUser, request);
		} else {
			response.onException("不支持的操作");
		}
		return response;
	}

	public UpdatePasswordResponse updatePassword(UserSupport loginUser, UpdatePasswordRequest request) {
		AppUserTypeEnum userTypeEnum = AppUserTypeEnum.from(String.valueOf(loginUser.getType()));
		return this.updatePassword(loginUser, request, userTypeEnum);
	}

	public UpdatePasswordResponse updatePassword(UserSupport loginUser, UpdatePasswordRequest request, AppUserTypeEnum userTypeEnum) {
		UpdatePasswordResponse response = new UpdatePasswordResponse();
		try {
			CommonSupport.checkNotNull(userTypeEnum, "用户类型不允许为空!");
			if (AppUserTypeEnum.ADMIN.equals(userTypeEnum) || AppUserTypeEnum.HQ.equals(userTypeEnum)) {
				return this.userService.updatePassword(loginUser, request);
			}
			response.onException("不支持的操作");
		} catch (Throwable e) {
			response.onException(e);
		}
		return response;
	}
}
