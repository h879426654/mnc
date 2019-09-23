package com.basics.app.dao.impl;

import org.springframework.stereotype.Repository;

import com.basics.app.dao.AppRewardDao;
import com.basics.app.entity.AppReward;
import com.basics.app.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class AppRewardMybatisDao extends GenericMybatisDaoSupport<AppReward> implements AppRewardDao {

	public AppRewardMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "REWARD_ID");
		defaultNameMapper.configFieldColumnName("rewardType", "REWARD_TYPE");
		defaultNameMapper.configFieldColumnName("machineId", "MACHINE_ID");
		defaultNameMapper.configFieldColumnName("machineNum", "MACHINE_NUM");
		defaultNameMapper.configFieldColumnName("versionNum", "VERSION_NUM");
		defaultNameMapper.configFieldColumnName("flagDel", "FLAG_DEL");
		defaultNameMapper.configFieldColumnName("createTime", "CREATE_TIME");
		defaultNameMapper.configFieldColumnName("createUser", "CREATE_USER");
		defaultNameMapper.configFieldColumnName("modifyUser", "MODIFY_USER");
		defaultNameMapper.configFieldColumnName("modifyDate", "MODIFY_DATE");
		return defaultNameMapper;
	}
}
