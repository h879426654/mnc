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
  <tags:form_group _title="国家CODE：">
   <input type="text" name="countryCode" class="form-control" required="required" data-required-msg="国家CODE为必填项">
  </tags:form_group>
  <tags:form_group _title="电话代码：">
   <input type="text" name="countryNum" class="form-control" required="required" data-required-msg="国家CODE为必填项">
  </tags:form_group>
  <tags:form_group _title="国家名字：">
   <input type="text" name="countryName" class="form-control" required="required" data-required-msg="国家名字为必填项">
  </tags:form_group>
  <tags:form_group _title="国家汇率：">
   <input type="text" name="countryRate" class="form-control" required="required" data-required-msg="国家汇率为必填项">
  </tags:form_group>
  <tags:form_group _title="货币符号：">
   <input type="text" name="countrySymbol" class="form-control" required="required" data-required-msg="货币符号为必填项">
  </tags:form_group>
  <tags:form_group _title="权重：">
   <input type="text" name="countrySort" class="form-control" required="required" data-required-msg="权重为必填项">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="国家CODE：">
   <input type="text" name="countryCode" class="form-control" required="required" data-required-msg="国家CODE为必填项">
  </tags:form_group>
  <tags:form_group _title="电话代码：">
   <input type="text" name="countryNum" class="form-control" required="required" data-required-msg="国家CODE为必填项">
  </tags:form_group>
  <tags:form_group _title="国家名字：">
   <input type="text" name="countryName" class="form-control" required="required" data-required-msg="国家名字为必填项">
  </tags:form_group>
  <tags:form_group _title="国家汇率：">
   <input type="text" name="countryRate" class="form-control" required="required" data-required-msg="国家汇率为必填项">
  </tags:form_group>
  <tags:form_group _title="货币符号：">
   <input type="text" name="countrySymbol" class="form-control" required="required" data-required-msg="货币符号为必填项">
  </tags:form_group>
  <tags:form_group _title="权重：">
   <input type="text" name="countrySort" class="form-control" required="required" data-required-msg="权重为必填项">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="国家ID：">
   <span name="id"></span>
  </tags:form_group>
  <tags:form_group _title="国家CODE：">
   <span name="countryCode"></span>
  </tags:form_group>
  <tags:form_group _title="国家名字：">
   <span name="countryName"></span>
  </tags:form_group>
  <tags:form_group _title="国家汇率：">
   <span name="countryRate"></span>
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
     {id:'countryCode',title:'国家CODE',columnClass:'text-center',hideType:'xs'}
    ,{id:'countryNum',title:'电话代码',columnClass:'text-center',hideType:'xs'}
    ,{id:'countryName',title:'国家名字',columnClass:'text-center',hideType:'xs'}
    ,{id:'countryRate',title:'国家汇率',columnClass:'text-center',hideType:'xs'}
    ,{id:'countrySymbol',title:'货币符号',columnClass:'text-center',hideType:'xs'}
    ,{id:'countrySort',title:'权重',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
