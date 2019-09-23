package com.basics.mall.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.mall.dao.MallProductClassifyDao;
import com.basics.mall.entity.MallProductClassify;
import com.basics.mall.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class MallProductClassifyMybatisDao extends GenericMybatisDaoSupport<MallProductClassify> implements MallProductClassifyDao {

public MallProductClassifyMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","CLASSIFY_ID");
 defaultNameMapper.configFieldColumnName("countryId","COUNTRY_ID");
 defaultNameMapper.configFieldColumnName("classifyName","CLASSIFY_NAME");
 defaultNameMapper.configFieldColumnName("classifyImg","CLASSIFY_IMG");
 defaultNameMapper.configFieldColumnName("classifyParentId","CLASSIFY_PARENT_ID");
 defaultNameMapper.configFieldColumnName("classifySort","CLASSIFY_SORT");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyDate","MODIFY_DATE");
return defaultNameMapper;
}
}
