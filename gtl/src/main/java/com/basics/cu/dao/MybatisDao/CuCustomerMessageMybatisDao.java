package com.basics.cu.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.cu.dao.CuCustomerMessageDao;
import com.basics.cu.entity.CuCustomerMessage;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class CuCustomerMessageMybatisDao extends GenericMybatisDaoSupport<CuCustomerMessage> implements CuCustomerMessageDao {

public CuCustomerMessageMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","MESSAGE_ID");
 defaultNameMapper.configFieldColumnName("customerId","CUSTOMER_ID");
 defaultNameMapper.configFieldColumnName("messageType","MESSAGE_TYPE");
 defaultNameMapper.configFieldColumnName("appMessageId","APP_MESSAGE_ID");
 defaultNameMapper.configFieldColumnName("messageTitle","MESSAGE_TITLE");
 defaultNameMapper.configFieldColumnName("messageContext","MESSAGE_CONTEXT");
 defaultNameMapper.configFieldColumnName("flagRead","FLAG_READ");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyDate","MODIFY_DATE");
return defaultNameMapper;
}
}
