package com.basics.cu.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.cu.dao.CuCustomerLoginDao;
import com.basics.cu.entity.CuCustomerLogin;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class CuCustomerLoginMybatisDao extends GenericMybatisDaoSupport<CuCustomerLogin> implements CuCustomerLoginDao {

	public CuCustomerLoginMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "CUSTOMER_ID");
		defaultNameMapper.configFieldColumnName("customerPassword", "CUSTOMER_PASSWORD");
		defaultNameMapper.configFieldColumnName("passSalt", "PASS_SALT");
		defaultNameMapper.configFieldColumnName("customerPhone", "CUSTOMER_PHONE");
		defaultNameMapper.configFieldColumnName("flagAuth", "FLAG_AUTH");
		defaultNameMapper.configFieldColumnName("customerStatus", "CUSTOMER_STATUS");
		defaultNameMapper.configFieldColumnName("loginErrorNum", "LOGIN_ERROR_NUM");
		defaultNameMapper.configFieldColumnName("lastLoginTime", "LAST_LOGIN_TIME");
		defaultNameMapper.configFieldColumnName("versionNum", "VERSION_NUM");
		defaultNameMapper.configFieldColumnName("flagDel", "FLAG_DEL");
		defaultNameMapper.configFieldColumnName("createTime", "CREATE_TIME");
		defaultNameMapper.configFieldColumnName("createUser", "CREATE_USER");
		defaultNameMapper.configFieldColumnName("modifyUser", "MODIFY_USER");
		defaultNameMapper.configFieldColumnName("modifyTime", "MODIFY_TIME");
		return defaultNameMapper;
	}
}
