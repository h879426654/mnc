<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@attribute name="_loadURL" type="java.lang.String" required="fale"%>
<%@attribute name="_grid" type="java.lang.String" required="fale"%>
<%@attribute name="_check" type="java.lang.String" required="false"%>
<%@attribute name="_autoload" type="java.lang.String" required="false"%>
<%@include file="/WEB-INF/views/taglib.jsp"%>
<c:set var="_$loadURLField" value="swgrid.do"/>
<c:set var="_$grid" value="grid"/>
<c:if test="${not empty _grid}">
<c:set var="_$grid" value="${_grid}"/>
</c:if>
<c:if test="${not empty _loadURL}">
<c:set var="_$loadURLField" value="${_loadURL}"/>
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
<div id="${_$grid}" class="box" data-role="swgrid" 
data-grid-container="${_$grid}_gridContainer"
data-toolbar-container="${_$grid}_gridToolBarContainer"
data-loadurl="${_$loadURLField}" data-check="${_$check}" data-autoload="${_$autoload}" data-columns="<jsp:doBody/>">
 <div id="${_$grid}_gridContainer" class="box-body dlshouwen-grid-container"></div>
 <div id="${_$grid}_gridToolBarContainer" class="box-footer dlshouwen-grid-toolbar-container"></div>
</div>
<!-- 列表} -->
