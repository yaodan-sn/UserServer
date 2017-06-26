package com.lefu.common;

import java.io.Serializable;

/**
 * 
 * @author dan
 *
 */
public class ResponseBean<T extends Serializable> extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String responseCode;
	private String responseMessage;
	private T responseValue;

	public ResponseBean() {
		super();
	}

	public ResponseBean(String responseCode, String responseMessage) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public ResponseBean(String responseCode, String responseMessage, T responseValue) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.responseValue = responseValue;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public T getResponseValue() {
		return responseValue;
	}

	public void setResponseValue(T responseValue) {
		this.responseValue = responseValue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseBean [responseCode=");
		builder.append(responseCode);
		builder.append(", responseMessage=");
		builder.append(responseMessage);
		builder.append(", responseValue=");
		builder.append(responseValue);
		builder.append(", requestId=");
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
