package com.xs.micro.order.extension.exception;

public class BadOperationException extends RuntimeException {

	private static final long serialVersionUID = 5224436711619531325L;

	public BadOperationException() {
	}

	public BadOperationException(String message) {
		super(message);
	}

	public BadOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadOperationException(Throwable cause) {
		super(cause);
	}
}
