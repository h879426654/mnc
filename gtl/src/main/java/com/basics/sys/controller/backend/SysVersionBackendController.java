package com.basics.sys.controller.backend;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.common.Constant;
import com.basics.support.FileStoreService;
import com.basics.support.FormResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.ServletUtils;
import com.basics.sys.entity.SysVersion;

@Controller
@RequestMapping("/backend/sys/sysVersion/")
public class SysVersionBackendController extends BaseBackendController<SysVersion> {
	
	@Autowired
	private FileStoreService baseFileStoreService;

	@Override
	@RequestMapping(value = "/save")
	public void save(SysVersion entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			this.beforeSave(result, entity, request);
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			entity.setCreateTime(new Date());
			entity.setVersionName(Constant.STATE_YES == entity.getVersionType() ? "安卓" : "苹果");
			/*if (null != entity.getFile() && !entity.getFile().isEmpty()) {
				String path = "app/"+entity.getFile().getOriginalFilename();
				try {
					this.baseFileStoreService.write(path, entity.getFile().getInputStream());
				} catch (Exception e) {
					e.printStackTrace();
				}
				String url = this.baseFileStoreService.getInternetUrl(path);
				entity.setVersionUrl(url);
			}*/ 
			this.onSave(entity);
			result.onSuccess("操作成功");
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

}
