package com.basics.sys.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.sys.dao.SysVersionDao;
import com.basics.sys.entity.SysVersion;
import com.basics.sys.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class SysVersionMybatisDao extends GenericMybatisDaoSupport<SysVersion> implements SysVersionDao {

public SysVersionMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","VERSION_ID");
 defaultNameMapper.configFieldColumnName("versionName","VERSION_NAME");
 defaultNameMapper.configFieldColumnName("versionType","VERSION_TYPE");
 defaultNameMapper.configFieldColumnName("versionContext","VERSION_CONTEXT");
 defaultNameMapper.configFieldColumnName("versionCode","VERSION_CODE");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("versionUrl","VERSION_URL");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
return defaultNameMapper;
}
}
