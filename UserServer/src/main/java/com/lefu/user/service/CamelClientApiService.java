package com.lefu.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lefu.camel.client.api.impl.CamelClientApiImpl;
import com.lefu.camel.remote.bean.CamelResponse;
import com.lefu.camel.remote.bean.Goods;
import com.lefu.camel.remote.bean.MessageLevel;
import com.lefu.camel.remote.bean.MessageSendType;
import com.lefu.camel.remote.bean.MessageType;
import com.lefu.camel.remote.bean.MessgReceiver;
import com.lefu.user.exception.CamelResponseException;

/**
 * 
 * @author dan
 *
 */
@Service
public class CamelClientApiService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private CamelClientApiImpl camelClientApiImpl;

	@Value("${com.lefu.camel.token}")
	private String camelToken;

	@Value("${com.lefu.camel.appcode}")
	private String appCode;

	@Value("${com.lefu.camel.busitype}")
	private String busiType;

	public void sendWarning(String errorMsg) {
		try {
			logger.error(errorMsg);
			Goods goods = new Goods();
			goods.setMessgType(MessageType.MONITOR);
			goods.setMessgLevel(MessageLevel.WARNING);
			goods.setAppCode(appCode);
			goods.setBusiType(busiType);
			goods.setToken(camelToken);
			goods.setTitle("警告");
			if (StringUtils.isNotBlank(errorMsg)) {
				if (errorMsg.length() > 500) {
					errorMsg = errorMsg.substring(0, 500);
				}
			} else {
				errorMsg = "无详细信息";
			}
			goods.setContent(errorMsg);
			goods.setRegion("北京");
			goods.setDatetime(new Date());
			CamelResponse response = camelClientApiImpl.send(goods);
			logger.error("" + response);
		} catch (Exception e) {
			logger.error("消息发送失败: " + e.getMessage(), e);
		}
	}

	public void sendSMS(String content, String phone) {
		logger.info("content:{}, phone:{}", content, phone);
		Goods goods = new Goods();
		goods.setMessgType(MessageType.NOTE);
		goods.setMessgLevel(MessageLevel.INFO);

		goods.setAppCode(appCode);
		goods.setBusiType(busiType);
		goods.setToken(camelToken);

		goods.setContent(content);
		List<MessageSendType> messgSendTypes = new ArrayList<MessageSendType>();
		messgSendTypes.add(MessageSendType.SMS);
		goods.setMessgSendTypes(messgSendTypes);
		MessgReceiver messgReceiver = new MessgReceiver();
		goods.setMessgReceiver(messgReceiver);
		messgReceiver.setPhone(phone);
		CamelResponse response = this.camelClientApiImpl.send(goods);
		logger.info("this.camelClientApiImpl.send response={}", response);
		if (response == null || !response.getResult()) {
			throw new CamelResponseException("短信发送失败" + response);
		}
	}

	public CamelClientApiImpl getCamelClientApiImpl() {
		return camelClientApiImpl;
	}

	public void setCamelClientApiImpl(CamelClientApiImpl camelClientApiImpl) {
		this.camelClientApiImpl = camelClientApiImpl;
	}

	public String getCamelToken() {
		return camelToken;
	}

	public void setCamelToken(String camelToken) {
		this.camelToken = camelToken;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

}
