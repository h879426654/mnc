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
				$("#editForm").kendoValidator();
				kendo.bind($("#editForm"), {});
				kendo.bind($("#grid"), {});
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

 </script>
</head>
<body>

<section class="content-header">
 <h1>${_urlMenuComment}</h1>
</section>
<section class="content">

    <form class="form-horizontal bv-form" id="editForm" method="post" enctype="multipart/form-data">
		<div class="box box-default">
			<div class="box-body">
				<div class="box box-success">
					<div class="box-header with-border">
						<h3 class="box-title"></h3>
						<div class="box-tools"></div>
					</div>
					<div class="box-body" style="width:60%;margin:auto;">
					<input type="hidden" name="id" />
					<div class="col-md-12">
							<div class="form-group has-feedback">
								<label for="shopName" class="col-sm-4 control-label"><spring:message code="shop.index.shopName" />：</label>
								<div class="col-sm-6">
									<input id="shopName" name="shopName" class="form-control"/>
								</div>
							</div>
					</div>
					<div class="col-md-12">
							<%--<div class="form-group has-feedback">
								<label for="shopStar" class="col-sm-4 control-label"><spring:message code="shop.index.shopStar" />：</label>
								<div class="col-sm-6">
									<input id="shopStar" name="shopStar" class="form-control"/>
								</div>
							</div>--%>
						</div>
						<div class="col-md-12">
							<div class="form-group has-feedback">
								<label for="shopNumber" class="col-sm-4 control-label"><spring:message code="shop.index.shopNumber" />：</label>
								<div class="col-sm-6">
									<input id="shopNumber" name="shopNumber" class="form-control" readonly/>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group has-feedback">
								<label for="shopImg" class="col-sm-4 control-label"><spring:message code="shop.index.shopLogo" />：</label>
								<div class="col-sm-6">
									 <input type="hidden" name="shopLogo" id="imgEditUrl" class="form-control" required="required" data-required-msg="商家图片为必填项">
   									<tags:bootstrap_uploader_image _multiple="" _id="imgEdit" _ownerClass="MallShop.shopLogo" _ownerId="idEdit" _bindUrlId="imgEditUrl" />
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group has-feedback">
								<label for="shopStatus" class="col-sm-4 control-label"><spring:message code="product.index.status" />：</label>
								<div class="col-sm-6">
									<input type="text" name="shopStatus" data-role="dropdownlist" data-required-msg="商家状态为必填项"
               data-value-field="code" data-text-field="name" class="form-control"
               data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
     		transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/SHOP_STATUS.do'}}}">
								</div>
							</div>
						</div>
					<div class="col-md-12">
							<div class="form-group has-feedback">
								<label for="shopService" class="col-sm-4 control-label"><spring:message code="shop.index.shopService" />：</label>
								<div class="col-sm-6">
									<input id="shopService" name="shopService" class="form-control"/>
								</div>
							</div>
						</div>						
						<div class="col-md-12">
							<div class="form-group has-feedback">
								<label for="shopAddress" class="col-sm-4 control-label"><spring:message code="shop.index.shopAddress" />：</label>
								<div class="col-sm-6">
									<textarea id="shopAddress" name="shopAddress" class="form-control">
									</textarea>
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
 <!-- 表格{ -->
  <tags:swgrid _loadURL="showBusinessById.do">
   [
    {id:'shopName',title:'<spring:message code="shop.index.shopName" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'shopStar',title:'<spring:message code="shop.index.shopStar" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'shopLogo',title:'<spring:message code="shop.index.shopLogo" />',columnClass:'text-center',hideType:'xs',resolution:formatImg}
    ,{id:'shopNumber',title:'<spring:message code="shop.index.shopNumber" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'shopStatus',title:'<spring:message code="product.index.status" />',columnClass:'text-center',hideType:'xs',resolution:formatStatus}
    ,{id:'shopService',title:'<spring:message code="shop.index.shopService" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'shopAddress',title:'<spring:message code="shop.index.shopAddress" />',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
<script>
    function formatStatus(value, record) {
		if (value==1){
		    return '营业';
		}else {
		    return '停业';
		}
    }
</script>

</body>
</html>
