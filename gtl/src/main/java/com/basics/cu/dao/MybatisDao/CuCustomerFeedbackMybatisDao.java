package com.basics.cu.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.cu.dao.CuCustomerFeedbackDao;
import com.basics.cu.entity.CuCustomerFeedback;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class CuCustomerFeedbackMybatisDao extends GenericMybatisDaoSupport<CuCustomerFeedback> implements CuCustomerFeedbackDao {

public CuCustomerFeedbackMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","feedback_id");
 defaultNameMapper.configFieldColumnName("feedbackType","feedback_type");
 defaultNameMapper.configFieldColumnName("customerId","customer_id");
 defaultNameMapper.configFieldColumnName("feedbackContext","feedback_context");
 defaultNameMapper.configFieldColumnName("feedbackStatus","feedback_status");
 defaultNameMapper.configFieldColumnName("feedbackRemark","feedback_remark");
 defaultNameMapper.configFieldColumnName("createTime","create_time");
return defaultNameMapper;
}
}
