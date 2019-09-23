package com.basics.app.service;

import com.basics.app.entity.AppUser;
import com.basics.app.shiro.UpdatePasswordRequest;
import com.basics.app.shiro.UpdatePasswordResponse;
import com.basics.app.shiro.UserSupport;
import com.basics.support.GenericService;

public interface AppUserService extends GenericService<AppUser> {

	public UserSupport findByUsername(String username, String password);

	public UpdatePasswordResponse updatePassword(UserSupport loginUser, UpdatePasswordRequest request);

	public AppUser getByCode(String code);

	public boolean isPasswordMatch(String plainPassword, String encodedPassword);

}
