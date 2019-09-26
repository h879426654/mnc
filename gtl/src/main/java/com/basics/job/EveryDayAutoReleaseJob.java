package com.basics.job;

import com.basics.common.BaseApiService;
import com.basics.common.Constant;
import com.basics.cu.entity.CuCustomerAccount;
import com.basics.or.entity.OrOrder;
import com.basics.support.CommonSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.job.EveryDayJob;
import com.basics.wallet.controller.dao.WalletBaseDao;
import com.basics.wallet.controller.entity.WalletEntity;
import com.qiniu.util.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 自动取消
 */
@Component
@Transactional
public class EveryDayAutoReleaseJob extends BaseApiService implements EveryDayJob {

	@Override
	public void doJob() {
		LogUtils.performance.info("{} begin at {}", this.getClass().getName(), new Date());
		List<WalletEntity> records = walletReleaseDao.query(new QueryFilterBuilder().put("orderStatus", Constant.ORDER_STATUS_2).build());
		if (CollectionUtils.isNotEmpty(records)){
			for (WalletEntity record : records){

//				CuCustomerAccount account = cuCustomerAccountDao.get(order.getCustomerId());
//				CommonSupport.checkNotNull(account, "用户account的错误");
				if(!StringUtils.isNullOrEmpty(record.getSuperNum())) {
					createActiveMq(record.getUserId(), Constant.ACTIVEMQ_TYPE_42, BigDecimal.ZERO, record.getId(), "流通钱包释放",null);
				}
				if(!StringUtils.isNullOrEmpty(record.getmTokenNum())) {
					createActiveMq(record.getUserId(), Constant.ACTIVEMQ_TYPE_43, BigDecimal.ZERO, record.getId(), "MTOKEN释放",null);
				}
				if(!StringUtils.isNullOrEmpty(record.getSuperNum())) {
					createActiveMq(record.getUserId(), Constant.ACTIVEMQ_TYPE_42, BigDecimal.ZERO, record.getId(), "超级钱包释放",null);
				}
				if(!StringUtils.isNullOrEmpty(record.getMoveNum())) {
					createActiveMq(record.getUserId(), Constant.ACTIVEMQ_TYPE_42, BigDecimal.ZERO, record.getId(), "流通钱包释放",null);
				}
			}
		}
		LogUtils.performance.info("{} end at {}", this.getClass().getName(), new Date());
	}

}
