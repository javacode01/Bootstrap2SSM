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
<body class="hold-transition login-page" style="background-color:#2683BE;">
	<div style="height:100%;padding-top:7%;">
	<div class="login-box" style="margin:auto;">
		<div class="login-logo">
			<a style="color:#FFFFFF;"><%=BspUtils.getSysName() %></a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body" style="background-color:#DCEBF4;">
			<c:if test="${param.error != null}">
				<p class="login-box-msg" style="color:red;">用户名密码错误</p>
			</c:if>
			<form id="loginform" action="${basepath}login" method="POST">
				<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
				<div class="form-group has-feedback">
					<input id="username" type="text" class="form-control" name="username" placeholder="用户名">
					<span class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input id="password" type="password" class="form-control" name="password" placeholder="密码">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-sm-8">
						<input id="authCode" type="text" class="form-control" name="authCode" placeholder="验证码">
					</div>
					<!-- /.col -->
					<div class="col-sm-4">
						<img src="${basepath}getAuthCode" id="codeImage" type="image" title="图片看不清？点击重新得到验证码" />
					</div>
					<!-- /.col -->
				</div>
				<div class="row" style="margin-top:20px;">
					<div class="col-sm-8">
						<div class="checkbox icheck">
							<label> <input type="checkbox" id="checkbox" checked> 记住我
							</label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-sm-4">
						<button id="login" type="button" class="btn btn-primary btn-block btn-flat">登 录</button>
					</div>
					<!-- /.col -->
				</div>
			</form>
		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->
	</div>

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
			$('#codeImage').click(function(){
				$('#codeImage').attr('src','${basepath}getAuthCode?abc='+Math.random());
			});
			$('#login').click(function(){
				if(!$("#username").val()){
					$("#username").css("border","1px solid #D9544F");
					return false;
				}else{
					$("#username").css("border","1px solid #3C8DBC");
				}
				if(!$("#password").val()){
					$("#password").css("border","1px solid #D9544F");
					return false;
				}else{
					$("#password").css("border","1px solid #3C8DBC");
				}
				if(!$("#authCode").val()){
					$("#authCode").css("border","1px solid #D9544F");
					return false;
				}else{
					$("#authCode").css("border","1px solid #3C8DBC");
				}
				local();
				$("#password").val(toMD5Str($("#password").val()));
				$("#loginform").submit();
			});
			$("#username").on('input propertychange',function(){
				if(!$("#username").val()){
					$("#username").css("border","1px solid #D9544F");
				}else{
					$("#username").css("border","1px solid #3C8DBC");
				}
			});
			$("#password").on('input propertychange',function(){
				if(!$("#password").val()){
					$("#password").css("border","1px solid #D9544F");
				}else{
					$("#password").css("border","1px solid #3C8DBC");
				}
			});
			$("#authCode").on('input propertychange',function(){
				if(!$("#authCode").val()){
					$("#authCode").css("border","1px solid #D9544F");
				}else{
					$("#authCode").css("border","1px solid #3C8DBC");
				}
			});
			if (typeof(Storage) !== "undefined") {
			    $("#username").val(localStorage.getItem("username"));
			    $("#password").val(localStorage.getItem("password"));
			}
		});
		function local(){
			if (typeof(Storage) !== "undefined") {
				if($("#checkbox").get(0).checked){
					localStorage.setItem("username", $("#username").val());
					localStorage.setItem("password", $("#password").val());
				}else{
					localStorage.removeItem("username");
					localStorage.removeItem("password");
				}
			}
		}
	</script>
</body>
</html>
