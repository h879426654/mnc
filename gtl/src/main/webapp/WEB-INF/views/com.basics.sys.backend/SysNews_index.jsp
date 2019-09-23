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
 </script>
</head>
<body>
<tags:form_add_dialog>
  <tags:form_group _title="规则ID：">
   <input type="text" name="id" class="form-control" required="required" data-required-msg="规则ID为必填项">
  </tags:form_group>
  <tags:form_group _title="新闻标题：">
   <input type="text" name="newsTitle" class="form-control" required="required" data-required-msg="新闻标题为必填项">
  </tags:form_group>
  <tags:form_group _title="新闻图片：">
   <input type="text" name="newsImg" class="form-control" required="required" data-required-msg="新闻图片为必填项">
  </tags:form_group>
  <tags:form_group _title="新闻内容：">
   <input type="text" name="newsContext" class="form-control" required="required" data-required-msg="新闻内容为必填项">
  </tags:form_group>
  <tags:form_group _title="新闻权重：">
   <input type="text" name="newsSort" class="form-control" required="required" data-required-msg="新闻权重为必填项">
  </tags:form_group>
  <tags:form_group _title="新闻状态(1显示 0不显示)：">
   <input type="text" name="newsStatus" class="form-control" required="required" data-required-msg="新闻状态(1显示 0不显示)为必填项">
  </tags:form_group>
  <tags:form_group _title="浏览量：">
   <input type="text" name="newsReadNum" class="form-control" required="required" data-required-msg="浏览量为必填项">
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
</tags:form_add_dialog>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="新闻标题：">
   <input type="text" name="newsTitle" class="form-control" required="required" data-required-msg="新闻标题为必填项">
  </tags:form_group>
  <tags:form_group _title="新闻图片：">
   <input type="text" name="newsImg" class="form-control" required="required" data-required-msg="新闻图片为必填项">
  </tags:form_group>
  <tags:form_group _title="新闻内容：">
   <input type="text" name="newsContext" class="form-control" required="required" data-required-msg="新闻内容为必填项">
  </tags:form_group>
  <tags:form_group _title="新闻权重：">
   <input type="text" name="newsSort" class="form-control" required="required" data-required-msg="新闻权重为必填项">
  </tags:form_group>
  <tags:form_group _title="新闻状态(1显示 0不显示)：">
   <input type="text" name="newsStatus" class="form-control" required="required" data-required-msg="新闻状态(1显示 0不显示)为必填项">
  </tags:form_group>
  <tags:form_group _title="浏览量：">
   <input type="text" name="newsReadNum" class="form-control" required="required" data-required-msg="浏览量为必填项">
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
  <tags:form_group _title="规则ID：">
   <span name="id"></span>
  </tags:form_group>
  <tags:form_group _title="新闻标题：">
   <span name="newsTitle"></span>
  </tags:form_group>
  <tags:form_group _title="新闻图片：">
   <span name="newsImg"></span>
  </tags:form_group>
  <tags:form_group _title="新闻内容：">
   <span name="newsContext"></span>
  </tags:form_group>
  <tags:form_group _title="新闻权重：">
   <span name="newsSort"></span>
  </tags:form_group>
  <tags:form_group _title="新闻状态(1显示 0不显示)：">
   <span name="newsStatus"></span>
  </tags:form_group>
  <tags:form_group _title="浏览量：">
   <span name="newsReadNum"></span>
  </tags:form_group>
  <tags:form_group _title="版本号：">
   <span name="versionNum"></span>
  </tags:form_group>
  <tags:form_group _title="是否删除（1是 0否）：">
   <span name="flagDel"></span>
  </tags:form_group>
  <tags:form_group _title="创建时间：">
   <span name="createTime"></span>
  </tags:form_group>
  <tags:form_group _title="创建人：">
   <span name="createUser"></span>
  </tags:form_group>
  <tags:form_group _title="修改人：">
   <span name="modifyUser"></span>
  </tags:form_group>
  <tags:form_group _title="修改时间：">
   <span name="modifyTime"></span>
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
     {id:'id',title:'规则ID',columnClass:'text-center'}
    ,{id:'newsTitle',title:'新闻标题',columnClass:'text-center',hideType:'xs'}
    ,{id:'newsImg',title:'新闻图片',columnClass:'text-center',hideType:'xs'}
    ,{id:'newsContext',title:'新闻内容',columnClass:'text-center',hideType:'xs'}
    ,{id:'newsSort',title:'新闻权重',columnClass:'text-center',hideType:'xs'}
    ,{id:'newsStatus',title:'新闻状态(1显示 0不显示)',columnClass:'text-center',hideType:'xs'}
    ,{id:'newsReadNum',title:'浏览量',columnClass:'text-center',hideType:'xs'}
    ,{id:'versionNum',title:'版本号',columnClass:'text-center',hideType:'xs'}
    ,{id:'flagDel',title:'是否删除（1是 0否）',columnClass:'text-center',hideType:'xs'}
    ,{id:'createTime',title:'创建时间',columnClass:'text-center',hideType:'xs'}
    ,{id:'createUser',title:'创建人',columnClass:'text-center',hideType:'xs'}
    ,{id:'modifyUser',title:'修改人',columnClass:'text-center',hideType:'xs'}
    ,{id:'modifyTime',title:'修改时间',columnClass:'text-center',hideType:'xs'}
   ]</tags:swgrid>
 </div>
 <!-- 表格} -->
</section>
</body>
</html>
