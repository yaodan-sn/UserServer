package com.lefu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;

/**
 * dubbo配置类 注：针对timeout,retries,loadbalance,actives等方法级优先，接口级次之，全局配置再次之。
 * 如果级别一样，则消费方优先，提供方次之。
 */

@Configuration
@PropertySource("classpath:dubbo.properties")
public class DubboConfig {

	@Value("${dubbo.application.name}")
	private String applicationName;// 服务名称，如果既有consumer又有provider则只需要声明一次

	@Value("${dubbo.application.logger}")
	private String loggerName;// 日志适配

	@Value("${dubbo.annotation.package}")
	private String packageName;

	@Value("${lefu.dubbo.regAddress}")
	private String lefuRegAddress;// dubbo注册地址

	@Value("${zyt.dubbo.regAddress}")
	private String zytRegAddress;// dubbo注册地址

	@Value("${dubbo.protocol.name}")
	private String protocolName;// dubbo使用协议

	@Value("${dubbo.protocol.port}")
	private int protocolPort;// 提供服务的端口

	@Value("${dubbo.provider.timeout}")
	private int timeout;

	@Value("${dubbo.provider.retries}")
	private int retries;// 是否重试

	@Value("${dubbo.provider.delay}")
	private int delay;// 延迟暴漏，防止死锁

	@Value("${dubbo.provider.filter:}")
	private String filter;// dubbo过滤器

	/**
	 * 设置dubbo扫描包
	 * 
	 * @param packageName
	 * @return
	 */
	@Bean
	public static AnnotationBean annotationBean(
			@Value("${dubbo.annotation.package}") String packageName) {
		AnnotationBean annotationBean = new AnnotationBean();
		annotationBean.setPackage("com.lefu.**.dubbo");
		return annotationBean;
	}

	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName(applicationName);
		applicationConfig.setLogger(loggerName);
		return applicationConfig;
	}

	/**
	 * 注入dubbo注册中心配置,基于zookeeper
	 * 
	 * @return
	 */
	@Bean(name = "lefu")
	@Primary
	public RegistryConfig lefuRegConfig() {
		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig(lefuRegAddress);
		registry.setId("lefu");
		return registry;
	}

	@Bean(name = "zyt")
	public RegistryConfig zytRegConfig() {
		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig(zytRegAddress);
		registry.setId("zyt");
		registry.setDefault(false);
		return registry;
	}

	/**
	 * 默认基于dubbo协议提供服务
	 *
	 * @return
	 */
	@Bean
	public ProtocolConfig protocolConfig() {
		// 服务提供者协议配置
		ProtocolConfig protocolConfig = new ProtocolConfig();
		protocolConfig.setName(protocolName);
		protocolConfig.setPort(protocolPort);
		protocolConfig.setThreads(200);
		return protocolConfig;
	}

	/**
	 * dubbo服务提供
	 *
	 * @param applicationConfig
	 * @param registryConfig
	 * @param protocolConfig
	 * @return
	 */
	@Bean(name = "defaultProvider")
	public ProviderConfig providerConfig(ApplicationConfig applicationConfig,
			RegistryConfig registryConfig, ProtocolConfig protocolConfig) {
		ProviderConfig providerConfig = new ProviderConfig();
		providerConfig.setTimeout(timeout);
		providerConfig.setRetries(retries);
		providerConfig.setDelay(delay);
		providerConfig.setApplication(applicationConfig);
		providerConfig.setRegistry(registryConfig);
		providerConfig.setExport(true);
		providerConfig.setRegister(true);
		providerConfig.setProtocol(protocolConfig);

		if (StringUtils.hasText(filter)) {
			providerConfig.setFilter(filter);
		}
		return providerConfig;
	}
}
