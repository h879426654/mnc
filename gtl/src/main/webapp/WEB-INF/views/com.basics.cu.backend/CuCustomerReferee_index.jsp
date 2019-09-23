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
      kendo.bind($("#recommendZTree"), {});
  });
 </script>
</head>
<body>
<tags:form_add_dialog>
  <tags:form_group _title="用户ID：">
   <input type="text" name="id" class="form-control" required="required" data-required-msg="用户ID为必填项">
  </tags:form_group>
  <tags:form_group _title="推荐人ID：">
   <input type="text" name="refereeId" class="form-control" required="required" data-required-msg="推荐人ID为必填项">
  </tags:form_group>
</tags:form_add_dialog>
<tags:form_edit_dialog>
 <input type="hidden" name="id" />
  <tags:form_group _title="推荐人ID：">
   <input type="text" name="refereeId" class="form-control" required="required" data-required-msg="推荐人ID为必填项">
  </tags:form_group>
</tags:form_edit_dialog>
<tags:form_view_dialog>
  <tags:form_group _title="用户ID：">
   <span name="id"></span>
  </tags:form_group>
  <tags:form_group _title="推荐人ID：">
   <span name="refereeId"></span>
  </tags:form_group>
</tags:form_view_dialog>
<section class="content-header">
 <h1>${_urlMenuComment}</h1>
</section>
<section class="content">
    <div class="box-body">
        <div class="row">
            <div class="col-md-6">
                <tags:form_group _title="模糊查询:">
                    <input type="text" class="form-control" name="searchKey" id="searchKey" placeholder="模糊查询">
                </tags:form_group>
            </div>
        </div>
    </div>
    <div class="box-footer">
        <input type="button" class="btn btn-default btn-sm label-primary  distance" onclick="searchTree()" value="查询" />
    </div>
    <div id="recommendZTree">
        <%--<ul class="ztree" data-url="${contextPath}/backend/cu/cuCustomerRecommend/ztree.do" data-check-enable=false
            data-expand-all=false data-role="ztree">
        </ul>--%>
        <ul id="recommendTree" class="ztree"></ul>
    </div>
</section>
<script>
    var zTreeObj;
    // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
    var setting = {
        async: {
            enable: true,
            url: "ztree.do",
            autoParam: ["customerPhone"],
            dataFilter: ajaxDataFilter
        }
    };
    function ajaxDataFilter(treeId, parentNode, responseData) {
        if (responseData) {
            console.log(responseData);
            responseData=responseData.item;
        }
        return responseData;
    };
    $(document).ready(function(){
        $.post("ztree.do",function(result){
            zTreeObj = $.fn.zTree.init($("#recommendTree"), setting, JSON.parse(result).item);
        });
    });
    function searchTree() {
        zTreeObj.destroy();
        var searchKey = $("#searchKey").val();
        $.post("ztree.do",{customerPhone:searchKey},function(result){
            zTreeObj = $.fn.zTree.init($("#recommendTree"), setting, JSON.parse(result).item);
        });
    }
</script>
</body>
</html>
