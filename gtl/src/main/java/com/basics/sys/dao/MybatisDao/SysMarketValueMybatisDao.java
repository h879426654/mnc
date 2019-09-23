package com.basics.sys.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.sys.dao.SysMarketValueDao;
import com.basics.sys.entity.SysMarketValue;
import com.basics.sys.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class SysMarketValueMybatisDao extends GenericMybatisDaoSupport<SysMarketValue> implements SysMarketValueDao {

public SysMarketValueMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","RATE_ID");
 defaultNameMapper.configFieldColumnName("dollarRate","DOLLAR_RATE");
 defaultNameMapper.configFieldColumnName("rmbRate","RMB_RATE");
 defaultNameMapper.configFieldColumnName("rateTime","RATE_TIME");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyTime","MODIFY_TIME");
return defaultNameMapper;
}
}
