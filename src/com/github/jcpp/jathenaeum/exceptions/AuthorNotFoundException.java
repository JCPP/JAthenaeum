/**
 * 
 */
package com.github.jcpp.jathenaeum.exceptions;

/**
 * Author not found exception. If the author isn't found.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class AuthorNotFoundException extends Exception {
	
	public AuthorNotFoundException() {
		super();
	}
	
	public AuthorNotFoundException(String msg){
		super(msg);
	}
	
	public AuthorNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}

}
