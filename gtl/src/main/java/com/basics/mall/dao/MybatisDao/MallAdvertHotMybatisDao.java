package com.basics.mall.dao.MybatisDao;

import com.basics.mall.dao.MallAdvertHotDao;
import com.basics.mall.entity.MallAdvertHot;
import com.basics.mall.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MallAdvertHotMybatisDao extends GenericMybatisDaoSupport<MallAdvertHot> implements MallAdvertHotDao {

public MallAdvertHotMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","ID");
 defaultNameMapper.configFieldColumnName("advertId","ADVERT_ID");
 defaultNameMapper.configFieldColumnName("imageUrl","IMAGE_URL");
 defaultNameMapper.configFieldColumnName("advertName","ADVERT_NAME");
 defaultNameMapper.configFieldColumnName("count","COUNT");
 defaultNameMapper.configFieldColumnName("distance","DISTANCE");
 defaultNameMapper.configFieldColumnName("isHot","IS_HOT");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
return defaultNameMapper;
}
}
