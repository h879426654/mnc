package com.basics.cu.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.cu.dao.CuCustomerProfitAdminDao;
import com.basics.cu.entity.CuCustomerProfitAdmin;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class CuCustomerProfitAdminMybatisDao extends GenericMybatisDaoSupport<CuCustomerProfitAdmin> implements CuCustomerProfitAdminDao {

public CuCustomerProfitAdminMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","PROFIT_ID");
 defaultNameMapper.configFieldColumnName("customerId","CUSTOMER_ID");
 defaultNameMapper.configFieldColumnName("profitNum","PROFIT_NUM");
 defaultNameMapper.configFieldColumnName("profitType","PROFIT_TYPE");
 defaultNameMapper.configFieldColumnName("profitStatus","PROFIT_STATUS");
 defaultNameMapper.configFieldColumnName("profitHavedTime","PROFIT_HAVED_TIME");
 defaultNameMapper.configFieldColumnName("profitSource","PROFIT_SOURCE");
 defaultNameMapper.configFieldColumnName("profitRemark","PROFIT_REMARK");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyTime","MODIFY_TIME");
return defaultNameMapper;
}
}
