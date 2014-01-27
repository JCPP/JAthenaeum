package com.github.jcpp.jathenaeum.beans;

import java.sql.Date;

import org.apache.struts.action.ActionForm;

public class UserRegisterForm extends ActionForm{

	private static final long serialVersionUID = 1;
	
	private String email;
	private String password;
	private String password_control;
	private String name;
	private String surname;
	private Date born_date;
	
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
	public Date getBornDate() {
		return born_date;
	}
	public void setDate(Date date) {
		this.born_date = date;
	}
	
	
	
}
