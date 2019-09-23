package com.basics.app.dao.impl;

import org.springframework.stereotype.Repository;

import com.basics.app.dao.AppUserDao;
import com.basics.app.entity.AppUser;
import com.basics.app.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class AppUserMybatisDao extends GenericMybatisDaoSupport<AppUser> implements AppUserDao {

 public AppUserMybatisDao() {
  super();
  this.setPrimaryKeyFields("id");
 }

 public INameMapper onBuildNameMapper() {
  DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
  defaultNameMapper.configFieldColumnName("id", "USER_ID");
  defaultNameMapper.configFieldColumnName("code", "USER_CODE");
  defaultNameMapper.configFieldColumnName("password", "USER_PASSWORD");
  defaultNameMapper.configFieldColumnName("comment", "USER_COMMENT");
  defaultNameMapper.configFieldColumnName("name", "USER_NAME");
  defaultNameMapper.configFieldColumnName("state", "USER_STATE");
  defaultNameMapper.configFieldColumnName("stateName", "stateDict.OPTION_NAME");
  defaultNameMapper.configFieldColumnName("type", "USER_TYPE");
  return defaultNameMapper;
 }
}
