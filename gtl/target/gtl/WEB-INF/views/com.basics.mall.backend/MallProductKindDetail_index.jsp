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
  <tags:form_group _title="主键：">
   <input type="text" name="id" class="form-control" required="required" data-required-msg="主键为必填项">
  </tags:form_group>
  <tags:form_group _title="商品维度主表ID：">
   <input type="text" name="detailKindId" class="form-control" required="required" data-required-msg="商品维度主表ID为必填项">
  </tags:form_group>
  <tags:form_group _title="名称：">
   <input type="text" name="detailName" class="form-control" required="required" data-required-msg="名称为必填项">
  </tags:form_group>
  <tags:form_group _title="描述：">
   <input type="text" name="detailDescription" class="form-control" required="required" data-required-msg="描述为必填项">
  </tags:form_group>
  <tags:form_group _title="维度值：">
   <input type="text" name="detailKindValue" class="form-control" required="required" data-required-msg="维度值为必填项">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="商品维度主表ID：">
   <input type="text" name="detailKindId" class="form-control" required="required" data-required-msg="商品维度主表ID为必填项">
  </tags:form_group>
  <tags:form_group _title="名称：">
   <input type="text" name="detailName" class="form-control" required="required" data-required-msg="名称为必填项">
  </tags:form_group>
  <tags:form_group _title="描述：">
   <input type="text" name="detailDescription" class="form-control" required="required" data-required-msg="描述为必填项">
  </tags:form_group>
  <tags:form_group _title="维度值：">
   <input type="text" name="detailKindValue" class="form-control" required="required" data-required-msg="维度值为必填项">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="主键：">
   <span name="id"></span>
  </tags:form_group>
  <tags:form_group _title="商品维度主表ID：">
   <span name="detailKindId"></span>
  </tags:form_group>
  <tags:form_group _title="名称：">
   <span name="detailName"></span>
  </tags:form_group>
  <tags:form_group _title="描述：">
   <span name="detailDescription"></span>
  </tags:form_group>
  <tags:form_group _title="维度值：">
   <span name="detailKindValue"></span>
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
     {id:'id',title:'主键',columnClass:'text-center'}
    ,{id:'detailKindId',title:'商品维度主表ID',columnClass:'text-center',hideType:'xs'}
    ,{id:'detailName',title:'名称',columnClass:'text-center',hideType:'xs'}
    ,{id:'detailDescription',title:'描述',columnClass:'text-center',hideType:'xs'}
    ,{id:'detailKindValue',title:'维度值',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
