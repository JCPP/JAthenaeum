/**
 * 
 */
package com.github.jcpp.jathenaeum.exceptions;

/**
 * Autore not found exception. If the autore isn't found.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class AutoreNotFoundException extends Exception {
	
	public AutoreNotFoundException() {
		super();
	}
	
	public AutoreNotFoundException(String msg){
		super(msg);
	}
	
	public AutoreNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}

}
