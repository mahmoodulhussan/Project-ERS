package com.ers.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public UsernameAlreadyExistsException() {
		super("A username was already created");
	}
	
}
