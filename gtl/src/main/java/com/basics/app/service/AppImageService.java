package com.basics.app.service;

import java.util.List;

import com.basics.app.entity.AppImage;
import com.basics.support.GenericService;
import com.basics.support.fileupload.BootstrapFileInputInitialResponse;
import com.basics.support.fileupload.BootstrapFileInputUploadResponse;
import com.basics.support.fileupload.KindEditorFileUploadResponse;

public interface AppImageService extends GenericService<AppImage> {

	public KindEditorFileUploadResponse saveKindEditorFileUpload(Class<?> entityClass, AppImage appImage);

	public BootstrapFileInputUploadResponse saveBootstrapFileInputUpload(Class<?> entityClass, AppImage appImage);

	public BootstrapFileInputInitialResponse doBootstrapFileInputInitial(AppImage appImage);

	public List<AppImage> listImgsByClassAndId(String string, String tradeId);

}
