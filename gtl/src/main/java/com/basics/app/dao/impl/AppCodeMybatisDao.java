package com.basics.app.dao.impl;

import org.springframework.stereotype.Repository;

import com.basics.app.dao.AppCodeDao;
import com.basics.app.entity.AppCode;
import com.basics.app.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class AppCodeMybatisDao extends GenericMybatisDaoSupport<AppCode> implements AppCodeDao {

	public AppCodeMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "CODE_ID");
		defaultNameMapper.configFieldColumnName("codeType", "CODE_TYPE");
		defaultNameMapper.configFieldColumnName("codeMobile", "CODE_MOBILE");
		defaultNameMapper.configFieldColumnName("codeCode", "CODE_CODE");
		defaultNameMapper.configFieldColumnName("codeState", "CODE_STATE");
		defaultNameMapper.configFieldColumnName("codeText", "CODE_TEXT");
		defaultNameMapper.configFieldColumnName("codeCreateTime", "CODE_CREATE_TIME");
		defaultNameMapper.configFieldColumnName("codeExpirationTime", "CODE_EXPIRATION_TIME");
		return defaultNameMapper;
	}
}
