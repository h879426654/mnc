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

 function rolePermissionDialogOpen() {
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
  $("#rolePermissionForm").form('load', row);
  $.post('getRolePermissions.do', {
   role : row.id
  }, function(data) {
   var data = $.parseJSON(data);
   if (data.success) {
    $("#rolePermissionTree").data('kendoZTree').setCheckIds(data.item);
    $("#rolePermissionDialog").modal('show');
   } else {
    CrudApp.alert(data.message);
   }
  });
 }

 function rolePermissionSave() {
  var roleId = $("#rolePermissionId").val();
  var permissions = $("#rolePermissionTree").data('kendoZTree').getCheckIds();
  $.post('saveRolePermissions.do', {
   roles : roleId,
   permissions : permissions
  }, function(data) {
   var data = $.parseJSON(data);
   if (data.success) {
    $("#rolePermissionDialog").modal('hide');
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
    codeRule : "角色编码已经存在"
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
    codeRule : "角色编码已经存在"
   }
  });

  kendo.bind($("#grid"), {});
  kendo.bind($("#rolePermissionForm"), {});
  CrudApp.init(options);
  CrudApp.on('search');
 });
</script>
</head>
<body>
 <tags:form_add_dialog>
  <input type="hidden" name="id" />
  <tags:form_group _title="角色编码：">
   <input type="text" name="code" class="form-control" required="required" data-required-msg="角色编码为必填项">
  </tags:form_group>
  <tags:form_group _title="角色名称：">
   <input type="text" name="name" class="form-control" required="required" data-required-msg="角色名称为必填项">
  </tags:form_group>
  <tags:form_group _title="角色描述：">
   <input type="text" name="comment" class="form-control" required="required" data-required-msg="角色描述为必填项">
  </tags:form_group>
  <tags:form_group _title="是否启用 ：">
   <input type="text" required="required" data-required-msg="是否启用为必填项" name="state" data-role="dropdownlist" data-value-field="code"
    data-text-field="name" class="form-control"
    data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
   transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/yes_no.do'}}}">
  </tags:form_group>
 </tags:form_add_dialog>
 <tags:form_edit_dialog>
  <input type="hidden" name="id" />
  <tags:form_group _title="角色编码：">
   <input type="text" name="code" class="form-control" required="required" data-required-msg="角色编码为必填项">
  </tags:form_group>
  <tags:form_group _title="角色名称：">
   <input type="text" name="name" class="form-control" required="required" data-required-msg="角色名称为必填项">
  </tags:form_group>
  <tags:form_group _title="角色描述：">
   <input type="text" name="comment" class="form-control" required="required" data-required-msg="角色描述为必填项">
  </tags:form_group>
  <tags:form_group _title="是否启用 ：">
   <input type="text" required="required" data-required-msg="是否启用为必填项" name="state" data-role="dropdownlist" data-value-field="code"
    data-text-field="name" class="form-control"
    data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
   transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/yes_no.do'}}}">
  </tags:form_group>
 </tags:form_edit_dialog>
 <tags:form_view_dialog>
  <tags:form_group _title="角色编码：">
   <span name="code"></span>
  </tags:form_group>
  <tags:form_group _title="角色名称：">
   <span name="name"></span>
  </tags:form_group>
  <tags:form_group _title="角色描述：">
   <span name="comment"></span>
  </tags:form_group>
  <tags:form_group _title="是否启用：">
   <span name="stateName"></span>
  </tags:form_group>
 </tags:form_view_dialog>
 <tags:form_dialog _name="rolePermission" _title="角色权限" _onsave="rolePermissionSave">
  <input type="hidden" name="id" id="rolePermissionId" />
  <tags:form_group _title="角色名称：">
   <span name="name"></span>
  </tags:form_group>
  <tags:form_group _title="角色权限：">
   <ul class="ztree" id="rolePermissionTree" data-url="${contextPath}/backend/app/appPermission/ztree.do" data-check-enable=true
    data-expand-all=true data-role="ztree">
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
     <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('rolePermissionDialogOpen')">
      <span class="glyphicon glyphicon-key"></span> 权限管理
     </button>
    </tags:form_grid_toolbar>
   </div>
   <tags:swgrid>
[{id:'code',title:'角色编码',columnClass:'text-center'} 
,{id:'name',title:'角色名称',columnClass:'text-center',hideType:'xs'} 
,{id:'comment',title:'角色描述',columnClass:'text-center',hideType:'xs'} 
,{id:'stateName',title:'是否启用',columnClass:'text-center',hideType:'xs'} 
]</tags:swgrid>
  </div>
  <!-- 表格} -->
 </section>
</body>
</html>
