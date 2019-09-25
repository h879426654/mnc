<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@attribute name="_name" type="java.lang.String" required="true"%>
<%@attribute name="_min" type="java.lang.String" required="false"%>
<%@attribute name="_max" type="java.lang.String" required="false"%>
<%@attribute name="_id" type="java.lang.String" required="false"%>
<%@attribute name="_style" type="java.lang.String" required="false"%>
<%@attribute name="_title" type="java.lang.String" required="false"%>
<%@attribute name="_required" type="java.lang.String" required="false"%>
<%@attribute name="_required_msg" type="java.lang.String" required="false"%>
<%@include file="/WEB-INF/views/taglib.jsp"%>
<c:set var="required_msg" value="${_title}为必填项" />
<c:if test="${not empty _required_msg}">
 <c:set var="required_msg" value="${_required_msg}" />
</c:if>
<input type="number" 
<c:if test="${not empty _id}">
id="${_id}"
</c:if>
<c:if test="${not empty _style}">
style="${_style}"
</c:if>
<c:if test="${not empty _required}">
required="${_required}" data-required-msg="${required_msg}"
</c:if>
<c:if test="${not empty _min}">
min="${_min}"
</c:if>
<c:if test="${not empty _max}">
max="${_max}"
</c:if>
name="${_name}" 
data-role="numerictextbox">
