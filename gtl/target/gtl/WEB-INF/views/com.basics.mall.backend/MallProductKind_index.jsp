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
  <tags:form_group _title="类别主键：">
   <input type="text" name="id" class="form-control" required="required" data-required-msg="类别主键为必填项">
  </tags:form_group>
  <tags:form_group _title="类别名称：">
   <input type="text" name="productKindName" class="form-control" required="required" data-required-msg="类别名称为必填项">
  </tags:form_group>
  <tags:form_group _title="描述：">
   <input type="text" name="productKindDescription" class="form-control" required="required" data-required-msg="描述为必填项">
  </tags:form_group>
  <tags:form_group _title="类别拼接权重，值越小越靠前：">
   <input type="text" name="productKindMosaicOrder" class="form-control" required="required" data-required-msg="类别拼接权重，值越小越靠前为必填项">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="类别名称：">
   <input type="text" name="productKindName" class="form-control" required="required" data-required-msg="类别名称为必填项">
  </tags:form_group>
  <tags:form_group _title="描述：">
   <input type="text" name="productKindDescription" class="form-control" required="required" data-required-msg="描述为必填项">
  </tags:form_group>
  <tags:form_group _title="类别拼接权重，值越小越靠前：">
   <input type="text" name="productKindMosaicOrder" class="form-control" required="required" data-required-msg="类别拼接权重，值越小越靠前为必填项">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="类别主键：">
   <span name="id"></span>
  </tags:form_group>
  <tags:form_group _title="类别名称：">
   <span name="productKindName"></span>
  </tags:form_group>
  <tags:form_group _title="描述：">
   <span name="productKindDescription"></span>
  </tags:form_group>
  <tags:form_group _title="类别拼接权重，值越小越靠前：">
   <span name="productKindMosaicOrder"></span>
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
     {id:'id',title:'类别主键',columnClass:'text-center'}
    ,{id:'productKindName',title:'类别名称',columnClass:'text-center',hideType:'xs'}
    ,{id:'productKindDescription',title:'描述',columnClass:'text-center',hideType:'xs'}
    ,{id:'productKindMosaicOrder',title:'类别拼接权重，值越小越靠前',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
