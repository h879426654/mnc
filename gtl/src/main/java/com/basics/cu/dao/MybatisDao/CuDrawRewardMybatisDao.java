package com.basics.cu.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.cu.dao.CuDrawRewardDao;
import com.basics.cu.entity.CuDrawReward;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class CuDrawRewardMybatisDao extends GenericMybatisDaoSupport<CuDrawReward> implements CuDrawRewardDao {

public CuDrawRewardMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","REWARD_ID");
 defaultNameMapper.configFieldColumnName("customerId","CUSTOMER_ID");
 defaultNameMapper.configFieldColumnName("rewardNum","REWARD_NUM");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyTime","MODIFY_TIME");
return defaultNameMapper;
}
}
