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
	   if ('showGoodsInfo' == what) {
	  		showGoodsInfoDialog();//商品详情
	   }

   }
  };

  $(function() {
   kendo.bind($("#grid"), {});
   CrudApp.init(options);
   CrudApp.on('search');
   kendo.bind($("#showGoodsInfoGrid"), {});
  });
  
  function showGoodsInfoDialog(){
	  var rows = CrudApp.getSelections();
	   if (rows.length > 1) {
	    CrudApp.warning('查看只能选择一条记录!');
	    return false;
	   }
	   if (rows.length == 0) {
	    CrudApp.warning('请选择要查看的记录!');
	    return false;
	   }
	   var row = rows[0];
	  onDialogOpen('showGoodsInfoDialog');
	  $("#showGoodsInfoGrid").data('kendoSwGrid').load({"orderId":row['id']});
  }
  
function formatName(value, record, column, grid, dataNo, columnNo) {
      if (value.length>20){
          var content = '';
          content += value.substr(0,20)+ '...' ;
          return content;
      }else {
          return value;
      }
  }
  
  
//打开窗口
  function onDialogOpen(dialogId) {
   $('#' + dialogId).modal({
    backdrop : 'static',
    keyboard : false
   });
  }
  
  function cancleOrder() {
	  var rows = CrudApp.getSelections();
	  if (rows.length != 1) {
	   CrudApp.warning('只能选择一个订单!');
	   return;
	  }
	  var row = rows[0];
	  var id = row.id;
      CrudApp.confirm('您确定取消此订单吗', function(){
          $.post('${contextPath}/backend/or/orOrder/cancelOrder.do',{id:id},function(data){
              var data = $.parseJSON(data);
              if (data.success) {
                  $("#batchUpdateViewDialog").modal('hide');
                  CrudApp.success('操作成功!');
                  CrudApp.onReflash();
              } else {
                  CrudApp.alert(data.message);
              }
          });
	  });
  }
 </script>
</head>
<body>
<!-- 商品详情 -->
 <tags:form_view_dialog_diy _id="showGoodsInfoDialog">
  <tags:swgrid_diy _grid="showGoodsInfoGrid" _loadURL="showProductListByOrderId.do" _check="false">
  [
  {id:'productName',title:'商品',columnClass:'text-center',hideType:'xs',resolution:formatName}
  ,{id:'combinationContext',title:'规格',columnClass:'text-center',hideType:'xs'}
  ,{id:'productImg',title:'封面图',columnClass:'text-center',hideType:'xs',resolution:formatImg}
  ,{id:'productPrice',title:'商品价格',columnClass:'text-center',hideType:'xs'}
  ,{id:'productNum',title:'商品数量',columnClass:'text-center',hideType:'xs'}
  ]</tags:swgrid_diy>
 </tags:form_view_dialog_diy>
 <tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="快递单号：">
   <input type="text" name="orderLogisticsNum" class="form-control" required="required" data-required-msg="物流单号">
  </tags:form_group>
</tags:form_edit_dialog>

<tags:form_view_dialog>
 <tags:form_group _title="用户名：">
   <span name="customerName"></span>
  </tags:form_group>
  <tags:form_group _title="订单总价：">
   <span name="orderTotalPrice"></span>
  </tags:form_group>
  <tags:form_group _title="订单余额支付金额：">
   <span name="orderBalancePrice"></span>
  </tags:form_group>
  <tags:form_group _title="支付金额：">
   <span name="orderPayPrice"></span>
  </tags:form_group>
  <tags:form_group _title="支付方式：">
   <span name="orderPayTypeName"></span>
  </tags:form_group>
  <tags:form_group _title="交易流水号：">
   <span name="orderNumber"></span>
  </tags:form_group>
  <tags:form_group _title="订单支付时间：">
   <span name="orderPayTime"></span>
  </tags:form_group>
  <tags:form_group _title="订单状态：">
   <span name="orderStatusName"></span>
  </tags:form_group>
  <tags:form_group _title="物流公司名称：">
   <span name="orderLogisticsName"></span>
  </tags:form_group>
  <tags:form_group _title="物流单号：">
   <span name="orderLogisticsNum"></span>
  </tags:form_group>
  <tags:form_group _title="收货人姓名：">
   <span name="orderReceiver"></span>
  </tags:form_group>
  <tags:form_group _title="收货人联系方式：">
   <span name="orderReceiverPhone"></span>
  </tags:form_group>
  <tags:form_group _title="收货详细地址：">
   <span name="addressInfo"></span>
  </tags:form_group>
  <tags:form_group _title="订单确认时间：">
   <span name="orderFinishTime"></span>
  </tags:form_group>
  <tags:form_group _title="买家备注：">
   <span name="orderRemark"></span>
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
   	 <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('showGoodsInfo');">
	  <span class="glyphicon glyphicon-eye-open"></span> 查看商品
	 </button>
  	<button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('cancleOrder');" >
	  	<span class="glyphicon glyphicon-pencil"></span> 取消订单
	 </button> 
  </div>
 <tags:swgrid>
   [
     {id:'shopName',title:'商家',columnClass:'text-center',hideType:'xs'}
     ,{id:'customerName',title:'用户名',columnClass:'text-center',hideType:'xs'}
     ,{id:'customerPhone',title:'手机号',columnClass:'text-center',hideType:'xs'}
    ,{id:'orderTotalPrice',title:'订单总价',columnClass:'text-center',hideType:'xs'}
    ,{id:'orderPayPrice',title:'支付金额',columnClass:'text-center',hideType:'xs'}
    ,{id:'orderNumber',title:'交易流水号',columnClass:'text-center',hideType:'xs'}
    ,{id:'orderPayTime',title:'订单支付时间',columnClass:'text-center',hideType:'xs',resolution:formatDate}
    ,{id:'orderStatusName',title:'订单状态',columnClass:'text-center',hideType:'xs'}
    ,{id:'orderLogisticsName',title:'物流公司名称',columnClass:'text-center',hideType:'xs'}
    ,{id:'orderLogisticsNum',title:'物流单号',columnClass:'text-center',hideType:'xs'}
    ,{id:'orderReceiver',title:'收货人姓名',columnClass:'text-center',hideType:'xs'}
    ,{id:'orderReceiverPhone',title:'收货人联系方式',columnClass:'text-center',hideType:'xs'}
    ,{id:'location',title:'收货详细地址',columnClass:'text-center',hideType:'xs'}
    ,{id:'orderFinishTime',title:'订单确认时间',columnClass:'text-center',hideType:'xs',resolution:formatDate}
    ,{id:'orderRemark',title:'买家备注',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
 </section>
</body>
</html>
