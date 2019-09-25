<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@ taglib prefix="decorator" uri="/WEB-INF/tld/sitemesh-decorator.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><spring:message code="webapp.name" /> | <decorator:title /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EDGE">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link href="${_assets}/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${_assets}/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${_assets}/adminlte/dist/css/adminlte.min.css">
<link rel="stylesheet" href="${_assets}/adminlte/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="${_assets}/kendo-ui/styles/kendo.common-bootstrap.min.css" />
<link rel="stylesheet" href="${_assets}/kendo-ui/styles/kendo.common-bootstrap.core.min.css" />
<link rel="stylesheet" href="${_assets}/kendo-ui/styles/kendo.bootstrap.min.css" />
<link rel="stylesheet" href="${_assets}/toastr/toastr.min.css" />
<link rel="stylesheet" href="${_assets}/adminlte/dist/css/style.css" />
<link rel="stylesheet" href="${_assets}/ztree/ztree.css" />
<link rel="stylesheet" type="text/css" href="${_assets}/datePicker/skin/WdatePicker.css" />
<link rel="stylesheet" type="text/css" href="${_assets}/datePicker/skin/default/datepicker.css" />
<link rel="stylesheet" type="text/css" href="${_assets}/dlshouwen/dlshouwen.grid.min.css" />
<link href="${_assets}/bootstrap-fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
<link href="${_assets}/bootstrap-fileinput/themes/explorer/theme.css" media="all" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${_assets}/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="${_assets}/html5shiv/dist/html5shiv.min.js"></script>
  <script src="${_assets}/responsejs/response.min.js"></script>
<![endif]-->
<script src="${_assets}/jquery/dist/jquery.min.js"></script>
<script src="${_assets}/bootstrap-fileinput/js/plugins/sortable.js" type="text/javascript"></script>
<script src="${_assets}/bootstrap-fileinput/js/fileinput.js" type="text/javascript"></script>
<script src="${_assets}/bootstrap-fileinput/js/locales/zh.js" type="text/javascript"></script>
<script src="${_assets}/bootstrap-fileinput/themes/explorer/theme.js" type="text/javascript"></script>
<script src="${_assets}/bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${_assets}/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="${_assets}/toastr/toastr.js" type="text/javascript"></script>
<script src="${_assets}/bootbox.js/bootbox.js" type="text/javascript"></script>
<script src="${_assets}/kendo-ui/js/kendo.all.min.js"></script>
<script src="${_assets}/kendo-ui/js/cultures/kendo.culture.zh-CN.min.js"></script>
<script src="${_assets}/kendo-ui/js/messages/kendo.messages.zh-CN.min.js"></script>
<script src="${_assets}/dlshouwen/dlshouwen.grid.js"></script>
<script src="${_assets}/dlshouwen/kendo.dlshouwen.grid.js"></script>
<script src="${_assets}/dlshouwen/i18n/en.js"></script>
<script src="${_assets}/dlshouwen/i18n/zh-cn.js"></script>
<script src="${_assets}/datePicker/WdatePicker.js"></script>
<script src="${_assets}/ztree/ztree.min.js"></script>
<script src="${_assets}/ztree/kendo.ztree.js"></script>
<script src="${_assets}/bootstrap-fileinput/kendo.bootstrap.fileinput.js" type="text/javascript"></script>
<script src="${_assets}/adminlte/dist/js/app.min.js"></script>
<!-- webuploader{ -->
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/webuploader/webuploader.css">
<script type="text/javascript" src="${contextPath}/assets/webuploader/webuploader.fix.js"></script>
<script type="text/javascript" src="${contextPath}/assets/webuploader/kendo.webuploader.js"></script>
<!-- webuploader} -->
<!-- kindeditor{ -->
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/kindeditor/themes/default/default.css" />
<script type="text/javascript" src="${contextPath}/assets/kindeditor/kindeditor-all.js"></script>
<script type="text/javascript" src="${contextPath}/assets/kindeditor/kendo.kindeditor.js"></script>
<!-- kindeditor} -->
<!-- jquery-jkit{ -->
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/jquery-jkit/jquery.jkit.css" media="screen" />
<script src="${contextPath}/assets/jquery-jkit/jquery.easing.1.3.js"></script>
<script src="${contextPath}/assets/jquery-jkit/jquery.jkit.1.1.29.js"></script>
<!-- jquery-jkit} -->
<!-- jsrender{ -->
<script src="${_assets}/jsrender/jsrender.min.js"></script>
<!-- jsrender} -->
<!-- ueditor{ -->
<script type="text/javascript" src="${_assets}/ueditor/ueditor.config.js.jsp"></script>
<script type="text/javascript" src="${_assets}/ueditor/ueditor.all.min.js"></script>
<!-- ueditor} -->
<!-- backend app -->
<script src="${_assets}/jquery.parser.js"></script>
<script src="${_assets}/jquery.form.js"></script>
<script src="${_assets}/app.js"></script>
<script type="text/javascript" src="${_assets}/fitz.widget.js"></script>
<script type="text/javascript" src="${_assets}/fitz.multiple.image.widget.js?20160509"></script>
<script type="text/javascript" src="${_assets}/fitz.template.widget.js"></script>
<script type="text/javascript" src="${_assets}/EasyCrudApp.js?20160509"></script>
<script type="text/javascript" src="${_assets}/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" ></script>
<script type="text/javascript" src="${_assets}/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" ></script>
<decorator:head />
<script type="text/javascript">
 function onLogout() {
  bootbox.setLocale('${pageContext.response.locale }');
  bootbox.confirm("<spring:message code='index.loginOutMessage' />", function(r) {
   if (r) {
    window.location = "${contextPath}/logout.do";
   }
  });
 }
 function onSessionTimeout(){
  window.location = "${contextPath}/logout.do";
 }
 $(function() {
  $("#logoutButton").click(function() {
   onLogout();
  });
 });
