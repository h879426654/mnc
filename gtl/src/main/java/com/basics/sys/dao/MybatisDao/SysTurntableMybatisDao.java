package com.basics.sys.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.sys.dao.SysTurntableDao;
import com.basics.sys.entity.SysTurntable;
import com.basics.sys.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class SysTurntableMybatisDao extends GenericMybatisDaoSupport<SysTurntable> implements SysTurntableDao {

public SysTurntableMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","TURNTABLE_ID");
 defaultNameMapper.configFieldColumnName("rewardType","REWARD_TYPE");
 defaultNameMapper.configFieldColumnName("rewardNum","REWARD_NUM");
 defaultNameMapper.configFieldColumnName("rewardRate","REWARD_RATE");
 defaultNameMapper.configFieldColumnName("rewardSort","REWARD_SORT");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyTime","MODIFY_TIME");
return defaultNameMapper;
}
}
