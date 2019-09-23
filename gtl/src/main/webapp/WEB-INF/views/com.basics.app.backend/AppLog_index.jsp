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
  <tags:form_group _title="日志ID：">
   <input type="text" name="id" class="form-control" required="required" data-required-msg="日志ID为必填项">
  </tags:form_group>
  <tags:form_group _title="日志说明：">
   <input type="text" name="logName" class="form-control" required="required" data-required-msg="日志说明为必填项">
  </tags:form_group>
  <tags:form_group _title="日志详情：">
   <input type="text" name="logContext" class="form-control" required="required" data-required-msg="日志详情为必填项">
  </tags:form_group>
  <tags:form_group _title="日志描述：">
   <input type="text" name="logRemark" class="form-control" required="required" data-required-msg="日志描述为必填项">
  </tags:form_group>
  <tags:form_group _title="创建时间：">
   <input type="text" name="logCreateDate" class="form-control" required="required" data-required-msg="创建时间为必填项">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="日志说明：">
   <input type="text" name="logName" class="form-control" required="required" data-required-msg="日志说明为必填项">
  </tags:form_group>
  <tags:form_group _title="日志详情：">
   <input type="text" name="logContext" class="form-control" required="required" data-required-msg="日志详情为必填项">
  </tags:form_group>
  <tags:form_group _title="日志描述：">
   <input type="text" name="logRemark" class="form-control" required="required" data-required-msg="日志描述为必填项">
  </tags:form_group>
  <tags:form_group _title="创建时间：">
   <input type="text" name="logCreateDate" class="form-control" required="required" data-required-msg="创建时间为必填项">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="日志ID：">
   <span name="id"></span>
  </tags:form_group>
  <tags:form_group _title="日志说明：">
   <span name="logName"></span>
  </tags:form_group>
  <tags:form_group _title="日志详情：">
   <span name="logContext"></span>
  </tags:form_group>
  <tags:form_group _title="日志描述：">
   <span name="logRemark"></span>
  </tags:form_group>
  <tags:form_group _title="创建时间：">
   <span name="logCreateDate"></span>
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
     {id:'id',title:'日志ID',columnClass:'text-center'}
    ,{id:'logName',title:'日志说明',columnClass:'text-center',hideType:'xs'}
    ,{id:'logContext',title:'日志详情',columnClass:'text-center',hideType:'xs'}
    ,{id:'logRemark',title:'日志描述',columnClass:'text-center',hideType:'xs'}
    ,{id:'logCreateDate',title:'创建时间',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
