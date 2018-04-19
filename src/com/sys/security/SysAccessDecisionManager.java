package com.sys.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

/**
 * @ClassName: SysAccessDecisionManager 
 * @Description: 授权器，通过登录用户的权限信息、资源、获取资源所需的权限来根据不同的授权策略来判断用户是否有权限访问资源
 * @author AK
 * @date 2017-11-16 上午9:15:37 
 *
 */
@Service
public class SysAccessDecisionManager implements AccessDecisionManager {
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	/**
	 * decide这个方法没有任何的返回值，需要在没有通过授权时抛出AccessDeniedException。
	 * 参数authentication是从spring的全局缓存SecurityContextHolder中拿到的，里面是用户的权限信息
	 * 参数configAttributes所需的权限  
	 */
	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		
		//校验Session是否有效 	
//		FilterInvocation fi = (FilterInvocation)object;
//		String sessionId = fi.getHttpRequest().getSession(true).getId();
//		SessionInformation si = sessionRegistry.getSessionInformation(sessionId);
//		if(null==si||si.isExpired()){
//			throw new SessionAuthenticationException("当前登陆用户已失效");
//		}
		Iterator<ConfigAttribute> cons = configAttributes.iterator();
		while (cons.hasNext()) {
			ConfigAttribute ca = cons.next();
			String needRole = ((SecurityConfig) ca).getAttribute();
			// gra 为用户所被赋予的权限，needRole为访问相应的资源应具有的权限
			for (GrantedAuthority gra : authentication.getAuthorities()) {
				if (needRole.trim().equals(gra.getAuthority().trim())) {
					return;
				}
			}
		}
		 //没有权限    会跳转到登录页面 
		throw new AccessDeniedException("没有权限");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
}
