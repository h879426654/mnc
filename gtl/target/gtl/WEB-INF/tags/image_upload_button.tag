<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@attribute name="_field" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_maxWidth" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_maxHeight" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_thumbWidth" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_thumbHeight" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_bindFileId" type="java.lang.String" required="false" rtexprvalue="false"%>
<%@attribute name="_bindThumbPathId" type="java.lang.String" required="false" rtexprvalue="false"%>
<c:set var="_$field" value="" />
<c:if test="${not empty _field}">
 <c:set var="_$field" value="${_field}" />
</c:if>
<c:set var="_$bindFileId" value="" />
<c:if test="${not empty _bindFileId}">
 <c:set var="_$bindFileId" value="${_bindFileId}" />
</c:if>
<c:set var="_$bindThumbPathId" value="" />
<c:if test="${not empty _bindThumbPathId}">
 <c:set var="_$bindThumbPathId" value="${_bindThumbPathId}" />
</c:if>
<c:set var="_$maxWidth" value="640" />
<c:if test="${not empty _maxWidth}">
 <c:set var="_$maxWidth" value="${_maxWidth}" />
</c:if>
<c:set var="_$maxHeight" value="960" />
<c:if test="${not empty _maxHeight}">
 <c:set var="_$maxHeight" value="${_maxHeight}" />
</c:if>
<c:set var="_$thumbWidth" value="150" />
<c:if test="${not empty _thumbWidth}">
 <c:set var="_$thumbWidth" value="${_thumbWidth}" />
</c:if>
<c:set var="_$thumbHeight" value="150" />
<c:if test="${not empty _thumbHeight}">
 <c:set var="_$thumbHeight" value="${_thumbHeight}" />
</c:if>
<table>
<tr align="center">
<td>
<input type="hidden" class="image-upload-button" id="${_$field}" name="${_$field}" 
data-img="${_$field}PreviewImg"
data-button="${_$field}UploadButton" 
data-uploadUrl="kindEditorFileUpload.do" 
data-showUrl="imageShow.do?path="
data-blankUrl="${_assets}/image-uploader.jpg"
data-maxWidth="${_$maxWidth}"
data-maxHeight="${_$maxHeight}"
data-thumbWidth="${_$thumbWidth}"
data-thumbHeight="${_$thumbHeight}"
data-bindFileId="${_$bindFileId}"
data-bindThumbPathId="${_$bindThumbPathId}"
>
<image data-jkit="[lightbox]" style="width:64px" id="${_$field}PreviewImg" />
</td>
</tr>
<tr align="center">
<td>
<input type="button" id="${_$field}UploadButton" value="上传" />
</td>
</tr>
</table>


