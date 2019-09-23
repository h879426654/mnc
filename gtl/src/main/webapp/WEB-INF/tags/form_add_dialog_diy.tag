<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@attribute name="title" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="width" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="id" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="function" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="colseOn" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="form" type="java.lang.String" required="false" rtexprvalue="false"%>
<c:set var="_function" value="CrudApp.onAddFormSave();"/>
<c:set var="_Closefunction" value="JavaScript:void(0)"/>
<c:set var="_title" value="添加"/>
<c:set var="_width" value="600"/>
<c:set var="_id" value="addDialog"/>
<c:set var="_form" value="addForm"/>

<c:if test="${not empty function}">
<c:set var="_function" value="${function}"/>
</c:if>
<c:if test="${not empty colseOn}">
<c:set var="_Closefunction" value="${colseOn}"/>
</c:if>
<c:if test="${not empty function}">
<c:set var="_function" value="${function}"/>
</c:if>
<c:if test="${not empty title}">
<c:set var="_title" value="${title}"/>
</c:if>
<c:if test="${not empty width}">
<c:set var="_width" value="${width}"/>
</c:if>
<c:if test="${not empty id}">
<c:set var="_id" value="${id}"/>
</c:if>
<c:if test="${not empty form}">
<c:set var="_form" value="${form}"/>
</c:if>
<!-- Modal Add{ -->
<div class="modal fade" id="${_id}" tabindex="-1" role="dialog" aria-labelledby="addDialogLabel">
 <div class="modal-dialog" role="document" style="width:${_width}">
  <div class="modal-content">
   <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="${_Closefunction}">
     <span aria-hidden="true">&times;</span>
    </button>
    <h4 class="modal-title" id="addDialogLabel">${_title}</h4>
   </div>
   <div class="modal-body">
    <form id="${_form}" method="post" class="form-signin" autocomplete="off">
     <jsp:doBody />
    </form>
   </div> 
   <div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="${_Closefunction}">关闭</button>
    <input type="button" type="button" class="btn btn-primary" onclick="${_function}" value="保存" />
   </div>
  </div>
 </div>
</div>
<!-- Modal Add} -->
