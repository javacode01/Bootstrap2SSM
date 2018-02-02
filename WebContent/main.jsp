<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:url value="/" var="basepath"></c:url>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录成功后主页面</title>
</head>
<body>
你好！<security:authentication property="principal.username"/>!
<security:authorize access="hasRole('ROLE_USER')">
拥有：ROLE_USER权限
</security:authorize>
<security:authorize access="hasRole('ROLE_ADMIN')">
拥有：ROLE_ADMIN权限
</security:authorize>
<form id="loginform" action="${basepath}signout" method="POST">
	<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
	<input type="submit" value="退出"/>
</form>
</body>
</html>