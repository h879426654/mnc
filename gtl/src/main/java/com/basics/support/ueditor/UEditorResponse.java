package com.basics.support.ueditor;

import org.codehaus.plexus.util.StringUtils;

/**
 * 统一请求格式说明
 * 
 * 为了规范化前后端通信的请求，这里统一规范前端请求格式和后端数据返回格式
 * 
 * 前端请求通过唯一的后台文件 controller.php处理前端的请求 controller.php通过GET上的action参数，判断是什么类型的请求
 * 省去不必要的请求，去除涂鸦添加背景的请求，用前端FileReader读取本地图片代替
 * 请求返回数据的格式，常规返回json字符串，数据包含state属性（成功时返回'SUCCESS'，错误时返回错误信息）。
 * 
 * @author yuwenfeng
 *
 */
public class UEditorResponse {

 private String state = "SUCCESS";

 private boolean success = true;

 public boolean isSuccess() {
  return success && StringUtils.equalsIgnoreCase("SUCCESS", this.state);
 }

 public void setSuccess(boolean success) {
  this.success = success;
 }

 public boolean success() {
  return StringUtils.equalsIgnoreCase(state, "SUCCESS");
 }

 public String getState() {
  return state;
 }

 public void setState(String state) {
  this.state = state;
 }

 public void onException(Throwable e) {
  this.state = e.getMessage();
 }

}
