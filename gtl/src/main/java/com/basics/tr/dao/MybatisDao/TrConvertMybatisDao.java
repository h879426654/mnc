package com.basics.tr.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.tr.dao.TrConvertDao;
import com.basics.tr.entity.TrConvert;
import com.basics.tr.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class TrConvertMybatisDao extends GenericMybatisDaoSupport<TrConvert> implements TrConvertDao {

public TrConvertMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","CONVERT_ID");
 defaultNameMapper.configFieldColumnName("convertSerial","CONVERT_SERIAL");
 defaultNameMapper.configFieldColumnName("convertName","CONVERT_NAME");
 defaultNameMapper.configFieldColumnName("convertRemark","CONVERT_REMARK");
 defaultNameMapper.configFieldColumnName("convertNum","CONVERT_NUM");
 defaultNameMapper.configFieldColumnName("convertTotalNum","CONVERT_TOTAL_NUM");
 defaultNameMapper.configFieldColumnName("convertStartTime","CONVERT_START_TIME");
 defaultNameMapper.configFieldColumnName("convertEndTime","CONVERT_END_TIME");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyTime","MODIFY_TIME");
return defaultNameMapper;
}
}
