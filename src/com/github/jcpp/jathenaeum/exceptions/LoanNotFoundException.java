/**
 * 
 */
package com.github.jcpp.jathenaeum.exceptions;

/**
 * Loan not found exception. If the loan isn't found.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class LoanNotFoundException extends Exception {
	
	public LoanNotFoundException() {
		super();
	}
	
	public LoanNotFoundException(String msg){
		super(msg);
	}
	
	public LoanNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}

}
