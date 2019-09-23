<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
 com.basics.support.UserAgentSupport userAgentSupport = com.basics.support.UserAgentUtils.from(request);
 if (userAgentSupport.isWeixin()) {
%>
<jsp:doBody />
<%
 }
%>
