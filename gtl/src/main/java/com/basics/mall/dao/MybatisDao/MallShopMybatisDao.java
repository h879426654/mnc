package com.basics.mall.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.mall.dao.MallShopDao;
import com.basics.mall.entity.MallShop;
import com.basics.mall.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class MallShopMybatisDao extends GenericMybatisDaoSupport<MallShop> implements MallShopDao {

public MallShopMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

@Override
public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","SHOP_ID");
 defaultNameMapper.configFieldColumnName("countryId","COUNTRY_ID");
 defaultNameMapper.configFieldColumnName("customerId","CUSTOMER_ID");
 defaultNameMapper.configFieldColumnName("shopName","SHOP_NAME");
 defaultNameMapper.configFieldColumnName("shopStar","SHOP_STAR");
 defaultNameMapper.configFieldColumnName("shopLogo","SHOP_LOGO");
 defaultNameMapper.configFieldColumnName("shopLicence","SHOP_LICENCE");
 defaultNameMapper.configFieldColumnName("shopPass","SHOP_PASS");
 defaultNameMapper.configFieldColumnName("applyStatus","APPLY_STATUS");
 defaultNameMapper.configFieldColumnName("applyContext","APPLY_CONTEXT");
 defaultNameMapper.configFieldColumnName("shopNumber","SHOP_NUMBER");
 defaultNameMapper.configFieldColumnName("shopStatus","SHOP_STATUS");
 defaultNameMapper.configFieldColumnName("needMoney","NEED_MONEY");
 defaultNameMapper.configFieldColumnName("shopDiscount","SHOP_DISCOUNT");
 defaultNameMapper.configFieldColumnName("shopService","SHOP_SERVICE");
 defaultNameMapper.configFieldColumnName("shopAddress","SHOP_ADDRESS");
 defaultNameMapper.configFieldColumnName("shopPhone","SHOP_PHONE");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyTime","MODIFY_TIME");
return defaultNameMapper;
}
}
