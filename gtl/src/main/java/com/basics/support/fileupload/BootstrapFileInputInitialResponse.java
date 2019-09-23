package com.basics.support.fileupload;

import java.util.ArrayList;
import java.util.List;

import com.basics.support.fileupload.BootstrapFileInputSupport.PreviewConfigItem;

/**
 * <a href="http://plugins.krajee.com/file-input">bootstrap文件上传服务端封装.</a>
 * 
 * @author yuwenfeng
 *
 */
public class BootstrapFileInputInitialResponse {

 // error: string, which will be the error message for the entire batch upload
 // and will help the plugin to identify error in the file upload. For example
 // the response from server would be sent as {error: 'You are not

 private String error;

 // initialPreview: array, the list of image files or any HTML markup to point
 // to your uploaded files. You will always send ONE row in this array - because
 // you will always receive ONE file with the upload in asynchronous mode. If
 // this property is set, the plugin will automatically replace the files
 // dynamically in the preview content after upload success. The configuration
 // for this is similar to the initialPreview option setting. For example:
 // initialPreview: [
 // '<img src='/images/desert.jpg' class='file-preview-image' alt='Desert'
 // title='Desert'>',
 // ],
 private List<String> initialPreview = new ArrayList<String>();

 // initialPreviewConfig: array, the configuration to identify properties for
 // each file markup in initialPreview item (that is setup as part of
 // initialPreview). You will always send ONE row in this array - because you
 // will always receive ONE file with the upload in asynchronous mode. If this
 // property is set, the plugin will automatically replace the files dynamically
 // in the preview content after upload success. The configuration for this is
 // similar to the initialPreviewConfig option setting. For example:
 //
 // initialPreviewConfig: [
 // {
 // caption: 'desert.jpg',
 // width: '120px',
 // url: 'http://localhost/avatar/delete', // server delete action
 // key: 100,
 // extra: {id: 100}
 // }
 // ]
 //
 private List<PreviewConfigItem> initialPreviewConfig = new ArrayList<PreviewConfigItem>();

 // append: boolean, whether to append the content to the initialPreview if you
 // already set an initialPreview on INIT. If not set this defaults to true. If
 // set to false, the plugin will overwrite the initialPreview content.
 private boolean append = true;

 public String getError() {
  return error;
 }

 public void setError(String error) {
  this.error = error;
 }

 public List<String> getInitialPreview() {
  return initialPreview;
 }

 public void setInitialPreview(List<String> initialPreview) {
  this.initialPreview = initialPreview;
 }

 public List<PreviewConfigItem> getInitialPreviewConfig() {
  return initialPreviewConfig;
 }

 public void setInitialPreviewConfig(List<PreviewConfigItem> initialPreviewConfig) {
  this.initialPreviewConfig = initialPreviewConfig;
 }

 public boolean isAppend() {
  return append;
 }

 public void setAppend(boolean append) {
  this.append = append;
 }

}
