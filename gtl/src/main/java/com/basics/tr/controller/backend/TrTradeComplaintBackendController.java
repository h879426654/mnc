package com.basics.tr.controller.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.support.FormResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.ServletUtils;
import com.basics.tr.entity.TrTradeComplaint;

@Controller
@RequestMapping("/backend/tr/trTradeComplaint/")
public class TrTradeComplaintBackendController extends BaseBackendController<TrTradeComplaint> {

	/**
	 * 审核
	 */
	@RequestMapping(value = "/save")
	public void save(TrTradeComplaint entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			this.beforeSave(result, entity, request);
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			this.onSave(entity);
			result.onSuccess("操作成功");
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

}
