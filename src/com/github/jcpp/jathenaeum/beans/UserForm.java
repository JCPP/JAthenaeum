package com.github.jcpp.jathenaeum.beans;

import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.github.jcpp.jathenaeum.db.dao.UserDAO;
import com.github.jcpp.jathenaeum.exceptions.LoginException;
import com.github.jcpp.jathenaeum.utils.Validator;

public class UserForm extends ActionForm{
	
	private String email;
	private String password;
	private String passwordControl;
	private String name;
	private String surname;
	private String bornDate;
	
	
	/*METODI*/
	
	public UserForm() {
		super();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordControl() {
		return passwordControl;
	}
	public void setPasswordControl(String passwordControl) {
		this.passwordControl = passwordControl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getBornDate() {
		return bornDate;
	}
	public void setBornDate(String date) {
		this.bornDate = date;
	}
	
	
	public ActionErrors validateLogin(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		/*
		System.out.println("email: " + email);
		System.out.println("password: " + password);
		*/
		
		//Check email
		if(email == null || email.isEmpty()){
			errors.add("email", new ActionMessage("user.email.empty"));
		}
		
		if(!Validator.isValidEmail(email)){
			errors.add("email", new ActionMessage("user.email.invalid"));
		}
		
		if(email != null && !UserDAO.exists(email)){
			errors.add("email", new ActionMessage("user.login.invalid"));
		}
		
		
		//Check password and password control
		if(password == null || password.isEmpty()) {
			errors.add("password", new ActionMessage("user.password.empty"));
		}
		
		
		//Check the user/password combination
		try {
			UserDAO.login(email, password);
		} catch (LoginException e) {
			if(errors.isEmpty()){
				errors.add("email", new ActionMessage("user.login.invalid"));
			}
		}
		
		return errors;
	}
	
	
	public ActionErrors validateSignUp(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		/*
		System.out.println("email: " + email);
		System.out.println("password: " + password);
		System.out.println("passwordControl: " + passwordControl);
		System.out.println("name: " + name);
		System.out.println("surname: " + surname);
		System.out.println("BornDate: " + bornDate);
		*/
		
		//Check email
		if(email == null || email.isEmpty()){
			errors.add("email", new ActionMessage("user.email.empty"));
		}
		
		if(!Validator.isValidEmail(email)){
			errors.add("email", new ActionMessage("user.email.invalid"));
		}
		
		if(email != null && UserDAO.exists(email)){
			errors.add("email", new ActionMessage("user.email.exists"));
		}
		
		
		//Check password and password control
		if(password == null || password.isEmpty()) {
			errors.add("password", new ActionMessage("user.password.empty"));
		}
		
		if(!password.equals(passwordControl)) {
			errors.add("password", new ActionMessage("user.password.different"));
		}
		
		//Check born date
		if(bornDate != null && !bornDate.isEmpty()){
			if(!Validator.isValidDate(bornDate)){
				errors.add("bornDate", new ActionMessage("user.borndate.invalid"));
			}
		}
		
		return errors;
	}

	
	
}
