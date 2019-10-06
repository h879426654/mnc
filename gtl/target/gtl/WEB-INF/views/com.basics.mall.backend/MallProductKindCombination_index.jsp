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
  <tags:form_group _title="组合编号：">
   <input type="text" name="id" class="form-control" required="required" data-required-msg="组合编号为必填项">
  </tags:form_group>
  <tags:form_group _title="商品编号：">
   <input type="text" name="productId" class="form-control" required="required" data-required-msg="商品编号为必填项">
  </tags:form_group>
  <tags:form_group _title="维度组合，程序计算出多个维度阶乘组合存入当前字段使用/隔开：">
   <input type="text" name="combination" class="form-control" required="required" data-required-msg="维度组合，程序计算出多个维度阶乘组合存入当前字段使用/隔开为必填项">
  </tags:form_group>
  <tags:form_group _title="库存数量：">
   <input type="text" name="combinationStockNum" class="form-control" required="required" data-required-msg="库存数量为必填项">
  </tags:form_group>
  <tags:form_group _title="已售数量：">
   <input type="text" name="combinationSellNum" class="form-control" required="required" data-required-msg="已售数量为必填项">
  </tags:form_group>
  <tags:form_group _title="：">
   <input type="text" name="combinationPrice" class="form-control" required="required" data-required-msg="为必填项">
  </tags:form_group>
  <tags:form_group _title="规格图片：">
   <input type="text" name="combinationImg" class="form-control" required="required" data-required-msg="规格图片为必填项">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="商品编号：">
   <input type="text" name="productId" class="form-control" required="required" data-required-msg="商品编号为必填项">
  </tags:form_group>
  <tags:form_group _title="维度组合，程序计算出多个维度阶乘组合存入当前字段使用/隔开：">
   <input type="text" name="combination" class="form-control" required="required" data-required-msg="维度组合，程序计算出多个维度阶乘组合存入当前字段使用/隔开为必填项">
  </tags:form_group>
  <tags:form_group _title="库存数量：">
   <input type="text" name="combinationStockNum" class="form-control" required="required" data-required-msg="库存数量为必填项">
  </tags:form_group>
  <tags:form_group _title="已售数量：">
   <input type="text" name="combinationSellNum" class="form-control" required="required" data-required-msg="已售数量为必填项">
  </tags:form_group>
  <tags:form_group _title="：">
   <input type="text" name="combinationPrice" class="form-control" required="required" data-required-msg="为必填项">
  </tags:form_group>
  <tags:form_group _title="规格图片：">
   <input type="text" name="combinationImg" class="form-control" required="required" data-required-msg="规格图片为必填项">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="组合编号：">
   <span name="id"></span>
  </tags:form_group>
  <tags:form_group _title="商品编号：">
   <span name="productId"></span>
  </tags:form_group>
  <tags:form_group _title="维度组合，程序计算出多个维度阶乘组合存入当前字段使用/隔开：">
   <span name="combination"></span>
  </tags:form_group>
  <tags:form_group _title="库存数量：">
   <span name="combinationStockNum"></span>
  </tags:form_group>
  <tags:form_group _title="已售数量：">
   <span name="combinationSellNum"></span>
  </tags:form_group>
  <tags:form_group _title="：">
   <span name="combinationPrice"></span>
  </tags:form_group>
  <tags:form_group _title="规格图片：">
   <span name="combinationImg"></span>
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
     {id:'id',title:'组合编号',columnClass:'text-center'}
    ,{id:'productId',title:'商品编号',columnClass:'text-center',hideType:'xs'}
    ,{id:'combination',title:'维度组合，程序计算出多个维度阶乘组合存入当前字段使用/隔开',columnClass:'text-center',hideType:'xs'}
    ,{id:'combinationStockNum',title:'库存数量',columnClass:'text-center',hideType:'xs'}
    ,{id:'combinationSellNum',title:'已售数量',columnClass:'text-center',hideType:'xs'}
    ,{id:'combinationPrice',title:'',columnClass:'text-center',hideType:'xs'}
    ,{id:'combinationImg',title:'规格图片',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
