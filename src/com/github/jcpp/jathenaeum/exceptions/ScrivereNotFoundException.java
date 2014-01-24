/**
 * 
 */
package com.github.jcpp.jathenaeum.exceptions;

/**
 * Scrivere not found exception. If the scrivere isn't found.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class ScrivereNotFoundException extends Exception {
	
	public ScrivereNotFoundException() {
		super();
	}
	
	public ScrivereNotFoundException(String msg){
		super(msg);
	}
	
	public ScrivereNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}

}
