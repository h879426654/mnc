package com.basics.job;

import com.basics.common.BaseApiService;
import com.basics.common.Constant;
import com.basics.cu.controller.response.CustomerInfoResponse;
import com.basics.cu.entity.CuCustomerAccount;
import com.basics.gty.controller.request.TokenTransferRequest;
import com.basics.gty.entity.GtyLimitWallet;
import com.basics.gty.entity.GtyWallet;
import com.basics.gty.entity.GtyWalletHistory;
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
import java.util.*;

/**
 * 自动释放资产
 */
@Component
@Transactional
public class EveryDayAutoReleaseJob extends BaseApiService implements EveryDayJob {

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

				if (!StringUtils.isNullOrEmpty(record.getUserId())) {

					BigDecimal mToken = record.getmTokenNum();//
					BigDecimal mSuper = record.getSuperNum();//
					BigDecimal mScore = record.getScoreNum();//
					BigDecimal mMove = record.getMoveNum();//
					BigDecimal mMnc = record.getMncNum();//

					Map<String, Object> map = new HashMap<>();
					map.put("id", record.getUserId());
					CustomerInfoResponse data = cuCustomerInfoDao.getExtend(map, "queryCustomerInfo");

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
								if (gtyLimitWallet.getLimitUpScoreRelease().compareTo(mpMc)==1) {// 判断是否大于上限
									BigDecimal bigDecimal = gtyLimitWallet.getLimitUpScoreRelease();
									if (bigDecimal.compareTo(BigDecimal.ZERO) == 0) {
										bigDecimal = BigDecimal.ONE;
									}
									if (bigDecimal.compareTo(mpMc) == -1) {

										mSuper = mSuper.add(mpMc.multiply(new BigDecimal(s1)).divide(bigDecimalPrice));
										mScore = mScore.subtract(mpMc.multiply(new BigDecimal(s1)).divide(bigDecimalPrice));
										addHistory(record.getUserId(),mpMc.multiply(new BigDecimal(s1)).divide(bigDecimalPrice)+"",8,"创业积分释放到超级钱包","+");
									}
								} else {// 判断是否小于下限
									if (gtyLimitWallet.getLimitDownScoreRelease().compareTo(mScore) == -1) {// 实际金额大于限制的值
										mSuper = mSuper.add(mpMc.multiply(new BigDecimal(s1)).divide(bigDecimalPrice));
										mScore = mScore.subtract(mpMc.multiply(new BigDecimal(s1)).divide(bigDecimalPrice));
										addHistory(record.getUserId(),mpMc.multiply(new BigDecimal(s1)).divide(bigDecimalPrice)+"",8,"创业积分释放到超级钱包","+");
									}
								}
							}
						}

					} else {// 正常情况
						if (gtyLimitWallet.getLimitUpScoreRelease().compareTo(mScore)==1) {// 判断是否大于上限
							BigDecimal bigDecimal = gtyLimitWallet.getLimitUpScoreRelease();
							if (bigDecimal.compareTo(BigDecimal.ZERO) == 0) {
								bigDecimal = BigDecimal.ONE;
							}
							if (bigDecimal.compareTo(mScore) == -1) {
								mSuper = mSuper.add(mScore.multiply(new BigDecimal(s1)).divide(bigDecimalPrice));
								mScore = mScore.subtract(mScore.multiply(new BigDecimal(s1)).divide(bigDecimalPrice));
								addHistory(record.getUserId(),mScore.multiply(new BigDecimal(s1)).divide(bigDecimalPrice)+"",8,"创业积分释放到超级钱包","+");
							}
						} else {// 判断是否小于下限
							if (gtyLimitWallet.getLimitDownScoreRelease().compareTo(mScore) == -1) {// 实际金额大于下限
								mSuper = mSuper.add(mScore.multiply(new BigDecimal(s1)).divide(bigDecimalPrice));
								mScore = mScore.subtract(mScore.multiply(new BigDecimal(s1)).divide(bigDecimalPrice));
								addHistory(record.getUserId(),mScore.multiply(new BigDecimal(s1)).divide(bigDecimalPrice)+"",8,"创业积分释放到超级钱包","+");
							}
						}
					}

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
							if (gtyLimitWallet.getLimitUpSuperRelease().compareTo(BigDecimal.ZERO)==1) {
								BigDecimal bigDecimal = gtyLimitWallet.getLimitUpSuperRelease();
								if (bigDecimal.compareTo(mSuper) == -1) {
									mMove = mMove.add(mSuper.multiply(new BigDecimal(s2)));
									mSuper = mSuper.subtract(mSuper.multiply(new BigDecimal(s2)));
									addHistory(record.getUserId(),mSuper.multiply(new BigDecimal(s1))+"",9,"超级钱包释放到流通钱包","+");
								}
							} else {
								mMove = mMove.add(mSuper.multiply(new BigDecimal(s2)));
								mSuper = mSuper.subtract(mSuper.multiply(new BigDecimal(s2)));
								addHistory(record.getUserId(),mSuper.multiply(new BigDecimal(s1))+"",9,"超级钱包释放到流通钱包","+");
							}
						}
					}


					//  Mtoken（MP） 千分之一释放到MNC资产
					if (mToken.compareTo(BigDecimal.ZERO) == 0) {
						BigDecimal oldMp;
						if (data == null || data.getCustomerIntegral() == null) {
							oldMp = new BigDecimal("0.00000");
						} else {
							oldMp = data.getCustomerIntegral();
							if (oldMp.compareTo(BigDecimal.ZERO) == 0) {

							} else {
								mToken = oldMp;
								mMnc = mMnc.add(mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
								mToken = mToken.subtract(mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
								addHistory(record.getUserId(),mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice)+"",10,"Mtoken释放到MNC","+");
							}
						}

					} else {

						if (gtyLimitWallet.getLimitUpMtokenRelease().compareTo(mToken) == -1) {// 实际金额大于限制的值
							mMnc = mMnc.add(gtyLimitWallet.getLimitUpMtokenRelease().multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
							mToken = mToken.subtract(gtyLimitWallet.getLimitUpMtokenRelease().multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
							addHistory(record.getUserId(),gtyLimitWallet.getLimitUpMtokenRelease().multiply(new BigDecimal(s3)).divide(bigDecimalPrice)+"",10,"Mtoken释放到MNC","+");
						}else{
							if(gtyLimitWallet.getLimitDownMtokenRelease().compareTo(mToken) == -1){
								mMnc = mMnc.add(mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
								mToken = mToken.subtract(mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice));
								addHistory(record.getUserId(),mToken.multiply(new BigDecimal(s3)).divide(bigDecimalPrice)+"",10,"Mtoken释放到MNC","+");
							}
						}

					}

					GtyWallet gtyWallet = new GtyWallet();
					gtyWallet.setmTokenNum(mToken.setScale(5, BigDecimal.ROUND_HALF_UP));
					gtyWallet.setScoreNum(mScore.setScale(5, BigDecimal.ROUND_HALF_UP));
					gtyWallet.setSuperNum(mSuper.setScale(5, BigDecimal.ROUND_HALF_UP));
					gtyWallet.setMncNum(mMnc.setScale(5, BigDecimal.ROUND_HALF_UP));
					gtyWallet.setMoveNum(mMove.setScale(5, BigDecimal.ROUND_HALF_UP));
					gtyWallet.setUserId(record.getUserId());

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
