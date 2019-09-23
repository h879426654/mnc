package com.basics.tr.controller.backend;

import java.util.Date;
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
import com.basics.support.GuidUtils;
import com.basics.support.ItemResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilter;
import com.basics.support.ServletUtils;
import com.basics.tr.entity.TrConvert;
@Controller
@RequestMapping("/backend/tr/trConvert/")
public class TrConvertBackendController extends BaseBackendController<TrConvert> {
	
	/**
	  * 加载规则
	  */
	@RequestMapping(value = "/load")
	public void load(HttpServletRequest request, HttpServletResponse response) {
		ItemResultSupport<TrConvert> result = new ItemResultSupport<>();
		try {
			List<TrConvert> list = this.service.query(new QueryFilter());
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
	public void modify(TrConvert entity, HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult) {
		FormResultSupport formResultSupport = new FormResultSupport();
		if (bindingResult.hasErrors()) {
			formResultSupport.onException("请检查填写字段！");
		} else {
			try {
				if (null != entity.getConvertStartTime() && null != entity.getConvertEndTime() && entity.getConvertStartTime().getTime() < entity.getConvertEndTime().getTime()) {
					ItemResultSupport<TrConvert> result = this.service.get(entity.getId());
					if(null != result && null != result.getItem()) {
						TrConvert item = result.getItem();
						entity.convertSerial(item.getConvertSerial()).versionNum(item.getVersionNum()).createTime(item.getCreateTime());
					} else {
						entity.convertSerial(GuidUtils.generateSimpleLongGuid()+"").createTime(new Date());
					}
					this.service.save(entity);
					formResultSupport.onSuccess("编辑成功！");
				} else {
					formResultSupport.onException("开始和结束时间不合法");
				}
			} catch (Throwable t) {
				formResultSupport.onException(t);
			}
		}
		ServletUtils.renderJsonAsText(response, formResultSupport);
	}

}
