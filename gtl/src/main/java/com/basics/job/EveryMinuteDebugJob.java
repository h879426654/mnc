package com.basics.job;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.basics.common.BaseApiService;
import com.basics.common.Constant;
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

		System.out.println("EveryMinuteDebugJob"+System.currentTimeMillis()/1000);

		SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
		List<WalletEntity> records = walletEntityDao.query(new QueryFilterBuilder().put("orderStatus", Constant.ORDER_STATUS_3).build());
		if (CollectionUtils.isNotEmpty(records)){
			for (WalletEntity record : records){

//				CuCustomerAccount account = cuCustomerAccountDao.get(order.getCustomerId());
//				CommonSupport.checkNotNull(account, "用户account的错误");
				if(!StringUtils.isNullOrEmpty(record.getUserId())) {
//					createActiveMq(record.getUserId(), Constant.ACTIVEMQ_TYPE_42, BigDecimal.ONE, record.getId(), "流通钱包释放",null);
					createActiveMq2("", Constant.ACTIVEMQ_TYPE_42, new BigDecimal(2), record.getMTokenNum(),
							record.getScoreNum(),
							record.getSuperNum(),
							record.getUserId(),
							null);

				}
//				if(!StringUtils.isNullOrEmpty(record.getmTokenNum())) {
//					createActiveMq(record.getUserId(), Constant.ACTIVEMQ_TYPE_43, BigDecimal.ZERO, record.getId(), "MTOKEN释放",null);
//				}
//				if(!StringUtils.isNullOrEmpty(record.getSuperNum())) {
//					createActiveMq(record.getUserId(), Constant.ACTIVEMQ_TYPE_42, BigDecimal.ZERO, record.getId(), "超级钱包释放",null);
//				}
//				if(!StringUtils.isNullOrEmpty(record.getMoveNum())) {
//					createActiveMq(record.getUserId(), Constant.ACTIVEMQ_TYPE_42, BigDecimal.ZERO, record.getId(), "流通钱包释放",null);
//				}
			}
		}
		LogUtils.performance.info("{} end at {}", this.getClass().getName(), new Date());
	}
}
