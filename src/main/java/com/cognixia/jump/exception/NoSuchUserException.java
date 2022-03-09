package com.cognixia.jump.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NoSuchUserException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NoSuchUserException(String msg) {
		super(msg);
	}
	

}