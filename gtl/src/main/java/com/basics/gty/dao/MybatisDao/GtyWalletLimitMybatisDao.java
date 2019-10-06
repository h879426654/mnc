package com.basics.gty.dao.MybatisDao;

import com.basics.gty.dao.GtyWalletHistoryDao;
import com.basics.gty.dao.GtyWalletLimitDao;
import com.basics.gty.entity.GtyLimitWallet;
import com.basics.gty.entity.GtyWalletHistory;
import com.basics.gty.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import org.springframework.stereotype.Repository;

@Repository
public class GtyWalletLimitMybatisDao extends GenericMybatisDaoSupport<GtyLimitWallet> implements GtyWalletLimitDao {

	public GtyWalletLimitMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "ID");
		defaultNameMapper.configFieldColumnName("limitWithDraw", "LIMIT_WITH_DRAW");
		defaultNameMapper.configFieldColumnName("limitDownSuperRelease", "LIMIT_DOWN_SUPER_RELEASE");
		defaultNameMapper.configFieldColumnName("limitUpSuperRelease", "LIMIT_UP_SUPER_RELEASE");
		defaultNameMapper.configFieldColumnName("limitDownScoreRelease", "LIMIT_DOWN_SCORE_RELEASE");
		defaultNameMapper.configFieldColumnName("limitUpScoreRelease", "LIMIT_UP_SCORE_RELEASE");
		return defaultNameMapper;
	}
}
