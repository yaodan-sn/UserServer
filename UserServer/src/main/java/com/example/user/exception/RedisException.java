package com.example.user.exception;

public class RedisException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8110331832501821518L;

	public RedisException() {
		super();
	}

	public RedisException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public RedisException(String code, String message) {
		super(code, message);
	}

	public RedisException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RedisException(String message, Throwable cause) {
		super(message, cause);
	}

	public RedisException(String message) {
		super(message);
	}

}
