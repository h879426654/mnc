<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<!-- 查询表单{ -->
<div class="box box-default">
 <div class="box-header with-border">
  <h3 class="box-title"><spring:message code="tag.form.grid.toolbar.search" /></h3>
  <div class="box-tools">
   <button type="button" class="btn btn-box-tool" data-widget="collapse">
    <i class="fa fa-minus"></i>
   </button>
  </div>
 </div>
 <form id="searchForm" method="post">
  <div class="box-body">
  	<div class="row">
   		<jsp:doBody />
   	</div>
  </div>
  <div class="box-footer">
   <input type="button" class="btn btn-default btn-sm label-primary  distance" onclick="CrudApp.on('search');" value="<spring:message code="tag.form.grid.toolbar.search" />" /> <input type="button" class="btn btn-default btn-sm label-primary  distance"
    onclick="CrudApp.on('clearSearch');" value="<spring:message code="tag.form.grid.toolbar.clearSearch" />" />
  </div>
 </form>
</div>
<!-- 查询表单} -->