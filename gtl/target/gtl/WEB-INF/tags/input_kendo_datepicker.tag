<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@attribute name="_name" type="java.lang.String" required="true"%>
<%@attribute name="_id" type="java.lang.String" required="false"%>
<%@attribute name="_style" type="java.lang.String" required="false"%>
<%@attribute name="_format" type="java.lang.String" required="false"%>
<%@attribute name="_title" type="java.lang.String" required="false"%>
<%@attribute name="_required" type="java.lang.String" required="false"%>
<%@attribute name="_required_msg" type="java.lang.String" required="false"%>
<%@include file="/WEB-INF/views/taglib.jsp"%>
<c:set var="required_msg" value="${_title}为必填项" />
<c:if test="${not empty _required_msg}">
 <c:set var="required_msg" value="${_required_msg}" />
</c:if>
<c:set var="format" value="yyyy-MM-dd" />
<c:if test="${not empty _format}">
 <c:set var="format" value="${_format}" />
</c:if>

<input type="text" 
<c:if test="${not empty _id}">
id="${_id}"
</c:if>
<c:if test="${not empty _style}">
style="${_style}"
</c:if>
<c:if test="${not empty _required}">
required="${_required}" data-required-msg="${required_msg}"
</c:if>
<c:if test="${not empty _option_label}">
data-option-label="${_option_label}"
</c:if>
name="${_name}" 
data-role="datepicker"
data-format="${format}" 
class="form-control">
