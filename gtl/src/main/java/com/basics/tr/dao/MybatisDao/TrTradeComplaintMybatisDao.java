package com.basics.tr.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.tr.dao.TrTradeComplaintDao;
import com.basics.tr.entity.TrTradeComplaint;
import com.basics.tr.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class TrTradeComplaintMybatisDao extends GenericMybatisDaoSupport<TrTradeComplaint> implements TrTradeComplaintDao {

public TrTradeComplaintMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","COMPLAINT_ID");
 defaultNameMapper.configFieldColumnName("tradeId","TRADE_ID");
 defaultNameMapper.configFieldColumnName("tradeType","TRADE_TYPE");
 defaultNameMapper.configFieldColumnName("customerId","CUSTOMER_ID");
 defaultNameMapper.configFieldColumnName("complaintContext","COMPLAINT_CONTEXT");
 defaultNameMapper.configFieldColumnName("complaintStatus","COMPLAINT_STATUS");
 defaultNameMapper.configFieldColumnName("complaintRemark","COMPLAINT_REMARK");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyTime","MODIFY_TIME");
return defaultNameMapper;
}
}
