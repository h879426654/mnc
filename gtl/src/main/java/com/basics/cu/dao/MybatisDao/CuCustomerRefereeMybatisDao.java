package com.basics.cu.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.cu.dao.CuCustomerRefereeDao;
import com.basics.cu.entity.CuCustomerReferee;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class CuCustomerRefereeMybatisDao extends GenericMybatisDaoSupport<CuCustomerReferee> implements CuCustomerRefereeDao {

public CuCustomerRefereeMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","CUSTOMER_ID");
 defaultNameMapper.configFieldColumnName("refereeId","REFEREE_ID");
return defaultNameMapper;
}
}
