package com.basics.app.dao.impl;

import org.springframework.stereotype.Repository;

import com.basics.app.dao.AppAreaDao;
import com.basics.app.entity.AppArea;
import com.basics.app.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class AppAreaMybatisDao extends GenericMybatisDaoSupport<AppArea> implements AppAreaDao {

	public AppAreaMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "AREA_ID");
		defaultNameMapper.configFieldColumnName("areaParentId", "AREA_PARENT_ID");
		defaultNameMapper.configFieldColumnName("areaCode", "AREA_CODE");
		defaultNameMapper.configFieldColumnName("areaName", "AREA_NAME");
		defaultNameMapper.configFieldColumnName("areaComment", "AREA_COMMENT");
		defaultNameMapper.configFieldColumnName("areaOrder", "AREA_ORDER");
		defaultNameMapper.configFieldColumnName("areaIcon", "AREA_ICON");
		defaultNameMapper.configFieldColumnName("areaUrl", "AREA_URL");
		defaultNameMapper.configFieldColumnName("areaType", "AREA_TYPE");
		defaultNameMapper.configFieldColumnName("areaFlag", "AREA_FLAG");
		return defaultNameMapper;
	}
}
