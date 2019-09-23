package com.basics.cu.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.cu.dao.CuCustomerInfoDao;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class CuCustomerInfoMybatisDao extends GenericMybatisDaoSupport<CuCustomerInfo> implements CuCustomerInfoDao {

	public CuCustomerInfoMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "CUSTOMER_ID");
		defaultNameMapper.configFieldColumnName("countryId", "COUNTRY_ID");
		defaultNameMapper.configFieldColumnName("customerNumber", "CUSTOMER_NUMBER");
		defaultNameMapper.configFieldColumnName("customerName", "CUSTOMER_NAME");
		defaultNameMapper.configFieldColumnName("customerHead", "CUSTOMER_HEAD");
		defaultNameMapper.configFieldColumnName("customerPhone", "CUSTOMER_PHONE");
		defaultNameMapper.configFieldColumnName("customerAlipay", "CUSTOMER_ALIPAY");
		defaultNameMapper.configFieldColumnName("customerWechat", "CUSTOMER_WECHAT");
		defaultNameMapper.configFieldColumnName("customerEmail", "CUSTOMER_EMAIL");
		defaultNameMapper.configFieldColumnName("realName", "REAL_NAME");
		defaultNameMapper.configFieldColumnName("customerCard", "CUSTOMER_CARD");
		defaultNameMapper.configFieldColumnName("bankCard", "BANK_CARD");
		defaultNameMapper.configFieldColumnName("bankName", "BANK_NAME");
		defaultNameMapper.configFieldColumnName("flagAuth", "FLAG_AUTH");
		defaultNameMapper.configFieldColumnName("flagTrade", "FLAG_TRADE");
		defaultNameMapper.configFieldColumnName("flagSpecial", "FLAG_SPECIAL");
		defaultNameMapper.configFieldColumnName("customerStatus", "CUSTOMER_STATUS");
		defaultNameMapper.configFieldColumnName("customerFreezeContext", "CUSTOMER_FREEZE_CONTEXT");
		defaultNameMapper.configFieldColumnName("registerTime", "REGISTER_TIME");
		defaultNameMapper.configFieldColumnName("versionNum", "VERSION_NUM");
		defaultNameMapper.configFieldColumnName("flagDel", "FLAG_DEL");
		defaultNameMapper.configFieldColumnName("createTime", "CREATE_TIME");
		defaultNameMapper.configFieldColumnName("createUser", "CREATE_USER");
		defaultNameMapper.configFieldColumnName("modifyUser", "MODIFY_USER");
		defaultNameMapper.configFieldColumnName("modifyTime", "MODIFY_TIME");
		return defaultNameMapper;
	}
}
