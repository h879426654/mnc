package com.basics.cu.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.cu.dao.CuCustomerAccountDao;
import com.basics.cu.entity.CuCustomerAccount;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class CuCustomerAccountMybatisDao extends GenericMybatisDaoSupport<CuCustomerAccount> implements CuCustomerAccountDao {

	public CuCustomerAccountMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "CUSTOMER_ID");
		defaultNameMapper.configFieldColumnName("useMoney", "USE_MONEY");
		defaultNameMapper.configFieldColumnName("lockMoney", "LOCK_MONEY");
		defaultNameMapper.configFieldColumnName("totalMoney", "TOTAL_MONEY");
		defaultNameMapper.configFieldColumnName("customerIntegral", "CUSTOMER_INTEGRAL");
		defaultNameMapper.configFieldColumnName("useCoin", "USE_COIN");
		defaultNameMapper.configFieldColumnName("lockCoin", "LOCK_COIN");
		defaultNameMapper.configFieldColumnName("totalCoin", "TOTAL_COIN");
		defaultNameMapper.configFieldColumnName("tradeCoin", "TRADE_COIN");
		defaultNameMapper.configFieldColumnName("customerPayPass", "CUSTOMER_PAY_PASS");
		defaultNameMapper.configFieldColumnName("customerPurse", "CUSTOMER_PURSE");
		defaultNameMapper.configFieldColumnName("rateNum", "RATE_NUM");
		defaultNameMapper.configFieldColumnName("versionNum", "VERSION_NUM");
		defaultNameMapper.configFieldColumnName("flagDel", "FLAG_DEL");
		defaultNameMapper.configFieldColumnName("createTime", "CREATE_TIME");
		defaultNameMapper.configFieldColumnName("createUser", "CREATE_USER");
		defaultNameMapper.configFieldColumnName("modifyUser", "MODIFY_USER");
		defaultNameMapper.configFieldColumnName("modifyTime", "MODIFY_TIME");
		return defaultNameMapper;
	}
}
