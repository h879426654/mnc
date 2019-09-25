package com.basics.cu.dao.MybatisDao;

import com.basics.cu.dao.CuHttpUrlDao;
import com.basics.cu.entity.CuHttpUrl;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CuHttpUrlMybatisDao extends GenericMybatisDaoSupport<CuHttpUrl> implements CuHttpUrlDao {

	public CuHttpUrlMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "ID");
		defaultNameMapper.configFieldColumnName("url", "URL");
		defaultNameMapper.configFieldColumnName("name", "NAME");
		return defaultNameMapper;
	}
}
