package com.basics.app.controller.frontend;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.app.shiro.AppUserUtils;
import com.basics.app.shiro.CaptchaException;
import com.basics.app.shiro.FormAuthenticationFilter;
import com.basics.app.shiro.ShiroUtils;
import com.basics.common.Constant;
import com.basics.support.CommonSupport;
import com.basics.support.FormResultSupport;
import com.basics.support.ResultSupport;
import com.basics.support.ServletUtils;
import com.basics.sys.entity.SysOperLog;
import com.basics.sys.service.SysOperLogService;

@Controller("app.apiController")
@RequestMapping(value = "/api/app/")
public class ApiController implements ApplicationContextAware {

	@Autowired
    private SysOperLogService sysOperLogService;
	
	protected ApplicationContext context;

	public void setApplicationContext(ApplicationContext conext) throws BeansException {
		this.context = conext;
	}

	@PostMapping("login")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			FormAuthenticationFilter filter = new FormAuthenticationFilter();
			result.setSuccess(filter.login(request, response));
			//登录日志
            SysOperLog operLog = new SysOperLog();
            operLog.setOperMethod("login");
            operLog.setOperIp(ShiroUtils.getIp());
            operLog.setLoginName(AppUserUtils.getCurrentUserSupport().getCode());
            operLog.setOperTime(new Date());
            operLog.setOperStatus(Constant.OPER_STATUS_SUCCESS);
            sysOperLogService.save(operLog);
		} catch (CaptchaException ce) {
			result.onException(ce);
		} catch (AuthenticationException ae) {
			// 默认的登录失败消息 是一段英文，如果是业务层抛出的异常,带个说明原因:
			if (CommonSupport.contains(ae.getMessage(), "原因:")) {
				result.onException(ae.getMessage());
			} else {
				result.onException("无效的登录信息");
			}
		} catch (Throwable e) {
			result.onException("无效的登录信息");
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 检查用户登录状态.
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "checkLogin")
	public void checkLogin(HttpServletRequest request, HttpServletResponse response) {
		ResultSupport result = new ResultSupport();
		try {
			result.setSuccess(AppUserUtils.getCurrentUserSupport() != null);
		} catch (Throwable e) {
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}
}
