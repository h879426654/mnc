<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@attribute name="_title" type="java.lang.String" required="true"%>
<%@attribute name="_name" type="java.lang.String" required="true"%>
<%@attribute name="_onsave" type="java.lang.String" required="true"%>
<!-- Modal Add{ -->
<div class="modal fade" id="${_name}Dialog" tabindex="-1" role="dialog" aria-labelledby="${_name}DialogLabel">
 <div class="modal-dialog" role="document">
  <div class="modal-content">
   <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
     <span aria-hidden="true">&times;</span>
    </button>
    <h4 class="modal-title" id="${_name}DialogLabel">${_title}</h4>
   </div>
   <div class="modal-body">
    <form id="${_name}Form">
     <jsp:doBody />
    </form>
   </div>
   <div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    <input type="button" type="button" class="btn btn-primary" onclick="CrudApp.on('${_onsave}');" value="保存" />
   </div>
  </div>
 </div>
</div>
<!-- Modal Add} -->
