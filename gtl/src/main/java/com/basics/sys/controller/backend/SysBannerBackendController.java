package com.basics.sys.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.support.QueryFilterBuilder;
import com.basics.sys.entity.SysBanner;
@Controller
@RequestMapping("/backend/sys/sysBanner/")
public class SysBannerBackendController extends BaseBackendController<SysBanner> {
	
	@Override
	public void willQuery(QueryFilterBuilder builder) {
		builder.orderBy("sysBanner.COUNTRY_ID ASC, BANNER_TYPE ASC, sysBanner.BANNER_SORT ASC");
	}

}
