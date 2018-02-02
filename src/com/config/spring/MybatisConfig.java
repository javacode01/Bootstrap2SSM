package com.config.spring;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.sys.client.SysClientConfig;

/**
 * @ClassName: MybatisConfig 
 * @Description: spring整合Mybatis配置
 * @author AK
 * @date 2017-11-14 上午9:33:38 
 *
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackageClasses={SysClientConfig.class})
@PropertySource("classpath:/datasource.properties")
public class MybatisConfig {
	
	@Autowired
	Environment env;
	
	/**
	 * 配置数据源
	 * @return
	 */
	@Bean(destroyMethod="close")
	public DataSource dataSource(){
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(env.getProperty("dataSource.url"));
		dataSource.setUsername(env.getProperty("dataSource.username"));
		dataSource.setPassword(env.getProperty("dataSource.password"));
		dataSource.setInitialSize(Integer.valueOf(env.getProperty("dataSource.initialSize")));
		dataSource.setMinIdle(Integer.valueOf(env.getProperty("dataSource.minIdle")));
		dataSource.setMaxActive(Integer.valueOf(env.getProperty("dataSource.maxActive")));
		dataSource.setMaxWait(Integer.valueOf(env.getProperty("dataSource.maxWait")));
		dataSource.setTimeBetweenEvictionRunsMillis(Integer.valueOf(env.getProperty("dataSource.timeBetweenEvictionRunsMillis")));
		dataSource.setMinEvictableIdleTimeMillis(Integer.valueOf(env.getProperty("dataSource.minEvictableIdleTimeMillis")));
		dataSource.setValidationQuery(env.getProperty("dataSource.validationQuery"));
		dataSource.setTestWhileIdle(Boolean.valueOf(env.getProperty("dataSource.testWhileIdle")));
		dataSource.setTestOnBorrow(Boolean.valueOf(env.getProperty("dataSource.testOnBorrow")));
		dataSource.setTestOnReturn(Boolean.valueOf(env.getProperty("dataSource.testOnReturn")));
		return dataSource;
	}
	
	/**
	 * 注入数据库事务管理bean
	 * @return
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource){
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}
	
	/**
	 * 注入sqlSessionFactory
	 * @param dataSource
	 * @return
	 * @throws IOException
	 */
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException{
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();  
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();  
        sqlSessionFactoryBean.setDataSource(dataSource);  
        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath*:com/sys/**/sqlmap/*.xml"));  
        return sqlSessionFactoryBean;
	}
	
}
