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
  <tags:form_group _title="横幅标题：">
   <input type="text" name="bannerTitle" class="form-control" required="required" data-required-msg="横幅标题为必填项">
  </tags:form_group>
    <tags:form_group _title="国家：">
        <input type="text" name="countryId" id="country_add" data-role="dropdownlist" data-value-field="countryId" data-text-field="countryName" data-option-label="请选择"
               class="form-control" data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
  transport: {read: {type:'POST',dataType:'json',contentType:'application/json',url: '${contextPath}/backend/sys/sysCountry/read.do'}
  ,parameterMap:function(options){return JSON.stringify(options);}}}" >
    </tags:form_group>
  <tags:form_group _title="横幅图片地址：">
   <input type="hidden" id="imgAddUrl" name="bannerImage" class="form-control" required="required" data-required-msg="路径为必填项">
   <tags:bootstrap_uploader_image _multiple="" _id="imgAdd" _ownerClass="SysBanner.bannerImage" _ownerId="idAdd" _bindUrlId="imgAddUrl" />
  </tags:form_group>
  <tags:form_group _title="横幅链接：">
   <input type="text" name="bannerUrl" class="form-control" required="required" data-required-msg="横幅链接为必填项">
  </tags:form_group>
  <tags:form_group _title="类型：">
   <input type="text" name="bannerType" data-role="dropdownlist"
      data-value-field="code" data-text-field="name" class="form-control" 
      data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
     transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/BANNER_TYPE.do'}}}" >
  </tags:form_group>
  <tags:form_group _title="横幅权重：">
   <input type="text" name="bannerSort" class="form-control" required="required" data-required-msg="横幅权重为必填项">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog _multipart="true">
 <input type="hidden" name="id" id="idEdit"/>
  <tags:form_group _title="横幅标题：">
   <input type="text" name="bannerTitle" class="form-control" required="required" data-required-msg="横幅标题为必填项">
  </tags:form_group>
    <tags:form_group _title="国家：">
        <input type="text" name="countryId" id="country_add" data-role="dropdownlist" data-value-field="countryId" data-text-field="countryName" data-option-label="请选择"
               class="form-control" data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
  transport: {read: {type:'POST',dataType:'json',contentType:'application/json',url: '${contextPath}/backend/sys/sysCountry/read.do'}
  ,parameterMap:function(options){return JSON.stringify(options);}}}" >
    </tags:form_group>
  <tags:form_group _title="横幅图片地址：">
   <input type="text" name="bannerImage" id="imgEditUrl" class="form-control" required="required" data-required-msg="banner图路径为必填项">
   <tags:bootstrap_uploader_image _multiple="" _id="imgEdit" _ownerClass="SysBanner.bannerImage" _ownerId="idEdit" _bindUrlId="imgEditUrl" />
  </tags:form_group>
  <tags:form_group _title="横幅链接：">
   <input type="text" name="bannerUrl" class="form-control" required="required" data-required-msg="横幅链接为必填项">
  </tags:form_group>
  <tags:form_group _title="类型：">
   <input type="text" name="bannerType" data-role="dropdownlist"
      data-value-field="code" data-text-field="name" class="form-control" 
      data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
     transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/BANNER_TYPE.do'}}}" >
  </tags:form_group>
  <tags:form_group _title="横幅权重：">
   <input type="text" name="bannerSort" class="form-control" required="required" data-required-msg="横幅权重为必填项">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="横幅标题：">
   <span name="bannerTitle"></span>
  </tags:form_group>
  <tags:form_group _title="横幅图片地址：">
   <span name="bannerImage"></span>
  </tags:form_group>
  <tags:form_group _title="横幅链接：">
   <span name="bannerUrl"></span>
  </tags:form_group>
  <tags:form_group _title="横幅权重：">
   <span name="bannerSort"></span>
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
     {id:'bannerTitle',title:'横幅标题',columnClass:'text-center',hideType:'xs'}
    ,{id:'countryName',title:'国家',columnClass:'text-center',hideType:'xs'}
    ,{id:'bannerTypeName',title:'横幅类型',columnClass:'text-center',hideType:'xs'}
    ,{id:'bannerImage',title:'横幅图片地址',columnClass:'text-center',hideType:'xs',resolution:formatImg}
    ,{id:'bannerUrl',title:'横幅链接',columnClass:'text-center',hideType:'xs'}
    ,{id:'bannerSort',title:'横幅权重',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
