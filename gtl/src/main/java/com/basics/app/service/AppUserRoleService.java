package com.basics.app.service;

import com.basics.app.entity.AppUserRole;
import com.basics.support.GenericService;

public interface AppUserRoleService extends GenericService<AppUserRole> {

 public void saveUserAndRole(String userId, String roleId);

 public void deleteUserRole(String userId);
}
