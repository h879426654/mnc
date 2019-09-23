<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/tld/shiro.tld" prefix="shiro"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@attribute name="_module" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_viewSpan" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_addSpan" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_editSpan" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_deleteSpan" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_viewfunction" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_addfunction" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_editfunction" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_deletefunction" type="java.lang.String" required="false" rtexprvalue="false"%>

<c:set var="viewSpan" value="查看"/>
<c:set var="addSpan" value="添加"/>
<c:set var="editSpan" value="编辑"/>
<c:set var="deleteSpan" value="删除"/>

<c:set var="viewfunction" value="CrudApp.on('view');"/>
<c:set var="addfunction" value="CrudApp.on('add');"/>
<c:set var="editfunction" value="CrudApp.on('edit')"/>
<c:set var="deletefunction" value="CrudApp.on('delete')"/>

<c:if test="${not empty _viewfunction}">
<c:set var="viewfunction" value="${_viewfunction}"/>
</c:if>
<c:if test="${not empty _addfunction}">
<c:set var="addfunction" value="${_addfunction}"/>
</c:if>
<c:if test="${not empty _editfunction}">
<c:set var="editfunction" value="${_editfunction}"/>
</c:if>
<c:if test="${not empty _deletefunction}">
<c:set var="deletefunction" value="${_deletefunction}"/>
</c:if>


<c:if test="${not empty _viewSpan}">
<c:set var="viewSpan" value="${_viewSpan}"/>
</c:if>
<c:if test="${not empty _addSpan}">
<c:set var="addSpan" value="${_addSpan}"/>
</c:if>
<c:if test="${not empty _editSpan}">
<c:set var="editSpan" value="${_editSpan}"/>
</c:if>
<c:if test="${not empty _deleteSpan}">
<c:set var="deleteSpan" value="${_deleteSpan}"/>
</c:if>
<c:set var="_lacksPermissionAdd" value="${_module}:add"/> 
<c:set var="_lacksPermissionEdit" value="${_module}:edit"/>
<!-- 工具栏{ -->
<div class="form-inline">
 <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="${viewfunction}">
  <span class="glyphicon glyphicon-eye-open"></span> ${viewSpan}
 </button>
 <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="${addfunction}"
 <c:if test="${not empty _module}">
 <shiro:lacksPermission name="${_lacksPermissionAdd}">
 disabled="disabled"
 </shiro:lacksPermission>
 </c:if>
 >
  <span class="glyphicon glyphicon-plus"></span>${addSpan}
 </button>
 <button type="button" class="btn btn-default btn-sm label-primary  distance" onclick="${editfunction}"
 <c:if test="${not empty _module}">
 <shiro:lacksPermission name="${_lacksPermissionEdit}">
 disabled="disabled"
 </shiro:lacksPermission>
 </c:if> 
 >
  <span class="glyphicon glyphicon-pencil"></span>${editSpan}
 </button>
 
 <button type="button" class="btn btn-default btn-sm label-danger  distance" onclick="${deletefunction}">
  <span class="glyphicon glyphicon-remove"></span>${deleteSpan}
 </button>
 <jsp:doBody />
</div>
<!-- 工具栏} -->
