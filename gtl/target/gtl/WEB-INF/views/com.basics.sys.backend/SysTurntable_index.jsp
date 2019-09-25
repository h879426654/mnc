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
  <tags:form_group _title="奖励类型：">
   <input type="text" name="rewardType" data-role="dropdownlist"
      data-value-field="code" data-text-field="name" class="form-control" 
      data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
     transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/MONEY_TYPE.do'}}}">
  </tags:form_group>
  <tags:form_group _title="奖励数目：">
   <input type="text" name="rewardNum" class="form-control" required="required" data-required-msg="奖励数目为必填项">
  </tags:form_group>
  <tags:form_group _title="中奖比例：">
   <input type="text" name="rewardRate" class="form-control" required="required" data-required-msg="中奖比例为必填项">
  </tags:form_group>
  <tags:form_group _title="权重：">
   <input type="text" name="rewardSort" class="form-control" required="required" data-required-msg="权重为必填项">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="奖励类型：">
   <input type="text" name="rewardType" data-role="dropdownlist"
      data-value-field="code" data-text-field="name" class="form-control" 
      data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
     transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/MONEY_TYPE.do'}}}">
  </tags:form_group>
  <tags:form_group _title="奖励数目：">
   <input type="text" name="rewardNum" class="form-control" required="required" data-required-msg="奖励数目为必填项">
  </tags:form_group>
  <tags:form_group _title="中奖比例：">
   <input type="text" name="rewardRate" class="form-control" required="required" data-required-msg="中奖比例为必填项">
  </tags:form_group>
   <tags:form_group _title="权重：">
   <input type="text" name="rewardSort" class="form-control" required="required" data-required-msg="权重必填项">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="奖励类型：">
   <span name="rewardTypeName"></span>
  </tags:form_group>
  <tags:form_group _title="奖励数目：">
   <span name="rewardNum"></span>
  </tags:form_group>
  <tags:form_group _title="中奖比例：">
   <span name="rewardRate"></span>
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
     {id:'rewardTypeName',title:'奖励类型',columnClass:'text-center',hideType:'xs'}
    ,{id:'rewardNum',title:'奖励数目',columnClass:'text-center',hideType:'xs'}
    ,{id:'rewardRate',title:'中奖比例',columnClass:'text-center',hideType:'xs'}
    ,{id:'rewardSort',title:'排列顺序',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
