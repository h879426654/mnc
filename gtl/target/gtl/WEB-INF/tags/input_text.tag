<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@attribute name="_name" type="java.lang.String" required="true"%>
<%@attribute name="_title" type="java.lang.String" required="true"%>
<%@attribute name="_required" type="java.lang.String" required="false"%>
<%@attribute name="_required_msg" type="java.lang.String" required="false"%>
<%@attribute name="_pattern" type="java.lang.String" required="false"%>
<%@attribute name="_pattern_msg" type="java.lang.String" required="false"%>
<%@attribute name="_maxlength" type="java.lang.String" required="false"%>
<%@include file="/WEB-INF/views/taglib.jsp"%>
<c:set var="required_msg" value="${_title}为必填项" />
<c:set var="pattern" value="" />
<c:set var="pattern_msg" value="${_title}不符合规则" />
<c:if test="${not empty _required_msg}">
 <c:set var="required_msg" value="${_required_msg}" />
</c:if>
<c:if test="${not empty _pattern}">
 <c:set var="pattern" value="${_pattern}" />
</c:if>
<c:if test="${not empty _pattern_msg}">
 <c:set var="pattern_msg" value="${_pattern_msg}" />
</c:if>
<input type="text" name="${_name}" class="form-control"
 <c:if test="${not empty pattern}">
pattern="${pattern}" data-pattern-msg="${pattern_msg}"
</c:if>
 <c:if test="${not empty _maxlength}">
maxlength="${_maxlength}"
</c:if>
 <c:if test="${not empty _required}">
required="${_required}" data-required-msg="${required_msg}"
</c:if>>
