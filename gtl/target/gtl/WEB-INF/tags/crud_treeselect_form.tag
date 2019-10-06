<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@attribute name="_width" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_height" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@include file="/WEB-INF/views/taglib.jsp"%>
<c:set var="_$widthField" value="480px" />
<c:if test="${not empty _width}">
 <c:set var="_$widthField" value="${_width}" />
</c:if>
<c:set var="_$heightField" value="480px" />
<c:if test="${not empty _height}">
 <c:set var="_$heightField" value="${_height}" />
</c:if>
<!-- 添加、编辑对话框 -->
<div id="treeselect_dialog" class="easyui-dialog" data-options="closed:true,modal:true,title:' ',iconCls:'icon-save'"
 style="width: ${_$widthField}; height: ${_$heightField}; padding: 10px">
 <form action="" id="treeselect_form" method="post">
  <jsp:doBody />
 </form>
</div>