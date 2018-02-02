<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/" var="basepath"></c:url>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页</title>
<script type="text/javascript" src="${basepath}resource/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${basepath}resource/js/md5.js"></script>
<script type="text/javascript">
	function login(){
		$("#password").val(toMD5Str($("#password").val()));
		$("#loginform").submit();
	}
</script>
</head>
<body>
Bootstrap2SSM登录页
<a href="${basepath}loginsuccess">主页</a>
<div>
	<form id="loginform" action="${basepath}login" method="POST">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" id="username" name="username" value="admin"/></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" id="password" name="password" value="123456"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input name="${_csrf.parameterName}" type="text" value="${_csrf.token}"/>
					<input type="button" value="登录" onclick='login()'/>
					<c:if test="${param.error != null}">        
					<p>
							Invalid username and password.${param.error }
					</p>
					</c:if>
					<c:if test="${param.logout != null}">       
						<p>
							You have been logged out.${param.logout }
						</p>
					</c:if>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>