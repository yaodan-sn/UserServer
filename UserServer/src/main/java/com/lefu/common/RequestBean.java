package com.lefu.common;

import java.io.Serializable;

/**
 * 
 * @author dan
 *
 */
public class RequestBean<T extends Serializable> extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private T requestParam;

	public RequestBean() {
		super();
	}

	public RequestBean(T requestParam) {
		super();
		this.requestParam = requestParam;
	}

	public T getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(T requestParam) {
		this.requestParam = requestParam;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestBean [requestParam=");
		builder.append(requestParam);
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
