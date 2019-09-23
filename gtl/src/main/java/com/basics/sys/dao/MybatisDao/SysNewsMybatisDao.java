package com.basics.sys.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.sys.dao.SysNewsDao;
import com.basics.sys.entity.SysNews;
import com.basics.sys.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class SysNewsMybatisDao extends GenericMybatisDaoSupport<SysNews> implements SysNewsDao {

public SysNewsMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","NEWS_ID");
 defaultNameMapper.configFieldColumnName("newsTitle","NEWS_TITLE");
 defaultNameMapper.configFieldColumnName("newsImg","NEWS_IMG");
 defaultNameMapper.configFieldColumnName("newsContext","NEWS_CONTEXT");
 defaultNameMapper.configFieldColumnName("newsSort","NEWS_SORT");
 defaultNameMapper.configFieldColumnName("newsStatus","NEWS_STATUS");
 defaultNameMapper.configFieldColumnName("newsReadNum","NEWS_READ_NUM");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyTime","MODIFY_TIME");
return defaultNameMapper;
}
}
