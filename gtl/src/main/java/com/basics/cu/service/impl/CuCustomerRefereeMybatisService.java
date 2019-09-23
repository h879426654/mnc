package com.basics.cu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.basics.cu.dao.CuCustomerRefereeDao;
import com.basics.cu.entity.CuCustomerReferee;
import com.basics.cu.service.CuCustomerRefereeService;
import com.basics.support.GenericMybatisService;
import com.basics.support.ItemResultSupport;
import com.basics.support.QueryFilterBuilder;

@Service
@Transactional
public class CuCustomerRefereeMybatisService extends GenericMybatisService<CuCustomerReferee> implements CuCustomerRefereeService {


    @Autowired
    private CuCustomerRefereeDao cuCustomerRecommendDao;

    @Override
    public ItemResultSupport<List<JSONObject>> doZTree(String customerPhone) {
        ItemResultSupport<List<JSONObject>> result = new ItemResultSupport<>();
        List<CuCustomerReferee> recommends;
        if (StringUtils.isBlank(customerPhone)) {
            recommends = cuCustomerRecommendDao.queryExtend(new QueryFilterBuilder().put("refereeId", "0").build(),"queryZtree");
        } else {
            QueryFilterBuilder builder = new QueryFilterBuilder();
            builder.put("customerPhone", customerPhone);
            recommends = cuCustomerRecommendDao.queryExtend(builder.build(),"queryZtree");
        }
        if (CollectionUtils.isNotEmpty(recommends)) {
            Map<String, Object> params = new HashMap<>();
            List<JSONObject> ztree = new ArrayList<>(recommends.size());
            JSONObject node;
            long count;
            for (CuCustomerReferee recommend : recommends) {
                node = new JSONObject();
                params.put("refereeId", recommend.getId());
                count = cuCustomerRecommendDao.count(params);
                node.put("isParent", count > 0);
                node.put("id", recommend.getId());
                node.put("customerPhone", recommend.getCustomerPhone());
                node.put("name", "[" + recommend.getCustomerPhone() + "] [" + recommend.getRealName() + "] [" + recommend.getCustomerName() + "]");
                ztree.add(node);
            }
            result.setItem(ztree);
        }
        return result;
    }
}
