package com.basics.support.log4j;

import java.util.Date;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.basics.app.shiro.AppUserUtils;
import com.basics.app.shiro.ShiroUtils;
import com.basics.common.Constant;
import com.basics.support.LogUtils;
import com.basics.sys.entity.SysOperLog;
import com.basics.sys.service.SysOperLogService;

@Aspect
@Order(1)
@Component
@EnableAsync
public class LogAspect {
	@Autowired
	private SysOperLogService sysOperLogService;

	@Pointcut("execution(public * com.basics.*.controller.backend.*Controller.*(..))")
	public void logPointExpression() {
	}

	@AfterReturning(value = "logPointExpression()")
	public void afterReturnMethod(JoinPoint joinPoint) {
		handleLog(joinPoint, null);
	}

	@AfterThrowing(value = "logPointExpression()", throwing = "ex")
	public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) {
		handleLog(joinPoint, ex);
	}

	@SuppressWarnings("unchecked")
	@Async
	@Transactional
	protected void handleLog(final JoinPoint joinPoint, final Exception e) {
		try {
			String methodName = joinPoint.getSignature().toLongString();
			RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
			ServletRequestAttributes servlet = (ServletRequestAttributes) attributes;
			Map<String, String[]> map = servlet.getRequest().getParameterMap();
			String params = JSONObject.toJSONString(map);
			SysOperLog operLog = new SysOperLog();
			String ip = ShiroUtils.getIp();
			operLog.setOperIp(ip);
			operLog.setOperMethod("操作方法" + methodName);
			operLog.setLoginName(AppUserUtils.getCurrentUserSupport().getCode());
			operLog.setOperParam(params);
			if (null != e) {
				operLog.setOperStatus(Constant.OPER_STATUS_FAIL);
				operLog.setErrorMsg(e.getMessage());
			}
			operLog.setOperTime(new Date());
			operLog.setOperStatus(Constant.OPER_STATUS_SUCCESS);
			sysOperLogService.save(operLog);
		} catch (Exception exp) {
			LogUtils.performance.debug("异常信息:{}", exp.getMessage());
			exp.printStackTrace();
		}
	}
}
