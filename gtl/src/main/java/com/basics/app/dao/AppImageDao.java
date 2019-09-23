package com.basics.app.dao;

import java.util.List;

import com.basics.app.entity.AppImage;
import com.basics.support.GenericDao;

public interface AppImageDao extends GenericDao<AppImage> {

 /**
  * 根据表的CLASS和数据ID定位图片列表
  * 
  * @param clazz
  *         引用表的CLASS
  * @param id
  *         引用数据的ID
  * @return
  */
 List<AppImage> listImgsByClassAndId(String clazz, String id);

 /**
  * 获取对象图片路径集合
  * 
  * @param clazz
  *         图片所属CLASS
  * @param id
  *         CLASS 的ID
  * @return List<String> 图片地址列表
  */
 List<String> listImgsURLByClassAndId(String clazz, String id);

 /**
  * 批量插入图片
  * 
  * @param appImages
  * @return
  */
 boolean multiInsert(List<AppImage> appImages);
 
 /**
  * 批量插入图
  * @param images
  * @param className
  * @param owerId
  * @return
  */
 public boolean multiInsert(String[] images, String className, String owerId);

}
