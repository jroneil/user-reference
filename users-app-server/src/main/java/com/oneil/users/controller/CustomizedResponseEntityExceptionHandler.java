package com.oneil.users.controller;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.oneil.users.exception.ExceptionResponse;
import com.oneil.users.exception.PermissionNotFoundException;
import com.oneil.users.exception.UserNotFoundException;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler 
extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		
		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false)), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PermissionNotFoundException.class)
	public final ResponseEntity<Object> handlePermissionNotFoundException(PermissionNotFoundException ex, WebRequest request) {
		
		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false)), HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false)), HttpStatus.BAD_REQUEST);
		
	}	
}
