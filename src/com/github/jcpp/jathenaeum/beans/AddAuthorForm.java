package com.github.jcpp.jathenaeum.beans;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.github.jcpp.jathenaeum.utils.Validator;

public class AddAuthorForm extends ActionForm{

	private static final long serialVersionUID = 1;
	
	private String name;
	private String surname;
	private String photo;
	private String bornDate;
	private String biography;
	
	public AddAuthorForm() {
		super();
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getBornDate() {
		return bornDate;
	}

	public void setBornDate(String date) {
		this.bornDate = date;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		/*
		System.out.println("name: " + name);
		System.out.println("surname: " + surname);
		System.out.println("photo: " + photo);
		System.out.println("bornDate: " + bornDate);
		System.out.println("biography: " + biography);
		*/
		
		//Check name
		if(name == null || name.isEmpty()){
			errors.add("name", new ActionMessage("author.name.empty"));
		}
		
		//Check surname
		if(surname == null || surname.isEmpty()){
			errors.add("surname", new ActionMessage("author.surname.empty"));
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
