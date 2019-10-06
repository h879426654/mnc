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
  <tags:form_group _title="用户ID：">
   <input type="text" name="id" class="form-control" required="required" data-required-msg="用户ID为必填项">
  </tags:form_group>
  <tags:form_group _title="用户密码：">
   <input type="text" name="customerPassword" class="form-control" required="required" data-required-msg="用户密码为必填项">
  </tags:form_group>
  <tags:form_group _title="密码盐值：">
   <input type="text" name="passSalt" class="form-control" required="required" data-required-msg="密码盐值为必填项">
  </tags:form_group>
  <tags:form_group _title="用户手机号：">
   <input type="text" name="customerPhone" class="form-control" required="required" data-required-msg="用户手机号为必填项">
  </tags:form_group>
  <tags:form_group _title="是否实名认证(1是 0否)：">
   <input type="text" name="flagAuth" class="form-control" required="required" data-required-msg="是否实名认证(1是 0否)为必填项">
  </tags:form_group>
  <tags:form_group _title="用户状态 0代表冻结 1代表正常：">
   <input type="text" name="customerStatus" class="form-control" required="required" data-required-msg="用户状态 0代表冻结 1代表正常为必填项">
  </tags:form_group>
  <tags:form_group _title="登录错误次数：">
   <input type="text" name="loginErrorNum" class="form-control" required="required" data-required-msg="登录错误次数为必填项">
  </tags:form_group>
  <tags:form_group _title="最后登录时间：">
   <input type="text" name="lastLoginTime" class="form-control" required="required" data-required-msg="最后登录时间为必填项">
  </tags:form_group>
  <tags:form_group _title="版本号：">
   <input type="text" name="versionNum" class="form-control" required="required" data-required-msg="版本号为必填项">
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
   <input type="text" name="modifyTime" class="form-control" required="required" data-required-msg="修改时间为必填项">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="用户密码：">
   <input type="text" name="customerPassword" class="form-control" required="required" data-required-msg="用户密码为必填项">
  </tags:form_group>
  <tags:form_group _title="密码盐值：">
   <input type="text" name="passSalt" class="form-control" required="required" data-required-msg="密码盐值为必填项">
  </tags:form_group>
  <tags:form_group _title="用户手机号：">
   <input type="text" name="customerPhone" class="form-control" required="required" data-required-msg="用户手机号为必填项">
  </tags:form_group>
  <tags:form_group _title="是否实名认证(1是 0否)：">
   <input type="text" name="flagAuth" class="form-control" required="required" data-required-msg="是否实名认证(1是 0否)为必填项">
  </tags:form_group>
  <tags:form_group _title="用户状态 0代表冻结 1代表正常：">
   <input type="text" name="customerStatus" class="form-control" required="required" data-required-msg="用户状态 0代表冻结 1代表正常为必填项">
  </tags:form_group>
  <tags:form_group _title="登录错误次数：">
   <input type="text" name="loginErrorNum" class="form-control" required="required" data-required-msg="登录错误次数为必填项">
  </tags:form_group>
  <tags:form_group _title="最后登录时间：">
   <input type="text" name="lastLoginTime" class="form-control" required="required" data-required-msg="最后登录时间为必填项">
  </tags:form_group>
  <tags:form_group _title="版本号：">
   <input type="text" name="versionNum" class="form-control" required="required" data-required-msg="版本号为必填项">
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
   <input type="text" name="modifyTime" class="form-control" required="required" data-required-msg="修改时间为必填项">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="用户ID：">
   <span name="id"></span>
  </tags:form_group>
  <tags:form_group _title="用户密码：">
   <span name="customerPassword"></span>
  </tags:form_group>
  <tags:form_group _title="密码盐值：">
   <span name="passSalt"></span>
  </tags:form_group>
  <tags:form_group _title="用户手机号：">
   <span name="customerPhone"></span>
  </tags:form_group>
  <tags:form_group _title="是否实名认证(1是 0否)：">
   <span name="flagAuth"></span>
  </tags:form_group>
  <tags:form_group _title="用户状态 0代表冻结 1代表正常：">
   <span name="customerStatus"></span>
  </tags:form_group>
  <tags:form_group _title="登录错误次数：">
   <span name="loginErrorNum"></span>
  </tags:form_group>
  <tags:form_group _title="最后登录时间：">
   <span name="lastLoginTime"></span>
  </tags:form_group>
  <tags:form_group _title="版本号：">
   <span name="versionNum"></span>
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
   <span name="modifyTime"></span>
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
     {id:'id',title:'用户ID',columnClass:'text-center'}
    ,{id:'customerPassword',title:'用户密码',columnClass:'text-center',hideType:'xs'}
    ,{id:'passSalt',title:'密码盐值',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerPhone',title:'用户手机号',columnClass:'text-center',hideType:'xs'}
    ,{id:'flagAuth',title:'是否实名认证(1是 0否)',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerStatus',title:'用户状态 0代表冻结 1代表正常',columnClass:'text-center',hideType:'xs'}
    ,{id:'loginErrorNum',title:'登录错误次数',columnClass:'text-center',hideType:'xs'}
    ,{id:'lastLoginTime',title:'最后登录时间',columnClass:'text-center',hideType:'xs'}
    ,{id:'versionNum',title:'版本号',columnClass:'text-center',hideType:'xs'}
    ,{id:'flagDel',title:'是否删除（1是 0否）',columnClass:'text-center',hideType:'xs'}
    ,{id:'createTime',title:'创建时间',columnClass:'text-center',hideType:'xs'}
    ,{id:'createUser',title:'创建人',columnClass:'text-center',hideType:'xs'}
    ,{id:'modifyUser',title:'修改人',columnClass:'text-center',hideType:'xs'}
    ,{id:'modifyTime',title:'修改时间',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
