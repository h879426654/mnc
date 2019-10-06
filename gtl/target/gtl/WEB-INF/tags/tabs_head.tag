<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@attribute name="_name" type="java.lang.String" required="true"%>
<%@attribute name="_title" type="java.lang.String" required="true"%>
<%@attribute name="_active" type="java.lang.String" required="false"%>
<li class="${_active}"><a href="#${_name}" data-toggle="tab">${_title}</a></li>
