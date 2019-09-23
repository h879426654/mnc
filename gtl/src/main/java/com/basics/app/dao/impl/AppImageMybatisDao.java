package com.basics.app.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.basics.app.dao.AppImageDao;
import com.basics.app.entity.AppImage;
import com.basics.app.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.GuidUtils;
import com.basics.support.INameMapper;
import com.basics.support.QueryFilterBuilder;

@Repository
public class AppImageMybatisDao extends GenericMybatisDaoSupport<AppImage> implements AppImageDao {

    public AppImageMybatisDao() {
        super();
        this.setPrimaryKeyFields("id");
    }

    public List<String> listImgsURLByClassAndId(String clazz, String id) {
        List<AppImage> images = this.listImgsByClassAndId(clazz, id);
        List<String> imageUrls = new ArrayList<String>();
        for (AppImage appImage : images) {
            if (appImage.getUrl() != null && !StringUtils.isEmpty(appImage.getUrl()))
                imageUrls.add(appImage.getUrl());
        }
        return imageUrls;
    }

    public List<AppImage> listImgsByClassAndId(String clazz, String id) {
        QueryFilterBuilder queryFilterBuilder = new QueryFilterBuilder();
        queryFilterBuilder.put("ownerClass", clazz);
        queryFilterBuilder.put("ownerId", id);
        queryFilterBuilder.orderBy("appImage.IMAGE_ORDER ASC");
        return this.query(queryFilterBuilder.build());
    }

    public INameMapper onBuildNameMapper() {
        DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
        defaultNameMapper.configFieldColumnName("id", "IMAGE_ID");
        defaultNameMapper.configFieldColumnName("ownerClass", "IMAGE_OWNER_CLASS");
        defaultNameMapper.configFieldColumnName("ownerId", "IMAGE_OWNER_ID");
        defaultNameMapper.configFieldColumnName("url", "IMAGE_URL");
        defaultNameMapper.configFieldColumnName("thumbnailUrl", "IMAGE_THUMBNAIL_URL");
        defaultNameMapper.configFieldColumnName("size", "IMAGE_SIZE");
        defaultNameMapper.configFieldColumnName("order", "IMAGE_ORDER");
        defaultNameMapper.configFieldColumnName("name", "IMAGE_NAME");
        return defaultNameMapper;
    }

    public boolean multiInsert(String[] images, String className, String owerId) {
        List<AppImage> appImages = new ArrayList<AppImage>();
        if (images == null || images.length <= 0) {
            return true;
        }
        for (int i = 0; i < images.length; i++) {
            AppImage appImage = new AppImage();
            appImage.setId(GuidUtils.generateSimpleGuid());
            appImage.setOwnerClass(className);
            appImage.setOwnerId(owerId);
            appImage.setUrl(images[i]);
            appImage.setThumbnailUrl(images[i]);
            appImage.setOrder(i + 1);
            appImage.setName(images[i]);
            appImages.add(appImage);
            this.save(appImage);
        }
        // if (images.length > 0) {
        // return this.multiInsert(appImages);
        // } else {
        return true;
        // }
    }

    public boolean multiInsert(List<AppImage> appImages) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("images", appImages);
        return this.insertExtend(params, "multiInsert") > 0 ? true : false;
    }

}
