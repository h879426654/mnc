<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@attribute name="_module" type="java.lang.String" required="true" rtexprvalue="false"%>
<c:set var="_lacksPermissionAdd" value="${_module}:add"/> 
<c:set var="_disabledAdd" value="false"/>
<c:set var="_lacksPermissionEdit" value="${_module}:edit"/>
<c:set var="_disabledEdit" value="false"/>
<!-- 工具栏 -->
<div id="toolbar">
<shiro:lacksPermission name="${_lacksPermissionAdd}">
<c:set var="_disabledAdd" value="true"/>
</shiro:lacksPermission>
<shiro:lacksPermission name="${_lacksPermissionEdit}">
<c:set var="_disabledEdit" value="true"/>
</shiro:lacksPermission>
<a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:${_disabledAdd},iconCls:'icon-add'" onclick="CrudApp.on('add');">添加</a>
<a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:${_disabledEdit},iconCls:'icon-edit'" onclick="CrudApp.on('edit');">编辑</a>
<jsp:doBody />
</div> 
