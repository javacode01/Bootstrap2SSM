package com.sys.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.sys.model.SysFunctions;
import com.sys.model.SysUsers;
import com.sys.utils.BspUtils;
import com.sys.utils.SysUtils;

/**
 * 自定义登录成功后的处理
 * 
 * @author Administrator
 *
 */
public class SysLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		// 认证成功后，获取用户信息并添加到session中(可直接从authenticationtication中获取，不用存储在session中)
		SysUsers sysUser = (SysUsers) authentication.getPrincipal();
		request.getSession().setAttribute("sysUser", sysUser);

		String url = null;
		// 从别的请求页面跳转过来的情况，savedRequest不为空
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest != null) {
			url = savedRequest.getRedirectUrl();
			//判断访问地址是否是配置的功能地址
			boolean f = false;
			for (SysFunctions function : BspUtils.getHandleList()) {  
                  if(!SysUtils.isNull(function.getFunctionUrl())&&url.contains(function.getFunctionUrl())) {
                	  f = true;
                	  break;
                  }
            } 
			if(!f) {
				getRedirectStrategy().sendRedirect(request, response, "/loginsuccess");
			}else {
				super.onAuthenticationSuccess(request, response, authentication);
			}
		}else {
			getRedirectStrategy().sendRedirect(request, response, "/loginsuccess");
		}
	}

	public RequestCache getRequestCache() {
		return requestCache;
	}

	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}
}
