package com.basics.support.ueditor;

/**
 * 
 * 6. listimage
 * 
 * 请求参数:
 * 
 * GET {"action": "listimage", "start": 0, "size": 20}
 * 
 * 返回格式:
 * 
 * // 需要支持callback参数,返回jsonp格式 { "state": "SUCCESS", "list": [{ "url":
 * "upload/1.jpg" }, { "url": "upload/2.jpg" }, ], "start": 20, "total": 100
 * 
 * 
 * @author yuwenfeng.
 *
 */
public class UEditorListImageRequest {

 int start;
 int size = 20;

 public int getStart() {
  return start;
 }

 public void setStart(int start) {
  this.start = start;
 }

 public int getSize() {
  return size;
 }

 public void setSize(int size) {
  this.size = size;
 }

}
