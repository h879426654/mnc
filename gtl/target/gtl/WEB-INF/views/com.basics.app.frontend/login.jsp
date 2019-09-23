<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<html>
<head>
<title><spring:message code="login.title" /></title>
<meta name="decorator" content="basics.login" />

</head>

<body>
	<h1>
		<spring:message code="webapp.name" />
	</h1>
	<div class="container w3layouts agileits">
	<div class="login w3layouts agileits">
		<div class="select_box">
		  <div class="lang_selected" onclick="showSelect()"><spring:message code="login.language" />: </div>
		  <div class="option_box" style="display:none">
		    <a href="?lang=zh_CN"><spring:message code="language.cn" /></a>
		  	<a href="?lang=en_US"><spring:message code="language.en" /></a>
		  	<a href="?lang=ja_JP"><spring:message code="language.ja" /></a>
		  	<a href="?lang=ko_KR"><spring:message code="language.ko" /></a>
		  </div>
		</div>
			<h2><spring:message code="login.title" /></h2>
			<form method="post" id="loginForm">
				<input type="hidden" name="userType" value="10000" /> 
				<input type="text" name="username" id="username" placeholder="<spring:message code='login.account' />"> 
				<input type="password" name="password" id="password" placeholder="<spring:message code='login.pass' />">
				<ul class="tick w3layouts agileits">
					<li><input type="checkbox" id="rememberMe" name="rememberMe" value="true"> <label for="rememberMe"><span></span><spring:message code="login.remember" /></label></li>
				</ul>
				<div class="send-button w3layouts agileits">
					<input type="button" onclick="doLogin()" value="<spring:message code="login.title" />">
				</div>
			</form>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="footer w3layouts agileits">
		<p>
			© Copyright
			<spring:message code="copyright.year" />
			<spring:message code="copyright" />
			<br>京ICP证030173号
		</p>
	</div>
	
	<script>
  function showSelect() {
    $('.option_box').show();
  }
</script>
</body>
</html>