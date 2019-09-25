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
  <tags:form_group _title="消息ID：">
   <input type="text" name="id" class="form-control" required="required" data-required-msg="消息ID为必填项">
  </tags:form_group>
  <tags:form_group _title="会员ID：">
   <input type="text" name="customerId" class="form-control" required="required" data-required-msg="会员ID为必填项">
  </tags:form_group>
  <tags:form_group _title="消息名称：">
   <input type="text" name="activemqType" class="form-control" required="required" data-required-msg="消息名称为必填项">
  </tags:form_group>
  <tags:form_group _title="消息结果：">
   <input type="text" name="activemqResponse" class="form-control" required="required" data-required-msg="消息结果为必填项">
  </tags:form_group>
  <tags:form_group _title="完成时间：">
   <input type="text" name="createDate" class="form-control" required="required" data-required-msg="完成时间为必填项">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="会员ID：">
   <input type="text" name="customerId" class="form-control" required="required" data-required-msg="会员ID为必填项">
  </tags:form_group>
  <tags:form_group _title="消息名称：">
   <input type="text" name="activemqType" class="form-control" required="required" data-required-msg="消息名称为必填项">
  </tags:form_group>
  <tags:form_group _title="消息结果：">
   <input type="text" name="activemqResponse" class="form-control" required="required" data-required-msg="消息结果为必填项">
  </tags:form_group>
  <tags:form_group _title="完成时间：">
   <input type="text" name="createDate" class="form-control" required="required" data-required-msg="完成时间为必填项">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="消息ID：">
   <span name="id"></span>
  </tags:form_group>
  <tags:form_group _title="会员ID：">
   <span name="customerId"></span>
  </tags:form_group>
  <tags:form_group _title="消息名称：">
   <span name="activemqType"></span>
  </tags:form_group>
  <tags:form_group _title="消息结果：">
   <span name="activemqResponse"></span>
  </tags:form_group>
  <tags:form_group _title="完成时间：">
   <span name="createDate"></span>
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
     {id:'id',title:'消息ID',columnClass:'text-center'}
    ,{id:'customerId',title:'会员ID',columnClass:'text-center',hideType:'xs'}
    ,{id:'activemqType',title:'消息名称',columnClass:'text-center',hideType:'xs'}
    ,{id:'activemqResponse',title:'消息结果',columnClass:'text-center',hideType:'xs'}
    ,{id:'createDate',title:'完成时间',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
