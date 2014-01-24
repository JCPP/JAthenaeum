/**
 * 
 */
package com.github.jcpp.jathenaeum.exceptions;

/**
 * Book not found exception. If the book isn't found.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class BookNotFoundException extends Exception {
	
	public BookNotFoundException() {
		super();
	}
	
	public BookNotFoundException(String msg){
		super(msg);
	}
	
	public BookNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}

}
