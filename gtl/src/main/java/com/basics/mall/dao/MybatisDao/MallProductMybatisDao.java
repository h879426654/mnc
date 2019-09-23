package com.basics.mall.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.mall.dao.MallProductDao;
import com.basics.mall.entity.MallProduct;
import com.basics.mall.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class MallProductMybatisDao extends GenericMybatisDaoSupport<MallProduct> implements MallProductDao {

public MallProductMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","PRODUCT_ID");
 defaultNameMapper.configFieldColumnName("shopId","SHOP_ID");
 defaultNameMapper.configFieldColumnName("countryId","COUNTRY_ID");
 defaultNameMapper.configFieldColumnName("productFirstClassify","PRODUCT_FIRST_CLASSIFY");
 defaultNameMapper.configFieldColumnName("productSecondClassify","PRODUCT_SECOND_CLASSIFY");
 defaultNameMapper.configFieldColumnName("productName","PRODUCT_NAME");
 defaultNameMapper.configFieldColumnName("productStatus","PRODUCT_STATUS");
 defaultNameMapper.configFieldColumnName("productImg","PRODUCT_IMG");
 defaultNameMapper.configFieldColumnName("productPrice","PRODUCT_PRICE");
 defaultNameMapper.configFieldColumnName("productCost","PRODUCT_COST");
 defaultNameMapper.configFieldColumnName("productFreight","PRODUCT_FREIGHT");
 defaultNameMapper.configFieldColumnName("productContext","PRODUCT_CONTEXT");
 defaultNameMapper.configFieldColumnName("productSale","PRODUCT_SALE");
 defaultNameMapper.configFieldColumnName("productStock","PRODUCT_STOCK");
 defaultNameMapper.configFieldColumnName("productCoolection","PRODUCT_COOLECTION");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyDate","MODIFY_DATE");
return defaultNameMapper;
}
}
