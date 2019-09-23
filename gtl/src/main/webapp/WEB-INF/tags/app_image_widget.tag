<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@attribute name="_dataOwnerId" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_dataOwnerClass" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_dataWidth" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_dataHeight" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@include file="/WEB-INF/views/taglib.jsp"%>
<c:set var="_$dataOwnerId" value="appImageId" />
<c:if test="${not empty _dataOwnerId}">
 <c:set var="_$dataOwnerId" value="${_dataOwnerId}" />
</c:if>
<c:set var="_$dataOwnerClass" value="appImageId" />
<c:if test="${not empty _dataOwnerClass}">
 <c:set var="_$dataOwnerClass" value="${_dataOwnerClass}" />
</c:if>
<c:set var="_$dataWidth" value="100%" />
<c:if test="${not empty _dataWidth}">
 <c:set var="_$dataWidth" value="${_dataWidth}" />
</c:if>
<c:set var="_$dataHeight" value="100%" />
<c:if test="${not empty _dataHeight}">
 <c:set var="_$dataHeight" value="${_dataHeight}" />
</c:if>
<div class="AppImageWidget" 
data-src="${contextPath}/backend/app/appImage/widget.do" 
data-owner-id="${_$dataOwnerId}"
data-owner-class="${_$dataOwnerClass}"
data-width="${_$dataWidth}"
data-height="${_$dataHeight}"
>
</div>