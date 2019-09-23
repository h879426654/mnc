package com.basics.cu.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.cu.dao.CuCustomerSignDao;
import com.basics.cu.entity.CuCustomerSign;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class CuCustomerSignMybatisDao extends GenericMybatisDaoSupport<CuCustomerSign> implements CuCustomerSignDao {

public CuCustomerSignMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","SIGN_ID");
 defaultNameMapper.configFieldColumnName("customerId","CUSTOMER_ID");
 defaultNameMapper.configFieldColumnName("signNum","SIGN_NUM");
 defaultNameMapper.configFieldColumnName("signTime","SIGN_TIME");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyDate","MODIFY_DATE");
return defaultNameMapper;
}
}
