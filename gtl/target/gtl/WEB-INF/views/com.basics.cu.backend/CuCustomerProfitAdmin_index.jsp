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
  function formatProfitStatus(value, record, column, grid, dataNo, columnNo) {
	var status = "";
	if(1 == value) {
		status = "发放";
	} else if (2 == value ) {
		status = "回扣";	
	}
	return status;
  }
  function formatProfitType(value, record, column, grid, dataNo, columnNo) {
	var status = "";
	if(1 == value) {
		status = "MC";
	} else if (2 == value ) {
		status = "MNC";	
	} else if (3 == value ) {
		status = "MP";	
	}
	return status;
  }
  
  function delProfit() {
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
	  CrudApp.confirm("是否确定优化", function(){
		 $.post("del.do", {id:id},function(){
			 CrudApp.success('操作成功!');
			 window.location.reload(); 
		 });
		  
		  
		  
	  });
  }
  
 </script>
</head>
<body>
<tags:form_view_dialog>
  <tags:form_group _title="领取收益：">
   <span name="profitNum"></span>
  </tags:form_group>
  <tags:form_group _title="收益类型：">
   <span name="profitType"></span>
  </tags:form_group>
  <tags:form_group _title="收益状态(1收入 2支出)：">
   <span name="profitStatus"></span>
  </tags:form_group>
  <tags:form_group _title="领取时间：">
   <span name="profitHavedTime"></span>
  </tags:form_group>
  <tags:form_group _title="收益来源：">
   <span name="profitSource"></span>
  </tags:form_group>
  <tags:form_group _title="收益说明：">
   <span name="profitRemark"></span>
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
		<%-- <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('delProfit')">
	      <span class="glyphicon glyphicon-group"></span> 优化
	    </button>--%>
   </tags:form_grid_toolbar_view>
  </div>
  <tags:swgrid>
   [
     {id:'customerName',title:'用户',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerPhone',title:'手机号',columnClass:'text-center',hideType:'xs'}
    ,{id:'profitNum',title:'领取收益',columnClass:'text-center',hideType:'xs'}
    ,{id:'profitType',title:'收益类型',columnClass:'text-center',hideType:'xs', resolution:formatProfitType}
    ,{id:'profitStatus',title:'收益状态',columnClass:'text-center',hideType:'xs', resolution:formatProfitStatus}
    ,{id:'profitRemark',title:'收益说明',columnClass:'text-center',hideType:'xs'}
    ,{id:'userName',title:'操作员',columnClass:'text-center',hideType:'xs'}
    ,{id:'profitHavedTime',title:'操作时间',columnClass:'text-center',hideType:'xs', resolution:formatDate}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
