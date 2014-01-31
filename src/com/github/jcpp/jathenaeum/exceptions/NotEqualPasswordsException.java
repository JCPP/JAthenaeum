/**
 * 
 */
package com.github.jcpp.jathenaeum.exceptions;

/**
 * Register exception. If the passwords of the user are different.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class NotEqualPasswordsException extends Exception {
	
	public NotEqualPasswordsException() {
		super();
	}
	
	public NotEqualPasswordsException(String msg){
		super(msg);
	}
	
	public NotEqualPasswordsException(String msg, Throwable cause){
		super(msg, cause);
	}

}
