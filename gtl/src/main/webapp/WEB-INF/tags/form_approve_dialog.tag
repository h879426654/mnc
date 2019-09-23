<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<!-- Modal Edit{ -->
<div class="modal fade" id="approveDialog" tabindex="-1" role="dialog" aria-labelledby="approveDialogLabel">
 <div class="modal-dialog" role="document">
  <div class="modal-content">
   <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
     <span aria-hidden="true">&times;</span>
    </button>
    <h4 class="modal-title" id="approveDialogLabel">审核</h4>
   </div>
   <div class="modal-body">
    <form id="approveForm" method="post">
     <jsp:doBody />
    </form>
   </div>
   <div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    <input id="approvePassButton" type="button" type="button" class="btn btn-primary" onclick="CrudApp.on('approvePass');" value="审核通过" />
    <input id="approveRejectButton" type="button" type="button" class="btn btn-danger" onclick="CrudApp.on('approveReject');" value="审核不通过" />
    <input id="approveSubmitNextButton" type="button" type="button" class="btn btn-primary" onclick="CrudApp.on('approveSubmitNext');" value="提交下一级" />
   </div>
  </div>
 </div>
</div>
<!-- Modal Edit} -->
