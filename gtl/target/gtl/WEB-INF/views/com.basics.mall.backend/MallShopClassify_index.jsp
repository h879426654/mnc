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
   },
   onAddToGenId : true
  };

  $(function() {
   kendo.bind($("#grid"), {});
   CrudApp.init(options);
   CrudApp.on('search');
  });
  
  function formatImg(value, record, column, grid, dataNo, columnNo) {
      var content = '';
      content += '<img width="100" src="';
       content += value;
       content += '\">';
      return content;
  }
 </script>
</head>
<body>
<tags:form_add_dialog _multipart="true">
  <input type="hidden" name="id" id="idAdd" />
  <tags:form_group _title="分类名称：">
   <input type="text" name="classifyName" class="form-control" required="required" data-required-msg="分类名称为必填项">
  </tags:form_group>
    <tags:form_group _title="国家：">
        <input type="text" name="countryId" id="country_add" data-role="dropdownlist" data-value-field="countryId" data-text-field="countryName" data-option-label="请选择"
               class="form-control" data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
  transport: {read: {type:'POST',dataType:'json',contentType:'application/json',url: '${contextPath}/backend/sys/sysCountry/read.do'}
  ,parameterMap:function(options){return JSON.stringify(options);}}}" >
    </tags:form_group>
  <tags:form_group _title="分类图片：">
   <input type="hidden" id="imgAddUrl" name="classifyImg" class="form-control" required="required" data-required-msg="分类图片路径为必填项">
   <tags:bootstrap_uploader_image _multiple="" _id="imgAdd" _ownerClass="BuBusinessClassify.classifyImg" _ownerId="idAdd" _bindUrlId="imgAddUrl" />
  </tags:form_group>
  <tags:form_group _title="权重：">
   <input type="text" name="classifySort" class="form-control" required="required" data-required-msg="权重为必填项">
  </tags:form_group>
</tags:form_add_dialog>

<tags:form_edit_dialog _multipart="true">
 <input type="hidden" name="id" id="idEdit"/>
  <tags:form_group _title="分类名称：">
   <input type="text" name="classifyName" class="form-control" required="required" data-required-msg="分类名称为必填项">
  </tags:form_group>
    <tags:form_group _title="国家：">
        <input type="text" name="countryName" class="form-control" required="required" readonly>
    </tags:form_group>
  <tags:form_group _title="分类图片：">
   <input type="text" name="classifyImg" id="imgEditUrl" class="form-control" required="required" data-required-msg="分类图片路径为必填项">
   <tags:bootstrap_uploader_image _multiple="" _id="imgEdit" _ownerClass="BuBusinessClassify.classifyImg" _ownerId="idEdit" _bindUrlId="imgEditUrl" />
  </tags:form_group>
  <tags:form_group _title="权重：">
   <input type="text" name="classifySort" class="form-control" required="required" data-required-msg="权重为必填项">
  </tags:form_group>
</tags:form_edit_dialog>

<tags:form_view_dialog>
  <tags:form_group _title="分类名称：">
   <span name="classifyName"></span>
  </tags:form_group>
  <tags:form_group _title="分类图：">
   <img name="classifyImg" width="100%"></img>
  </tags:form_group>
  <tags:form_group _title="权重：">
   <span name="classifySort"></span>
  </tags:form_group>
  <tags:form_group _title="是否删除：">
   <span name="flagDelName"></span>
  </tags:form_group>
</tags:form_view_dialog>
<section class="content-header">
 <h1>${_urlMenuComment}</h1>
</section>
<section class="content">
 <tags:form_search>
  <div class="col-md-6">
      <tags:form_group _title="国家：">
          <input type="text" name="countryId" id="country_add" data-role="dropdownlist" data-value-field="countryId" data-text-field="countryName" data-option-label="请选择"
                 class="form-control" data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
  transport: {read: {type:'POST',dataType:'json',contentType:'application/json',url: '${contextPath}/backend/sys/sysCountry/read.do'}
  ,parameterMap:function(options){return JSON.stringify(options);}}}" >
      </tags:form_group>
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
     {id:'classifyName',title:'分类名称',columnClass:'text-center',hideType:'xs'}
    ,{id:'countryName',title:'国家',columnClass:'text-center',hideType:'xs'}
    ,{id:'classifyImg',title:'分类图',columnClass:'text-center',hideType:'xs',resolution:formatImg}
    ,{id:'classifySort',title:'权重',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
