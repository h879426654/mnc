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
import com.basics.support.PaginationSupport;
import com.basics.support.QueryFilterBuilder;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.Int;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CuCustomerCollectMybatisServiceImpl extends BaseApiService implements CuCustomerCollectService {

    @Override
        public List<CuCustomerCollect> searchCollect(String token, Integer page, Integer rows) {
        String customerId = this.getCuCustomerInfo(token);
        if (null != customerId) {
            List<CuCustomerCollect> list = cuCustomerCollectDao.query(new QueryFilterBuilder().put("customerId", customerId).put("state","1").put("page", (page-1)*10).put("rows", rows).build());
            for (CuCustomerCollect cuCustomerCollect:list) {
                MallShopAdvert mallShopAdvert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("id", cuCustomerCollect.getShopId()).build());
                cuCustomerCollect.setLatitude(mallShopAdvert.getAdvertLatitude());
                cuCustomerCollect.setLongitude(mallShopAdvert.getAdvertLongitude());
                cuCustomerCollect.setImage(mallShopAdvert.getAdvertImage());
                cuCustomerCollect.setAddress(mallShopAdvert.getAdvertAddress());
                cuCustomerCollect.setShopName(mallShopAdvert.getAdvertName());
                List<CuConsume> list1 = cuConsumeDao.query(new QueryFilterBuilder().put("shopId", cuCustomerCollect.getShopId()).build());
                cuCustomerCollect.setCount(list1.size());
            }
            return list;
        }
        return null;
    }

    @Override
    public String insertCollect(CollectRequest collectRequest) {
        try {
            Map<String, Object> map = new HashMap<>();
            String customerId = this.getCuCustomerInfo(collectRequest.getToken());
            map.put("shopId", collectRequest.getShopId());
            map.put("customerId", customerId);
            CuCustomerCollect cuCustomerCollect1 =cuCustomerCollectDao.queryOne(new QueryFilterBuilder().putAll(map).build());
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
            cuCustomerCollect.setCustomerId(customerId);
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
    public String searchMy(String token) {
        String customerId = this.getCuCustomerInfo(token);
        if (null != customerId) {
            CuCustomerInfo cuCustomerInfo = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("customerId", customerId).build());
            MallShopAdvert mallShopAdvert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("customerId", cuCustomerInfo.getId()).build());
            CuHttpUrl cuHttpUrl = new CuHttpUrl();
            cuHttpUrl.setImage(cuCustomerInfo.getCustomerHead());
            cuHttpUrl.setUserName(cuCustomerInfo.getCustomerName());
            cuHttpUrl.setMncCoin(cuCustomerInfo.getMncCoin());
            cuHttpUrl.setMp(cuCustomerInfo.getMp());
            List<CuHttpUrl> cuHttpUrls = cuHttpUrlDao.query(new QueryFilterBuilder().put("token", token).build());
            List<CuCustomerCollect> cuCustomerCollects = cuCustomerCollectDao.query(new QueryFilterBuilder().put("customerId",cuCustomerInfo.getId()).put("state","1").build());
            cuHttpUrl.setVermicelli(cuCustomerCollects.size());
            cuHttpUrl.setShopId(mallShopAdvert.getId());
            JSONArray jsons = JSONArray.fromObject(cuHttpUrls);
            cuHttpUrl.setHttpList(jsons.toString());
            JSONObject json = (JSONObject) JSONObject.toJSON(cuHttpUrl);
            return json.toString();
        }
        return "查询我的页面数据失败";
    }

    @Override
    public String searchConConsume(String state, String token, String type) {
        String customerId = this.getCuCustomerInfo(token);
        if (null != customerId) {
            MallShopAdvert mallShopAdvert = null;
            MallShopAdvert mallShopAdvert1 = null;
            List<CuConsume> cuConsumes = null;
            if ("1".equals(type)) {
                cuConsumes = cuConsumeDao.query(new QueryFilterBuilder().put("customerId", customerId).put("state",state).orderBy("createTime desc").build());
            } else if ("2".equals(type)) {
                mallShopAdvert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("customerId", customerId).build());
                cuConsumes = cuConsumeDao.query(new QueryFilterBuilder().put("shopId",mallShopAdvert.getId()).put("state",state).orderBy("createTime desc").build());
            }

            for (CuConsume cuConsume:cuConsumes) {
                if ("1".equals(type)) {
                    mallShopAdvert1 = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("id", cuConsume.getShopId()).build());
                    cuConsume.setImage(mallShopAdvert1.getAdvertImage());
                } else if ("2".equals(type)) {
                    cuConsume.setImage(mallShopAdvert.getAdvertImage());
                }
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
    public String updateConConsume(String id, String state, String mp) {
        try {
            CuConsume cuConsume = cuConsumeDao.queryOne(new QueryFilterBuilder().put("id",id).build());
            cuConsume.setState(state);
            cuConsumeDao.update(cuConsume);
            if ("1".equals(state)) {
                this.returnMp(id, mp);
            }
            return "成功";
        } catch (Exception e) {
            return "失败";
        }
    }

    @Override
    public String insertHistory(HistoryRequest historyRequest) {
        try {
            String customerId = this.getCuCustomerInfo(historyRequest.getToken());
            CuHistory cuHistory1 = cuHistoryDao.queryOne(new QueryFilterBuilder().put("customerId", customerId).put("shopId", historyRequest.getShopId()).build());
            if (null != cuHistory1) {
                cuHistoryDao.update(cuHistory1);
                return "成功";
            }
            List<CuHistory> cuHistorys = cuHistoryDao.query(new QueryFilterBuilder().put("customerId", customerId).orderBy("create_time desc").build());
            if (cuHistorys.size() > 20) {
                cuHistoryDao.delete(cuHistorys.get(0).getId());
            }
            CuHistory cuHistory = new CuHistory();
            cuHistory.setShopId(historyRequest.getShopId());
            cuHistory.setShopName(historyRequest.getShopName());
            cuHistory.setAddress(historyRequest.getAddress());
            cuHistory.setCustomerId(customerId);
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
    public String searchHistory(String token) {
        try {
            String customerId = this.getCuCustomerInfo(token);
            if (null != customerId) {
                List<CuHistory> list = cuHistoryDao.query(new QueryFilterBuilder().put("customerId", customerId).orderBy("create_time desc").build());
                for (CuHistory cuHistory:list) {
                    List<CuConsume> cuConsumes = cuConsumeDao.query(new QueryFilterBuilder().put("shopId", cuHistory.getShopId()).build());
                    cuHistory.setCount(cuConsumes.size());
                }
                JSONArray json = JSONArray.fromObject(list);
                return json.toString();
            }
            return "token有误";
        } catch (Exception e) {
            return "查询历史失败";
        }
    }

    @Override
    public String searchIsCollect(String token, String shopId) {
        String customerId = this.getCuCustomerInfo(token);
        if (null != customerId) {
            CuCustomerCollect cuCustomerCollect = cuCustomerCollectDao.queryOne(new QueryFilterBuilder().put("customerId", customerId).put("shopId",shopId).put("state","1").build());
            if (null != cuCustomerCollect) {
                return "1";
            } else {
                return "0";
            }
        }
        return "失败";
    }

    @Override
    public String getImageAndName(String shopId, String token) {
        String customerId = this.getCuCustomerInfo(token);
        if (null != customerId) {
            CuCustomerInfo cuCustomerInfo = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("customerId",customerId).build());
            MallShopAdvert mallShopAdvert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("id", shopId).build());
            mallShopAdvert.setCustomerPhone(cuCustomerInfo.getCustomerPhone());
            JSONObject json = (JSONObject) JSONObject.toJSON(mallShopAdvert);
            return json.toString();
        }
        return "";
    }

    @Override
    public String insertDiscuss(String token, String shopId, String remark) {
        String customerId = this.getCuCustomerInfo(token);
        if (null != customerId) {
            try {
                CuDiscuss cuDiscuss = new CuDiscuss();
                cuDiscuss.setCustomerId(customerId);
                cuDiscuss.setShopId(shopId);
                cuDiscuss.setRemark(remark);
                cuDiscussDao.insert(cuDiscuss);
                return "成功";
            } catch (Exception e) {
                return "失败";
            }
        }
        return "token有误";
    }

    @Override
    public String searchDiscuss(String shopId, Integer pageNum, Integer pageSize) {
        try {
            if (null == pageNum && null == pageSize) {
                pageNum = 1;
                pageSize = 10;
            }
            Map params = new HashMap();
            params.put("shopId", shopId);
            params.put("state", "0");
            params.put("pagey", (pageNum-1)*10);
            params.put("pages", pageSize);
            CuDiscuss cD = new CuDiscuss();
            List<CuDiscuss> cuDiscusses = cuDiscussDao.query(new QueryFilterBuilder().putAll(params).build());
            for (CuDiscuss cuDiscuss : cuDiscusses) {
                CuCustomerInfo cuCustomerInfo = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("customerId", cuDiscuss.getCustomerId()).build());
                cuDiscuss.setImage(cuCustomerInfo.getCustomerHead());
                cuDiscuss.setName(cuCustomerInfo.getCustomerName());
            }
            JSONArray json = JSONArray.fromObject(cuDiscusses);
            Long totalCount = cuDiscussDao.count(params);
            cD.setTotal(totalCount);
            cD.setList(json.toString());
            JSONObject json1 = (JSONObject) JSONObject.toJSON(cD);
            return json1.toString();
        } catch (Exception e) {
            return "查询异常";
        }
    }

    /**
     * 返还积分
     * @param id
     * @param mp
     * @return
     */
    private String returnMp(String id, String mp) {
        try {
            CuConsume cuConsume = cuConsumeDao.queryOne(new QueryFilterBuilder().put("id", id).build());
            CuCustomerInfo cuCustomerInfo = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("id", cuConsume.getCustomerId()).build());
            cuCustomerInfo.setMp(cuCustomerInfo.getMp().add(new BigDecimal(mp)));
            cuCustomerInfoDao.update(cuCustomerInfo);
            return "成功";
        } catch (Exception e) {
            return "失败";
        }

    }

    private String getCuCustomerInfo(String token) {
        String customerId = null;
        if (!token.isEmpty()) {
            AppToken appToken = appTokenDao.queryOne(new QueryFilterBuilder().put("id", token).build());
            return appToken.getUserId();
        }
        return null;
    }
}
