package com.basics.app.support;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basics.app.entity.AppImage;
import com.basics.app.entity.AppUser;
import com.basics.app.service.AppImageService;
import com.basics.app.shiro.AppUserUtils;
import com.basics.support.FastjsonUtils;
import com.basics.support.GenericModelSupportAction;
import com.basics.support.GuidUtils;
import com.basics.support.ItemResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.ModelSupport;
import com.basics.support.ServletUtils;
import com.basics.support.VFSUtils;
import com.basics.support.fileupload.BootstrapFileInputInitialResponse;
import com.basics.support.fileupload.BootstrapFileInputUploadResponse;
import com.basics.support.fileupload.KindEditorFileUploadResponse;

/**
 * BackendControllerSupport.
 *
 * @param <T>
 *            the generic type
 */
public class BackendControllerSupport<T extends ModelSupport> extends GenericModelSupportAction<T> {
	@Autowired
	protected AppImageService appImageService;

	public AppUser getCurrentUserObject(HttpServletRequest request) {
		return AppUserUtils.getCurrentUser(AppUser.class);
	}

	public String getCurrentUser(HttpServletRequest request) {
		return AppUserUtils.getCurrentUser(AppUser.class).getId();
	}

	public String getThumbPath(String path) {
		String dir = FilenameUtils.getPath(path);
		String basename = FilenameUtils.getBaseName(path);
		String ext = FilenameUtils.getExtension(path);
		String thumbname = String.format("%s_thumb.%s", basename, ext);
		return VFSUtils.buildVirtualPath(dir, thumbname);
	}

	/**
	 * 返回业务类名称.
	 *
	 * @return the biz class
	 */
	protected String getBizClass() {
		return this.entityClass.getSimpleName();
	}

	@RequestMapping(value = "/generateGuid")
	public void generateGuid(HttpServletRequest request, HttpServletResponse response) {
		ItemResultSupport<String> result = new ItemResultSupport<String>();
		result.setItem(GuidUtils.generateSimpleGuid());
		ServletUtils.renderJsonAsText(response, result);
	}

	@RequestMapping(value = "/kindEditorFileUpload")
	public void kindEditorFileUpload(AppImage appImage, HttpServletRequest request, HttpServletResponse response) {
		KindEditorFileUploadResponse result = this.appImageService.saveKindEditorFileUpload(this.entityClass, appImage);
		ServletUtils.renderJsonAsText(response, result);
	}

	@RequestMapping(value = "/appImageFileUpload")
	public void appImageFileUpload(AppImage appImage, HttpServletRequest request, HttpServletResponse response) {
		KindEditorFileUploadResponse result = this.appImageService.saveKindEditorFileUpload(this.entityClass, appImage);
		ServletUtils.renderJsonAsText(response, result);
	}

	@RequestMapping(value = "/bootstrapFileInputUpload")
	public void bootstrapFileInputUpload(AppImage appImage, HttpServletRequest request, HttpServletResponse response) {
		BootstrapFileInputUploadResponse result = this.appImageService.saveBootstrapFileInputUpload(this.entityClass, appImage);
		LogUtils.performance.info("bootstrapFileInputUpload:{}", FastjsonUtils.string(result));
		ServletUtils.renderJsonAsText(response, result);
	}

	@RequestMapping(value = "/bootstrapFileInputDelete")
	public void bootstrapFileInputDelete(AppImage appImage, HttpServletRequest request, HttpServletResponse response) {
		LogUtils.performance.info("bootstrapFileInputDelete:{}", FastjsonUtils.string(appImage));
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			this.appImageService.delete(appImage);
		} catch (Throwable e) {
			result.put("error", "文件删除失败");
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	@RequestMapping(value = "/bootstrapFileInputInitial")
	public void bootstrapFileInputInitial(AppImage appImage, HttpServletRequest request, HttpServletResponse response) {
		LogUtils.performance.info("bootstrapFileInputInitial:{}", FastjsonUtils.string(appImage));
		BootstrapFileInputInitialResponse result = this.appImageService.doBootstrapFileInputInitial(appImage);
		ServletUtils.renderJsonAsText(response, result);
	}

	public static void setFileDownloadHeader(HttpServletRequest request, HttpServletResponse response, String fileName) {
		try {
			// 中文文件名支持
			String encodedfileName = null;
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("MSIE")) {// IE
				encodedfileName = java.net.URLEncoder.encode(fileName, "UTF-8");
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {
				encodedfileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			} else {
				encodedfileName = java.net.URLEncoder.encode(fileName, "UTF-8");
			}
			response.setContentType("application/x-excel");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
