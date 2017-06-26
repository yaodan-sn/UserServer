package com.lefu.user.exception;

public class CamelResponseException extends ServiceException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5130049566056538018L;

	public CamelResponseException() {
		super();
	}

	public CamelResponseException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public CamelResponseException(String code, String message) {
		super(code, message);
	}

	public CamelResponseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CamelResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	public CamelResponseException(String message) {
		super(message);
	}

	
}
