package com.basics.cu.dao.MybatisDao;

import com.basics.cu.dao.CuDiscussDao;
import com.basics.cu.dao.CuReatil3Dao;
import com.basics.cu.entity.CuDiscuss;
import com.basics.cu.entity.CuReatil3;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CuDiscussMybatisDao extends GenericMybatisDaoSupport<CuDiscuss> implements CuDiscussDao {

	public CuDiscussMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "ID");
		defaultNameMapper.configFieldColumnName("customerId", "CUSTOMER_ID");
		defaultNameMapper.configFieldColumnName("shopId", "SHOP_ID");
		defaultNameMapper.configFieldColumnName("createTime", "CREATE_TIME");
		defaultNameMapper.configFieldColumnName("state", "STATE");
		defaultNameMapper.configFieldColumnName("remark", "REMARK");
		return defaultNameMapper;
	}
}
