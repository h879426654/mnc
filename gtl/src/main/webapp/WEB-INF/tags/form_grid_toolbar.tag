<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@attribute name="_module" type="java.lang.String" required="false" rtexprvalue="false"%>
<c:set var="_lacksPermissionAdd" value="${_module}:add"/> 
<c:set var="_lacksPermissionEdit" value="${_module}:edit"/>
<!-- 工具栏{ -->
<div class="form-inline">
 <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('view');">
  <span class="glyphicon glyphicon-eye-open"></span> <spring:message code="tag.form.grid.toolbar.view" />
 </button>
 <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('add');"
 <c:if test="${not empty _module}">
 <shiro:lacksPermission name="${_lacksPermissionAdd}">
 disabled="disabled"
 </shiro:lacksPermission>
 </c:if>
 >
  <span class="glyphicon glyphicon-plus"></span> <spring:message code="tag.form.grid.toolbar.add" />
 </button>
 <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('edit')"
 <c:if test="${not empty _module}">
 <shiro:lacksPermission name="${_lacksPermissionEdit}">
 disabled="disabled"
 </shiro:lacksPermission>
 </c:if> 
 >
  <span class="glyphicon glyphicon-pencil"></span> <spring:message code="tag.form.grid.toolbar.edit" />
 </button>
 <%
  //开发的时候,启用删除功能
  if (com.basics.support.ProfileUtils.isDev()) {
 %>
 <button type="button" class="btn btn-default btn-sm label-danger  distance" onclick="CrudApp.on('delete')">
  <span class="glyphicon glyphicon-remove"></span> <spring:message code="tag.form.grid.toolbar.delete" />
 </button>
 <%
  }
 %>
 <jsp:doBody />
</div>
<!-- 工具栏} -->
