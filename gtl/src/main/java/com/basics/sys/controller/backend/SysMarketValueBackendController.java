package com.basics.sys.controller.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.support.FormResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.ServletUtils;
import com.basics.sys.entity.SysMarketValue;
import com.basics.sys.service.SysMarketValueService;
@Controller
@RequestMapping("/backend/sys/sysMarketValue/")
public class SysMarketValueBackendController extends BaseBackendController<SysMarketValue> {
	
	@Autowired
	private SysMarketValueService sysMarketValueService;
	
	
	@Override
	public void save(SysMarketValue entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			this.beforeSave(result, entity, request);
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			SysMarketValue moneyRate = sysMarketValueService.queryOne(new QueryFilterBuilder().put("rateTime", entity.getRateTime()).build());
			if(null != moneyRate && null == entity.getId()) {
				result.onException("该日汇率比已存在！");
			} else {
				sysMarketValueService.save(entity);
				result.onSuccess("操作成功");
			}
		} catch (Exception e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}
	
	@Override
	public void willQuery(QueryFilterBuilder builder) {
		builder.orderBy("sysMarketValue.RATE_TIME DESC");
	}

}
