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
  
  function formatShopStatus(value, record, column, grid, dataNo, columnNo) {
		if(1==value){
			return "营业";
		} else if(2 == value) {
			return "停业";
		} else {
			return "";
		}
  }
  
  function showShopAptitude(){
	  if (!getSingleSelect()) {
		   return;
		  }
		  var id = getSingleSelect();
		  $.ajax({
		   type : "GET",
		   dataType : "json",
		   url : '${pageContext.request.contextPath}/backend/mall/mallShop/showShopAptitude/' + id + '.do',
		   success : function(data) {
			   $('.modal-body').html("");
			   $.each(data.data, function(index, value) {
			       $('.modal-body').append('<img src="'+value.url+'" style="width:80%">');
			   });
		   }
		  });
		  onDialogOpen('shopAptitude');
  }
  
//获取选取的唯一记录
  function getSingleSelect() {
   var rows = CrudApp.getSelections();
   if (rows.length > 1) {
    CrudApp.warning('查看只能选择一条记录!');
    return false;
   }
   if (rows.length == 0) {
    CrudApp.warning('请选择要查看的记录!');
    return false;
   }
   if (rows.length == 1) {
    var row = rows[0];
    return row['id'];
   }
  }
//打开窗口
  function onDialogOpen(dialogId) {
   $('#' + dialogId).modal({
    backdrop : 'static',
    keyboard : false
   });
  }
  
  function updateShopStatus(value, record, column, grid, dataNo, columnNo) {
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
	  CrudApp.confirm("是否改变商家状态", function(){
		 $.post("updateShopStatus.do", {id:id }, function(data){
			 var data = $.parseJSON(data);
			 if (data.success) {
			    CrudApp.success('操作成功!');
			    window.location.reload();
			 } else {
			    CrudApp.alert(data.message);
			 }
		 });
	  });
  }

  function deleteShop(value, record, column, grid, dataNo, columnNo) {
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
      CrudApp.confirm("是否删除商家", function(){
          $.post("deleteShop.do", {id:id }, function(data){
              var data = $.parseJSON(data);
              if (data.success) {
                  CrudApp.success('操作成功!');
                  window.location.reload();
              } else {
                  CrudApp.alert(data.message);
              }
          });
      });
  }
  
  
 </script>
</head>
<body>

	<div class="modal fade" id="shopAptitude" tabindex="-1" role="dialog" aria-labelledby="voucherLabel">
	  <div class="modal-dialog" role="document">
	   <div class="modal-content">
	    <div class="modal-header">
	     <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	      <span aria-hidden="true">&times;</span>
	     </button>
	     <h4 class="modal-title" id="priceHistoryLabel">商店资质</h4>
	    </div>
	    <div class="modal-body">
	    </div>
	    <div class="modal-footer">
	     <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	    </div>
	   </div>
	  </div>
	 </div>


