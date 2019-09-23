package com.basics.app.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.plexus.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.basics.app.dao.AppImageDao;
import com.basics.app.entity.AppImage;
import com.basics.app.service.AppImageService;
import com.basics.support.CommonSupport;
import com.basics.support.FileStoreService;
import com.basics.support.GenericMybatisService;
import com.basics.support.GuidUtils;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilter;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.VFSUtils;
import com.basics.support.fileupload.BootstrapFileInputInitialResponse;
import com.basics.support.fileupload.BootstrapFileInputSupport.PreviewConfigItem;
import com.basics.support.fileupload.BootstrapFileInputUploadResponse;
import com.basics.support.fileupload.KindEditorFileUploadResponse;

@Service
public class AppImageMybatisService extends GenericMybatisService<AppImage> implements AppImageService {
	@Autowired
	private AppImageDao appImageDao;

	/** The base file store service. */
	@Autowired
	protected FileStoreService baseFileStoreService;

	public String getRelativePath(File baseDir, File targetFile) {
		return StringUtils.substringAfter(targetFile.getAbsolutePath(), baseDir.getAbsolutePath());
	}

	@Override
	public void deleteByPK(Serializable id) {
		try {
			AppImage appImage = this.queryByPK(id);
			if (appImage != null) {
				if (StringUtils.isNotBlank(appImage.getUrl())) {
					this.baseFileStoreService.delete(appImage.getUrl());
				}
				if (StringUtils.isNotBlank(appImage.getThumbnailUrl())) {
					this.baseFileStoreService.delete(appImage.getThumbnailUrl());
				}
			}
		} catch (Throwable e) {
			LogUtils.performance.error("{}", e);
		}
		super.deleteByPK(id);
	}

	/**
	 * Generate biz file path.
	 *
	 * @param bizClass
	 *         the biz class
	 * @param file
	 *         the file
	 * @return the string
	 */
	public String generateBizFilePath(String bizClass, MultipartFile file) {
		String fileExt = FilenameUtils.getExtension(file.getOriginalFilename());
		// 生成唯一的文件名.
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String uploadToFileName = StringUtils.isBlank(fileExt) ? uuid : uuid + "." + fileExt;
		String path = VFSUtils.buildVirtualPath("base", bizClass, uploadToFileName);
		return path;
	}

	public String getThumbPath(String path) {
		String dir = FilenameUtils.getPath(path);
		String basename = FilenameUtils.getBaseName(path);
		String ext = FilenameUtils.getExtension(path);
		String thumbname = "";
		if (StringUtils.isBlank(ext)) {
			thumbname = String.format("%s_thumb", basename);
		} else {
			thumbname = String.format("%s_thumb.%s", basename, ext);
		}
		return VFSUtils.buildVirtualPath(dir, thumbname);
	}

	public int generateImageSort(String ownerId) {
		if (StringUtils.isBlank(ownerId)) {
			return 1;
		}
		return (int) (this.count(new QueryFilterBuilder().put("ownerId", ownerId).build().getParams()) + 1);
	}

