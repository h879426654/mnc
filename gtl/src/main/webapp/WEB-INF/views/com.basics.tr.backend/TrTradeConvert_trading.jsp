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
  
  function cancleTrade(){
	  var rows = CrudApp.getSelections();
	  if (rows.length > 1) {
	   CrudApp.warning('只能选择一个交易!');
	   return;
	  }
	  if (rows.length == 0) {
	   CrudApp.warning('请选择一个交易!');
	   return;
	  }
	  var row = rows[0];
	  var id = row.id;
	  $.post('${contextPath}/backend/tr/trTradeConvert/cancelTrade.do',{id:id},function(data){
		  CrudApp.success('操作成功!');
		  window.location.reload()
	  });
  }
  
  function finishTrade(){
	  var rows = CrudApp.getSelections();
	  if (rows.length > 1) {
	   CrudApp.warning('只能选择一个交易!');
	   return;
	  }
	  if (rows.length == 0) {
	   CrudApp.warning('请选择一个交易!');
	   return;
	  }
	  var row = rows[0];
	  var id = row.id;
	  var customerId = row.customerId;
	  $.post('${contextPath}/backend/tr/trTradeConvert/finishTrade.do',{id:id, customerId:customerId},function(data){
		  CrudApp.success('操作成功!');
		  window.location.reload()
	  });
  }
  
 </script>
</head>
<body>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <input type="hidden" name="tradeStatus"/>
  <tags:form_group _title="审核状态：">
   <input type="text" name="applyStatus" data-role="dropdownlist"
      data-value-field="code" data-text-field="name" class="form-control" 
      data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
     transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/APPLY_STATUS.do'}}}">
  </tags:form_group>
  <tags:form_group _title="审核说明：">
   <input type="text" name="applyContext" class="form-control" required="required" data-required-msg="审核说明为必填项">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="交易流水号：">
   <span name="tradeSerial"></span>
  </tags:form_group>
  <tags:form_group _title="出售用户：">
   <span name="customerName"></span>
  </tags:form_group>
  <tags:form_group _title="交易类型：">
   <span name="tradeTypeName"></span>
  </tags:form_group>
  <tags:form_group _title="平台币数量：">
   <span name="moneyNum"></span>
  </tags:form_group>
  <tags:form_group _title="交易状态：">
   <span name="tradeStatusName"></span>
  </tags:form_group>
  <tags:form_group _title="购买用户：">
   <span name="customerBuyName"></span>
  </tags:form_group>
  <tags:form_group _title="支付时间：">
   <span id="tradePayTimeName"></span>
  </tags:form_group>
  <tags:form_group _title="审核状态状态：">
   <span name="applyStatusName"></span>
  </tags:form_group>
  <tags:form_group _title="审核意见：">
   <span name="applyContext"></span>
  </tags:form_group>
  <tags:form_group _title="审核时间：">
   <span id="applyTimeName"></span>
  </tags:form_group>
  <tags:form_group _title="卖方应冻结平台币：">
   <span name="lockMoneyNum"></span>
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
   <tags:form_grid_toolbar_view>
   		<button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('finishTrade')">
	      <span class="glyphicon glyphicon-group"></span> 交易完成
	     </button>
	   	<button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('cancleTrade')">
	      <span class="glyphicon glyphicon-group"></span> 交易取消
	     </button>
   </tags:form_grid_toolbar_view>
  </div>
  <tags:swgrid _loadURL="selectTradeByTrading.do">
   [
     {id:'customerName',title:'出售用户',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerRealName',title:'出售姓名',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerPhone',title:'出售手机号',columnClass:'text-center',hideType:'xs'}
    ,{id:'moneyNum',title:'数量',columnClass:'text-center',hideType:'xs'}
    ,{id:'lockMoneyNum',title:'卖方应冻结平台币',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerBuyName',title:'购买用户',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerBuyRealName',title:'购买姓名',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerBuyPhone',title:'购买手机号',columnClass:'text-center',hideType:'xs'}
    ,{id:'tradeStatusName',title:'交易状态',columnClass:'text-center',hideType:'xs'}
    ,{id:'tradePayTime',title:'支付时间',columnClass:'text-center',hideType:'xs',resolution:formatDate}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
