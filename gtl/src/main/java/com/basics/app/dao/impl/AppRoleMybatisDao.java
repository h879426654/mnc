package com.basics.app.dao.impl;

import org.springframework.stereotype.Repository;

import com.basics.app.dao.AppRoleDao;
import com.basics.app.entity.AppRole;
import com.basics.app.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class AppRoleMybatisDao extends GenericMybatisDaoSupport<AppRole> implements AppRoleDao {

 public AppRoleMybatisDao() {
  super();
  this.setPrimaryKeyFields("id");
 }

 public INameMapper onBuildNameMapper() {
  DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
  defaultNameMapper.configFieldColumnName("id", "ROLE_ID");
  defaultNameMapper.configFieldColumnName("code", "ROLE_CODE");
  defaultNameMapper.configFieldColumnName("name", "ROLE_NAME");
  defaultNameMapper.configFieldColumnName("comment", "ROLE_COMMENT");
  defaultNameMapper.configFieldColumnName("state", "ROLE_STATE");
  defaultNameMapper.configFieldColumnName("stateName", "stateDict.OPTION_NAME");
  return defaultNameMapper;
 }
}
