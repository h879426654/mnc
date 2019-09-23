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
  
  $(function() {
	   $("#editForm").kendoValidator();
	   kendo.bind($("#grid"), {});
	   kendo.bind($("#editForm"), {});
	   CrudApp.init(options);
	   CrudApp.on('search');
	   $.post('load.do', {}, function(data) {
			var _data = $.parseJSON(data);
			if (_data.success) {
				$("#editForm").form('load', _data.item);
			} else {
				CrudApp.alert(_data.message);
			}
		});
	  });
	  
	  function onWillAddFormSave() {
		$(".kindeditor").each(function() {
			$(this).data('kendoKindEditor').sync();
		});
	 }
	  
	  function editFormSave() {
			$('#editForm').form('submit', {
				url : 'modify.do',
				onSubmit : function() {
					try {
						onWillAddFormSave();
					} catch (e) {
						CrudApp.alert(e);
					}
					return true;
				},
				success : function(data) {
					var data = $.parseJSON(data);
					if (data.success) {
						CrudApp.alert('编辑成功！');
						window.location.reload();
					} else {
						CrudApp.alert(data.message);
					}
				}
			});
		}
	  
	  function formatTextShow(value, record, column, grid, dataNo, columnNo) {
		  var text = "";
		  if(null != value) {
			 text = value*100 + "%";
		  }
		  return text;
	  }
  
 </script>
</head>
<body>
<section class="content-header">
 <h1>${_urlMenuComment}</h1>
