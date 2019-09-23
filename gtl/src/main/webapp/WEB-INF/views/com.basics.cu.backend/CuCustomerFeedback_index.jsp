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
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="反馈类型：">
   <input type="text" name="feedbackTypeName"  readonly="readonly">
  </tags:form_group>
  <tags:form_group _title="反馈内容：">
   <input type="text" name="feedbackContext" class="form-control" required="required" data-required-msg="反馈内容为必填项" readonly="readonly">
  </tags:form_group>
  <tags:form_group _title="反馈状态：">
   <input type="text" name="feedbackStatus" data-role="dropdownlist"
      data-value-field="code" data-text-field="name" class="form-control" 
      data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
     transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/APPLY_STATUS.do'}}}">
  </tags:form_group>
  <tags:form_group _title="反馈处理说明：">
   <input type="text" name="feedbackRemark" class="form-control" required="required" data-required-msg="反馈处理说明为必填项">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="反馈ID：">
   <span name="id"></span>
  </tags:form_group>
  <tags:form_group _title="反馈类型：">
   <span name="feedbackTypeName"></span>
  </tags:form_group>
  <tags:form_group _title="反馈用户：">
   <span name="customerPhone"></span>
  </tags:form_group>
  <tags:form_group _title="反馈内容：">
   <span name="feedbackContext"></span>
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
  <tags:swgrid _loadURL="selectFeedbackInfo.do">
   [
     {id:'feedbackTypeName',title:'反馈类型',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerName',title:'反馈用户',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerPhone',title:'反馈用户手机号',columnClass:'text-center',hideType:'xs'}
    ,{id:'feedbackContext',title:'反馈内容',columnClass:'text-center',hideType:'xs'}
    ,{id:'feedbackStatus',title:'反馈状态',columnClass:'text-center',hideType:'xs', resolution:formatApplyStatus}
    ,{id:'feedbackRemark',title:'反馈处理说明',columnClass:'text-center',hideType:'xs'}
    ,{id:'createTime',title:'反馈时间',columnClass:'text-center',hideType:'xs', resolution:formatDate}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
