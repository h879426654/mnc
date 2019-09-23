package com.basics.tr.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.tr.dao.TrTradeConvertDao;
import com.basics.tr.entity.TrTradeConvert;
import com.basics.tr.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class TrTradeConvertMybatisDao extends GenericMybatisDaoSupport<TrTradeConvert> implements TrTradeConvertDao {

public TrTradeConvertMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","TRADE_ID");
 defaultNameMapper.configFieldColumnName("tradeSerial","TRADE_SERIAL");
 defaultNameMapper.configFieldColumnName("customerId","CUSTOMER_ID");
 defaultNameMapper.configFieldColumnName("moneyNum","MONEY_NUM");
 defaultNameMapper.configFieldColumnName("tradeStatus","TRADE_STATUS");
 defaultNameMapper.configFieldColumnName("customerBuyId","CUSTOMER_BUY_ID");
 defaultNameMapper.configFieldColumnName("tradePayTime","TRADE_PAY_TIME");
 defaultNameMapper.configFieldColumnName("tradeFinishTime","TRADE_FINISH_TIME");
 defaultNameMapper.configFieldColumnName("applyStatus","APPLY_STATUS");
 defaultNameMapper.configFieldColumnName("applyContext","APPLY_CONTEXT");
 defaultNameMapper.configFieldColumnName("applyTime","APPLY_TIME");
 defaultNameMapper.configFieldColumnName("lockMoneyNum","LOCK_MONEY_NUM");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyTime","MODIFY_TIME");
return defaultNameMapper;
}
}
