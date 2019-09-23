<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@attribute name="_multipart" type="java.lang.String" required="false"%>
<!-- Modal Edit{ -->
<div class="modal fade" id="editDialog" tabindex="-1" role="dialog" aria-labelledby="editDialogLabel">
 <div class="modal-dialog" role="document">
  <div class="modal-content">
   <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
     <span aria-hidden="true">&times;</span>
    </button>
    <h4 class="modal-title" id="editDialogLabel">编辑</h4>
   </div>
   <div class="modal-body">
    <form id="editForm" method="post" <c:if test="${not empty _multipart}">enctype="multipart/form-data"</c:if>>
     <jsp:doBody />
    </form>
   </div>
   <div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    <input type="button" type="button" class="btn btn-primary" onclick="CrudApp.on('save');" value="保存" />
   </div>
  </div>
 </div>
</div>
<!-- Modal Edit} -->
