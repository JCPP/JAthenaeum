/**
 * 
 */
package com.github.jcpp.jathenaeum.exceptions;

/**
 * Libro not found exception. If the libro isn't found.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class LibroNotFoundException extends Exception {
	
	public LibroNotFoundException() {
		super();
	}
	
	public LibroNotFoundException(String msg){
		super(msg);
	}
	
	public LibroNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}

}
