package com.config.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.sys.security.SysAccessDecisionManager;
import com.sys.security.SysAccessDeniedHandler;
import com.sys.security.SysAuthenticationProvider;
import com.sys.security.SysLoginFailureHandler;
import com.sys.security.SysLoginSuccessHandler;
import com.sys.security.SysSecurityFilter;
import com.sys.security.SysSecurityMetadataSource;

/**
 * @ClassName: SecurityConfig 
 * @Description: Spring Security安全性配置
 * @author AK
 * @date 2017-11-13 下午10:32:38 
 *
 */
@Configuration
@EnableWebMvcSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	/**
	 * 配置Spring Security的Filter链
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/resource/**","/mobile/**");//允许访问静态的资源，和在XML配置http@security=none的效果一样
	}
	
	/**
	 * 配置如何通过拦截器保护请求
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
		.antMatchers("/index.jsp","/login","/getAuthCode").permitAll()//登录页面无条件允许访问
		.anyRequest().hasAuthority("ROLE_USER")//其他访问都需要有ROLE_USER角色权限
		.and()
		.formLogin().loginPage("/login").loginProcessingUrl("/login")//配置登录页和登录地址
//		.defaultSuccessUrl("/loginsuccess")//配置登录成功后跳转地址
		.successHandler(sysLoginSuccessHandler())//登录成功后，自定义处理
		.failureHandler(sysLoginFailureHandler())//登录失败后，自定义处理
		.and()
//		.rememberMe().tokenValiditySeconds(4*7*24*60*60).key("Bootstrap2SSM")//启用Remember-me功能，设置有效时间（单位秒）和私钥
//		.and()
		.logout().logoutUrl("/signout").logoutSuccessUrl("/").invalidateHttpSession(true)//退出
		.and()
		.exceptionHandling().accessDeniedHandler(sysAccessDeniedHandler())
		.and()
		.headers().frameOptions().disable();//允许Iframe
		//添加自定义过滤器
		http.addFilterBefore(sysSecurityFilter(), FilterSecurityInterceptor.class);
	}
	
	/**
	 * 配置user-detail服务
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		// TODO Auto-generated method stub
		//使用自定义用户验证
		auth.authenticationProvider(sysAuthenticationProvider());
	}
	
	/**
	 * 自定义过滤器（在原有过滤器链中加入自定义过滤器）
	 * @return
	 * @throws Exception 
	 */
	@Bean
	public SysSecurityFilter sysSecurityFilter() throws Exception{
		SysSecurityFilter sysSecurityFilter = new SysSecurityFilter();
		sysSecurityFilter.setAuthenticationManager(this.authenticationManager());
		sysSecurityFilter.setAccessDecisionManager(sysAccessDecisionManager());
		return sysSecurityFilter;
	}
	
	/**
	 * 从数据库提取权限和资源，装配到HashMap中，供Spring Security使用，用于权限校验。
	 * @return
	 */
	@Bean
	public SysSecurityMetadataSource sysSecurityMetadataSource(){
		SysSecurityMetadataSource sysSecurityMetadataSource = new SysSecurityMetadataSource();
		return sysSecurityMetadataSource;
	}
	
	/**
	 * 设置自定义过滤器的授权器，通过登录用户的权限信息、资源、获取资源所需的权限来根据不同的授权策略来判断用户是否有权限访问资源
	 * @return
	 */
	@Bean
	public SysAccessDecisionManager sysAccessDecisionManager(){
		SysAccessDecisionManager sysAccessDecisionManager = new SysAccessDecisionManager();
		return sysAccessDecisionManager;
	}
	
	
	/**
	 * 自定义用户验证，并获取用户权限
	 * @return
	 */
	@Bean
	public SysAuthenticationProvider sysAuthenticationProvider(){
		SysAuthenticationProvider sysAuthenticationProvider = new SysAuthenticationProvider();
		return sysAuthenticationProvider;
	}
	
	/**
	 * 自定义登录成功后处理
	 * @return
	 */
	@Bean
	public SysLoginSuccessHandler sysLoginSuccessHandler() {
		SysLoginSuccessHandler sysLoginSuccessHandler = new SysLoginSuccessHandler();
		return sysLoginSuccessHandler;
	}
	
	/**
	 * 自定义登录失败处理
	 * @return
	 */
	@Bean
	public SysLoginFailureHandler sysLoginFailureHandler() {
		SysLoginFailureHandler sysLoginFailureHandler = new SysLoginFailureHandler();
		sysLoginFailureHandler.setDefaultFailureUrl("/login?error=true");
		return sysLoginFailureHandler;
	}
	
	@Bean
	public SessionRegistry sessionRegistry(){
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}
	
	/**
	 * 自定义权限不足处理
	 * @return
	 */
	public SysAccessDeniedHandler sysAccessDeniedHandler() {
		SysAccessDeniedHandler sysAccessDeniedHandler = new SysAccessDeniedHandler();
		sysAccessDeniedHandler.setErrorPage("/error/denied");
		return sysAccessDeniedHandler;
	}
	
}
