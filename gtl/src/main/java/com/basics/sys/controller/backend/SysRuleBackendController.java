package com.basics.sys.controller.backend;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.basics.support.FormResultSupport;
import com.basics.support.ItemResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilter;
import com.basics.support.ServletUtils;
import com.basics.sys.entity.SysRule;
@Controller
@RequestMapping("/backend/sys/sysRule/")
public class SysRuleBackendController extends BaseBackendController<SysRule> {
	
	/**
	  * 加载规则
	  */
	@RequestMapping(value = "/load")
	public void load(HttpServletRequest request, HttpServletResponse response) {
		ItemResultSupport<SysRule> result = new ItemResultSupport<SysRule>();
		try {
			List<SysRule> list = this.service.query(new QueryFilter());
			if (CollectionUtils.isNotEmpty(list)) {
				result.setItem(list.get(0));
			}
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 修改规则
	 */
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public void modify(SysRule entity, HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult) {
		FormResultSupport formResultSupport = new FormResultSupport();
		if (bindingResult.hasErrors()) {
			formResultSupport.onException("请检查填写字段！");
		} else {
			try {
				this.service.save(entity);
				formResultSupport.onSuccess("编辑成功！");
			} catch (Throwable t) {
				formResultSupport.onException(t);
			}
		}
		ServletUtils.renderJsonAsText(response, formResultSupport);
	}
	
	

}
