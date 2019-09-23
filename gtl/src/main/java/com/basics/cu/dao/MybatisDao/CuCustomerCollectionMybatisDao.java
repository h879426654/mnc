package com.basics.cu.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.cu.dao.CuCustomerCollectionDao;
import com.basics.cu.entity.CuCustomerCollection;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class CuCustomerCollectionMybatisDao extends GenericMybatisDaoSupport<CuCustomerCollection> implements CuCustomerCollectionDao {

public CuCustomerCollectionMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","COLLECTION_ID");
 defaultNameMapper.configFieldColumnName("customerId","CUSTOMER_ID");
 defaultNameMapper.configFieldColumnName("collectionType","COLLECTION_TYPE");
 defaultNameMapper.configFieldColumnName("sourceId","SOURCE_ID");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyDate","MODIFY_DATE");
return defaultNameMapper;
}
}