</section>
<section class="content">
 
 <!-- 表格{ -->
 <div class="box">
  <div class="box-header with-border">
	   <form class="form-horizontal bv-form" id="editForm" method="post">
			<div class="box box-default">
				<div class="box-body">
					<div class="box box-success">
						<div class="box-header with-border">
							<h3 class="box-title">基本信息</h3>
							<div class="box-tools"></div>
						</div>
						<div class="box-body" style="width:60%;margin:auto;">
							<input type="hidden" name="id" />
							<input type="hidden" name="convertSerial" />
							<tags:form_group_edit _title="每日释放比例:" _id="releaseRateDay"></tags:form_group_edit>
							<tags:form_group_edit _title="余额转积分首次:" _id="rateToIntegralFirst"></tags:form_group_edit>
							<tags:form_group_edit _title="余额转积分非首次:" _id="rateToIntegralMore"></tags:form_group_edit>
							<tags:form_group_edit _title="余额转账商家奖励比例:" _id="moneyOutRate"></tags:form_group_edit>
							<tags:form_group_edit _title="余额交易个人奖励比例(买方):" _id="moneyTradeRate"></tags:form_group_edit>
							<tags:form_group_edit _title="余额交易个人奖励比例(卖方):" _id="moneyTradeRateSale"></tags:form_group_edit>
							<tags:form_group_edit _title="余额消费奖励比例:" _id="moneySaleRate"></tags:form_group_edit>
							<tags:form_group_edit _title="转化比例:" _id="convertRate"></tags:form_group_edit>
							<tags:form_group_edit _title="签到奖励积分数量:" _id="signRewardNum"></tags:form_group_edit>
							<tags:form_group_edit _title="交易手续费比例:" _id="tradeRate"></tags:form_group_edit>
							<tags:form_group_edit _title="余额交易折扣:" _id="discountNum"></tags:form_group_edit>
							
							<div class="col-md-12">
								<div class="form-group has-feedback">
									<label for="tradeApplyFlag" class="col-sm-4 control-label">交易是否审核：</label>
									<div class="col-sm-6">
										<input type="text" name="tradeApplyFlag" data-role="dropdownlist"
									      data-value-field="code" data-text-field="name" class="form-control" 
									      data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
									     transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/YES_NO.do'}}}">
									</div>
								</div>
							</div>
							
							
							<tags:form_group_edit _title="交易开始时间(24小时制):" _id="tradeStartTime"></tags:form_group_edit>
							<tags:form_group_edit _title="交易结束时间(24小时制):" _id="tradeEndTime"></tags:form_group_edit>
							<tags:form_group_edit _title="交易最小值:" _id="tradeMinNum"></tags:form_group_edit>
							<tags:form_group_edit _title="交易最大值:" _id="tradeMaxNum"></tags:form_group_edit>
							<tags:form_group_edit _title="卖方最低单价比例:" _id="mallMinPrice"></tags:form_group_edit>
							<tags:form_group_edit _title="卖方最高单价比例:" _id="mallMaxPrice"></tags:form_group_edit>
							<tags:form_group_edit _title="买方最低单价比例:" _id="buyMinPrice"></tags:form_group_edit>
							<tags:form_group_edit _title="买方最高单价比例:" _id="buyMaxPrice"></tags:form_group_edit>
							<tags:form_group_edit _title="交易超过时间:" _id="tradeTimeOut"></tags:form_group_edit>
							<tags:form_group_edit _title="开店保证金:" _id="needShopMoney"></tags:form_group_edit>
							<div class="col-md-12">
								<div class="form-group has-feedback">
									<label for="rewardFlag" class="col-sm-4 control-label">是否需要上传营业执照：</label>
									<div class="col-sm-6">
										<input type="text" name="needUploadLicence" data-role="dropdownlist"
									      data-value-field="code" data-text-field="name" class="form-control" 
									      data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
									     transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/YES_NO.do'}}}">
									</div>
								</div>
							</div>
							<tags:form_group_edit _title="抽奖金额:" _id="rewardNum"></tags:form_group_edit>
							<div class="col-md-12">
								<div class="form-group has-feedback">
									<label for="rewardFlag" class="col-sm-4 control-label">抽奖是否开启：</label>
									<div class="col-sm-6">
										<input type="text" name="rewardFlag" data-role="dropdownlist"
									      data-value-field="code" data-text-field="name" class="form-control" 
									      data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
									     transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/YES_NO.do'}}}">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="box-footer text-center">
				<input type="button" class="btn btn-default label-primary" onclick="editFormSave();" value="保存" />
			</div>
		</form>
  </div>
  <tags:swgrid>
   [
     {id:'releaseRateDay',title:'每日释放比例',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'rateToIntegralFirst',title:'余额转积分首次',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'rateToIntegralMore',title:'余额转积分非首次',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'moneyOutRate',title:'余额转账商家奖励比例',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'moneyTradeRate',title:'余额交易个人奖励比例(买方)',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'moneyTradeRateSale',title:'余额交易个人奖励比例(卖方)',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'moneySaleRate',title:'余额消费奖励比例',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'convertRate',title:'转化比例',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'signRewardNum',title:'签到奖励积分数量',columnClass:'text-center',hideType:'xs'}
    ,{id:'tradeRate',title:'交易手续费比例',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'discountNum',title:'余额交易折扣',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'tradeStartTime',title:'交易开始时间(24小时制)',columnClass:'text-center',hideType:'xs'}
    ,{id:'tradeEndTime',title:'交易结束时间(24小时制)',columnClass:'text-center',hideType:'xs'}
    ,{id:'tradeMinNum',title:'交易最小值',columnClass:'text-center',hideType:'xs'}
    ,{id:'tradeMaxNum',title:'交易最大值(-1表示无穷大)',columnClass:'text-center',hideType:'xs'}
    ,{id:'mallMinPrice',title:'卖方最低单价比例',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'mallMaxPrice',title:'卖方最高单价比例',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'buyMinPrice',title:'买方最低单价比例',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'buyMaxPrice',title:'买方最高单价比例',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'tradeTimeOut',title:'交易超过时间',columnClass:'text-center',hideType:'xs'}
    ,{id:'needShopMoney',title:'开店保证金',columnClass:'text-center',hideType:'xs'}
    ,{id:'rewardNum',title:'抽奖金额',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
