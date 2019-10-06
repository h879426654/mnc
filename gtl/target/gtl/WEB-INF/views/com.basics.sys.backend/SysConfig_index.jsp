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
<tags:form_add_dialog>
  <tags:form_group _title="配置：">
   <input type="text" name="configCode" class="form-control" required="required" data-required-msg="配置为必填项">
  </tags:form_group>
  <tags:form_group _title="配置名：">
   <input type="text" name="configName" class="form-control" required="required" data-required-msg="配置名为必填项">
  </tags:form_group>
  <tags:form_group _title="配置值：">
   <input type="text" name="configValue" class="form-control" required="required" data-required-msg="配置值为必填项">
  </tags:form_group>
  <tags:form_group _title="是否启用：">
   <input type="text" required="required" data-required-msg="是否启用为必填项" name="configFlag" data-role="dropdownlist" data-value-field="code"
    data-text-field="name" class="form-control"
    data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
   transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/yes_no.do'}}}">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="配置：">
   <input type="text" name="configCode" class="form-control" required="required" data-required-msg="配置为必填项" readonly="readonly">
  </tags:form_group>
  <tags:form_group _title="配置名：">
   <input type="text" name="configName" class="form-control" required="required" data-required-msg="配置名为必填项">
  </tags:form_group>
  <tags:form_group _title="配置值：">
   <input type="text" name="configValue" class="form-control" required="required" data-required-msg="配置值为必填项">
  </tags:form_group>
  <tags:form_group _title="是否启用：">
    <input type="text" required="required" data-required-msg="是否启用为必填项" name="configFlag" data-role="dropdownlist" data-value-field="code"
    data-text-field="name" class="form-control"
    data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
   transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/yes_no.do'}}}">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="配置ID：">
   <span name="id"></span>
  </tags:form_group>
  <tags:form_group _title="配置：">
   <span name="configCode"></span>
  </tags:form_group>
  <tags:form_group _title="配置名：">
   <span name="configName"></span>
  </tags:form_group>
  <tags:form_group _title="配置值：">
   <span name="configValue"></span>
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
     {id:'configCode',title:'配置',columnClass:'text-center',hideType:'xs'}
    ,{id:'configName',title:'配置名',columnClass:'text-center',hideType:'xs'}
    ,{id:'configValue',title:'配置值',columnClass:'text-center',hideType:'xs'}
    ,{id:'configFlag',title:'是否启用',columnClass:'text-center',hideType:'xs',resolution:formatSatus}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
