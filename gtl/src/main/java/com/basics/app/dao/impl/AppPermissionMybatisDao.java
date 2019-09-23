package com.basics.app.dao.impl;

import org.springframework.stereotype.Repository;

import com.basics.app.dao.AppPermissionDao;
import com.basics.app.entity.AppPermission;
import com.basics.app.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class AppPermissionMybatisDao extends GenericMybatisDaoSupport<AppPermission> implements AppPermissionDao {

 public AppPermissionMybatisDao() {
  super();
  this.setPrimaryKeyFields("id");
 }

 public INameMapper onBuildNameMapper() {
  DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
  defaultNameMapper.configFieldColumnName("id", "PERMISSION_ID");
  defaultNameMapper.configFieldColumnName("parentId", "PERMISSION_PARENT_ID");
  defaultNameMapper.configFieldColumnName("code", "PERMISSION_CODE");
  defaultNameMapper.configFieldColumnName("name", "PERMISSION_NAME");
  defaultNameMapper.configFieldColumnName("comment", "PERMISSION_COMMENT");
  defaultNameMapper.configFieldColumnName("order", "PERMISSION_ORDER");
  defaultNameMapper.configFieldColumnName("icon", "PERMISSION_ICON");
  defaultNameMapper.configFieldColumnName("url", "PERMISSION_URL");
  defaultNameMapper.configFieldColumnName("type", "PERMISSION_TYPE");
  defaultNameMapper.configFieldColumnName("state", "PERMISSION_STATE");
  defaultNameMapper.configFieldColumnName("stateName", "stateDict.OPTION_NAME");
  return defaultNameMapper;
 }
}
