/**
 * 
 */
package com.github.jcpp.jathenaeum.exceptions;

/**
 * Utente Not Found exception. If a user is not found.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class UtenteNotFound extends Exception {
	
	public UtenteNotFound() {
		super();
	}
	
	public UtenteNotFound(String msg){
		super(msg);
	}
	
	public UtenteNotFound(String msg, Throwable cause){
		super(msg, cause);
	}

}
