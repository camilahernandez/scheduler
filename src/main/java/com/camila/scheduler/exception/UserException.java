package com.camila.scheduler.exception;

import org.springframework.http.HttpStatus;

public class UserException {
	private final String message;
	private final HttpStatus httpStatus;
	
	
	public UserException(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	
}
