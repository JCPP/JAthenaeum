/**
 * 
 */
package com.github.jcpp.jathenaeum;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Customer class.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class Customer {
	
	private int cardNumber;
	private String email;
	private String name;
	private String surname;
	
	/**
	 * Create a Customer instance.
	 */
	public Customer(){
		
	}
	
	/**
	 * Create a Customer instance from a ResultSet.
	 * @param resultSet
	 * @throws SQLException 
	 */
	public Customer(ResultSet resultSet) throws SQLException {
		cardNumber = resultSet.getInt(1);
		email = resultSet.getString(2);
		name = resultSet.getString(3);
		surname = resultSet.getString(4);
	}
	/**
	 * @return the cardNumber
	 */
	public int getCardNumber() {
		return cardNumber;
	}
	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
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
	
	

}
