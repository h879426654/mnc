package com.basics.cu.service;

import com.basics.common.DataPageComonResponse;
import com.basics.common.DataResponse;
import com.basics.cu.entity.CuReatil1;

import java.util.List;

public interface CuReatil1Service {
    DataPageComonResponse<CuReatil1> searchReatil(String customerId, Integer pageNum, Integer pageSize);

    CuReatil1 searchReatilandIncome(String customerId);
}
