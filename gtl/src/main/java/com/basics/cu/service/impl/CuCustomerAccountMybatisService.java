package com.basics.cu.service.impl;

import org.springframework.stereotype.Service;

import com.basics.cu.entity.CuCustomerAccount;
import com.basics.cu.service.CuCustomerAccountService;
import com.basics.support.GenericMybatisService;

@Service
public class CuCustomerAccountMybatisService extends GenericMybatisService<CuCustomerAccount> implements CuCustomerAccountService {

}
