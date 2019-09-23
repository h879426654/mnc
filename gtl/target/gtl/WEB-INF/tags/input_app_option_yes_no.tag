<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@attribute name="_name" type="java.lang.String" required="true"%>
<%@attribute name="_id" type="java.lang.String" required="false"%>
<%@attribute name="_option_label" type="java.lang.String" required="false"%>
<%@attribute name="_style" type="java.lang.String" required="false"%>
<%@attribute name="_title" type="java.lang.String" required="true"%>
<%@attribute name="_required" type="java.lang.String" required="false"%>
<%@attribute name="_required_msg" type="java.lang.String" required="false"%>
<%@include file="/WEB-INF/views/taglib.jsp"%>
<c:set var="required_msg" value="${_title}为必填项" />
<c:if test="${not empty _required_msg}">
 <c:set var="required_msg" value="${_required_msg}" />
</c:if>
<c:set var="_value_field" value="code" />
<c:set var="_text_field" value="name" />
<c:set var="_parentId" value="yes_no" />
<c:set var="_data_source_url" value="${pageContext.request.contextPath}/backend/app/appOption/options/${_parentId}.do" />
<input type="text" <c:if test="${not empty _id}">
id="${_id}"
</c:if> <c:if test="${not empty _style}">
style="${_style}"
</c:if>
 <c:if test="${not empty _required}">
required="${_required}" data-required-msg="${required_msg}"
</c:if>
 <c:if test="${not empty _option_label}">
data-option-label="${_option_label}"
</c:if> 
name="${_name}" 
data-role="dropdownlist"
data-value-field="${_value_field}" 
data-text-field="${_text_field}" 
class="form-control"
data-source="
{schema : {total: 'total',data : 'data'},
 serverPaging: true,
 serverFiltering : true,
 transport: 
  {read:
   {type:'POST',
    dataType:'json',
    contentType:'application/json',
    url: '${_data_source_url}'
   },
   parameterMap:function(options){return JSON.stringify(options);}
  }
}">