</script>
</head>
<body class="hold-transition skin-blue fixed sidebar-mini">
 <!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
 <div class="wrapper">
  <!-- Main Header -->
  <header class="main-header"> <!-- Logo --> <a href="${contextPath}/backend/index.do" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
   <span class="logo-mini"><spring:message code="webapp.name" /></span> <!-- logo for regular state and mobile devices --> <span
   class="logo-lg"><spring:message code="webapp.name" /></span>
  </a> <!-- Header Navbar --> <nav class="navbar navbar-static-top" role="navigation"> <!-- Sidebar toggle button--> <a href="#"
   class="sidebar-toggle" data-toggle="offcanvas" role="button"> <span class="sr-only">Toggle navigation</span>
  </a> <!-- Navbar Right Menu -->
 
  <div class="navbar-custom-menu">
   <ul class="nav navbar-nav">
   		<!-- 语言切换 -->
   	<li role="presentation" class="dropdown">
	    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
	      切换语言 <span class="caret"></span>
	    </a>
	    <ul class="dropdown-menu">
	      	<li><a href="?lang=zh_CN"><spring:message code="language.cn" /></a><li>
		  	<li><a href="?lang=en_US"><spring:message code="language.en" /></a><li>
		  	<li><a href="?lang=ja_JP"><spring:message code="language.ja" /></a><li>
		  	<li><a href="?lang=ko_KR"><spring:message code="language.ko" /></a><li>
	    </ul>
	  </li>
    <!-- User Account Menu -->
    <li class="dropdown user user-menu">
     <!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <!-- The user image in the navbar <img
      src="${_assets}/adminlte/dist/img/user2-160x160.jpg" class="user-image" alt="User Image"> --> <!-- hidden-xs hides the username on small devices so only the image appears. -->
      <!-- <spring:message code="login.nowlanguage" />: ${pageContext.response.locale } -->
      <span class="hidden-xs"><spring:message code="index.loginNow" /> ：<shiro:principal property="displayName" />
      
      </span>
    </a>
 
     <ul class="dropdown-menu">
      <!-- Menu Footer-->
      <li class="user-footer">
       <div class="pull-right">
        <input type="button" id="logoutButton" class="btn btn-primary" value="<spring:message code='index.logoutButton' />" />
       </div>
      </li>
     </ul>
    </li>
    <!-- Control Sidebar Toggle Button -->
   </ul>
  </div>
  </nav> </header>
  <aside class="main-sidebar"> <section class="sidebar">
  <ul class="sidebar-menu">
  
   <c:forEach items="${_systems}" var="sys">
    <li class="<c:if test="${sys.active}">active</c:if> treeview"><a href="#"><i class="fa fa-th"></i> <span>
    	<c:choose>
		  	<c:when test="${'zh_CN'==pageContext.response.locale }">
		  		${sys.name}
		  	</c:when>
		  	<c:when test="${'en_US'==pageContext.response.locale }">
		  		${sys.permissionEnglishName}
		  	</c:when>
		  	<c:when test="${'ko_KR'==pageContext.response.locale }">
		  		${sys.permissionKoreanName}
		  	</c:when>
		  	<c:when test="${'ja_JP'==pageContext.response.locale }">
		  		${sys.permissionJapaneseName}
		  	</c:when>
		  	<c:otherwise>
		  		${sys.name}
		  	</c:otherwise>
	  </c:choose>
    </span></a> 
    <c:forEach items="${sys.dirs}" var="dir">
      <ul class="treeview-menu">
       <li class="treeview <c:if test="${dir.active}">active</c:if>"><a href="#"><i class="fa ${dir.icon}"></i> 
       <c:choose>
		  	<c:when test="${'zh_CN'==pageContext.response.locale }">
		  		${dir.name}
		  	</c:when>
		  	<c:when test="${'en_US'==pageContext.response.locale }">
		  		${dir.permissionEnglishName}
		  	</c:when>
		  	<c:when test="${'ko_KR'==pageContext.response.locale }">
		  		${dir.permissionKoreanName}
		  	</c:when>
		  	<c:when test="${'ja_JP'==pageContext.response.locale }">
		  		${dir.permissionJapaneseName}
		  	</c:when>
		  	<c:otherwise>
		  		${dir.name}
		  	</c:otherwise>
	  </c:choose></a>
        <ul class="treeview-menu">
         <c:forEach items="${dir.menus}" var="dirMenu">
          <li class="treeview <c:if test="${dirMenu.active}"> menu-open</c:if>"><a href="${contextPath}/backend/${dirMenu.url}"> <i
            class="fa ${dirMenu.icon}"></i>
            <c:choose>
			  	<c:when test="${'zh_CN'==pageContext.response.locale }">
			  		${dirMenu.name}
			  	</c:when>
			  	<c:when test="${'en_US'==pageContext.response.locale }">
			  		${dirMenu.permissionEnglishName}
			  	</c:when>
			  	<c:when test="${'ko_KR'==pageContext.response.locale }">
			  		${dirMenu.permissionKoreanName}
			  	</c:when>
			  	<c:when test="${'ja_JP'==pageContext.response.locale }">
			  		${dirMenu.permissionJapaneseName}
			  	</c:when>
			  	<c:otherwise>
			  		${dirMenu.name}
			  	</c:otherwise>
		  </c:choose>
          </a></li>
         </c:forEach>
        </ul></li>
      </ul>
     </c:forEach>
     <ul class="treeview-menu">
      <c:forEach items="${sys.menus}" var="menu">
       <li class="<c:if test="${menu.active}">active</c:if>"><a href="${contextPath}/backend/${menu.url}"> <i class="fa ${menu.icon}"></i>
         <c:choose>
		  	<c:when test="${'zh_CN'==pageContext.response.locale }">
		  		${menu.name}
		  	</c:when>
		  	<c:when test="${'en_US'==pageContext.response.locale }">
		  		${menu.permissionEnglishName}
		  	</c:when>
		  	<c:when test="${'ko_KR'==pageContext.response.locale }">
		  		${menu.permissionKoreanName}
		  	</c:when>
		  	<c:when test="${'ja_JP'==pageContext.response.locale }">
		  		${menu.permissionJapaneseName}
		  	</c:when>
		  	<c:otherwise>
		  		${menu.name}
		  	</c:otherwise>
		  </c:choose>
       </a></li>
      </c:forEach>
     </ul></li>
   </c:forEach>
  </ul>
  <!-- /.sidebar-menu --> </section> <!-- /.sidebar --> </aside>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
   <decorator:body />
  </div>
  <!-- /.content-wrapper -->
  <!-- Main Footer -->
  <!-- Control Sidebar -->
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
 </div>
 <!-- ./wrapper -->
</body>
</html>