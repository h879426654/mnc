<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@attribute name="_id" type="java.lang.String" required="true"%>
<%@attribute name="_bindFileId" type="java.lang.String" required="true"%>
<%@include file="/WEB-INF/views/taglib.jsp"%>
<!-- 列表{ -->
<img id="${_id}_thumbImg" src="" style="display:none" />
<button type="button" id="${_id}Button" 
data-role="kinduploadbutton" 
data-bind-file-id="${_bindFileId}" 
data-thumb-img-id="${_id}_thumbImg" 
 />
<!-- 列表} -->
