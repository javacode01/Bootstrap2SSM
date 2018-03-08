package com.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.sys.service.SysServiceConfig;
import com.sys.utils.cache.DictionariesCache;
import com.sys.utils.cache.FunctionsCache;

/**
 * @ClassName: RootConfig 
 * @Description: 根配置文件 
 * @author AK
 * @date 2017-11-13 下午7:56:03 
 * 注明该类为配置类，并设置自动扫描的包（通过指定类，指定所需要扫描的基础包（类所在的包））
 */
@Configuration
@ComponentScan(basePackageClasses={SysServiceConfig.class})
@Import({MybatisConfig.class})
public class RootConfig {
	/**
	 * 加载数据字典缓存类
	 * @return
	 */
	@Bean
	public DictionariesCache dictionariesCache() {
		return new DictionariesCache();
	}
	/**
	 * 加载功能缓存类
	 * @return
	 */
	@Bean
	public FunctionsCache functionsCache() {
		return new FunctionsCache();
	}
}
