package com.basics.cu.service;

import com.basics.cu.entity.CuReatil1;

import java.util.List;

public interface CuReatil1Service {
    List<CuReatil1> searchByCustomerId(String customerId);

    CuReatil1 searchReatilandIncome(String customerId);
}
