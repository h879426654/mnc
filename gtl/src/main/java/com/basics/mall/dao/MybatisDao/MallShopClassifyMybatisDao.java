package com.basics.mall.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.mall.dao.MallShopClassifyDao;
import com.basics.mall.entity.MallShopClassify;
import com.basics.mall.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class MallShopClassifyMybatisDao extends GenericMybatisDaoSupport<MallShopClassify> implements MallShopClassifyDao {

public MallShopClassifyMybatisDao() {
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
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyTime","MODIFY_TIME");
return defaultNameMapper;
}
}
