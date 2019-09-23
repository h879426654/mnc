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
  <tags:form_group _title="完成时间：">
   <span id="tradeFinishTimeName"></span>
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
   <tags:form_grid_toolbar_view/>
  </div>
  <tags:swgrid _loadURL="selectTradeRecord.do">
   [
     {id:'tradeSerial',title:'交易流水号',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerName',title:'发布用户',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerRealName',title:'出售姓名',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerPhone',title:'出售手机号',columnClass:'text-center',hideType:'xs'}
    ,{id:'moneyNum',title:'数量',columnClass:'text-center',hideType:'xs'}
    ,{id:'lockMoneyNum',title:'卖方应冻结平台币',columnClass:'text-center',hideType:'xs'}
    ,{id:'tradeStatusName',title:'交易状态',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerBuyName',title:'购买用户',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerBuyRealName',title:'购买姓名',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerBuyPhone',title:'购买手机号',columnClass:'text-center',hideType:'xs'}
    ,{id:'tradePayTime',title:'支付时间',columnClass:'text-center',hideType:'xs',resolution:formatDate}
    ,{id:'tradeFinishTime',title:'完成时间',columnClass:'text-center',hideType:'xs',resolution:formatDate}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
