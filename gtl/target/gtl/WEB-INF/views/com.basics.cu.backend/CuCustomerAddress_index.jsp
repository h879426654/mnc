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
  <tags:form_group _title="地址ID：">
   <input type="text" name="id" class="form-control" required="required" data-required-msg="地址ID为必填项">
  </tags:form_group>
  <tags:form_group _title="客户ID：">
   <input type="text" name="customerId" class="form-control" required="required" data-required-msg="客户ID为必填项">
  </tags:form_group>
  <tags:form_group _title="联系电话：">
   <input type="text" name="addressPhone" class="form-control" required="required" data-required-msg="联系电话为必填项">
  </tags:form_group>
  <tags:form_group _title="联系姓名：">
   <input type="text" name="addressName" class="form-control" required="required" data-required-msg="联系姓名为必填项">
  </tags:form_group>
  <tags:form_group _title="省ID：">
   <input type="text" name="addressProvince" class="form-control" required="required" data-required-msg="省ID为必填项">
  </tags:form_group>
  <tags:form_group _title="市ID：">
   <input type="text" name="addressCity" class="form-control" required="required" data-required-msg="市ID为必填项">
  </tags:form_group>
  <tags:form_group _title="区域ID：">
   <input type="text" name="addressArea" class="form-control" required="required" data-required-msg="区域ID为必填项">
  </tags:form_group>
  <tags:form_group _title="详细地址：">
   <input type="text" name="addressInfo" class="form-control" required="required" data-required-msg="详细地址为必填项">
  </tags:form_group>
  <tags:form_group _title="是否为默认地址(1是 0否)：">
   <input type="text" name="addressFlag" class="form-control" required="required" data-required-msg="是否为默认地址(1是 0否)为必填项">
  </tags:form_group>
  <tags:form_group _title="是否删除（1是 0否）：">
   <input type="text" name="flagDel" class="form-control" required="required" data-required-msg="是否删除（1是 0否）为必填项">
  </tags:form_group>
  <tags:form_group _title="创建时间：">
   <input type="text" name="createTime" class="form-control" required="required" data-required-msg="创建时间为必填项">
  </tags:form_group>
  <tags:form_group _title="创建人：">
   <input type="text" name="createUser" class="form-control" required="required" data-required-msg="创建人为必填项">
  </tags:form_group>
  <tags:form_group _title="修改人：">
   <input type="text" name="modifyUser" class="form-control" required="required" data-required-msg="修改人为必填项">
  </tags:form_group>
  <tags:form_group _title="修改时间：">
   <input type="text" name="modifyDate" class="form-control" required="required" data-required-msg="修改时间为必填项">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="客户ID：">
   <input type="text" name="customerId" class="form-control" required="required" data-required-msg="客户ID为必填项">
  </tags:form_group>
  <tags:form_group _title="联系电话：">
   <input type="text" name="addressPhone" class="form-control" required="required" data-required-msg="联系电话为必填项">
  </tags:form_group>
  <tags:form_group _title="联系姓名：">
   <input type="text" name="addressName" class="form-control" required="required" data-required-msg="联系姓名为必填项">
  </tags:form_group>
  <tags:form_group _title="省ID：">
   <input type="text" name="addressProvince" class="form-control" required="required" data-required-msg="省ID为必填项">
  </tags:form_group>
  <tags:form_group _title="市ID：">
   <input type="text" name="addressCity" class="form-control" required="required" data-required-msg="市ID为必填项">
  </tags:form_group>
  <tags:form_group _title="区域ID：">
   <input type="text" name="addressArea" class="form-control" required="required" data-required-msg="区域ID为必填项">
  </tags:form_group>
  <tags:form_group _title="详细地址：">
   <input type="text" name="addressInfo" class="form-control" required="required" data-required-msg="详细地址为必填项">
  </tags:form_group>
  <tags:form_group _title="是否为默认地址(1是 0否)：">
   <input type="text" name="addressFlag" class="form-control" required="required" data-required-msg="是否为默认地址(1是 0否)为必填项">
  </tags:form_group>
  <tags:form_group _title="是否删除（1是 0否）：">
   <input type="text" name="flagDel" class="form-control" required="required" data-required-msg="是否删除（1是 0否）为必填项">
  </tags:form_group>
  <tags:form_group _title="创建时间：">
   <input type="text" name="createTime" class="form-control" required="required" data-required-msg="创建时间为必填项">
  </tags:form_group>
  <tags:form_group _title="创建人：">
   <input type="text" name="createUser" class="form-control" required="required" data-required-msg="创建人为必填项">
  </tags:form_group>
  <tags:form_group _title="修改人：">
   <input type="text" name="modifyUser" class="form-control" required="required" data-required-msg="修改人为必填项">
  </tags:form_group>
  <tags:form_group _title="修改时间：">
   <input type="text" name="modifyDate" class="form-control" required="required" data-required-msg="修改时间为必填项">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="地址ID：">
   <span name="id"></span>
  </tags:form_group>
  <tags:form_group _title="客户ID：">
   <span name="customerId"></span>
  </tags:form_group>
  <tags:form_group _title="联系电话：">
   <span name="addressPhone"></span>
  </tags:form_group>
  <tags:form_group _title="联系姓名：">
   <span name="addressName"></span>
  </tags:form_group>
  <tags:form_group _title="省ID：">
   <span name="addressProvince"></span>
  </tags:form_group>
  <tags:form_group _title="市ID：">
   <span name="addressCity"></span>
  </tags:form_group>
  <tags:form_group _title="区域ID：">
   <span name="addressArea"></span>
  </tags:form_group>
  <tags:form_group _title="详细地址：">
   <span name="addressInfo"></span>
  </tags:form_group>
  <tags:form_group _title="是否为默认地址(1是 0否)：">
   <span name="addressFlag"></span>
  </tags:form_group>
  <tags:form_group _title="是否删除（1是 0否）：">
   <span name="flagDel"></span>
  </tags:form_group>
  <tags:form_group _title="创建时间：">
   <span name="createTime"></span>
  </tags:form_group>
  <tags:form_group _title="创建人：">
   <span name="createUser"></span>
  </tags:form_group>
  <tags:form_group _title="修改人：">
   <span name="modifyUser"></span>
  </tags:form_group>
  <tags:form_group _title="修改时间：">
   <span name="modifyDate"></span>
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
     {id:'id',title:'地址ID',columnClass:'text-center'}
    ,{id:'customerId',title:'客户ID',columnClass:'text-center',hideType:'xs'}
    ,{id:'addressPhone',title:'联系电话',columnClass:'text-center',hideType:'xs'}
    ,{id:'addressName',title:'联系姓名',columnClass:'text-center',hideType:'xs'}
    ,{id:'addressProvince',title:'省ID',columnClass:'text-center',hideType:'xs'}
    ,{id:'addressCity',title:'市ID',columnClass:'text-center',hideType:'xs'}
    ,{id:'addressArea',title:'区域ID',columnClass:'text-center',hideType:'xs'}
    ,{id:'addressInfo',title:'详细地址',columnClass:'text-center',hideType:'xs'}
    ,{id:'addressFlag',title:'是否为默认地址(1是 0否)',columnClass:'text-center',hideType:'xs'}
    ,{id:'flagDel',title:'是否删除（1是 0否）',columnClass:'text-center',hideType:'xs'}
    ,{id:'createTime',title:'创建时间',columnClass:'text-center',hideType:'xs'}
    ,{id:'createUser',title:'创建人',columnClass:'text-center',hideType:'xs'}
    ,{id:'modifyUser',title:'修改人',columnClass:'text-center',hideType:'xs'}
    ,{id:'modifyDate',title:'修改时间',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
