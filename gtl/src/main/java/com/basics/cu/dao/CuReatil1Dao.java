package com.basics.cu.dao;
import com.basics.cu.entity.CuAccountConvert;
import com.basics.cu.entity.CuReatil1;
import com.basics.support.GenericDao;

public interface CuReatil1Dao extends GenericDao<CuReatil1> {

    CuReatil1 searchMoneyAndIndirectMoney(String customerId);
}