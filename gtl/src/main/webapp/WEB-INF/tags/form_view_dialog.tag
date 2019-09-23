<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<!-- Modal View{ -->
<div class="modal fade" id="viewDialog" tabindex="-1" role="dialog" aria-labelledby="viewDialogLabel">
 <div class="modal-dialog" role="document">
  <div class="modal-content">
   <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
     <span aria-hidden="true">&times;</span>
    </button>
    <h4 class="modal-title" id="viewDialogLabel">查看</h4>
   </div>
   <div class="modal-body">
    <form id="viewForm">
     <jsp:doBody />
    </form>
   </div>
   <div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
   </div>
  </div>
 </div>
</div>
<!-- Modal View} -->