	public KindEditorFileUploadResponse saveKindEditorFileUpload(Class<?> entityClass, AppImage appImage) {
		KindEditorFileUploadResponse response = new KindEditorFileUploadResponse();
		ByteArrayOutputStream bos = null;
		try {
			if (appImage.getImgFile() == null || appImage.getImgFile().isEmpty()) {
				throw new Exception("请指定上传的文件!");
			}
			MultipartFile file = appImage.getImgFile();
			String bizClass = entityClass.getSimpleName();
			if (StringUtils.isBlank(appImage.getPath())) {
				appImage.setPath(this.generateBizFilePath(bizClass, appImage.getImgFile()));
			}
			String path = appImage.getPath();
			// 文件存储记录
			if (StringUtils.isBlank(appImage.getOwnerId())) {
				appImage.setOwnerId(GuidUtils.generateSimpleGuid());
			}
			appImage.setOrder(this.generateImageSort(appImage.getOwnerId()));
			appImage.attach(appImage.getImgFile());

			// 根据最大宽度和高度进行裁剪
			/*
			 * if (appImage.needResize() && ThumbnailsUtils.support(file.getBytes())) {
			 * LogUtils.performance.info("resize image to {}x{}", appImage.getMaxWidth(),
			 * appImage.getMaxHeight()); bos = new ByteArrayOutputStream();
			 * ThumbnailsUtils.resize(file.getInputStream(), bos, appImage.getMaxWidth(),
			 * appImage.getMaxWidth()); bos.flush(); this.baseFileStoreService.write(path,
			 * bos.toByteArray()); } else { this.baseFileStoreService.write(path,
			 * file.getInputStream()); }
			 */
			// 根据要求生成缩略图
			/*
			 * if (appImage.needThumb() && ThumbnailsUtils.support(file.getBytes())) {
			 * LogUtils.performance.info("thumb image to {}x{}", appImage.getThumbWidth(),
			 * appImage.getThumbHeight()); bos = new ByteArrayOutputStream();
			 * ThumbnailsUtils.resize(file.getInputStream(), bos, appImage.getThumbWidth(),
			 * appImage.getThumbHeight()); bos.flush(); String thumbPath =
			 * this.getThumbPath(path); this.baseFileStoreService.write(thumbPath,
			 * bos.toByteArray()); response.setThumbPath(thumbPath);
			 * appImage.setThumbnailUrl(this.baseFileStoreService.getInternetUrl(thumbPath))
			 * ; }
			 */
			this.baseFileStoreService.write(path, file.getInputStream());
			String url = this.baseFileStoreService.getInternetUrl(path);
			LogUtils.performance.info("url:{}", url);
			response.setUrl(url);
			response.setPath(path);
			// 保存文件存储
			appImage.setUrl(url);
			// 非多文件,删除已经存在
			LogUtils.performance.info("multipe:{}", appImage.multiple());
			if (!appImage.multiple()) {
				LogUtils.performance.info("不允许上传多个文件:删除已经存在的文件");
				this.dao.deleteAll(new QueryFilterBuilder().put("ownerId", appImage.getOwnerId()).put("ownerClass", appImage.getOwnerClass()).build().getParams());
			}
			this.save(appImage);
			response.setFileId(appImage.getId());
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			response.onException(e);
		} finally {
			IOUtils.closeQuietly(bos);
		}
		return response;
	}

	public BootstrapFileInputUploadResponse saveBootstrapFileInputUpload(Class<?> entityClass, AppImage appImage) {
		BootstrapFileInputUploadResponse response = new BootstrapFileInputUploadResponse();
		KindEditorFileUploadResponse kindEditorFileUpload = this.saveKindEditorFileUpload(entityClass, appImage);
		if (!kindEditorFileUpload.isSuccess()) {
			response.setError(kindEditorFileUpload.getMessage());
		} else {
			response.getInitialPreview().add(kindEditorFileUpload.getUrl());
			PreviewConfigItem previewConfigItem = new PreviewConfigItem();
			previewConfigItem.setCaption(appImage.getName());
			previewConfigItem.getExtra().put("ownerId", appImage.getOwnerId());
			previewConfigItem.getExtra().put("ownerClass", appImage.getOwnerClass());
			previewConfigItem.setKey(appImage.getId());
			// previewConfigItem.setUrl(kindEditorFileUpload.getUrl());
			response.getInitialPreviewConfig().add(previewConfigItem);
		}
		return response;
	}

	public BootstrapFileInputInitialResponse doBootstrapFileInputInitial(AppImage appImage) {
		BootstrapFileInputInitialResponse response = new BootstrapFileInputInitialResponse();
		QueryFilter filter = new QueryFilterBuilder().put("ownerId", appImage.getOwnerId()).put("ownerClass", appImage.getOwnerClass()).build();
		if (filter.getParams().isEmpty()) {
			LogUtils.performance.error("ownerId/owerClass required.");
			return response;
		}
		List<AppImage> images = this.query(filter);
		for (AppImage image : images) {
			response.getInitialPreview().add(this.baseFileStoreService.getInternetUrl(image.getUrl()));
			PreviewConfigItem previewConfigItem = new PreviewConfigItem();
			// previewConfigItem.setCaption(image.getName());
			previewConfigItem.getExtra().put("ownerId", image.getOwnerId());
			previewConfigItem.getExtra().put("ownerClass", image.getOwnerClass());
			previewConfigItem.setKey(image.getId());
			// previewConfigItem.setUrl(kindEditorFileUpload.getUrl());
			previewConfigItem.setShowDelete(CommonSupport.equals("0", appImage.getView()));
			response.getInitialPreviewConfig().add(previewConfigItem);
		}
		return response;
	}

	@Override
	public List<AppImage> listImgsByClassAndId(String string, String tradeId) {
		return appImageDao.listImgsByClassAndId(string, tradeId);
	}

}
