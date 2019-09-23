package com.basics.mall.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.mall.dao.MallCustomerCarDao;
import com.basics.mall.entity.MallCustomerCar;
import com.basics.mall.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class MallCustomerCarMybatisDao extends GenericMybatisDaoSupport<MallCustomerCar> implements MallCustomerCarDao {

public MallCustomerCarMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","CAR_ID");
 defaultNameMapper.configFieldColumnName("customerId","CUSTOMER_ID");
 defaultNameMapper.configFieldColumnName("productId","PRODUCT_ID");
 defaultNameMapper.configFieldColumnName("productNum","PRODUCT_NUM");
 defaultNameMapper.configFieldColumnName("combinationId","COMBINATION_ID");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyDate","MODIFY_DATE");
return defaultNameMapper;
}
}
