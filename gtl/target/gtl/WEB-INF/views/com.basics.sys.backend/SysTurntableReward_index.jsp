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
<tags:form_view_dialog>
  <tags:form_group _title="用户：">
   <span name="customerName"></span>
  </tags:form_group>
  <tags:form_group _title="奖励类型：">
   <span name="rewardTypeName"></span>
  </tags:form_group>
  <tags:form_group _title="奖励数目：">
   <span name="rewardNum"></span>
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
   <tags:form_grid_toolbar_view></tags:form_grid_toolbar_view>
  </div>
  <tags:swgrid _loadURL="selectTurntableRewardInfo.do">
   [
     {id:'customerName',title:'用户',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerPhone',title:'用户手机号',columnClass:'text-center',hideType:'xs'}
    ,{id:'rewardTypeName',title:'奖励类型',columnClass:'text-center',hideType:'xs'}
    ,{id:'rewardNum',title:'奖励数目',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
