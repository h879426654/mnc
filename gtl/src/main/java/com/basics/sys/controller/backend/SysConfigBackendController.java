package com.basics.sys.controller.backend;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.support.FormResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.ServletUtils;
import com.basics.sys.entity.SysConfig;
@Controller
@RequestMapping("/backend/sys/sysConfig/")
public class SysConfigBackendController extends BaseBackendController<SysConfig> {
	
	@Override
	public void willQuery(QueryFilterBuilder builder) {
		builder.orderBy("sysConfig.CONFIG_CODE ASC");
	}
	
	@Override
	@RequestMapping(value = "/save")
	public void save(SysConfig entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			this.beforeSave(result, entity, request);
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			SysConfig rule = this.service.queryOne(new QueryFilterBuilder().put("configCode", entity.getConfigCode()).build());
			if(StringUtils.isNotBlank(entity.getId())) {
				this.onSave(entity);
				result.onSuccess("操作成功");
			} else {
				if(null != rule) {
					result.onException("当前code已存在");
				} else {
					entity.setCreateTime(new Date());
					this.onSave(entity);
					result.onSuccess("操作成功");
				}
			}
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

}
