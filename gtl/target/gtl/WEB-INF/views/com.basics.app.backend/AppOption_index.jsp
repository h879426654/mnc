<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/taglib.jsp"%>
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
 <tags:form_add_dialog>
  <input type="hidden" name="id" />
  <tags:form_group _title="上级选项ID：">
   <input type="text" name="parentId" data-role="dropdownlist" data-value-field="id" data-text-field="name" data-option-label="请选择"
    class="form-control"
    data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
   transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options.do'}}}">
  </tags:form_group>
  <tags:form_group _title="选项标识：">
   <input type="text" name="code" class="form-control">
  </tags:form_group>
  <tags:form_group _title="选项名称：">
   <input type="text" name="name" class="form-control">
  </tags:form_group>
  <tags:form_group _title="选项描述：">
   <input type="text" name="comment" class="form-control">
  </tags:form_group>
  <tags:form_group _title="选项排序：">
   <input type="text" name="order" class="form-control">
  </tags:form_group>
  <tags:form_group _title="选项图标：">
   <input type="text" name="icon" class="form-control">
  </tags:form_group>
  <tags:form_group _title="选项URL：">
   <input type="text" name="url" class="form-control">
  </tags:form_group>
  <tags:form_group _title="选项类型 ：">
   <input type="radio" name="type" value="0">选项组&nbsp;
    <input type="radio" name="type" value="1">选项参数&nbsp;
  </tags:form_group>
  <tags:form_group _title="是否启用：">
   <input type="text" required="required" data-required-msg="是否启用为必填项" name="flag" data-role="dropdownlist" data-value-field="code"
    data-text-field="name" data-option-label="请选择" class="form-control"
    data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
   transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/yes_no.do'}}}">
  </tags:form_group>
 </tags:form_add_dialog>
 <tags:form_edit_dialog>
  <input type="hidden" name="id" />
  <tags:form_group _title="上级选项ID：">
   <input type="text" name="parentId" data-role="dropdownlist" data-value-field="id" data-text-field="name" data-option-label="请选择"
    class="form-control"
    data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
   transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options.do'}}}">
  </tags:form_group>
  <tags:form_group _title="选项标识：">
   <input type="text" name="code" class="form-control">
  </tags:form_group>
  <tags:form_group _title="选项名称：">
   <input type="text" name="name" class="form-control">
  </tags:form_group>
  <tags:form_group _title="选项描述：">
   <input type="text" name="comment" class="form-control">
  </tags:form_group>
  <tags:form_group _title="选项排序：">
   <input type="text" name="order" class="form-control">
  </tags:form_group>
  <tags:form_group _title="选项类型 ：">
   <input type="radio" name="type" value="0">选项组&nbsp;
    <input type="radio" name="type" value="1">选项参数&nbsp;
   
  </tags:form_group>
  <tags:form_group _title="是否启用：">
   <input type="text" required="required" data-required-msg="是否启用为必填项" name="flag" data-role="dropdownlist" data-value-field="code"
    data-text-field="name" data-option-label="请选择" class="form-control"
    data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
   transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/yes_no.do'}}}">
  </tags:form_group>
 </tags:form_edit_dialog>
 <tags:form_view_dialog>
  <tags:form_group _title="选项ID：">
   <span name="id"></span>
  </tags:form_group>
  <tags:form_group _title="上级选项ID：">
   <span name="parentId"></span>
  </tags:form_group>
  <tags:form_group _title="选项标识：">
   <span name="code"></span>
  </tags:form_group>
  <tags:form_group _title="选项名称：">
   <span name="name"></span>
  </tags:form_group>
  <tags:form_group _title="选项描述：">
   <span name="comment"></span>
  </tags:form_group>
  <tags:form_group _title="选项排序：">
   <span name="order"></span>
  </tags:form_group>
  <tags:form_group _title="选项类型 ：">
   <span name="type"></span>
  </tags:form_group>
  <tags:form_group _title="是否启用：">
   <span name="flagName"></span>
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
{id:'code',title:'选项标识',columnClass:'text-center'} 
,{id:'name',title:'选项名称',columnClass:'text-center',hideType:'xs'} 
,{id:'order',title:'选项排序',columnClass:'text-center',hideType:'xs'}
]</tags:swgrid>
  </div>
  <!-- 表格} -->
 </section>
</body>
</html>
