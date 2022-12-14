package com.fittoo.exception;

public class DateParseException extends RuntimeException{

	public DateParseException() {
	}

	public DateParseException(String message) {
		super(message);
	}

	public DateParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public DateParseException(Throwable cause) {
		super(cause);
	}

	public DateParseException(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
