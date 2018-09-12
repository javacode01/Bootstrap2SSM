package com.sys.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class SysWebAuthenticationDetails extends WebAuthenticationDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4034638260365098640L;
	
	private final String requestAuthCode;
	
	private final String sessionAuthCode;

	public SysWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		requestAuthCode = request.getParameter("authCode");
		HttpSession session = request.getSession(false);
		if(null!=session&&null!=session.getAttribute("authCode")) {
			sessionAuthCode = (String) session.getAttribute("authCode");
		}else {
			sessionAuthCode = "";
		}
	}

	public String getRequestAuthCode() {
		return requestAuthCode;
	}

	public String getSessionAuthCode() {
		return sessionAuthCode;
	}
	
}
