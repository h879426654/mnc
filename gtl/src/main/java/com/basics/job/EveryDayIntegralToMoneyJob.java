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
import com.basics.support.LogUtils;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.job.EveryDayJob;

/**
 * 每日自动释放积分
 * 
 * @author fan
 *
 */
@Component
@Transactional
public class EveryDayIntegralToMoneyJob extends BaseApiService implements EveryDayJob {

	@Override
	public void doJob() {
		LogUtils.performance.info("{} begin at {}", this.getClass().getName(), new Date());
		List<CuCustomerAccount> list = cuCustomerAccountDao.query(new QueryFilterBuilder().put("GTCustomerIntegral", Constant.UN_ACTIVE_NUM).build());
		if (CollectionUtils.isNotEmpty(list)) {
			/*SysRule rule = sysRuleDao.queryOne(new QueryFilterBuilder().build());
			CommonSupport.checkNotNull(rule, "系统规则不存在！");*/
			createActiveMq("", Constant.ACTIVEMQ_TYPE_27, BigDecimal.ZERO, list.size() + "", "静态释放", null);
		}
		LogUtils.performance.info("{} end at {}", this.getClass().getName(), new Date());
	}

}
