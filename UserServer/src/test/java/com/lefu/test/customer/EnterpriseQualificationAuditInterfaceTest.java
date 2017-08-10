package com.lefu.test.customer;

import javax.annotation.Resource;

import org.junit.Test;

import com.lefu.test.common.WebAppContextSetupTest;
import com.pay.enterprise.dubbo.EnterpriseQualificationAuditInterface;

public class EnterpriseQualificationAuditInterfaceTest extends WebAppContextSetupTest {

	@Resource
	private EnterpriseQualificationAuditInterface enterpriseQualificationAuditInterface;

	@Test
	public void testAuditManualAsync() {
		logger.info("begin");
		enterpriseQualificationAuditInterface.auditManualAsync("N", "86201960232", null);
		logger.info("end");
	}
}
