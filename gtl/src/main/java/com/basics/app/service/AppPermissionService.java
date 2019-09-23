package com.basics.app.service;

import com.basics.app.entity.AppPermission;
import com.basics.support.GenericService;

public interface AppPermissionService extends GenericService<AppPermission> {

 public AppPermission getByCode(String code);

 public AppPermission saveOrGet(AppPermission item);
}
