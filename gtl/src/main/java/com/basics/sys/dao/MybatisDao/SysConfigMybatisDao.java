package com.basics.sys.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.sys.dao.SysConfigDao;
import com.basics.sys.entity.SysConfig;
import com.basics.sys.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import com.basics.support.QueryFilterBuilder;

@Repository
public class SysConfigMybatisDao extends GenericMybatisDaoSupport<SysConfig> implements SysConfigDao {

	public SysConfigMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "CONFIG_ID");
		defaultNameMapper.configFieldColumnName("configCode", "CONFIG_CODE");
		defaultNameMapper.configFieldColumnName("configName", "CONFIG_NAME");
		defaultNameMapper.configFieldColumnName("configValue", "CONFIG_VALUE");
		defaultNameMapper.configFieldColumnName("configFlag", "CONFIG_FLAG");
		defaultNameMapper.configFieldColumnName("versionNum", "VERSION_NUM");
		defaultNameMapper.configFieldColumnName("flagDel", "FLAG_DEL");
		defaultNameMapper.configFieldColumnName("createTime", "CREATE_TIME");
		defaultNameMapper.configFieldColumnName("createUser", "CREATE_USER");
		defaultNameMapper.configFieldColumnName("modifyUser", "MODIFY_USER");
		defaultNameMapper.configFieldColumnName("modifyTime", "MODIFY_TIME");
		return defaultNameMapper;
	}

	@Override
	public SysConfig getConfigByCode(String configCode) {
		return queryOne(new QueryFilterBuilder().put("configCode", configCode).build());
	}
}
