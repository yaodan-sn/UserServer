package com.pay.cust.ioc.FullInject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.pay.cust.ioc.MyInject.MyInject;

@Component
public class MyInjectBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {
	private ApplicationContext applicationContext;

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if (hasAnnotation(bean.getClass().getAnnotations(), FullInject.class.getName())) {
			Class beanClass = bean.getClass();
			do {
				Field[] fields = beanClass.getDeclaredFields();
				for (Field field : fields) {
					setField(bean, field);
				}
			} while ((beanClass = beanClass.getSuperclass()) != null);
		} else {
			processMyInject(bean);
		}
		return bean;
	}

	private void processMyInject(Object bean) {
		Class beanClass = bean.getClass();
		do {
			Field[] fields = beanClass.getDeclaredFields();
			for (Field field : fields) {
				if (!hasAnnotation(field.getAnnotations(), MyInject.class.getName())) {
					continue;
				}
				setField(bean, field);
			}
		} while ((beanClass = beanClass.getSuperclass()) != null);
	}

	private void setField(Object bean, Field field) {
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		try {
			field.set(bean, applicationContext.getBean(field.getType()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean hasAnnotation(Annotation[] annotations, String annotationName) {
		if (annotations == null) {
			return false;
		}
		for (Annotation annotation : annotations) {
			if (annotation.annotationType().getName().equals(annotationName)) {
				return true;
			}
		}
		return false;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}