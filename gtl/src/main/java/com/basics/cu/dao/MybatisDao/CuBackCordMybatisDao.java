package com.basics.cu.dao.MybatisDao;

import com.basics.cu.dao.CuBackCordDao;
import com.basics.cu.dao.CuReatil1Dao;
import com.basics.cu.entity.CuBackCord;
import com.basics.cu.entity.CuReatil1;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CuBackCordMybatisDao extends GenericMybatisDaoSupport<CuBackCord> implements CuBackCordDao {

	public CuBackCordMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "ID");
		defaultNameMapper.configFieldColumnName("customerId", "CUSTOMER_ID");
		defaultNameMapper.configFieldColumnName("cordNumber", "cord_number");
		defaultNameMapper.configFieldColumnName("createTime", "create_time");
		defaultNameMapper.configFieldColumnName("delFlag", "del_flag");
		return defaultNameMapper;
	}
}
