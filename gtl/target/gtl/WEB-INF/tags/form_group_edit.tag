<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@attribute name="_title" type="java.lang.String" required="true"%>
<%@attribute name="_id" type="java.lang.String" required="true"%>
<div class="col-md-12">
	<div class="form-group has-feedback">
		<label for="${_id}" class="col-sm-4 control-label">${_title}</label>
		<div class="col-sm-6">
			<input type="text" id="${_id}" name="${_id}" class="form-control" required="required">
		</div>
	</div>
</div>
