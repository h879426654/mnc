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
  
  var changeType = function(obj){
	  if(5==obj.value){
		  $('#contextDiv').hide();
		  $('#contextDiv2').show();
		  $('#contextEditDiv').hide();
		  $('#contextEditDiv2').show();
	  } else {
		 $('#contextDiv').show();
		 $('#contextDiv2').hide();
		 $('#contextEditDiv').show();
		 $('#contextEditDiv2').hide();
	  }
  }
  
  
 </script>
</head>
<body>
<tags:form_add_dialog>
  <tags:form_group _title="公告标题：">
   <input type="text" name="noticeTitle" class="form-control" required="required" data-required-msg="公告标题为必填项">
  </tags:form_group>
  <tags:form_group _title="公告类型：">
   <input type="text" name="bulletinType" data-role="dropdownlist"
      data-value-field="code" data-text-field="name" class="form-control" 
      data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
     transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/BULLETIN_TYPE.do'}}}" onchange="changeType(this)">
  </tags:form_group>
  <div id="contextDiv"  >
	  <tags:form_group _title="公告内容：">
	   <textarea style="width: 100%; height: 200px"  required-data-msg="请填写公告内容！" data-role="kindeditor"
	     class="kindeditor" name="noticeContext"></textarea>
	  </tags:form_group>
  </div>
  <div id="contextDiv2" style="display:none">
	  <tags:form_group _title="公告内容：">
	   <input type="text" name="bulletinTypeName" class="form-control"  data-required-msg="请填写公告内容:">
	  </tags:form_group>
  </div>
  <tags:form_group _title="权重：">
   <input type="text" name="noticeSort" class="form-control" required="required" data-required-msg="权重为必填项">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="公告标题：">
   <input type="text" name="noticeTitle" class="form-control" required="required" data-required-msg="公告标题为必填项">
  </tags:form_group>
  <tags:form_group _title="公告类型：">
   <input type="text" name="bulletinType" data-role="dropdownlist"
      data-value-field="code" data-text-field="name" class="form-control" 
      data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
     transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/BULLETIN_TYPE.do'}}}" onchange="changeType(this)">
  </tags:form_group>
  <div id="contextEditDiv">
	  <tags:form_group _title="公告内容：">
	   <textarea style="width: 100%; height: 200px" required="required" required-data-msg="请填写公告内容！" data-role="kindeditor"
	     class="kindeditor" name="noticeContext"></textarea>
	  </tags:form_group>
  </div>
  <div id="contextEditDiv2" style="display:none">
	  <tags:form_group _title="公告内容：">
	   <input type="text" name="bulletinTypeName" class="form-control" required="required" data-required-msg="请填写公告内容:">
	  </tags:form_group>
  </div>
  <tags:form_group _title="权重：">
   <input type="text" name="noticeSort" class="form-control" required="required" data-required-msg="权重为必填项">
  </tags:form_group>

</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="公告类型：">
   <span name="bulletinTypeName"></span>
  </tags:form_group>
  <tags:form_group _title="公告标题：">
   <span name="noticeTitle"></span>
  </tags:form_group>
  <tags:form_group _title="公告内容：">
   <span name="noticeContext"></span>
  </tags:form_group>
  <tags:form_group _title="权重：">
   <span name="noticeSort"></span>
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
   <tags:form_grid_toolbar>
   <button type="button" class="btn btn-default btn-sm label-danger  distance" onclick="CrudApp.on('delete')">
	  <span class="glyphicon glyphicon-remove"></span> 删除
	 </button>
   </tags:form_grid_toolbar>
  </div>
  <tags:swgrid>
   [
     {id:'bulletinTypeName',title:'公告类型',columnClass:'text-center',hideType:'xs'}
    ,{id:'noticeTitle',title:'公告标题',columnClass:'text-center',hideType:'xs'}
    ,{id:'noticeContext',title:'公告内容',columnClass:'text-center',hideType:'xs'}
    ,{id:'noticeSort',title:'权重',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
