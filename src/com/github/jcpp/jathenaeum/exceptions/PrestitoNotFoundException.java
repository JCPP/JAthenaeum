/**
 * 
 */
package com.github.jcpp.jathenaeum.exceptions;

/**
 * Prestito not found exception. If the prestito isn't found.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class PrestitoNotFoundException extends Exception {
	
	public PrestitoNotFoundException() {
		super();
	}
	
	public PrestitoNotFoundException(String msg){
		super(msg);
	}
	
	public PrestitoNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}

}
