package com.basics.app.dao.impl;

import org.springframework.stereotype.Repository;

import com.basics.app.dao.AppLogDao;
import com.basics.app.entity.AppLog;
import com.basics.app.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class AppLogMybatisDao extends GenericMybatisDaoSupport<AppLog> implements AppLogDao {

	public AppLogMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "LOG_ID");
		defaultNameMapper.configFieldColumnName("logName", "LOG_NAME");
		defaultNameMapper.configFieldColumnName("logContext", "LOG_CONTEXT");
		defaultNameMapper.configFieldColumnName("logRemark", "LOG_REMARK");
		defaultNameMapper.configFieldColumnName("logCreateDate", "LOG_CREATE_DATE");
		return defaultNameMapper;
	}
}
