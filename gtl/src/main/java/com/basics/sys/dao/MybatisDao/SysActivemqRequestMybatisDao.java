package com.basics.sys.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.sys.dao.SysActivemqRequestDao;
import com.basics.sys.entity.SysActivemqRequest;
import com.basics.sys.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class SysActivemqRequestMybatisDao extends GenericMybatisDaoSupport<SysActivemqRequest> implements SysActivemqRequestDao {

public SysActivemqRequestMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","ACTIVEMQ_ID");
 defaultNameMapper.configFieldColumnName("customerId","CUSTOMER_ID");
 defaultNameMapper.configFieldColumnName("activemqType","ACTIVEMQ_TYPE");
 defaultNameMapper.configFieldColumnName("activemqContext","ACTIVEMQ_CONTEXT");
 defaultNameMapper.configFieldColumnName("activemqRemark","ACTIVEMQ_REMARK");
 defaultNameMapper.configFieldColumnName("createDate","CREATE_DATE");
return defaultNameMapper;
}
}
