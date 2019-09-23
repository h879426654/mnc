package com.basics.app.service;

import com.basics.app.entity.AppRole;
import com.basics.support.GenericService;

public interface AppRoleService extends GenericService<AppRole> {

 public AppRole getByCode(String code);

 public AppRole saveOrGet(AppRole role);

 public String getUserRoleNames(String userId);
}
