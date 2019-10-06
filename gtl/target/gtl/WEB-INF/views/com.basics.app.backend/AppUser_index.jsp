<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/taglib.jsp"%>
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
 
 function clearCache(){
	 $.post('clearCache.do',function(data){
		  CrudApp.success('操作成功!');
		  window.location.reload()
	  });
 }

 function userRoleDialogOpen() {
  var rows = CrudApp.getSelections();
  if (rows.length > 1) {
   CrudApp.warning('只能选择一个角色!');
   return;
  }
  if (rows.length == 0) {
   CrudApp.warning('请选择一个角色!');
   return;
  }
  var row = rows[0];
  $("#userRoleForm").form('load', row);
  $.post('getUserRoles.do', {
   user : row.id
  }, function(data) {
   var data = $.parseJSON(data);
   if (data.success) {
    $("#userRoleTree").data('kendoZTree').setCheckIds(data.item);
    $("#userRoleDialog").modal('show');
   } else {
    CrudApp.alert(data.message);
   }
  });
 }

 function userRoleSave() {
  var userId = $("#userRoleId").val();
  var roles = $("#userRoleTree").data('kendoZTree').getCheckIds();
  $.post('saveUsersForRoles.do', {
   users : userId,
   roles : roles
  }, function(data) {
   var data = $.parseJSON(data);
   if (data.success) {
    $("#userRoleDialog").modal('hide');
    CrudApp.success('操作成功!');
   } else {
    CrudApp.alert(data.message);
   }
  });
 }

 $(function() {
  //自定义的验证
  $("#addForm").kendoValidator({
   rules : {
    codeRule : function(input) {
     if (input.is("[name=code]")) {
      var code = input.val();
      var settings = {
       url : 'validateCode.do',
       data : 'code=' + code,
       async : false
      };
      var resultText = $.ajax(settings).responseText;
      var result = $.parseJSON(resultText);
      return result.success;
     }
     return true;
    }
   },
   messages : {
    codeRule : "编码已经存在"
   }
  });

  //自定义的验证
  $("#editForm").kendoValidator({
   rules : {
    codeRule : function(input) {
     if (input.is("[name=code]")) {
      var code = input.val();
      var id = $("#editForm").find('[name=id]').val();
      var settings = {
       url : 'validateCode.do',
       data : 'code=' + code + '&id=' + id,
       async : false
      };
      var resultText = $.ajax(settings).responseText;
      var result = $.parseJSON(resultText);
      return result.success;
     }
     return true;
    }
   },
   messages : {
    codeRule : "编码已经存在"
   }
  });

  //初始化app
  kendo.bind($("#grid"), {});
  kendo.bind($("#userRoleForm"), {});
  CrudApp.init(options);
  CrudApp.on('search');
 });
</script>
</head>
<body>
 <tags:form_add_dialog>
  <input type="hidden" name="id" />
  <tags:form_group _title="用户编码：">
   <input type="text" name="code" class="form-control" required="required" data-required-msg="用户编码为必填项">
  </tags:form_group>
  <tags:form_group _title="用户名称：">
   <input type="text" name="name" class="form-control" required="required" data-required-msg="用户名称为必填项">
  </tags:form_group>
  <tags:form_group _title="用户描述：">
   <input type="text" name="comment" class="form-control" required="required" data-required-msg="用户描述为必填项">
  </tags:form_group>
  <tags:form_group _title="用户密码：">
   <input type="password" name="passwordNew" class="form-control" required="required" data-required-msg="用户密码为必填项">
  </tags:form_group>
  <tags:form_group _title="确认用户密码：">
   <input type="password" name="passwordConfirmed" class="form-control" required="required" data-required-msg="确认用户密码为必填项">
  </tags:form_group>
  <tags:form_group _title="是否启用 ：">
   <input type="text" required="required" data-required-msg="是否启用为必填项" name="state" data-role="dropdownlist" data-value-field="code"
    data-text-field="name" class="form-control"
    data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
   transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/yes_no.do'}}}">
  </tags:form_group>
  <tags:form_group _title="用户类型 ：">
   <input type="radio" name="type" value="0">普通用户&nbsp;
    <input type="radio" name="type" value="1">管理用户&nbsp;
  </tags:form_group>
 </tags:form_add_dialog>
 <tags:form_edit_dialog>
  <input type="hidden" name="id" />
  <tags:form_group _title="用户编码：">
   <input type="text" name="code" class="form-control" required="required" data-required-msg="用户编码为必填项">
  </tags:form_group>
  <tags:form_group _title="用户名称：">
   <input type="text" name="name" class="form-control" required="required" data-required-msg="用户名称为必填项">
  </tags:form_group>
  <tags:form_group _title="用户描述：">
   <input type="text" name="comment" class="form-control" required="required" data-required-msg="用户描述为必填项">
  </tags:form_group>
  <tags:form_group _title="用户密码：">
   <input type="password" name="passwordNew" class="form-control">
  </tags:form_group>
  <tags:form_group _title="确认用户密码：">
   <input type="password" name="passwordConfirmed" class="form-control">
  </tags:form_group>
  <tags:form_group _title="是否启用 ：">
   <input type="text" required="required" data-required-msg="是否启用为必填项" name="state" data-role="dropdownlist" data-value-field="code"
    data-text-field="name" class="form-control"
    data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
   transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/yes_no.do'}}}">
  </tags:form_group>
 </tags:form_edit_dialog>
 <tags:form_view_dialog>
  <tags:form_group _title="用户编码：">
   <span name="code"></span>
  </tags:form_group>
  <tags:form_group _title="用户名称：">
   <span name="name"></span>
  </tags:form_group>
  <tags:form_group _title="用户描述：">
   <span name="comment"></span>
  </tags:form_group>
  <tags:form_group _title="是否启用：">
   <span name="stateName"></span>
  </tags:form_group>
 </tags:form_view_dialog>
 <tags:form_dialog _name="userRole" _title="用户角色" _onsave="userRoleSave">
  <input type="hidden" name="id" id="userRoleId" />
  <tags:form_group _title="用户名称：">
   <span name="name"></span>
  </tags:form_group>
  <tags:form_group _title="角色：">
   <ul class="ztree" id="userRoleTree" data-url="${contextPath}/backend/app/appRole/ztree.do" data-check-enable=true data-expand-all=true
    data-role="ztree">
   </ul>
  </tags:form_group>
 </tags:form_dialog>
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
    <tags:form_grid_toolbar>
     <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('userRoleDialogOpen')">
      <span class="glyphicon glyphicon-group"></span> 角色管理
     </button>
     <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('clearCache')">
      <span class="glyphicon glyphicon-group"></span> 清理缓存
     </button>
    </tags:form_grid_toolbar>
   </div>
   <tags:swgrid>
[
{id:'code',title:'用户编码',columnClass:'text-center'}
,{id:'name',title:'用户实名',columnClass:'text-center',hideType:'xs'} 
,{id:'stateName',title:'是否启用',columnClass:'text-center',hideType:'xs'} 
]</tags:swgrid>
  </div>
  <!-- 表格} -->
 </section>
</body>
</html>
