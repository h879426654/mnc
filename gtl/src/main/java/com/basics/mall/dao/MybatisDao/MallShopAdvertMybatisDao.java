package com.basics.mall.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.mall.dao.MallShopAdvertDao;
import com.basics.mall.entity.MallShopAdvert;
import com.basics.mall.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class MallShopAdvertMybatisDao extends GenericMybatisDaoSupport<MallShopAdvert> implements MallShopAdvertDao {

public MallShopAdvertMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","ADVERT_ID");
 defaultNameMapper.configFieldColumnName("customerId","CUSTOMER_ID");
 defaultNameMapper.configFieldColumnName("countryId","COUNTRY_ID");
 defaultNameMapper.configFieldColumnName("advertName","ADVERT_NAME");
 defaultNameMapper.configFieldColumnName("advertContext","ADVERT_CONTEXT");
 defaultNameMapper.configFieldColumnName("advertImage","ADVERT_IMAGE");
 defaultNameMapper.configFieldColumnName("shopLicence","SHOP_LICENCE");
 defaultNameMapper.configFieldColumnName("shopVideo","SHOP_VIDEO");
 defaultNameMapper.configFieldColumnName("advertClassifyId","ADVERT_CLASSIFY_ID");
 defaultNameMapper.configFieldColumnName("advertPhone","ADVERT_PHONE");
 defaultNameMapper.configFieldColumnName("addressProvince","ADDRESS_PROVINCE");
 defaultNameMapper.configFieldColumnName("addressCity","ADDRESS_CITY");
 defaultNameMapper.configFieldColumnName("addressArea","ADDRESS_AREA");
 defaultNameMapper.configFieldColumnName("advertAddress","ADVERT_ADDRESS");
 defaultNameMapper.configFieldColumnName("advertLongitude","ADVERT_LONGITUDE");
 defaultNameMapper.configFieldColumnName("advertLatitude","ADVERT_LATITUDE");
 defaultNameMapper.configFieldColumnName("applyStatus","APPLY_STATUS");
 defaultNameMapper.configFieldColumnName("applyContext","APPLY_CONTEXT");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyTime","MODIFY_TIME");
return defaultNameMapper;
}
}
