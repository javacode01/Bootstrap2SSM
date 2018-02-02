package com.config.springMVC;

import java.io.File;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.config.spring.RootConfig;
import com.config.springsecurity.SpringSecurityConfig;
import com.sys.utils.ConfigUtils;

/**
 * @ClassName: WebAppInitializer 
 * @Description: 初始化spring应用上下文，包括DispatcherServlet加载的应用上下文
 * 和ContextLoaderListener创建的应用上下文
 * @author AK
 * @date 2017-11-13 下午8:36:21 
 * 1、按照传统的方式，像DispatcherServlet这样的Servlet会配置在web.xml文件中。
 * 2、扩展AbstractAnnotation-ConfigDispatcherServletInitializer的任意类都
 * 会自动地配置Dispatcher-Servlet和Spring应用上下文，Spring的应用上下文会位于应用
 * 程序的Servlet上下文之中。
 * 3、两个应用上下文之间的故事
 * 当DispatcherServlet启动的时候，它会创建Spring应用上下文，并加载配置文件或配置类
 * 中所声明的bean。在getServletConfigClasses()方法中，我们要
 * 求DispatcherServlet加载应用上下文时，使用定义在WebConfig配置类中的bean。
 * 但是在Spring Web应用中，通常还会有另外一个应用上下文。另外的这个应用上下文是
 * 由ContextLoaderListener创建的。
 * 我们希望DispatcherServlet加载包含Web组件的bean，如控制器、视图解析器以及处理器
 * 映射，而ContextLoaderListener要加载应用中的其他bean。这些bean通常是驱动应用后
 * 端的中间层和数据层组件。
 * 实际上，AbstractAnnotationConfigDispatcherServletInitializer会同时创建
 * DispatcherServlet和ContextLoaderListener。GetServlet-ConfigClasses()
 * 方法返回的带有@Configuration注解的类将会用来定义DispatcherServlet应用上下
 * 文中的bean。getRootConfigClasses()方法返回的带有@Configuration注解的类将会
 * 用来配置ContextLoaderListener创建的应用上下文中的bean。
 */

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	/**
	 * ContextLoaderListener要加载应用中的其他bean,
	 * 这些bean通常是驱动应用后端的中间层和数据层组件
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] {RootConfig.class,SpringSecurityConfig.class};
	}

	/**
	 * DispatcherServlet加载包含Web组件的bean，如控制器、视图解析器以及处理器映射
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] {WebConfig.class};
	}
	
	/**
	 * getServletMappings()方法会将一个或多个路径映射到DispatcherServlet上。
	 * 在本类中，它映射的是“/”，这表示它会是应用的默认Servlet。它会处理进入应用的所有请求。
	 */
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}
	
	/**
	 * 配置springMVC上传文件参数
	 */
	@Override
	protected void customizeRegistration(Dynamic registration) {
		File tempFile = new File(ConfigUtils.getValue("SYS.ATTACHMENT_PATH"));
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		//临时路径为SYS.ATTACHMENT_PATH，单个文件大小不超过2M，整个请求不超过4M
		registration.setMultipartConfig(new MultipartConfigElement(ConfigUtils.getValue("SYS.ATTACHMENT_PATH"),2097152,4194304,0));
	}
}
