package com.sys.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;

public class SysAuthCodeFilter extends OncePerRequestFilter {

	@Autowired
	private AuthenticationFailureHandler sysLoginFailureHandler;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getRequestURI().contains("/login") && "post".equals(request.getMethod().toLowerCase())) {
			try {
				String sessionAuthCode = (String) request.getSession(false).getAttribute("authCode");
				request.getSession(false).removeAttribute("authCode");
				String authCode = request.getParameter("authCode");
				if (!sessionAuthCode.equals(authCode)) {
					throw new AuthenticationServiceException("验证码操作");
				}
			} catch (Exception e) {
				sysLoginFailureHandler.onAuthenticationFailure(request, response, new AuthenticationServiceException("验证码错误"));
				// 不继续执行
				return;
			}
		}
		// 继续执行下一步
		filterChain.doFilter(request, response);
	}

}
