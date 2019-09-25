package com.basics.cu.dao.MybatisDao;

import com.basics.cu.dao.CuConsumeDao;
import com.basics.cu.dao.CuReatil3Dao;
import com.basics.cu.entity.CuConsume;
import com.basics.cu.entity.CuReatil3;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CuConsumeMybatisDao extends GenericMybatisDaoSupport<CuConsume> implements CuConsumeDao {

	public CuConsumeMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "ID");
		defaultNameMapper.configFieldColumnName("shopId", "SHOP_ID");
		defaultNameMapper.configFieldColumnName("customerId", "CUSTOMER_ID");
		defaultNameMapper.configFieldColumnName("shopName", "SHOP_NAME");
		defaultNameMapper.configFieldColumnName("money", "MONEY");
		defaultNameMapper.configFieldColumnName("phone", "PHONE");
		defaultNameMapper.configFieldColumnName("state", "STATE");
		defaultNameMapper.configFieldColumnName("image", "IMAGE");
		defaultNameMapper.configFieldColumnName("createTime", "CREATE_TIME");
		return defaultNameMapper;
	}
}
