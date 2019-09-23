package com.basics.sys.controller.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.common.Constant;
import com.basics.cu.entity.CuDrawReward;
import com.basics.cu.service.CuDrawRewardService;
import com.basics.support.FormResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.ServletUtils;
import com.basics.sys.entity.SysTurntable;
@Controller
@RequestMapping("/backend/sys/sysTurntable/")
public class SysTurntableBackendController extends BaseBackendController<SysTurntable> {
	
	@Autowired
	private CuDrawRewardService cuDrawRewardService;
	
	@RequestMapping(value= "record")
	public String recordView() {
		return getView("record");
	}
	
	
	@Override
	public void willQuery(QueryFilterBuilder builder) {
		builder.orderBy("REWARD_SORT ASC");
	}
	
	
	@RequestMapping(value = "/save")
	public void save(SysTurntable entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			this.beforeSave(result, entity, request);
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			} else {
				if(Constant.MIN_PROFIT_NUM_SHOW < entity.getRewardRate().doubleValue() || 0 == entity.getRewardRate().hashCode()) {
					this.onSave(entity);
					result.onSuccess("操作成功");
				} else {
					result.onException("中奖率错误");
				}
			}
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}
	
	@RequestMapping("/selectTurntableEveryDay")
	public void selectTurntableRewardInfo(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		swgrid(gridPager, builder, cuDrawRewardService, "selectRewardEveryDay", "countRewardEveryDay", request, response);
	}

}
