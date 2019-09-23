package com.basics.mall.controller.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.basics.app.shiro.AppUserUtils;
import com.basics.app.shiro.UserSupport;
import com.basics.mall.entity.MallProductComment;
import com.basics.mall.service.MallProductCommentService;
import com.basics.support.QueryFilterBuilder;
@Controller
@RequestMapping("/backend/mall/mallProductComment/")
public class MallProductCommentBackendController extends BaseBackendController<MallProductComment> {
	
	@Autowired
	private MallProductCommentService mallProductCommentService;
	  
	/**
	 * 商品列表
	 */
	@RequestMapping(value = "showProductCommentById", method = RequestMethod.POST)
	public void showProductListByOrderId(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		UserSupport user = AppUserUtils.getCurrentUserSupport();
		QueryFilterBuilder builder = new QueryFilterBuilder();		
		builder.put("shopId", user.getId()).orderBy("replyTime DESC").build();
		swgrid(gridPager, builder, mallProductCommentService, "showProductCommentById", "countProductCommentById", request, response);
	}
	

}
