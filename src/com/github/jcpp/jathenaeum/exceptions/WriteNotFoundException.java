/**
 * 
 */
package com.github.jcpp.jathenaeum.exceptions;

/**
 * Write not found exception. If the write object isn't found.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class WriteNotFoundException extends Exception {
	
	public WriteNotFoundException() {
		super();
	}
	
	public WriteNotFoundException(String msg){
		super(msg);
	}
	
	public WriteNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}

}
