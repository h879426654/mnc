package com.basics.cu.dao.MybatisDao;

import com.basics.cu.dao.CuCustomerAccountDao;
import com.basics.cu.dao.CuReatil1Dao;
import com.basics.cu.entity.CuCustomerAccount;
import com.basics.cu.entity.CuReatil1;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public class CuReatil1MybatisDao extends GenericMybatisDaoSupport<CuReatil1> implements CuReatil1Dao {

	public CuReatil1MybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "ID");
		defaultNameMapper.configFieldColumnName("customerId", "CUSTOMER_ID");
		defaultNameMapper.configFieldColumnName("money", "MONEY");
		defaultNameMapper.configFieldColumnName("indirectMoney", "INDIRECT_MONEY");
		defaultNameMapper.configFieldColumnName("create_time", "CREATE_TIME");
		return defaultNameMapper;
	}

	@Override
	@Select("select money,INDIRECT_MONEY from cu_retail1 where Customer_id = #{customerId} ")
	public CuReatil1 searchMoneyAndIndirectMoney(String customerId) {
		return null;
	}
}
