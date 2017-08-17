package com.example.user.service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.common.json.JSONObject;
import com.example.common.BaseService;
import com.example.user.bean.KongAuthorizeResponseBean;
import com.example.user.bean.KongOauth2AuthorizeBean;
import com.example.user.bean.KongOauth2TokenParamBean;
import com.example.user.constant.IncrementerConstant;
import com.example.user.constant.RedisCacheConstant;
import com.example.user.constant.RedisKeyConstant;
import com.example.user.constant.ResultConstant;
import com.example.user.constant.StringConstant;
import com.example.user.dao.OperatorMapper;
import com.example.user.entity.Operator;
import com.example.user.exception.UserServiceException;
import com.example.util.RandomUtils;
import com.lefu.common.ResponseBean;
import com.lefu.user.bean.OperatorBean;
import com.lefu.user.bean.TokenBean;

@Service
public class OperatorService extends BaseService {
	@Resource
	private OperatorMapper operatorMapper;

	@Autowired
	private StringRedisTemplate template;

	@Resource
	private KongService kongService;

	@Value("${user.register.verify.msg}")
	private String userRegisterVerifyMsg;

	@Resource
	private IncrementerService incrementerService;

	@Cacheable(cacheNames = { RedisCacheConstant.OPERATOR_CACHE }, key = RedisCacheConstant.OPERATOR_KEY)
	public List<Operator> findOperator(OperatorBean operatorBean) {
		Operator record = new Operator();
		BeanUtils.copyProperties(operatorBean, record);
		return operatorMapper.selectByOperator(record);
	}

	/**
	 * 用户登录
	 * 
	 * @param operatorBean
	 * @return
	 */
	public ResponseBean<TokenBean> login(OperatorBean operatorBean) {

		logger.info("login:{}", operatorBean);
		Assert.notNull(operatorBean, "参数错误");
		Assert.hasText(operatorBean.getClientId(), "客户端ID为空");
		Assert.hasText(operatorBean.getClientSecret(), "客户端密钥为空");
		Assert.hasText(operatorBean.getHostname(), "域名为空");

		Assert.hasText(operatorBean.getUsername(), "请填写用户名");
		Assert.hasText(operatorBean.getPassword(), "请填写密码");

		/*
		 * KongClientAPP oauth2 =
		 * kongService.oauth2(operatorBean.getClientId()); Assert.isTrue(oauth2
		 * != null, "客户端非法");
		 */

		Operator record = new Operator();
		BeanUtils.copyProperties(operatorBean, record);
		record.setStatus(StringConstant.TRUE);
		List<Operator> list = operatorMapper.selectByOperator(record);
		Assert.notEmpty(list, "用户名或密码错误");
		Assert.isTrue(list.size() == 1, "参数错误");

		Operator o = list.get(0);
		String md5Password = DigestUtils.md5DigestAsHex(operatorBean.getPassword().getBytes(
				StandardCharsets.UTF_8));
		if (!md5Password.equalsIgnoreCase(o.getPassword())) {
			throw new UserServiceException("用户名或密码错误");
		}

		KongOauth2AuthorizeBean authorize = new KongOauth2AuthorizeBean();
		authorize.setHostname(operatorBean.getHostname());
		authorize.setClientId(operatorBean.getClientId());
		authorize.setResponseType("code");
		authorize.setScope("email address");
		authorize.setProvisionKey("function");
		authorize.setAuthenticatedUserid(o.getOwnerNo());
		// 通过kong服务获取授权信息
		KongAuthorizeResponseBean authorizeResponseBean = kongService.authorize(authorize);
		String redirectUri = authorizeResponseBean.getRedirectUri();

		String code = redirectUri.substring(redirectUri.indexOf("code=") + 5);

		KongOauth2TokenParamBean tokenParamBean = new KongOauth2TokenParamBean();
		tokenParamBean.setHost(operatorBean.getHostname());
		tokenParamBean.setClientId(operatorBean.getClientId());
		tokenParamBean.setClientSecret(operatorBean.getClientSecret());
		tokenParamBean.setGrantType("authorization_code");
		tokenParamBean.setCode(code);

		TokenBean oauth2Token = kongService.oauth2Token(tokenParamBean);
		Assert.hasText(oauth2Token.getAccessToken(), "获取token失败");

		ResponseBean<TokenBean> responseBean = new ResponseBean<>(ResultConstant.SUCCESS_CODE,
				null, oauth2Token);
		return responseBean;
	}

	public String verifyCode(String phone) {
		Assert.hasText(phone, "请填写手机号");
		String key = RedisKeyConstant.USER_REGISTER_PRE + phone;

		String value = template.boundValueOps(key).get();
		if (StringUtils.hasText(value)) {
			throw new UserServiceException("验证码已发送");
		}
		// 验证手机号是否合法

		String random6bit = RandomUtils.getRandom6bit();

		template.boundValueOps(key).set(random6bit, 120, TimeUnit.SECONDS);
		String content = String.format(userRegisterVerifyMsg, random6bit);
//		camelClientApiService.sendSMS(content, phone);

		JSONObject json = new JSONObject();
		json.put(ResultConstant.RESULT_CODE, ResultConstant.SUCCESS_CODE);
		return json.toString();

	}

	/**
	 * 校验指定key值的验证码是否正确 验证成功之后，清除缓存中的验证码
	 * 
	 * @param key
	 * @param code
	 */
	public void checkVerifyCode(String key, String code) {
		Assert.hasText(key, "参数错误");
		Assert.hasText(code, "请填写验证码");

		/*
		 * String value = template.boundValueOps(key).get(); if
		 * (!StringUtils.hasText(value)) { throw new
		 * UserServiceException("验证码错误"); }
		 * 
		 * if (!value.equalsIgnoreCase(code)) { throw new
		 * UserServiceException("验证码错误"); }
		 */
		template.delete(key);
	}

	public String saveUserRegister(OperatorBean operatorBean) {
		Assert.notNull(operatorBean, "参数错误");
		Assert.hasText(operatorBean.getVerifyCode(), "请填写验证码");
		Assert.hasText(operatorBean.getUsername(), "请填写手机号");
		Assert.hasText(operatorBean.getPassword(), "请填写密码");

		String key = RedisKeyConstant.USER_REGISTER_PRE + operatorBean.getUsername();
		checkVerifyCode(key, operatorBean.getVerifyCode());

		Operator record = new Operator();
		BeanUtils.copyProperties(operatorBean, record);

		String md5Password = DigestUtils.md5DigestAsHex(operatorBean.getPassword().getBytes(
				StandardCharsets.UTF_8));

		record.setPassword(md5Password);
		record.setStatus(StringConstant.TRUE);
		int insertSelective = this.operatorMapper.insertSelective(record);
		logger.info("operatorMapper.insertSelective success size: {}", insertSelective);

		JSONObject json = new JSONObject();
		json.put(ResultConstant.RESULT_CODE, ResultConstant.SUCCESS_CODE);
		return json.toString();

	}

	public void nextId() throws InterruptedException {

		Operator record = new Operator();
		record.setUsername("18301310622");
		this.operatorMapper.insert(record);

		for (int i = 0; i < 1000; i++) {
			try {
				logger.info("nextLong {}",
						incrementerService.nextLongValue(IncrementerConstant.CUSTOMER_NO));
			} catch (DataAccessException e) {
				i--;
				logger.error(e.getMessage());
			}
		}

		Thread.sleep(999999);
	}

}