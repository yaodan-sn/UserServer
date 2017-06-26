package com.lefu.user.exception;

import com.lefu.user.constant.ResultConstant;

public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6152587743946675839L;
	private String code;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public ServiceException(String code, String message) {
		super(message);
		this.code = code;
	}

	public ServiceException(String message) {
		this(ResultConstant.FAIL_CODE_98, message);
	}

	public ServiceException(String message, Throwable cause) {
		this(ResultConstant.FAIL_CODE_98, message, cause);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
