package com.basics.cu.dao.MybatisDao;

import com.basics.cu.dao.CuReatilMoneyDao;
import com.basics.cu.entity.CuReatilMoney;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CuLogsMybatisDao extends GenericMybatisDaoSupport<CuReatilMoney> implements CuReatilMoneyDao {

	public CuLogsMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "ID");
		defaultNameMapper.configFieldColumnName("type", "TYPE");
		defaultNameMapper.configFieldColumnName("customerId", "CUSTOMER_ID");
		defaultNameMapper.configFieldColumnName("shopId", "SHOP_ID");
		defaultNameMapper.configFieldColumnName("mp", "MP");
		defaultNameMapper.configFieldColumnName("money", "MONEY");
		defaultNameMapper.configFieldColumnName("remark", "REMARK");
		defaultNameMapper.configFieldColumnName("createTime", "CREATE_TIME");
		return defaultNameMapper;
	}
}
