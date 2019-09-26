package com.basics.cu.dao.MybatisDao;

import com.basics.cu.dao.CuReatil1Dao;
import com.basics.cu.dao.CuReatil3Dao;
import com.basics.cu.entity.CuReatil1;
import com.basics.cu.entity.CuReatil3;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public class CuReatil3MybatisDao extends GenericMybatisDaoSupport<CuReatil3> implements CuReatil3Dao {

	public CuReatil3MybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "ID");
		defaultNameMapper.configFieldColumnName("customerId", "CUSTOMER_ID");
		defaultNameMapper.configFieldColumnName("customerIdSecond", "CUSTOMER_ID_SECOND");
		defaultNameMapper.configFieldColumnName("customerIdThird", "CUSTOMER_ID_THIRD");
		defaultNameMapper.configFieldColumnName("image", "IMAGE");
		return defaultNameMapper;
	}
}
