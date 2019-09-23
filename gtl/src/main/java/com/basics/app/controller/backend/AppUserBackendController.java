package com.basics.app.controller.backend;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.app.entity.AppRole;
import com.basics.app.entity.AppUser;
import com.basics.app.entity.UsersAndRoles;
import com.basics.app.service.AppRoleService;
import com.basics.app.service.AppUserRolePermissionService;
import com.basics.app.ui.AppRoleZTreeDataProvider;
import com.basics.support.ItemResultSupport;
import com.basics.support.ListResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.ResultSupport;
import com.basics.support.ServletUtils;
import com.basics.support.mybatis.CacheUsed;
import com.basics.support.ztree.ZTreeBuilder;
import com.basics.support.ztree.ZTreeNode;

@Controller("app.appUserBackendController")
@RequestMapping("/backend/app/appUser/")
@RequiresPermissions("app:appUser:index")
public class AppUserBackendController extends BaseBackendController<AppUser> {

	@Override
	public String getUiTextField() {
		return "name";
	}

	@Autowired
	AppUserRolePermissionService urpService;

	@RequestMapping(value = "getUserRoles")
	public void getUserRoles(String user, HttpServletRequest request, HttpServletResponse response) {
		ItemResultSupport<String> result = new ItemResultSupport<String>();
		try {
			result = this.urpService.getUserRoleIds(user);
		} catch (Throwable e) {
			result.onException(e);
			LogUtils.performance.error("{}", e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	@RequestMapping(value = "saveUsersForRoles")
	public void saveUsersForRoles(UsersAndRoles usersAndRoles, HttpServletRequest request, HttpServletResponse response) {
		ItemResultSupport<UsersAndRoles> result = new ItemResultSupport<UsersAndRoles>();
		try {
			result = this.urpService.saveUsersForRoles(usersAndRoles);
		} catch (Throwable e) {
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	@Autowired
	AppRoleService roleService;

	public void didList(ListResultSupport result, HttpServletRequest request, HttpServletResponse response) {
		if (result.isSuccess()) {
			List<AppUser> items = result.getRows();
			for (AppUser item : items) {
				if (StringUtils.isBlank(item.getRoleNames())) {
					item.setRoleNames(roleService.getUserRoleNames(item.getId()));
				}
			}
		}
	}

	@RequestMapping(value = "validateCode")
	public void validateCode(AppUser entity, HttpServletRequest request, HttpServletResponse response) {
		ResultSupport result = new ResultSupport();
		try {
			if (StringUtils.isBlank(entity.getCode())) {
				throw new RuntimeException("编码不允许为空");
			}
			if (StringUtils.isBlank(entity.getCode())) {
				throw new RuntimeException("编码不允许为空");
			}
			AppUser existed = this.service.queryOne(new QueryFilterBuilder().put("code", entity.getCode()).build());
			if (existed != null && !existed.getId().equals(entity.getId())) {
				throw new RuntimeException("编码" + existed.getCode() + "已经存在");
			}
		} catch (Throwable e) {
			result.onException(e.getMessage());
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	@Autowired
	AppRoleService appRoleService;

	@RequestMapping(value = "appRoleZtree")
	public void appRoleZtree(HttpServletRequest request, HttpServletResponse response) {
		ItemResultSupport<List<ZTreeNode>> result = new ItemResultSupport<List<ZTreeNode>>();
		try {
			AppRoleZTreeDataProvider dataProvider = new AppRoleZTreeDataProvider(appRoleService);
			result.setItem(new ZTreeBuilder<AppRole>().build(dataProvider));
		} catch (Throwable e) {
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	@RequestMapping(value = "clearCache")
	public void clearCache(HttpServletRequest request, HttpServletResponse response) {
		ResultSupport result = new ResultSupport();
		try {
			CacheUsed.clearAllCache();
			result.onSuccess("操作成功");
		} catch (Throwable e) {
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

}
