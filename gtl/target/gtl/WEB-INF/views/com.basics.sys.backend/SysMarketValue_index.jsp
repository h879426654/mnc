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
  
//kendo格式化时间
function formatDate(value, record, column, grid, dataNo, columnNo) {
	if(null != value && "" != value ) {
		var dateString = new Date(value).format('yyyy-MM-dd');
		$('#'+column.id+'Name').html(dateString);
		return dateString;
	}
	return "";
}

//kendo格式化时间
function formatDate(value, record, column, grid, dataNo, columnNo) {
	if(null != value && "" != value ) {
		var dateString = new Date(value).format('yyyy-MM-dd');
		$('#'+column.id+'Name').html(dateString);
		record.rateTime = dateString
		return dateString;
	}
	return "";
}
  
 </script>
</head>
<body>
<tags:form_add_dialog>
  <tags:form_group _title="平台币与美元间的汇率：">
   <input type="text" name="dollarRate" class="form-control" required="required" data-required-msg="平台币与美元间的汇率为必填项">
  </tags:form_group>
  <tags:form_group _title="人民币转平台币：">
   <input type="text" name="rmbRate" class="form-control" required="required" data-required-msg="人民币转平台币为必填项">
  </tags:form_group>
  <tags:form_group _title="时间：">
   <input type="text" name="rateTime" data-role="datetimepicker" class="form-control" data-format="yyyy-MM-dd">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="平台币与美元间的汇率：">
   <input type="text" name="dollarRate" class="form-control" required="required" data-required-msg="平台币与美元间的汇率为必填项">
  </tags:form_group>
  <tags:form_group _title="人民币转平台币：">
   <input type="text" name="rmbRate" class="form-control" required="required" data-required-msg="人民币转平台币为必填项">
  </tags:form_group>
  <tags:form_group _title="时间：">
   <input type="text" name="rateTime" data-role="datetimepicker" class="form-control" data-format="yyyy-MM-dd">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="平台币与美元间的汇率：">
   <span name="dollarRate"></span>
  </tags:form_group>
  <tags:form_group _title="人民币转平台币：">
   <span name="rmbRate"></span>
  </tags:form_group>
  <tags:form_group _title="时间：">
   <span id="rateTimeName"></span>
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
     {id:'dollarRate',title:'平台币与美元间的汇率',columnClass:'text-center',hideType:'xs'}
    ,{id:'rmbRate',title:'人民币转平台币',columnClass:'text-center',hideType:'xs'}
    ,{id:'rateTime',title:'时间',columnClass:'text-center',hideType:'xs',resolution:formatDate}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
