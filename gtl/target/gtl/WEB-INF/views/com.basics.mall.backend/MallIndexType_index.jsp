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
   },
   onAddToGenId : true
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
 </script>
</head>
<body>
<tags:form_add_dialog _multipart="true">
  <input type="hidden" name="id" id="idAdd" />
  <tags:form_group _title="模块说明：">
   <input type="text" name="typeTitle" class="form-control" required="required" data-required-msg="模块说明为必填项">
  </tags:form_group>
  <tags:form_group _title="图片地址：">
   <input type="hidden" name="typeImg" id="imgAddUrl" class="form-control" required="required" data-required-msg="请上传图片">
   <tags:bootstrap_uploader_image _multiple="" _id="imgAdd" _ownerClass="MallIndexType.typeImg" _ownerId="idAdd" _bindUrlId="imgAddUrl" />
  </tags:form_group>
  <tags:form_group _title="链接地址：">
   <input type="text" name="typeUrl" class="form-control" required="required" data-required-msg="链接地址为必填项">
  </tags:form_group>
  <tags:form_group _title="权重：">
   <input type="text" name="typeSort" class="form-control" required="required" data-required-msg="权重为必填项">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog _multipart="true">
 <input type="hidden" name="id" id="idEdit" />
 <tags:form_group _title="模块说明：">
   <input type="text" name="typeTitle" class="form-control" required="required" data-required-msg="模块说明为必填项">
  </tags:form_group>
  <tags:form_group _title="图片地址：">
   <input type="hidden" name="typeImg" id="imgEditUrl" class="form-control" required="required" data-required-msg="请上传图片" />
   <tags:bootstrap_uploader_image _multiple="" _id="imgEdit" _ownerClass="MallIndexType.typeImg" _ownerId="idEdit" _bindUrlId="imgEditUrl" />
  </tags:form_group>
  <tags:form_group _title="链接地址：">
   <input type="text" name="typeUrl" class="form-control" required="required" data-required-msg="链接地址为必填项">
  </tags:form_group>
  <tags:form_group _title="权重：">
   <input type="text" name="typeSort" class="form-control" required="required" data-required-msg="权重为必填项">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="模块说明：">
   <span name="typeTitle"></span>
  </tags:form_group>
  <tags:form_group _title="图片地址：">
   <img name="typeImg" width='100%'></img>
  </tags:form_group>
  <tags:form_group _title="链接地址：">
   <span name="typeUrl"></span>
  </tags:form_group>
  <tags:form_group _title="权重：">
   <span name="typeSort"></span>
  </tags:form_group>
  <tags:form_group _title="版本号：">
   <span name="versionNum"></span>
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
     {id:'typeTitle',title:'模块说明',columnClass:'text-center',hideType:'xs'}
    ,{id:'typeImg',title:'图片',columnClass:'text-center',hideType:'xs',resolution:formatImg}
    ,{id:'typeUrl',title:'链接地址',columnClass:'text-center',hideType:'xs'}
    ,{id:'typeSort',title:'权重',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
