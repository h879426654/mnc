package com.basics.app.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

	public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	public String getCaptchaParam() {
		return captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	public static final String DEFAULT_USER_TYPE_PARAM = "userType";

	private String userTypeParam = DEFAULT_USER_TYPE_PARAM;

	public String getUserTypeParam() {
		return userTypeParam;
	}

	protected String getUserType(ServletRequest request) {
		return WebUtils.getCleanParam(request, getUserTypeParam());
	}

	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		if (password == null) {
			password = "";
		}
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host);
		String userType = this.getUserType(request);
		token.setUserType(userType);
		return token;
	}

	public boolean login(ServletRequest request, ServletResponse response) {
		UsernamePasswordToken token = (UsernamePasswordToken) createToken(request, response);
		if (token == null) {
			String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " + "must be created in order to execute a login attempt.";
			throw new IllegalStateException(msg);
		}
		Subject subject = getSubject(request, response);
		subject.login(token);
		return true;
	}
}