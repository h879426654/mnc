package com.basics.cu.dao.MybatisDao;

import com.basics.cu.dao.CuHistoryDao;
import com.basics.cu.dao.CuReatil3Dao;
import com.basics.cu.entity.CuHistory;
import com.basics.cu.entity.CuReatil3;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CuHistoryMybatisDao extends GenericMybatisDaoSupport<CuHistory> implements CuHistoryDao {

	public CuHistoryMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "ID");
		defaultNameMapper.configFieldColumnName("customerId", "customer_id");
		defaultNameMapper.configFieldColumnName("shopId", "shop_id");
		defaultNameMapper.configFieldColumnName("shopName", "shop_name");
		defaultNameMapper.configFieldColumnName("createTime", "create_time");
		defaultNameMapper.configFieldColumnName("image", "image");
		defaultNameMapper.configFieldColumnName("address", "address");
		defaultNameMapper.configFieldColumnName("longitude", "longitude");
		defaultNameMapper.configFieldColumnName("latitude", "latitude");
		return defaultNameMapper;
	}
}
