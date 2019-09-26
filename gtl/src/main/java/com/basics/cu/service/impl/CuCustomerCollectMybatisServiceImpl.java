package com.basics.cu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.basics.app.entity.AppToken;
import com.basics.common.BaseApiService;
import com.basics.cu.controller.request.CollectRequest;
import com.basics.cu.controller.request.ConsumeRequest;
import com.basics.cu.controller.request.HistoryRequest;
import com.basics.cu.entity.*;
import com.basics.cu.service.CuCustomerCollectService;
import com.basics.mall.entity.MallShopAdvert;
import com.basics.support.QueryFilterBuilder;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class CuCustomerCollectMybatisServiceImpl extends BaseApiService implements CuCustomerCollectService {

    @Override
        public List<CuCustomerCollect> searchCollect(String phone, String token) {
        CuCustomerInfo cuCustomerInfo = this.getCuCustomerInfo(phone, token);
        List<CuCustomerCollect> list = cuCustomerCollectDao.query(new QueryFilterBuilder().put("customerId",cuCustomerInfo.getId()).put("state","1").build());
        for (CuCustomerCollect cuCustomerCollect:list) {
            MallShopAdvert mallShopAdvert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("id", cuCustomerCollect.getShopId()).build());
            cuCustomerCollect.setLatitude(mallShopAdvert.getAdvertLatitude());
            cuCustomerCollect.setLongitude(mallShopAdvert.getAdvertLongitude());
            cuCustomerCollect.setImage(mallShopAdvert.getAdvertImage());
            cuCustomerCollect.setAddress(mallShopAdvert.getAdvertAddress());
            List<CuConsume> list1 = cuConsumeDao.query(new QueryFilterBuilder().put("shopId", cuCustomerCollect.getShopId()).build());
            cuCustomerCollect.setCount(list1.size());
        }
        return list;
    }

    @Override
    public String insertCollect(CollectRequest collectRequest) {
        try {
            CuCustomerInfo cuCustomerInfo = this.getCuCustomerInfo(collectRequest.getPhone(), collectRequest.getToken());
            CuCustomerCollect cuCustomerCollect1 =cuCustomerCollectDao.queryOne(new QueryFilterBuilder().put("customerId", cuCustomerInfo.getId()).put("showId", collectRequest.getShopId()).build());
            if (null != cuCustomerCollect1) {
                cuCustomerCollect1.setState(collectRequest.getState());
                cuCustomerCollectDao.update(cuCustomerCollect1);
                if ("1".equals(collectRequest.getState())) {
                    return "收藏成功";
                }
                return "取消成功";
            }
            CuCustomerCollect cuCustomerCollect = new CuCustomerCollect();
            cuCustomerCollect.setId(UUID.randomUUID().toString().replace("-",""));
            cuCustomerCollect.setShopId(collectRequest.getShopId());
            cuCustomerCollect.setShopName(collectRequest.getShopName());
            cuCustomerCollect.setCustomerId(cuCustomerInfo.getId());
            cuCustomerCollectDao.insert(cuCustomerCollect);
            return "收藏成功";

        } catch (Exception e) {
            return "收藏失败";
        }

    }

    @Override
    public String updateCollect(String id, String state) {
        try {
            CuCustomerCollect cuCustomerCollect = cuCustomerCollectDao.queryOne(new QueryFilterBuilder().put("id",id).build());
            cuCustomerCollect.setState(state);
            cuCustomerCollectDao.update(cuCustomerCollect);
            return "取消成功";
        } catch (Exception e) {
            return "取消失败";
        }
    }

    @Override
    public String searchMy(String phone, String token) {
        CuCustomerInfo cuCustomerInfo = this.getCuCustomerInfo(phone, token);
        if (null != cuCustomerInfo) {
            CuHttpUrl cuHttpUrl = new CuHttpUrl();
            cuHttpUrl.setImage(cuCustomerInfo.getCustomerHead());
            cuHttpUrl.setUserName(cuCustomerInfo.getCustomerName());
            cuHttpUrl.setMncCoin(cuCustomerInfo.getMncCoin());
            cuHttpUrl.setMp(cuCustomerInfo.getMp());
            List<CuHttpUrl> cuHttpUrls = cuHttpUrlDao.query(new QueryFilterBuilder().put("phone",phone).build());
            List<CuCustomerCollect> cuCustomerCollects = cuCustomerCollectDao.query(new QueryFilterBuilder().put("customerId",cuCustomerInfo.getId()).put("state","1").build());
            cuHttpUrl.setVermicelli(cuCustomerCollects.size());
            JSONArray jsons = JSONArray.fromObject(cuHttpUrls);
            cuHttpUrl.setHttpList(jsons.toString());
            JSONObject json = (JSONObject) JSONObject.toJSON(cuHttpUrl);
            return json.toString();
        }
        return "查询我的页面数据失败";
    }

    @Override
    public String searchConConsume(String phone, String state, String token, String type) {
        CuCustomerInfo cuCustomerInfo = this.getCuCustomerInfo(phone, token);
        if (null != cuCustomerInfo) {
            List<CuConsume> cuConsumes = null;
            if ("1".equals(type)) {
                cuConsumes = cuConsumeDao.query(new QueryFilterBuilder().put("customerId",cuCustomerInfo.getId()).put("state",state).orderBy("createTime desc").build());
            } else if ("2".equals(type)) {
                MallShopAdvert mallShopAdvert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("customerId",cuCustomerInfo.getId()).build());
                cuConsumes = cuConsumeDao.query(new QueryFilterBuilder().put("shopId",mallShopAdvert.getId()).put("state",state).orderBy("createTime desc").build());
            }

            for (CuConsume cuConsume:cuConsumes) {
                if ("0".equals(cuConsume.getState())) {
                    cuConsume.setStateText("待记账");
                } else if ("1".equals(cuConsume.getState())) {
                    cuConsume.setStateText("已记账");
                } else if ("2".equals(cuConsume.getState())) {
                    cuConsume.setStateText("已取消");
                }
            }
            JSONArray jsons = JSONArray.fromObject(cuConsumes);
            return jsons.toString();
        }
        return "查询我的消费页面数据失败";
    }

    @Override
    public String insertConConsume(ConsumeRequest request) {
        try {
            MallShopAdvert mallShopAdvert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("id", request.getShopId()).build());
            CuConsume cuConsume = new CuConsume();
            cuConsume.setShopId(request.getShopId());
            cuConsume.setShopName(mallShopAdvert.getAdvertName());
            cuConsume.setCustomerId(request.getCustomerId());
            cuConsume.setImage(mallShopAdvert.getAdvertImage());
            cuConsume.setMoney(new BigDecimal(request.getMoney()));
            cuConsume.setPhone(request.getPhone());
            cuConsumeDao.insert(cuConsume);
            return "成功";
        } catch (Exception e) {
            return "失败";
        }
    }

    @Override
    public String updateConConsume(String id, String state) {
        try {
            CuConsume cuConsume = cuConsumeDao.queryOne(new QueryFilterBuilder().put("id",id).build());
            cuConsume.setState(state);
            cuConsumeDao.update(cuConsume);
            return "成功";
        } catch (Exception e) {
            return "失败";
        }
    }

    @Override
    public String insertHistory(HistoryRequest historyRequest) {
        try {
            CuHistory cuHistory1 = cuHistoryDao.queryOne(new QueryFilterBuilder().put("customerId", historyRequest.getCustomerId()).put("shopId", historyRequest.getShopId()).build());
            if (null != cuHistory1) {
                cuHistoryDao.update(cuHistory1);
                return "成功";
            }
                CuHistory cuHistory = new CuHistory();
            cuHistory.setShopId(historyRequest.getShopId());
            cuHistory.setShopName(historyRequest.getShopName());
            cuHistory.setAddress(historyRequest.getAddress());
            cuHistory.setCustomerId(historyRequest.getCustomerId());
            cuHistory.setImage(historyRequest.getImage());
            cuHistory.setLongitude(historyRequest.getLongitude());
            cuHistory.setLatitude(historyRequest.getLatitude());
            cuHistoryDao.insert(cuHistory);
            return "成功";
        } catch (Exception e) {
            return "添加到浏览历史失败";
        }
    }

    @Override
    public String searchHistory(String phone, String token) {
        try {
            CuCustomerInfo cuCustomerInfo = this.getCuCustomerInfo(phone, token);
            List<CuHistory> list = cuHistoryDao.query(new QueryFilterBuilder().put("customerId",cuCustomerInfo.getId()).orderBy("create_time desc").build());
            for (CuHistory cuHistory:list) {
                List<CuConsume> cuConsumes = cuConsumeDao.query(new QueryFilterBuilder().put("shopId", cuHistory.getShopId()).build());
                cuHistory.setCount(cuConsumes.size());
            }
            JSONArray json = JSONArray.fromObject(list);
            return json.toString();
        } catch (Exception e) {
            return "查询历史失败";
        }
    }

    @Override
    public String searchIsCollect(String phone, String token, String shopId) {
        CuCustomerInfo cuCustomerInfo = this.getCuCustomerInfo(phone, token);
        CuCustomerCollect cuCustomerCollect = cuCustomerCollectDao.queryOne(new QueryFilterBuilder().put("cusetomerId", cuCustomerInfo.getId()).put("shopId",shopId).put("state","1").build());
        if (null != cuCustomerCollect) {
            return "1";
        } else {
            return "0";
        }
    }

    private CuCustomerInfo getCuCustomerInfo(String phone, String token) {
        CuCustomerInfo cuCustomerInfo = null;
        if (!phone.isEmpty()) {
            cuCustomerInfo = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("customerPhone",phone).build());
        } else if (!token.isEmpty()) {
            AppToken appToken = appTokenDao.queryOne(new QueryFilterBuilder().put("id", token).build());
            cuCustomerInfo = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("customerId", appToken.getUserId()).build());
        }
        return cuCustomerInfo;
    }
}
