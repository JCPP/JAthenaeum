/**
 * 
 */
package com.github.jcpp.jathenaeum.exceptions;

/**
 * Login exception. If a user try to login and his login info
 * are wrong.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class LoginException extends Exception {
	
	public LoginException() {
		super();
	}
	
	public LoginException(String msg){
		super(msg);
	}
	
	public LoginException(String msg, Throwable cause){
		super(msg, cause);
	}
	

}
