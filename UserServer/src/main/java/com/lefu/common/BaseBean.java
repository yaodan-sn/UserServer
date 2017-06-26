package com.lefu.common;

import java.io.Serializable;
import java.util.UUID;

import com.lefu.user.util.IpUtil;

/**
 * 
 * @author dan
 *
 */
public abstract class BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 请求ID */
	protected String requestId = UUID.randomUUID().toString().replaceAll("-", "");

	/** 系统标识 */
	protected String systemCode;

	/** 业务描述 */
	protected String businessDescription;

	/** 操作人 */
	protected String operator;

	/**
	 * 请求IP
	 */
	protected String requestHostIP = IpUtil.getIp();

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getBusinessDescription() {
		return businessDescription;
	}

	public void setBusinessDescription(String businessDescription) {
		this.businessDescription = businessDescription;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRequestHostIP() {
		return requestHostIP;
	}

	public void setRequestHostIP(String requestHostIP) {
		this.requestHostIP = requestHostIP;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseBean [requestId=");
		builder.append(requestId);
		builder.append(", systemCode=");
		builder.append(systemCode);
		builder.append(", businessDescription=");
		builder.append(businessDescription);
		builder.append(", operator=");
		builder.append(operator);
		builder.append(", requestHostIP=");
		builder.append(requestHostIP);
		builder.append("]");
		return builder.toString();
	}


}
