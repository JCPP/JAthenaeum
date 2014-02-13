/**
 * 
 */
package com.github.jcpp.jathenaeum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


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
	 * Create an Author instance from a resultSet
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
