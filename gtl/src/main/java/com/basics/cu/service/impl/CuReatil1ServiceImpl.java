package com.basics.cu.service.impl;

import com.basics.common.*;
import com.basics.cu.dao.CuReatil1Dao;
import com.basics.cu.entity.*;
import com.basics.cu.service.CuReatil1Service;
import com.basics.support.*;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CuReatil1ServiceImpl extends BaseApiService implements CuReatil1Service {
    @Autowired
    private CuReatil1Dao cuReatil1Dao;

    @Override
    public List<CuReatil1> searchByCustomerId(String customerId) {
        List<CuReatil2> list2  = cuReatil2Dao.query(new QueryFilterBuilder().put("customerId", customerId).build());
        List<CuReatil3> list3 = cuReatil3Dao.query(new QueryFilterBuilder().put("customerId", customerId).build());
        List<CuReatil1> list = cuReatil1Dao.query(new QueryFilterBuilder().put("customerId", customerId).build());
        Integer count2 = list2.size();
        Integer count3 = list3.size();
        JSONArray json2 = JSONArray.fromObject(list2);
        JSONArray json3 = JSONArray.fromObject(list3);
        for (CuReatil1 cuReatil1 : list) {
            cuReatil1.setCount2(count2);
            cuReatil1.setCount3(count3);
            cuReatil1.setList2(json2.toString());
            cuReatil1.setList3(json3.toString());
        }
        return list;
    }

    @Override
    public CuReatil1 searchReatilandIncome(String customerId) {
        CuReatil1 cuReatil1 = cuReatil1Dao.queryOne(new QueryFilterBuilder().put("customerId", customerId).build());
        List<CuReatil2> list2  = cuReatil2Dao.query(new QueryFilterBuilder().put("customerId", customerId).build());
        List<CuReatil3> list3 = cuReatil3Dao.query(new QueryFilterBuilder().put("customerId", customerId).build());
        int count = list2.size() + list3.size();
        for (CuReatil2 cuReatil2 : list2) {
            CuReatil3 c = new CuReatil3();
            c.setImage(cuReatil2.getImage());
            list3.add(c);
        }
        JSONArray json3 = JSONArray.fromObject(list3);
        cuReatil1.setList2(json3.toString());
        cuReatil1.setCount2(count);
        cuReatil1.setMoney(cuReatil1.getMoney().add(cuReatil1.getIndirectMoney()));
        return cuReatil1;
    }
}
