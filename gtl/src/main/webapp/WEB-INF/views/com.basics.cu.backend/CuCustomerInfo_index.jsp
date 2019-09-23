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
   kendo.bind($("#showDirectCustomerGrid"), {});
   kendo.bind($("#showProfitGrid"), {});
   kendo.bind($("#setLevelForm"), {});
  });
  
  //设置等级
  function setLevelOpen() {
	  var rows = CrudApp.getSelections();
	  if (rows.length > 1) {
	   CrudApp.warning('只能选择一个会员!');
	   return;
	  }
	  if (rows.length == 0) {
	   CrudApp.warning('请选择一个会员!');
	   return;
	  }
	  var row = rows[0];
	  $("#setLevelForm").form('load', row);
	  $("#setLevelDialog").modal('show');
  }
  function setLevelSave() {
	  var customerId = $("#setLevelId").val();
	  var customerLevel = $("#setlevelLevelId").val();
	  $.post('setLevel.do', {id : customerId, customerLevelId : customerLevel}, function(data) {
	   var data = $.parseJSON(data);
	   if (data.success) {
	    $("#deductCoinDialog").modal('hide');
	    CrudApp.success('操作成功!');
	    window.location.reload();
	   } else {
	    CrudApp.alert(data.message);
	   }
	  });
	 }
  //恢复等级
  function recoveryLevel() {
	  var rows = CrudApp.getSelections();
	  if (rows.length > 1) {
	   CrudApp.warning('只能选择一个会员!');
	   return;
	  }
	  if (rows.length == 0) {
	   CrudApp.warning('请选择一个会员!');
	   return;
	  }
	  var row = rows[0];
	  var id = row.id;
	  CrudApp.confirm("是否恢复等级, 根据持积分范围确定等级", function(){
		  $.post('recoveryLevel.do',{id:id},function(data){
			  CrudApp.success('操作成功!');
			  window.location.reload()
		  });
	  });
  }
  
  function deductMoneyDialogOpen() {
	  var rows = CrudApp.getSelections();
	  if (rows.length > 1) {
	   CrudApp.warning('只能选择一个会员!');
	   return;
	  }
	  if (rows.length == 0) {
	   CrudApp.warning('请选择一个会员!');
	   return;
	  }
	  var row = rows[0];
	  $("#deductMoneyForm").form('load', row);
	  $("#deductMoneyDialog").modal('show');
  }
  
  function deductIntegralDialogOpen() {
	  var rows = CrudApp.getSelections();
	  if (rows.length > 1) {
	   CrudApp.warning('只能选择一个会员!');
	   return;
	  }
	  if (rows.length == 0) {
	   CrudApp.warning('请选择一个会员!');
	   return;
	  }
	  var row = rows[0];
	  $("#deductIntegralForm").form('load', row);
	  $("#deductIntegralDialog").modal('show');
  }
  
  function deductCoinDialogOpen() {
	  var rows = CrudApp.getSelections();
	  if (rows.length > 1) {
	   CrudApp.warning('只能选择一个会员!');
	   return;
	  }
	  if (rows.length == 0) {
	   CrudApp.warning('请选择一个会员!');
	   return;
	  }
	  var row = rows[0];
	  $("#deductCoinForm").form('load', row);
	  $("#deductCoinDialog").modal('show');
  }
  
  function deductTradeCoinDialogOpen() {
	  var rows = CrudApp.getSelections();
	  if (rows.length > 1) {
	   CrudApp.warning('只能选择一个会员!');
	   return;
	  }
	  if (rows.length == 0) {
	   CrudApp.warning('请选择一个会员!');
	   return;
	  }
	  var row = rows[0];
	  $("#deductTradeCoinForm").form('load', row);
	  $("#deductTradeCoinDialog").modal('show');
  }
  
  
  
  function granDeductMoney() {
	  var customerId = $("#moneyId").val();
	  var moneyNum = $("#moneyNum").val();
	  $.post('granDeductMoney.do', {id : customerId, useMoney : moneyNum}, function(data) {
	   var data = $.parseJSON(data);
	   if (data.success) {
	    $("#deductMoneyDialog").modal('hide');
	    CrudApp.success('操作成功!');
	    window.location.reload();
	   } else {
	    CrudApp.alert(data.message);
	   }
	  });
	 }
  
  function granDeductIntegral() {
	  var customerId = $("#integralId").val();
	  var integralNum = $("#integralNum").val();
	  $.post('granDeductIntegral.do', {id : customerId, customerIntegral : integralNum}, function(data) {
	   var data = $.parseJSON(data);
	   if (data.success) {
	    $("#deductIntegralDialog").modal('hide');
	    CrudApp.success('操作成功!');
	    window.location.reload();
	   } else {
	    CrudApp.alert(data.message);
	   }
	  });
	 }
  
  function granDeductCoin() {
	  var customerId = $("#coinId").val();
	  var coinNum = $("#coinNum").val();
	  $.post('granDeductCoin.do', {id : customerId, useCoin : coinNum}, function(data) {
	   var data = $.parseJSON(data);
	   if (data.success) {
	    $("#deductCoinDialog").modal('hide');
	    CrudApp.success('操作成功!');
	    window.location.reload();
	   } else {
	    CrudApp.alert(data.message);
	   }
	  });
	 }
  
  function granDeductTradeCoin() {
	  var customerId = $("#tradeCoinId").val();
	  var coinNum = $("#tradeCoinNum").val();
	  $.post('granDeductTradeCoin.do', {id : customerId, tradeCoin : coinNum}, function(data) {
	   var data = $.parseJSON(data);
	   if (data.success) {
	    $("#deductTradeCoinDialog").modal('hide');
	    CrudApp.success('操作成功!');
	    window.location.reload();
	   } else {
	    CrudApp.alert(data.message);
	   }
	  });
	 }
  
  function frozenCustomer() {
	  var rows = CrudApp.getSelections();
	  if (rows.length > 1) {
	   CrudApp.warning('只能选择一个会员!');
	   return;
	  }
	  if (rows.length == 0) {
	   CrudApp.warning('请选择一个会员!');
	   return;
	  }
	  var row = rows[0];
	  var id = row.id;
	  var customerStatus = row.customerStatus;
	  $.post('updateCustomerStatus.do',{id:id,customerStatus:customerStatus},function(data){
		  CrudApp.success('操作成功!');
		  window.location.reload()
	  });
  }
  
  function deleteCustomer() {
	  var rows = CrudApp.getSelections();
	  if (rows.length > 1) {
	   CrudApp.warning('只能选择一个会员!');
	   return;
	  }
	  if (rows.length == 0) {
	   CrudApp.warning('请选择一个会员!');
	   return;
	  }
	  var row = rows[0];
	  var id = row.id;
	  CrudApp.confirm("您确定需要删除该用户吗,这会导致该节点以下的全部删除", function(){
		  CrudApp.confirm("这可能需要几秒钟或者几分钟完成,请耐心等待", function(){
			  $.post('deleteCustomer.do',{id:id},function(data){
				  CrudApp.success('操作成功!');
				  window.location.reload()
			  });
		  });
	  });
  }
  
  function formatOnclick(value, record, column, grid, dataNo, columnNo) {
      var id=record['id'];
      var content = '';
      content +='<a href="javascript:void(0);" onclick="selectDirectCustomer(\''+id+'\')">';
      content += value;
      content += '</a>';
      return content;
  }
  function selectDirectCustomer(userId){
      onDialogOpen('showDirectCustomerDialog');
      $("#showDirectCustomerGrid").data('kendoSwGrid').load({"refereeId":userId});
  }
  
  
  //流水
  function showProfit(value, record, column, grid, dataNo, columnNo) {
	var id =  record['id'];
	return `<a onclick="formatShowProfit\('`+id+`',1\)">MC</a>`+`<a onclick="formatShowProfit\('`+id+`',3\)">&nbsp;MP</a>`+`<a onclick="formatShowProfit\('`+id+`',2\)">&nbsp;MNC</a>`;	
  }
  var formatShowProfit = function(id, type) {
	  onDialogOpen('showProfitDialog');
      $("#showProfitGrid").data('kendoSwGrid').load({"customerId":id, "profitType":type});
  }
  
  function formatSource(value, record, column, grid, dataNo, columnNo) {
	if(undefined != value && "" != value) {
		return record.sourceName + ": " + record.sourcePhone;
	}
	return "";
  }
  
  function formatProfitStatus(value, record, column, grid, dataNo, columnNo) {
	var status = "";
	if(1 == value) {
		status = "收入";
	} else if (2 == value ) {
		status = "支出";	
	}
	return status;
  }
  
  //打开窗口
  function onDialogOpen(dialogId) {
      $('#' + dialogId).modal({
          backdrop : 'static',
          keyboard : false
      });
  }
  
  
  var banCustomerFlagTrade = function() {
	  var rows = CrudApp.getSelections();
	  if (rows.length > 1) {
	   CrudApp.warning('只能选择一个会员!');
	   return;
	  }
	  if (rows.length == 0) {
	   CrudApp.warning('请选择一个会员!');
	   return;
	  }
	  var row = rows[0];
	  var id = row.id;
	  $.post('banCustomerFlagTrade.do',{id:id},function(data){
		  CrudApp.success('操作成功!');
		  window.location.reload()
	  });
  }

  //kendo格式化时间
  function formatDate(value, record, column, grid, dataNo, columnNo) {
      if(null != value && "" != value) {
          var dateString = new Date(value).format('yyyy-MM-dd');
          $('#'+column.id+'Name').html(dateString);
          return dateString;
      }
      return "";
  }
  
 </script>
