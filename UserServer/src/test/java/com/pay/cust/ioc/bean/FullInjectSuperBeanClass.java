package com.pay.cust.ioc.bean;


public class FullInjectSuperBeanClass {
	private FieldClass superFieldClass;

	public void superPrint() {
		superFieldClass.print();
	}
}
