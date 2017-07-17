package com.xs.micro.order.extension.exception;

public class InvalidParamException extends RuntimeException {

	private static final long serialVersionUID = 1837289007687310288L;

	public InvalidParamException() {
	}

	public InvalidParamException(String message) {
		super(message);
	}

	public InvalidParamException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidParamException(Throwable cause) {
		super(cause);
	}
}
