<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@ taglib prefix="decorator" uri="/WEB-INF/tld/sitemesh-decorator.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="webapp.name" /> | <decorator:title /></title>
<link rel="stylesheet" href="${_assets}/loginStyle/css/style.css" type="text/css" media="all">
<script src="${_assets}/jquery/dist/jquery.min.js"></script>
<script src="${_assets}/toastr/toastr.js" type="text/javascript"></script>
<script src="${_assets}/jquery.parser.js"></script>
<script src="${_assets}/jquery.form.js"></script>
<script src="${_assets}/kendo-ui/js/kendo.custom.min.js" type="text/javascript"></script>
<script src="${_assets}/app.js"></script>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script type="text/javascript">
 //loginPage
 function changeCodeImage() {
  $('#validateCodeImage').attr('src', 'validateCode.do?' + new Date().getTime());
 }

 function doLogin() {
  $("#loginForm").form('submit', {
   url : 'api/app/login.do',
   success : function(data) {
    var data = $.parseJSON(data);
    if (data.success) {
     location.href = 'success.do';
    } else {
     alert(data.message);
    }
   }
  });
 }
 $(function() {
  changeCodeImage();
  $("#loginForm").kendoValidator({
   validateOnBlur : true
  });
  //enter to login.
  $(document).keydown(function(event) {
   if (event.keyCode == 13) {
    doLogin();
   }
  });
 });
</script>
</head>
<body <decorator:getProperty property="body.class" writeEntireProperty="true"/>>
 <decorator:body />
</body>
</html>