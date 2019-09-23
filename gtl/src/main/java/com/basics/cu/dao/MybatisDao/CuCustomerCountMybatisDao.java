package com.basics.cu.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.cu.dao.CuCustomerCountDao;
import com.basics.cu.entity.CuCustomerCount;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class CuCustomerCountMybatisDao extends GenericMybatisDaoSupport<CuCustomerCount> implements CuCustomerCountDao {

public CuCustomerCountMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","COUNT_ID");
 defaultNameMapper.configFieldColumnName("customerLevelId","CUSTOMER_LEVEL_ID");
 defaultNameMapper.configFieldColumnName("flagLevelAuto","FLAG_LEVEL_AUTO");
 defaultNameMapper.configFieldColumnName("salfNum","SALF_NUM");
 defaultNameMapper.configFieldColumnName("teamNum","TEAM_NUM");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyTime","MODIFY_TIME");
return defaultNameMapper;
}
}
