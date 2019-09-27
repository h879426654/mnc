package com.basics.gty.dao.MybatisDao;

import com.basics.gty.dao.GtyWalletDao;
import com.basics.gty.entity.GtyWallet;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import com.basics.sys.support.GenericMybatisDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class GtyWalletMybatisDao extends GenericMybatisDaoSupport<GtyWallet> implements GtyWalletDao {

	public GtyWalletMybatisDao() {
		super();
		this.setPrimaryKeyFields("userId");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("userId", "USER_ID");
		defaultNameMapper.configFieldColumnName("mncNum", "MNC_NUM");
		defaultNameMapper.configFieldColumnName("moveNum", "MOVE_NUM");
		return defaultNameMapper;
	}
}
