package com.basics.or.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.or.dao.OrOrderDetailsDao;
import com.basics.or.entity.OrOrderDetails;
import com.basics.or.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class OrOrderDetailsMybatisDao extends GenericMybatisDaoSupport<OrOrderDetails> implements OrOrderDetailsDao {

public OrOrderDetailsMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","ORDER_DETAIL_ID");
 defaultNameMapper.configFieldColumnName("orderId","ORDER_ID");
 defaultNameMapper.configFieldColumnName("modifyDate","MODIFY_DATE");
 defaultNameMapper.configFieldColumnName("combinationId","COMBINATION_ID");
 defaultNameMapper.configFieldColumnName("productId","PRODUCT_ID");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("productNum","PRODUCT_NUM");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
return defaultNameMapper;
}
}
