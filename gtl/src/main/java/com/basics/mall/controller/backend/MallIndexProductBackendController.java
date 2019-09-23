package com.basics.mall.controller.backend;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.app.shiro.AppUserUtils;
import com.basics.app.shiro.UserSupport;
import com.basics.common.Constant;
import com.basics.mall.entity.MallIndexProduct;
import com.basics.support.FormResultSupport;
import com.basics.support.QueryFilterBuilder;
@Controller
@RequestMapping("/backend/mall/mallIndexProduct/")
public class MallIndexProductBackendController extends BaseBackendController<MallIndexProduct> {
	
	@Override
	public void beforeSave(FormResultSupport result, MallIndexProduct entity, HttpServletRequest request) {
		UserSupport user = AppUserUtils.getCurrentUserSupport();
		if(null == entity.getId()) {
			entity.createUser(user.getId()).createTime(new Date());
		}
		entity.modifyUser(user.getId()).modifyDate(new Date());
	}
	
	@Override
	public void willQuery(QueryFilterBuilder builder) {
		builder.put("flagDel", Constant.STATE_NO).orderBy("mallIndexProduct.TYPE_ID ASC");
	}

}
