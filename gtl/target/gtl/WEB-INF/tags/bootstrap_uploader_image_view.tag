<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@attribute name="_id" type="java.lang.String" required="true"%>
<%@attribute name="_ownerId" type="java.lang.String" required="true"%>
<%@attribute name="_ownerClass" type="java.lang.String" required="true"%>
<%@attribute name="_multiple" type="java.lang.String" required="true"%>
<%@attribute name="_extend" type="java.lang.String" required="false"%>

<%@include file="/WEB-INF/views/taglib.jsp"%>
<!-- bootstrap uploader image{ -->
<input id="${_id}FileInput" 
type="file" 
name="imgFile" 
class="file"
${_multiple}
data-role="bootstrapfileinput"
data-owner-id="${_ownerId}"
data-owner-class="${_ownerClass}" 
data-upload-url="bootstrapFileInputUpload${_extend}.do"
data-init-url="bootstrapFileInputInitial${_extend}.do"
data-delete-url="bootstrapFileInputDelete${_extend}.do"
data-initial-preview-as-data="true"
data-language="zh"
data-show-close="false"
data-show-caption="false"
data-show-upload="false"
data-show-remove="false"
data-show-browse="false"
data-show-close="false"
data-view="1"
>
<!-- bootstrap uploader image} -->
