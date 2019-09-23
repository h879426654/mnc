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
			CrudApp.init(options);
			$.post('load.do',{},function(data) {
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
				url : 'modifyShopPass.do',
				onSubmit : function() {
					try {
						onWillAddFormSave();
					} catch (e) {
						CrudApp.alert(e);
					}
					if (!$(this).data("kendoValidator").validate()) {
						CrudApp.warning('请验证您的输入!');
						return false;
					}
					return true;
				},
				success : function(data) {
					var data = $.parseJSON(data);
					if (data.success) {
						CrudApp.alert('编辑成功！');
						window.location.href = 'pass.do'
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

    <section class="content-header">
		<h1>商家密码修改</h1>
	</section>
	<section class="content">
		<form class="form-horizontal bv-form" id="editForm" method="post">
			<div class="box box-default">
				<div class="box-body">
					<div class="box box-success">
						<div class="box-header with-border">
							<h3 class="box-title">基本信息</h3>
							<div class="box-tools"></div>
						</div>
						<div class="box-body">
							<input type="hidden" name="id" />
							<div class="col-md-12">
								<div class="form-group has-feedback">
									<label for="password" class="col-sm-2 control-label">原密码</label>
									<div class="col-sm-9">
										<input type="password" id="oldPassword" name="oldPassword" class="form-control" required="required">
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group has-feedback">
									<label for="passwordNew" class="col-sm-2 control-label">新密码</label>
									<div class="col-sm-9">
										<input type="password" id="newPassword" name="newPassword" class="form-control" required="required">
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group has-feedback">
									<label for="passwordConfirmed" class="col-sm-2 control-label">确认密码</label>
									<div class="col-sm-9">
										<input type="password" id="code" name="code" class="form-control" required="required">
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
	</section>

</body>
</html>
