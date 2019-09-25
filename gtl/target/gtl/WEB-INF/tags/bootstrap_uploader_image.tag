<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@attribute name="_id" type="java.lang.String" required="true"%>
<%@attribute name="_ownerId" type="java.lang.String" required="true"%>
<%@attribute name="_ownerClass" type="java.lang.String" required="true"%>
<%@attribute name="_multiple" type="java.lang.String" required="true"%>
<%@attribute name="_bindUrlId" type="java.lang.String" required="false"%>
<%@include file="/WEB-INF/views/taglib.jsp"%>
<c:set var="_overwriteInitial" value="false" />
<c:if test="${_multiple eq ''}">
<c:set var="_overwriteInitial" value="true" />
</c:if>
<!-- bootstrap uploader image{ -->
<input id="${_id}FileInput" 
type="file" 
name="imgFile" 
class="file"
${_multiple}
data-role="bootstrapfileinput"
data-owner-id="${_ownerId}"
data-owner-class="${_ownerClass}" 
data-upload-url="bootstrapFileInputUpload.do"
data-init-url="bootstrapFileInputInitial.do"
data-delete-url="bootstrapFileInputDelete.do"
data-initial-preview-as-data="true"
data-overwrite-initial="${_overwriteInitial}" 
data-language="zh"
data-show-close="false"
data-show-caption="false"
accept="image/*"
data-browse-class="btn btn-primary btn-block"
data-browse-label="选择图片"
data-bind-url-id="${_bindUrlId}"
data-multiple="${_multiple}"
>
<!-- bootstrap uploader image} -->
