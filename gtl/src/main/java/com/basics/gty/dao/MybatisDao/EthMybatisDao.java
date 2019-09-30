package com.basics.gty.dao.MybatisDao;

import com.basics.gty.dao.EthDao;
import com.basics.gty.dao.GtyWalletDao;
import com.basics.gty.entity.EthBean;
import com.basics.gty.entity.GtyWallet;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import com.basics.sys.support.GenericMybatisDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class EthMybatisDao extends GenericMybatisDaoSupport<EthBean> implements EthDao {

	public EthMybatisDao() {
		super();
		this.setPrimaryKeyFields("address");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("address", "ADDRESS");
		defaultNameMapper.configFieldColumnName("privateKey", "PRIVATE_KEY");
		return defaultNameMapper;
	}
}
