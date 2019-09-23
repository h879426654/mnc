package com.basics.mall.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.mall.dao.MallProductKindDao;
import com.basics.mall.entity.MallProductKind;
import com.basics.mall.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class MallProductKindMybatisDao extends GenericMybatisDaoSupport<MallProductKind> implements MallProductKindDao {

public MallProductKindMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","PRODUCT_KIND_ID");
 defaultNameMapper.configFieldColumnName("productKindName","PRODUCT_KIND_NAME");
 defaultNameMapper.configFieldColumnName("productKindDescription","PRODUCT_KIND_DESCRIPTION");
 defaultNameMapper.configFieldColumnName("productKindMosaicOrder","PRODUCT_KIND_MOSAIC_ORDER");
return defaultNameMapper;
}
}
