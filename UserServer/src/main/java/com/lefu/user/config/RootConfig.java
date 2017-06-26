package com.lefu.user.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.lefu.camel.client.api.impl.CamelClientApiImpl;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.lefu.user" }, excludeFilters = @ComponentScan.Filter(classes = Controller.class))
@MapperScan(basePackages = { "com.lefu.user.dao" }, sqlSessionFactoryRef = "sqlSessionFactory")
@PropertySource({ "classpath:sms.properties", "classpath:system.properties" })
public class RootConfig {
	@Resource
	private DataSource dataSource;

	@Value("${com.lefu.camel.host}")
	private String camelHost;
	@Value("${com.lefu.camel.port}")
	private int camelPort;

	/*
	 * @Bean public static PropertySourcesPlaceholderConfigurer
	 * propertySourcesPlaceholderConfigurer() { return new
	 * PropertySourcesPlaceholderConfigurer(); }
	 */

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		org.springframework.core.io.Resource[] resources = resolver
				.getResources("classpath:com/lefu/user/dao/**/*.xml");
		sqlSessionFactoryBean.setMapperLocations(resources);
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public CamelClientApiImpl camelClientApiImpl() {
		CamelClientApiImpl c = new CamelClientApiImpl();
		c.setHost(camelHost);
		c.setPort(camelPort);
		return c;
	}

}
