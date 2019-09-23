<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@attribute name="_name" type="java.lang.String" required="true"%>
<%@attribute name="_id" type="java.lang.String" required="false"%>
<%@attribute name="_value_field" type="java.lang.String" required="true"%>
<%@attribute name="_text_field" type="java.lang.String" required="true"%>
<%@attribute name="_option_label" type="java.lang.String" required="false"%>
<%@attribute name="_filter" type="java.lang.String" required="false"%>
<%@attribute name="_style" type="java.lang.String" required="false"%>
<%@attribute name="_data_source_url" type="java.lang.String" required="true" rtexprvalue="true"%>
<%@attribute name="_title" type="java.lang.String" required="true"%>
<%@attribute name="_required" type="java.lang.String" required="false"%>
<%@attribute name="_required_msg" type="java.lang.String" required="false"%>
<%@include file="/WEB-INF/views/taglib.jsp"%>
<c:set var="required_msg" value="${_title}为必填项" />
<c:if test="${not empty _required_msg}">
 <c:set var="required_msg" value="${_required_msg}" />
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
data-role="dropdownlist" 
data-value-field="${_value_field}" 
data-text-field="${_text_field}"
<c:if test="${not empty _filter}">
data-filter="${_filter}"
</c:if>
class="form-control"
data-source="
{schema : {total: 'total',data : 'data'},
 pageSize: 20,
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
