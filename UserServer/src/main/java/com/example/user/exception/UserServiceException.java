package com.example.user.exception;

public class UserServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7727118927058257467L;

	public UserServiceException() {
		super();
	}

	public UserServiceException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public UserServiceException(String code, String message) {
		super(code, message);
	}

	public UserServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserServiceException(String message) {
		super(message);

	}

}
