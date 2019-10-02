package com.basics.cu.dao.MybatisDao;

import com.basics.cu.dao.CuReatil3Dao;
import com.basics.cu.dao.CuReatilMoneyDao;
import com.basics.cu.entity.CuReatil3;
import com.basics.cu.entity.CuReatilMoney;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CuReatilMoneyMybatisDao extends GenericMybatisDaoSupport<CuReatilMoney> implements CuReatilMoneyDao {

	public CuReatilMoneyMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "ID");
		defaultNameMapper.configFieldColumnName("reatilType", "reatil_type");
		defaultNameMapper.configFieldColumnName("money", "money");
		defaultNameMapper.configFieldColumnName("delFlag", "del_flag");
		return defaultNameMapper;
	}
}
