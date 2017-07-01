package com.lefu.test.user.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.util.Assert;

import com.lefu.test.common.WebAppContextSetupTest;
import com.lefu.user.bean.KongApiBean;
import com.lefu.user.bean.KongClientAPP;
import com.lefu.user.bean.KongOauth2AuthorizeBean;
import com.lefu.user.bean.KongOauth2TokenParamBean;
import com.lefu.user.bean.TokenBean;
import com.lefu.user.service.KongService;
import com.lefu.util.HttpUtil;

public class KongServiceTest extends WebAppContextSetupTest {

	@Resource
	private KongService kongService;

	@Test
	public void testApis() {
		KongApiBean kongApiBean = new KongApiBean();

		kongApiBean.setName("mockbin");
		kongApiBean.setUris("/mockbin");
		kongApiBean.setUpstreamUrl("http://mockbin.org/request");
		kongApiBean.setStripUri("true");
		kongService.apis(kongApiBean);
	}

	@Test
	public void testRequestUpStreamService() {
		HttpUtil.post(kongService.getKongAppUrl() + "/mockbin");
	}

	@Test
	public void testApiAddPlugin() {
		kongService.apiAddPlugin("mockbin");
	}

	@Test
	public void testConsumers() {
		kongService.consumers("dan.yao");
	}

	@Test
	public void testConsumersOauth() {
		kongService.consumersOauth("e0c60c32-744e-4f8d-9111-8572ceddbae9", "Hello World App",
				"http://www.json.cn/");
	}

	@Test
	public void testOauth2() {
		KongClientAPP oauth2 = kongService.oauth2("fe5ef422cff64a088982c53e6a2e9c45");
		Assert.notNull(oauth2, "查询客户端失败");
	}

	@Test
	public void testAuthorize() {
		KongOauth2AuthorizeBean authorize = new KongOauth2AuthorizeBean();
		authorize.setClientId("fe5ef422cff64a088982c53e6a2e9c45");
		authorize.setResponseType("code");
		authorize.setScope("email address");
		authorize.setProvisionKey("function");
		authorize.setAuthenticatedUserid("zhang.san");
		kongService.authorize(authorize);
	}

	@Test
	public void testAuth2Token() {
		KongOauth2TokenParamBean tokenBean = new KongOauth2TokenParamBean();
		tokenBean.setHost("v.lefu8.com");
		tokenBean.setClientId("fe5ef422cff64a088982c53e6a2e9c45");
		tokenBean.setClientSecret("330953a9d0144110b81875979d480cef");
		tokenBean.setGrantType("authorization_code");
		tokenBean.setCode("17749faa302a41db9d7d0f189f6885e5");
		TokenBean oauth2Token = kongService.oauth2Token(tokenBean);
		Assert.hasText(oauth2Token.getAccessToken(), "获取token失败");
	}

}