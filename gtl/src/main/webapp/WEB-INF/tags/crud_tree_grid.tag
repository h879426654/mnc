<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@attribute name="_idField" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_treeField" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_url" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@include file="/WEB-INF/views/taglib.jsp"%>
<c:set var="_$idField" value="id" />
<c:if test="${not empty _idField}">
 <c:set var="_$idField" value="${_idField}" />
</c:if>
<c:set var="_$treeField" value="name" />
<c:if test="${not empty _treeField}">
 <c:set var="_$treeField" value="${_treeField}" />
</c:if>
<c:set var="_$url" value="list.do" />
<c:if test="${not empty _url}">
 <c:set var="_$url" value="${_url}" />
</c:if>
<!-- center{ -->
<div data-options="region:'center',border:false">
 <!-- 数据列表 -->
 <table id="grid" class="easyui-treegrid"
  data-options="
     nowrap : true,
     striped : true,
     toolbar : '#toolbar',
     fit : true,
     fitColumns : true,
     collapsible : true,
     url : '${_$url}',
     remoteSort : true,
     singleSelect : true,
     idField : '${_$idField}',
     treeField:'${_$treeField}',
     pagination : true,pageSize:200,pageList:[200,400,500],
     rownumbers : true,dnd:true,
     onDblClickRow : function(rowIndex) {
      CrudApp.on('edit');
     }
  ">
  <jsp:doBody />
 </table>
</div>
<!-- center} -->