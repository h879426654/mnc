package com.basics.sys.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.sys.dao.SysRuleDao;
import com.basics.sys.entity.SysRule;
import com.basics.sys.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class SysRuleMybatisDao extends GenericMybatisDaoSupport<SysRule> implements SysRuleDao {

public SysRuleMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","RULE_ID");
 defaultNameMapper.configFieldColumnName("releaseRateDay","RELEASE_RATE_DAY");
 defaultNameMapper.configFieldColumnName("rateToIntegralFirst","RATE_TO_INTEGRAL_FIRST");
 defaultNameMapper.configFieldColumnName("rateToIntegralMore","RATE_TO_INTEGRAL_MORE");
 defaultNameMapper.configFieldColumnName("moneyOutRate","MONEY_OUT_RATE");
 defaultNameMapper.configFieldColumnName("moneyTradeRate","MONEY_TRADE_RATE");
 defaultNameMapper.configFieldColumnName("moneySaleRate","MONEY_SALE_RATE");
 defaultNameMapper.configFieldColumnName("moneyTradeRateSale","MONEY_TRADE_RATE_SALE");
 defaultNameMapper.configFieldColumnName("convertRate","CONVERT_RATE");
 defaultNameMapper.configFieldColumnName("signRewardNum","SIGN_REWARD_NUM");
 defaultNameMapper.configFieldColumnName("tradeRate","TRADE_RATE");
 defaultNameMapper.configFieldColumnName("tradeStartTime","TRADE_START_TIME");
 defaultNameMapper.configFieldColumnName("tradeEndTime","TRADE_END_TIME");
 defaultNameMapper.configFieldColumnName("customerTradeNum","CUSTOMER_TRADE_NUM");
 defaultNameMapper.configFieldColumnName("tradeApplyFlag","TRADE_APPLY_FLAG");
 defaultNameMapper.configFieldColumnName("tradeMinNum","TRADE_MIN_NUM");
 defaultNameMapper.configFieldColumnName("tradeMaxNum","TRADE_MAX_NUM");
 defaultNameMapper.configFieldColumnName("mallMinPrice","MALL_MIN_PRICE");
 defaultNameMapper.configFieldColumnName("mallMaxPrice","MALL_MAX_PRICE");
 defaultNameMapper.configFieldColumnName("buyMinPrice","BUY_MIN_PRICE");
 defaultNameMapper.configFieldColumnName("buyMaxPrice","BUY_MAX_PRICE");
 defaultNameMapper.configFieldColumnName("tradeTimeOut","TRADE_TIME_OUT");
 defaultNameMapper.configFieldColumnName("needShopMoney","NEED_SHOP_MONEY");
 defaultNameMapper.configFieldColumnName("discountNum","DISCOUNT_NUM");
 defaultNameMapper.configFieldColumnName("rewardNum","REWARD_NUM");
 defaultNameMapper.configFieldColumnName("rewardFlag","REWARD_FLAG");
 defaultNameMapper.configFieldColumnName("needUploadLicence","NEED_UPLOAD_LICENCE");
 defaultNameMapper.configFieldColumnName("androidVersion","ANDROID_VERSION");
 defaultNameMapper.configFieldColumnName("androidDownload","ANDROID_DOWNLOAD");
 defaultNameMapper.configFieldColumnName("appleVersion","APPLE_VERSION");
 defaultNameMapper.configFieldColumnName("appleDownload","APPLE_DOWNLOAD");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyTime","MODIFY_TIME");
return defaultNameMapper;
}
}
