package com.config.spring;

import java.io.IOException;
import java.sql.SQLException;

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
	 * @throws SQLException 
	 */
	@Bean(initMethod="init",destroyMethod="close")
	public DataSource dataSource() throws SQLException{
		DruidDataSource dataSource = new DruidDataSource();
		//基本属性 url、user、password
		dataSource.setUrl(env.getProperty("dataSource.url"));
		dataSource.setUsername(env.getProperty("dataSource.username"));
		dataSource.setPassword(env.getProperty("dataSource.password"));
		//配置初始化大小、最小、最大
		dataSource.setInitialSize(Integer.valueOf(env.getProperty("dataSource.initialSize")));
		dataSource.setMinIdle(Integer.valueOf(env.getProperty("dataSource.minIdle")));
		dataSource.setMaxActive(Integer.valueOf(env.getProperty("dataSource.maxActive")));
		//配置获取连接等待超时的时间
		dataSource.setMaxWait(Integer.valueOf(env.getProperty("dataSource.maxWait")));
		//配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
		dataSource.setTimeBetweenEvictionRunsMillis(Integer.valueOf(env.getProperty("dataSource.timeBetweenEvictionRunsMillis")));
		//配置一个连接在池中最小生存的时间，单位是毫秒
		dataSource.setMinEvictableIdleTimeMillis(Integer.valueOf(env.getProperty("dataSource.minEvictableIdleTimeMillis")));
		dataSource.setValidationQuery(env.getProperty("dataSource.validationQuery"));
		dataSource.setTestWhileIdle(Boolean.valueOf(env.getProperty("dataSource.testWhileIdle")));
		dataSource.setTestOnBorrow(Boolean.valueOf(env.getProperty("dataSource.testOnBorrow")));
		dataSource.setTestOnReturn(Boolean.valueOf(env.getProperty("dataSource.testOnReturn")));
		//打开PSCache，并且指定每个连接上PSCache的大小
		dataSource.setPoolPreparedStatements(Boolean.valueOf(env.getProperty("dataSource.poolPreparedStatements")));
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.valueOf(env.getProperty("dataSource.maxPoolPreparedStatementPerConnectionSize")));
		//配置监控统计拦截的filters，去掉后监控界面sql无法统计
		dataSource.setFilters(env.getProperty("dataSource.filters"));
		//每隔5分钟将监控日志输出到日志文件中，单位是毫秒
		dataSource.setTimeBetweenLogStatsMillis(Long.valueOf(env.getProperty("dataSource.timeBetweenLogStatsMillis")));
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
