package com.basics.sys.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.support.QueryFilterBuilder;
import com.basics.sys.entity.SysCountry;
@Controller
@RequestMapping("/backend/sys/sysCountry/")
public class SysCountryBackendController extends BaseBackendController<SysCountry> {
	
	@Override
	public void willQuery(QueryFilterBuilder builder) {
		builder.orderBy("sysCountry.COUNTRY_SORT ASC");
	}

}
