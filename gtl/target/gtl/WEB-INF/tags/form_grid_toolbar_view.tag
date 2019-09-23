<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@attribute name="_module" type="java.lang.String" required="false" rtexprvalue="false"%>
<c:set var="_lacksPermissionAdd" value="${_module}:add" />
<c:set var="_lacksPermissionEdit" value="${_module}:edit" />
<!-- 工具栏{ -->
<div class="form-inline">
 <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('view');">
  <span class="glyphicon glyphicon-eye-open"></span> <spring:message code="tag.form.grid.toolbar.view" />
 </button>
  <jsp:doBody />
</div>
<!-- 工具栏} -->
