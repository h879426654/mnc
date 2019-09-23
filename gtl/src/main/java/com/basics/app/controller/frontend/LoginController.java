package com.basics.app.controller.frontend;

import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.basics.app.shiro.AppUserUtils;
import com.basics.app.shiro.FormAuthenticationFilter;
import com.basics.app.shiro.ShiroSupport;
import com.basics.support.CommonSupport;
import com.basics.support.LogUtils;
import com.basics.support.ResultSupport;
import com.basics.support.ServletUtils;
import com.google.code.kaptcha.Producer;

@Controller
public class LoginController extends BaseFrontendController {

	/**
	 * 登录成功，进入管理首页
	 */
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		// 如果已经登录，则跳转到管理首页
		if (AppUserUtils.isCurrentUserAuthenticated()) {
			return "redirect:/success.do";
		}
		return "redirect:/login.do";
	}

	@Autowired(required = false)
	ShiroSupport shiroSupport;

	public String toLoginView(String view, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 如果已经登录，则跳转到管理首页
		if (AppUserUtils.isCurrentUserAuthenticated()) {
			return "redirect:/success.do";
		}
		request.setAttribute("rememberMeParam", shiroSupport.getRememberMeParam());
		return this.getView(view);
	}

	/**
	 * 管理登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		return this.toLoginView("login", request, response, model);
	}

	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (AppUserUtils.isCurrentUserAuthenticated()) {
			return "redirect:/success.do";
		}
		LogUtils.performance.error("login:失败:{}", username);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
		model.addAttribute("isValidateCodeLogin", isValidateCodeLogin(username, true, false));
		model.addAttribute("msg", "无效的登录帐号或者用密码!");
		return this.getView("login");
	}

	/**
	 * 登录成功，进入管理首页
	 */
	@RequiresUser
	@RequestMapping(value = "/success")
	public String success(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/backend/index.do";
	}

	/**
	 * 管理登出
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
		LogUtils.performance.error("logout sessionId:{}", request.getSession().getId());
		try {
			SecurityUtils.getSubject().logout();
		} catch (Throwable e) {
			LogUtils.performance.error("logout error {}", e);
		}
		clearCookies(request, response);
		// 跳转到前台首页
		return "redirect:index.do";
	}

	@RequestMapping(value = "/unauthorized")
	public String unauthorized(HttpServletRequest request) {
		return "unauthorized";
	}

	@Autowired(required = false)
	private Producer captchaProducer;

	@RequestMapping(value = "/validateCode")
	public ModelAndView validateCode(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setDateHeader("Expires", 0);
			response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			response.setHeader("Pragma", "no-cache");
			response.setContentType("image/jpeg");
			String capText = captchaProducer.createText();
			ServletOutputStream out = null;
			try {
				out = response.getOutputStream();
				HttpSession session = request.getSession();
				LogUtils.performance.debug("validateCode:{}", capText);
				session.setAttribute(com.basics.app.shiro.FormAuthenticationFilter.DEFAULT_CAPTCHA_PARAM, capText);
				SecurityUtils.getSubject().getSession().setAttribute(com.basics.app.shiro.FormAuthenticationFilter.DEFAULT_CAPTCHA_PARAM, capText);
				java.awt.image.BufferedImage bi = captchaProducer.createImage(capText);
				ImageIO.write(bi, "jpg", out);
				out.flush();
			} finally {
				CommonSupport.closeQuietly(out);
			}
		} catch (Exception e) {
			LogUtils.performance.debug("Exception:{}", e.getMessage());
		}
		return null;
	}

	/**
	 * 清除Cookies.
	 * 
	 * @param request
	 * @param response
	 */
	public void clearCookies(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}

	private static Map<String, Integer> loginFailMap = new java.util.concurrent.ConcurrentHashMap<String, Integer>();

	/**
	 * 是否是验证码登录
	 * 
	 * @param username
	 *         用户名
	 * @param isFail
	 *         计数加1
	 * @param clean
	 *         计数清零
	 * @return
	 */
	public static boolean isValidateCodeLogin(String username, boolean isFail, boolean clean) {
		Integer loginFailNum = loginFailMap.get(username);
		if (loginFailNum == null) {
			loginFailNum = 0;
		}
		if (isFail) {
			loginFailNum++;
			loginFailMap.put(username, loginFailNum);
		}
		if (clean) {
			loginFailMap.remove(username);
		}
		boolean isValidate = loginFailNum >= 3;
		LogUtils.performance.info("isValidate={} username={} loginFailNum={}", new Object[] { isValidate, username, loginFailNum });
		return isValidate;
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
