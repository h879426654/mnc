<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%
  response.setStatus(200);
%>
<%
  Throwable ex = null;
			if (exception != null)
				ex = exception;
			if (request.getAttribute("javax.servlet.error.exception") != null)
				ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>500 - 系统内部错误</title>
</head>
<body>
 <div class="container-fluid">
  <div class="page-header">
   <h1>系统发生内部错误.</h1>
  </div>
  <p>错误信息：</p>
  <p>
   <%
     if (ex != null) {
      out.print(ex + "<br/>");
     }
   %>
  </p>
 </div>
</body>
</html>