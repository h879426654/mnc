package com.basics.sys.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.sys.dao.SysActivemqResponseDao;
import com.basics.sys.entity.SysActivemqResponse;
import com.basics.sys.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class SysActivemqResponseMybatisDao extends GenericMybatisDaoSupport<SysActivemqResponse> implements SysActivemqResponseDao {

public SysActivemqResponseMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","ACTIVEMQ_ID");
 defaultNameMapper.configFieldColumnName("customerId","CUSTOMER_ID");
 defaultNameMapper.configFieldColumnName("activemqType","ACTIVEMQ_TYPE");
 defaultNameMapper.configFieldColumnName("activemqResponse","ACTIVEMQ_RESPONSE");
 defaultNameMapper.configFieldColumnName("createDate","CREATE_DATE");
return defaultNameMapper;
}
}
