package com.basics.sys.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.sys.dao.SysProfitDao;
import com.basics.sys.entity.SysProfit;
import com.basics.sys.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class SysProfitMybatisDao extends GenericMybatisDaoSupport<SysProfit> implements SysProfitDao {

public SysProfitMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","PROFIT_ID");
 defaultNameMapper.configFieldColumnName("profitSourceId","PROFIT_SOURCE_ID");
 defaultNameMapper.configFieldColumnName("profitSource","PROFIT_SOURCE");
 defaultNameMapper.configFieldColumnName("profitNum","PROFIT_NUM");
 defaultNameMapper.configFieldColumnName("profitRemark","PROFIT_REMARK");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyTime","MODIFY_TIME");
return defaultNameMapper;
}
}
