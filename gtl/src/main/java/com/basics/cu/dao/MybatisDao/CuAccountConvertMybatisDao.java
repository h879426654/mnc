package com.basics.cu.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.cu.dao.CuAccountConvertDao;
import com.basics.cu.entity.CuAccountConvert;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class CuAccountConvertMybatisDao extends GenericMybatisDaoSupport<CuAccountConvert> implements CuAccountConvertDao {

public CuAccountConvertMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","CONVERT_ID");
 defaultNameMapper.configFieldColumnName("customerId","CUSTOMER_ID");
 defaultNameMapper.configFieldColumnName("convertType","CONVERT_TYPE");
 defaultNameMapper.configFieldColumnName("convertMoney","CONVERT_MONEY");
 defaultNameMapper.configFieldColumnName("convertNum","CONVERT_NUM");
 defaultNameMapper.configFieldColumnName("sourceId","SOURCE_ID");
 defaultNameMapper.configFieldColumnName("convertRemark","CONVERT_REMARK");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyDate","MODIFY_DATE");
return defaultNameMapper;
}
}
