package com.lefu.user.exception;

public class HttpException extends ServiceException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5130049566056538018L;

	public HttpException() {
		super();
	}

	public HttpException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public HttpException(String code, String message) {
		super(code, message);
	}

	public HttpException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public HttpException(String message, Throwable cause) {
		super(message, cause);
	}

	public HttpException(String message) {
		super(message);
	}

	
}
