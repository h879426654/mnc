package com.basics.mall.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.mall.dao.MallIndexTypeDao;
import com.basics.mall.entity.MallIndexType;
import com.basics.mall.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class MallIndexTypeMybatisDao extends GenericMybatisDaoSupport<MallIndexType> implements MallIndexTypeDao {

public MallIndexTypeMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","TYPE_ID");
 defaultNameMapper.configFieldColumnName("typeTitle","TYPE_TITLE");
 defaultNameMapper.configFieldColumnName("typeImg","TYPE_IMG");
 defaultNameMapper.configFieldColumnName("typeUrl","TYPE_URL");
 defaultNameMapper.configFieldColumnName("typeSort","TYPE_SORT");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyDate","MODIFY_DATE");
return defaultNameMapper;
}
}
