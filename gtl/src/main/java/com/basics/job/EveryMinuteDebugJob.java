package com.basics.job;

import java.math.BigDecimal;
import java.util.*;

import com.basics.common.BaseApiService;
import com.basics.common.Constant;
import com.basics.cu.controller.response.CustomerInfoResponse;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.gty.entity.GtyLimitWallet;
import com.basics.gty.entity.GtyWallet;
import com.basics.gty.entity.GtyWalletHistory;
import com.basics.gty.entity.TradeBean;
import com.basics.support.QueryFilter;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.auth.HttpClientUtils;
import com.basics.sys.entity.SysRule;
import com.basics.wallet.entity.WalletEntity;
import com.google.gson.Gson;
import com.qiniu.util.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.basics.support.LogUtils;
import com.basics.support.job.EveryMinuteJob;

@Component
public class EveryMinuteDebugJob extends BaseApiService implements EveryMinuteJob {

    @Override
    public void doJob() {
        LogUtils.performance.info("{} begin at {}", this.getClass().getName(), new Date());

        String s1 = "0.001";// 创业积分比率
        String s2 = "0.005";// mnc比率
        String s3 = "0.001";// mtoken比率
        List<GtyWallet> records = gtyWalletDao.query(new QueryFilterBuilder().build());
        QueryFilter filter = new QueryFilter();
        Map<String, Object> maps = new HashMap<>();
        maps.put("id", "1");
        filter.setParams(maps);
        GtyLimitWallet gtyLimitWallet = gtyWalletLimitDao.queryOne(filter);
        if(gtyLimitWallet.getLimitScoreReleasePoint().compareTo(BigDecimal.ZERO)==1){
            s1 = gtyLimitWallet.getLimitScoreReleasePoint()+"";
        }
        if(gtyLimitWallet.getLimitMncReleasePoint().compareTo(BigDecimal.ZERO)==1){
            s2 = gtyLimitWallet.getLimitMncReleasePoint()+"";
        }
        if(gtyLimitWallet.getLimitMtokenReleasePoint().compareTo(BigDecimal.ZERO)==1){
            s3 = gtyLimitWallet.getLimitMtokenReleasePoint()+"";
        }
        Map<String,String> maps1 = new HashMap<>();
        maps1.put("symbol","3");
        String tradeBean = HttpClientUtils.invokeGet("http://bitin.io:8090/api/v1/ticker",maps1);
        TradeBean tradeBean1= new Gson().fromJson(tradeBean,TradeBean.class);
        String price;
        if(tradeBean1!=null && tradeBean1.getData()!=null && !StringUtils.isNullOrEmpty(tradeBean1.getData().getLast())){
            if(tradeBean1.getData().getLast().equals("0")){
                price = "1";
            }else{
                price = tradeBean1.getData().getLast();
            }
        }else{
            price = "1";
        }
        BigDecimal bigDecimalPrice = new BigDecimal(price);
        if (CollectionUtils.isNotEmpty(records)) {
            for (GtyWallet record : records) {
                if(record.getWalletFrozen()==0){// 账号冻结
                    continue;
                }
                if(!record.getUserId().equals("0005efe2451b4cdfa6335ae5a7815be8")){
                    continue;
                }
                if (!StringUtils.isNullOrEmpty(record.getUserId())) {

                    BigDecimal mToken = record.getmTokenNum();//
                    BigDecimal mSuper = record.getSuperNum();//
                    BigDecimal mScore = record.getScoreNum();//
                    BigDecimal mMove = record.getMoveNum();//
                    BigDecimal mMnc = record.getMncNum();//

                    BigDecimal mRelaseMnc = record.getReleasedMnc();//

                    Map<String, Object> map = new HashMap<>();
                    map.put("id", record.getUserId());
                    CustomerInfoResponse data = cuCustomerInfoDao.getExtend(map, "queryCustomerInfo");

                    //  创业积分 千分之一（老MP+老MC的和）  释放到超级钱包
                    if(mScore.compareTo(BigDecimal.ZERO)==1){// 等于0不处理
                        if (gtyLimitWallet.getLimitUpScoreRelease().compareTo(mScore)==1) {// 上限大于score
                            BigDecimal bigDecimal = gtyLimitWallet.getLimitUpScoreRelease();
                            if (bigDecimal.compareTo(BigDecimal.ZERO) == 1) {// 有上限
                                if (mScore.compareTo(bigDecimal) >=0) {//大于上限
                                    mSuper = mSuper.add(bigDecimal.multiply(new BigDecimal(s1)).divide(bigDecimalPrice));
                                    mScore = mScore.subtract(bigDecimal.multiply(new BigDecimal(s1)).divide(bigDecimalPrice));
                                    addHistory(record.getUserId(),bigDecimal.multiply(new BigDecimal(s1)).divide(bigDecimalPrice)+"",8,"创业积分释放到超级钱包","+");
                                }else{
                                    mSuper = mSuper.add(mScore.multiply(new BigDecimal(s1)).divide(bigDecimalPrice));
                                    mScore = mScore.subtract(mScore.multiply(new BigDecimal(s1)).divide(bigDecimalPrice));
                                    addHistory(record.getUserId(),mScore.multiply(new BigDecimal(s1)).divide(bigDecimalPrice)+"",8,"创业积分释放到超级钱包","+");
                                }
                            }else{// 上限为0则无限制
                                mSuper = mSuper.add(mScore.multiply(new BigDecimal(s1)).divide(bigDecimalPrice));
                                mScore = mScore.subtract(mScore.multiply(new BigDecimal(s1)).divide(bigDecimalPrice));
                                addHistory(record.getUserId(),mScore.multiply(new BigDecimal(s1)).divide(bigDecimalPrice)+"",8,"创业积分释放到超级钱包","+");
                            }

                        } else {// 判断是否小于下限
                            if (mScore.compareTo(gtyLimitWallet.getLimitDownScoreRelease()) >=0 ){// 实际金额大于下限
                                mSuper = mSuper.add(mScore.multiply(new BigDecimal(s1)).divide(bigDecimalPrice));
                                mScore = mScore.subtract(mScore.multiply(new BigDecimal(s1)).divide(bigDecimalPrice));
                                addHistory(record.getUserId(),mScore.multiply(new BigDecimal(s1)).divide(bigDecimalPrice)+"",8,"创业积分释放到超级钱包","+");
                            }
                        }
                    }


                    if(mSuper.compareTo(BigDecimal.ZERO)==1){// 等于0不处理
                        if (gtyLimitWallet.getLimitUpSuperRelease().compareTo(mSuper)==1) {// 上限大于super
                            BigDecimal bigDecimal = gtyLimitWallet.getLimitUpSuperRelease();
                            if (bigDecimal.compareTo(BigDecimal.ZERO) == 1) {// 有上限
                                if (mSuper.compareTo(bigDecimal) >=0) {//大于上限
                                    mRelaseMnc = mRelaseMnc.add(bigDecimal.multiply(new BigDecimal(s2)));
                                    mSuper = mSuper.subtract(bigDecimal.multiply(new BigDecimal(s2)));
                                    addHistory(record.getUserId(),bigDecimal.multiply(new BigDecimal(s1))+"",9,"超级钱包释放到可提转","+");
                                }else{
                                    mRelaseMnc = mRelaseMnc.add(mSuper.multiply(new BigDecimal(s2)));
                                    mSuper = mSuper.subtract(mSuper.multiply(new BigDecimal(s2)));
                                    addHistory(record.getUserId(),mSuper.multiply(new BigDecimal(s1))+"",9,"超级钱包释放到可提转","+");
                                }
                            }else{// 上限为0则无限制
                                mRelaseMnc = mRelaseMnc.add(mSuper.multiply(new BigDecimal(s2)));
                                mSuper = mSuper.subtract(mSuper.multiply(new BigDecimal(s2)));
                                addHistory(record.getUserId(),mSuper.multiply(new BigDecimal(s1))+"",9,"超级钱包释放到可提转","+");
                            }

                        } else {// 判断是否小于下限
                            if (mSuper.compareTo(gtyLimitWallet.getLimitDownSuperRelease()) >=0 ){// 实际金额大于下限
                                mRelaseMnc = mRelaseMnc.add(mSuper.multiply(new BigDecimal(s2)));
                                mSuper = mSuper.subtract(mSuper.multiply(new BigDecimal(s2)));
                                addHistory(record.getUserId(),mSuper.multiply(new BigDecimal(s1))+"",9,"超级钱包释放到可提转","+");
                            }
                        }
                    }

//                    if (mSuper.compareTo(BigDecimal.ZERO) == 1) {// super不为空
//                        if (gtyLimitWallet.getLimitDownSuperRelease().compareTo(mSuper) == -1) {// super大于限制的值
//                            if (gtyLimitWallet.getLimitUpSuperRelease().compareTo(BigDecimal.ZERO)==1) {
//                                BigDecimal bigDecimal = gtyLimitWallet.getLimitUpSuperRelease();
//                                if (bigDecimal.compareTo(mSuper) == -1) {
//                                    mRelaseMnc = mRelaseMnc.add(mSuper.multiply(new BigDecimal(s2)));
//                                    mSuper = mSuper.subtract(mSuper.multiply(new BigDecimal(s2)));
//                                    addHistory(record.getUserId(),mSuper.multiply(new BigDecimal(s1))+"",9,"超级钱包释放到可提转","+");
//                                }
//                            } else {
//                                mRelaseMnc = mRelaseMnc.add(mSuper.multiply(new BigDecimal(s2)));
//                                mSuper = mSuper.subtract(mSuper.multiply(new BigDecimal(s2)));
//                                addHistory(record.getUserId(),mSuper.multiply(new BigDecimal(s1))+"",9,"超级钱包释放到可提转","+");
//                            }
//                        }else{// 下限
//                            if(mSuper.compareTo(gtyLimitWallet.getLimitDownSuperRelease()) >=0){
//                                mRelaseMnc = mRelaseMnc.add(mSuper.multiply(new BigDecimal(s2)).divide(bigDecimalPrice));
//                                mSuper = mSuper.subtract(mSuper.multiply(new BigDecimal(s2)).divide(bigDecimalPrice));
//                                addHistory(record.getUserId(),mSuper.multiply(new BigDecimal(s1)).divide(bigDecimalPrice)+"",9,"超级钱包释放到可提转","+");
//                            }
//                        }
//                    }



                    if(mToken.compareTo(BigDecimal.ZERO)==1){// 等于0不处理
                        if (gtyLimitWallet.getLimitUpMtokenRelease().compareTo(mToken)==1) {// 上限大于super
                            BigDecimal bigDecimal = gtyLimitWallet.getLimitUpMtokenRelease();
                            if (bigDecimal.compareTo(BigDecimal.ZERO) == 1) {// 有上限
                                if (mToken.compareTo(bigDecimal) >=0) {//大于上限
                                    mMnc = mMnc.add(bigDecimal.multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
                                    mToken = mToken.subtract(bigDecimal.multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
                                    addHistory(record.getUserId(),bigDecimal.multiply(new BigDecimal(s3)).divide(bigDecimalPrice)+"",10,"Mtoken释放到MNC","+");
                                }else{
                                    mMnc = mMnc.add(mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
                                    mToken = mToken.subtract(mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
                                    addHistory(record.getUserId(),mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice)+"",10,"Mtoken释放到MNC","+");
                                }
                            }else{// 上限为0则无限制
                                mMnc = mMnc.add(mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
                                mToken = mToken.subtract(mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
                                addHistory(record.getUserId(),mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice)+"",10,"Mtoken释放到MNC","+");
                            }

                        } else {// 判断是否小于下限
                            if (mToken.compareTo(gtyLimitWallet.getLimitDownMtokenRelease()) >=0 ){// 实际金额大于下限
                                mMnc = mMnc.add(mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
                                mToken = mToken.subtract(mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
                                addHistory(record.getUserId(),mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice)+"",10,"Mtoken释放到MNC","+");
                            }
                        }
                    }


                    //  Mtoken（MP） 千分之一释放到MNC资产
//                    if (mToken.compareTo(BigDecimal.ZERO) == 0) {
//                        BigDecimal oldMp;
//                        if (data == null || data.getCustomerIntegral() == null) {
//                            oldMp = new BigDecimal("0.00000");
//                        } else {
//                            oldMp = data.getCustomerIntegral();
//                            if (oldMp.compareTo(BigDecimal.ZERO) == 0) {
//
//                            } else {
//                                mToken = oldMp;
//                                mMnc = mMnc.add(mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
//                                mToken = mToken.subtract(mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
//                                addHistory(record.getUserId(),mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice)+"",10,"Mtoken释放到MNC","+");
//                            }
//                        }
//
//                    } else {
//
//                        if (gtyLimitWallet.getLimitUpMtokenRelease().compareTo(mToken) == -1) {// 实际金额大于限制的值
//                            if (gtyLimitWallet.getLimitUpSuperRelease().compareTo(BigDecimal.ZERO)==1) {
//                                gtyLimitWallet.getLimitUpSuperRelease() = mToken;
//                            }
//                            mMnc = mMnc.add(gtyLimitWallet.getLimitUpMtokenRelease().multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
//                            mToken = mToken.subtract(gtyLimitWallet.getLimitUpMtokenRelease().multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
//                            addHistory(record.getUserId(),gtyLimitWallet.getLimitUpMtokenRelease().multiply(new BigDecimal(s3)).divide(bigDecimalPrice)+"",10,"Mtoken释放到MNC","+");
//                        }else{
//                            if(gtyLimitWallet.getLimitDownMtokenRelease().compareTo(mToken) == -1){
//                                mMnc = mMnc.add(mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
//                                mToken = mToken.subtract(mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
//                                addHistory(record.getUserId(),mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice)+"",10,"Mtoken释放到MNC","+");
//                            }
//                        }
//                    }

                    GtyWallet gtyWallet = new GtyWallet();
                    gtyWallet.setmTokenNum(mToken.setScale(5, BigDecimal.ROUND_HALF_UP));
                    gtyWallet.setScoreNum(mScore.setScale(5, BigDecimal.ROUND_HALF_UP));
                    gtyWallet.setSuperNum(mSuper.setScale(5, BigDecimal.ROUND_HALF_UP));
                    gtyWallet.setMncNum(mMnc.setScale(5, BigDecimal.ROUND_HALF_UP));
                    gtyWallet.setMoveNum(mMove.setScale(5, BigDecimal.ROUND_HALF_UP));
                    gtyWallet.setReleasedMnc(mRelaseMnc.setScale(5, BigDecimal.ROUND_HALF_UP));
                    gtyWallet.setUserId(record.getUserId());
//                    gtyWallet.setWalletFrozen(1);
                    createActiveMq2(record.getUserId(), Constant.ACTIVEMQ_TYPE_42, gtyWallet, null);
                }

            }
        }
        LogUtils.performance.info("{} end at {}", this.getClass().getName(), new Date());
    }

    private void addHistory(String uid, String num,int type, String recordName , String mark ){

        String uuid = UUID.randomUUID().toString().replace("-", "");
        GtyWalletHistory gtyWalletHistory = new GtyWalletHistory();
        gtyWalletHistory.setId(uuid);
        gtyWalletHistory.setRecordNum(new BigDecimal(num));
        gtyWalletHistory.setRecordType(type);
        gtyWalletHistory.setUserId(uid);
        gtyWalletHistory.setRecordName(recordName);
        gtyWalletHistory.setMark(mark);
        geyWallethistoryDao.save(gtyWalletHistory);

    }
}
