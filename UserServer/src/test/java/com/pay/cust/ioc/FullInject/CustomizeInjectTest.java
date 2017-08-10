package com.pay.cust.ioc.FullInject;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.pay.cust.ioc.bean.FullInjectBeanClass;

@Configuration
@ComponentScan(basePackages = { "com.pay.cust.ioc.FullInject", "com.pay.cust.ioc.bean" })
public class CustomizeInjectTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(CustomizeInjectTest.class);
		annotationConfigApplicationContext.refresh();
		FullInjectBeanClass fullInjectBeanClass = annotationConfigApplicationContext
				.getBean(FullInjectBeanClass.class);
		fullInjectBeanClass.print();
		fullInjectBeanClass.superPrint();
	}
}