<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@attribute name="_loadURL" type="java.lang.String" required="fale"%>
<%@attribute name="_id" type="java.lang.String" required="false"%>
<%@attribute name="_check" type="java.lang.String" required="false"%>
<%@attribute name="_autoload" type="java.lang.String" required="false"%>
<%@include file="/WEB-INF/views/taglib.jsp"%>
<c:set var="_$loadURLField" value="swgrid.do" />
<c:if test="${not empty _loadURL}">
 <c:set var="_$loadURLField" value="${_loadURL}" />
</c:if>
<c:set var="_$id" value="grid" />
<c:if test="${not empty _id}">
 <c:set var="_$id" value="${_id}" />
</c:if>
<c:set var="_$check" value="true" />
<c:if test="${not empty _check}">
 <c:set var="_$check" value="${_check}" />
</c:if>
<c:set var="_$autoload" value="false" />
<c:if test="${not empty _autoload}">
 <c:set var="_$autoload" value="${_autoload}" />
</c:if>
<!-- 列表{ -->
<div 
id="${_$id}" 
data-grid-container="${_$id}Container"
data-toolbar-container="${_$id}ToolBarContainer"
class="box" 
data-role="swgrid" 
data-loadurl="${_$loadURLField}" 
data-check="${_$check}" 
data-autoload="${_$autoload}"
data-columns="<jsp:doBody/>">
 <div id="${_$id}Container" class="box-body dlshouwen-grid-container"></div>
 <div id="${_$id}ToolBarContainer" class="box-footer dlshouwen-grid-toolbar-container"></div>
</div>
<!-- 列表} -->
