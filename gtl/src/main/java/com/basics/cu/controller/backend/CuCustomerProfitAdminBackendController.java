package com.basics.cu.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.basics.cu.entity.CuCustomerProfitAdmin;
import com.basics.support.QueryFilterBuilder;
@Controller
@RequestMapping("/backend/cu/cuCustomerProfitAdmin/")
public class CuCustomerProfitAdminBackendController extends BaseBackendController<CuCustomerProfitAdmin> {
	
	@RequestMapping("/allot")
	public String allotView() {
		return getView("allot");
	}
	
	@Override
	public void willQuery(QueryFilterBuilder builder) {
		builder.orderBy("cuCustomerProfitAdmin.CREATE_TIME DESC");
	}
	
	

}
