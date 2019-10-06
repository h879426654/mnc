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
  <tags:form_group _title="短信ID：">
   <input type="text" name="id" class="form-control" required="required" data-required-msg="短信ID为必填项">
  </tags:form_group>
  <tags:form_group _title="验证码类型1:注册 2:忘记密码 3修改密码：">
   <input type="text" name="codeType" class="form-control" required="required" data-required-msg="验证码类型1:注册 2:忘记密码 3修改密码为必填项">
  </tags:form_group>
  <tags:form_group _title="手机号：">
   <input type="text" name="codeMobile" class="form-control" required="required" data-required-msg="手机号为必填项">
  </tags:form_group>
  <tags:form_group _title="短信验证码：">
   <input type="text" name="codeCode" class="form-control" required="required" data-required-msg="短信验证码为必填项">
  </tags:form_group>
  <tags:form_group _title="验证码是否有效：">
   <input type="text" name="codeState" class="form-control" required="required" data-required-msg="验证码是否有效为必填项">
  </tags:form_group>
  <tags:form_group _title="验证码内容：">
   <input type="text" name="codeText" class="form-control" required="required" data-required-msg="验证码内容为必填项">
  </tags:form_group>
  <tags:form_group _title="验证码生成时间：">
   <input type="text" name="codeCreateTime" class="form-control" required="required" data-required-msg="验证码生成时间为必填项">
  </tags:form_group>
  <tags:form_group _title="验证码过期时间：">
   <input type="text" name="codeExpirationTime" class="form-control" required="required" data-required-msg="验证码过期时间为必填项">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="验证码类型1:注册 2:忘记密码 3修改密码：">
   <input type="text" name="codeType" class="form-control" required="required" data-required-msg="验证码类型1:注册 2:忘记密码 3修改密码为必填项">
  </tags:form_group>
  <tags:form_group _title="手机号：">
   <input type="text" name="codeMobile" class="form-control" required="required" data-required-msg="手机号为必填项">
  </tags:form_group>
  <tags:form_group _title="短信验证码：">
   <input type="text" name="codeCode" class="form-control" required="required" data-required-msg="短信验证码为必填项">
  </tags:form_group>
  <tags:form_group _title="验证码是否有效：">
   <input type="text" name="codeState" class="form-control" required="required" data-required-msg="验证码是否有效为必填项">
  </tags:form_group>
  <tags:form_group _title="验证码内容：">
   <input type="text" name="codeText" class="form-control" required="required" data-required-msg="验证码内容为必填项">
  </tags:form_group>
  <tags:form_group _title="验证码生成时间：">
   <input type="text" name="codeCreateTime" class="form-control" required="required" data-required-msg="验证码生成时间为必填项">
  </tags:form_group>
  <tags:form_group _title="验证码过期时间：">
   <input type="text" name="codeExpirationTime" class="form-control" required="required" data-required-msg="验证码过期时间为必填项">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="短信ID：">
   <span name="id"></span>
  </tags:form_group>
  <tags:form_group _title="验证码类型1:注册 2:忘记密码 3修改密码：">
   <span name="codeType"></span>
  </tags:form_group>
  <tags:form_group _title="手机号：">
   <span name="codeMobile"></span>
  </tags:form_group>
  <tags:form_group _title="短信验证码：">
   <span name="codeCode"></span>
  </tags:form_group>
  <tags:form_group _title="验证码是否有效：">
   <span name="codeState"></span>
  </tags:form_group>
  <tags:form_group _title="验证码内容：">
   <span name="codeText"></span>
  </tags:form_group>
  <tags:form_group _title="验证码生成时间：">
   <span name="codeCreateTime"></span>
  </tags:form_group>
  <tags:form_group _title="验证码过期时间：">
   <span name="codeExpirationTime"></span>
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
     {id:'id',title:'短信ID',columnClass:'text-center'}
    ,{id:'codeType',title:'验证码类型1:注册 2:忘记密码 3修改密码',columnClass:'text-center',hideType:'xs'}
    ,{id:'codeMobile',title:'手机号',columnClass:'text-center',hideType:'xs'}
    ,{id:'codeCode',title:'短信验证码',columnClass:'text-center',hideType:'xs'}
    ,{id:'codeState',title:'验证码是否有效',columnClass:'text-center',hideType:'xs'}
    ,{id:'codeText',title:'验证码内容',columnClass:'text-center',hideType:'xs'}
    ,{id:'codeCreateTime',title:'验证码生成时间',columnClass:'text-center',hideType:'xs'}
    ,{id:'codeExpirationTime',title:'验证码过期时间',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
