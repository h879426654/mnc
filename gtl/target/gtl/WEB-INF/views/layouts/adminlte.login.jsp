<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@ taglib prefix="decorator" uri="/WEB-INF/tld/sitemesh-decorator.tld"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EDGE">
<title><spring:message code="webapp.name" /> | <decorator:title /></title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<link href="${_assets}/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${_assets}/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${_assets}/adminlte/dist/css/adminlte.min.css" rel="stylesheet" type="text/css" />
<link href="${_assets}/adminlte/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
<script src="${_assets}/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap JS -->
<script src="${_assets}/bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${_assets}/jquery.timer/jquery.timer.js"></script>
<link rel="stylesheet" href="${_assets}/toastr/toastr.min.css" />
<script src="${_assets}/toastr/toastr.js" type="text/javascript"></script>
<decorator:head />
</head>
<body class="login-page">
 <decorator:body />
</body>
</html>