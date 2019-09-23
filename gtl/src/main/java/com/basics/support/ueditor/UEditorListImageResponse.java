package com.basics.support.ueditor;

import java.util.ArrayList;
import java.util.List;

/**
 * * 6. listimage
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
 * @author yuwenfeng.
 *
 */
public class UEditorListImageResponse extends UEditorResponse {

 List<UEditorImage> list = new ArrayList<UEditorImage>();
 int start;
 long total;

 public List<UEditorImage> getList() {
  return list;
 }

 public void setList(List<UEditorImage> list) {
  this.list = list;
 }

 public int getStart() {
  return start;
 }

 public void setStart(int start) {
  this.start = start;
 }

 public long getTotal() {
  return total;
 }

 public void setTotal(long total) {
  this.total = total;
 }

}
