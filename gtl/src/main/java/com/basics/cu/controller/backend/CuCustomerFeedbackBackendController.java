package com.basics.cu.controller.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.common.Constant;
import com.basics.cu.entity.CuCustomerFeedback;
import com.basics.support.QueryFilterBuilder;
@Controller
@RequestMapping("/backend/cu/cuCustomerFeedback/")
public class CuCustomerFeedbackBackendController extends BaseBackendController<CuCustomerFeedback> {
	/*
	反馈记录
	 */
	@RequestMapping(value="record")
	public String recordView() {
		return getView("record");
	}
	/*
	选择反馈信息
	 */
	@RequestMapping("/selectFeedbackInfo")
	public void selectFeedbackInfo(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.put("feedbackStatus", Constant.TRADE_STATUS_1).orderBy("cuCustomerFeedback.create_time DESC");
		swgrid(gridPager, builder, this.service, "queryFeedbackInfo", "count", request, response);
	}
	
	/*
	记录选择的反馈信息？
	 */
	@RequestMapping("/selectFeedbackInfoRecord")
	public void selectFeedbackInfoRecord(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.put("notEqFeedbackStatus", Constant.TRADE_STATUS_1).orderBy("cuCustomerFeedback.create_time DESC");
		swgrid(gridPager, builder, this.service, "queryFeedbackInfo", "count", request, response);
	}

}
