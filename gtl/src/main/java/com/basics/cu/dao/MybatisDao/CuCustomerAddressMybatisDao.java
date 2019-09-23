package com.basics.cu.dao.MybatisDao;

import org.springframework.stereotype.Repository;

import com.basics.cu.dao.CuCustomerAddressDao;
import com.basics.cu.entity.CuCustomerAddress;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;

@Repository
public class CuCustomerAddressMybatisDao extends GenericMybatisDaoSupport<CuCustomerAddress> implements CuCustomerAddressDao {

    public CuCustomerAddressMybatisDao() {
        super();
        this.setPrimaryKeyFields("id");
    }

    @Override
    public INameMapper onBuildNameMapper() {
        DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
        defaultNameMapper.configFieldColumnName("id", "ADDRESS_ID");
        defaultNameMapper.configFieldColumnName("customerId", "CUSTOMER_ID");
        defaultNameMapper.configFieldColumnName("addressPhone", "ADDRESS_PHONE");
        defaultNameMapper.configFieldColumnName("addressName", "ADDRESS_NAME");
        defaultNameMapper.configFieldColumnName("addressProvince", "ADDRESS_PROVINCE");
        defaultNameMapper.configFieldColumnName("addressCity", "ADDRESS_CITY");
        defaultNameMapper.configFieldColumnName("addressArea", "ADDRESS_AREA");
        defaultNameMapper.configFieldColumnName("addressInfo", "ADDRESS_INFO");
        defaultNameMapper.configFieldColumnName("addressFlag", "ADDRESS_FLAG");
        defaultNameMapper.configFieldColumnName("flagDel", "FLAG_DEL");
        defaultNameMapper.configFieldColumnName("createTime", "CREATE_TIME");
        defaultNameMapper.configFieldColumnName("createUser", "CREATE_USER");
        defaultNameMapper.configFieldColumnName("modifyUser", "MODIFY_USER");
        defaultNameMapper.configFieldColumnName("modifyDate", "MODIFY_DATE");
        return defaultNameMapper;
    }
}
