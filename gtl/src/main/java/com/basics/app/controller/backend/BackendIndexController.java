package com.basics.app.controller.backend;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.app.shiro.AppUserUtils;
import com.basics.app.shiro.PermissionSupport;
import com.basics.app.shiro.ShiroSupport;
import com.basics.app.shiro.UpdatePasswordRequest;
import com.basics.app.shiro.UpdatePasswordResponse;
import com.basics.app.shiro.UpdatePasswordService;
import com.basics.app.shiro.UserSupport;
import com.basics.support.CommonSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.ServletUtils;

@Controller
public class BackendIndexController implements ApplicationContextAware {

	@Autowired
	protected ShiroSupport shiroSupport;

	public String getBaseViewDirName() {
		return "com.basics.app.backend";
	}

	protected ApplicationContext context;

	public void setApplicationContext(ApplicationContext conext) throws BeansException {
		this.context = conext;
	}

	/**
	 * 根据实体类名、基础目录、方法名称生成对应的视图名.
	 * 
	 * @param name
	 *         方法名.
	 * @return
	 */
	public String getView(String name) {
		StringBuilder sb = new StringBuilder();
		String baseViewDirName = this.getBaseViewDirName();
		sb.append(baseViewDirName);
		if (StringUtils.isNotBlank(baseViewDirName) && !StringUtils.endsWith(baseViewDirName, "/")) {
			sb.append("/");
		}
		sb.append(name);
		return sb.toString();
	}

	@RequestMapping("/backend/index")
	public String index(Model model) {
		boolean autoShowFirst = true;
		if (autoShowFirst) {
			UserSupport userSupport = AppUserUtils.getCurrentUser(UserSupport.class);
			QueryFilterBuilder filterBuilder = new QueryFilterBuilder();
			filterBuilder.put("userId", userSupport.getId());
			filterBuilder.put("state", 1);
			filterBuilder.put("type", PermissionSupport.TYPE_MENU);
			filterBuilder.put("urlNotEmpy", "true");
			filterBuilder.limit(1);
			List<PermissionSupport> menus = this.shiroSupport.getUserRolePermissionSupport().getPermissions(filterBuilder.orderBy("order").build());
			if (menus.size() > 0) {
				PermissionSupport menu = menus.get(0);
				String url = menu.getUrl();
				if (CommonSupport.isNotBlank(url)) {
					return "redirect:/backend/" + url;
				} else {
					LogUtils.performance.error("菜单没配置url:{}({})", menu.getName(), menu.getCode());
				}
			}
		}
		return this.getView("index");
	}

	@RequestMapping("/backend/menu")
	public String menu() {
		return this.getView("menu");
	}

	@RequestMapping(value = "/backend/toUpdatePassword")
	public String toUpdatePassword() {
		return this.getView("updatePassword");
	}

	public <T> Map<String, T> getBeansOfType(Class<T> paramClass) throws BeansException {
		Map<String, T> results = this.context.getBeansOfType(paramClass);
		for (ApplicationContext parent = this.context.getParent(); parent != null; parent = parent.getParent()) {
			results.putAll(parent.getBeansOfType(paramClass));
		}
		return results;
	}

	@RequestMapping(value = "/backend/updatePassword")
	public void updatePassword(UpdatePasswordRequest updateRequest, HttpServletRequest request, HttpServletResponse response) {
		UpdatePasswordResponse updateResponse = new UpdatePasswordResponse();
		try {
			boolean processed = false;
			@SuppressWarnings("rawtypes")
			List principals = SecurityUtils.getSubject().getPrincipals().asList();
			LogUtils.performance.debug("principals:{}", principals);
			Map<String, UpdatePasswordService> upsMapper = getBeansOfType(UpdatePasswordService.class);
			LogUtils.performance.debug("upsMappers:{}", upsMapper);
			// UpdatePasswordService updatePasswordService =
			// this.context.getBean(UpdatePasswordService.class);
			for (UpdatePasswordService updatePasswordService : upsMapper.values()) {
				for (Object principal : principals) {
					boolean accept = updatePasswordService.accept(principal);
					if (accept) {
						processed = true;
						updateResponse = updatePasswordService.updatePassword(principal, updateRequest);
					}
				}
			}
			if (!processed) {
				throw new RuntimeException("密码修改服务不可用.");
			}
		} catch (Throwable e) {
			updateResponse.onException(e.getMessage());
		}
		ServletUtils.renderJsonAsText(response, updateResponse);
	}
}
