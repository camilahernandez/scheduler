package com.camila.scheduler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

	@ExceptionHandler(UserRequestException.class)
	public ResponseEntity<Object> handlerUserException(UserRequestException e){
		
		UserException exeption = new UserException(e.getMessage(),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(exeption,HttpStatus.BAD_REQUEST);
		
	}
}
