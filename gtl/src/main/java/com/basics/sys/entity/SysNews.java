package com.basics.sys.entity;

import java.util.Date;
public class SysNews extends SysNewsBase{
 /**
 * 规则ID
 */
 public SysNews id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 新闻标题
 */
 public SysNews newsTitle(String newsTitle){
  this.setNewsTitle(newsTitle);
  return this;
 }
 /**
 * 新闻图片
 */
 public SysNews newsImg(String newsImg){
  this.setNewsImg(newsImg);
  return this;
 }
 /**
 * 新闻内容
 */
 public SysNews newsContext(String newsContext){
  this.setNewsContext(newsContext);
  return this;
 }
 /**
 * 新闻权重
 */
 public SysNews newsSort(Integer newsSort){
  this.setNewsSort(newsSort);
  return this;
 }
 /**
 * 新闻状态(1显示 0不显示)
 */
 public SysNews newsStatus(Integer newsStatus){
  this.setNewsStatus(newsStatus);
  return this;
 }
 /**
 * 浏览量
 */
 public SysNews newsReadNum(Integer newsReadNum){
  this.setNewsReadNum(newsReadNum);
  return this;
 }
 /**
 * 版本号
 */
 public SysNews versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public SysNews flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public SysNews createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public SysNews createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public SysNews modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public SysNews modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}