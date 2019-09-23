<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!-- 工具栏{ -->
<div id="toolbar">
<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="CrudApp.on('add');">添加</a>
<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="CrudApp.on('edit');">编辑</a>
<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="CrudApp.on('delete');">删除</a>
<jsp:doBody />
</div>
<!-- 工具栏} --> 