</head>
<body>

<!-- 矿机流水 -->
<tags:form_view_dialog_diy _id="showDirectCustomerDialog">
    <tags:swgrid_diy _grid="showDirectCustomerGrid" _loadURL="selectDirectCustomer.do" _check="false">
        [
        {id:'customerName',title:'用户',columnClass:'text-center',hideType:'xs'}
        ,{id:'customerPhone',title:'手机',columnClass:'text-center',hideType:'xs'}
        ,{id:'useMoney',title:'MC',columnClass:'text-center',hideType:'xs'}
        ,{id:'useCoin',title:'MNC',columnClass:'text-center',hideType:'xs'}
        ,{id:'customerIntegral',title:'MP',columnClass:'text-center',hideType:'xs'}
        ]</tags:swgrid_diy>
</tags:form_view_dialog_diy>

<!-- 收支流水 -->
<tags:form_view_dialog_diy _id="showProfitDialog">
    <tags:swgrid_diy _grid="showProfitGrid" _loadURL="selectProfit.do" _check="false">
        [
         {id:'profitStatus',title:'类型',columnClass:'text-center',hideType:'xs',resolution:formatProfitStatus}
        ,{id:'profitNum',title:'数量',columnClass:'text-center',hideType:'xs'}
        ,{id:'profitRemark',title:'备注',columnClass:'text-center',hideType:'xs'}
        ,{id:'profitHavedTime',title:'收益时间',columnClass:'text-center',hideType:'xs',resolution:formatDate}
        ,{id:'sourceName',title:'来源',columnClass:'text-center',hideType:'xs', resolution:formatSource}
        ]</tags:swgrid_diy>
