package com.ers.exceptions;

public class UserNameAlreadyExistException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	

	public UserNameAlreadyExistException() {
		super("A username was already created");
	}
	
}
