package com.basics.mall.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.mall.dao.MallProductCommentDao;
import com.basics.mall.entity.MallProductComment;
import com.basics.mall.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class MallProductCommentMybatisDao extends GenericMybatisDaoSupport<MallProductComment> implements MallProductCommentDao {

public MallProductCommentMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","COMMENT_ID");
 defaultNameMapper.configFieldColumnName("productId","PRODUCT_ID");
 defaultNameMapper.configFieldColumnName("customerId","CUSTOMER_ID");
 defaultNameMapper.configFieldColumnName("commentContext","COMMENT_CONTEXT");
 defaultNameMapper.configFieldColumnName("commentType","COMMENT_TYPE");
 defaultNameMapper.configFieldColumnName("commentDescribeSart","COMMENT_DESCRIBE_SART");
 defaultNameMapper.configFieldColumnName("commentServiceSart","COMMENT_SERVICE_SART");
 defaultNameMapper.configFieldColumnName("commentLogisticsSart","COMMENT_LOGISTICS_SART");
 defaultNameMapper.configFieldColumnName("flagAnonymous","FLAG_ANONYMOUS");
 defaultNameMapper.configFieldColumnName("replyContext","REPLY_CONTEXT");
 defaultNameMapper.configFieldColumnName("replyTime","REPLY_TIME");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyDate","MODIFY_DATE");
return defaultNameMapper;
}
}