</tags:form_view_dialog_diy>

<tags:form_dialog _name="deductMoney" _title="MC扣除" _onsave="granDeductMoney">
  <input type="hidden" name="id" id="moneyId" />
  <tags:form_group _title="用户名：">
   <span name="customerName"></span>
  </tags:form_group>
   <tags:form_group _title="数量：">
   <input type="text" name="moneyNum" id="moneyNum" class="form-control" required="required" data-required-msg="数量为必填项">
  </tags:form_group>
 </tags:form_dialog>
 
<tags:form_dialog _name="deductIntegral" _title="MP扣除" _onsave="granDeductIntegral">
  <input type="hidden" name="id" id="integralId" />
  <tags:form_group _title="用户名：">
   <span name="customerName"></span>
  </tags:form_group>
   <tags:form_group _title="数量：">
   <input type="text" name="integralName" id="integralNum" class="form-control" required="required" data-required-msg="数量为必填项">
  </tags:form_group>
 </tags:form_dialog>
 
 <tags:form_dialog _name="deductCoin" _title="MNC扣除" _onsave="granDeductCoin">
  <input type="hidden" name="id" id="coinId" />
  <tags:form_group _title="用户名：">
   <span name="customerName"></span>
  </tags:form_group>
   <tags:form_group _title="数量：">
   <input type="text" name="coinNum" id="coinNum" class="form-control" required="required" data-required-msg="数量为必填项">
  </tags:form_group>
 </tags:form_dialog>
 
 <tags:form_dialog _name="deductTradeCoin" _title="交易链扣除" _onsave="granDeductTradeCoin">
  <input type="hidden" name="id" id="tradeCoinId" />
  <tags:form_group _title="用户名：">
   <span name="customerName"></span>
  </tags:form_group>
   <tags:form_group _title="数量：">
   <input type="text" name="tradeCoinNum" id="tradeCoinNum" class="form-control" required="required" data-required-msg="数量为必填项">
  </tags:form_group>
 </tags:form_dialog>
 
 <tags:form_dialog _name="setLevel" _title="等级设置" _onsave="setLevelSave">
  <input type="hidden" name="id" id="setLevelId" />
  <tags:form_group _title="用户名：">
   <span name="customerName"></span>
  </tags:form_group>
  <tags:form_group _title="等级：">
   <input type="text" name="levelId" id="setlevelLevelId"  data-role="dropdownlist" data-value-field="id" data-text-field="levelName" data-option-label="请选择"
    class="form-control" data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
   transport: {read: {type:'POST',dataType:'json',contentType:'application/json',url: '${contextPath}/backend/sys/sysCustomerLevel/read.do'}
   ,parameterMap:function(options){return JSON.stringify(options);}}}">
  </tags:form_group>
  
 </tags:form_dialog>

