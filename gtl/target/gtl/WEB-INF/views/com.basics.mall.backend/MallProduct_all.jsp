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
  
  function formatName(value, record, column, grid, dataNo, columnNo) {
      if (value.length>15){
          var content = '';
          content += value.substr(0,15)+ '...' ;
          return content;
      }else {
          return value;
      }
  }
  
 </script>
</head>
<body>
<tags:form_edit_dialog _multipart="true">
 <input type="hidden" name="id" id="idEdit"/>
  <tags:form_group _title="商品名称：">
   <input type="text" name="productName" class="form-control" required="required" data-required-msg="商品名称为必填项" readonly>
  </tags:form_group>
  <tags:form_group _title="商品状态：">
        <input type="text" name="productStatus" data-role="dropdownlist"
               data-value-field="code" data-text-field="name" class="form-control"
               data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
     	transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/PRODUCT_STATUS.do'}}}">
  </tags:form_group>
  <tags:form_group _title="销量：">
   <input type="text" name="productSale" class="form-control" required="required" data-required-msg="销量为必填项">
  </tags:form_group>
</tags:form_edit_dialog>

<tags:form_view_dialog>
  <tags:form_group _title="商品名称：">
   <span name="productName"></span>
  </tags:form_group>
  <tags:form_group _title="商品一级分类：">
   <span name="productFirstClassifyName"></span>
  </tags:form_group>
  <tags:form_group _title="商品二级分类：">
   <span name="productSecondClassifyName"></span>
  </tags:form_group>
  <tags:form_group _title="商品状态：">
   <span name="productStatusName"></span>
  </tags:form_group>
  <tags:form_group _title="封面图：">
   <img name="productImg" width="80%"/>
  </tags:form_group>
  <tags:form_group _title="商品价格：">
   <span name="productPrice"></span>
  </tags:form_group>
  <tags:form_group _title="商品成本价：">
   <span name="productCost"></span>
  </tags:form_group>
  <tags:form_group _title="运费：">
   <span name="productFreight"></span>
  </tags:form_group>
  <tags:form_group _title="销量：">
   <span name="productSale"></span>
  </tags:form_group>
  <tags:form_group _title="库存：">
   <span name="productStock"></span>
  </tags:form_group>
  <tags:form_group _title="商品详情描述：">
   <p name="productContext"></p>
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
       <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('edit');">
           <span class="glyphicon glyphicon-pencil"></span> 编辑
       </button>
       <button type="button" class="btn btn-default btn-sm label-danger  distance" onclick="CrudApp.on('delete')">
           <span class="glyphicon glyphicon-remove"></span> 删除
       </button>
   </tags:form_grid_toolbar_view>
  </div>
  <tags:swgrid _loadURL="swgridByAdmin.do">
   [
     {id:'productName',title:'商品名称',columnClass:'text-center',hideType:'xs',resolution:formatName}
    ,{id:'shopName',title:'店名',columnClass:'text-center',hideType:'xs'}
    ,{id:'productFirstClassifyName',title:'一级分类',columnClass:'text-center',hideType:'xs'}
    ,{id:'productSecondClassifyName',title:'二级分类',columnClass:'text-center',hideType:'xs'}
    ,{id:'productStatus',title:'商品状态',columnClass:'text-center',hideType:'xs',resolution:formatGoodsStatus}
    ,{id:'productImg',title:'商品封面图',columnClass:'text-center',hideType:'xs',resolution:formatImg}
    ,{id:'productPrice',title:'价格',columnClass:'text-center',hideType:'xs'}
    ,{id:'productFreight',title:'运费',columnClass:'text-center',hideType:'xs'}
    ,{id:'productSale',title:'销量',columnClass:'text-center',hideType:'xs'}
    ,{id:'productStock',title:'库存',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
<script>
    var formatGoodsStatus = function(value, record) {
        if (value==1){
            record.goodsStatusName='待上架';
            return '待上架';
        }else if (value==2){
            record.goodsStatusName='上架中';
            return '上架中';
        } else {
            record.goodsStatusName='已下架';
            return '已下架';
        }
    }
</script>
</body>
</html>
