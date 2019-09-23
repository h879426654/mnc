package com.basics.sys.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.sys.dao.SysNoticeDao;
import com.basics.sys.entity.SysNotice;
import com.basics.sys.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class SysNoticeMybatisDao extends GenericMybatisDaoSupport<SysNotice> implements SysNoticeDao {

public SysNoticeMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","NOTICE_ID");
 defaultNameMapper.configFieldColumnName("bulletinType","BULLETIN_TYPE");
 defaultNameMapper.configFieldColumnName("noticeTitle","NOTICE_TITLE");
 defaultNameMapper.configFieldColumnName("noticeContext","NOTICE_CONTEXT");
 defaultNameMapper.configFieldColumnName("noticeSort","NOTICE_SORT");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyTime","MODIFY_TIME");
return defaultNameMapper;
}
}
