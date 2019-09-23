package com.basics.sys.controller.backend;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.common.Constant;
import com.basics.support.FormResultSupport;
import com.basics.support.QueryFilterBuilder;
import com.basics.sys.entity.SysNotice;
@Controller
@RequestMapping("/backend/sys/sysNotice/")
public class SysNoticeBackendController extends BaseBackendController<SysNotice> {
	
	@Override
	public void beforeSave(FormResultSupport result, SysNotice entity, HttpServletRequest request) {
		if(Constant.BULLETIN_TYPE_5 == entity.getBulletinType()) {
			entity.setNoticeContext(entity.getBulletinTypeName());
		}
		entity.setCreateTime(new Date());
	}
	
	@Override
	public void willQuery(QueryFilterBuilder builder) {
		builder.orderBy("sysNotice.BULLETIN_TYPE ASC, NOTICE_SORT ASC");
	}

}
