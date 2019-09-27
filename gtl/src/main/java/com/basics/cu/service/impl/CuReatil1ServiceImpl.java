package com.basics.cu.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.basics.app.entity.AppToken;
import com.basics.common.*;
import com.basics.cu.dao.CuReatil1Dao;
import com.basics.cu.entity.*;
import com.basics.cu.service.CuReatil1Service;
import com.basics.support.*;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CuReatil1ServiceImpl extends BaseApiService implements CuReatil1Service {
    @Autowired
    private CuReatil1Dao cuReatil1Dao;

    @Override
    public DataPageComonResponse<CuReatil1> searchReatil(String token, Integer pageNum, Integer pageSize) {
        DataPageComonResponse<CuReatil1> response = new DataPageComonResponse<>();
        //查询用户的id
        AppToken appToken = appTokenDao.queryOne(new QueryFilterBuilder().put("id", token).build());
        if (null == appToken) {
            response.setMsg("没有查询到该用户");
            response.setStatus(2);
            return response;
        }
        String customerId = appToken.getUserId();
        //查询是否已经是1级
        CuReatil1 cuReatil1 = cuReatil1Dao.queryOne(new QueryFilterBuilder().put("customerId", customerId).build());
        if (null == cuReatil1) {
            response.setMsg("该用户没有下级");
            response.setStatus(2);
            return response;
        }

        //直接的下级id
        StringBuilder sbDirectIds = new StringBuilder();
        //间接的下级id
        StringBuilder sbIndirectIds =  new StringBuilder();

        //如果已经是1级了,查看直接代理了多少人
        List<CuReatil2> list = this.searchCuReatil2(customerId);
        for (CuReatil2 cuReatil2 :list) {
            //拼接直接下级的id
            sbDirectIds.append(",'").append(cuReatil2.getCustomerIdSecond()).append("'");
            //为了计算竖着超没超过22个人用的
            int vertical = 0;
            //为了记载下级的用户id用的
            String customerIds = "," + cuReatil2.getCustomerIdSecond();
            while(true) {
                //最大只能跟21个下级有关联
                if (vertical == 21) {
                    break;
                }
                String arr[] = customerIds.substring(1).split(",");
                customerIds = "";
                //为了计算横着超没超过21个人用的
                int horizontal = 0;
                //为了记载有没有下级
                int j = 0;
                for (String userId :arr) {
                    if (horizontal == 22) {
                        break;
                    }
                    //查询3级-22级的数据 没有中间没有查到说明没有下级了
                    List<CuReatil2> cuReatil2s = this.searchCuReatil2(userId);
                    for (CuReatil2 cureatil2 : cuReatil2s) {
                        j++;
                        //拼接间接用户id
                        sbIndirectIds.append(",'").append(cureatil2.getCustomerIdSecond()).append("'");
                        customerIds += "," + cureatil2.getCustomerIdSecond();
                    }
                    horizontal++;
                }
                //判断有没有下级了
                if (j == 0) {
                    break;
                }
                vertical++;
            }
        }
        //直接人数
        String directIds = sbDirectIds.toString().substring(1);
        Integer directCount = directIds.split(",").length;
        cuReatil1.setDirectCount(directCount);
        //间接人数
        String indirectids = sbIndirectIds.toString().substring(1);
        Integer indirectidCount = indirectids.split(",").length;
        cuReatil1.setIndirectidCount(indirectidCount);

        try {
            //通过id查询直接的用户
            PaginationSupport paginationSupport = this.searchByCustomerId(pageNum, pageSize, directIds);
            JSONArray json = JSONArray.fromObject(paginationSupport.getItems());
            cuReatil1.setDirectList(json.toString());
            //通过id查间接的用户
            PaginationSupport paginationSupport1 = this.searchByCustomerId(pageNum, pageSize, indirectids);
            JSONArray json1 = JSONArray.fromObject(paginationSupport1.getItems());
            cuReatil1.setIndirectList(json1.toString());

            JSONObject json2 = (JSONObject) JSONObject.toJSON(cuReatil1);
            response.setMsg(json2.toString());
            response.setStatus(1);
            return response;
        } catch (Exception e) {
            response.setMsg("异常");
            response.setStatus(2);
            return response;
        }
    }

    @Override
    public CuReatil1 searchReatilandIncome(String customerId) {
        return null;
    }

    private List<CuReatil2>  searchCuReatil2(String customerId) {
        return cuReatil2Dao.query(new QueryFilterBuilder().put("customerId", customerId).build());
    }

    private PaginationSupport searchByCustomerId(Integer pageNum, Integer pageSize, String param) {
        Map params = new HashMap();
        params.put("l", param);
        return cuCustomerInfoDao.queryPaginationExtend("searchByCustomerId", "count", params, pageNum , pageSize);
    }
}
