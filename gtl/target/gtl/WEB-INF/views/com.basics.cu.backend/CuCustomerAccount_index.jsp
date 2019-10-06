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
  <tags:form_group _title="可用余额：">
   <input type="text" name="useMoney" class="form-control" required="required" data-required-msg="可用余额为必填项">
  </tags:form_group>
  <tags:form_group _title="冻结余额：">
   <input type="text" name="lockMoney" class="form-control" required="required" data-required-msg="冻结余额为必填项">
  </tags:form_group>
  <tags:form_group _title="总共余额：">
   <input type="text" name="totalMoney" class="form-control" required="required" data-required-msg="总共余额为必填项">
  </tags:form_group>
  <tags:form_group _title="会员积分：">
   <input type="text" name="customerIntegral" class="form-control" required="required" data-required-msg="会员积分为必填项">
  </tags:form_group>
  <tags:form_group _title="可用链：">
   <input type="text" name="useCoin" class="form-control" required="required" data-required-msg="可用链为必填项">
  </tags:form_group>
  <tags:form_group _title="冻结链：">
   <input type="text" name="lockCoin" class="form-control" required="required" data-required-msg="冻结链为必填项">
  </tags:form_group>
  <tags:form_group _title="总共链：">
   <input type="text" name="totalCoin" class="form-control" required="required" data-required-msg="总共链为必填项">
  </tags:form_group>
  <tags:form_group _title="二级密码：">
   <input type="text" name="customerPayPass" class="form-control" required="required" data-required-msg="二级密码为必填项">
  </tags:form_group>
  <tags:form_group _title="会员钱包：">
   <input type="text" name="customerPurse" class="form-control" required="required" data-required-msg="会员钱包为必填项">
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
  <tags:form_group _title="可用余额：">
   <input type="text" name="useMoney" class="form-control" required="required" data-required-msg="可用余额为必填项">
  </tags:form_group>
  <tags:form_group _title="冻结余额：">
   <input type="text" name="lockMoney" class="form-control" required="required" data-required-msg="冻结余额为必填项">
  </tags:form_group>
  <tags:form_group _title="总共余额：">
   <input type="text" name="totalMoney" class="form-control" required="required" data-required-msg="总共余额为必填项">
  </tags:form_group>
  <tags:form_group _title="会员积分：">
   <input type="text" name="customerIntegral" class="form-control" required="required" data-required-msg="会员积分为必填项">
  </tags:form_group>
  <tags:form_group _title="可用链：">
   <input type="text" name="useCoin" class="form-control" required="required" data-required-msg="可用链为必填项">
  </tags:form_group>
  <tags:form_group _title="冻结链：">
   <input type="text" name="lockCoin" class="form-control" required="required" data-required-msg="冻结链为必填项">
  </tags:form_group>
  <tags:form_group _title="总共链：">
   <input type="text" name="totalCoin" class="form-control" required="required" data-required-msg="总共链为必填项">
  </tags:form_group>
  <tags:form_group _title="二级密码：">
   <input type="text" name="customerPayPass" class="form-control" required="required" data-required-msg="二级密码为必填项">
  </tags:form_group>
  <tags:form_group _title="会员钱包：">
   <input type="text" name="customerPurse" class="form-control" required="required" data-required-msg="会员钱包为必填项">
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
  <tags:form_group _title="可用余额：">
   <span name="useMoney"></span>
  </tags:form_group>
  <tags:form_group _title="冻结余额：">
   <span name="lockMoney"></span>
  </tags:form_group>
  <tags:form_group _title="总共余额：">
   <span name="totalMoney"></span>
  </tags:form_group>
  <tags:form_group _title="会员积分：">
   <span name="customerIntegral"></span>
  </tags:form_group>
  <tags:form_group _title="可用链：">
   <span name="useCoin"></span>
  </tags:form_group>
  <tags:form_group _title="冻结链：">
   <span name="lockCoin"></span>
  </tags:form_group>
  <tags:form_group _title="总共链：">
   <span name="totalCoin"></span>
  </tags:form_group>
  <tags:form_group _title="二级密码：">
   <span name="customerPayPass"></span>
  </tags:form_group>
  <tags:form_group _title="会员钱包：">
   <span name="customerPurse"></span>
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
    ,{id:'useMoney',title:'可用余额',columnClass:'text-center',hideType:'xs'}
    ,{id:'lockMoney',title:'冻结余额',columnClass:'text-center',hideType:'xs'}
    ,{id:'totalMoney',title:'总共余额',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerIntegral',title:'会员积分',columnClass:'text-center',hideType:'xs'}
    ,{id:'useCoin',title:'可用链',columnClass:'text-center',hideType:'xs'}
    ,{id:'lockCoin',title:'冻结链',columnClass:'text-center',hideType:'xs'}
    ,{id:'totalCoin',title:'总共链',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerPayPass',title:'二级密码',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerPurse',title:'会员钱包',columnClass:'text-center',hideType:'xs'}
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
