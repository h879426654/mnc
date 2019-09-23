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
  <tags:form_group _title="交易ID：">
   <input type="text" name="id" class="form-control" required="required" data-required-msg="交易ID为必填项">
  </tags:form_group>
  <tags:form_group _title="交易流水号：">
   <input type="text" name="tradeSerial" class="form-control" required="required" data-required-msg="交易流水号为必填项">
  </tags:form_group>
  <tags:form_group _title="发布用户ID：">
   <input type="text" name="customerId" class="form-control" required="required" data-required-msg="发布用户ID为必填项">
  </tags:form_group>
  <tags:form_group _title="平台币数量：">
   <input type="text" name="moneyNum" class="form-control" required="required" data-required-msg="平台币数量为必填项">
  </tags:form_group>
  <tags:form_group _title="交易状态(1待支付2待确认3交易取消4交易完成)：">
   <input type="text" name="tradeStatus" class="form-control" required="required" data-required-msg="交易状态(1待支付2待确认3交易取消4交易完成)为必填项">
  </tags:form_group>
  <tags:form_group _title="购买用户ID：">
   <input type="text" name="customerBuyId" class="form-control" required="required" data-required-msg="购买用户ID为必填项">
  </tags:form_group>
  <tags:form_group _title="支付时间：">
   <input type="text" name="tradePayTime" class="form-control" required="required" data-required-msg="支付时间为必填项">
  </tags:form_group>
  <tags:form_group _title="交易完成时间：">
   <input type="text" name="tradeFinishTime" class="form-control" required="required" data-required-msg="交易完成时间为必填项">
  </tags:form_group>
  <tags:form_group _title="审核状态状态(1待审核 2审核通过 3审核不通过)：">
   <input type="text" name="applyStatus" class="form-control" required="required" data-required-msg="审核状态状态(1待审核 2审核通过 3审核不通过)为必填项">
  </tags:form_group>
  <tags:form_group _title="审核意见：">
   <input type="text" name="applyContext" class="form-control" required="required" data-required-msg="审核意见为必填项">
  </tags:form_group>
  <tags:form_group _title="审核时间：">
   <input type="text" name="applyTime" class="form-control" required="required" data-required-msg="审核时间为必填项">
  </tags:form_group>
  <tags:form_group _title="卖方应冻结平台币：">
   <input type="text" name="lockMoneyNum" class="form-control" required="required" data-required-msg="卖方应冻结平台币为必填项">
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
  <tags:form_group _title="交易流水号：">
   <input type="text" name="tradeSerial" class="form-control" required="required" data-required-msg="交易流水号为必填项">
  </tags:form_group>
  <tags:form_group _title="发布用户ID：">
   <input type="text" name="customerId" class="form-control" required="required" data-required-msg="发布用户ID为必填项">
  </tags:form_group>
  <tags:form_group _title="平台币数量：">
   <input type="text" name="moneyNum" class="form-control" required="required" data-required-msg="平台币数量为必填项">
  </tags:form_group>
  <tags:form_group _title="交易状态(1待支付2待确认3交易取消4交易完成)：">
   <input type="text" name="tradeStatus" class="form-control" required="required" data-required-msg="交易状态(1待支付2待确认3交易取消4交易完成)为必填项">
  </tags:form_group>
  <tags:form_group _title="购买用户ID：">
   <input type="text" name="customerBuyId" class="form-control" required="required" data-required-msg="购买用户ID为必填项">
  </tags:form_group>
  <tags:form_group _title="支付时间：">
   <input type="text" name="tradePayTime" class="form-control" required="required" data-required-msg="支付时间为必填项">
  </tags:form_group>
  <tags:form_group _title="交易完成时间：">
   <input type="text" name="tradeFinishTime" class="form-control" required="required" data-required-msg="交易完成时间为必填项">
  </tags:form_group>
  <tags:form_group _title="审核状态状态(1待审核 2审核通过 3审核不通过)：">
   <input type="text" name="applyStatus" class="form-control" required="required" data-required-msg="审核状态状态(1待审核 2审核通过 3审核不通过)为必填项">
  </tags:form_group>
  <tags:form_group _title="审核意见：">
   <input type="text" name="applyContext" class="form-control" required="required" data-required-msg="审核意见为必填项">
  </tags:form_group>
  <tags:form_group _title="审核时间：">
   <input type="text" name="applyTime" class="form-control" required="required" data-required-msg="审核时间为必填项">
  </tags:form_group>
  <tags:form_group _title="卖方应冻结平台币：">
   <input type="text" name="lockMoneyNum" class="form-control" required="required" data-required-msg="卖方应冻结平台币为必填项">
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
  <tags:form_group _title="交易ID：">
   <span name="id"></span>
  </tags:form_group>
  <tags:form_group _title="交易流水号：">
   <span name="tradeSerial"></span>
  </tags:form_group>
  <tags:form_group _title="发布用户ID：">
   <span name="customerId"></span>
  </tags:form_group>
  <tags:form_group _title="平台币数量：">
   <span name="moneyNum"></span>
  </tags:form_group>
  <tags:form_group _title="交易状态(1待支付2待确认3交易取消4交易完成)：">
   <span name="tradeStatus"></span>
  </tags:form_group>
  <tags:form_group _title="购买用户ID：">
   <span name="customerBuyId"></span>
  </tags:form_group>
  <tags:form_group _title="支付时间：">
   <span name="tradePayTime"></span>
  </tags:form_group>
  <tags:form_group _title="交易完成时间：">
   <span name="tradeFinishTime"></span>
  </tags:form_group>
  <tags:form_group _title="审核状态状态(1待审核 2审核通过 3审核不通过)：">
   <span name="applyStatus"></span>
  </tags:form_group>
  <tags:form_group _title="审核意见：">
   <span name="applyContext"></span>
  </tags:form_group>
  <tags:form_group _title="审核时间：">
   <span name="applyTime"></span>
  </tags:form_group>
  <tags:form_group _title="卖方应冻结平台币：">
   <span name="lockMoneyNum"></span>
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
     {id:'id',title:'交易ID',columnClass:'text-center'}
    ,{id:'tradeSerial',title:'交易流水号',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerId',title:'发布用户ID',columnClass:'text-center',hideType:'xs'}
    ,{id:'moneyNum',title:'平台币数量',columnClass:'text-center',hideType:'xs'}
    ,{id:'tradeStatus',title:'交易状态(1待支付2待确认3交易取消4交易完成)',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerBuyId',title:'购买用户ID',columnClass:'text-center',hideType:'xs'}
    ,{id:'tradePayTime',title:'支付时间',columnClass:'text-center',hideType:'xs'}
    ,{id:'tradeFinishTime',title:'交易完成时间',columnClass:'text-center',hideType:'xs'}
    ,{id:'applyStatus',title:'审核状态状态(1待审核 2审核通过 3审核不通过)',columnClass:'text-center',hideType:'xs'}
    ,{id:'applyContext',title:'审核意见',columnClass:'text-center',hideType:'xs'}
    ,{id:'applyTime',title:'审核时间',columnClass:'text-center',hideType:'xs'}
    ,{id:'lockMoneyNum',title:'卖方应冻结平台币',columnClass:'text-center',hideType:'xs'}
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
