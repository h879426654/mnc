package com.basics.sys.controller.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.support.QueryFilterBuilder;
import com.basics.sys.entity.SysTurntableReward;
@Controller
@RequestMapping("/backend/sys/sysTurntableReward/")
public class SysTurntableRewardBackendController extends BaseBackendController<SysTurntableReward> {
	
	@RequestMapping("/selectTurntableRewardInfo")
	public void selectTurntableRewardInfo(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		QueryFilterBuilder builder = new QueryFilterBuilder();
		swgrid(gridPager, builder, this.service, "queryTurntableReward", "count", request, response);
	}
	
	
	
	
	

}
