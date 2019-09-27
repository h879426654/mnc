package com.basics.gty.controller.api;

import com.basics.common.DataItemResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenIdRequest;
import com.basics.gty.entity.GtyWallet;
import com.basics.gty.service.GtyWalletService;
import com.basics.support.QueryFilter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gty/wallet/")
public class GtyWalletApiController implements ApplicationContextAware {

	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;
	@Autowired
	private GtyWalletService gtySer;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@RequestMapping("getWalletInfo")
	public GtyWallet query(TokenIdRequest request, HttpServletRequest req) {
		GtyWallet gtyWallet = new GtyWallet();
		if(StringUtils.isBlank(request.getId())){
			gtyWallet.setMsg("未找到该用户");
			gtyWallet.setStatus(1);
			return gtyWallet;
		}
		QueryFilter filter = new QueryFilter();
		Map<String,Object> map = new HashMap<>();
		map.put("USER_ID",request.getId());
		filter.setParams(map);
		gtyWallet = gtySer.queryOne(filter);
		gtyWallet.setMsg("成功");
		gtyWallet.setStatus(0);
//		response.setItem(gtySer.query(filter));
//		response.setStatus(0);
		return gtyWallet;
	}
}
