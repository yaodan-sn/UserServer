package com.lefu.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.lefu.common.BaseService;
import com.lefu.user.bean.KongApiBean;
import com.lefu.user.bean.KongAuthorizeResponseBean;
import com.lefu.user.bean.KongClientAPP;
import com.lefu.user.bean.KongClientAppListBean;
import com.lefu.user.bean.KongOauth2AuthorizeBean;
import com.lefu.user.bean.KongOauth2TokenParamBean;
import com.lefu.user.bean.TokenBean;
import com.lefu.user.util.HttpUtil;

@Service
@PropertySource("classpath:system.properties")
public class KongService extends BaseService {

	@Value("${kong.app.url}")
	private String kongAppUrl;
	@Value("${kong.admin.url}")
	private String kongAdminUrl;
	@Value("${kong.api.url}")
	private String kongApiUrl;

	/**
	 * API注册
	 * 
	 * @param kongApiBean
	 * @return
	 */
	public String apis(KongApiBean kongApiBean) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("name", kongApiBean.getName());
		param.put("uris", kongApiBean.getUris());
		param.put("upstream_url", kongApiBean.getUpstreamUrl());
		param.put("strip_uri", kongApiBean.getStripUri());
		String post = HttpUtil.post(kongAdminUrl + "/apis", null, param);
		return post;
	}

	/**
	 * api关联插件
	 * 
	 * @param serverName
	 * @return
	 */
	public String apiAddPlugin(String serverName) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("name", "oauth2");
		param.put("config.scopes", "email, phone, address");
		param.put("config.mandatory_scope", "true");
		param.put("config.enable_authorization_code", "true");
		String post = HttpUtil
				.post(kongAdminUrl + "/apis/" + serverName + "/plugins/", null, param);
		return post;
	}

	/**
	 * 添加消费者
	 * 
	 * @param username
	 * @return
	 */
	public String consumers(String username) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("username", username);
		String post = HttpUtil.post(kongAdminUrl + "/consumers", null, param);
		return post;
	}

	public String consumersOauth(String consumerId, String name, String redirectUri) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("name", name);
		param.put("redirect_uri", redirectUri);
		String post = HttpUtil.post(kongAdminUrl + "/consumers/" + consumerId + "/oauth2/", null,
				param);
		return post;
	}

	public KongClientAPP oauth2(String clientId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("client_id", clientId);
		String get = HttpUtil.get(kongAdminUrl + "/oauth2", null, param);
		Gson gson = new Gson();
		KongClientAppListBean appBean = gson.fromJson(get, KongClientAppListBean.class);
		List<KongClientAPP> apps = appBean.getApps();
		if (!CollectionUtils.isEmpty(apps)) {
			return apps.get(0);
		} else {
			return null;
		}
	}

	public KongAuthorizeResponseBean authorize(KongOauth2AuthorizeBean authorize) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("client_id", authorize.getClientId());
		param.put("response_type", authorize.getResponseType());
		param.put("scope", authorize.getScope());
		param.put("provision_key", authorize.getProvisionKey());
		param.put("authenticated_userid", authorize.getAuthenticatedUserid());
		Map<String, String> header = new HashMap<String, String>();
		header.put("host", authorize.getHostname());
		String post = HttpUtil.post(kongApiUrl + "/oauth2/authorize", header, param);
		Gson gson = new Gson();
		KongAuthorizeResponseBean responseBean = gson.fromJson(post,
				KongAuthorizeResponseBean.class);
		return responseBean;
	}

	public TokenBean oauth2Token(KongOauth2TokenParamBean tokenBean) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("client_id", tokenBean.getClientId());
		param.put("client_secret", tokenBean.getClientSecret());
		param.put("grant_type", tokenBean.getGrantType());
		param.put("code", tokenBean.getCode());
		Map<String, String> header = new HashMap<String, String>();
		header.put("host", tokenBean.getHost());
		String post = HttpUtil.post(kongApiUrl + "/oauth2/token", header, param);
		Gson gson = new Gson();
		TokenBean responseBean = gson.fromJson(post,
				TokenBean.class);
		return responseBean;
	}

	public String getKongAppUrl() {
		return kongAppUrl;
	}

	public void setKongAppUrl(String kongAppUrl) {
		this.kongAppUrl = kongAppUrl;
	}

	public String getKongAdminUrl() {
		return kongAdminUrl;
	}

	public void setKongAdminUrl(String kongAdminUrl) {
		this.kongAdminUrl = kongAdminUrl;
	}

	public String getKongApiUrl() {
		return kongApiUrl;
	}

	public void setKongApiUrl(String kongApiUrl) {
		this.kongApiUrl = kongApiUrl;
	}

}