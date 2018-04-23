package com.config.springMVC;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.demo.action.DemoActionConfig;
import com.mobile.action.MobileActionConfig;
import com.mobile.interceptor.JwtInterceptor;
import com.sys.action.SysActionConfig;

/**
 * @ClassName: WebConfig 
 * @Description: 配置SpringMVC加载的应用上下文
 * @author AK
 * @date 2017-11-13 下午9:04:30 
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses={SysActionConfig.class,DemoActionConfig.class,MobileActionConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter{
	
	/**
	 * 配置JSP视图解析器
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
		return resolver;
	}
	
	/**
	 * DispatcherServlet将对静态资源的请求转发到Servlet容器中默认的Servlet上，
	 * 而不是使用DispatcherServlet本身来处理此类请求。
	 */
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		configurer.enable();
	}
	
	/**
	 * 添加自定义SpringMVC拦截器
	 * @return
	 */
	@Bean
	public JwtInterceptor jwtInterceptor() {
		return new JwtInterceptor();
	}
	
	/** 
     * 将自定义拦截器添加到springMVC拦截器，并设置拦截路径 
     * @param registry 
     */
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor()).addPathPatterns("/mobile/**");
    }
	
//	/**
//	 * 配置springMVC文件上传
//	 * @return
//	 */
//	@Bean
//	public MultipartResolver multipartResolver() {
//		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//		multipartResolver.setDefaultEncoding("UTF-8");
//		//设置一次上传文件总大小不能超过20M
//		multipartResolver.setMaxUploadSize(2048000000);
//		return multipartResolver;
//	}
	
	/**
	 * 配置springMVC文件上传
	 * @return
	 */
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
}