<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="用户手机号：">
   <input type="text" name="customerPhone" class="form-control" required="required" data-required-msg="用户手机号为必填项">
  </tags:form_group>
  <tags:form_group _title="用户名：">
   <input type="text" name="customerName" class="form-control" required="required" data-required-msg="用户名为必填项">
  </tags:form_group>
  <tags:form_group _title="支付宝账号：">
   <input type="text" name="customerAlipay" class="form-control" >
  </tags:form_group>
  <tags:form_group _title="用户真实姓名：">
   <input type="text" name="realName" class="form-control">
  </tags:form_group>
  <tags:form_group _title="身份证号：">
   <input type="text" name="customerCard" class="form-control" >
  </tags:form_group>
  <tags:form_group _title="银行卡：">
   <input type="text" name="bankCard" class="form-control" >
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="用户账号：">
   <span name="customerNumber"></span>
  </tags:form_group>
  <tags:form_group _title="国家：">
   <span name="countryName"></span>
  </tags:form_group>
  <tags:form_group _title="用户名：">
   <span name="customerName"></span>
  </tags:form_group>
  <tags:form_group _title="用户头像的路径：">
   <span name="customerHead"></span>
  </tags:form_group>
  <tags:form_group _title="用户手机号：">
   <span name="customerPhone"></span>
  </tags:form_group>
  <tags:form_group _title="支付宝账号：">
   <span name="customerAlipay"></span>
  </tags:form_group>
  <tags:form_group _title="推荐人昵称：">
   <span name="refereeName"></span>
  </tags:form_group>
  <tags:form_group _title="推荐人手机：">
   <span name="refereePhone"></span>
  </tags:form_group>
  <tags:form_group _title="用户真实姓名：">
   <span name="realName"></span>
  </tags:form_group>
  <tags:form_group _title="身份证号：">
   <span name="customerCard"></span>
  </tags:form_group>
  <tags:form_group _title="银行卡：">
   <span name="bankCard"></span>
  </tags:form_group>
  <tags:form_group _title="用户冻结原因：">
   <span name="customerFreezeContext"></span>
  </tags:form_group>
  <tags:form_group _title="注册时间：">
   <span id="registerTimeName"></span>
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
   		<button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('edit');">
		  <span class="glyphicon glyphicon-pencil"></span> 编辑
		</button>
        <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('frozenCustomer')">
	      <span class="glyphicon glyphicon-group"></span> 冻结/解冻
	     </button>
	     <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('deductMoneyDialogOpen')">
          <span class="glyphicon "></span>MC扣除
        </button>
        <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('deductIntegralDialogOpen')">
          <span class="glyphicon "></span>MP扣除
        </button>
        <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('deductCoinDialogOpen')">
          <span class="glyphicon "></span>MNC扣除
        </button>
        <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('deductTradeCoinDialogOpen')">
          <span class="glyphicon "></span>交易链扣除
        </button>
        <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('banCustomerFlagTrade')">
          <span class="glyphicon "></span>允许/禁止交易
        </button>
        <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('setLevelOpen')">
          <span class="glyphicon "></span>等级设置
        </button>
        <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('recoveryLevel')">
          <span class="glyphicon "></span>等级恢复
        </button>
        <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('deleteCustomer')">
          <span class="glyphicon "></span>删除
        </button>
   </tags:form_grid_toolbar_view>
  </div>
  <tags:swgrid _loadURL="selectCustomerInfo.do">
   [
     {id:'customerName',title:'昵称',columnClass:'text-center',hideType:'xs'}
    ,{id:'realName',title:'姓名',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerPhone',title:'手机号',columnClass:'text-center',hideType:'xs'}
    ,{id:'registerTime',title:'注册时间',columnClass:'text-center',hideType:'xs',resolution:formatDate}
    ,{id:'refereeName',title:'推荐人昵称',columnClass:'text-center',hideType:'xs'}
    ,{id:'refereeRealName',title:'推荐人姓名',columnClass:'text-center',hideType:'xs'}
    ,{id:'refereePhone',title:'推荐人手机',columnClass:'text-center',hideType:'xs'}
    ,{id:'useMoney',title:'MC',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerIntegral',title:'MP',columnClass:'text-center',hideType:'xs'}
    ,{id:'useCoin',title:'MNC',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerLevelName',title:'等级',columnClass:'text-center',hideType:'xs'}
    ,{id:'flagLevelAuto',title:'等级自动',columnClass:'text-center',hideType:'xs',resolution:formatSatus}
    ,{id:'customerStatus',title:'会员状态',columnClass:'text-center',hideType:'xs',resolution:formatCustomerSataus}
    ,{id:'salfNum',title:'直推',columnClass:'text-center',hideType:'xs',resolution:formatOnclick}
    ,{id:'teamNum',title:'团队',columnClass:'text-center',hideType:'xs'}
    ,{id:'flagTrade',title:'是否可交易',columnClass:'text-center',hideType:'xs',resolution:formatSatus}
    ,{id:'',title:'收支流水',columnClass:'text-center',hideType:'xs',resolution:showProfit}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
