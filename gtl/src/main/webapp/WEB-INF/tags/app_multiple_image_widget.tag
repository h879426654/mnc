<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@attribute name="_dataBindId" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_dataWidth" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_dataHeight" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@include file="/WEB-INF/views/taglib.jsp"%>
<c:set var="_$dataBindId" value="mainId" />
<c:if test="${not empty _dataBindId}">
 <c:set var="_$dataBindId" value="${_dataBindId}" />
</c:if>
<c:set var="_$dataWidth" value="100%" />
<c:if test="${not empty _dataWidth}">
 <c:set var="_$dataWidth" value="${_dataWidth}" />
</c:if>
<c:set var="_$dataHeight" value="100%" />
<c:if test="${not empty _dataHeight}">
 <c:set var="_$dataHeight" value="${_dataHeight}" />
</c:if>
<div class="MultipleImageWidget" id="${_$dataBindId}MultipleImageWidget" data-bind-mainId="${_$dataBindId}"
 data-option-listImageUrl="${contextPath}/backend/app/ueditor/controller.do?action=listimage&mainId={{:mainId}}&_tm=<%=System.currentTimeMillis()%>"
 data-option-itemImpl="${_$dataBindId}UploadImageWidgetItemImpl"
 data-option-allowUpload="true"
 ></div>
<script id="${_$dataBindId}UploadImageWidgetItemImpl" type="text/x-jsrender">
<span  style="width:100px;float:left" class="thumbnail" id="uploadImageItemContainer{{:id}}">
      <img data-jkit="[lightbox]" src="{{:url}}" style="height:128px">
      <div class="caption">
        <button 
onclick="$('#${_$dataBindId}MultipleImageWidget').MultipleImageWidget('deleteImage',{'imageUrl':'{{:urlDelete}}','id':'{{:id}}'});" 
class="btn btn-default" type="button">删除</button>
      </div>
</span> 
</script>