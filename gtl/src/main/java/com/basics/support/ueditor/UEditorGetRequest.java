package com.basics.support.ueditor;

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
public class UEditorGetRequest {

 private String action;

 public String getAction() {
  return action;
 }

 public void setAction(String action) {
  this.action = action;
 }

 private String mainId;

 public String getMainId() {
  return mainId;
 }

 public void setMainId(String mainId) {
  this.mainId = mainId;
 }

}
