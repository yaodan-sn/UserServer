package com.example.config;

import java.io.IOException;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.lefu.config", "com.lefu.aop", "com.lefu.**.service",
		"com.lefu.user.task" }, excludeFilters = @ComponentScan.Filter(classes = Controller.class))
@PropertySource({ "classpath:system.properties", "classpath:sms.properties" })
public class RootConfig {

	@Bean
	public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() throws IOException {
		PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		org.springframework.core.io.Resource[] resources = resolver
				.getResources("classpath*:*.properties");
		propertyPlaceholderConfigurer.setLocations(resources);
		return propertyPlaceholderConfigurer;
	}

}
