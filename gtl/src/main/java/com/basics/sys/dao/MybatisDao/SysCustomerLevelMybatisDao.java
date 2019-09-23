package com.basics.sys.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.sys.dao.SysCustomerLevelDao;
import com.basics.sys.entity.SysCustomerLevel;
import com.basics.sys.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class SysCustomerLevelMybatisDao extends GenericMybatisDaoSupport<SysCustomerLevel> implements SysCustomerLevelDao {

public SysCustomerLevelMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","LEVEL_ID");
 defaultNameMapper.configFieldColumnName("levelName","LEVEL_NAME");
 defaultNameMapper.configFieldColumnName("levelEnglishName","LEVEL_ENGLISH_NAME");
 defaultNameMapper.configFieldColumnName("levelKoreanName","LEVEL_KOREAN_NAME");
 defaultNameMapper.configFieldColumnName("levelJapaneseName","LEVEL_JAPANESE_NAME");
 defaultNameMapper.configFieldColumnName("levelMinIntegral","LEVEL_MIN_INTEGRAL");
 defaultNameMapper.configFieldColumnName("levelMaxIntegral","LEVEL_MAX_INTEGRAL");
 defaultNameMapper.configFieldColumnName("salfNum","SALF_NUM");
 defaultNameMapper.configFieldColumnName("salfRewardRate","SALF_REWARD_RATE");
 defaultNameMapper.configFieldColumnName("recastRewardRate","RECAST_REWARD_RATE");
 defaultNameMapper.configFieldColumnName("teamOutRewardRate","TEAM_OUT_REWARD_RATE");
 defaultNameMapper.configFieldColumnName("teamInRewardRate","TEAM_IN_REWARD_RATE");
 defaultNameMapper.configFieldColumnName("flatRewardRate","FLAT_REWARD_RATE");
 defaultNameMapper.configFieldColumnName("exchangeRate","EXCHANGE_RATE");
 defaultNameMapper.configFieldColumnName("levelSort","LEVEL_SORT");
 defaultNameMapper.configFieldColumnName("floorNum","FLOOR_NUM");
 defaultNameMapper.configFieldColumnName("limitCoin","LIMIT_COIN");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyTime","MODIFY_TIME");
return defaultNameMapper;
}
}
