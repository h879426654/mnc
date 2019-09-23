package com.basics.mall.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.mall.dao.MallProductKindCombinationDao;
import com.basics.mall.entity.MallProductKindCombination;
import com.basics.mall.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class MallProductKindCombinationMybatisDao extends GenericMybatisDaoSupport<MallProductKindCombination> implements MallProductKindCombinationDao {

public MallProductKindCombinationMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","COMBINATION_ID");
 defaultNameMapper.configFieldColumnName("productId","PRODUCT_ID");
 defaultNameMapper.configFieldColumnName("combination","COMBINATION");
 defaultNameMapper.configFieldColumnName("combinationStockNum","COMBINATION_STOCK_NUM");
 defaultNameMapper.configFieldColumnName("combinationSellNum","COMBINATION_SELL_NUM");
 defaultNameMapper.configFieldColumnName("combinationPrice","COMBINATION_PRICE");
 defaultNameMapper.configFieldColumnName("combinationImg","COMBINATION_IMG");
return defaultNameMapper;
}
}
