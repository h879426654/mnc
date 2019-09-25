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
  
  function formatImg(value, record, column, grid, dataNo, columnNo) {
      var content = '';
      content += '<img width="100" src="';
       content += value;
       content += '\">';
      return content;
  }
  
  function delShopAdvert() {
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
	  $.post('${contextPath}/backend/mall/mallShopAdvert/delShopAdvert.do',{id:id},function(data){
		  CrudApp.success('操作成功!');
		  window.location.reload()
	  });
  }
  
  function formatDel(value, record, column, grid, dataNo, columnNo) {
      if(0 == value) {
    	  return "否"
      } else {
    	  return "是";
      }
  }
  
  function formatOnclick(value, record, column, grid, dataNo, columnNo) {
	var id =  record['id'];
	return `<a onclick="findImgs\('`+id+`'\)">查看</a>`;	
  }
  

  
  var findImgs = function(id) {
		$.ajax({
			type : "GET",
			dataType : "json",
			url : '${pageContext.request.contextPath}/backend/mall/mallShopAdvert/findImgs/' + id + '.do',
			success : function(result) {
				var arr = result.data;
				if(0 < arr.length) {
					var html = "<div>";
					for(var i = 0; i < arr.length; i++) {
						html += "<img  src='"+arr[i].url+"'  width='60%' ></img><br/>";
					}
					html += "</div>";
					$('#imgsShow').html(html);
				}
			}
		});
		onDialogOpen('showImgsDialog');
  }
  
//打开窗口
  function onDialogOpen(dialogId) {
      $('#' + dialogId).modal({
          backdrop : 'static',
          keyboard : false
      });
  }
  
 </script>
</head>
<body>

<tags:form_view_dialog_diy _id="showImgsDialog">
   <div id="imgsShow"></div>
</tags:form_view_dialog_diy>

<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="商圈名称：">
   <input type="text" name="advertName" class="form-control" required="required" data-required-msg="商家名称为必填项">
  </tags:form_group>
  <tags:form_group _title="商圈介绍：">
   <input type="text" name="advertContext" class="form-control" required="required" data-required-msg="商家介绍为必填项">
  </tags:form_group>
  <tags:form_group _title="商圈封面图片：">
   <input type="text" name="advertImage" class="form-control" required="required" data-required-msg="商家封面图片为必填项">
  </tags:form_group>
  <tags:form_group _title="商圈分类ID：">
   <input type="text" name="advertClassifyId" class="form-control" required="required" data-required-msg="商家分类ID为必填项">
  </tags:form_group>
  <tags:form_group _title="联系方式：">
   <input type="text" name="advertPhone" class="form-control" required="required" data-required-msg="联系方式为必填项">
  </tags:form_group>
  <tags:form_group _title="省ID：">
   <input type="text" name="addressProvince" class="form-control" required="required" data-required-msg="省ID为必填项">
  </tags:form_group>
  <tags:form_group _title="市ID：">
   <input type="text" name="addressCity" class="form-control" required="required" data-required-msg="市ID为必填项">
  </tags:form_group>
  <tags:form_group _title="区域ID：">
   <input type="text" name="addressArea" class="form-control" required="required" data-required-msg="区域ID为必填项">
  </tags:form_group>
  <tags:form_group _title="地址：">
   <input type="text" name="advertAddress" class="form-control" required="required" data-required-msg="地址为必填项">
  </tags:form_group>
</tags:form_edit_dialog>
 
<tags:form_view_dialog>
  <tags:form_group _title="商圈名称：">
   <span name="advertName"></span>
  </tags:form_group>
  <tags:form_group _title="商圈介绍：">
   <span name="advertContext"></span>
  </tags:form_group>
  <tags:form_group _title="商圈封面图片：">
   <img name="advertImage"  width="60%" ></img>
  </tags:form_group>
  <tags:form_group _title="营业执照：">
   <img name="shopLicence"  width="100%" ></img>
  </tags:form_group>
  <tags:form_group _title="商圈分类：">
   <span name="advertClassifyName"></span>
  </tags:form_group>
  <tags:form_group _title="联系方式：">
   <span name="advertPhone"></span>
  </tags:form_group>
  <tags:form_group _title="省：">
   <span name="addressProvinceName"></span>
  </tags:form_group>
  <tags:form_group _title="市：">
   <span name="addressCityName"></span>
  </tags:form_group>
  <tags:form_group _title="区域：">
   <span name="addressAreaName"></span>
  </tags:form_group>
  <tags:form_group _title="地址：">
   <span name="advertAddress"></span>
  </tags:form_group>
  <tags:form_group _title="经度：">
   <span name="advertLongitude"></span>
  </tags:form_group>
  <tags:form_group _title="纬度：">
   <span name="advertLatitude"></span>
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
   	<button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('delShopAdvert')">
      <span class="glyphicon glyphicon-group"></span> 删除
     </button>
   </tags:form_grid_toolbar_view>
  </div>
  <tags:swgrid>
   [
     {id:'advertName',title:'商圈名称',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerName',title:'用户',columnClass:'text-center',hideType:'xs'}
    ,{id:'customerPhone',title:'手机号',columnClass:'text-center',hideType:'xs'}
    ,{id:'advertImage',title:'商圈封面图片',columnClass:'text-center',hideType:'xs',resolution:formatImg}
    ,{id:'shopLicence',title:'商圈营业执照',columnClass:'text-center',hideType:'xs',resolution:formatImg}
    ,{id:'advertClassifyName',title:'商圈分类',columnClass:'text-center',hideType:'xs'}
    ,{id:'advertPhone',title:'联系方式',columnClass:'text-center',hideType:'xs'}
     ,{id:'',title:'查看',columnClass:'text-center',hideType:'xs', resolution:formatOnclick}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
