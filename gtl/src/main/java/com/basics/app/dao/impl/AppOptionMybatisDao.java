package com.basics.app.dao.impl;

import org.springframework.stereotype.Repository;

import com.basics.app.dao.AppOptionDao;
import com.basics.app.entity.AppOption;
import com.basics.app.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class AppOptionMybatisDao extends GenericMybatisDaoSupport<AppOption> implements AppOptionDao {

 public AppOptionMybatisDao() {
  super();
  this.setPrimaryKeyFields("id");
 }

 public INameMapper onBuildNameMapper() {
  DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
  defaultNameMapper.configFieldColumnName("id", "OPTION_ID");
  defaultNameMapper.configFieldColumnName("parentId", "OPTION_PARENT_ID");
  defaultNameMapper.configFieldColumnName("code", "OPTION_CODE");
  defaultNameMapper.configFieldColumnName("name", "OPTION_NAME");
  defaultNameMapper.configFieldColumnName("comment", "OPTION_COMMENT");
  defaultNameMapper.configFieldColumnName("order", "OPTION_ORDER");
  defaultNameMapper.configFieldColumnName("icon", "OPTION_ICON");
  defaultNameMapper.configFieldColumnName("url", "OPTION_URL");
  defaultNameMapper.configFieldColumnName("type", "OPTION_TYPE");
  defaultNameMapper.configFieldColumnName("flag", "OPTION_FLAG");
  defaultNameMapper.configFieldColumnName("endTime", "OPTION_END_TIME");
  return defaultNameMapper;
 }
}
