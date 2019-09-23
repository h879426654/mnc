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
  <tags:form_group _title="角色id：">
  <input type="text" name="roleId" class="form-control" required="required" data-required-msg="角色id为必填项">
  </tags:form_group>
  <tags:form_group _title="权限id：">
  <input type="text" name="permissionId" class="form-control" required="required" data-required-msg="权限id为必填项">
  </tags:form_group>
 </tags:form_add_dialog>
 <tags:form_edit_dialog>
  <input type="hidden" name="id" />
  <tags:form_group _title="角色id：">
  <input type="text" name="roleId" class="form-control" required="required" data-required-msg="角色id为必填项">
  </tags:form_group>
  <tags:form_group _title="权限id：">
  <input type="text" name="permissionId" class="form-control" required="required" data-required-msg="权限id为必填项">
  </tags:form_group>
 </tags:form_edit_dialog>
 <tags:form_view_dialog>
  <tags:form_group _title="角色id：">
   <span name="roleId"></span> 
  </tags:form_group>
  <tags:form_group _title="权限id：">
   <span name="permissionId"></span> 
  </tags:form_group>
 </tags:form_view_dialog>
 <section class="content-header">
  <h1>${_urlMenuComment}</h1>
 </section>
 <section class="content">
  <tags:form_search>
   <tags:form_group _title="模糊查询:">
    <input type="text" class="form-control" name="q" placeholder="模糊查询">
   </tags:form_group>
  </tags:form_search>
<!-- 表格{ -->
  <div class="box">
   <div class="box-header with-border">
    <tags:form_grid_toolbar></tags:form_grid_toolbar>
   </div>
   <tags:swgrid>
[
{id:'roleId',title:'角色id',columnClass:'text-center'} 
,{id:'permissionId',title:'权限id',columnClass:'text-center',hideType:'xs'} 
]</tags:swgrid>
  </div>
  <!-- 表格} -->
 </section>
</body>
</html>
