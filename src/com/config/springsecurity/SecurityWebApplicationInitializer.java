package com.config.springsecurity;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
/**
 * @ClassName: SecurityWebApplicationInitializer 
 * @Description: AbstractSecurityWebApplicationInitializer实现了
 * WebApplicationInitializer，因此Spring会发现它，并用它在Web容器中注册
 * DelegatingFilterProxy。DelegatingFilterProxy会拦截发往应用中的请求，
 * 并将请求委托给ID为springSecurityFilterChain bean。
 * @author AK
 * @date 2017-11-13 下午11:48:13 
 *
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer{

}
