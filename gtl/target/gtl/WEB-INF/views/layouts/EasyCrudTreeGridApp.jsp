<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/WEB-INF/views/taglib.jsp"%><%@ taglib prefix="decorator" uri="/WEB-INF/tld/sitemesh-decorator.tld"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><decorator:title /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EDGE">
<!-- Tell the browser to be responsive to screen width -->
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<!-- Bootstrap 3.3.4 -->
<link href="${_assets}/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- Font Awesome Icons -->
<link href="${_assets}/fontawesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- easyui{ -->
<link rel="stylesheet" href="${_assets}/jquery-easyui/themes/gray/base.css?20160303" />
<link rel="stylesheet" href="${_assets}/jquery-easyui/themes/gray/easyui.css?20160303" />
<link rel="stylesheet" href="${_assets}/jquery-easyui/themes/icon.css" />
<script src="${_assets}/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="${_assets}/jquery-easyui/jquery.easyui.min.app.js?20160308"></script>
<script type="text/javascript" src="${_assets}/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<!-- easyui} -->
<!-- kindeditor{ -->
<script type="text/javascript" src="${_assets}/kindeditor/kindeditor-all.js"></script>
<script type="text/javascript" src="${_assets}/kindeditor/lang/zh_CN.js"></script>
<!-- kindeditor} -->
<!-- jquery-jkit{ -->
<link rel="stylesheet" type="text/css" href="${_assets}/jquery-jkit/jquery.jkit.css" media="screen" />
<script src="${_assets}/jquery-jkit/jquery.easing.1.3.js"></script>
<script src="${_assets}/jquery-jkit/jquery.jkit.1.1.29.js"></script>
<!-- jquery-jkit} -->
<script type="text/javascript" src="${_assets}/EasyCrudTreeGridApp.js?201601030442"></script>
<script type="text/javascript">
 CrudApp.defaultSettings({
  contextPath : '${contextPath}'
 });
</script>
<decorator:head />
</head>
<body <decorator:getProperty property="body.class" writeEntireProperty="true"/>>
 <decorator:body />
</body>
</html>