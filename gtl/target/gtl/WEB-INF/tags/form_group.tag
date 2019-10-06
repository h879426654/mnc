<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@attribute name="_title" type="java.lang.String" required="true"%>
<div class="form-group">
 <label>${_title}</label>
 <jsp:doBody /> 
</div>
