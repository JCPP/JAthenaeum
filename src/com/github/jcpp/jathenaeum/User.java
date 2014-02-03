/**
 * 
 */
package com.github.jcpp.jathenaeum;

import java.util.Date;


/**
 * User class.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class User {
	private int id;
	private String email;
	private String password;
	private String name;
	private String surname;
	private Date bornDate;
	
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
	 * @return the bornDate
	 */
	public Date getBornDate() {
		return bornDate;
	}
	/**
	 * @param string the bornDate to set
	 */
	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}
	/**
	 * @return the cardNumber
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

}
