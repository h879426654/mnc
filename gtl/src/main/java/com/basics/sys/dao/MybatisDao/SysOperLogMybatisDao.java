package com.basics.sys.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.sys.dao.SysOperLogDao;
import com.basics.sys.entity.SysOperLog;
import com.basics.sys.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class SysOperLogMybatisDao extends GenericMybatisDaoSupport<SysOperLog> implements SysOperLogDao {

public SysOperLogMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","OPER_ID");
 defaultNameMapper.configFieldColumnName("operMethod","OPER_METHOD");
 defaultNameMapper.configFieldColumnName("loginName","LOGIN_NAME");
 defaultNameMapper.configFieldColumnName("operIp","OPER_IP");
 defaultNameMapper.configFieldColumnName("operParam","OPER_PARAM");
 defaultNameMapper.configFieldColumnName("operStatus","OPER_STATUS");
 defaultNameMapper.configFieldColumnName("errorMsg","ERROR_MSG");
 defaultNameMapper.configFieldColumnName("operTime","OPER_TIME");
return defaultNameMapper;
}
}
