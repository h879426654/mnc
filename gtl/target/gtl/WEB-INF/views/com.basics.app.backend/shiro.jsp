<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
 <h3>Shiro</h3>
 <p>
  principal:
  <shiro:principal />
 </p>
 <h3>Roles</h3>
 <p>${roles}</p>
 <h3>Permissions</h3>
 <p>${permissions}</p>
 <shiro:hasPermission name="app:appUser:index">
   app:appUser:index
</shiro:hasPermission>
</body>
</html>
