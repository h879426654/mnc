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
  </div>
  <tags:swgrid _loadURL="showProductCommentById.do">
   [
     {id:'productName',title:'<spring:message code="product.comment.title.productName" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerName',title:'<spring:message code="product.comment.title.customerName" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'commentContext',title:'<spring:message code="product.comment.title.commentContext" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'commentType',title:'<spring:message code="product.comment.title.commentType" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'commentDescribeSart',title:'<spring:message code="product.comment.title.commentDescribeSart" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'commentServiceSart',title:'<spring:message code="product.comment.title.commentServiceSart" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'commentLogisticsSart',title:'<spring:message code="product.comment.title.commentLogisticsSart" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'flagAnonymous',title:'<spring:message code="product.comment.title.flagAnonymous" />',columnClass:'text-center',hideType:'xs',resolution:formatSatus}
    ,{id:'replyContext',title:'<spring:message code="product.comment.title.replyContext" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'replyTime',title:'<spring:message code="product.comment.title.replyTime" />',columnClass:'text-center',hideType:'xs',resolution:formatDate}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
