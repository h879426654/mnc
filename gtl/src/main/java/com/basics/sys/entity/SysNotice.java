package com.basics.sys.entity;

import java.util.Date;
public class SysNotice extends SysNoticeBase{
 /**
 * 公告ID
 */
 public SysNotice id(String id){
  this.setId(id);
  return this;
 }
 /**
 * 公告类型(1公告通知 2站内信)
 */
 public SysNotice bulletinType(Integer bulletinType){
  this.setBulletinType(bulletinType);
  return this;
 }
 /**
 * 公告标题
 */
 public SysNotice noticeTitle(String noticeTitle){
  this.setNoticeTitle(noticeTitle);
  return this;
 }
 /**
 * 公告内容
 */
 public SysNotice noticeContext(String noticeContext){
  this.setNoticeContext(noticeContext);
  return this;
 }
 /**
 * 权重
 */
 public SysNotice noticeSort(Integer noticeSort){
  this.setNoticeSort(noticeSort);
  return this;
 }
 /**
 * 版本号
 */
 public SysNotice versionNum(Integer versionNum){
  this.setVersionNum(versionNum);
  return this;
 }
 /**
 * 是否删除（1是 0否）
 */
 public SysNotice flagDel(Integer flagDel){
  this.setFlagDel(flagDel);
  return this;
 }
 /**
 * 创建时间
 */
 public SysNotice createTime(Date createTime){
  this.setCreateTime(createTime);
  return this;
 }
 /**
 * 创建人
 */
 public SysNotice createUser(String createUser){
  this.setCreateUser(createUser);
  return this;
 }
 /**
 * 修改人
 */
 public SysNotice modifyUser(String modifyUser){
  this.setModifyUser(modifyUser);
  return this;
 }
 /**
 * 修改时间
 */
 public SysNotice modifyTime(Date modifyTime){
  this.setModifyTime(modifyTime);
  return this;
 }
}