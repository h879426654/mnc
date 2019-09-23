package com.basics.job;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.basics.common.BaseApiService;
import com.basics.common.Constant;
import com.basics.cu.entity.CuCustomerAccount;
import com.basics.or.entity.OrOrder;
import com.basics.support.CommonSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.job.EveryDayJob;

/**
 * 自动取消
 */
@Component
@Transactional
public class EveryDayAutoCancelOrderJob extends BaseApiService implements EveryDayJob {

	@Override
	public void doJob() {
		LogUtils.performance.info("{} begin at {}", this.getClass().getName(), new Date());
		List<OrOrder> orders = orOrderDao.query(new QueryFilterBuilder().put("orderStatus", Constant.ORDER_STATUS_2).put("orderConfirm",new Date()).build());
		if (CollectionUtils.isNotEmpty(orders)){
		    for (OrOrder order : orders){
                CuCustomerAccount account = cuCustomerAccountDao.get(order.getCustomerId());
                CommonSupport.checkNotNull(account, "用户account的错误");
                if(account.getLockMoney().doubleValue() >= order.getOrderPayPrice().doubleValue()) {
                    createActiveMq(account.getId(), Constant.ACTIVEMQ_TYPE_34, BigDecimal.ZERO, order.getId(), "商城--自动取消",null);
                }
            }
        }
		LogUtils.performance.info("{} end at {}", this.getClass().getName(), new Date());
	}

}
