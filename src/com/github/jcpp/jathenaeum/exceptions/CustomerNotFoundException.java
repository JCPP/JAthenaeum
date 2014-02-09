/**
 * 
 */
package com.github.jcpp.jathenaeum.exceptions;

/**
 * Customer not found exception. If the customer isn't found.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class CustomerNotFoundException extends Exception {
	
	public CustomerNotFoundException() {
		super();
	}
	
	public CustomerNotFoundException(String msg){
		super(msg);
	}
	
	public CustomerNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}

}
