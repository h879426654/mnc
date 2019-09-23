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
                var start = _data.item.convertStartTime;
                var startName = "";
                var end = _data.item.convertEndTime;
                var endName = "";
                if(null != start && "" != start) {
                    _data.item.convertStartTime = new Date(start).format('yyyy-MM-dd hh:mm:ss');
                }
                if(null != end && "" != end) {
                    _data.item.convertEndTime= new Date(end).format('yyyy-MM-dd hh:mm:ss');
                }
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
	  
	  function formatDateShow(value, record, column, grid, dataNo, columnNo) {
		  var start = record.convertStartTime;
		  var startName = "";
		  var end = record.convertEndTime;
		  var endName = "";
		  if(null != start && "" != start) {
				var startName = new Date(start).format('yyyy-MM-dd hh:mm:ss');
		  }
		  if(null != end && "" != end) {
				var endName = new Date(end).format('yyyy-MM-dd hh:mm:ss');
		  }
		  return startName+"  ~  "+endName;
	  }
  
  
 </script>
</head>
<body>
<section class="content-header">
 <h1>兑换总表</h1>
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
					<div class="box-body" style="width:60%;margin:auto;">
						<input type="hidden" name="id" />
						<input type="hidden" name="convertSerial" />
						<tags:form_group_edit _title="兑换主题:" _id="convertName"></tags:form_group_edit>
						<tags:form_group_edit _title="兑换说明:" _id="convertRemark"></tags:form_group_edit>
						<tags:form_group_edit _title="总量:" _id="convertTotalNum"></tags:form_group_edit>
						<tags:form_group_edit _title="兑换数量:" _id="convertNum"></tags:form_group_edit>
						<div class="col-md-12">
							<div class="form-group has-feedback">
								<label for="tradeApplyFlag" class="col-sm-4 control-label">开始时间：</label>
								<div class="col-sm-6">
									<input type="text" name="convertStartTime" data-role="datetimepicker" class="form-control" data-format="yyyy-MM-dd HH:mm:ss">
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group has-feedback">
								<label for="tradeApplyFlag" class="col-sm-4 control-label">结束时间：</label>
								<div class="col-sm-6">
									<input type="text" name="convertEndTime" data-role="datetimepicker" class="form-control" data-format="yyyy-MM-dd HH:mm:ss">
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
 <div class="box">
  <div class="box-header with-border">
  </div>
  <tags:swgrid>
   [
     {id:'convertSerial',title:'兑换流水号',columnClass:'text-center',hideType:'xs'}
    ,{id:'convertName',title:'兑换主题',columnClass:'text-center',hideType:'xs'}
    ,{id:'convertRemark',title:'兑换说明',columnClass:'text-center',hideType:'xs'}
     ,{id:'convertTotalNum',title:'总量',columnClass:'text-center',hideType:'xs'}
    ,{id:'convertNum',title:'剩余数量',columnClass:'text-center',hideType:'xs'}
    ,{id:'convertStartTime',title:'时间',columnClass:'text-center',hideType:'xs',resolution:formatDateShow}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
