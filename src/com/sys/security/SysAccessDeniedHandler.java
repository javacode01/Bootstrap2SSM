package com.sys.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.sys.utils.SysUtils;

/**
 * 自定义权限不足处理
 * @author Administrator
 *
 */
public class SysAccessDeniedHandler implements AccessDeniedHandler{
	
	private String errorPage;
	
	@Override
	public void handle(HttpServletRequest request,HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,ServletException {
		// TODO Auto-generated method stub
		boolean isAjax = SysUtils.isAjaxRequest(request);
		if (isAjax) {
			request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);
			// Set the 403 status code.
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		} else if (!response.isCommitted()) {
			if (errorPage != null) {
				// Put exception into request scope (perhaps of use to a view)
				request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);

				// Set the 403 status code.
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);

				// forward to error page.
				RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
				dispatcher.forward(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
			}
		}
	}

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		if ((errorPage != null) && !errorPage.startsWith("/")) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}
		this.errorPage = errorPage;
	}

}
