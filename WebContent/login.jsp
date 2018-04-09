<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.sys.utils.BspUtils"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/" var="basepath"></c:url>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><%=BspUtils.getSysName() %></title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<!-- 设置系统图标 -->
<link rel="shortcut icon" href="${basepath}resource/img/<%=BspUtils.getFavicon() %>" type="image/x-icon"/>
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet" href="${basepath}resource/bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="${basepath}resource/bower_components/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="${basepath}resource/bower_components/Ionicons/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${basepath}resource/dist/css/AdminLTE.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="${basepath}resource/plugins/iCheck/square/blue.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="${basepath}resource/js/html5shiv.min.js"></script>
  <script src="${basepath}resource/js/respond.min.js"></script>
  <![endif]-->
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a><%=BspUtils.getSysName() %></a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<c:if test="${param.error != null}">
				<p class="login-box-msg" style="color:red;">用户名密码错误</p>
			</c:if>
			<form id="loginform" action="${basepath}login" method="POST">
				<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
				<div class="form-group has-feedback">
					<input id="username" type="text" class="form-control" name="username" placeholder="登录名">
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input id="password" type="password" class="form-control" name="password" placeholder="密码">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label> <input type="checkbox" id="checkbox" checked> 记住我
							</label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button id="login" type="button" class="btn btn-primary btn-block btn-flat">登 录</button>
					</div>
					<!-- /.col -->
				</div>
			</form>
			<div class="row">
				<div class="col-xs-3"><a href="#">忘记密码</a></div>
				<div class="col-xs-6"></div>
				<div class="col-xs-3"><a href="#" class="text-center">注册</a></div>
			</div>
		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<!-- jQuery 3 -->
	<script src="${basepath}resource/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script src="${basepath}resource/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- iCheck -->
	<script src="${basepath}resource/plugins/iCheck/icheck.min.js"></script>
	<script type="text/javascript" src="${basepath}resource/js/md5.js"></script>
	<script>
		$(function() {
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' // optional
			});
			$('#login').click(function(){
				local();
				$("#password").val(toMD5Str($("#password").val()));
				$("#loginform").submit();
			});
			if (typeof(Storage) !== "undefined") {
			    $("#username").val(localStorage.getItem("username"));
			    $("#password").val(localStorage.getItem("password"));
			}
		});
		function local(){
			if($("#checkbox").get(0).checked){
				if (typeof(Storage) !== "undefined") {
					localStorage.setItem("username", $("#username").val());
					localStorage.setItem("password", $("#password").val());
				}
			}
		}
	</script>
</body>
</html>
