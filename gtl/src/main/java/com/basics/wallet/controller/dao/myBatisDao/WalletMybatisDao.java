package com.basics.wallet.controller.dao.myBatisDao;

import com.basics.mall.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import com.basics.wallet.controller.dao.WalletEntityDao;
import com.basics.wallet.controller.dao.WalletReleaseDao;
import com.basics.wallet.controller.entity.WalletEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WalletMybatisDao extends GenericMybatisDaoSupport<WalletEntity> implements WalletReleaseDao {

    public WalletMybatisDao() {
        super();
        this.setPrimaryKeyFields("userId");
    }

    public INameMapper onBuildNameMapper() {
        DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
        defaultNameMapper.configFieldColumnName("userId", "USER_ID");
        defaultNameMapper.configFieldColumnName("record_type", "SUPER_RELEASE");
        defaultNameMapper.configFieldColumnName("mtoken_release", "MTOKEN_RELEASE");
        defaultNameMapper.configFieldColumnName("score_release", "SCORE_RELEASE");

        return defaultNameMapper;
    }
}
