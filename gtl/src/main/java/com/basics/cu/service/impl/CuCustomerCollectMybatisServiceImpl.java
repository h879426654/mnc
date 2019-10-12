package com.basics.cu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.basics.app.entity.AppToken;
import com.basics.common.BaseApiService;
import com.basics.cu.controller.request.CollectRequest;
import com.basics.cu.controller.request.ConsumeRequest;
import com.basics.cu.controller.request.HistoryRequest;
import com.basics.cu.entity.*;
import com.basics.cu.service.CuCustomerCollectService;
import com.basics.gty.entity.GtyWallet;
import com.basics.mall.dao.MallShopAdvertDao;
import com.basics.mall.entity.MallShopAdvert;
import com.basics.mall.entity.MallUser;
import com.basics.support.PaginationSupport;
import com.basics.support.QueryFilterBuilder;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.abi.datatypes.Int;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class CuCustomerCollectMybatisServiceImpl extends BaseApiService implements CuCustomerCollectService {

    @Override
        public List<CuCustomerCollect> searchCollect(String token, Integer page, Integer rows) {
        String customerId = this.getCuCustomerInfo(token);
        if (null != customerId) {
            List<CuCustomerCollect> list = cuCustomerCollectDao.query(new QueryFilterBuilder().put("customerId", customerId).put("state","1").put("page", (page-1)*10).put("rows", rows).build());
            for (CuCustomerCollect cuCustomerCollect:list) {
                MallShopAdvert mallShopAdvert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("id", cuCustomerCollect.getShopId()).put("applyStatus", "2").put("flagDel", "0").build());
                if (null != mallShopAdvert) {
                    cuCustomerCollect.setLatitude(mallShopAdvert.getAdvertLatitude());
                    cuCustomerCollect.setLongitude(mallShopAdvert.getAdvertLongitude());
                    cuCustomerCollect.setImage(mallShopAdvert.getAdvertImage());
                    cuCustomerCollect.setAddress(mallShopAdvert.getAdvertAddress());
                    cuCustomerCollect.setShopName(mallShopAdvert.getAdvertName());
                    cuCustomerCollect.setCount(mallShopAdvert.getAdvertSale());
                }
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
            CuState custate = new CuState();
            custate.setState("0");
            JSONObject json = (JSONObject) JSONObject.toJSON(custate);
            return json.toString();
        } catch (Exception e) {
            CuState custate = new CuState();
            custate.setState("1");
            JSONObject json = (JSONObject) JSONObject.toJSON(custate);
            return json.toString();
        }
    }

    @Override
    public String searchMy(String token) {
        String customerId = this.getCuCustomerInfo(token);
        if (null != customerId) {
            CuHttpUrl cuHttpUrl = new CuHttpUrl();
            CuCustomerInfo cuCustomerInfo = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("id", customerId).build());
            MallShopAdvert mallShopAdvert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("customerId", cuCustomerInfo.getId()).put("applyStatus", "2").put("flagDel", "0").build());
            if (null == mallShopAdvert) {
                //用户
                cuHttpUrl.setIsAdv("3");
            } else {
                if ("1".equals(mallShopAdvert.getApplyStatus())) {
                    //审核中
                    cuHttpUrl.setIsAdv("1");
                } else if ("2".equals(mallShopAdvert.getApplyStatus())) {
                    //通过
                    cuHttpUrl.setIsAdv("0");
                } else if ("3".equals(mallShopAdvert.getApplyStatus())) {
                    //下架
                    cuHttpUrl.setIsAdv("2");
                }
            }
            cuHttpUrl.setImage(cuCustomerInfo.getCustomerHead());
            cuHttpUrl.setUserName(cuCustomerInfo.getCustomerName());
            GtyWallet gtyWallet = gtyWalletDao.queryOne(new QueryFilterBuilder().put("userId", customerId).build());
            if (gtyWallet != null) {
                cuHttpUrl.setMncCoin(gtyWallet.getMncNum());
                cuHttpUrl.setMp(gtyWallet.getmTokenNum());
            }
            List<CuHttpUrl> cuHttpUrls = cuHttpUrlDao.query(new QueryFilterBuilder().put("token", token).build());
            if (mallShopAdvert != null) {
                List<CuCustomerCollect> cuCustomerCollects = cuCustomerCollectDao.query(new QueryFilterBuilder().put("shopId", mallShopAdvert.getId()).put("state","1").build());
                cuHttpUrl.setVermicelli(cuCustomerCollects.size());
            } else {
                cuHttpUrl.setVermicelli(0);
            }
            if (null != mallShopAdvert) {
                cuHttpUrl.setShopId(mallShopAdvert.getId());
            }
            JSONArray jsons = JSONArray.fromObject(cuHttpUrls);
            cuHttpUrl.setHttpList(jsons.toString());
            JSONObject json = (JSONObject) JSONObject.toJSON(cuHttpUrl);
            return json.toString();
        }
        return "查询我的页面数据失败";
    }

    @Override
    public String searchConConsume(String state, String token, String type, Integer pageNum, Integer pageSize) {
        try {
            String customerId = this.getCuCustomerInfo(token);
            if (null != customerId) {
                MallShopAdvert mallShopAdvert = null;
                MallShopAdvert mallShopAdvert1 = null;
                List<CuConsume> cuConsumes = null;
                if ("1".equals(type)) {
                    cuConsumes = cuConsumeDao.query(new QueryFilterBuilder().put("customerId", customerId).put("state",state).put("pageN", (pageNum-1)*10).put("pageS" ,pageSize).orderBy("createTime desc").build());
                } else if ("2".equals(type)) {
                    mallShopAdvert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("customerId", customerId).put("applyStatus", "2").put("flagDel", "0").build());
                    cuConsumes = cuConsumeDao.query(new QueryFilterBuilder().put("shopId",mallShopAdvert.getId()).put("state",state).put("pageN", (pageNum-1)*10).put("pageS" ,pageSize).orderBy("createTime desc").build());
                }

                for (CuConsume cuConsume:cuConsumes) {
                    if ("1".equals(type)) {
                        mallShopAdvert1 = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("id", cuConsume.getShopId()).put("applyStatus", "2").put("flagDel", "0").build());
                        if (null != mallShopAdvert) {
                            cuConsume.setImage(mallShopAdvert1.getAdvertImage());
                        }
                    } else if ("2".equals(type)) {
                        if (null != mallShopAdvert) {
                            cuConsume.setImage(mallShopAdvert.getAdvertImage());
                        }
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
        }catch (Exception e) {
            return "查询我的消费页面数据失败";
        }
        return "";
    }

    @Override
    public String insertConConsume(ConsumeRequest request) {
        try {
            MallShopAdvert mallShopAdvert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("id", request.getShopId()).put("applyStatus", "2").put("flagDel", "0").build());
            if (mallShopAdvert.getCustomerId().equals(request.getCustomerId())) {
                return "自己不能给自己记账";
            }
            CuConsume cuConsume = new CuConsume();
            cuConsume.setId(UUID.randomUUID().toString().replace("-",""));
            cuConsume.setShopId(request.getShopId());
            cuConsume.setPhone(request.getPhone());
            cuConsume.setShopName(mallShopAdvert.getAdvertName());
            cuConsume.setCustomerId(request.getCustomerId());
            cuConsume.setImage(mallShopAdvert.getAdvertImage());
            cuConsume.setMoney(new BigDecimal(request.getMoney()));
            cuConsumeDao.insert(cuConsume);
            return "成功";
        } catch (Exception e) {
            return "失败";
        }
    }

    @Override
    public String updateConConsume(String token, String id, String state, String mp) {
        try {
            String customerId = this.getCuCustomerInfo(token);
            GtyWallet gtyWallet = gtyWalletDao.queryOne(new QueryFilterBuilder().put("userId", customerId).build());
            BigDecimal rNum = gtyWallet.getRecordNum().multiply(new BigDecimal(0.5));
            if (new BigDecimal(mp).compareTo(rNum) > 0 ) {
                return "返还积分必须是记账奖励积分的一半以下";
            }
            CuConsume cuConsume = cuConsumeDao.queryOne(new QueryFilterBuilder().put("id",id).build());
            cuConsume.setState(state);
            cuConsume.setMtoken(new BigDecimal(mp));
            cuConsumeDao.update(cuConsume);
            if ("1".equals(state)) {
                this.returnMp(mp, cuConsume);
                MallShopAdvert mallShopAdvert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("id", cuConsume.getShopId()).build());
                mallShopAdvert.setAdvertSale(mallShopAdvert.getAdvertSale()+1);
                mallShopAdvertDao.update(mallShopAdvert);
                GtyWallet gtyWallet1 = gtyWalletDao.queryOne(new QueryFilterBuilder().put("userId", mallShopAdvert.getCustomerId()).build());
                gtyWallet1.setmTokenNum(gtyWallet1.getmTokenNum().add(new BigDecimal(mp)));
                BigDecimal recordNum = gtyWallet1.getRecordNum().subtract(new BigDecimal(mp)).subtract(new BigDecimal(mp));
                gtyWallet1.setRecordNum(recordNum);
                gtyWalletDao.update(gtyWallet1);
            }
            CuState cuState = new CuState();
            cuState.setState("0");
            JSONObject json = (JSONObject) JSONObject.toJSON(cuState);
            return json.toString();
        } catch (Exception e) {
            throw new RuntimeException();
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
                cuHistoryDao.delete(cuHistorys.get(cuHistorys.size()).getId());
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
                Map map = new HashMap();
                for (CuHistory cuHistory:list) {
                    map.put("shopId", cuHistory.getShopId());
                    map.put("state", "1");
                    Long count = cuConsumeDao.count(map);
                    cuHistory.setCount(count.intValue());
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
        try {

            String customerId = this.getCuCustomerInfo(token);
            if (null != customerId) {
                CuCustomerInfo cuCustomerInfo = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("id",customerId).build());
                MallShopAdvert mallShopAdvert = mallShopAdvertDao.queryOne(new QueryFilterBuilder().put("id", shopId).put("applyStatus", "2").put("flagDel", "0").build());
                mallShopAdvert.setCustomerPhone(cuCustomerInfo.getCustomerPhone());
                mallShopAdvert.setCustomerId(customerId);
                JSONObject json = (JSONObject) JSONObject.toJSON(mallShopAdvert);
                return json.toString();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public String insertDiscuss(String token, String shopId, String remark, String id) {
        String customerId = this.getCuCustomerInfo(token);
        if (null != customerId) {
            try {
                CuDiscuss cuDiscuss = new CuDiscuss();
                cuDiscuss.setCustomerId(customerId);
                cuDiscuss.setShopId(shopId);
                cuDiscuss.setRemark(remark);
                cuDiscussDao.insert(cuDiscuss);
                CuConsume cuConsume = cuConsumeDao.queryOne(new QueryFilterBuilder().put("id", id).build());
                cuConsume.setAppraise("1");
                cuConsumeDao.update(cuConsume);
                CuState cuState = new CuState();
                cuState.setState("0");
                JSONObject json = (JSONObject)JSONObject.toJSON(cuState);
                return json.toString();
            } catch (Exception e) {
                CuState cuState = new CuState();
                cuState.setState("1");
                JSONObject json = (JSONObject)JSONObject.toJSON(cuState);
                return json.toString();
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
                CuCustomerInfo cuCustomerInfo = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("id", cuDiscuss.getCustomerId()).build());
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

    @Override
    public String getPhone(String personToken) {
        String customerId = this.getCuCustomerInfo(personToken);
        String phone = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("id", customerId).build()).getCustomerPhone();
        return phone;
    }

    @Override
    public String searchPicture() {
        List<CuPicture> list = cuPictureDao.query(new QueryFilterBuilder().put("delFlag", "0").build());
        JSONArray json = JSONArray.fromObject(list);
        return json.toString();
    }

    @Override
    public String searchToken(String token) {
        AppToken appToken = appTokenDao.queryOne(new QueryFilterBuilder().put("id", token).build());
        CuState cuState = new CuState();
        if (null != appToken) {
            cuState.setState("0");
            JSONObject json = (JSONObject) JSONObject.toJSON(cuState);
            return json.toString();
        }
        cuState.setState("1");
        JSONObject json = (JSONObject) JSONObject.toJSON(cuState);
        return json.toString();
    }

    @Override
    public String searchImageAndName(String token) {
        String customerId = this.getCuCustomerInfo(token);
        if (null != customerId && !customerId.isEmpty()) {
            CuCustomerInfo cuCustomerInfo = cuCustomerInfoDao.queryOne(new QueryFilterBuilder().put("id", customerId).build());
            JSONObject json = (JSONObject) JSONObject.toJSON(cuCustomerInfo);
            return json.toString();
        }
        return null;
    }

    @Override
    public void addMp(BigDecimal mp, String customerId) {
        BigDecimal zpzo = new BigDecimal(0.01);
        int i = 0;
        //判断一共有多少人吃利息
        while(true) {
            CuReatil2 cu2 = cuReatil2Dao.queryOne(new QueryFilterBuilder().put("customerIdSecond", customerId).build());
            if (null == cu2) {
                break;
            }
            if (i==21) {
                break;
            }
            customerId = cu2.getCustomerId();
            i++;
        }
        CuLogs cuLogs = new CuLogs();
        //计算并更新
        for (int j=i; j>0; j--) {
            CuReatil2 cu2 = cuReatil2Dao.queryOne(new QueryFilterBuilder().put("customerId", customerId).build());
            CuReatilMoney cuReatilMoney = cuReatilMoneyDao.queryOne(new QueryFilterBuilder().put("id", j).build());
            BigDecimal rate = cuReatilMoney.getMoney().multiply(zpzo);
            BigDecimal mToken = mp.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
            CuReatil1 cureatil1 = cuReatil1Dao.queryOne(new QueryFilterBuilder().put("customerId", customerId).build());
            if (j == 1) {
                cureatil1.setMoney(cureatil1.getMoney().add(mToken));
                cuLogs.setRemark("您的某下级记账直接得到mp:"+mToken);
            } else {
                cureatil1.setIndirectMoney(cureatil1.getIndirectMoney().add(mToken));
                cuLogs.setRemark("您的某下级记账间接得到mp:"+mToken);
            }
            cuLogs.setCustomerId(customerId);
            cuLogs.setType("1");
            cuLogs.setMp(mToken);
            cuLogsDao.insert(cuLogs);
            cuReatil1Dao.update(cureatil1);
            this.updateWalletInfo(customerId, mToken);
            if (cu2 == null) {
                break;
            }
            customerId = cu2.getCustomerIdSecond();
        }
    }

    @Override
    public String searchAdvUser(String token) {
        String customerId = this.getCuCustomerInfo(token);
        MallUser mallUser = mallUserDao.queryOne(new QueryFilterBuilder().put("customerId", customerId).build());
        JSONObject json = (JSONObject) JSONObject.toJSON(mallUser);
        return json.toString();
    }

    /**
     * 返还积分
     * @param cuConsume
     * @param mp
     * @return
     */
    private void returnMp(String mp, CuConsume cuConsume) {
        this.updateWalletInfo(cuConsume.getCustomerId(), new BigDecimal(mp));
        this.addMp(new BigDecimal(mp), cuConsume.getCustomerId());
        CuLogs cuLogs = new CuLogs();
        cuLogs.setCustomerId(cuConsume.getCustomerId());
        cuLogs.setShopId(cuConsume.getShopId());
        cuLogs.setType("1");
        cuLogs.setMoney(cuConsume.getMoney());
        cuLogs.setMp(new BigDecimal(mp));
        cuLogs.setRemark("记账:"+cuConsume.getMoney()+"返还mp:"+mp);
        cuLogsDao.insert(cuLogs);
    }

    private void updateWalletInfo(String customerId, BigDecimal mToken) {
        GtyWallet gtyWallet = gtyWalletDao.queryOne(new QueryFilterBuilder().put("userId", customerId).build());
        gtyWallet.setmTokenNum(gtyWallet.getmTokenNum().add(mToken));
        gtyWalletDao.update(gtyWallet);
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
