package com.github.jcpp.jathenaeum.beans;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.github.jcpp.jathenaeum.db.dao.UtenteDAO;

public class UserRegisterForm extends ActionForm{

	private static final long serialVersionUID = 1;
	
	private String email;
	private String password;
	private String password_control;
	private String name;
	private String surname;
	private String bornDate;
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	/*METODI*/
	
	public UserRegisterForm() {
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
	public String getPassword_control() {
		return password_control;
	}
	public void setPassword_control(String password_control) {
		this.password_control = password_control;
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
	public void setDate(String date) {
		this.bornDate = date;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		
		//Check email
		if(email == null || email.isEmpty()){
			errors.add("email", new ActionMessage("signup.email.empty"));
		}
		
		if(Pattern.compile(EMAIL_PATTERN).matcher(email).matches()){
			errors.add("email", new ActionMessage("signup.email.invalid"));
		}
		
		if(email != null && UtenteDAO.exists(email)){
			errors.add("email", new ActionMessage("signup.email.exists"));
		}
		
		
		//Check password
		if(!password.equals(password_control)) {
			errors.add("password", new ActionMessage("signup.password.different"));
		}
		
		return errors;
	}

	
	
}
