package com.basics.job;

import com.basics.common.BaseApiService;
import com.basics.common.Constant;
import com.basics.cu.controller.response.CustomerInfoResponse;
import com.basics.cu.entity.CuCustomerAccount;
import com.basics.gty.entity.GtyLimitWallet;
import com.basics.gty.entity.GtyWallet;
import com.basics.gty.entity.TradeBean;
import com.basics.or.entity.OrOrder;
import com.basics.support.CommonSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilter;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.auth.HttpClientUtils;
import com.basics.support.job.EveryDayJob;
import com.basics.wallet.entity.WalletEntity;
import com.google.gson.Gson;
import com.qiniu.util.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自动释放资产
 */
@Component
@Transactional
public class EveryDayAutoReleaseJob extends BaseApiService implements EveryDayJob {

	@Override
	public void doJob() {
		LogUtils.performance.info("{} begin at {}", this.getClass().getName(), new Date());

		String s1 = "0.001";
		String s2 = "0.005";
		List<GtyWallet> records = gtyWalletDao.query(new QueryFilterBuilder().build());
		QueryFilter filter = new QueryFilter();
		Map<String, Object> maps = new HashMap<>();
		maps.put("ID", "1");
		filter.setParams(maps);
		GtyLimitWallet gtyLimitWallet = gtyWalletLimitDao.queryOne(filter);
		if (CollectionUtils.isNotEmpty(records)) {
			for (GtyWallet record : records) {

				if (!StringUtils.isNullOrEmpty(record.getUserId())) {

					BigDecimal mToken = record.getmTokenNum();//
					BigDecimal mSuper = record.getSuperNum();//
					BigDecimal mScore = record.getScoreNum();//
					BigDecimal mMncRelease = record.getReleasedMnc();//

					Map<String, Object> map = new HashMap<>();
					map.put("id", record.getUserId());
					CustomerInfoResponse data = cuCustomerInfoDao.getExtend(map, "queryCustomerInfo");

					//超级钱包（老版mnc）千分之五释放到 （服务器MNC ）
					if (mSuper.compareTo(BigDecimal.ZERO) == 0) {
						if (data == null) {
							mSuper = new BigDecimal("0.00000");
						} else {
							BigDecimal oldMnc = data.getUseCoin();// 老mNC
							if (oldMnc.compareTo(BigDecimal.ZERO) == 0) {

							} else {// 有老mnc
								mSuper = oldMnc;
							}
						}
					}
					if (mSuper.compareTo(BigDecimal.ZERO) == 1) {
						if (gtyLimitWallet.getLimitDownSuperRelease().compareTo(mSuper) == -1) {// 实际金额大于限制的值
							if (!StringUtils.isNullOrEmpty(gtyLimitWallet.getLimitUpSuperRelease())) {
								BigDecimal bigDecimal = new BigDecimal(gtyLimitWallet.getLimitUpSuperRelease());
								if (bigDecimal.compareTo(mSuper) == -1) {
									mMncRelease = mMncRelease.add(bigDecimal.multiply(new BigDecimal(s2)));
									mSuper = mSuper.subtract(bigDecimal.multiply(new BigDecimal(s2)));
								}
							} else {
								mMncRelease = mMncRelease.add(mSuper.multiply(new BigDecimal(s2)));
								mSuper = mSuper.subtract(mSuper.multiply(new BigDecimal(s2)));
							}
						}
					}

					//  创业积分 千分之一（老MP+老MC的和）  释放到超级钱包
					if (mScore.compareTo(BigDecimal.ZERO) == 0) {// 如果为0可能是数据有异常，做二次判断
						BigDecimal mpMc;
						if (data == null) {
							mpMc = new BigDecimal("0.00000");
						} else {
							if (data.getCustomerIntegral() == null) {
								data.setCustomerIntegral(BigDecimal.ZERO);
							}
							if (data.getUseMoney() == null) {
								data.setUseMoney(BigDecimal.ZERO);
							}
							mpMc = data.getCustomerIntegral().add(data.getUseMoney());
							if (mpMc.compareTo(BigDecimal.ZERO) == 0) {

							} else {
								if (!StringUtils.isNullOrEmpty(gtyLimitWallet.getLimitUpScoreRelease())) {// 判断是否大于上限
									BigDecimal bigDecimal = new BigDecimal(gtyLimitWallet.getLimitUpScoreRelease());
									if (bigDecimal.compareTo(mpMc) == -1) {
										mSuper = mSuper.add(bigDecimal.multiply(new BigDecimal(s1)));
										mScore = mScore.subtract(bigDecimal.multiply(new BigDecimal(s1)));

									}
								} else {// 判断是否小于下限
									if (gtyLimitWallet.getLimitDownScoreRelease().compareTo(mScore) == -1) {// 实际金额大于限制的值
										mSuper = mSuper.add(mpMc.multiply(new BigDecimal(s1)));
										mScore = mScore.subtract(mpMc.multiply(new BigDecimal(s1)));
									}
								}
							}
						}

					} else {// 正常情况
						if (!StringUtils.isNullOrEmpty(gtyLimitWallet.getLimitUpScoreRelease())) {// 判断是否大于上限
							BigDecimal bigDecimal = new BigDecimal(gtyLimitWallet.getLimitUpScoreRelease());
							if (bigDecimal.compareTo(BigDecimal.ZERO) == 0) {
								bigDecimal = BigDecimal.ONE;
							}
							if (bigDecimal.compareTo(mScore) == -1) {
								mSuper = mSuper.add(bigDecimal.multiply(new BigDecimal(s1)));
								mScore = mScore.subtract(bigDecimal.multiply(new BigDecimal(s1)));

							}
						} else {// 判断是否小于下限
							if (gtyLimitWallet.getLimitDownScoreRelease().compareTo(mScore) == -1) {// 实际金额大于下限
								mSuper = mSuper.add(mScore.multiply(new BigDecimal(s1)));
								mScore = mScore.subtract(mScore.multiply(new BigDecimal(s1)));
							}
						}
					}

					//  Mtoken（MP） 千分之一释放到MNC资产（新MNC）
					if (mToken.compareTo(BigDecimal.ZERO) == 0) {
						BigDecimal oldMp;
						if (data == null || data.getCustomerIntegral() == null) {
							oldMp = new BigDecimal("0.00000");
						} else {
							oldMp = data.getCustomerIntegral();
							if (oldMp.compareTo(BigDecimal.ZERO) == 0) {

							} else {
								mToken = oldMp;
								mMncRelease = mMncRelease.add(mToken.multiply(new BigDecimal(s1)));
								mToken = mToken.subtract(mToken.multiply(new BigDecimal(s1)));
							}
						}

					} else {
						mMncRelease = mMncRelease.add(mToken.multiply(new BigDecimal(s1)));
						mToken = mToken.subtract(mToken.multiply(new BigDecimal(s1)));
					}

					GtyWallet gtyWallet = new GtyWallet();
					gtyWallet.setmTokenNum(mToken.setScale(5, BigDecimal.ROUND_HALF_UP));
					gtyWallet.setReleasedMnc(mMncRelease.setScale(5, BigDecimal.ROUND_HALF_UP));
					gtyWallet.setScoreNum(mScore.setScale(5, BigDecimal.ROUND_HALF_UP));
					gtyWallet.setSuperNum(mSuper.setScale(5, BigDecimal.ROUND_HALF_UP));
					gtyWallet.setUserId(record.getUserId());
					createActiveMq2(record.getUserId(), Constant.ACTIVEMQ_TYPE_42, gtyWallet, null);
				}

			}
		}
		LogUtils.performance.info("{} end at {}", this.getClass().getName(), new Date());
	}

}
