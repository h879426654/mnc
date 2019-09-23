package com.basics.app.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.basics.app.entity.AppPermission;
import com.basics.app.service.AppPermissionService;
import com.basics.support.GenericMybatisService;
import com.basics.support.QueryFilter;
import com.basics.support.QueryFilterBuilder;

@Service
public class AppPermissionMybatisService extends GenericMybatisService<AppPermission> implements AppPermissionService {

 public AppPermission getByCode(String code) {
  if (StringUtils.isBlank(code)) {
   return null;
  }
  QueryFilter filter = new QueryFilterBuilder().put("code", code).put("state", new Integer(1)).build();
  AppPermission item = this.queryOne(filter);
  if (item == null) {
   return this.findByPK(code);
  }
  return item;
 }

 public AppPermission saveOrGet(AppPermission item) {
  AppPermission that = this.getByCode(item.getCode());
  if (that == null) {
   this.save(item);
   return item;
  } else {
   return that;
  }
 }
}
