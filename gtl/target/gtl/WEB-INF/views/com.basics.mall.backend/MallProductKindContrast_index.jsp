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
  <tags:form_group _title="商品维度对照ID：">
   <input type="text" name="id" class="form-control" required="required" data-required-msg="商品维度对照ID为必填项">
  </tags:form_group>
  <tags:form_group _title="商品主维度ID：">
   <input type="text" name="kindId" class="form-control" required="required" data-required-msg="商品主维度ID为必填项">
  </tags:form_group>
  <tags:form_group _title="商品子维度ID：">
   <input type="text" name="kindDetailId" class="form-control" required="required" data-required-msg="商品子维度ID为必填项">
  </tags:form_group>
  <tags:form_group _title="商品ID：">
   <input type="text" name="productId" class="form-control" required="required" data-required-msg="商品ID为必填项">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="商品主维度ID：">
   <input type="text" name="kindId" class="form-control" required="required" data-required-msg="商品主维度ID为必填项">
  </tags:form_group>
  <tags:form_group _title="商品子维度ID：">
   <input type="text" name="kindDetailId" class="form-control" required="required" data-required-msg="商品子维度ID为必填项">
  </tags:form_group>
  <tags:form_group _title="商品ID：">
   <input type="text" name="productId" class="form-control" required="required" data-required-msg="商品ID为必填项">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="商品维度对照ID：">
   <span name="id"></span>
  </tags:form_group>
  <tags:form_group _title="商品主维度ID：">
   <span name="kindId"></span>
  </tags:form_group>
  <tags:form_group _title="商品子维度ID：">
   <span name="kindDetailId"></span>
  </tags:form_group>
  <tags:form_group _title="商品ID：">
   <span name="productId"></span>
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
     {id:'id',title:'商品维度对照ID',columnClass:'text-center'}
    ,{id:'kindId',title:'商品主维度ID',columnClass:'text-center',hideType:'xs'}
    ,{id:'kindDetailId',title:'商品子维度ID',columnClass:'text-center',hideType:'xs'}
    ,{id:'productId',title:'商品ID',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
