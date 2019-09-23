package com.basics.sys.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.sys.dao.SysBannerDao;
import com.basics.sys.entity.SysBanner;
import com.basics.sys.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class SysBannerMybatisDao extends GenericMybatisDaoSupport<SysBanner> implements SysBannerDao {

public SysBannerMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","BANNER_ID");
 defaultNameMapper.configFieldColumnName("countryId","COUNTRY_ID");
 defaultNameMapper.configFieldColumnName("bannerTitle","BANNER_TITLE");
 defaultNameMapper.configFieldColumnName("bannerImage","BANNER_IMAGE");
 defaultNameMapper.configFieldColumnName("bannerUrl","BANNER_URL");
 defaultNameMapper.configFieldColumnName("bannerType","BANNER_TYPE");
 defaultNameMapper.configFieldColumnName("bannerSort","BANNER_SORT");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyTime","MODIFY_TIME");
return defaultNameMapper;
}
}
