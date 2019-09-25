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
  <tags:form_group _title="审核状态：">
   <input type="text" name="complaintStatus" data-role="dropdownlist"
      data-value-field="code" data-text-field="name" class="form-control" 
      data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
     transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/APPLY_STATUS.do'}}}">
  </tags:form_group>
  <tags:form_group _title="审核说明：">
   <input type="text" name="complaintRemark" class="form-control" required="required" data-required-msg="审核说明为必填项">
  </tags:form_group>
  
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="交易：">
   <span name="tradeNumber"></span>
  </tags:form_group>
  <tags:form_group _title="交易类型(1余额 2链)：">
   <span name="tradeType"></span>
  </tags:form_group>
  <tags:form_group _title="用户：">
   <span name="customerName"></span>
  </tags:form_group>
  <tags:form_group _title="投诉内容：">
   <span name="complaintContext"></span>
  </tags:form_group>
  <tags:form_group _title="投诉状态：">
   <span name="complaintStatus"></span>
  </tags:form_group>
  <tags:form_group _title="处理说明：">
   <span name="complaintRemark"></span>
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
   <tags:form_grid_toolbar_view>
   		
   </tags:form_grid_toolbar_view>
  </div>
  <tags:swgrid>
   [
     {id:'tradeNumber',title:'交易',columnClass:'text-center',hideType:'xs'}
    ,{id:'tradeType',title:'交易类型',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerName',title:'用户',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerPhone',title:'用户手机号',columnClass:'text-center',hideType:'xs'}
    ,{id:'complaintContext',title:'投诉内容',columnClass:'text-center',hideType:'xs'}
    ,{id:'complaintStatus',title:'投诉状态',columnClass:'text-center',hideType:'xs'}
    ,{id:'complaintRemark',title:'处理说明',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
