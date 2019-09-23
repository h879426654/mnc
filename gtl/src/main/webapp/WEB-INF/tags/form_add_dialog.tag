<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@attribute name="_multipart" type="java.lang.String" required="false"%>
<!-- Modal Add{ -->
<div class="modal fade" id="addDialog" tabindex="-1" role="dialog" aria-labelledby="addDialogLabel">
 <div class="modal-dialog" role="document">
  <div class="modal-content">
   <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
     <span aria-hidden="true">&times;</span>
    </button>
    <h4 class="modal-title" id="addDialogLabel">添加</h4>
   </div>
   <div class="modal-body">
    <form id="addForm" method="post" <c:if test="${not empty _multipart}">enctype="multipart/form-data"</c:if>>
     <jsp:doBody />
    </form>
   </div>
   <div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    <input type="button" type="button" class="btn btn-primary" onclick="CrudApp.onAddFormSave();" value="保存" />
   </div>
  </div>
 </div>
</div>
<!-- Modal Add} -->
