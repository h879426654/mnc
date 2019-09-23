package com.basics.tr.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import com.basics.tr.dao.TrTradeMoneyDao;
import com.basics.tr.entity.TrTradeMoney;
import com.basics.tr.support.GenericMybatisDaoSupport;

@Repository
public class TrTradeMoneyMybatisDao extends GenericMybatisDaoSupport<TrTradeMoney> implements TrTradeMoneyDao {

	public TrTradeMoneyMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "TRADE_ID");
		defaultNameMapper.configFieldColumnName("countryId", "COUNTRY_ID");
		defaultNameMapper.configFieldColumnName("tradeSerial", "TRADE_SERIAL");
		defaultNameMapper.configFieldColumnName("customerId", "CUSTOMER_ID");
		defaultNameMapper.configFieldColumnName("tradeType", "TRADE_TYPE");
		defaultNameMapper.configFieldColumnName("tradePrice", "TRADE_PRICE");
		defaultNameMapper.configFieldColumnName("moneyNum", "MONEY_NUM");
		defaultNameMapper.configFieldColumnName("tradeStatus", "TRADE_STATUS");
		defaultNameMapper.configFieldColumnName("customerBuyId", "CUSTOMER_BUY_ID");
		defaultNameMapper.configFieldColumnName("tradeMatchTime", "TRADE_MATCH_TIME");
		defaultNameMapper.configFieldColumnName("tradePayType", "TRADE_PAY_TYPE");
		defaultNameMapper.configFieldColumnName("tradePayTime", "TRADE_PAY_TIME");
		defaultNameMapper.configFieldColumnName("tradeFinishTime", "TRADE_FINISH_TIME");
		defaultNameMapper.configFieldColumnName("applyStatus", "APPLY_STATUS");
		defaultNameMapper.configFieldColumnName("applyContext", "APPLY_CONTEXT");
		defaultNameMapper.configFieldColumnName("applyTime", "APPLY_TIME");
		defaultNameMapper.configFieldColumnName("lockMoneyNum", "LOCK_MONEY_NUM");
		defaultNameMapper.configFieldColumnName("lockTradeCoin", "LOCK_TRADE_COIN");
		defaultNameMapper.configFieldColumnName("versionNum", "VERSION_NUM");
		defaultNameMapper.configFieldColumnName("flagDel", "FLAG_DEL");
		defaultNameMapper.configFieldColumnName("createTime", "CREATE_TIME");
		defaultNameMapper.configFieldColumnName("createUser", "CREATE_USER");
		defaultNameMapper.configFieldColumnName("modifyUser", "MODIFY_USER");
		defaultNameMapper.configFieldColumnName("modifyTime", "MODIFY_TIME");
		return defaultNameMapper;
	}
}
