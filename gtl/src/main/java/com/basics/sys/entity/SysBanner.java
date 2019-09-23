package com.basics.sys.entity;

import java.util.Date;
public class SysBanner extends SysBannerBase{
 /**
 * 横幅ID
 */
 public SysBanner id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 横幅标题
 */
 public SysBanner bannerTitle(String bannerTitle){
  this.setBannerTitle(bannerTitle);
  return this;
 }
 /**
  * 横幅图片地址
  */
 public SysBanner bannerType(String bannerType){
	 this.setBannerType(bannerType);
	 return this;
 }
 /**
 * 横幅图片地址
 */
 public SysBanner bannerImage(String bannerImage){
  this.setBannerImage(bannerImage);
  return this;
 }
 /**
 * 横幅链接
 */
 public SysBanner bannerUrl(String bannerUrl){
  this.setBannerUrl(bannerUrl);
  return this;
 }
 /**
 * 横幅权重
 */
 public SysBanner bannerSort(Integer bannerSort){
  this.setBannerSort(bannerSort);
  return this;
 }
 /**
 * 版本号
 */
 public SysBanner versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public SysBanner flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public SysBanner createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public SysBanner createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public SysBanner modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public SysBanner modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}