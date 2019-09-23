package com.basics.app.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.basics.app.dao.AppUserRoleDao;
import com.basics.app.entity.AppUser;
import com.basics.app.service.AppUserService;
import com.basics.app.shiro.UpdatePasswordRequest;
import com.basics.app.shiro.UpdatePasswordResponse;
import com.basics.app.shiro.UserSupport;
import com.basics.app.support.AppUserTypeEnum;
import com.basics.support.GenericMybatisService;
import com.basics.support.QueryFilter;
import com.basics.support.QueryFilterBuilder;

@Service
public class AppUserMybatisService extends GenericMybatisService<AppUser> implements AppUserService {
	@Autowired
	PasswordEncoder passwordCryptor;
	@Autowired
	AppUserRoleDao appUserRoleDao;

	public AppUser getByCode(String code) {
		if (StringUtils.isBlank(code)) {
			return null;
		}
		QueryFilter filter = new QueryFilterBuilder().put("code", code).put("state", new Integer(1)).build();
		AppUser item = this.queryOne(filter);
		if (item == null) {
			return this.findByPK(code);
		}
		return item;
	}

	public UserSupport findByUsername(String username, String password) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return null;
		}
		return this.queryOne(new QueryFilterBuilder().put("code", username).put("password", this.passwordCryptor.encode(password)).put("state", new Integer(1)).build());
	}

	public UpdatePasswordResponse updatePassword(UserSupport loginUser, UpdatePasswordRequest request) {
		UpdatePasswordResponse response = new UpdatePasswordResponse();
		try {
			String username = loginUser.getMobile();
			UserSupport user = this.queryOne(new QueryFilterBuilder().put("code", username).put("state", new Integer(1)).build());
			if (user == null) {
				throw new RuntimeException("用户" + username + "不存在!");
			}
			StringBuilder sb = new StringBuilder();
			if (StringUtils.isBlank(request.getPasswordOld())) {
				sb.append("\r\n原密码不允许为空!");
			} else {
				if (this.passwordCryptor.matches(request.getPasswordOld(), user.getPassword())) {
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
				AppUser thisUser = new AppUser();
				thisUser.setId(user.getId());
				thisUser.setPassword(this.passwordCryptor.encode(request.getPasswordNew()));
				this.save(thisUser);
			}
		} catch (Throwable e) {
			response.onException(e.getMessage());
		}
		return response;
	}

	@Override
	public void save(AppUser entity) {
		if (StringUtils.isNotBlank(entity.getPasswordNew())) {
			StringBuilder sb = new StringBuilder();
			if (!StringUtils.equals(entity.getPasswordNew(), entity.getPasswordConfirmed())) {
				sb.append("\r\n确认新密码不一致!");
			} else {
				if (StringUtils.length(entity.getPasswordNew()) > 32) {
					sb.append("密码长度不允许超过32!");
				}
			}
			if (sb.length() > 0) {
				throw new RuntimeException(sb.toString());
			}
			try {
				entity.setPassword(this.passwordCryptor.encode(entity.getPasswordNew()));
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		// 默认是总部用户.
		if (entity.getType() == null) {
			entity.setType(AppUserTypeEnum.HQ.getValue());
		}
		// CommonSupport.checkState(this.dao.uniqueByField(entity, "mobile"),
		// "手机号码已经存在");
		super.save(entity);
		// this.appUserAreaDao.save(entity.getId(), entity);
		// if (CommonSupport.isNotBlank(entity.getOrgId())) {
		// this.appUserOrgDao.saveUserOrg(entity.getId(), entity.getOrgId(), true);
		// }
		if (entity.getType() != null) {
			AppUserTypeEnum typeEnum = AppUserTypeEnum.from(entity.getType().toString());
			if (typeEnum != null) {
				// this.appUserRoleDao.saveUserRoles(entity.getId(),
				// typeEnum.getDefaultRole().getValue());
			}
		}
	}

	public boolean isPasswordMatch(String plainPassword, String encodedPassword) {
		return this.passwordCryptor.matches(plainPassword, encodedPassword);
	}
}
