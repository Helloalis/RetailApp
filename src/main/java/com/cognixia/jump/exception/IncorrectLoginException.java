package com.cognixia.jump.exception;

public class IncorrectLoginException extends Exception {
	
	private static final long serialVersionUID = 1L;
	public IncorrectLoginException(String msg) {
		super(msg);
	}
}
