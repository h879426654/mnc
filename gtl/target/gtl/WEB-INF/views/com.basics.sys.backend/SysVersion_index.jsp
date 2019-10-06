<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
 <meta name="decorator" content="backend" />
 <title>${_urlMenuComment}</title>
 <meta charset="utf-8">
 <script type="text/javascript">
  var options = {
   on : function(what) {
    //alert('on' + what);
   }
  };

  $(function() {
   kendo.bind($("#grid"), {});
   CrudApp.init(options);
   CrudApp.on('search');
  });
 </script>
</head>
<body>
<tags:form_add_dialog _multipart="true">
  <tags:form_group _title="版本类型：">
    <input type="text" name="versionType" data-role="dropdownlist"
      data-value-field="code" data-text-field="name" class="form-control" 
      data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
     transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/APP_TYPE.do'}}}">
  </tags:form_group>
  <tags:form_group _title="更新内容：">
     <textarea style="width: 100%; height: 200px"  required-data-msg="请填写公告内容！" data-role="kindeditor" class="kindeditor" name="versionContext"></textarea>
  </tags:form_group>
  <tags:form_group _title="版本号：">
   <input type="number" name="versionCode" class="form-control" required="required" data-required-msg="版本号为必填项">
  </tags:form_group>
  <tags:form_group _title="版本：">
   <input type="text" name="versionNum" class="form-control" required="required" data-required-msg="版本为必填项">
  </tags:form_group>
 <tags:form_group _title="下载地址：">
   <input type="text" name="versionUrl" class="form-control" required="required" data-required-msg="下载地址为必填项">
  </tags:form_group> 
  <%-- <tags:form_group _title="下载地址：">
   <input type="file" name="file" class="form-control">
  </tags:form_group> --%>
</tags:form_add_dialog>


<tags:form_edit_dialog _multipart="true">
 <input type="hidden" name="id" />
  <tags:form_group _title="版本类型：">
   <input type="text" name="versionType" data-role="dropdownlist"
      data-value-field="code" data-text-field="name" class="form-control" 
      data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
     transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/APP_TYPE.do'}}}">
  </tags:form_group>
  <tags:form_group _title="更新内容：">
   <textarea style="width: 100%; height: 200px"  required-data-msg="请填写公告内容！" data-role="kindeditor" class="kindeditor" name="versionContext"></textarea>
  </tags:form_group>
  <tags:form_group _title="版本号：">
   <input type="number" name="versionCode" class="form-control" required="required" data-required-msg="版本号为必填项">
  </tags:form_group>
  <tags:form_group _title="版本：">
   <input type="text" name="versionNum" class="form-control" required="required" data-required-msg="版本为必填项">
  </tags:form_group>
  <tags:form_group _title="下载地址：">
   <input type="text" name="versionUrl" class="form-control" required="required" data-required-msg="下载地址为必填项">
  </tags:form_group> 
  <%-- <tags:form_group _title="下载地址：">
   <input type="file" name="file" class="form-control"  >
  </tags:form_group> --%>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="版本ID：">
   <span name="id"></span>
  </tags:form_group>
  <tags:form_group _title="版本名：">
   <span name="versionName"></span>
  </tags:form_group>
  <tags:form_group _title="版本类型：">
   <span name="versionType"></span>
  </tags:form_group>
  <tags:form_group _title="更新内容：">
   <span name="versionContext"></span>
  </tags:form_group>
  <tags:form_group _title="版本号：">
   <span name="versionCode"></span>
  </tags:form_group>
  <tags:form_group _title="版本：">
   <span name="versionNum"></span>
  </tags:form_group>
  <tags:form_group _title="下载地址：">
   <span name="versionUrl"></span>
  </tags:form_group>
</tags:form_view_dialog>
<section class="content-header">
 <h1>${_urlMenuComment}</h1>
</section>
<section class="content">
 <tags:form_search>
  <div class="col-md-6">
   <tags:form_group _title="模糊查询:">
    <input type="text" class="form-control" name="q" placeholder="模糊查询">
   </tags:form_group>
  </div>
 </tags:form_search>
 <!-- 表格{ -->
 <div class="box">
  <div class="box-header with-border">
   <tags:form_grid_toolbar></tags:form_grid_toolbar>
  </div>
  <tags:swgrid>
   [
     {id:'versionName',title:'版本名',columnClass:'text-center',hideType:'xs'}
    ,{id:'versionType',title:'版本类型',columnClass:'text-center',hideType:'xs'}
    ,{id:'versionContext',title:'更新内容',columnClass:'text-center',hideType:'xs'}
    ,{id:'versionCode',title:'版本号',columnClass:'text-center',hideType:'xs'}
    ,{id:'versionNum',title:'版本',columnClass:'text-center',hideType:'xs'}
    ,{id:'versionUrl',title:'下载地址',columnClass:'text-center',hideType:'xs'}
    ,{id:'createTime',title:'创建时间',columnClass:'text-center',hideType:'xs', resolution:formatDate}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
