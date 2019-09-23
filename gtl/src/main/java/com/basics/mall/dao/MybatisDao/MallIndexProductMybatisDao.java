package com.basics.mall.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.mall.dao.MallIndexProductDao;
import com.basics.mall.entity.MallIndexProduct;
import com.basics.mall.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class MallIndexProductMybatisDao extends GenericMybatisDaoSupport<MallIndexProduct> implements MallIndexProductDao {

public MallIndexProductMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","INDEX_ID");
 defaultNameMapper.configFieldColumnName("typeId","TYPE_ID");
 defaultNameMapper.configFieldColumnName("productId","PRODUCT_ID");
 defaultNameMapper.configFieldColumnName("productImage","PRODUCT_IMAGE");
 defaultNameMapper.configFieldColumnName("indexSort","INDEX_SORT");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyDate","MODIFY_DATE");
return defaultNameMapper;
}
}
