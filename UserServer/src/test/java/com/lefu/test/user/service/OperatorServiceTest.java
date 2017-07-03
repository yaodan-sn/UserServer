package com.lefu.test.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.util.Assert;

import com.lefu.common.ResponseBean;
import com.lefu.test.common.WebAppContextSetupTest;
import com.lefu.user.bean.OperatorBean;
import com.lefu.user.bean.TokenBean;
import com.lefu.user.constant.ResultConstant;
import com.lefu.user.entity.Operator;
import com.lefu.user.service.OperatorService;

public class OperatorServiceTest extends WebAppContextSetupTest {
	@Resource
	private OperatorService operatorService;
	
	@Test
	public void testLogin() {
		OperatorBean operator = new OperatorBean();
		operator.setClientId("fe5ef422cff64a088982c53e6a2e9c45");
		operator.setClientSecret("330953a9d0144110b81875979d480cef");
		operator.setHostname("v.lefu8.com");
		operator.setUsername("13085209546");
		operator.setPassword("1");
		operator.setOwnerNo("8614199593");
		ResponseBean<TokenBean> login = operatorService.login(operator);
		Assert.isTrue(ResultConstant.SUCCESS_CODE.equals(login.getResponseCode()),
				login.getResponseMessage());
	}

	@Test
	public void testFindOperator() {
		OperatorBean operator = new OperatorBean();
		operator.setUsername("18680000166");
		operator.setOwnerNo("8616154829");
		List<Operator> list = operatorService.findOperator(operator);
		logger.info("operatorService.findOperator {}", list);
		Assert.notEmpty(list, "操作员不存在: " + operator);
	}

	@Test
	public void testVerifyCode() {
		operatorService.verifyCode("18301311235");
	}

	@Test
	public void testSaveUserRegister() {
		OperatorBean operatorBean = new OperatorBean();
		operatorBean.setUsername("18301311237");
		operatorBean.setPassword("123456");
		operatorBean.setVerifyCode("552489");

		operatorService.saveUserRegister(operatorBean);
	}
	
	@Test
	public void testNextId() throws InterruptedException{
		operatorService.nextId();
	}
}
