package com.lefu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.lefu.camel.client.api.impl.CamelClientApiImpl;

@Configuration
@PropertySource("classpath:system.properties")
public class CamelClientConfig {

	@Value("${com.lefu.camel.host}")
	private String camelHost;
	@Value("${com.lefu.camel.port}")
	private int camelPort;

	@Bean
	public CamelClientApiImpl camelClientApiImpl() {
		CamelClientApiImpl c = new CamelClientApiImpl();
		c.setHost(camelHost);
		c.setPort(camelPort);
		return c;
	}
}
