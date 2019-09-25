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
  <tags:form_group _title="首页模块：">
   <input type="text" name="typeId" data-role="dropdownlist" data-value-field="id" data-text-field="typeTitle" data-option-label="请选择"
    class="form-control" data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
   transport: {read: {type:'POST',dataType:'json',contentType:'application/json',url: '${contextPath}/backend/mall/mallIndexType/read.do'}
   ,parameterMap:function(options){return JSON.stringify(options);}}}">
  </tags:form_group>
    <tags:form_group _title="商品一级分类:">
        <input type="text" id="productFirstClassify_add" name="productFirstClassify" data-role="dropdownlist" data-value-field="productFirstClassify" data-text-field="classifyName"
               data-option-label="请选择" class="form-control" data-auto-bind="false" style="width:350px"
               data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
		     transport: {read: {type:'POST',contentType:'application/json',url: '${contextPath}/backend/mall/mallProductClassify/firstClassify.do'}}}">
    </tags:form_group>
    <tags:form_group _title="商品二级分类:">
        <input type="text" id="productSecondClassify_add" name="productSecondClassify" data-role="dropdownlist" data-value-field="id" data-text-field="classifyName"
               data-option-label="请选择" data-cascade-from="productFirstClassify_add" class="form-control" data-auto-bind="false" style="width:350px"
               data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
		   transport: {read: {type:'POST',dataType:'json',contentType:'application/json',url: '${contextPath}/backend/mall/mallProductClassify/secondClassify.do'}
		   ,parameterMap:function(options){return JSON.stringify(options);}
		   }}">
    </tags:form_group>
  <tags:form_group _title="商品：">
   <input type="text" name="productId" data-role="dropdownlist" data-value-field="id" data-text-field="productName" data-option-label="请选择"
          data-cascade-from="productSecondClassify_add" class="form-control" data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
   transport: {read: {type:'POST',dataType:'json',contentType:'application/json',url: '${contextPath}/backend/mall/mallProduct/getProducts.do'}
   ,parameterMap:function(options){return JSON.stringify(options);}}}">
  </tags:form_group>
  <tags:form_group _title="权重：">
   <input type="text" name="indexSort" class="form-control" required="required" data-required-msg="权重为必填项">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="首页模块：">
   <input type="text" name="typeId" data-role="dropdownlist" data-value-field="id" data-text-field="typeTitle" data-option-label="请选择"
    class="form-control" data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
   transport: {read: {type:'POST',dataType:'json',contentType:'application/json',url: '${contextPath}/backend/mall/mallIndexType/read.do'}
   ,parameterMap:function(options){return JSON.stringify(options);}}}">
  </tags:form_group>
    <tags:form_group _title="商品一级分类:">
        <input type="text" id="productFirstClassify_add2" name="productFirstClassify" data-role="dropdownlist" data-value-field="productFirstClassify" data-text-field="classifyName"
               data-option-label="请选择" class="form-control" data-auto-bind="false" style="width:350px"
               data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
		     transport: {read: {type:'POST',contentType:'application/json',url: '${contextPath}/backend/mall/mallProductClassify/firstClassify.do'}}}">
    </tags:form_group>
    <tags:form_group _title="商品二级分类:">
        <input type="text" id="productSecondClassify_add2" name="productSecondClassify" data-role="dropdownlist" data-value-field="id" data-text-field="classifyName"
               data-option-label="请选择" data-cascade-from="productFirstClassify_add2" class="form-control" data-auto-bind="false" style="width:350px"
               data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
		   transport: {read: {type:'POST',dataType:'json',contentType:'application/json',url: '${contextPath}/backend/mall/mallProductClassify/secondClassify.do'}
		   ,parameterMap:function(options){return JSON.stringify(options);}
		   }}">
    </tags:form_group>
    <tags:form_group _title="商品：">
        <input type="text" name="productId" data-role="dropdownlist" data-value-field="id" data-text-field="productName" data-option-label="请选择"
               data-cascade-from="productSecondClassify_add2" class="form-control" data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
   transport: {read: {type:'POST',dataType:'json',contentType:'application/json',url: '${contextPath}/backend/mall/mallProduct/getProducts.do'}
   ,parameterMap:function(options){return JSON.stringify(options);}}}">
    </tags:form_group>
  <tags:form_group _title="权重：">
   <input type="text" name="indexSort" class="form-control" required="required" data-required-msg="权重为必填项">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="首页模块：">
     <span name="typeTitle"></span>
  </tags:form_group>
  <tags:form_group _title="模块Banner：">
     <img name="indexImg" width='100%'/>
  </tags:form_group>
  <tags:form_group _title="商品：">
     <span name="productName"></span>
  </tags:form_group>
  <tags:form_group _title="商品图片：">
     <img name="productImg" width='100%'/>
  </tags:form_group>
  <tags:form_group _title="权重：">
     <span name="indexSort"></span>
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
     {id:'typeTitle',title:'首页模块',columnClass:'text-center',hideType:'xs'}
    ,{id:'indexImg',title:'模块Banner',columnClass:'text-center',hideType:'xs',resolution:formatImg}
    ,{id:'productImg',title:'商品图片',columnClass:'text-center',hideType:'xs',resolution:formatImg}
    ,{id:'indexSort',title:'权重',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
