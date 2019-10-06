<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@attribute name="_id" type="java.lang.String" required="true"%>
<%@attribute name="_bindFileId" type="java.lang.String" required="true"%>
<%@include file="/WEB-INF/views/taglib.jsp"%>
<!-- web uploader image{ -->
  <img id="${_id}_thumbImg" src="" style="display: none; width: 150px" />
 <div id="${_id}_web_uploader_image_view" data-role="webuploader" data-auto=true data-debug=false
  data-swf="${contextPath}/assets/webuploader/Uploader.swf" data-field-name="imgFile"
  data-upload-json="${contextPath}/backend/cms/cmsContent/kindEditorFileUpload.do"
  data-get-json="${contextPath}/backend/cms/cmsContent/getKindEditorFileUpload.do"
  data-accept="{title : 'Images',extensions : 'gif,jpg,jpeg,bmp,png',mimeTypes : 'image/*'}"
  data-pick="" data-file-num-limit=1 data-file-size-limit=10240
  data-file-single-size-limit=10240 data-bind-file-id="${_bindFileId}" data-thumb-img-id="${_id}_thumbImg"
 ></div>
<!-- web uploader image} -->
