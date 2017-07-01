package com.lefu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.lefu.config", "com.lefu.aop", "com.lefu.**.service" }, excludeFilters = @ComponentScan.Filter(classes = Controller.class))
@PropertySource({ "classpath:system.properties", "classpath:sms.properties" })
public class RootConfig {

}
