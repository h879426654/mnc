package com.basics.mall.dao.MybatisDao;

import com.basics.mall.dao.MallShopDao;
import com.basics.mall.dao.MallUserDao;
import com.basics.mall.entity.MallShop;
import com.basics.mall.entity.MallUser;
import com.basics.mall.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MallUserMybatisDao extends GenericMybatisDaoSupport<MallUser> implements MallUserDao {

public MallUserMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

@Override
public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","id");
 defaultNameMapper.configFieldColumnName("customerId","customer_id");
 defaultNameMapper.configFieldColumnName("userName","user_name");
 defaultNameMapper.configFieldColumnName("passWord","pass_word");
 defaultNameMapper.configFieldColumnName("createTime","create_time");
return defaultNameMapper;
}
}