<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="会员ID：">
   <input type="text" name="customerId" class="form-control" required="required" data-required-msg="会员ID为必填项">
  </tags:form_group>
  <tags:form_group _title="店铺名称：">
   <input type="text" name="shopName" class="form-control" required="required" data-required-msg="店铺名称为必填项">
  </tags:form_group>
  <tags:form_group _title="店铺信誉：">
   <input type="text" name="shopStar" class="form-control" required="required" data-required-msg="店铺信誉为必填项">
  </tags:form_group>
  <tags:form_group _title="店铺LOGO：">
   <input type="text" name="shopLogo" class="form-control" required="required" data-required-msg="店铺LOGO为必填项">
  </tags:form_group>
  <tags:form_group _title="店铺编号：">
   <input type="text" name="shopNumber" class="form-control" required="required" data-required-msg="店铺编号为必填项">
  </tags:form_group>
  <tags:form_group _title="店铺状态(1营业 2停业)：">
   <input type="text" name="shopStatus" class="form-control" required="required" data-required-msg="店铺状态(1营业 2停业)为必填项">
  </tags:form_group>
  <tags:form_group _title="店铺折扣比例：">
   <input type="text" name="shopDiscount" class="form-control" required="required" data-required-msg="店铺折扣比例为必填项">
  </tags:form_group>
  <tags:form_group _title="店铺说明：">
   <input type="text" name="shopService" class="form-control" required="required" data-required-msg="店铺说明为必填项">
  </tags:form_group>
  <tags:form_group _title="店铺地址：">
   <input type="text" name="shopAddress" class="form-control" required="required" data-required-msg="店铺地址为必填项">
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
  <tags:form_group _title="店铺ID：">
   <span name="id"></span>
  </tags:form_group>
  <tags:form_group _title="会员ID：">
   <span name="customerId"></span>
  </tags:form_group>
  <tags:form_group _title="店铺名称：">
   <span name="shopName"></span>
  </tags:form_group>
  <tags:form_group _title="店铺信誉：">
   <span name="shopStar"></span>
  </tags:form_group>
  <tags:form_group _title="店铺LOGO：">
   <img name="shopLogo" width="30%"></img>
  </tags:form_group>
  <tags:form_group _title="店铺营业执照：">
   <img name="shopLicence" width="100%"></img>
  </tags:form_group>
  <tags:form_group _title="店铺编号：">
   <span name="shopNumber"></span>
  </tags:form_group>
  <tags:form_group _title="店铺状态(1营业 2停业)：">
   <span name="shopStatus"></span>
  </tags:form_group>
  <tags:form_group _title="店铺折扣比例：">
   <span name="shopDiscount"></span>
  </tags:form_group>
  <tags:form_group _title="店铺说明：">
   <span name="shopService"></span>
  </tags:form_group>
  <tags:form_group _title="店铺地址：">
   <span name="shopAddress"></span>
  </tags:form_group>
</tags:form_view_dialog>
<section class="content-header">
 <h1>${_urlMenuComment}</h1>
</section>
<section class="content">
 <tags:form_search>
  <div class="col-md-6">
      <tags:form_group _title="国家：">
          <input type="text" name="countryId" id="country_add" data-role="dropdownlist" data-value-field="countryId" data-text-field="countryName" data-option-label="请选择"
                 class="form-control" data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
  transport: {read: {type:'POST',dataType:'json',contentType:'application/json',url: '${contextPath}/backend/sys/sysCountry/read.do'}
  ,parameterMap:function(options){return JSON.stringify(options);}}}" >
      </tags:form_group>
   <tags:form_group _title="模糊查询:">
    <input type="text" class="form-control" name="q" placeholder="模糊查询">
   </tags:form_group>
  </div>
 </tags:form_search>
 <!-- 表格{ -->
 <div class="box">
  <div class="box-header with-border">
   <tags:form_grid_toolbar_view>
   		<button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('updateShopStatus');">
           <span class="glyphicon glyphicon-pencil"></span> 停业/营业
       </button>
   
   		<button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('showShopAptitude');">
           <span class="glyphicon glyphicon-pencil"></span> 查看资质
       	</button>
       <button type="button" class="btn btn-default btn-sm label-danger  distance" onclick="CrudApp.on('deleteShop')">
           <span class="glyphicon glyphicon-remove"></span> 删除
       </button>
   </tags:form_grid_toolbar_view>
  </div>
  <tags:swgrid _loadURL="showShopRecord.do">
   [
     {id:'customerName',title:'会员',columnClass:'text-center',hideType:'xs'}
    ,{id:'shopName',title:'店铺名称',columnClass:'text-center',hideType:'xs'}
    ,{id:'countryName',title:'国家',columnClass:'text-center',hideType:'xs'}
    ,{id:'shopStar',title:'店铺信誉',columnClass:'text-center',hideType:'xs'}
    ,{id:'shopLogo',title:'店铺LOGO',columnClass:'text-center',hideType:'xs',resolution:formatImg}
    ,{id:'shopLicence',title:'营业执照',columnClass:'text-center',hideType:'xs',resolution:formatImg}
    ,{id:'shopNumber',title:'店铺编号',columnClass:'text-center',hideType:'xs'}
    ,{id:'shopStatus',title:'店铺状态',columnClass:'text-center',hideType:'xs',resolution:formatShopStatus}
    ,{id:'shopService',title:'店铺说明',columnClass:'text-center',hideType:'xs'}
    ,{id:'shopAddress',title:'店铺地址',columnClass:'text-center',hideType:'xs'}
   ,{id:'applyStatus',title:'审核状态',columnClass:'text-center',hideType:'xs',resolution:formatApplyStatus}
   ,{id:'applyContext',title:'审核意见',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
