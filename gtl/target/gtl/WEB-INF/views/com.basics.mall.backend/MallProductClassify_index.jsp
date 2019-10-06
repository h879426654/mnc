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
      kendo.bind($("#operationProductGrid"), {});
      kendo.bind($("#modifyProductDialog"), {});
  });
  function formatModify(value, record) {
      return '<a onclick="formatOnclickModify(\''+record['id']+'\',\''+record['classifyName']+'\',\''+record['classifySort']+'\')">修改<a>';
  }
  function formatOnclickModify(id,name,srot) {
      var row={id:id,classifyName:name,classifySort:srot};
      $("#modifyProductForm").form('load', row);
      onDialogOpen('modifyProductDialog');
  }
  function modifyProductFun() {
      $.post("save.do",$('#modifyProductForm').serialize(),function(result){
          result = $.parseJSON(result);
          if (result.success){
              CrudApp.success('操作成功!');
              window.location.reload();
          }else {
              CrudApp.alert(result.message);
          }
      });
  }
 </script>
</head>
<body>
<tags:form_view_dialog_diy _id="operationProductDialog">
    <tags:swgrid_diy _grid="operationProductGrid" _loadURL="getSecondClassify.do" _check="false">
        [
        {id:'classifyName',title:'分类名称',columnClass:'text-center',hideType:'xs'}
        ,{id:'classifyParentName',title:'父级分类名称',columnClass:'text-center',hideType:'xs'}
        ,{id:'classifySort',title:'权重',columnClass:'text-center',hideType:'xs'}
        ,{title:'  ',columnClass:'text-center',hideType:'xs',resolution:formatModify}
        ]</tags:swgrid_diy>
</tags:form_view_dialog_diy>

<tags:form_dialog _name="modifyProduct"  _title="修改" _onsave="modifyProductFun">
    <input type="hidden" name="id"/>
    <tags:form_group _title="分类名称：">
        <input type="text" name="classifyName" class="form-control" required="required" data-required-msg="分类名称为必填项">
    </tags:form_group>
    <tags:form_group _title="权重：">
        <input type="text" name="classifySort" class="form-control" required="required" data-required-msg="权重为必填项">
    </tags:form_group>
</tags:form_dialog>

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
   <tags:bootstrap_uploader_image _multiple="" _id="imgAdd" _ownerClass="MallGoodsClassify.classifyImg" _ownerId="idAdd" _bindUrlId="imgAddUrl" />
  </tags:form_group>
  <tags:form_group _title="父级分类名称：">
   <input type="text" name="classifyParentId" id="classifyParentCheckBox" data-role="dropdownlist" data-value-field="id" data-text-field="classifyName"
          data-option-label="请选择" data-cascade-from="country_add"
    class="form-control" data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
   transport: {read: {type:'POST',dataType:'json',contentType:'application/json',url: '${contextPath}/backend/mall/mallProductClassify/readFirst.do'}
   ,parameterMap:function(options){return JSON.stringify(options);}}}" >
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
   <tags:bootstrap_uploader_image _multiple="" _id="imgEdit" _ownerClass="MallGoodsClassify.classifyImg" _ownerId="idEdit" _bindUrlId="imgEditUrl" />
  </tags:form_group>
  <tags:form_group _title="权重：">
   <input type="text" name="classifySort" class="form-control" required="required" data-required-msg="权重为必填项">
  </tags:form_group>
</tags:form_edit_dialog>

<tags:form_view_dialog>
  <tags:form_group _title="分类名称：">
   <span name="classifyName"></span>
  </tags:form_group>
  <tags:form_group _title="父级分类名称：">
   <span name="classifyParentName"></span>
  </tags:form_group>
  <tags:form_group _title="分类图：">
   <img name="classifyImg" width='100%'></img>
  </tags:form_group>
  <tags:form_group _title="权重：">
   <span name="classifySort"></span>
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
  <tags:swgrid _loadURL="getFirstClassify.do">
   [
     {id:'classifyName',title:'分类名称',columnClass:'text-center',hideType:'xs'}
    ,{id:'countryName',title:'国家',columnClass:'text-center',hideType:'xs'}
    ,{id:'classifyImg',title:'分类图',columnClass:'text-center',hideType:'xs',resolution:formatImg}
    ,{id:'classifySort',title:'权重',columnClass:'text-center',hideType:'xs'}
    ,{id:'',title:'操作',columnClass:'text-center',hideType:'xs',resolution:formatOperation}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
 <!-- ,{id:'classifyParentName',title:'父级分类名称',columnClass:'text-center',hideType:'xs'} -->
</section>
<script>
    function formatOperation(value,record) {
        var id=record['id'];
        var content = '';
        content +='<a href="javascript:void(0);" onclick="operationProduct(\''+id+'\')">';
        content += '二级分类';
        content += '</a>';
        return content;
    }
    function operationProduct(id){
        var rows={classifyParentId:id};
        onDialogOpen('operationProductDialog');
        $("#operationProductGrid").data('kendoSwGrid').load(rows);
    }
    //打开窗口
    function onDialogOpen(dialogId) {
        $('#' + dialogId).modal({
            backdrop : 'static',
            keyboard : false
        });
    }
    function formatImg(value, record) {
        var content = '';
        content += '<img width="50" src="';
        content += value;
        content += '\">';
        return content;
    }
</script>
</body>
</html>
