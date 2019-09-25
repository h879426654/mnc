package com.basics.cu.dao.MybatisDao;

import com.basics.cu.dao.CuReatil2Dao;
import com.basics.cu.entity.CuReatil2;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CuReatil2MybatisDao extends GenericMybatisDaoSupport<CuReatil2> implements CuReatil2Dao {

	public CuReatil2MybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "ID");
		defaultNameMapper.configFieldColumnName("customerIdSecond", "CUSTOMER_ID_SECOND");
		defaultNameMapper.configFieldColumnName("customerIdThird", "CUSTOMER_ID_THIRD");
		defaultNameMapper.configFieldColumnName("image", "IMAGE");
		return defaultNameMapper;
	}
}
