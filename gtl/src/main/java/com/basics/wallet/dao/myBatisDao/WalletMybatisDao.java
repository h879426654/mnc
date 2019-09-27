package com.basics.wallet.dao.myBatisDao;

import com.basics.mall.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import com.basics.wallet.dao.WalletEntityDao;
import com.basics.wallet.entity.WalletEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WalletMybatisDao extends GenericMybatisDaoSupport<WalletEntity> implements WalletEntityDao {

    public WalletMybatisDao() {
        super();
        this.setPrimaryKeyFields("userId");
    }

    public INameMapper onBuildNameMapper() {
        DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
        defaultNameMapper.configFieldColumnName("userId", "USER_ID");

        return defaultNameMapper;
    }
}
