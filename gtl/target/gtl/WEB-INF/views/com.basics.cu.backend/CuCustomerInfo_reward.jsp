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
  });
  
  function changeRefereeDialogOpen() {
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
	  $("#changeRefereeForm").form('load', row);
	  $("#changeRefereeDialog").modal('show');
  }
  
  function awardMoneyDialogOpen() {
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
	  $("#awardMoneyForm").form('load', row);
	  $("#awardMoneyDialog").modal('show');
  }
  
  function awardIntegralDialogOpen() {
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
	  $("#awardIntegralForm").form('load', row);
	  $("#awardIntegralDialog").modal('show');
  }
  
  function awardCoinDialogOpen() {
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
	  $("#awardCoinForm").form('load', row);
	  $("#awardCoinDialog").modal('show');
  }
  
  function awardTradeCoinDialogOpen() {
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
	  $("#awardTradeCoinForm").form('load', row);
	  $("#awardTradeCoinDialog").modal('show');
  }
  
  function changeReleaseRate() {
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
	  $("#changeReleaseRateForm").form('load', row);
	  $("#changeReleaseRateDialog").modal('show');
  }

  function changeFlagSpecial() {
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
	  var phone = row.customerPhone;
      CrudApp.confirm("是否确认修改, 一经修改不可更改", function(){
          $.post('save.do', {id : id, customerPhone: phone, flagSpecial : 3}, function(data) {
              var data = $.parseJSON(data);
              if (data.success) {
                  $("#changeRefereeDialog").modal('hide');
                  CrudApp.success('操作成功!');
                  window.location.reload();
              } else {
                  CrudApp.alert(data.message);
              }
          });
      });
  }
  
  function dealChangeReferee() {
	  var customerId = $("#changeRefereeId").val();
	  var customerPhone = $("#refereePhone").val();
	  $.post('dealChangeReferee.do', {id : customerId, customerPhone : customerPhone}, function(data) {
	   var data = $.parseJSON(data);
	   if (data.success) {
	    $("#changeRefereeDialog").modal('hide');
	    CrudApp.success('操作成功!');
	    window.location.reload();
	   } else {
	    CrudApp.alert(data.message);
	   }
	  });
	 }
  
  
  function granRewardMoney() {
	  var customerId = $("#moneyId").val();
	  var moneyNum = $("#moneyNum").val();
	  $.post('granRewardMoney.do', {id : customerId, useMoney : moneyNum}, function(data) {
	   var data = $.parseJSON(data);
	   if (data.success) {
	    $("#awardMoneyDialog").modal('hide');
	    CrudApp.success('操作成功!');
	    window.location.reload();
	   } else {
	    CrudApp.alert(data.message);
	   }
	  });
	 }
  
  function granRewardIntegral() {
	  var customerId = $("#integralId").val();
	  var integralNum = $("#integralNum").val();
	  $.post('granRewardIntegral.do', {id : customerId, customerIntegral : integralNum}, function(data) {
	   var data = $.parseJSON(data);
	   if (data.success) {
	    $("#awardIntegralDialog").modal('hide');
	    CrudApp.success('操作成功!');
	    window.location.reload();
	   } else {
	    CrudApp.alert(data.message);
	   }
	  });
	 }
  
  function granRewardCoin() {
	  var customerId = $("#coinId").val();
	  var coinNum = $("#coinNum").val();
	  $.post('granRewardCoin.do', {id : customerId, useCoin : coinNum}, function(data) {
	   var data = $.parseJSON(data);
	   if (data.success) {
	    $("#awardCoinDialog").modal('hide');
	    CrudApp.success('操作成功!');
	    window.location.reload();
	   } else {
	    CrudApp.alert(data.message);
	   }
	  });
	 }

  function granRewardTradeCoin() {
	  var customerId = $("#tradeCoinId").val();
	  var coinNum = $("#tradeCoinNum").val();
	  $.post('granRewardTradeCoin.do', {id : customerId, tradeCoin : coinNum}, function(data) {
	   var data = $.parseJSON(data);
	   if (data.success) {
	    $("#awardTradeCoinDialog").modal('hide');
	    CrudApp.success('操作成功!');
	    window.location.reload();
	   } else {
	    CrudApp.alert(data.message);
	   }
	  });
	 }
  
  function granReleaseRate() {
	  var customerId = $("#releaseId").val();
	  var coinNum = $("#releaseNum").val();
	  $.post('granReleaseRate.do', {id : customerId, tradeCoin : coinNum}, function(data) {
	   var data = $.parseJSON(data);
	   if (data.success) {
	    $("#changeReleaseRateDialog").modal('hide');
	    CrudApp.success('操作成功!');
	    window.location.reload();
	   } else {
	    CrudApp.alert(data.message);
	   }
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
	return `<a onclick="formatShowProfit\('`+id+`',1\)">MC</a>`+`<a onclick="formatShowProfit\('`+id+`',3\)">&nbsp;MP</a>`+`<a onclick="formatShowProfit\('`+id+`',2\)">&nbsp;MNC</a>` +`<a onclick="formatShowProfit\('`+id+`',4\)">&nbsp;交易链</a>`;	
  }
  var formatShowProfit = function(id, type) {
	  onDialogOpen('showProfitDialog');
      $("#showProfitGrid").data('kendoSwGrid').load({"customerId":id, "profitType":type});
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
  
  function formatSource(value, record, column, grid, dataNo, columnNo) {
		if(undefined != value && "" != value) {
			return record.sourceName + ": " + record.sourcePhone;
		}
		return "";
	  }
  function formatflagSpecial(value, record, column, grid, dataNo, columnNo) {
		if(0 == value) {
		    return "A";
        } else if(1 == value) {
            return "B";
        } else if (3 === value) {
		    return "C";
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

<tags:form_view_dialog>
  <tags:form_group _title="用户账号：">
   <span name="customerNumber"></span>
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

<tags:form_dialog _name="awardMoney" _title="奖励MC" _onsave="granRewardMoney">
  <input type="hidden" name="id" id="moneyId" />
  <tags:form_group _title="用户名：">
   <span name="customerName"></span>
  </tags:form_group>
   <tags:form_group _title="数量：">
   <input type="text" name="moneyNum" id="moneyNum" class="form-control" required="required" data-required-msg="数量为必填项">
  </tags:form_group>
 </tags:form_dialog>
 
<tags:form_dialog _name="awardIntegral" _title="奖励MP" _onsave="granRewardIntegral">
  <input type="hidden" name="id" id="integralId" />
  <tags:form_group _title="用户名：">
   <span name="customerName"></span>
  </tags:form_group>
   <tags:form_group _title="数量：">
   <input type="text" name="integralName" id="integralNum" class="form-control" required="required" data-required-msg="数量为必填项">
  </tags:form_group>
 </tags:form_dialog>
 
  <tags:form_dialog _name="awardCoin" _title="奖励MNC" _onsave="granRewardCoin">
  <input type="hidden" name="id" id="coinId" />
  <tags:form_group _title="用户名：">
   <span name="customerName"></span>
  </tags:form_group>
   <tags:form_group _title="数量：">
   <input type="text" name="coinNum" id="coinNum" class="form-control" required="required" data-required-msg="数量为必填项">
  </tags:form_group>
 </tags:form_dialog>
 
 <tags:form_dialog _name="awardTradeCoin" _title="奖励交易链" _onsave="granRewardTradeCoin">
  <input type="hidden" name="id" id="tradeCoinId" />
  <tags:form_group _title="用户名：">
   <span name="customerName"></span>
  </tags:form_group>
   <tags:form_group _title="数量：">
   <input type="text" name="tradeCoinNum" id="tradeCoinNum" class="form-control" required="required" data-required-msg="数量为必填项">
  </tags:form_group>
 </tags:form_dialog>
 
 <tags:form_dialog _name="changeReferee" _title="修改推荐人" _onsave="dealChangeReferee">
  <input type="hidden" name="id" id="changeRefereeId" />
  <tags:form_group _title="用户名：">
   <span name="customerName"></span>
  </tags:form_group>
   <tags:form_group _title="推荐人手机：">
   <input type="text" name="refereePhone" id="refereePhone" class="form-control" required="required" data-required-msg="手机号为必填项">
  </tags:form_group>
 </tags:form_dialog>
 
 <tags:form_dialog _name="changeReleaseRate" _title="修改释放比例" _onsave="granReleaseRate">
  <input type="hidden" name="id" id="releaseId" />
  <tags:form_group _title="用户名：">
   <span name="customerName"></span>
  </tags:form_group>
   <tags:form_group _title="释放比例：">
   <input type="text" name="releaseNum" id="releaseNum" class="form-control" required="required" data-required-msg="数量为必填项">
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
   <tags:form_grid_toolbar_view>
        <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('awardMoneyDialogOpen')">
          <span class="glyphicon "></span>MC发放
        </button>
        <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('awardIntegralDialogOpen')">
          <span class="glyphicon "></span>MP发放
        </button>
        <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('awardCoinDialogOpen')">
          <span class="glyphicon "></span>MNC发放
        </button>
        <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('awardTradeCoinDialogOpen')">
          <span class="glyphicon "></span>交易链发放
        </button>
        <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('changeRefereeDialogOpen')">
          <span class="glyphicon "></span>修改推荐人
        </button>
       <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('changeFlagSpecial')">
           <span class="glyphicon "></span>双通转账
       </button>
       <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('changeReleaseRate')">
           <span class="glyphicon "></span>释放比例
       </button>
   </tags:form_grid_toolbar_view>
  </div>
  <tags:swgrid _loadURL="selectCustomerInfo.do">
   [
     {id:'customerName',title:'昵称',columnClass:'text-center',hideType:'xs'}
    ,{id:'realName',title:'姓名',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerPhone',title:'手机号',columnClass:'text-center',hideType:'xs'}
    ,{id:'refereeName',title:'推荐人昵称',columnClass:'text-center',hideType:'xs'}
    ,{id:'refereeRealName',title:'推荐人姓名',columnClass:'text-center',hideType:'xs'}
    ,{id:'refereePhone',title:'推荐人手机',columnClass:'text-center',hideType:'xs'}
    ,{id:'useMoney',title:'MC',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerIntegral',title:'MP',columnClass:'text-center',hideType:'xs'}
    ,{id:'useCoin',title:'MNC',columnClass:'text-center',hideType:'xs'}
    ,{id:'tradeCoin',title:'交易链',columnClass:'text-center',hideType:'xs'}
     ,{id:'rateNum',title:'释放比例',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerStatus',title:'会员状态',columnClass:'text-center',hideType:'xs',resolution:formatCustomerSataus}
    ,{id:'salfNum',title:'直推',columnClass:'text-center',hideType:'xs',resolution:formatOnclick}
    ,{id:'teamNum',title:'团队',columnClass:'text-center',hideType:'xs'}
    ,{id:'flagSpecial',title:'标记',columnClass:'text-center',hideType:'xs',resolution:formatflagSpecial}
    ,{id:'',title:'收支流水',columnClass:'text-center',hideType:'xs',resolution:showProfit}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
