package com.basics.app.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.basics.app.entity.AppRole;
import com.basics.app.service.AppRoleService;
import com.basics.support.GenericMybatisService;
import com.basics.support.QueryFilter;
import com.basics.support.QueryFilterBuilder;

@Service
public class AppRoleMybatisService extends GenericMybatisService<AppRole> implements AppRoleService {

 public AppRole getByCode(String code) {
  if (StringUtils.isBlank(code)) {
   return null;
  }
  QueryFilter filter = new QueryFilterBuilder().put("code", code).put("state", new Integer(1)).build();
  AppRole item = this.queryOne(filter);
  if (item == null) {
   return this.findByPK(code);
  }
  return item;
 }

 public AppRole saveOrGet(AppRole item) {
  AppRole that = this.getByCode(item.getCode());
  if (that == null) {
   this.save(item);
   return item;
  } else {
   return that;
  }
 }

 public String getUserRoleNames(String userId) {
  if (StringUtils.isBlank(userId)) {
   return "";
  }
  QueryFilter filter = new QueryFilterBuilder().put("userId", userId).build();
  List<AppRole> items = this.query(filter);
  if (items.isEmpty()) {
   return "";
  }
  StringBuilder sb = new StringBuilder();
  for (int i = 0; i < items.size(); i++) {
   AppRole item = items.get(i);
   if (i > 0) {
    sb.append(" ");
   }
   sb.append(item.getName());
  }
  return sb.toString();
 }
}
