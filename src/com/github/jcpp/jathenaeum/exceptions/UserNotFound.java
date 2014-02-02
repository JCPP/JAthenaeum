/**
 * 
 */
package com.github.jcpp.jathenaeum.exceptions;

/**
 * User Not Found exception. If a user is not found.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class UserNotFound extends Exception {
	
	public UserNotFound() {
		super();
	}
	
	public UserNotFound(String msg){
		super(msg);
	}
	
	public UserNotFound(String msg, Throwable cause){
		super(msg, cause);
	}

}
