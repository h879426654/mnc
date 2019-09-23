package com.basics.cu.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.basics.cu.entity.CuCustomerReferee;
import com.basics.support.GenericService;
import com.basics.support.ItemResultSupport;

public interface CuCustomerRefereeService extends GenericService<CuCustomerReferee> {

    ItemResultSupport<List<JSONObject>> doZTree(String customerNumber);
}
