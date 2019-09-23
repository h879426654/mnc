package com.basics.sys.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.sys.dao.SysCountryDao;
import com.basics.sys.entity.SysCountry;
import com.basics.sys.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class SysCountryMybatisDao extends GenericMybatisDaoSupport<SysCountry> implements SysCountryDao {

public SysCountryMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("contryId","COUNTRY_ID");
 defaultNameMapper.configFieldColumnName("countryNum","COUNTRY_NUM");
 defaultNameMapper.configFieldColumnName("countryCode","COUNTRY_CODE");
 defaultNameMapper.configFieldColumnName("countryName","COUNTRY_NAME");
 defaultNameMapper.configFieldColumnName("countryRate","COUNTRY_RATE");
 defaultNameMapper.configFieldColumnName("countrySymbol","COUNTRY_SYMBOL");
 defaultNameMapper.configFieldColumnName("countrySort","COUNTRY_SORT");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyTime","MODIFY_TIME");
return defaultNameMapper;
}
}
