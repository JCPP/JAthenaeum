package com.github.jcpp.jathenaeum.exceptions;

/**
 * Registration exception. If a user try to register itself and it registration info
 * are wrong.
*/
public class RegistrationException extends Exception{

	private static final long serialVersionUID = 1L;

	public RegistrationException() {
		super();
	}
	
	public RegistrationException(String msg){
		super(msg);
	}
	
	public RegistrationException(String msg, Throwable cause){
		super(msg, cause);
	}
}
