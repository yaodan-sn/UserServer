package com.lefu.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.transform.Source;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author dan
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.lefu.**.web" })
public class WebConfig extends WebMvcConfigurerAdapter {

	/**
	 * 静态资源文件
	 */
	private static final String RESOURCE_PATH = "/static/**";
	private static final String RESOURCE_LOCATIONS = "/static/";
	
	private static final String PREFIX = "/WEB-INF/jsp/";//页面前缀
    private static final String SUFFIX = ".jsp";//页面后缀

    private static final String JSON = "json";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";//日期格式化

	@Resource
	private ApplicationContext applicationContext;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(RESOURCE_PATH).addResourceLocations(RESOURCE_LOCATIONS);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		stringConverter.setWriteAcceptCharset(false);

		converters.add(new ByteArrayHttpMessageConverter());
		converters.add(stringConverter);
		converters.add(new ResourceHttpMessageConverter());
		converters.add(new SourceHttpMessageConverter<Source>());

		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
				.applicationContext(this.applicationContext).build();
		converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
	}

	/**
	 * 内容协商解析器
	 * 
	 * @param manager
	 * @return
	 */
	@Bean
	public ViewResolver getContentNegotiatingViewResolver(ContentNegotiationManager manager) {
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		List<View> view = new ArrayList<View>();
		// 解析json
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.getObjectMapper().setDateFormat(new SimpleDateFormat(DATE_FORMAT));
		view.add(jsonView);
		// 解析jsp
		InternalResourceViewResolver jspResolver = new InternalResourceViewResolver();
		jspResolver.setPrefix(PREFIX);
		jspResolver.setSuffix(SUFFIX);
		jspResolver.setOrder(1);
		resolvers.add(jspResolver);

		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setDefaultViews(view);
		resolver.setViewResolvers(resolvers);
		resolver.setContentNegotiationManager(manager);
		return resolver;
	}

	/**
	 * 配置内容协商管理器 1、默认解析为html 2、带有.json后缀的解析为json数据返回
	 * 
	 * @param configurer
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(true).ignoreAcceptHeader(true).useJaf(false)
				.defaultContentType(MediaType.TEXT_HTML)
				.mediaType(JSON, MediaType.APPLICATION_JSON);
	}

}