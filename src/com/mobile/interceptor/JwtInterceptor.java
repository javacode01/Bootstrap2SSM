package com.mobile.interceptor;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.mobile.utils.Const;
import com.mobile.utils.JwtUtil;
import com.mobile.utils.ResultData;

/**
 * Token校验
 * @author Administrator
 *
 */
public class JwtInterceptor extends HandlerInterceptorAdapter {
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        if (path.matches(Const.NO_INTERCEPTOR_PATH)) {
            return true;
        } else {
        	//验证token
        	ResultData rd = new ResultData();
        	String token = request.getParameter(Const.REQUEST_TOKEN);
        	if(null==token||"".equals(token)){
        		rd.setCode(Const.TOKEN_NULL);
        		rd.setResult(Const.TOKEN_NULL_DESCRIPTION);
        	}else{
        		rd = JwtUtil.parseJWT(token,JwtUtil.TOKEN_TYPE_REQUEST);
        	}
        	if(Const.SUCCESS.equals(rd.getCode())){
        		request.setAttribute(Const.REQUEST_USER_ID, (String)rd.getResult());
        		return true;
        	}else{
	        	response.setContentType("application/json;charset=UTF-8");
	    		OutputStream oStream = response.getOutputStream();
				oStream.write(JSONObject.toJSONString(rd).getBytes("UTF-8"));
				oStream.close();
				return false;
        	}
        }
    }
	
}
