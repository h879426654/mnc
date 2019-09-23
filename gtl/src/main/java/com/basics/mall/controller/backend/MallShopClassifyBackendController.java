package com.basics.mall.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.basics.mall.entity.MallShopClassify;
import com.basics.support.QueryFilterBuilder;
@Controller
@RequestMapping("/backend/mall/mallShopClassify/")
public class MallShopClassifyBackendController extends BaseBackendController<MallShopClassify> {
	
	@Override
	public void willQuery(QueryFilterBuilder builder) {
		builder.orderBy("mallShopClassify.CLASSIFY_SORT ASC");
	}

}
