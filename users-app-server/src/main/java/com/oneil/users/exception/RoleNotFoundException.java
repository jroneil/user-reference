package com.oneil.users.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public RoleNotFoundException(String message) {
		super(message);
	}
	
	public RoleNotFoundException(String message, Throwable t) {
		super(message, t);
	}
}