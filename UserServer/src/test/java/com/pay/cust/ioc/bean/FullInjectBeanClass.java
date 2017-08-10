package com.pay.cust.ioc.bean;

import org.springframework.stereotype.Component;

import com.pay.cust.ioc.FullInject.FullInject;

@Component
@FullInject
public class FullInjectBeanClass extends FullInjectSuperBeanClass {
	private FieldClass fieldClass;

	public void print() {
		fieldClass.print();
	}
}