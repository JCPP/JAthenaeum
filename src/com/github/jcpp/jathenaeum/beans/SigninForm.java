/**
 * 
 */
package com.github.jcpp.jathenaeum.beans;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.github.jcpp.jathenaeum.db.dao.UtenteDAO;
import com.github.jcpp.jathenaeum.exceptions.LoginException;
import com.github.jcpp.jathenaeum.utils.Validator;

/**
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class SigninForm extends ActionForm {

	private String email;
	private String password;
	
	public SigninForm(){
		super();
	}
	
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		/*
		System.out.println("email: " + email);
		System.out.println("password: " + password);
		*/
		
		//Check email
		if(email == null || email.isEmpty()){
			errors.add("email", new ActionMessage("signup.email.empty"));
		}
		
		if(!Validator.isValidEmail(email)){
			errors.add("email", new ActionMessage("signup.email.invalid"));
		}
		
		if(email != null && !UtenteDAO.exists(email)){
			errors.add("email", new ActionMessage("signin.user.invalid"));
		}
		
		
		//Check password and password control
		if(password == null || password.isEmpty()) {
			errors.add("password", new ActionMessage("signup.password.empty"));
		}
		
		try {
			UtenteDAO.login(email, password);
		} catch (LoginException e) {
			if(errors.isEmpty()){
				errors.add("email", new ActionMessage("signin.user.invalid"));
			}
		}
		
		return errors;
	}
}
