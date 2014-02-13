/**
 * 
 */
package com.github.jcpp.jathenaeum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import com.github.jcpp.jathenaeum.beans.AuthorForm;
import com.github.jcpp.jathenaeum.utils.Converter;
import com.github.jcpp.jathenaeum.utils.Validator;


/**
 * Author class.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class Author {
	
	private int id;
	private String name;
	private String surname;
	private String photo;
	private Date bornDate;
	private String biography;
	
	
	/**
	 * Create an Author instance.
	 */
	public Author(){
		
	}
	
	
	/**
	 * Create an Author instance from a ResultSet.
	 * @param resultSet
	 * @throws SQLException 
	 */
	public Author(ResultSet resultSet) throws SQLException {
		id = resultSet.getInt(1);
		name = resultSet.getString(2);
		surname = resultSet.getString(3);
		photo = resultSet.getString(4);
		bornDate = resultSet.getDate(5);
		biography = resultSet.getString(6);
	}
	
	/**
	 * Create an Author instance from an AuthorForm.
	 * @param authorForm
	 * @throws ParseException 
	 */
	public Author(AuthorForm authorForm) throws ParseException {
		name = authorForm.getName();
		surname = authorForm.getSurname();
		photo = authorForm.getPhoto();
		
		if(Validator.isValidDate(authorForm.getBornDate())){
			bornDate = Converter.fromStringToDate(authorForm.getBornDate());
		}
		
		biography = authorForm.getBiography();
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}
	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	/**
	 * @return the dataNascita
	 */
	public Date getBornDate() {
		return bornDate;
	}
	/**
	 * @param bornDate the bornDate to set
	 */
	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}
	/**
	 * @return the biography
	 */
	public String getBiography() {
		return biography;
	}
	/**
	 * @param biography the biography to set
	 */
	public void setBiography(String biography) {
		this.biography = biography;
	}

}
