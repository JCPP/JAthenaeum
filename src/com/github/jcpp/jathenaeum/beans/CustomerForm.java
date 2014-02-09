/**
 * 
 */
package com.github.jcpp.jathenaeum.beans;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.github.jcpp.jathenaeum.utils.Validator;

/**
 * 
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class CustomerForm extends ActionForm {
	
	private String email;
	private String name;
	private String surname;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		/*
		System.out.println("email: " + email);
		System.out.println("name: " + name);
		System.out.println("surname: " + surname);
		*/
		
		//Check name
		if(name == null || name.isEmpty()){
			errors.add("name", new ActionMessage("author.name.empty"));
		}
		
		//Check surname
		if(surname == null || surname.isEmpty()){
			errors.add("surname", new ActionMessage("author.surname.empty"));
		}
		
		//Check email
		if(email == null || email.isEmpty()){
			errors.add("email", new ActionMessage("signup.email.empty"));
		}
		
		if(!Validator.isValidEmail(email)){
			errors.add("email", new ActionMessage("signup.email.invalid"));
		}
		
		return errors;
	}

}
