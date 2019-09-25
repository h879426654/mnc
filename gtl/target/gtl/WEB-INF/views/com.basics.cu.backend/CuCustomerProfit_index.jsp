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
  
  function formatProfitType(value, record, column, grid, dataNo, columnNo) {
		if(1==value){
			return "收入";
		} else {
			return "支出";
		}
	}
  function formatProfitStatus(value, record, column, grid, dataNo, columnNo) {
		if(1==value){
			return "MC";
		} else if(2 == value){
			return "MP";
		} else if(3 == value){
			return "MNC";
		} else {
			return "";
		}
	}
  
  
  
 </script>
</head>
<body>
<tags:form_view_dialog>
  <tags:form_group _title="用户：">
   <span name="customerName"></span>
  </tags:form_group>
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
   <tags:form_grid_toolbar_view/>
  </div>
  <tags:swgrid _loadURL="selectCustomerProfitInfo.do">
   [
     {id:'customerName',title:'用户',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerPhone',title:'用户手机号',columnClass:'text-center',hideType:'xs'}
    ,{id:'profitType',title:'收益类型',columnClass:'text-center',hideType:'xs', resolution:formatProfitType}
    ,{id:'profitNum',title:'领取收益',columnClass:'text-center',hideType:'xs'}
    ,{id:'profitStatus',title:'收益状态',columnClass:'text-center',hideType:'xs', resolution:formatProfitStatus}
    ,{id:'profitHavedTime',title:'领取时间',columnClass:'text-center',hideType:'xs', resolution:formatDate}
    ,{id:'profitRemark',title:'收益说明',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
