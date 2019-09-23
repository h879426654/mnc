package com.basics.app.dao.impl;

import org.springframework.stereotype.Repository;

import com.basics.app.dao.AppTokenDao;
import com.basics.app.entity.AppToken;
import com.basics.app.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class AppTokenMybatisDao extends GenericMybatisDaoSupport<AppToken> implements AppTokenDao {

public AppTokenMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","TOKEN_ID");
 defaultNameMapper.configFieldColumnName("tokenCreateTime","TOKEN_CREATE_TIME");
 defaultNameMapper.configFieldColumnName("tokenExpirationTime","TOKEN_EXPIRATION_TIME");
 defaultNameMapper.configFieldColumnName("userId","USER_ID");
return defaultNameMapper;
}
}
