package com.basics.sys.controller.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.support.QueryFilterBuilder;
import com.basics.sys.entity.SysOperLog;
@Controller
@RequestMapping("/backend/sys/sysOperLog/")
public class SysOperLogBackendController extends BaseBackendController<SysOperLog> {

	/**
     * 登录日志
     */
	@RequestMapping("loginLog")
	public void loginLog(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.put("operMethod","login");
        builder.orderBy("operTime desc");
		swgrid(gridPager, builder, this.service, request, response);
	}
	
}
