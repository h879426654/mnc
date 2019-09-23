<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@attribute name="_name" type="java.lang.String" required="true"%>
<%@attribute name="_title" type="java.lang.String" required="true"%>
<%@attribute name="_active" type="java.lang.String" required="false"%>
<div class="tab-pane ${_active}" id="${_name}">
 <jsp:doBody />
</div>