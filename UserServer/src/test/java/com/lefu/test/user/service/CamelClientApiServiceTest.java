package com.lefu.test.user.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.lefu.test.common.WebAppContextSetupTest;
import com.lefu.user.service.CamelClientApiService;

public class CamelClientApiServiceTest extends WebAppContextSetupTest {
	@Resource
	private CamelClientApiService camelClientApiService;

	@Test
	public void testSendSMS() {
		camelClientApiService.sendSMS("234234342", "18301310620");
	}

}