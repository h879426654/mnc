package com.basics.sys.dao;
import com.basics.sys.entity.SysConfig;
import com.basics.support.GenericDao;

public interface SysConfigDao extends GenericDao<SysConfig> {

	SysConfig getConfigByCode(String string);

}