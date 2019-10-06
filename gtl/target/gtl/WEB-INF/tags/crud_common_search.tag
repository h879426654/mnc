<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!-- north{ -->
 <div data-options="region:'north',border:false">
 <!-- search form{ -->
 <form action="" id="search_form" method="post">
  <table class="editTable">
   <tr>
    <td class="title">模糊查询:</td>
    <td><input type="text" name="q" /></td>
   </tr>
   <tr style="text-align: center;">
    <td colspan="2">
     <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="CrudApp.on('search');">查询</a> <a
     href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="CrudApp.on('clearSearch');">重置</a></td>
   </tr>
  </table>
 </form>
 <!-- search form} -->
</div>
<!-- north} -->
