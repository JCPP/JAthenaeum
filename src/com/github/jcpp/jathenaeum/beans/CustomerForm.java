/**
 * 
 */
package com.github.jcpp.jathenaeum.beans;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.github.jcpp.jathenaeum.db.dao.AuthorDAO;
import com.github.jcpp.jathenaeum.db.dao.CustomerDAO;
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
			errors.add("email", new ActionMessage("user.email.empty"));
		}
		
		if(!Validator.isValidEmail(email)){
			errors.add("email", new ActionMessage("user.email.invalid"));
		}
		
		return errors;
	}
	
	
	public ActionErrors validateEmailInDb(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		if(CustomerDAO.getNumberByEmail(email) > 0){
			errors.add("email", new ActionMessage("customer.email.alreadyused"));
		}
		
		return errors;
	}
	
	
	public ActionErrors validateId(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		if(request.getParameter("id") == null){
			errors.add("id", new ActionMessage("customer.id.empty"));
		}
		else if(!Validator.isValidInt(request.getParameter("id"))){
			errors.add("id", new ActionMessage("customer.id.invalid"));
		}
		else if(!CustomerDAO.exists(Integer.parseInt(request.getParameter("id")))){
			errors.add("id", new ActionMessage("customer.id.invalid"));
		}
		
		return errors;
	}

}
