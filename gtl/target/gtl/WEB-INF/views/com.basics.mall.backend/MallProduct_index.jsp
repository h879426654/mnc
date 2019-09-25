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
      kendo.bind($("#operationProductGrid"), {});
      kendo.bind($("#modifyProductDialog"), {});
  });
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
  function formatModify(value, record) {
      var id =  record['combinationId'];
      return '<a onclick="formatOnclickModify(\''+id+'\',\''+record['productName']+'\',\''+record['combinationPrice']+'\','+record['stock']+',\''+record['combination']+'\')"><spring:message code="tag.form.grid.toolbar.edit" /><a>';
     // return `<a onclick="formatOnclickModify\('`+JSON.stringify(record)+`'\)">修改<a>`;
  }
  function formatOnclickModify(id,name,price,stock,combination) {
      var row={combinationId:id,productName:name,combinationPrice:price,stock:stock,combination:combination};
      $("#modifyProductForm").form('load', row);
      onDialogOpen('modifyProductDialog');
  }
  function modifyProductFun() {
      $.post("productModify.do",$('#modifyProductForm').serialize(),function(result){
          result = $.parseJSON(result);
          if (result.success){
              CrudApp.success('操作成功!');
              window.location.reload();
          }else {
              CrudApp.alert(result.message);
          }
      });
  }
 </script>
</head>
<body>
<tags:form_view_dialog_diy _id="operationProductDialog">
    <tags:swgrid_diy _grid="operationProductGrid" _loadURL="getProductDetail.do" _check="false">
        [
        {id:'productName',title:'<spring:message code="product.add.name" />',columnClass:'text-center',hideType:'xs'}
        ,{id:'combinationImg',title:'<spring:message code="product.add.img" />',columnClass:'text-center',hideType:'xs',resolution:formatImg}
        ,{id:'combinationPrice',title:'<spring:message code="product.add.price" />',columnClass:'text-center',hideType:'xs'}
        ,{id:'combination',title:'<spring:message code="product.index.kinds" />',columnClass:'text-center',hideType:'xs'}
        ,{id:'stock',title:'<spring:message code="product.add.stock" />',columnClass:'text-center',hideType:'xs'}
        ,{title:'  ',columnClass:'text-center',hideType:'xs',resolution:formatModify}
        ]</tags:swgrid_diy>
</tags:form_view_dialog_diy>

<tags:form_dialog _name="modifyProduct"  _title="商品修改" _onsave="modifyProductFun">
    <input type="hidden" name="combinationId"/>
    <tags:form_group _title="商品名称">
        <input type="text" name="productName" class="form-control" required="required" data-required-msg="商品名称为必填项">
    </tags:form_group>
    <tags:form_group _title="规格：">
        <input type="text" name="combination" class="form-control" required="required" data-required-msg="商品规格为必填项" readonly>
    </tags:form_group>
    <tags:form_group _title="价格：">
        <input type="text" name="combinationPrice" class="form-control" required="required" data-required-msg="商品价格为必填项">
    </tags:form_group>
    <tags:form_group _title="库存：">
        <input type="text" name="stock" class="form-control" required="required" data-required-msg="库存为必填项">
    </tags:form_group>
</tags:form_dialog>

<tags:form_edit_dialog _multipart="true">
 <input type="hidden" name="id" id="idEdit"/>
  <tags:form_group _title="商品名称：">
   <input type="text" name="productName" class="form-control" required="required" data-required-msg="商品名称为必填项">
  </tags:form_group>
  <tags:form_group _title="商品一级分类：">
   <input type="text" name="productFirstClassifyName" class="form-control" readonly>
  </tags:form_group>
  <tags:form_group _title="商品二级分类：">
   <input type="text" name="productSecondClassifyName" class="form-control" readonly>
  </tags:form_group>
  <tags:form_group _title="商品状态：">
        <input type="text" name="productStatus" data-role="dropdownlist"
               data-value-field="code" data-text-field="name" class="form-control"
               data-source="{schema : {total: 'total',data : 'data'},serverFiltering : true,
     	transport: {read: {dataType: 'json',url: '${contextPath}/backend/app/appOption/options/PRODUCT_STATUS.do'}}}">
  </tags:form_group>
  <tags:form_group _title="封面图：">
	   <input type="hidden" name="productImg" id="imgEditUrl" class="form-control" required="required" data-required-msg="封面图为必填项">
	   <tags:bootstrap_uploader_image _multiple="" _id="imgEdit" _ownerClass="MallProduct.productImg" _ownerId="idEdit" _bindUrlId="imgEditUrl" />
  </tags:form_group>
