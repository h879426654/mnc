package com.basics.job;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.basics.common.BaseApiService;
import com.basics.common.Constant;
import com.basics.cu.controller.response.CustomerInfoResponse;
import com.basics.gty.entity.GtyWallet;
import com.basics.support.QueryFilter;
import com.basics.support.QueryFilterBuilder;
import com.basics.sys.entity.SysRule;
import com.basics.wallet.entity.WalletEntity;
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

        System.out.println("EveryMinuteDebugJob" + System.currentTimeMillis() / 1000);

//        List<GtyWallet> records = gtyWalletDao.query(new QueryFilterBuilder().build());
//        if (CollectionUtils.isNotEmpty(records)) {
//            for (GtyWallet record : records) {
//
//                if (!StringUtils.isNullOrEmpty(record.getUserId())) {
//
//                    BigDecimal mToken = record.getmTokenNum();//
//                    BigDecimal mSuper = record.getSuperNum();//
//                    BigDecimal mScoreRelease = record.getScoreNum();//
//                    BigDecimal mMncRelease = record.getReleasedMnc();//
//
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("id", record.getUserId());
//                    CustomerInfoResponse data = cuCustomerInfoDao.getExtend(map, "queryCustomerInfo");
//
//                    //超级钱包（老版mnc）千分之五释放到 （服务器MNC ）
//                    if (mSuper.compareTo(BigDecimal.ZERO) == 0) {
//                        BigDecimal oldMnc = data.getUseCoin();// 老mNC
//                        if (oldMnc.compareTo(BigDecimal.ZERO) == 0) {
//
//                        } else {// 有老mnc
//                            mSuper = oldMnc;
//                            mMncRelease = mMncRelease.add(mSuper.multiply(new BigDecimal(5 / 1000)));
//                            mSuper = mSuper.subtract(mSuper.multiply(new BigDecimal(5 / 1000)));
//                        }
//                    } else {
//                        mMncRelease = mMncRelease.add(mSuper.multiply(new BigDecimal(5 / 1000)));
//                        mSuper = mSuper.subtract(mSuper.multiply(new BigDecimal(5 / 1000)));
//                    }
//
//                    //  创业积分 千分之一（老MP+老MC的和）  释放到超级钱包
//                    if (mScoreRelease.compareTo(BigDecimal.ZERO) == 0) {
//                        BigDecimal mpMc = data.getCustomerIntegral().add(data.getUseMoney());
//                        if (mpMc.compareTo(BigDecimal.ZERO) == 0) {
//
//                        } else {
//                            mSuper = mSuper.add(mpMc.multiply(new BigDecimal(1 / 1000)));
//                            mScoreRelease = mScoreRelease.subtract(mpMc.multiply(new BigDecimal(1 / 1000)));
//                        }
//
//                    } else {
//                        mSuper = mSuper.add(mScoreRelease.multiply(new BigDecimal(1 / 1000)));
//                        mScoreRelease = mScoreRelease.subtract(mScoreRelease.multiply(new BigDecimal(1 / 1000)));
//                    }
//
//                    //  Mtoken（MP） 千分之一释放到MNC资产（新MNC）
//                    if (mToken.compareTo(BigDecimal.ZERO) == 0) {
//                        BigDecimal oldMp = data.getCustomerIntegral();
//                        if (oldMp.compareTo(BigDecimal.ZERO) == 0) {
//
//                        } else {
//                            mToken = oldMp;
//                            mMncRelease = mMncRelease.add(mToken.multiply(new BigDecimal(1 / 1000)));
//                            mToken = mToken.subtract(mToken.multiply(new BigDecimal(1 / 1000)));
//                        }
//                    } else {
//                        mMncRelease.add(mToken.multiply(new BigDecimal(1 / 1000)));
//                        mToken = mToken.subtract(mToken.multiply(new BigDecimal(1 / 1000)));
//
//                    }
//
//                    GtyWallet gtyWallet = new GtyWallet();
//                    gtyWallet.setmTokenNum(mToken.setScale(5, BigDecimal.ROUND_HALF_UP));
//                    gtyWallet.setReleasedMnc(mMncRelease.setScale(5, BigDecimal.ROUND_HALF_UP));
//                    gtyWallet.setScoreNum(mScoreRelease.setScale(5, BigDecimal.ROUND_HALF_UP));
//                    gtyWallet.setSuperNum(mSuper.setScale(5, BigDecimal.ROUND_HALF_UP));
//
//                    createActiveMq2(gtyWalletDao.get("USER_ID").toString(), Constant.ACTIVEMQ_TYPE_42, gtyWallet, null);
//                }
//
//            }
//        }
//        LogUtils.performance.info("{} end at {}", this.getClass().getName(), new Date());
    }
}
