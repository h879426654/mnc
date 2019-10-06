<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@attribute name="_id" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_function" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="width" type="java.lang.String" required="false" rtexprvalue="false"%>
<c:set var="id" value="viewDialog"/>
<c:set var="function" value="JavaScript:void(0);"/>
<c:set var="_width" value="600"/>
<c:if test="${not empty _id}">
<c:set var="id" value="${_id}"/>
</c:if>
<c:if test="${not empty _function}">
<c:set var="function" value="${_function}"/>
</c:if>
<c:if test="${not empty width}">
<c:set var="_width" value="${width}"/>
</c:if>
<!-- Modal View{ -->
<div class="modal fade" id="${id}" tabindex="-1" role="dialog" aria-labelledby="viewDialogLabel">
 <div class="modal-dialog" role="document" style="width:${_width}">
  <div class="modal-content">
   <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
     <span aria-hidden="true">&times;</span>
    </button>
    <h4 class="modal-title" id="${id}" onclick="${function}">查看</h4>
   </div>
   <div class="modal-body">
    <form id="${id}viewForm">
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