<%--  <tags:form_group _title="商品价格：">
   <input type="text" name="productPrice" class="form-control" required="required" data-required-msg="商品价格为必填项">
  </tags:form_group>
  <tags:form_group _title="商品成本价：">
   <input type="text" name="productCost" class="form-control" required="required" data-required-msg="商品成本价为必填项">
  </tags:form_group>--%>
  <tags:form_group _title="商品详情描述：">
      <textarea style="width: 100%; height: 200px" required="required" required-data-msg="内容为必填项" data-role="kindeditor"
                class="kindeditor" name="productContext"></textarea>
  </tags:form_group>
 <%-- <tags:form_group _title="运费：">
   <input type="text" name="productFreight" class="form-control" required="required" data-required-msg="运费为必填项">
  </tags:form_group>--%>
  <tags:form_group _title="销量：">
   <input type="text" name="productSale" class="form-control" required="required" data-required-msg="销量为必填项" readonly>
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
    <input type="text" class="form-control" name="q" placeholder="<spring:message code="tag.form.grid.toolbar.fuzzyQuery" />">
   </tags:form_group>
  </div>
 </tags:form_search>
 <!-- 表格{ -->
 <div class="box">
  <div class="box-header with-border">
   <tags:form_grid_toolbar_view>
       <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('edit');">
           <span class="glyphicon glyphicon-pencil"></span> <spring:message code="tag.form.grid.toolbar.edit" />
       </button>
        <button type="button" class="btn btn-default btn-sm label-danger  distance" onclick="CrudApp.on('delete')">
		  <span class="glyphicon glyphicon-remove"></span> <spring:message code="tag.form.grid.toolbar.delete" />
		 </button>
   </tags:form_grid_toolbar_view>
  </div>
  <tags:swgrid _loadURL="showProductBusinessById.do">
   [
     {id:'productName',title:'<spring:message code="product.add.name" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'productFirstClassifyName',title:'<spring:message code="product.add.first.classify" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'productSecondClassifyName',title:'<spring:message code="product.add.second.classify" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'productStatus',title:'<spring:message code="product.index.status" />',columnClass:'text-center',hideType:'xs',resolution:formatGoodsStatus}
    ,{id:'productImg',title:'<spring:message code="product.add.img" />',columnClass:'text-center',hideType:'xs',resolution:formatImg}
    ,{id:'productPrice',title:'<spring:message code="product.add.price" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'productFreight',title:'<spring:message code="product.add.freight" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'productSale',title:'<spring:message code="product.index.sale" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'productStock',title:'<spring:message code="product.add.stock" />',columnClass:'text-center',hideType:'xs'}
    ,{id:'',title:'<spring:message code="product.index.operating" />',columnClass:'text-center',hideType:'xs',resolution:formatOperation}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
<script>
    function formatOperation(value,record) {
        var id=record['id'];
        var content = '';
        content +='<a href="javascript:void(0);" onclick="operationProduct(\''+id+'\')">';
        content += '编辑';
        content += '</a>';
        return content;
    }
    function operationProduct(id){
        var rows={id:id};
        onDialogOpen('operationProductDialog');
        $("#operationProductGrid").data('kendoSwGrid').load(rows);
    }
    //打开窗口
    function onDialogOpen(dialogId) {
        $('#' + dialogId).modal({
            backdrop : 'static',
            keyboard : false
        });
    }
</script>
</body>
</html>
