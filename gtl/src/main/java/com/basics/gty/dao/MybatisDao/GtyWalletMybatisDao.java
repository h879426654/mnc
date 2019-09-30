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
		defaultNameMapper.configFieldColumnName("superNum", "SUPER_NUM");
		defaultNameMapper.configFieldColumnName("recordNum", "RECORD_NUM");
		defaultNameMapper.configFieldColumnName("mTokenNum", "MTOKEN_NUM");
		defaultNameMapper.configFieldColumnName("scoreNum", "SCORE_NUM");

		defaultNameMapper.configFieldColumnName("RELEASED_SUPER_NUM", "releasedSuperNum");
		defaultNameMapper.configFieldColumnName("RELEASED_MNC_NUM", "releasedMnc");
		defaultNameMapper.configFieldColumnName("walletAddress", "WALLET_ADDRESS");

		defaultNameMapper.configFieldColumnName("blockNum", "BLOCK_NUM");
		defaultNameMapper.configFieldColumnName("walletFrozen", "WALLET_FROZEN");

		return defaultNameMapper;
	}
}
