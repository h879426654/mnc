package com.basics.gty.dao.MybatisDao;

import com.basics.gty.dao.GtyWalletDao;
import com.basics.gty.dao.GtyWalletHistoryDao;
import com.basics.gty.entity.GtyWallet;
import com.basics.gty.entity.GtyWalletHistory;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import com.basics.sys.support.GenericMybatisDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class GtyWalletHistoryMybatisDao extends GenericMybatisDaoSupport<GtyWalletHistory> implements GtyWalletHistoryDao {

	public GtyWalletHistoryMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "ID");
		defaultNameMapper.configFieldColumnName("userId", "USER_ID");
		defaultNameMapper.configFieldColumnName("recordType", "RECORD_TYPE");
		defaultNameMapper.configFieldColumnName("recordNum", "RECORD_NUM");
		defaultNameMapper.configFieldColumnName("toAccount", "RECORD_TO_ACCOUNT");
		return defaultNameMapper;
	}
}
