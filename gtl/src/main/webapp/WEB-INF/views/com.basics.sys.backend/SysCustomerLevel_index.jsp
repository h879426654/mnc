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
  
  var formatMoneyScope = function(value, record, column, grid, dataNo, columnNo) {
	  return record.levelMinIntegral+" ~  "+record.levelMaxIntegral;
  }
  
  function formatTextShow(value, record, column, grid, dataNo, columnNo) {
	  var text = "";
	  if(null != value) {
		 var num = value*100;
		 text = num.toFixed(2) + "%";
	  }
	  return text;
  }
  
 </script>
</head>
<body>
<tags:form_add_dialog>
  <tags:form_group _title="等级名称：">
   <input type="text" name="levelName" class="form-control" required="required" data-required-msg="等级名称为必填项">
  </tags:form_group>
  <tags:form_group _title="英文等级名称：">
      <input type="text" name="levelEnglishName" class="form-control" required="required" data-required-msg="等级名称为必填项">
  </tags:form_group>
  <tags:form_group _title="韩文等级名称：">
      <input type="text" name="levelKoreanName" class="form-control" required="required" data-required-msg="等级名称为必填项">
  </tags:form_group>
  <tags:form_group _title="日文等级名称：">
      <input type="text" name="levelJapaneseName" class="form-control" required="required" data-required-msg="等级名称为必填项">
  </tags:form_group>
  <tags:form_group _title="积分范围：">
   	<input type="text" name="levelMinIntegral"  required="required" data-required-msg="最小值为必填项" style="border:1px solid black;background-color:transparent;width:15%">
   	&nbsp; ~ &nbsp;
    <input type="text" name="levelMaxIntegral"  required="required" data-required-msg="最大值为必填项" style="border:1px solid black;background-color:transparent;width:15%">
  </tags:form_group>
  <tags:form_group _title="MNC限购数量：">
      <input type="text" name="limitCoin" class="form-control" required="required" data-required-msg="MNC限购数量为必填项">
  </tags:form_group>
  <tags:form_group _title="直推人数：">
   <input type="text" name="salfNum" class="form-control" required="required" data-required-msg="直推人数为必填项">
  </tags:form_group>
  <tags:form_group _title="直推奖励比例：">
   <input type="text" name="salfRewardRate" class="form-control" required="required" data-required-msg="直推奖励比例为必填项">
  </tags:form_group>
  <tags:form_group _title="复投奖励比例：">
   <input type="text" name="recastRewardRate" class="form-control" required="required" data-required-msg="复投奖励比例必填项">
  </tags:form_group>
  <tags:form_group _title="团队支出奖励比例：">
   <input type="text" name="teamOutRewardRate" class="form-control" required="required" data-required-msg="团队支出奖励比例为必填项">
  </tags:form_group>
  <tags:form_group _title="团队支入奖励比例：">
   <input type="text" name="teamInRewardRate" class="form-control" required="required" data-required-msg="团队支入奖励比例为必填项">
  </tags:form_group>
  <tags:form_group _title="平级奖：">
   <input type="text" name="flatRewardRate" class="form-control" required="required" data-required-msg="团队支入奖励比例为必填项">
  </tags:form_group>
  <tags:form_group _title="兑换奖励：">
   <input type="text" name="exchangeRate" class="form-control" required="required" data-required-msg="团队支入奖励比例为必填项">
  </tags:form_group>
  <tags:form_group _title="层数：">
   <input type="number" name="floorNum" class="form-control" required="required" data-required-msg="团队支入奖励比例为必填项">
  </tags:form_group>
  <tags:form_group _title="等级权重：">
   <input type="text" name="levelSort" class="form-control" required="required" data-required-msg="等级权重为必填项">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="等级名称：">
   <input type="text" name="levelName" class="form-control" required="required" data-required-msg="等级名称为必填项">
  </tags:form_group>
  <tags:form_group _title="英文等级名称：">
      <input type="text" name="levelEnglishName" class="form-control" required="required" data-required-msg="等级名称为必填项">
  </tags:form_group>
  <tags:form_group _title="韩文等级名称：">
      <input type="text" name="levelKoreanName" class="form-control" required="required" data-required-msg="等级名称为必填项">
  </tags:form_group>
  <tags:form_group _title="日文等级名称：">
      <input type="text" name="levelJapaneseName" class="form-control" required="required" data-required-msg="等级名称为必填项">
  </tags:form_group>
  <tags:form_group _title="积分范围：">
   	<input type="text" name="levelMinIntegral"  required="required" data-required-msg="最小值为必填项" style="border:1px solid black;background-color:transparent;width:15%">
   	&nbsp; ~ &nbsp;
    <input type="text" name="levelMaxIntegral"  required="required" data-required-msg="最大值为必填项" style="border:1px solid black;background-color:transparent;width:15%">
  </tags:form_group>
  <tags:form_group _title="MNC限购数量：">
   <input type="text" name="limitCoin" class="form-control" required="required" data-required-msg="MNC限购数量为必填项">
  </tags:form_group>
  <tags:form_group _title="直推人数：">
   <input type="text" name="salfNum" class="form-control" required="required" data-required-msg="直推人数为必填项">
  </tags:form_group>
  <tags:form_group _title="直推奖励比例：">
   <input type="text" name="salfRewardRate" class="form-control" required="required" data-required-msg="直推奖励比例为必填项">
  </tags:form_group>
  <tags:form_group _title="复投奖励比例：">
   <input type="text" name="recastRewardRate" class="form-control" required="required" data-required-msg="复投奖励比例必填项">
  </tags:form_group>
  <tags:form_group _title="团队支出奖励比例：">
   <input type="text" name="teamOutRewardRate" class="form-control" required="required" data-required-msg="团队支出奖励比例为必填项">
  </tags:form_group>
  <tags:form_group _title="团队支入奖励比例：">
   <input type="text" name="teamInRewardRate" class="form-control" required="required" data-required-msg="团队支入奖励比例为必填项">
  </tags:form_group>
  <tags:form_group _title="平级奖：">
   <input type="text" name="flatRewardRate" class="form-control" required="required" data-required-msg="团队支入奖励比例为必填项">
  </tags:form_group>
  <tags:form_group _title="兑换奖励：">
   <input type="text" name="exchangeRate" class="form-control" required="required" data-required-msg="团队支入奖励比例为必填项">
  </tags:form_group>
  <tags:form_group _title="层数：">
   <input type="number" name="floorNum" class="form-control" required="required" data-required-msg="层数为必填项">
  </tags:form_group>
  <tags:form_group _title="等级权重：">
   <input type="text" name="levelSort" class="form-control" required="required" data-required-msg="等级权重为必填项">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="等级名称：">
   <span name="levelName"></span>
  </tags:form_group>
  <tags:form_group _title="等级最小值：">
   <span name="levelMinIntegral"></span>
  </tags:form_group>
  <tags:form_group _title="等级最大值：">
   <span name="levelMaxIntegral"></span>
  </tags:form_group>
  <tags:form_group _title="直推人数：">
   <span name="salfNum"></span>
  </tags:form_group>
  <tags:form_group _title="直推奖励比例：">
   <span name="salfRewardRate"></span>
  </tags:form_group>
  <tags:form_group _title="团队支出奖励比例：">
   <span name="teamOutRewardRate"></span>
  </tags:form_group>
  <tags:form_group _title="团队支入奖励比例：">
   <span name="teamInRewardRate"></span>
  </tags:form_group>
  <tags:form_group _title="等级权重：">
   <span name="levelSort"></span>
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
     {id:'levelName',title:'等级名称',columnClass:'text-center',hideType:'xs'}
    ,{id:'levelEnglishName',title:'英文名称',columnClass:'text-center',hideType:'xs'}
    ,{id:'levelKoreanName',title:'韩文名称',columnClass:'text-center',hideType:'xs'}
    ,{id:'levelJapaneseName',title:'日文名称',columnClass:'text-center',hideType:'xs'}
    ,{id:'levelMinIntegral',title:'持积分范围',columnClass:'text-center',hideType:'xs',resolution:formatMoneyScope}
    ,{id:'limitCoin',title:'MNC限购数量',columnClass:'text-center',hideType:'xs'}
    ,{id:'salfNum',title:'直推人数',columnClass:'text-center',hideType:'xs'}
    ,{id:'salfRewardRate',title:'直推奖励比例',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'recastRewardRate',title:'复投奖励比例',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'teamOutRewardRate',title:'团队支出奖励比例',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'teamInRewardRate',title:'团队支入奖励比例',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'flatRewardRate',title:'平级奖',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'exchangeRate',title:'兑换奖励',columnClass:'text-center',hideType:'xs',resolution:formatTextShow}
    ,{id:'floorNum',title:'层数',columnClass:'text-center',hideType:'xs'}
    ,{id:'levelSort',title:'等级权重',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
