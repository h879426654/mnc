package com.basics.or.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.or.dao.OrOrderDao;
import com.basics.or.entity.OrOrder;
import com.basics.or.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class OrOrderMybatisDao extends GenericMybatisDaoSupport<OrOrder> implements OrOrderDao {

public OrOrderMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","ORDER_ID");
 defaultNameMapper.configFieldColumnName("shopId","SHOP_ID");
 defaultNameMapper.configFieldColumnName("customerId","CUSTOMER_ID");
 defaultNameMapper.configFieldColumnName("orderTotalPrice","ORDER_TOTAL_PRICE");
 defaultNameMapper.configFieldColumnName("orderPayPrice","ORDER_PAY_PRICE");
 defaultNameMapper.configFieldColumnName("orderPayType","ORDER_PAY_TYPE");
 defaultNameMapper.configFieldColumnName("orderNumber","ORDER_NUMBER");
 defaultNameMapper.configFieldColumnName("orderPayTime","ORDER_PAY_TIME");
 defaultNameMapper.configFieldColumnName("orderStatus","ORDER_STATUS");
 defaultNameMapper.configFieldColumnName("orderLogisticsCode","ORDER_LOGISTICS_CODE");
 defaultNameMapper.configFieldColumnName("orderLogisticsName","ORDER_LOGISTICS_NAME");
 defaultNameMapper.configFieldColumnName("orderLogisticsNum","ORDER_LOGISTICS_NUM");
 defaultNameMapper.configFieldColumnName("orderLogisticsTime","ORDER_LOGISTICS_TIME");
 defaultNameMapper.configFieldColumnName("orderReceiver","ORDER_RECEIVER");
 defaultNameMapper.configFieldColumnName("orderReceiverPhone","ORDER_RECEIVER_PHONE");
 defaultNameMapper.configFieldColumnName("addressProvince","ADDRESS_PROVINCE");
 defaultNameMapper.configFieldColumnName("addressCity","ADDRESS_CITY");
 defaultNameMapper.configFieldColumnName("addressArea","ADDRESS_AREA");
 defaultNameMapper.configFieldColumnName("addressInfo","ADDRESS_INFO");
 defaultNameMapper.configFieldColumnName("orderFinishTime","ORDER_FINISH_TIME");
 defaultNameMapper.configFieldColumnName("orderRemark","ORDER_REMARK");
 defaultNameMapper.configFieldColumnName("versionNum","VERSION_NUM");
 defaultNameMapper.configFieldColumnName("flagDel","FLAG_DEL");
 defaultNameMapper.configFieldColumnName("createTime","CREATE_TIME");
 defaultNameMapper.configFieldColumnName("createUser","CREATE_USER");
 defaultNameMapper.configFieldColumnName("modifyUser","MODIFY_USER");
 defaultNameMapper.configFieldColumnName("modifyDate","MODIFY_DATE");
return defaultNameMapper;
}
}
